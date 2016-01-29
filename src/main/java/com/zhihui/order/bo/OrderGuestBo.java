package com.zhihui.order.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.order.dao.OrderGuestDao;
import com.zhihui.order.model.OrderGuestModel;

public class OrderGuestBo extends BoBase {
	@Autowired
	private OrderGuestDao orderGuestDao;

	@Override
	public DaoBase getDao() {
		return this.orderGuestDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderGuestModel getById(long id) {
		return this.orderGuestDao.getById(id);
	}

	public OrderGuestModel add(OrderGuestModel orderGuestModel) {
		this.orderGuestDao.add(orderGuestModel);
		return orderGuestModel;
	}
}
