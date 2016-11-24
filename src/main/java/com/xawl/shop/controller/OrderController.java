package com.xawl.shop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.Order;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.domain.User;
import com.xawl.shop.domain.VO.OrderItemVO;
import com.xawl.shop.domain.VO.OrderVO;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.BusinessService;
import com.xawl.shop.service.CartItemService;
import com.xawl.shop.service.GoodsService;
import com.xawl.shop.service.OrderService;
import com.xawl.shop.util.ArrayUtil;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class OrderController extends BaseController {
	public static String OK = "1"; // 已审核
	public static String NO = "0"; // 待审核

	public static String ORDER_NO_PLAY = "-1"; // 待支付
	public static String ORDER_YES_PLAY = "0"; // 已经支付 待处理
	public static String ORDER_DEAL = "2"; // 待确认 已处理
	public static String ORDER_SUCCESS = "3"; // 完成 完成
	public static String ORDER_FAIL = "1"; // 退款 退款
	@Resource
	private OrderService orderService;

	@Resource
	private GoodsService goodsService;

	@Resource
	private CartItemService cartItemService;

	@Resource
	private BusinessService businessService;

	// 获取所有订单
	@RequestMapping("/admin/order/list")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	@JsonMenu(menu = "Order")
	Object list(Page<Order> page, JSON json) {
		// System.out.println(page.getParams().get("bname").toString().isEmpty());
		List<Order> findPage = orderService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	// 获取子订单！！ 详情
	@RequestMapping("/admin/order/get")
	@Role(role = Role.ROLE_ADMIN)
	public @ResponseBody
	Object view(@RequestParam() String oid, JSON json) {
		List<OrderItemVO> all = orderService.getAll(oid);
		json.add("orderitem", all);
		return json.toString();
	}

	// 审核通过
	@RequestMapping("/admin/order/examineOk")
	@Role(role = Role.ROLE_ADMIN)
	public @ResponseBody
	Object examine(@RequestParam() String oid, JSON json) {
		Map map = new HashMap<>();
		map.put("oinspect", OK);
		map.put("oid", oid);
		orderService.examine(map);
		return json.toString();
	}

	// 获取自己的订单
	// 1 商家的
	// 2 用户的

	// 创建订单 购买一个
	@RequestMapping("/front/order/add")
	public @ResponseBody
	Object createOrder(JSON json, @RequestParam() String gid,
			@UserSession(session = "user") User user) {
		Goods goods = goodsService.get(gid);
		if (goods == null) {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + ""; // 传入的商品不存在
		}
		// 1 先创建order订单对象
		OrderVO order = new OrderVO();

		String uuid = UUID.randomUUID().toString();
		order.setOid(uuid);// 订单号
		String date = DateUtil.getSqlDate();
		order.setOrdertime(date);
		// status 默认是0 未支付
		order.setUid(user.getUid());
		order.setUname(user.getUname());
		order.setUphone(user.getUphone());
		order.setBid(goods.getBid());
		order.setBname(goods.getBname());
		order.setBphone(businessService.getPhoneByBid(goods.getBid())); // 商家电话
		order.setStatus(-1);
		// oinspect 是审核 0表示为审核
		// currentPrice 表示修改后的价格
		// orderItem
		OrderItem orderItem = new OrderItem();
		orderItem.setGid(goods.getGid());
		orderItem.setGname(goods.getGname());
		orderItem.setOid(uuid);
		orderItem.setGprice(goods.getGprice());
		orderItem.setGimage(ArrayUtil.Array2String(goods.getGimage()));
		order.setTotal(orderItem.getGprice());
		order.setCurrentprice(orderItem.getGprice());
		order.getItemlist().add(orderItem);
		// 封装完毕
		// 写入数据库
		List orderList = new ArrayList<OrderVO>();
		orderList.add(order);
		orderService.insert(orderList);
		order.getItemlist().clear();
		json.add("orders", orderList);
		return json + "";
	}

	// 购物车批量购买
	// 一个商家生成一个订单
	@RequestMapping("/front/order/addAll")
	public @ResponseBody
	Object createOrder2(JSON json, @RequestParam() String[] gid,
			@UserSession(session = "user") User user) {
		// 1 先遍历所有 的gid 找出相同的gid对应的bid商家 生成一单
		ArrayList<Goods> arrayList = new ArrayList<Goods>();
		List<OrderVO> orders = new ArrayList<OrderVO>();
		for (int i = 0; i < gid.length; i++) {
			Goods goods = goodsService.get(gid[i]);
			if (goods != null) {
				arrayList.add(goods);
			}
		}
		if (arrayList.size() == 0) {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
		boolean flag = false;
		for (Goods goods : arrayList) {
			flag = false;
			for (OrderVO orderVO : orders) {
				if (goods.getBid() == orderVO.getBid()) {
					// 已经有了该商家的订单
					OrderItem item = new OrderItem();
					item.setGid(goods.getGid());
					item.setGname(goods.getGname());
					item.setGprice(goods.getGprice());
					item.setGimage(ArrayUtil.Array2String(goods.getGimage()));
					item.setOid(orderVO.getOid());
					orderVO.getItemlist().add(item);
					orderVO.setTotal(orderVO.getAllTotal()); // 刷新总价
					orderVO.setCurrentprice(orderVO.getAllTotal()); // 刷新总价
					flag = true;
					break;
				}
			}
			// 新建订单
			if (!flag) {
				OrderVO order = new OrderVO();
				String uuid = UUID.randomUUID().toString();
				order.setOid(uuid);// 订单号
				String date = DateUtil.getSqlDate();
				order.setOrdertime(date);
				// status 默认是0 未支付
				order.setUid(user.getUid());
				order.setUname(user.getUname());
				order.setUphone(user.getUphone());
				order.setBid(goods.getBid());
				order.setBname(goods.getBname());
				order.setBphone(businessService.getPhoneByBid(goods.getBid()));
				order.setStatus(-1);
				// 订单项
				OrderItem orderItem = new OrderItem();
				orderItem.setGid(goods.getGid());
				orderItem.setGname(goods.getGname());
				orderItem.setOid(uuid);
				orderItem.setGprice(goods.getGprice());
				orderItem.setGimage(ArrayUtil.Array2String(goods.getGimage()));
				order.setTotal(orderItem.getGprice());
				order.setCurrentprice(orderItem.getGprice());
				order.getItemlist().add(orderItem);
				orders.add(order);
			}

		}
		orderService.insert(orders);
		// 把ordersVO对象转换为orders对象
		for (OrderVO order : orders) {
			order.getItemlist().clear();
		}
		// 删除购物车的选中的条目
		json.add("orders", orders);
		return json + "";
	}

	@RequestMapping("/front/order/statusList")
	public @ResponseBody @Role(role = Role.ROLE_USER)
	Object getOrderStatusNumber(JSON json,
			@UserSession(session = "user") User user) {
		Map<String, Map<String, Integer>> status_number = orderService
				.getOrderStatusNumber(user.getUid());
		Map<String, Integer> map = status_number.get(null);
		json.add("status_array", map);
		// 判断是否是商家
		return json + "";
	}

	@RequestMapping("/front/order/statusbusiness")
	public @ResponseBody
	Object getOrderStatusNumber2(JSON json,
			@UserSession(session = "business") Business business) {
		if (business != null) {
			// 传递商家订单信息
			Map<String, Map<String, Integer>> status_business = orderService
					.getOrderStatusNumberByBid(business.getBid());
			Map<String, Integer> map1 = status_business.get(null);
			json.add("status_array", map1);
		}
		return json + "";
	}

	// ToDo 获取商家对应的订单集合状态

	// 根据传入的状态吗，获取对应的状态的订单集合
	@RequestMapping("/front/order/statusBycode")
	public @ResponseBody
	Object getOrderStatusNumbers(JSON json,
			@UserSession(session = "user") User user,
			@RequestParam() String status) {
		Map map = new HashMap<>();
		map.put("uid", user.getUid());
		map.put("status", status);
		List<OrderVO> statusByCode = orderService.getStatusByCode(map);
		json.add("orders", statusByCode);
		return json + "";
	}

	// 支付
	@RequestMapping("/front/order/updateOrder")
	public @ResponseBody
	Object updateStatus(JSON json, @UserSession(session = "user") User user,
			@RequestParam() String status, @RequestParam() String oid) {
		// TODO 支付成功调用此函数
		// TODO 进一步检测，去数据库查询金额是否到账后，进行更改订单状态
		Map map = new HashMap<String, Object>();
		map.put("uid", user.getUid());
		map.put("status", status);
		map.put("oid", oid);
		orderService.updateCode(map);
		return json + "";
	}

	@RequestMapping("/front/order/updateOrderBusiness")
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	Object updateStatus2(JSON json,
			@UserSession(session = "business") Business business,
			@RequestParam() String status, @RequestParam() String oid) {
		// TODO 支付成功调用此函数
		// TODO 进一步检测，去数据库查询金额是否到账后，进行更改订单状态
		Map map = new HashMap<String, Object>();
		map.put("bid", business.getBid());
		map.put("status", status);
		map.put("oid", oid);
		orderService.updateCodeBusiness(map);
		return json + "";
	}

	// 支付成功后的异步通知
	@RequestMapping("/front/order/return_pay")
	public @ResponseBody
	Object pay_return(JSON json) {
			//异步通知一般是检测订单支付是否正常
			//同步通知一般是通知用户支付状态
		return json + "";
	}

}
