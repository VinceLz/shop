package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Order;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.domain.VO.OrderItemVO;
import com.xawl.shop.domain.VO.OrderVO;
import com.xawl.shop.pagination.Page;

public interface OrderService {
	List<Order> findPage(Page page);

	List<OrderItemVO> getAll(Serializable oid);

	void examine(Map map);

	void insert(List<OrderVO> order);

	Map<String, Map<String,Integer>> getOrderStatusNumber(Serializable uid);
	List<OrderVO> getStatusByCode(Map map);

	void updateCode(Map map);
	Map<String, Map<String, Integer>> getOrderStatusNumberByBid(Serializable bid);

	void updateCodeBusiness(Map map);
}
