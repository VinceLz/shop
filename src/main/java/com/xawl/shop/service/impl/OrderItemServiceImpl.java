package com.xawl.shop.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.GoodsDao;
import com.xawl.shop.dao.OrderItemDao;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.service.OrderItemService;

@Service
public class OrderItemServiceImpl implements OrderItemService {

	@Resource
	private OrderItemDao orderItemDao;
	@Resource
	private GoodsDao goodsDao;

	@Override
	public void insert(OrderItem orderitem) {
		orderItemDao.insert(orderitem);
		// 对应的gid 服务售量+1
		goodsDao.saleAdd(orderitem.getGid() + "");
	}

}
