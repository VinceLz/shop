package com.xawl.shop.interceptor;

import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.xawl.shop.util.JsonUtil;
import com.xawl.shop.util.MapUtil;
import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.keyUtil;
import com.xawl.shop.webCategory.OrderedProperties;

public class JsonInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.addHeader("Access-Control-Allow-Origin", "*");

		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		JSONObject jsonObjec = JsonUtil.createJson(keyUtil.SERVICE_SUCCESS);
		HandlerMethod m = (HandlerMethod) handler;
		java.lang.reflect.Method method = m.getMethod();

		JsonMenu annotation = method.getAnnotation(JsonMenu.class);
		if (annotation != null) {
			// 先检测当前用户是谁 从session中查 只给管理员返回
			Object attribute = request.getSession().getAttribute(
					ResourceUtil.CURRENT_ADMIN);

			// if (attribute != null) {
			// 说明当前用户是admin
			String menu = annotation.menu();
			String url = annotation.url();
			if (!menu.isEmpty()) {
				OrderedProperties properties = new OrderedProperties();
				url = url + menu + ".properties";
				InputStream resourceAsStream = this.getClass().getClassLoader()
						.getResourceAsStream(url);
				properties.load(resourceAsStream);
				Object[][] order = MapUtil.propertsToArray(properties);
				jsonObjec.element("order", order);
				// }
			}
		}

		request.setAttribute(ResourceUtil.CURRENT_JSON, jsonObjec);
		// 1 进行用户控制检测 待续
		// 2 对特殊操作要进行记录日志
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

}
