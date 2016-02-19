package com.zhihui.order.api;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Controller;

import com.zhihui.core.api.ApiBo;
import com.zhihui.core.api.ApiNoMethodException;
import com.zhihui.core.api.ApiResponse;
import com.zhihui.core.context.MyContext;
import com.zhihui.core.exception.CoreException;

@Controller
@Path("/order")
public class Rest {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public String doGet() {
		String content = null;
		try {
			String fileName = MyContext.getServletContext().getRealPath("/") + "/WEB-INF/index.htm";
			FileInputStream fis = new FileInputStream(fileName);
			BufferedReader rd = new BufferedReader(new InputStreamReader(fis, Charset.forName("utf-8")));
			String line = null;
			StringBuffer sb = new StringBuffer();
			while ((line = rd.readLine()) != null)
				sb.append(line);

			rd.close();
			fis.close();
			content = new String(sb);
		} catch (Throwable e) {
		}

		return content;
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public ApiResponse doPost(@Context HttpHeaders headers, String messageBody) {
		try {
			// if content is null, it will throw "HTTP entity may not be null"
			ApiBo<?> bo = null;
			String sign = null;
			String contentType = null;
			String method = null;

			sign = headers.getRequestHeader("Sign") == null ? "" : headers.getRequestHeader("Sign").get(0);
			contentType = headers.getRequestHeader("Content-Type") == null ? (headers.getRequestHeader("Content-type") == null ? "" : headers.getRequestHeader("Content-type").get(0)) : headers.getRequestHeader("Content-Type").get(0);
			// extract the parameter:method, do you have any other good ideas?
			if (contentType.equalsIgnoreCase(MediaType.APPLICATION_XML)) {
				Pattern pattern = Pattern.compile("<\\s*method\\s*>([^\\<\\>]+)<\\s*/method\\s*>");
				Matcher matcher = pattern.matcher(messageBody);
				while (matcher.find()) {
					method = matcher.group(1);
					break;
				}
			} else if (contentType.equalsIgnoreCase(MediaType.APPLICATION_JSON)) {
				Pattern pattern = Pattern.compile("['\"]{1}method['\"]{1}\\s*\\:\\s*['\"]{1}([^'\"]+)['\"]{1}");
				Matcher matcher = pattern.matcher(messageBody);
				while (matcher.find()) {
					method = matcher.group(1);
					break;
				}
			} else
				throw new ApiNoMethodException("no such thethod.");

			// transform the exception
			try {
				bo = (ApiBo<?>) MyContext.getRootApplicationContext().getBean(method);
			} catch (Throwable e) {
				throw new ApiNoMethodException(e);
			}

			bo.doInit(sign, contentType, messageBody);
			bo.doAsign();
			bo.doCheck();
			bo.doBusiness();
			return bo.getApiResponse();

		} catch (Throwable e) {
			Throwable rootExcep = e;
			while (rootExcep.getCause() != null)
				rootExcep = e.getCause();

			ApiResponse arp = new ApiResponse();
			arp.setCode("default");
			arp.setMsg(rootExcep.getMessage());
			if (rootExcep instanceof CoreException) {
				CoreException ae = (CoreException) rootExcep;
				arp.setCode(ae.getCode());
				arp.setSubCode(ae.getSubCode());
				arp.setSubMsg(rootExcep.getMessage());
			}
			return arp;
		}
	}
}