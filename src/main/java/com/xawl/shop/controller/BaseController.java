package com.xawl.shop.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.keyUtil;

@ControllerAdvice
public abstract class BaseController {
//	@InitBinder
//	// 此方法用于日期的转换，如果未加，当页面日期格式转换错误，将报400错误，实际是因为此方法
//	public void initBinder(WebDataBinder binder) {
//		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		dateFormat.setLenient(true);
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				dateFormat, true));
//		// binder.setValidator(new ArgValidate());
//	}

	@ExceptionHandler(org.springframework.validation.BindException.class)
	public void exceptionHandler(Exception e, HttpServletRequest request,
			HttpServletResponse spResponse) throws IOException {
		if (e.getClass().equals(
				org.springframework.validation.BindException.class)) {
			// 验证出错
			JSONObject json = (JSONObject) request.getAttribute(ResourceUtil.CURRENT_JSON);
			json.element("status", keyUtil.SERVICE_FAIL);
			spResponse.getWriter().print(json + "");
		}
	}
}
