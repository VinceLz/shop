package com.xawl.shop.interceptor;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xawl.shop.util.JsonUtil;
import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.keyUtil;

public class RoleInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		// 进行检测权限
		HandlerMethod methodHandler = (HandlerMethod) handler;
		java.lang.reflect.Method method = methodHandler.getMethod();
		Role role = method.getAnnotation(Role.class);
		if (role != null) {
			int roleCode = role.role(); // 权限码

			if ((roleCode & Role.ROLE_USER) != 0) {
				if (request.getSession()
						.getAttribute(ResourceUtil.CURRENT_USER) != null) {
					return true;
				} else {
					send(response, keyUtil.SERVICE_NO_LOGIN);
					request.getSession().invalidate();
					return false;
				}

			}

			if ((roleCode & Role.ROLE_ADMIN) != 0) {
				if (request.getSession().getAttribute(
						ResourceUtil.CURRENT_ADMIN) != null) {
					return true;
				} else {
					send(response, keyUtil.SERVICE_FAIL);
					return false;
				}
			}
			if ((roleCode & Role.ROLE_BUSINESS) != 0) {

				if (request.getSession().getAttribute(
						ResourceUtil.CURRENT_BUSINESS) != null) {
					return true;
				} else {
					send(response, keyUtil.SERVICE_FAIL);
					request.getSession().invalidate();
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

	public void send(HttpServletResponse response, int status) {
		response.setStatus(HttpStatus.OK.value()); // 设置状态码
		response.setContentType(MediaType.APPLICATION_JSON_VALUE); // 设置ContentType
		response.setCharacterEncoding("UTF-8"); // 避免乱码
		response.setContentType("application/json;charset=UTF-8");
		JSONObject jsonObjec = JsonUtil.createJson(status);
		jsonObjec.element("msg", "出错");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		try {
			response.getWriter().print(jsonObjec.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
