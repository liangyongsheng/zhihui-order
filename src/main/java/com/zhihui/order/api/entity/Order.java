package com.zhihui.order.api.entity;

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

import com.zhihui.core.jsonmapper.JsonStr2DateDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DateSerializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeSerializer;
import com.zhihui.core.xmladapter.XmlStr2DateAdapter;
import com.zhihui.core.xmladapter.XmlStr2DatetimeAdapter;

@XmlRootElement(name = "order")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class Order {
	private Long orderId;
	private Integer partnerId;
	private String outerOrderSn;
	private String outerOrderName;
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
	private Integer flag;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date createTime;

	private Integer createOprtId;

	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date lastReviseTime;

	private Integer lastReviseOprtId;
	private String innRemark;
	private String remark;

	@XmlElementWrapper(name = "orderGuests")
	@XmlElement(name = "orderGuest")
	private List<OrderGuest> orderGuests;

	@XmlElementWrapper(name = "orderPrices")
	@XmlElement(name = "orderPrice")
	private List<OrderPrice> orderPrices;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Integer getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(Integer partnerId) {
		this.partnerId = partnerId;
	}

	public String getOuterOrderSn() {
		return outerOrderSn;
	}

	public void setOuterOrderSn(String outerOrderSn) {
		this.outerOrderSn = outerOrderSn;
	}

	public String getOuterOrderName() {
		return outerOrderName;
	}

	public void setOuterOrderName(String outerOrderName) {
		this.outerOrderName = outerOrderName;
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

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getCreateOprtId() {
		return createOprtId;
	}

	public void setCreateOprtId(Integer createOprtId) {
		this.createOprtId = createOprtId;
	}

	public Date getLastReviseTime() {
		return lastReviseTime;
	}

	public void setLastReviseTime(Date lastReviseTime) {
		this.lastReviseTime = lastReviseTime;
	}

	public Integer getLastReviseOprtId() {
		return lastReviseOprtId;
	}

	public void setLastReviseOprtId(Integer lastReviseOprtId) {
		this.lastReviseOprtId = lastReviseOprtId;
	}

	public String getInnRemark() {
		return innRemark;
	}

	public void setInnRemark(String innRemark) {
		this.innRemark = innRemark;
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
}
