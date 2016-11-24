package com.xawl.shop.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xawl.shop.domain.Comment;
import com.xawl.shop.domain.JSON;
import com.xawl.shop.interceptor.JsonMenu;
import com.xawl.shop.interceptor.Role;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.CommentService;
import com.xawl.shop.util.BadWorldUtil;
import com.xawl.shop.util.DateUtil;
import com.xawl.shop.util.keyUtil;

@Controller
public class CommentController extends BaseController {

	@Resource
	private CommentService commentService;

	@Resource(name = "badUtil")
	private BadWorldUtil badWorldUtil;

	@RequestMapping("/admin/comment/list")
	public @ResponseBody
	@Role(role = Role.ROLE_ADMIN)
	@JsonMenu(menu = "Comment")
	Object list(Page<Comment> page, JSON json) {
		List<Comment> findPage = commentService.findPage(page);
		page.setResults(findPage);
		json.add("page", page);
		return json.toString();
	}

	// todo 删除完应该发送内信提示
	@RequestMapping("/admin/comment/deleteall")
	@Role(role = Role.ROLE_ADMIN)
	public @ResponseBody
	Object deleteAll(@RequestParam() String[] comment_id, JSON json) {
		commentService.delete(comment_id);
		// 发送内信
		return json.toString();
	}

	@RequestMapping("/front/comment/get")
	public @ResponseBody
	Object get(@RequestParam() String gid, JSON json) {

		List<Comment> list = commentService.get(gid);

		json.add("comment", list);

		return json.toString();
	}

	@RequestMapping(value = "/front/comment/add", method = RequestMethod.POST)
	public @ResponseBody
	@Role(role = Role.ROLE_USER)
	Object add(@Validated Comment comment, JSON json) {
		String content = comment.getContent();
		comment.setContent(badWorldUtil.replaceCheck(content)); // 敏感词过滤
		comment.setDate(DateUtil.getSqlDate());
		commentService.insert(comment);
		return json.toString();
	}

	// todo 获取最新发表的评论

}
