package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Order;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.domain.VO.OrderItemVO;
import com.xawl.shop.domain.VO.OrderVO;

public interface OrderDao extends BaseDao<Order> {
	List<OrderItemVO> getAll(Serializable id);

	void examine(Map map);

	void insertAll(OrderVO order);

	Map<String, Map<String,Integer>> getOrderStatusNumberList(Serializable uid);
	List<OrderVO> getStatusByCode(Map map);
	void updateCode(Map map);
	Map<String, Map<String, Integer>> getOrderStatusNumberByBid(Serializable bid);

	void updateCodeBusiness(Map map);
}
