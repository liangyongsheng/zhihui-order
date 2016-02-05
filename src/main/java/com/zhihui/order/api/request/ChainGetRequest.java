package com.zhihui.order.api.request;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonAutoDetect.Visibility;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import com.zhihui.core.api.ApiRequest;
import com.zhihui.core.exception.AsignException;
import com.zhihui.core.exception.CheckException;
import com.zhihui.order.api.response.ChainGetResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ChainGetRequest extends ApiRequest<ChainGetResponse> {
	private Integer brandId;
	private Integer cityId;
	private Integer chainId;
	private String nameRelated;

	public Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getChainId() {
		return chainId;
	}

	public void setChainId(Integer chainId) {
		this.chainId = chainId;
	}

	public String getNameRelated() {
		return nameRelated;
	}

	public void setNameRelated(String nameRelated) {
		this.nameRelated = nameRelated;
	}

	@Override
	public Class<ChainGetResponse> getResponseType() {
		return ChainGetResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
	}
}
