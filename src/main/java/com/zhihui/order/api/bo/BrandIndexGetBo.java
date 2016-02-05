package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.BrandIndex;
import com.zhihui.order.api.request.BrandIndexGetRequest;
import com.zhihui.order.api.response.BrandIndexGetResponse;
import com.zhihui.order.bo.BrandIndexBo;
import com.zhihui.order.model.BrandIndexModel;

@Service
public class BrandIndexGetBo extends ApiBo<BrandIndexGetRequest> {
	@Autowired
	private BrandIndexBo brandIndexBo;

	@Override
	public Class<BrandIndexGetRequest> getRequestType() {
		return BrandIndexGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			BrandIndexGetResponse rsp = (BrandIndexGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setBrandIndexs(new ArrayList<BrandIndex>());

			List<BrandIndexModel> brandIndexModels = this.brandIndexBo.getByCondt(this.apiRequest.getBrandIndexId(), this.apiRequest.getNameRelated());
			for (int i = 0; i < brandIndexModels.size(); i++) {
				BrandIndex e = new BrandIndex();
				e.setBrandIndex(brandIndexModels.get(i).getBrandIndexId());
				e.setCreateTime(new Date(brandIndexModels.get(i).getCreateTime().getTime()));
				e.setName(brandIndexModels.get(i).getName());
				e.setRemark(brandIndexModels.get(i).getRemark());
				rsp.getBrandIndexs().add(e);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}

}
