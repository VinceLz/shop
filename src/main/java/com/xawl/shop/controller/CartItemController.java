package com.xawl.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.CartItem;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.User;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.service.CartItemService;
import com.xawl.shop.util.DateUtil;

@Controller
public class CartItemController extends BaseController {

	@Resource
	private CartItemService cartItemService;

	@RequestMapping("/front/cartitem/get")
	public @ResponseBody
	// 后去usersession的
	@Role(role = Role.ROLE_USER)
	Object get(JSON json, @UserSession(session = UserSession.USER) User user) {
		// 获取所有购物车物品
		List<CartItem> list = cartItemService.findById(user.getUid()); //
		// 开始解析封装这个VO对象
		json.add("cartitem", list);
		return json.toString();
	}

	// 加入购物车
	@RequestMapping("/front/cartitem/add")
	public @ResponseBody @Role(role = Role.ROLE_USER)
	Object add(JSON json, @Validated CartItem cartitem,
			@UserSession(session = "user") User user) {
		cartitem.setUid(user.getUid());
		cartitem.setDate(DateUtil.getSqlDate());
		cartItemService.insert(cartitem);
		return json + "";

	}

	@RequestMapping("/front/cartitem/delete")
	public @ResponseBody
	Object delete(JSON json,
			@UserSession(session = UserSession.USER) User user,
			@RequestParam() String[] cartitem_id) {
		Map map = new HashMap<String, Object>();
		map.put("uid", user.getUid());
		map.put("cartitem_id", cartitem_id);
		cartItemService.deleteByCid(map);
		return json + "";
	}

}
