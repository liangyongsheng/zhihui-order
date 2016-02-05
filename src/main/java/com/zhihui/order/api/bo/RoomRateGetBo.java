package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.RoomRate;
import com.zhihui.order.api.request.RoomRateGetRequest;
import com.zhihui.order.api.response.RoomRateGetResponse;
import com.zhihui.order.bo.RoomRateBo;
import com.zhihui.order.model.RoomRateModel;

public class RoomRateGetBo extends ApiBo<RoomRateGetRequest> {
	@Autowired
	private RoomRateBo roomRateBo;

	@Override
	public Class<RoomRateGetRequest> getRequestType() {
		return RoomRateGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			RoomRateGetResponse rsp = (RoomRateGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setRoomRates(new ArrayList<RoomRate>());

			List<RoomRateModel> roomRateModels = this.roomRateBo.getCondt();
			for (int i = 0; i < roomRateModels.size(); i++) {
				RoomRate e = new RoomRate();
				e.setRoomRateId(roomRateModels.get(i).getRoomRateId());
				e.setPartnerId(roomRateModels.get(i).getPartnerId());
				e.setChainId(roomRateModels.get(i).getChainId());
				e.setRoomTypeId(roomRateModels.get(i).getRoomTypeId());
				e.setOuterRoomrateSn(roomRateModels.get(i).getOuterRoomrateSn());
				e.setOuterRoomrateName(roomRateModels.get(i).getOuterRoomrateName());
				e.setEndOfDay(new Date(roomRateModels.get(i).getEndOfDay().getTime()));
				e.setRetailPrice(roomRateModels.get(i).getRetailPrice());
				e.setPrice(roomRateModels.get(i).getPrice());
				e.setQuota(roomRateModels.get(i).getQuota());
				e.setCreateTime(new Date(roomRateModels.get(i).getCreateTime().getTime()));
				e.setCreateOprtId(roomRateModels.get(i).getCreateOprtId());
				e.setLastReviseTime(new Date(roomRateModels.get(i).getLastReviseTime().getTime()));
				e.setLastReviseOprtId(roomRateModels.get(i).getLastReviseOprtId());
				e.setRemark(roomRateModels.get(i).getRemark());
				rsp.getRoomRates().add(e);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
