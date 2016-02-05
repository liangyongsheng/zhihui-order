package com.zhihui.order.api.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.exception.BusinessException;
import com.zhihui.order.api.entity.RoomTypeIndex;
import com.zhihui.order.api.request.RoomTypeIndexGetRequest;
import com.zhihui.order.api.response.RoomTypeIndexGetResponse;
import com.zhihui.order.bo.RoomTypeIndexBo;
import com.zhihui.order.model.RoomTypeIndexModel;

@Service
public class RoomTypeIndexGetBo extends ApiBo<RoomTypeIndexGetRequest> {
	@Autowired
	private RoomTypeIndexBo roomTypeIndexBo;

	@Override
	public Class<RoomTypeIndexGetRequest> getRequestType() {
		return RoomTypeIndexGetRequest.class;
	}

	@Override
	public void doBusiness() throws BusinessException {
		try {
			RoomTypeIndexGetResponse rsp = (RoomTypeIndexGetResponse) this.apiResponse;
			rsp.setSuccess(false);
			rsp.setRoomTypeIndexs(new ArrayList<RoomTypeIndex>());

			List<RoomTypeIndexModel> roomTypeIndexModels = this.roomTypeIndexBo.getByCondt(this.apiRequest.getRoomTypeIndexId(), this.apiRequest.getNameRelated());
			for (int i = 0; i < roomTypeIndexModels.size(); i++) {
				RoomTypeIndex e = new RoomTypeIndex();
				e.setRoomTypeIndex(roomTypeIndexModels.get(i).getRoomTypeIndexId());
				e.setCreateTime(new Date(roomTypeIndexModels.get(i).getCreateTime().getTime()));
				e.setName(roomTypeIndexModels.get(i).getName());
				e.setRemark(roomTypeIndexModels.get(i).getRemark());
				rsp.getRoomTypeIndexs().add(e);
			}

			rsp.setSuccess(true);
		} catch (Throwable e) {
			throw new BusinessException(e);
		}
	}
}
