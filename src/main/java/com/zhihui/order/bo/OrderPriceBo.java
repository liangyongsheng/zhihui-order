package com.zhihui.order.bo;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.order.dao.OrderPriceDao;
import com.zhihui.order.model.OrderPriceModel;

public class OrderPriceBo extends BoBase {
	@Autowired
	private OrderPriceDao orderPriceDao;

	@Override
	public DaoBase getDao() {
		return this.orderPriceDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public OrderPriceModel getById(long id) {
		return this.orderPriceDao.getById(id);
	}

	public OrderPriceModel add(OrderPriceModel orderPriceModel) {
		this.orderPriceDao.add(orderPriceModel);
		return orderPriceModel;
	}
}
