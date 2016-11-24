package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.CartItemDao;
import com.xawl.shop.domain.CartItem;

@Repository
public class CartItemImpl extends BaseDaoImpl<CartItem> implements CartItemDao {

	public CartItemImpl() {
		super.setNs("com.xawl.shop.CartitemMapper");
	}

	@Override
	public List<CartItem> findById(Serializable id) {
		return this.getSqlSession().selectList(this.getNs() + ".getById", id);
	}

	@Override
	public void deleteByCid(Map cartitem_id) {
		getSqlSession().delete(getNs() + ".deleteByCid", cartitem_id);
	}

	@Override
	public void deleteByGid(Integer gid) {
		getSqlSession().delete(getNs() + ".deleteByGid", gid);
	}

}
