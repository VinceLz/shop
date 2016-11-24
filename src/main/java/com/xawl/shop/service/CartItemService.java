package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.CartItem;

public interface CartItemService {
	List<CartItem> findById(Serializable id);

	void insert(CartItem cartitem);

	void delete(Serializable[] gid);

	void deleteByCid(Map cartitem_id);

	void deleteByGid(Integer gid);
		
}
