package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.request.OrderGuestGetRequest;
import com.zhihui.order.api.response.OrderGuestGetResponse;
import com.zhihui.order.bo.OrderGuestBo;
import com.zhihui.order.model.OrderGuestModel;

@Service
public class OrderGuestGetBo extends ApiBo<OrderGuestGetRequest> {
	@Autowired
	private OrderGuestBo orderGuestBo;

	@Override
	public Class<OrderGuestGetRequest> getRequestType() {
		return OrderGuestGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			OrderGuestGetResponse rsp = (OrderGuestGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setOrderGuests(new ArrayList<OrderGuest>());

			List<OrderGuestModel> orderGuestModels = this.orderGuestBo.getByOrderId(this.apiRequest.getOrderId());
			for (OrderGuestModel e : orderGuestModels) {
				OrderGuest orderGuest = new OrderGuest();
				orderGuest.setOrderGuestId(e.getOrderGuestId());
				orderGuest.setContactName(e.getContactName());
				orderGuest.setContactGender(e.getContactGender());
				orderGuest.setContactMobile(e.getContactMobile());
				orderGuest.setRemark(e.getRemark());
				rsp.getOrderGuests().add(orderGuest);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
