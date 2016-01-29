package com.zhihui.order.api.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.entity.OrderPrice;
import com.zhihui.order.api.request.OrderAddRequest;
import com.zhihui.order.api.response.OrderAddResponse;
import com.zhihui.order.bo.OrderBo;
import com.zhihui.order.bo.OrderGuestBo;
import com.zhihui.order.bo.OrderPriceBo;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.model.OrderPriceModel;

public class OrderAddBo extends ApiBo<OrderAddRequest> {
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

			// TODO check the parameters

			OrderModel orderModel = new OrderModel();
			orderModel.setPartnerId(this.apiRequest.getPartnerId());
			orderModel.setChainId(this.apiRequest.getChainId());
			orderModel.setMebId(this.apiRequest.getMebId());
			orderModel.setRoomTypeId(this.apiRequest.getRoomTypeId());
			orderModel.setAmount(this.apiRequest.getAmount());
			orderModel.setArrEndOfDay(new java.sql.Date(this.apiRequest.getArrEndOfDay().getTime()));
			orderModel.setDepEndOfDay(new java.sql.Date(this.apiRequest.getDepEndOfDay().getTime()));
			// orderModel.setReserveTime(null);
			// orderModel.setEarlyArrTime(null);
			// orderModel.setLastArrTime(null);
			orderModel.setChannelSellerId(this.apiRequest.getChannelSellerId());
			orderModel.setSellerId(this.apiRequest.getSellerId());
			// orderModel.setOuterOrderSn(null);
			// orderModel.setOuterChainSn(null);
			// orderModel.setOuterroomTypeSn(null);
			orderModel.setMessage(this.apiRequest.getMessage());
			orderModel.setFlag(1);
			orderModel.setCreateOprtId(this.apiRequest.getOprtId());
			orderModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
			orderModel.setLastReviseOprtId(this.apiRequest.getOprtId());
			orderModel.setInnRemark(null);
			orderModel.setRemark("下单");
			this.orderBo.add(orderModel);
			// order-guest
			for (OrderGuest e : this.apiRequest.getOrderGuests()) {
				OrderGuestModel orderGuestModel = new OrderGuestModel();
				orderGuestModel.setOrderId(orderModel.getOrderId());
				orderGuestModel.setContactName(e.getContactName());
				orderGuestModel.setContactMobile(e.getContactMobile());
				this.orderGuestBo.add(orderGuestModel);
			}
			// order-price
			for (OrderPrice e : this.apiRequest.getOrderPrices()) {
				OrderPriceModel orderPriceModel = new OrderPriceModel();
				orderPriceModel.setOrderId(orderModel.getOrderId());
				orderPriceModel.setEndOfday(new java.sql.Date(e.getEndOfDay().getTime()));
				orderPriceModel.setPrice(e.getPrice());
				this.orderPriceBo.add(orderPriceModel);
			}

			// TODO To partner party company
			// invoke partner company interface;
			// if false set the "flag" field 0;

			rsp.setOrderId(orderModel.getOrderId());
			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
