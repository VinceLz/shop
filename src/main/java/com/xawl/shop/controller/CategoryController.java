package com.xawl.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.Category;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.VO.CategoryVO;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.service.CategoryService;
import com.xawl.shop.service.GoodsService;
import com.xawl.shop.service.PostService;
import com.xawl.shop.util.CategoryUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class CategoryController extends BaseController {

	// 权限 就是有些方法必须登录，并且角色满足
	// 有些不需要登录即可

	// 有权限验证注解，就需要验证，没有注解，说明公开
	@Resource
	private CategoryService categoryService;
	@Resource
	private GoodsService goodsService;
	@Resource
	private PostService postService;

	@RequestMapping("/admin/category/list")
	public @ResponseBody @JsonMenu(menu="Category")
	Object list(JSON json, HttpServletResponse response) {
		// 获取所有分类
		List<CategoryVO> list = categoryService.find(); // 包括所有级别
		// 开始解析封装这个VO对象
		List<CategoryVO> root = CategoryUtil.parse(list);
		json.add("root", root);
		response.addHeader("Cache-Control", "max-age="+keyUtil.CACHE_TIME);
		return json.toString();
	}

	// 需要把pid传过来 pid=对应的上级菜单
	@RequestMapping("/admin/category/add")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object add(@Validated Category category, JSON json) {
		// pid 是0 表示是一级菜单
		categoryService.insertByPid(category);

		return json.toString();
	}

	// 删除
	@RequestMapping("/admin/category/delete")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object delete(@RequestParam() String cid, JSON json) {
		categoryService.deleteByIdAll(cid); // 后期需要做多级删除 从数据库中联级删除，但是mysql
		// 不支持这样的sql语句，所以只能把pid也传进来 但是为了通用，就不需要pid了
		return json.toString();
	}

	// 更新名字
	@RequestMapping("/admin/category/update")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object update(@RequestParam() String cid, @RequestParam() String cname,
			JSON json) {
		Map map = new HashMap<String, Object>();
		map.put("cname", cname);
		map.put("cid", cid);
		categoryService.updateCname(map);
		// 更新好后，需要把goods 和post都更新一遍

		goodsService.updateCname(map);
		postService.updateCname(map);
		return json.toString();
	}
}
