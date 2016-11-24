package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.CommentDao;
import com.xawl.shop.domain.Comment;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	@Resource
	private CommentDao commentDao;
	
	
	@Override
	public void deleteById(Serializable id) {
		commentDao.deleteById(id);
	}

	@Override
	public void delete(String[] id) {
			commentDao.delete(id);
	}

	@Override
	public List<Comment> findPage(Page page) {
		return commentDao.findPage(page);
	}

	@Override
	public void insert(Comment c) {
		commentDao.insert(c);
	}

	@Override
	public List<Comment> get(String gid) {
		return commentDao.getList(gid);
	}

}
