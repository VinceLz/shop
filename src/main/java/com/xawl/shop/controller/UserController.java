package com.xawl.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.SMS;
import com.xawl.shop.domain.User;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.BusinessService;
import com.xawl.shop.service.OrderService;
import com.xawl.shop.service.SMSSerice;
import com.xawl.shop.service.UserService;
import com.xawl.shop.util.CodeUtil;
import com.xawl.shop.util.CyptoUtils;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.SMSUtil;
import com.xawl.shop.util.TokenUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class UserController extends BaseController {
	public static String OK = "1";
	public static String STOP = "0";

	@Resource
	private SMSSerice smsSerice;
	@Resource
	private UserService userService;
	@Resource
	private BusinessService businessService;
	@Resource
	private OrderService orderService;
	private PropertiesUtil proper = new PropertiesUtil(this.getClass()
			.getClassLoader().getResourceAsStream("config.properties"));// 读取配置文件

	@RequestMapping("/admin/user/list")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	@JsonMenu(menu = "User")
	Object list(Page<User> page, JSON json) {
		List<User> findPage = userService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	@RequestMapping("/admin/user/get")
	public @ResponseBody
	Object view(@RequestParam() String uid, JSON json) {
		User user = userService.get(uid);

		json.add("user", user);

		return json.toString();
	}

	// 登陆禁止
	@RequestMapping("/admin/user/updateStatus")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object freeze(@RequestParam() String uid, @RequestParam() String status,
			JSON json) {

		Map map = new HashMap<String, Object>();
		map.put("uid", uid);
		if (STOP.equals(status)) {
			map.put("status", OK);
			userService.updateStatus(map);
		} else if (OK.equals(status)) {
			map.put("status", STOP);
			// 登陆时提示禁用
			userService.updateStatus(map);
		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
		}

		return json.toString();
	}

	// 登陆之前，加密
	@RequestMapping(value = "/front/user/firstlogin")
	public @ResponseBody
	Object firstlogin(JSON json, HttpServletRequest request) throws Exception {
		String uuid = UUID.randomUUID().toString();
		json.add("uuid", uuid); // 公钥发送给前端
		request.getSession().setAttribute("uuid", uuid);
		return json + "";
	}

	// 登陆
	// 1 登陆完检测是否是商家，如果是返回商家信息，已经最新动态
	// 2 不是商家，直接返回用户信息，或者加最新动态
	// 登陆后检查是他不是商家 把user business 全部放到session中
	@RequestMapping(value = "/front/user/login")
	public @ResponseBody
	Object login(@RequestParam() String ulogin,
			@RequestParam() String upassword, JSON json,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String prikey = (String) request.getSession().getAttribute("uuid");
		System.out.println("prikey" + prikey);
		// 拿到私钥
		if (prikey == null) {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
		Map map = new HashMap<String, Object>();
		map.put("ulogin", ulogin);
		map.put("upassword", CyptoUtils.decode(prikey, upassword));
		User user = userService.getUser(map);
		if (user != null) {
			// 被封杀
			if (user.getStatus() == 0) { // || user.getIsonline() == 1 单点登陆
				json.add("user_status", keyUtil.LOGIN_USER_FAIL);
				return json + "";
			}
			// 登陆成功
			// 1 检测是否是商家
			String code = UUID.randomUUID().toString();
			Business business = businessService.getBusinessByKey(user.getBid());

			if (business != null && business.getStatus() > 0) { // 正常
				// 不是商家
				// 把用户放到session中，前台发送数据
				// 更新最后一次登陆时间
				// 是商家，需要把商家信息一并返回并且把bussiness 放到session中
				request.getSession().setAttribute(
						ResourceUtil.CURRENT_BUSINESS, business);
				json.add("business", business);

			}
			user.setUlastlogin(DateUtil.getSqlDate());
			user.setToken(TokenUtil.generateToken(user.getUlogin()));
			user.setIsonline(1);
			// 更新登陆状态 和 最后登陆时间
			userService.update(user);
			request.getSession().setAttribute(ResourceUtil.CURRENT_USER, user);
			json.add("user", user);
			json.add("user_status", keyUtil.LOGIN_USER_SUCCESS);
			return json + "";
		} else {
			// 登陆失败没有相关的账号信息
			json.add("user_status", keyUtil.LOGIN_USER_NO);
			json.add("status", keyUtil.SERVICE_FAIL);
		}
		// persistenceClientSession(response, request.getSession(), 86400);
		return json.toString();
	}

	// 上传头像
	@RequestMapping("/front/user/upload")
	@Role(role = Role.ROLE_USER_BUSINESS)
	public Object upload(@RequestParam() String uid, JSON json,
			HttpServletRequest request, @RequestParam("file") MultipartFile file) {
		String bimage = null;
		if (file != null && !uid.isEmpty()) {

			// 获得项目的路径
			ServletContext sc = request.getSession().getServletContext();
			// 上传位置
			String path = sc.getRealPath("img") + "/"; // 设定文件保存的目录

			String dbpath = "/img" + "/";
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			// 获得原始文件名
			String fileName = file.getOriginalFilename();
			// 新文件名
			String newFileName = UUID.randomUUID() + fileName;
			if (!file.isEmpty()) {
				try {
					FileOutputStream fos = new FileOutputStream(path
							+ newFileName);
					InputStream in = file.getInputStream();
					int b1 = 0;
					while ((b1 = in.read()) != -1) {
						fos.write(b1);
					}
					fos.close();
					in.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			bimage = dbpath + newFileName;
		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
		Map map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("uimage", bimage);
		userService.updateImage(map);
		return json.toString();
	}

	// 后台登陆
	@RequestMapping(value = "/front/user/blogin")
	public @ResponseBody
	Object login2(@RequestParam() String token, JSON json,
			HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		Map map = new HashMap<String, Object>();
		map.put("token", token);
		User user = userService.getUser(map);
		if (user != null) {
			// 被封杀
			if (user.getStatus() == 0) {
				json.add("status", keyUtil.SERVICE_FAIL);
				return json + "";
			}
			switch (user.getStatus()) {
			case 0:
				// 禁用
				json.add("user_status", 0);
				break;
			case 1:
				json.add("user_status", 1); // 正常
				break;
			}
			// 登陆成功
			Business business = businessService.getBusinessByKey(user.getBid());
				System.out.println(business+"商家");
			if (business != null && business.getStatus() > 0) { // 正常
				// 不是商家
				// 把用户放到session中，前台发送数据
				// 更新最后一次登陆时间
				// 是商家，需要把商家信息一并返回并且把bussiness 放到session中
				
				request.getSession().setAttribute(
						ResourceUtil.CURRENT_BUSINESS, business);
				switch (business.getStatus()) {
				case 1: // 禁用
					json.add("business_status", 1);
					break;
				case 2: // 正常
					json.add("business_status", 2);
					break;
				}
			}
			map.clear();
			map.put("uid", user.getUid());
			map.put("ulastlogin", DateUtil.getSqlDate());
			userService.updateLast(map);
			request.getSession().setAttribute(ResourceUtil.CURRENT_USER, user);
			// persistenceClientSession(response, request.getSession(), 86400);
			return json + "";
		} else {
			// 登陆失败没有相关的账号信息
			json.add("status", keyUtil.SERVICE_FAIL);
		}
		// persistenceClientSession(response, request.getSession(), 86400);
		return json.toString();
	}

	@RequestMapping("/front/user/registfirst")
	public @ResponseBody
	Object regist(@RequestParam() String ulogin, JSON json,
			HttpServletRequest request) {

		User user = userService.getUserByUlogin(ulogin);
		if (user != null) {
			// 说明注册过了
			json.add("status", 0);
			return json + "";
		}

		String isOk = (String) request.getSession().getAttribute("isSMS");
		if (isOk == null || "no".equals(isOk)) {
			// 不能发送短信
			json.add("status", -1);
			return json + "";
		}
		String accountSid = proper.getProperty("accountSid");
		String token = proper.getProperty("token");
		String appId = proper.getProperty("appId");
		String templateId = proper.getProperty("templateId");
		String para = SMSUtil.getRandNum(6);
		SMSUtil.testTemplateSMS(true, accountSid, token, appId, templateId,
				ulogin, para);
		String encode = UUID.randomUUID().toString();
		SMS sms = new SMS();
		sms.setPhone(ulogin);
		sms.setReturnCode(para);
		sms.setSendTime(DateUtil.getSqlDate());
		sms.setType(1);
		sms.setEncode(encode);
		json.add("ulogin", ulogin);
		json.add("code", para);
		json.add("encode", encode);
		request.getSession().setAttribute("VirCode", sms);
		return json.toString();
	}

	@RequestMapping("/front/user/registlast")
	public @ResponseBody
	Object regist2(@RequestParam() String ulogin,
			@RequestParam() String upassword, JSON json,
			HttpServletRequest request) throws ParseException {

		// 进行有效期的校验
		// SMS sms = smsSerice.getSMS(ulogin); //
		SMS sms = (SMS) request.getSession().getAttribute("VirCode");
		if (sms == null) {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
		String sendTime = sms.getSendTime();
		// 比较时间
		if (!DateUtil.compTo(sendTime, 10)) {
			// 超过10分钟了
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
		User user = new User();
		user.setUlogin(ulogin);
		user.setUphone(ulogin);
		user.setUdate(DateUtil.getSqlDate());
		user.setUpassword(CyptoUtils.decode(sms.getEncode(), upassword));
		userService.insertregist(user);
		// 删除验证码
		// smsSerice.deletedrop();
		request.getSession().removeAttribute("VirCode");
		return json + "";
	}

	// 退出登录
	@RequestMapping("/front/user/logout")
	public @ResponseBody
	Object logout(JSON json, HttpServletRequest request) {
		request.getSession().invalidate();

		return json.toString();
	}

	// 退出登录
	@RequestMapping("/front/user/logoutNoOnline")
	public @ResponseBody
	Object logout2(JSON json, @UserSession(session = "user") User user,
			HttpServletRequest request) {
		request.getSession().invalidate();
		Map map = new HashMap<String, Object>();
		map.put("uid", user.getUid());
		map.put("isonline", 0);
		userService.updateonli(map);
		return json.toString();
	}

	// 修改资料
	@RequestMapping("/front/user/updateInfo")
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object update(JSON json, @Validated() User user) {
		userService.updateInfo(user);
		return json.toString();
	}

	// public void persistenceClientSession(HttpServletResponse response,
	// HttpSession session, int savedSeconds) {
	// Cookie cookie = new Cookie("JSESSIONID", session.getId());
	// cookie.setPath("/");
	// cookie.setMaxAge(savedSeconds);// 单位:s
	// response.addCookie(cookie);
	// }

	@RequestMapping(value = "/front/user/registCode")
	@ResponseBody
	public void captcha(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CodeUtil.outputCaptcha(request, response);
	}

	@RequestMapping(value = "/front/user/registPhone")
	@ResponseBody
	public String isRegist(JSON json, @RequestParam() String ulogin) {

		User user = userService.getUserByUlogin(ulogin);
		if (user != null) {
			System.out.println(user);
			// 说明注册过了
			json.add("status", 0);
			return json + "";
		}
		return json + "";
	}

	@RequestMapping(value = "/front/user/registCodeVali")
	@ResponseBody
	public String captcha2(HttpServletRequest request, JSON json,
			@RequestParam() String code) {
		String ser_code = (String) request.getSession().getAttribute("imgcode"); // 获取服务器的图形验证吗
		System.out.println(ser_code + "sessopm");
		if (ser_code != null && ser_code.equals(code)) {
			// 相等 正确
			request.getSession().setAttribute("isSMS", "ok");
			return json + "";
		} else {
			// 不相等
			request.getSession().setAttribute("isSMS", "no");
			json.add("status", 0);
			return json + "";
		}

	}
	
	
	
	
	
	
	
	
	//测试接口
	@RequestMapping(value = "/front/user/login2")
	public @ResponseBody
	Object login2(@RequestParam() String ulogin,
			@RequestParam() String upassword, JSON json,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
//		String prikey = (String) request.getSession().getAttribute("uuid");
//		System.out.println("prikey" + prikey);
		// 拿到私钥
//		if (prikey == null) {
//			json.add("status", keyUtil.SERVICE_FAIL);
//			return json + "";
//		}
		Map map = new HashMap<String, Object>();
		map.put("ulogin", ulogin);
		map.put("upassword", upassword);
		User user = userService.getUser(map);
		if (user != null) {
			// 被封杀
			if (user.getStatus() == 0) { // || user.getIsonline() == 1 单点登陆
				json.add("user_status", keyUtil.LOGIN_USER_FAIL);
				return json + "";
			}
			// 登陆成功
			// 1 检测是否是商家
			String code = UUID.randomUUID().toString();
			Business business = businessService.getBusinessByKey(user.getBid());

			if (business != null && business.getStatus() > 0) { // 正常
				// 不是商家
				// 把用户放到session中，前台发送数据
				// 更新最后一次登陆时间
				// 是商家，需要把商家信息一并返回并且把bussiness 放到session中
				request.getSession().setAttribute(
						ResourceUtil.CURRENT_BUSINESS, business);
				json.add("business", business);

			}
			user.setUlastlogin(DateUtil.getSqlDate());
			user.setToken(TokenUtil.generateToken(user.getUlogin()));
			user.setIsonline(1);
			// 更新登陆状态 和 最后登陆时间
			userService.update(user);
			request.getSession().setAttribute(ResourceUtil.CURRENT_USER, user);
			json.add("user", user);
			json.add("user_status", keyUtil.LOGIN_USER_SUCCESS);
			return json + "";
		} else {
			// 登陆失败没有相关的账号信息
			json.add("user_status", keyUtil.LOGIN_USER_NO);
			json.add("status", keyUtil.SERVICE_FAIL);
		}
		// persistenceClientSession(response, request.getSession(), 86400);
		return json.toString();
	}
	
	
	
	
	
}
