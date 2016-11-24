package com.xawl.shop.dao.impl;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.OrderItemDao;
import com.xawl.shop.domain.OrderItem;

@Repository
public class OrderItemDaoImpl extends BaseDaoImpl<OrderItem> implements
		OrderItemDao {
	public OrderItemDaoImpl() {
		super.setNs("com.xawl.shop.OrderItemMapper");
	}


}
