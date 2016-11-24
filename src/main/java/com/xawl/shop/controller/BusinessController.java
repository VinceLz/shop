package com.xawl.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.User;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.BusinessService;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.service.UserService;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.ResourceUtil;
import com.xawl.shop.util.keyUtil;

//-1 表示审核不通过 0 待审核 1禁用 2 正常
@Controller
public class BusinessController extends BaseController {
	public static String REVIEWED_FAIL = "-1";
	public static String REVIEWED_WAIT = "0";
	public static String STOP = "1";
	public static String OK = "2";
	private PropertiesUtil proper = new PropertiesUtil(this.getClass()
			.getClassLoader().getResourceAsStream("Message.properties"));// 读取配置文件
	@Resource
	private MessageService messageService;
	@Resource
	private BusinessService businessService;
	@Resource
	private UserService userService;

	/**
	 * 前台和后台应该是分开写2套接口，因为有些敏感信息需要过滤
	 * 
	 * @param page
	 * @param json
	 * @return
	 */
	// 查询
	@RequestMapping("/admin/business/list")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	@JsonMenu(menu = "Business")
	Object list(Page<Business> page, JSON json) {
		// 商家列表 把状态-1 或者0的删去
		page.getParams().put("cool2ok", STOP);// 表示>=1的 集合 也就是 正常和禁用这些集合
		List<Business> findPage = businessService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	// 获取商家的信息 把商家图片，敏感信息过滤删去了
	// 没有查找到就返回0
	@RequestMapping("/front/business/get")
	public @ResponseBody
	Object view(@RequestParam() String bid, JSON json) {
		Business business = businessService.get(bid);
		json.add("business", business);
		return json.toString();
	}

	// 获取自己的信息 User是从session中获取到了，可以使用拦截器注入用户
	@RequestMapping("/front/business/mybusiness")
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	Object MyBusiness(@UserSession(session = "business") Business business,
			JSON json) {
		json.add("business", business);
		return json.toString();
	}

	// 申请商家需要上传信息
	@RequestMapping(value = "/front/business/add", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object add(@UserSession(session = "user") User user, @Validated Business b,
			JSON json, HttpServletRequest request) {
		b.setBdate(DateUtil.getSqlDate());
		businessService.insert(b);
		// 商家插入成功 更新用户bid字段
		user.setBid(b.getBid());
		userService.updateBid(user);
		return json.toString();
	}

	// 禁用后提示，或者在点击按钮后提示 內信
	@RequestMapping("/admin/business/updateStatus")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object update(@RequestParam() String bid, @RequestParam() String status,
			JSON json) {
		Map map = new HashMap<String, Object>();
		map.put("bid", bid);
		if ("1".equals(status)) {
			map.put("status", OK); // 2是正常
			businessService.updateStatus(map,
					proper.getProperty("business.start"));
		} else if ("2".equals(status)) {
			map.put("status", STOP); // 1是冻结 0是带审核
			businessService.updateStatus(map,
					proper.getProperty("business.stop"));
		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
		}
		return json.toString();
	}

	// 申请时候的上传图片
	@RequestMapping(value = "/front/business/upload", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	String upload(@RequestParam("file") MultipartFile file[],
			HttpServletRequest request, JSON json, @RequestParam() String bid) {
		String bimage = null;
		if (file != null) { // 图片不为空
			// 业务逻辑
			// 1 需要上传文件
			// 获得项目的路径
			ServletContext sc = request.getSession().getServletContext();
			// 上传位置
			String path = sc.getRealPath("img") + "/"; // 设定文件保存的目录

			String dbpath = "/img" + "/";
			File f = new File(path);
			if (!f.exists())
				f.mkdirs();
			for (int i = 0; i < file.length; i++) {
				// 获得原始文件名
				String fileName = file[i].getOriginalFilename();
				// 新文件名
				String newFileName = UUID.randomUUID() + fileName;
				if (!file[i].isEmpty()) {
					try {
						FileOutputStream fos = new FileOutputStream(path
								+ newFileName);
						InputStream in = file[i].getInputStream();
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
				if (bimage == null) {
					bimage = dbpath + newFileName;
				} else {
					bimage = bimage + "," + dbpath + newFileName;
				}
			}
			Map map = new HashMap<String, Object>();
			map.put("bid", bid);
			map.put("bimage", bimage);
			businessService.updateGimage(map);
			return json + "";
		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}
	}

	// 待审核
	@RequestMapping("/admin/list/examinelist")
	@JsonMenu(menu = "Business2")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object examine(JSON json) {
		Page<Business> page = new Page<Business>();
		// -1 表示审核不通过 0 待审核 1禁用 2 正常
		page.getParams().put("status", REVIEWED_WAIT);
		List<Business> findPage = businessService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	@RequestMapping("/admin/list/examineok")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object examine2(@RequestParam() String bid, JSON json) throws Exception {
		Map map = new HashMap<String, Object>();
		map.put("status", OK);
		map.put("bid", bid);
		businessService.updateStatus(map, proper.getProperty("business.pass")); // 审核通过
		return json.toString();
	}

	// 审核不通过
	@RequestMapping("/admin/list/examineno")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object examine3(@RequestParam() String bid, JSON json) throws Exception {
		// 发送内信
		Map map = new HashMap<>();
		map.put("status", REVIEWED_FAIL);
		map.put("bid", bid);
		businessService
				.updateStatus(map, proper.getProperty("business.nopass"));
		return json.toString();
	}

	// 审核不通过连表
	@RequestMapping("/admin/business/examineoklist")
	public @ResponseBody
	Object daiexamine(JSON json) {
		Page<Business> page = new Page<Business>();
		page.getParams().put("status", REVIEWED_FAIL);
		List<Business> findPage = businessService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	// 获取商家状态码
	@RequestMapping("/front/business/code")
	public @ResponseBody @Role(role=Role.ROLE_USER)
	Object getcode(JSON json, @UserSession(session = "user") User user) {
		int status = businessService.getcode(user.getBid());
		json.add("business_status", status);
		return json.toString();
	}

	@RequestMapping("/front/business/getByBkey")
	public @ResponseBody
	Object freeze(@UserSession(session = "user") User user, JSON json) {
		String bid = user.getBid();
		Business businessByKey = businessService.getBusinessByKey(bid);
		json.add("business", businessByKey);
		return json.toString();
	}

	// 修改资料
	@RequestMapping("/front/business/updateInfo")
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	Object update(JSON json, Business business) {
		businessService.updateInfo(business);
		return json.toString();
	}

	// 修改资料
	@RequestMapping("/front/business/updateSession")
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object update(HttpServletRequest request, JSON json,
			@UserSession(session = "user") User user) {
		String bid = user.getBid();
		if ("0".equals(bid) || bid.isEmpty()) {
			// 不是商家
			request.getSession().removeAttribute(ResourceUtil.CURRENT_BUSINESS);
			return json.toString();
		}
		Business business = businessService.getBusinessByKey(bid);
		request.getSession().setAttribute(ResourceUtil.CURRENT_BUSINESS,
				business);
		return json.toString();
	}

}
