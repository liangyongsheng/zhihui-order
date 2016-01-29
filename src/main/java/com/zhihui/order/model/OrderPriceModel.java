package com.zhihui.order.model;

import java.sql.Timestamp;
import java.sql.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "order_price")
@Access(AccessType.FIELD)
public class OrderPriceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long orderId;
	private Date endOfday;
	private Double price;
	private Timestamp createTime;
	private String remark;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public Date getEndOfday() {
		return endOfday;
	}

	public void setEndOfday(Date endOfday) {
		this.endOfday = endOfday;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
