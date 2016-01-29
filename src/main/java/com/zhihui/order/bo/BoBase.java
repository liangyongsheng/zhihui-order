package com.zhihui.order.bo;

import org.springframework.stereotype.Service;

import com.zhihui.core.hibernate.DaoBase;

@Service
public abstract class BoBase {
	public abstract DaoBase getDao();

	public abstract <T> T getById(long id);
}
