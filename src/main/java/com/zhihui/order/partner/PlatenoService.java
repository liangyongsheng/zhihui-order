package com.zhihui.order.partner;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zhihui.core.exception.PartnerServiceException;
import com.zhihui.order.model.ChainModel;
import com.zhihui.order.model.OrderGuestModel;
import com.zhihui.order.model.OrderModel;
import com.zhihui.order.model.OrderPriceModel;
import com.zhihui.order.model.RoomTypeModel;
import com.zhihui.order.partner.plateno.service.PlatenoBookSvc;
import com.zhihui.order.partner.plateno.service.PlatenoLoginSvc;
import com.zhihui.order.partner.plateno.service.book.CRequestBookingInfo;
import com.zhihui.order.partner.plateno.service.book.DRequestBookingGuest;

@Service
public class PlatenoService extends PartnerService {

	@Override
	public String getDesc() {
		return "铂涛集团";
	}

	@Override
	public String addBook(ChainModel chainModel, RoomTypeModel roomTypeModel, OrderModel orderModel, List<OrderGuestModel> orderGuestModels, List<OrderPriceModel> orderPriceModels) throws PartnerServiceException {
		String rs = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		PlatenoBookSvc platenoBookSvc = new PlatenoBookSvc();
		PlatenoLoginSvc platenoLoginSvc = new PlatenoLoginSvc();
		CRequestBookingInfo requestBookingInfo = new CRequestBookingInfo();

		try {
			Date beginDate = orderModel.getArrEndOfDay();
			Date endDate = orderModel.getDepEndOfDay();
			int dayLength = (int) (endDate.getTime() / (24 * 60 * 60 * 1000) - beginDate.getTime() / (24 * 60 * 60 * 1000));
			dayLength = dayLength <= 0 ? 1 : dayLength;

			GregorianCalendar beginCalender = new GregorianCalendar();
			GregorianCalendar endCalender = new GregorianCalendar();
			beginCalender.setTimeInMillis(df.parse(df.format(beginDate)).getTime() + beginCalender.getTimeZone().getRawOffset());
			endCalender.setTimeInMillis(df.parse(df.format(endDate)).getTime() + endCalender.getTimeZone().getRawOffset());
			// 客人
			List<DRequestBookingGuest> requestBookingGuests = new ArrayList<DRequestBookingGuest>();
			for (int i = 0; i < orderGuestModels.size(); i++) {
				DRequestBookingGuest e = new DRequestBookingGuest();
				e.setMobile(orderGuestModels.get(0).getContactMobile());
				e.setName(orderGuestModels.get(0).getContactName());
				requestBookingGuests.add(e);
			}
			DRequestBookingGuest[][] eachOrderGuests = new DRequestBookingGuest[orderModel.getNum()][orderGuestModels.size()];
			for (int i = 0; i < orderModel.getNum(); i++)
				eachOrderGuests[i] = requestBookingGuests.toArray((new DRequestBookingGuest[requestBookingGuests.size()]));

			// 价格
			List<BigDecimal> roomPrices = new ArrayList<BigDecimal>();
			for (int i = 0; i < orderPriceModels.size(); i++)
				roomPrices.add(new BigDecimal(orderPriceModels.get(i).getPrice()));

			requestBookingInfo.setRoomCount(orderModel.getNum());
			requestBookingInfo.setDayLength(dayLength);
			requestBookingInfo.setContactor(orderGuestModels.get(0).getContactName());
			requestBookingInfo.setHotelId(Integer.parseInt(chainModel.getOuterChainSn()));
			requestBookingInfo.setOrganId(1);// XXX organ
			requestBookingInfo.setRoomType(roomTypeModel.getOuterRoomTypeSn());
			requestBookingInfo.setStartTime(beginCalender);
			requestBookingInfo.setSponsionType(1); // XXX sponsionType
			requestBookingInfo.setMobile(orderGuestModels.get(0).getContactMobile());
			requestBookingInfo.setRemark(orderModel.getMessage());
			requestBookingInfo.setBKMebCardNo("92330902");// XXX bkMebCardNo
			// requestBookingInfo.setAuthorNumber(platenoLoginSvc.getAuthen()); // XXX authorNumber
			requestBookingInfo.setRoomRates(roomPrices.toArray((new BigDecimal[roomPrices.size()])));
			requestBookingInfo.setEachOrderGuests(eachOrderGuests);
			rs = platenoBookSvc.addBook(requestBookingInfo, roomPrices.get(0).doubleValue());

			if (rs == null)
				throw new PartnerServiceException("result is null");
		} catch (Throwable e) {
			throw new PartnerServiceException(e);
		}

		return rs;
	}
}
