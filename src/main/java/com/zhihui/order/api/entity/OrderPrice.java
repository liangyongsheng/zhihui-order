package com.zhihui.order.api.entity;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;

@XmlRootElement(name = "orderPrice")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class OrderPrice {
	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date endOfDay;
	private Double price;

	public Date getEndOfDay() {
		return endOfDay;
	}

	public void setEndOfDay(Date endOfDay) {
		this.endOfDay = endOfDay;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
