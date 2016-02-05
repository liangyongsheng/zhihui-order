package com.zhihui.order.test;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;

import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import com.zhihui.core.util.MyAlgorithmUtils;
import com.zhihui.order.api.entity.OrderGuest;
import com.zhihui.order.api.entity.OrderPrice;
import com.zhihui.order.api.request.OrderAddRequest;

public class TestOrderAddRequest {

	@Test
	public void doTest() {
		String ct = MediaType.APPLICATION_JSON;
		String at = MediaType.APPLICATION_JSON;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String postData = "";

		try {
			OrderAddRequest t = new OrderAddRequest();
			List<OrderGuest> guests = new ArrayList<OrderGuest>();
			List<OrderPrice> prices = new ArrayList<OrderPrice>();
			t.setMethod("order.add");
			t.setTimestamp(new Date());
			t.setOprtId(1);
			t.setPartnerId(1);
			t.setChainId(4);
			t.setRoomTypeId(3);
			t.setMebId(1L);
			t.setNum(1);
			t.setArrEndOfDay(new Date(df.parse(df.format(new Date())).getTime() + 1 * 24 * 60 * 60 * 1000));
			t.setDepEndOfDay(new Date(df.parse(df.format(new Date())).getTime() + 2 * 24 * 60 * 60 * 1000));
			// t.setReserveTime(null);
			// t.setEarlyArrTime(null);
			// t.setLastArrTime(null);
			t.setChannelSellerId(1);
			t.setSellerId(1);
			t.setMessage("tttt");
			t.setRemark("remark");
			// 用户
			OrderGuest orderGuest = new OrderGuest();
			orderGuest.setContactMobile("1238943894394");
			orderGuest.setContactName("LLL没");
			guests.add(orderGuest);
			t.setOrderGuests(guests);
			// 价格
			OrderPrice orderPrice = new OrderPrice();
			orderPrice.setEndOfDay(new Date(df.parse(df.format(new Date())).getTime() + 1 * 24 * 60 * 60 * 1000));
			orderPrice.setPrice(127D);
			prices.add(orderPrice);
			t.setOrderPrices(prices);

			// for XML
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			JAXBContext jc = JAXBContext.newInstance(t.getClass());
			jc.createMarshaller().marshal(t, baos);
			baos.flush();
			if (ct.equals(MediaType.APPLICATION_XML))
				postData = baos.toString();

			// for JSON
			ObjectMapper om = new ObjectMapper();
			om.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
			if (ct.equals(MediaType.APPLICATION_JSON))
				postData = om.writeValueAsString(t);

			// ...
			String sign = MyAlgorithmUtils.MD5(postData);
			String requstUrl = "http://localhost:8080/zhihui-order/rest/order";
			URL url = new URL(requstUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-type", ct);
			connection.setRequestProperty("Accept", at);
			connection.setRequestProperty("Sign", sign);
			connection.setConnectTimeout(30 * 1000);
			connection.setReadTimeout(60 * 1000);
			connection.setUseCaches(false);
			connection.setDoInput(true);

			// send request
			if (postData != null && !postData.equals("")) {
				connection.setDoOutput(true);
				OutputStreamWriter os = new OutputStreamWriter(connection.getOutputStream());
				os.write(postData);
				os.flush();
				os.close();
			}

			// get response
			InputStream inputStream = connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));
			String line = null;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null)
				response.append(line);

			inputStream.close();
			connection.disconnect();
			System.out.println(response.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
