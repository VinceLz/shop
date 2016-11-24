package com.xawl.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.Rank;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.RankService;
import com.xawl.shop.util.keyUtil;

@Controller
public class RankController extends BaseController {

	@Resource
	private RankService rankService;

	@RequestMapping("/admin/rank/list")
	public @ResponseBody
	@JsonMenu(menu = "Rank")
	Object list(Page<Rank> page, JSON json) {
		List<Rank> findPage = rankService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString() ;
	}

	@RequestMapping("/admin/rank/update")
	public @ResponseBody
	@Role(role =Role.ROLE_ADMIN)
	Object edit(@RequestParam()String rid,@RequestParam()String rname, JSON json) {
		Map map=new HashMap<String, String>();
		map.put("rid", rid);
		map.put("rname", rname);
		rankService.update(map);
		return json.toString();

	}

	@RequestMapping("/admin/rank/get")
	public @ResponseBody
	@JsonMenu(menu = "Rank")
	Object view(@RequestParam()String rid, JSON json) {
		Rank rank = rankService.get(rid);
		if (rank!=null) {
			json.add("rank", rank);
		}else{
			json.add("status", keyUtil.SERVICE_FAIL);
		}
		
		return json.toString();
	}

}
