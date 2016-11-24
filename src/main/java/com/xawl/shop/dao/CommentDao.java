package com.xawl.shop.dao;

import java.util.List;

import com.xawl.shop.domain.Comment;

public interface CommentDao extends BaseDao<Comment>{

	List<Comment> getList(String gid);
	
}
