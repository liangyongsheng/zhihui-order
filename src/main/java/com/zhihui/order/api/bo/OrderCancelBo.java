package com.zhihui.order.api.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.context.MyContext;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.order.api.request.OrderCancelRequest;
import com.zhihui.order.api.response.OrderCancelResponse;
import com.zhihui.order.bo.OrderBo;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.partner.PartnerService;

@Service
public class OrderCancelBo extends ApiBo<OrderCancelRequest> {
	@Autowired
	private OrderBo orderBo;

	@Override
	public Class<OrderCancelRequest> getRequestType() {
		return OrderCancelRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			OrderCancelResponse rsp = (OrderCancelResponse) this.apiResponse;
			rsp.setSuccess(false);

			// check the parameters
			// 订单
			OrderModel orderModel = this.orderBo.getById(this.apiRequest.getOrderId());
			if (orderModel == null)
				throw new BusinessException("不存在订单号为：" + this.apiRequest.getOrderId() + "的订单");
			if (orderModel.getFlag() != 2)
				throw new BusinessException("此状态不允许取消");
			// 此合作人
			PartnerService partnerService = (PartnerService) MyContext.getRootApplicationContext().getBean("order.partner." + orderModel.getPartnerId());
			if (partnerService == null)
				throw new BusinessException("不存在此合作人");

			// 0、影子订单；1、下单中；2、已下单；3、取消中；4、已取消；5、NOSHOW；6、入住；7、离店
			// 实时取消
			try {
				if (!MyStringUtils.isEmpty(orderModel.getOuterOrderSn()))
					partnerService.cancelBook(orderModel);
				orderModel.setFlag(4);
				this.orderBo.update(orderModel);
			} catch (Throwable pe) {
				orderModel.setInnRemark(partnerService.getErrMsg(1000));
				orderModel.setFlag(3);
				this.orderBo.update(orderModel);
				throw pe;
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
