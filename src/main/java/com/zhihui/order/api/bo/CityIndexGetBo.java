package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.CityIndex;
import com.zhihui.order.api.request.CityIndexGetRequest;
import com.zhihui.order.api.response.CityIndexGetResponse;
import com.zhihui.order.bo.CityIndexBo;
import com.zhihui.order.model.CityIndexModel;

@Service
public class CityIndexGetBo extends ApiBo<CityIndexGetRequest> {
	@Autowired
	private CityIndexBo cityIndexBo;

	@Override
	public Class<CityIndexGetRequest> getRequestType() {
		return CityIndexGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			CityIndexGetResponse rsp = (CityIndexGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setCityIndexs(new ArrayList<CityIndex>());

			List<CityIndexModel> cityIndexModels = this.cityIndexBo.getByCondt(this.apiRequest.getCityIndexId(), this.apiRequest.getNameRelated());
			for (int i = 0; i < cityIndexModels.size(); i++) {
				CityIndex e = new CityIndex();
				e.setCityIndexId(cityIndexModels.get(i).getCityIndexId());
				e.setCreateTime(new Date(cityIndexModels.get(i).getCreateTime().getTime()));
				e.setName(cityIndexModels.get(i).getName());
				e.setRemark(cityIndexModels.get(i).getRemark());
				rsp.getCityIndexs().add(e);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
