package com.zhihui.order.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.order.dao.OrderDao;
import com.zhihui.order.model.OrderModel;

public class OrderBo extends BoBase {
	@Autowired
	private OrderDao orderDao;

	@Override
	public DaoBase getDao() {
		return this.orderDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderModel getById(long id) {
		return this.orderDao.getById(id);
	}

	public OrderModel add(OrderModel orderModel) {
		if (orderModel.getLastReviseTime() == null)
			orderModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (orderModel.getLastReviseOprtId() == null)
			orderModel.setLastReviseOprtId(orderModel.getCreateOprtId());
		this.orderDao.add(orderModel);
		return orderModel;
	}
}
