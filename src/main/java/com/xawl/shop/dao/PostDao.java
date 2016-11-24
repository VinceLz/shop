package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Post;
import com.xawl.shop.pagination.Page;

public interface PostDao extends BaseDao<Post>{
			
	void updateStatus(Post post);
	void updateCname(Map map);
	List<Post> findPage2(Page<Post> pagePost);
	List<Post> getById(Serializable id);
	void deletesByUid(Map map);
	
}
