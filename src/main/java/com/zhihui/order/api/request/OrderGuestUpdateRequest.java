package com.zhihui.order.api.request;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiRequest;
import com.zhihui.core.exception.AsignException;
import com.zhihui.core.exception.CheckEmptyException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.core.util.MyStringUtils;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.response.OrderGuestUpdateResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class OrderGuestUpdateRequest extends ApiRequest<OrderGuestUpdateResponse> {
	private Long orderId;

	@XmlElementWrapper(name = "orderGuests")
	@XmlElement(name = "orderGuest")
	private List<OrderGuest> orderGuests;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public List<OrderGuest> getOrderGuests() {
		return orderGuests;
	}

	public void setOrderGuests(List<OrderGuest> orderGuests) {
		this.orderGuests = orderGuests;
	}

	@Override
	public Class<OrderGuestUpdateResponse> getResponseType() {
		return OrderGuestUpdateResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.orderId == null || this.orderId <= 0)
			throw new CheckIllicitValueException("field: orderId, value is illicit.");

		if (this.orderGuests == null || this.orderGuests.size() <= 0)
			throw new CheckIllicitValueException("field: orderGuests, value is illicit.");
		for (OrderGuest e : this.orderGuests) {
			if (e.getOrderGuestId() != null && e.getOrderGuestId() <= 0)
				throw new CheckIllicitValueException("field: orderGuestId, value is illicit.");
			if (MyStringUtils.isEmpty(e.getContactName()))
				throw new CheckEmptyException("field: contactName, value is empty.");
			if (e.getContactGender() == null || e.getContactGender() <= 0 || e.getContactGender() >= 4)
				throw new CheckIllicitValueException("field: contactGender, value is illicit.");
			if (MyStringUtils.isEmpty(e.getContactMobile()))
				throw new CheckEmptyException("field: contactMobile, value is empty.");
		}
	}
}
