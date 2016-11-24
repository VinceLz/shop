package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.CartItemDao;
import com.xawl.shop.domain.CartItem;
import com.xawl.shop.service.CartItemService;

@Service
public class CartItemServiceImpl implements CartItemService {

	@Resource
	private CartItemDao cartItemDao;

	@Override
	public List<CartItem> findById(Serializable id) {
		return cartItemDao.findById(id);
	}

	@Override
	public void insert(CartItem cartitem) {
		cartItemDao.insert(cartitem);
	}

	@Override
	public void delete(Serializable[] gid) {
		cartItemDao.delete(gid);
	}

	@Override
	public void deleteByCid(Map cartitem_id) {
		cartItemDao.deleteByCid(cartitem_id);
	}

	@Override
	public void deleteByGid(Integer gid) {
		cartItemDao.deleteByGid(gid);
	}

}
