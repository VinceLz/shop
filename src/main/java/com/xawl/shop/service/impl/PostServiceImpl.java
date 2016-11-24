package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.controller.MessageController;
import com.xawl.shop.dao.PostDao;
import com.xawl.shop.domain.Message;
import com.xawl.shop.domain.Post;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.service.PostService;
import com.xawl.shop.util.MessageUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.PushMessageUtil;

@Service
public class PostServiceImpl implements PostService {

	@Resource
	private PostDao postDao;

	@Resource
	private MessageService messageService;

	@Override
	public void insert(Post psot) {
		postDao.insert(psot);
	}

	@Override
	public List<Post> findPage(Page page) {
		return postDao.findPage(page);
	}

	@Override
	public void deleteById(Serializable id) {
		postDao.deleteById(id);
	}

	@Override
	public void deleteAll(String[] pid, String msg) {
		if (msg != null) {
			Post post = null;
			for (int i = 0; i < pid.length; i++) {
				if (!pid[i].isEmpty()) {
					post = get(pid[i]);
					deleteById(pid[i]); // 发送内信
					Message message = MessageUtil.createMessage();
					message.setUid(post.getUid());
					message.setMessage(msg);
					message.setMessage_id(UUID.randomUUID().toString());
					message.setPname(post.getTitle());
					message.setType(MessageController.MESSAGE_SEND_USER);
					messageService.insertUserMessage(message);
					PushMessageUtil.send(post.getUid() + "", false, null);
				}
			}
		} else {
			postDao.delete(pid);
		}

	}

	@Override
	public void updateStatus(Post post) {
		postDao.updateStatus(post);
	}

	@Override
	public void updateCname(Map map) {
		postDao.updateCname(map);
	}

	@Override
	public Post get(Serializable id) {
		return postDao.get(id);
	}

	@Override
	public List<Post> findPage2(Page<Post> pagePost) {
		return postDao.findPage2(pagePost);
	}

	@Override
	public List<Post> getById(Serializable id) {
		return postDao.getById(id);
	}

	@Override
	public void deletesByUid(Map map) {
		postDao.deletesByUid(map);
	}

}
