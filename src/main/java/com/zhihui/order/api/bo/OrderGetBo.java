package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.Order;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.entity.OrderPrice;
import com.zhihui.order.api.request.OrderGetRequest;
import com.zhihui.order.api.response.OrderGetResponse;
import com.zhihui.order.bo.OrderBo;
import com.zhihui.order.bo.OrderGuestBo;
import com.zhihui.order.bo.OrderPriceBo;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.model.OrderPriceModel;

@Service
public class OrderGetBo extends ApiBo<OrderGetRequest> {
	@Autowired
	private OrderBo orderBo;
	@Autowired
	private OrderGuestBo orderGuestBo;
	@Autowired
	private OrderPriceBo orderPriceBo;

	@Override
	public Class<OrderGetRequest> getRequestType() {
		return OrderGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			OrderGetResponse rsp = (OrderGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setOrders(new ArrayList<Order>());

			List<OrderModel> orderModels = new ArrayList<OrderModel>();
			if (this.apiRequest.getOrderId() != null) {
				OrderModel orderModel = this.orderBo.getById(this.apiRequest.getOrderId());
				if (orderModel == null)
					throw new BusinessException("不存在订单号为：" + this.apiRequest.getOrderId() + "的订单");
				orderModels.add(orderModel);
			} else
				orderModels = this.orderBo.getByMebId(this.apiRequest.getMebId());

			for (OrderModel e : orderModels) {
				Order order = new Order();
				order.setArrEndOfDay(new Date(e.getArrEndOfDay().getTime()));
				order.setChainId(e.getChainId());
				order.setChannelSellerId(e.getChannelSellerId());
				order.setCreateOprtId(e.getCreateOprtId());
				order.setCreateTime(e.getCreateTime() == null ? null : new Date(e.getCreateTime().getTime()));
				order.setDepEndOfDay(e.getDepEndOfDay() == null ? null : new Date(e.getDepEndOfDay().getTime()));
				order.setEarlyArrTime(e.getEarlyArrTime() == null ? null : new Date(e.getEarlyArrTime().getTime()));
				order.setFlag(e.getFlag());
				order.setInnRemark(e.getInnRemark());
				order.setLastArrTime(e.getLastArrTime() == null ? null : new Date(e.getLastArrTime().getTime()));
				order.setLastReviseOprtId(e.getLastReviseOprtId());
				order.setLastReviseTime(e.getLastReviseTime() == null ? null : new Date(e.getLastReviseTime().getTime()));
				order.setMebId(e.getMebId());
				order.setMessage(e.getMessage());
				order.setOrderGuests(null); // 多个时清0
				order.setOrderId(e.getOrderId());
				order.setOrderPrices(null); // 多个时清0
				order.setOuterOrderName(e.getOuterOrderName());
				order.setOuterOrderSn(e.getOuterOrderSn());
				order.setPartnerId(order.getPartnerId());
				order.setRemark(e.getMessage());
				order.setReserveTime(e.getReserveTime() == null ? null : new Date(e.getReserveTime().getTime()));
				order.setRoomTypeId(e.getRoomTypeId());
				order.setSellerId(e.getSellerId());
				if (this.apiRequest.getOrderId() != null) {
					order.setOrderGuests(new ArrayList<OrderGuest>());
					order.setOrderPrices(new ArrayList<OrderPrice>());

					List<OrderGuestModel> orderGuestModels = this.orderGuestBo.getByOrderId(this.apiRequest.getOrderId());
					for (OrderGuestModel g : orderGuestModels) {
						OrderGuest orderGuest = new OrderGuest();
						orderGuest.setContactName(g.getContactName());
						orderGuest.setContactMobile(g.getContactMobile());
						order.getOrderGuests().add(orderGuest);
					}

					List<OrderPriceModel> orderPriceModels = this.orderPriceBo.getByOrderId(this.apiRequest.getOrderId());
					for (OrderPriceModel p : orderPriceModels) {
						OrderPrice orderPrice = new OrderPrice();
						orderPrice.setEndOfDay(p.getEndOfday() == null ? null : new Date(p.getEndOfday().getTime()));
						orderPrice.setPrice(p.getPrice());
						order.getOrderPrices().add(orderPrice);
					}
				}
				rsp.getOrders().add(order);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
