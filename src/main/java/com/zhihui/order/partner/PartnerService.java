package com.zhihui.order.partner;

import java.util.List;

import org.springframework.stereotype.Service;

import com.zhihui.core.exception.PartnerServiceException;
import com.zhihui.order.model.ChainModel;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.model.OrderPriceModel;
import com.zhihui.order.model.RoomTypeModel;

@Service
public abstract class PartnerService {
	public abstract String getDesc();

	public abstract String addBook(ChainModel chainModel, RoomTypeModel roomTypeModel, OrderModel orderModel, List<OrderGuestModel> orderGuestModels, List<OrderPriceModel> orderPriceModels) throws PartnerServiceException;
}
