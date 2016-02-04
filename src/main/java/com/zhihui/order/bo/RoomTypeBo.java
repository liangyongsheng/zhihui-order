package com.zhihui.order.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;
import com.zhihui.order.dao.RoomTypeDao;
import com.zhihui.order.model.RoomTypeModel;

@Service
public class RoomTypeBo extends BoBase {
	@Autowired
	private RoomTypeDao roomTypeDao;

	@Override
	public DaoBase getDao() {
		return this.roomTypeDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public RoomTypeModel getById(long id) {
		return roomTypeDao.getById(id);
	}

}
