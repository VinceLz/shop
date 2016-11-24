package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.CartItem;

public interface CartItemDao extends BaseDao<CartItem> {
	List<CartItem> findById(Serializable id);
	void deleteByCid(Map cartitem_id);
	void deleteByGid(Integer gid);
}
