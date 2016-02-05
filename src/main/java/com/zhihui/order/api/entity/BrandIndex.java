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

import com.zhihui.core.jsonmapper.JsonStr2DatetimeDeserializer;
import com.zhihui.core.jsonmapper.JsonStr2DatetimeSerializer;
import com.zhihui.core.xmladapter.XmlStr2DatetimeAdapter;

@XmlRootElement(name = "brandIndex")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class BrandIndex {
	private Integer brandIndex;
	private String name;
	@XmlJavaTypeAdapter(value = XmlStr2DatetimeAdapter.class)
	@JsonSerialize(using = JsonStr2DatetimeSerializer.class, include = JsonSerialize.Inclusion.NON_NULL)
	@JsonDeserialize(using = JsonStr2DatetimeDeserializer.class)
	private Date createTime;
	private String remark;

	public Integer getBrandIndex() {
		return brandIndex;
	}

	public void setBrandIndex(Integer brandIndex) {
		this.brandIndex = brandIndex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
