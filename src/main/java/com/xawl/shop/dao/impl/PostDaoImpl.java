package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.PostDao;
import com.xawl.shop.domain.Post;
import com.xawl.shop.pagination.Page;

@Repository
public class PostDaoImpl extends BaseDaoImpl<Post> implements PostDao {

	public PostDaoImpl() {
		super.setNs("com.xawl.shop.PostMapper");
	}

	@Override
	public void updateStatus(Post post) {
		this.getSqlSession().update(this.getNs() + ".updateStatus", post);
	}

	@Override
	public void updateCname(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateCname", map);
	}

	@Override
	public List<Post> findPage2(Page<Post> pagePost) {
		return this.getSqlSession().selectList(this.getNs() + ".findPage2",
				pagePost);
	}

	@Override
	public List<Post> getById(Serializable id) {
		return getSqlSession().selectList(getNs() + ".getAllPost", id);
	}

	@Override
	public void deletesByUid(Map map) {
		getSqlSession().delete(getNs() + ".deletesByUid", map);
	}

	

}
