package com.xawl.shop.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.xawl.shop.domain.Admin;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.service.AdminService;
import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.keyUtil;

//测试
@Controller
public class AdminController extends BaseController {

	@Resource
	private AdminService adminService;
	private ServletContext application;
	@RequestMapping("/admin/login")
	public @ResponseBody
	Object login(@Validated Admin admin, JSON json, HttpServletRequest request) {
		Admin admin2 = adminService.getAdmin(admin.getAusername(),
				admin.getApassword());
		if (admin2 != null) {
			// 登陆成功
			request.getSession().setAttribute(ResourceUtil.CURRENT_ADMIN,
					admin2);
			json.add("admin", admin2);
			if (application == null) {
				application = RequestContextUtils.getWebApplicationContext(
						request).getServletContext();
			}
			ArrayList<String> user_id = (ArrayList<String>) application
					.getAttribute("user_id");

			if (user_id != null) {
				user_id.add(request.getSession().getId());
			} else {
				user_id = new ArrayList<String>();
				user_id.add(request.getSession().getId());
				application.setAttribute("user_id", user_id);
			}

		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
		}

		return json.toString();
	}

	@RequestMapping("/admin/isalive")
	public @ResponseBody
	Object isAlive(
			@CookieValue(value = "JSESSIONID", required = false, defaultValue = "") String sessionId,
			JSON json, HttpServletRequest request) {
		if (application == null) {
			application = RequestContextUtils.getWebApplicationContext(request)
					.getServletContext();
		}

		json.add("status", keyUtil.SERVICE_FAIL);
		if (sessionId.isEmpty()) {
			return json + "";
		}
		List<String> attribute = (ArrayList<String>) application
				.getAttribute("user_id");
		if (attribute == null) {
			return json + "";
		}
		for (String str : attribute) {
			if (str.equals(str)) {
				System.out.println(str + "---------------");
				// 登陆了
				json.add("status", keyUtil.SERVICE_SUCCESS);
				return json + "";
			} else {
				return json + "";
			}
		}
		return json + "";
	}

	
}
