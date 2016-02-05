package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.Chain;
import com.zhihui.order.api.request.ChainGetRequest;
import com.zhihui.order.api.response.ChainGetResponse;
import com.zhihui.order.bo.ChainBo;
import com.zhihui.order.model.ChainModel;

@Service
public class ChainGetBo extends ApiBo<ChainGetRequest> {
	@Autowired
	private ChainBo chainBo;

	@Override
	public Class<ChainGetRequest> getRequestType() {
		return ChainGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			ChainGetResponse rsp = (ChainGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setChains(new ArrayList<Chain>());

			List<ChainModel> chainModels = this.chainBo.getByCondt(this.apiRequest.getBrandId(), this.apiRequest.getCityId(), this.apiRequest.getChainId(), this.apiRequest.getNameRelated());
			for (int i = 0; i < chainModels.size(); i++) {
				Chain e = new Chain();
				e.setChainId(chainModels.get(i).getCityId());
				e.setPartnerId(chainModels.get(i).getPartnerId());
				e.setOuterChainSn(chainModels.get(i).getOuterChainSn());
				e.setOuterChainName(chainModels.get(i).getOuterChainName());
				e.setCityId(chainModels.get(i).getCityId());
				e.setBrandId(chainModels.get(i).getBrandId());
				e.setAddress(chainModels.get(i).getAddress());
				e.setTelephone(chainModels.get(i).getTelephone());
				e.setFaxphone(chainModels.get(i).getFaxphone());
				e.setBaiduX(chainModels.get(i).getBaiduX());
				e.setBaiduY(chainModels.get(i).getBaiduY());
				e.setDescription(chainModels.get(i).getDescription());
				e.setCreateTime(new Date(chainModels.get(i).getCreateTime().getTime()));
				e.setCreateOprtId(chainModels.get(i).getCreateOprtId());
				e.setLastReviseTime(new Date(chainModels.get(i).getLastReviseTime().getTime()));
				e.setLastReviseOprtId(chainModels.get(i).getLastReviseOprtId());
				e.setRemark(chainModels.get(i).getRemark());
				rsp.getChains().add(e);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}

}
