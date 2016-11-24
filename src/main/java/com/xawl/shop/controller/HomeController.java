package com.xawl.shop.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.Home.HomeContent;
import com.xawl.shop.domain.Home.HomeTop;
import com.xawl.shop.service.HomeContentService;
import com.xawl.shop.service.HomeTopService;
import com.xawl.shop.util.keyUtil;

/**
 * 主页控制器
 * 
 * @author kernel
 * 
 */
@Controller
public class HomeController extends BaseController {

	@Resource
	private HomeContentService homeContentService;
	@Resource
	private HomeTopService homeTopService;

	// 首页加载
	@RequestMapping("/front/home/index")
	public @ResponseBody
	Object Home(JSON json, HttpServletResponse response) throws Exception {
		List<HomeContent> content = homeContentService.findContent();
		List<HomeTop> image = homeTopService.findByType("0");
		List<HomeTop> top = homeTopService.findByType("1");
		System.out.println(top);
		json.add("image", image);
		json.add("top", top);
		json.add("content", content);
		response.addHeader("Cache-Control", "max-age=" + keyUtil.CACHE_TIME);		
		return json + "";
	}

	// 后期控制升级 信息配置等

}
