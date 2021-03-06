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
import com.zhihui.order.api.response.BrandIndexGetResponse;

@XmlRootElement(name = "apiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class BrandIndexGetRequest extends ApiRequest<BrandIndexGetResponse> {
	private Integer brandIndexId;
	private String nameRelated;

	public Integer getBrandIndexId() {
		return brandIndexId;
	}

	public void setBrandIndexId(Integer brandIndexId) {
		this.brandIndexId = brandIndexId;
	}

	public String getNameRelated() {
		return nameRelated;
	}

	public void setNameRelated(String nameRelated) {
		this.nameRelated = nameRelated;
	}

	@Override
	public Class<BrandIndexGetResponse> getResponseType() {
		return BrandIndexGetResponse.class;
	}

	@Override
	public void asignApiParams() throws AsignException {
	}

	@Override
	public void checkApiParams() throws CheckException {
	}
}
