package com.xawl.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.CommentDao;
import com.xawl.shop.domain.Comment;

@Repository
public class CommentDaoImpl extends BaseDaoImpl<Comment> implements CommentDao {

	public CommentDaoImpl() {
		super.setNs("com.xawl.shop.CommentMapper");
	}

	@Override
	public List<Comment> getList(String gid) {
		return this.getSqlSession().selectList(this.getNs() + ".getList", gid);
	}
}
