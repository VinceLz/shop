package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Post;
import com.xawl.shop.pagination.Page;

public interface PostService {

	void insert(Post psot);

	List<Post> findPage(Page page);

	void deleteById(Serializable id);

	void updateStatus(Post post);

	void updateCname(Map map);

	Post get(Serializable id);

	List<Post> findPage2(Page<Post> pagePost);

	List<Post> getById(Serializable id);

	void deletesByUid(Map map);

	void deleteAll(String[] pid, String msg);

}
