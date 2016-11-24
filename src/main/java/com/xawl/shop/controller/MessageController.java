package com.xawl.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.Message;
import com.xawl.shop.domain.User;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.util.keyUtil;

@Controller
public class MessageController extends BaseController {

	public static Integer MESSAGE_SEND_ADMIN = 0;
	public static Integer MESSAGE_SEND_USER = 1;
	public static Integer MESSAGE_SEND_BUSINESS = 2;

	@Resource
	private MessageService messageService;

	// 获取指定uid用户的站内信
	// 普通用户的
	@RequestMapping("/front/message/list")
	public @ResponseBody
	Object getMessage(JSON json, @UserSession(session = "user") User user,
			@UserSession(session = "business") Business business) {
		List<Message> list = messageService.get(user.getUid()); // 获取到所有信息
		json.add("message", list);
		return json + "";
	}
	@RequestMapping("/front/message/get")
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object getMessage2(JSON json, @RequestParam() String mid) {
		// 拿到mid 获取消息
		Message byMid = messageService.updateAndgetByMid(mid);
		json.add("message", byMid);
		return json + "";
	}

}
