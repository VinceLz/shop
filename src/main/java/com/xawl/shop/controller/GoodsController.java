package com.xawl.shop.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
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
import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.VO.GoodsVO;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.GoodsService;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class GoodsController extends BaseController {
	private PropertiesUtil proper = new PropertiesUtil(this.getClass()
			.getClassLoader().getResourceAsStream("Message.properties"));// 读取配置文件

	@Resource
	private MessageService messageService;
	@Resource
	private GoodsService goodsService;

	// 查看详情
	@RequestMapping("/admin/goods/list")
	public @ResponseBody
	@JsonMenu(menu = "Goods")
	Object list(Page<Goods> page, JSON json) {

		List<Goods> findPage = goodsService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	@RequestMapping("/front/goods/list")
	public @ResponseBody
	Object list2(Page<Goods> page, JSON json)
			throws UnsupportedEncodingException {
		List<Goods> findPage = goodsService.findPage2(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	// todo 发送内信提醒
	// 下架
	@RequestMapping("/admin/goods/updateStatus")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	String updateStatus(@RequestParam() String gid,
			@RequestParam() String status, JSON json) {
		Map map = new HashMap<String, Object>();
		map.put("gid", gid);
		if ("0".equals(status)) {
			map.put("status", "1"); // 启用
			goodsService.updateStatus(map, proper.getProperty("goods.start"));
		} else if ("1".equals(status)) {
			map.put("status", "0");
			// 下架，需要发送内信
			goodsService.updateStatus(map, proper.getProperty("goods.stop"));
		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
		}

		return json + "";
	}

	// todo 获取最新发布的服务
	// todo 对方法进行权限验证，商家才能执行的方法，普通用户无法执行的权限 利用拦截做
	@RequestMapping(value = "/front/goods/add", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	String add(@Validated Goods goods, JSON json) {
		/*
		 * String bimage = null; // 获得项目的路径 ServletContext sc =
		 * request.getSession().getServletContext(); // 上传位置 String path =
		 * sc.getRealPath("img") + "/"; // 设定文件保存的目录
		 * 
		 * String dbpath = "/img" + "/"; File f = new File(path); if
		 * (!f.exists()) f.mkdirs(); for (int i = 0; i < file.length; i++) { //
		 * 获得原始文件名 String fileName = file[i].getOriginalFilename(); // 新文件名
		 * String newFileName = UUID.randomUUID() + fileName; if
		 * (!file[i].isEmpty()) { try { FileOutputStream fos = new
		 * FileOutputStream(path + newFileName); InputStream in =
		 * file[i].getInputStream(); int b1 = 0; while ((b1 = in.read()) != -1)
		 * { fos.write(b1); } fos.close(); in.close(); } catch (Exception e) {
		 * e.printStackTrace(); } } if (bimage == null) { bimage = dbpath +
		 * newFileName; } else { bimage = bimage + "," + dbpath + newFileName; }
		 * }
		 */
		// goods.setGimage(bimage);
		goods.setGscore(1.0F);
		goods.setGdate(DateUtil.getSqlDate());
		// goods.setGkey(keyUtil.createKey()); 由前台发送
		goodsService.insert(goods);
		return json + "";
	}

	// 权限验证
	@RequestMapping("/front/goods/delete")
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS_ADMIN)
	String delete(@RequestParam() String[] gid, JSON json) {
		goodsService.deleteAll(gid, null);
		return json + "";
	}

	// 获取服务
	@RequestMapping("/front/goods/get")
	public @ResponseBody
	String get(@RequestParam() String gid, JSON json) {
		Goods goods = goodsService.get(gid);

		json.add("goods", goods);

		return json + "";
	}

	// 获取服务 和对应的评价打包发送
	@RequestMapping("/front/goods/get2comment")
	public @ResponseBody
	@Role(role = Role.ROLE_USER_ADMIN)
	String getGoods2Comment(@RequestParam() String gid, JSON json) {

		GoodsVO result = goodsService.get2comment(gid);

		json.add("goods", result);

		return json + "";
	}

	@RequestMapping("/front/goods/update")
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	String update(@Validated Goods goods, JSON json) {
		goods.setGdate(DateUtil.getSqlDate());
		goodsService.update(goods);
		return "" + json;
	}

	// 获取自己发布的产品 权限
	@RequestMapping("/front/goods/mygoods")
	public @ResponseBody
	String myGoods(JSON json, @UserSession(session = "business") Business b) {
		System.out.println("mygoods" + b);
		List<Goods> goods = goodsService.getBusinessGoods(b.getBid());
		json.add("goods", goods);
		return json + "";
	}

	// 发布服务时候的上传图片
	@RequestMapping(value = "/front/goods/upload", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_BUSINESS)
	String upload(@RequestParam("file") MultipartFile file[],
			HttpServletRequest request, JSON json, @RequestParam() String gkey) {

		if (file.length > 0 && !gkey.isEmpty()) {
			String bimage = null;
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
				String newFileName = UUID.randomUUID() + ".jpg";
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
			map.put("gkey", gkey);
			map.put("gimage", bimage);
			goodsService.updateGimage(map);
			return json + "";

		} else {
			json.add("status", keyUtil.SERVICE_FAIL);
			return json + "";
		}

	}

}
