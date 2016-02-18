package com.zhihui.order.api.bo;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.request.OrderGuestUpdateRequest;
import com.zhihui.order.api.response.OrderGuestUpdateResponse;
import com.zhihui.order.bo.OrderBo;
import com.zhihui.order.bo.OrderGuestBo;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;

@Service
public class OrderGuestUpdateBo extends ApiBo<OrderGuestUpdateRequest> {
	@Autowired
	private OrderBo orderBo;
	@Autowired
	private OrderGuestBo orderGuestBo;

	@Override
	public Class<OrderGuestUpdateRequest> getRequestType() {
		return OrderGuestUpdateRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			OrderGuestUpdateResponse rsp = (OrderGuestUpdateResponse) this.apiResponse;
			rsp.setSuccess(false);

			// check the parameters
			OrderModel orderModel = this.orderBo.getById(this.apiRequest.getOrderId());
			if (orderModel == null)
				throw new BusinessException("不存在些订单");

			List<OrderGuestModel> addOrderGuestModels = new ArrayList<OrderGuestModel>();
			List<OrderGuestModel> updateOrderGuestModels = new ArrayList<OrderGuestModel>();
			for (OrderGuest e : this.apiRequest.getOrderGuests()) {
				boolean isAdd = false;
				OrderGuestModel orderGuestModel = null;
				if (e.getOrderGuestId() == null) {
					isAdd = true;
					orderGuestModel = new OrderGuestModel();
				} else {
					orderGuestModel = this.orderGuestBo.getById(e.getOrderGuestId());
					if (orderGuestModel == null)
						throw new BusinessException("不存在此入住客人");
					if (orderGuestModel.getOrderId() == this.apiRequest.getOrderId().longValue())
						throw new BusinessException("此入住客人不属于此订单");

				}

				if (isAdd) {
					orderGuestModel.setOrderId(this.apiRequest.getOrderId());
					orderGuestModel.setCreateOprtId(this.apiRequest.getOprtId());
				}
				orderGuestModel.setContactName(e.getContactName());
				orderGuestModel.setContactGender(e.getContactGender());
				orderGuestModel.setContactMobile(e.getContactMobile());
				orderGuestModel.setLastReviseOprtId(this.apiRequest.getOprtId());
				orderGuestModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
				orderGuestModel.setRemark(e.getRemark());
				if (isAdd)
					addOrderGuestModels.add(orderGuestModel);
				else
					updateOrderGuestModels.add(orderGuestModel);
				for (OrderGuestModel g : addOrderGuestModels)
					this.orderGuestBo.add(g);
				for (OrderGuestModel g : updateOrderGuestModels)
					this.orderGuestBo.update(g);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
