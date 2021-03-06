package com.zhihui.order.api.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.context.MyContext;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.entity.OrderPrice;
import com.zhihui.order.api.request.OrderAddRequest;
import com.zhihui.order.api.response.OrderAddResponse;
import com.zhihui.order.bo.ChainBo;
import com.zhihui.order.bo.OrderBo;
import com.zhihui.order.bo.OrderGuestBo;
import com.zhihui.order.bo.OrderPriceBo;
import com.zhihui.order.bo.RoomTypeBo;
import com.zhihui.order.model.ChainModel;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.model.OrderPriceModel;
import com.zhihui.order.model.RoomTypeModel;
import com.zhihui.order.partner.PartnerService;

@Service
public class OrderAddBo extends ApiBo<OrderAddRequest> {
	@Autowired
	private ChainBo chainBo;
	@Autowired
	private RoomTypeBo roomTypeBo;
	@Autowired
	private OrderBo orderBo;
	@Autowired
	private OrderGuestBo orderGuestBo;
	@Autowired
	private OrderPriceBo orderPriceBo;

	@Override
	public Class<OrderAddRequest> getRequestType() {
		return OrderAddRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			OrderAddResponse rsp = (OrderAddResponse) this.apiResponse;
			rsp.setSuccess(false);

			// check the parameters
			// 分店
			ChainModel chainModel = this.chainBo.getById(this.apiRequest.getChainId());
			if (chainModel == null)
				throw new BusinessException("不存在此分店");
			// 房型
			RoomTypeModel roomTypeModel = this.roomTypeBo.getById(this.apiRequest.getRoomTypeId());
			if (roomTypeModel == null)
				throw new BusinessException("不存在此房型");
			// 此合作人
			PartnerService partnerService = (PartnerService) MyContext.getRootApplicationContext().getBean("order.partner." + this.apiRequest.getPartnerId());
			if (partnerService == null)
				throw new BusinessException("不存在此合作人");

			OrderModel orderModel = new OrderModel();
			orderModel.setPartnerId(this.apiRequest.getPartnerId());
			orderModel.setChainId(this.apiRequest.getChainId());
			orderModel.setMebId(this.apiRequest.getMebId());
			orderModel.setMebName(this.apiRequest.getMebName());
			orderModel.setMebGender(this.apiRequest.getMebGender());
			orderModel.setMebMobile(this.apiRequest.getMebMobile());
			orderModel.setRoomTypeId(this.apiRequest.getRoomTypeId());
			orderModel.setNum(this.apiRequest.getNum());
			orderModel.setArrEndOfDay(new java.sql.Date(this.apiRequest.getArrEndOfDay().getTime()));
			orderModel.setDepEndOfDay(new java.sql.Date(this.apiRequest.getDepEndOfDay().getTime()));
			// orderModel.setReserveTime(null);
			orderModel.setEarlyArrTime(this.apiRequest.getEarlyArrTime() == null ? null : new Timestamp(this.apiRequest.getEarlyArrTime().getTime()));
			orderModel.setLastArrTime(this.apiRequest.getLastArrTime() == null ? null : new Timestamp(this.apiRequest.getLastArrTime().getTime()));
			orderModel.setChannelSellerId(this.apiRequest.getChannelSellerId());
			orderModel.setSellerId(this.apiRequest.getSellerId());
			// orderModel.setOuterOrderSn(null);
			orderModel.setOuterOrderName(partnerService.getDesc());
			orderModel.setMessage(this.apiRequest.getMessage());
			// 0、影子订单；1、下单中；2、已下单；3、取消中；4、已取消；5、NOSHOW；6、入住；7、离店
			orderModel.setFlag(1);// 下单中
			orderModel.setCreateOprtId(this.apiRequest.getOprtId());
			orderModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			orderModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			orderModel.setInnRemark(null);
			orderModel.setRemark("下单");
			this.orderBo.add(orderModel);
			// order-guest
			List<OrderGuestModel> orderGuestModels = new ArrayList<OrderGuestModel>();
			for (OrderGuest e : this.apiRequest.getOrderGuests()) {
				OrderGuestModel orderGuestModel = new OrderGuestModel();
				orderGuestModel.setOrderId(orderModel.getOrderId());
				orderGuestModel.setContactName(e.getContactName());
				orderGuestModel.setContactMobile(e.getContactMobile());
				orderGuestModel.setRemark(e.getRemark());
				orderGuestModel.setCreateOprtId(this.apiRequest.getOprtId());
				orderGuestModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				orderGuestModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				this.orderGuestBo.add(orderGuestModel);
				orderGuestModels.add(orderGuestModel);
			}
			// order-price
			List<OrderPriceModel> orderPriceModels = new ArrayList<OrderPriceModel>();
			for (OrderPrice e : this.apiRequest.getOrderPrices()) {
				OrderPriceModel orderPriceModel = new OrderPriceModel();
				orderPriceModel.setOrderId(orderModel.getOrderId());
				orderPriceModel.setEndOfday(new java.sql.Date(e.getEndOfDay().getTime()));
				orderPriceModel.setPrice(e.getPrice());
				orderPriceModel.setRemark(e.getRemark());
				orderPriceModel.setCreateOprtId(this.apiRequest.getOprtId());
				orderPriceModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				orderPriceModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				this.orderPriceBo.add(orderPriceModel);
				orderPriceModels.add(orderPriceModel);
			}

			// 实时下单
			try {
				String orderNo = partnerService.addBook(chainModel, roomTypeModel, orderModel, orderGuestModels, orderPriceModels);
				orderModel.setOuterOrderSn(orderNo);
				orderModel.setFlag(2);// 已下单
				this.orderBo.update(orderModel);
			} catch (Throwable pe) {
				orderModel.setInnRemark(partnerService.getErrMsg(1000));
				orderModel.setFlag(0);
				this.orderBo.update(orderModel);
				throw pe;
			}

			rsp.setOrderId(orderModel.getOrderId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
