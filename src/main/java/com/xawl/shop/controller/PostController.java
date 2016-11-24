package com.xawl.shop.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.JSON;
import com.xawl.shop.domain.Message;
import com.xawl.shop.domain.Post;
import com.xawl.shop.domain.User;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.interceptor.UserSession;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.service.PostService;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.MessageUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class PostController extends BaseController {
	@Resource
	private PostService postService;
	private PropertiesUtil proper = new PropertiesUtil(this.getClass()
			.getClassLoader().getResourceAsStream("Message.properties"));// 读取配置文件
	@Resource
	private MessageService messageService;

	@RequestMapping("/admin/post/list")
	public @ResponseBody
	@JsonMenu(menu = "Post")
	Object list(Page<Post> pagePost, JSON jsonPost) throws IOException {

		List<Post> find = postService.findPage(pagePost);
		pagePost.setResults(find);
		jsonPost.add("page", pagePost);
		return jsonPost.toString();
	}

	@RequestMapping("/front/post/list")
	public @ResponseBody
	Object list2(Page<Post> pagePost, JSON jsonPost) throws IOException {

		List<Post> find = postService.findPage2(pagePost);
		pagePost.setResults(find);
		jsonPost.add("page", pagePost);
		return jsonPost.toString();
	}

	@RequestMapping("/front/post/get")
	public @ResponseBody
	Object getPost(JSON json, @RequestParam() String pid) {
		Post post = postService.get(pid);
			json.add("post", post);
		return json.toString();
	}

	// 发送内信 todo
	@RequestMapping("/admin/post/deleteall")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	Object delete(@RequestParam() String pid[], JSON json) {
		postService.deleteAll(pid,proper.getProperty("post.delete"));
		return json.toString();
	}

	// //发送内信
	// @RequestMapping("/admin/post/updateStatus")
	// public String updateStatus(Post post, JSON json) {
	// if (post.getStatus().equals("0") || post.getStatus() == 0) {
	// post.setStatus(1);
	// } else {
	// post.setStatus(0);
	// }
	// postService.updateStatus(post);
	// return json + "";
	// }

	// 前台发帖
	@RequestMapping(value = "/front/post/add", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object add(@Validated Post post, JSON json) {
		post.setPdate(DateUtil.getSqlDate());
		postService.insert(post);
		return json.toString();
	}

	// 前台删帖
	@RequestMapping("/front/post/deleteall")
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object deleteall(JSON josn, @RequestParam() String pid[]) {
		postService.deleteAll(pid, null);
		return josn.toString();
	}

	// 前台查看自己的发帖
	@RequestMapping("/front/post/mypost")
	public @ResponseBody
	
	Object myPost(@UserSession(session = "user") User user, JSON json) {
		List<Post> byId = postService.getById(user.getUid());
		
			json.add("post", byId);
		
		return json.toString();
	}

	// 删除指定用户的帖子
	@RequestMapping("/front/post/delete")
	
	public @ResponseBody
	Object deleteByUser(@UserSession(session = "user") User user, JSON json,
			@RequestParam() String[] pid) {
		Map map = new HashMap<String, Object>();
		map.put("pid", pid);
		map.put("uid", user.getUid());
		postService.deletesByUid(map);
		return json + "";
	}
}