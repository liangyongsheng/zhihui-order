package com.zhihui.order.bo;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.order.dao.OrderPriceDao;
import com.zhihui.order.model.OrderPriceModel;

@Service
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
		if (orderPriceModel.getLastReviseTime() == null)
			orderPriceModel.setLastReviseTime(new Timestamp((new Date()).getTime()));
		if (orderPriceModel.getLastReviseOprtId() == null)
			orderPriceModel.setLastReviseOprtId(orderPriceModel.getCreateOprtId());
		this.orderPriceDao.add(orderPriceModel);
		return orderPriceModel;
	}
}
