package com.zhihui.order.api.response;

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

import com.zhihui.core.api.ApiResponse;
import com.zhihui.order.api.entity.Chain;

@XmlRootElement(name = "apiResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonAutoDetect(creatorVisibility = Visibility.NONE, fieldVisibility = Visibility.ANY, getterVisibility = Visibility.NONE, isGetterVisibility = Visibility.NONE, setterVisibility = Visibility.NONE)
public class ChainGetResponse extends ApiResponse {
	@XmlElementWrapper(name = "chains")
	@XmlElement(name = "chain")
	private List<Chain> chains;

	public List<Chain> getChains() {
		return chains;
	}

	public void setChains(List<Chain> chains) {
		this.chains = chains;
	}
}
