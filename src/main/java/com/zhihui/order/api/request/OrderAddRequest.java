package com.zhihui.order.api.request;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiRequest;
import com.zhihui.core.exception.AsignException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.core.exception.CheckIllicitValueException;
import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.core.xmladapter.XmlStr2DatetimeAdapter;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.entity.OrderPrice;
import com.zhihui.order.api.response.OrderAddResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class OrderAddRequest extends ApiRequest<OrderAddResponse> {
	private Integer partnerId;
	private Integer chainId;
	private Long mebId;
	private Integer roomTypeId;
	private Integer num;

	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date arrEndOfDay;

	@XmlJavaTypeAdapter(value = XmlStr2DateAdapter.class)
	@JsonSerialize(using = JsonStr2DateSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DateDeserializer.class)
	private Date depEndOfDay;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date reserveTime;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date earlyArrTime;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date lastArrTime;

	private Integer channelSellerId;
	private Integer sellerId;
	private String message;
	private String remark;
	@XmlElementWrapper(name = "orderGuests")
	@XmlElement(name = "orderGuest")
	private List<OrderGuest> orderGuests;
	@XmlElementWrapper(name = "orderPrices")
	@XmlElement(name = "orderPrice")
	private List<OrderPrice> orderPrices;

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public Integer getChainId() {
		return chainId;
	}

	public void setChainId(Integer chainId) {
		this.chainId = chainId;
	}

	public Long getMebId() {
		return mebId;
	}

	public void setMebId(Long mebId) {
		this.mebId = mebId;
	}

	public Integer getRoomTypeId() {
		return roomTypeId;
	}

	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public Date getArrEndOfDay() {
		return arrEndOfDay;
	}

	public void setArrEndOfDay(Date arrEndOfDay) {
		this.arrEndOfDay = arrEndOfDay;
	}

	public Date getDepEndOfDay() {
		return depEndOfDay;
	}

	public void setDepEndOfDay(Date depEndOfDay) {
		this.depEndOfDay = depEndOfDay;
	}

	public Date getReserveTime() {
		return reserveTime;
	}

	public void setReserveTime(Date reserveTime) {
		this.reserveTime = reserveTime;
	}

	public Date getEarlyArrTime() {
		return earlyArrTime;
	}

	public void setEarlyArrTime(Date earlyArrTime) {
		this.earlyArrTime = earlyArrTime;
	}

	public Date getLastArrTime() {
		return lastArrTime;
	}

	public void setLastArrTime(Date lastArrTime) {
		this.lastArrTime = lastArrTime;
	}

	public Integer getChannelSellerId() {
		return channelSellerId;
	}

	public void setChannelSellerId(Integer channelSellerId) {
		this.channelSellerId = channelSellerId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<OrderGuest> getOrderGuests() {
		return orderGuests;
	}

	public void setOrderGuests(List<OrderGuest> orderGuests) {
		this.orderGuests = orderGuests;
	}

	public List<OrderPrice> getOrderPrices() {
		return orderPrices;
	}

	public void setOrderPrices(List<OrderPrice> orderPrices) {
		this.orderPrices = orderPrices;
	}

	@Override
	public Class<OrderAddResponse> getResponseType() {
		return OrderAddResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
		if (this.partnerId == null || this.partnerId <= 0)
			throw new CheckIllicitValueException("field: partnerId, value is illicit");

		if (this.chainId == null || this.chainId <= 0)
			throw new CheckIllicitValueException("field: chainId, value is illicit");

		if (this.mebId == null || this.mebId <= 0)
			throw new CheckIllicitValueException("field: mebId, value is illicit");

		if (this.roomTypeId == null || this.roomTypeId <= 0)
			throw new CheckIllicitValueException("field: roomTypeId, value is illicit");

		if (this.num == null || this.num <= 0)
			throw new CheckIllicitValueException("field: num, value is illicit");

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			if (this.arrEndOfDay == null || this.arrEndOfDay.getTime() < df.parse(df.format(new Date())).getTime())
				throw new CheckIllicitValueException("field: arrEndOfDay, value is illicit");
		} catch (Throwable e) {
			throw new CheckIllicitValueException("field: arrEndOfDay, value is illicit");
		}
		if (this.depEndOfDay == null || this.arrEndOfDay.getTime() > this.depEndOfDay.getTime())
			throw new CheckIllicitValueException("field: arrEndOfDay, value is illicit");

		if (this.depEndOfDay == null)
			throw new CheckIllicitValueException("field: depEndOfDay, value is illicit");

		long day = (this.depEndOfDay.getTime() - this.arrEndOfDay.getTime()) / (1000 * 24 * 60 * 60);
		if (day > 15)
			throw new CheckIllicitValueException("the span between arrEndOfDay and depEndOfDay is larger then 15 days");

		if (this.orderGuests == null || this.orderGuests.size() <= 0)
			throw new CheckIllicitValueException("field: orderGuests, value is illicit");

		if (this.orderPrices == null || this.orderPrices.size() <= 0)
			throw new CheckIllicitValueException("field: orderPrices, value is illicit");
	}
}
