package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;

import com.xawl.shop.domain.Comment;
import com.xawl.shop.pagination.Page;

public interface CommentService {
	void deleteById(Serializable id);
	void delete(String []id);
	List<Comment> findPage(Page page);
	void insert(Comment c);
	List<Comment> get(String gid);
}
