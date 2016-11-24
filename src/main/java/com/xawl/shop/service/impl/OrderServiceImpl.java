package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.CartItemDao;
import com.xawl.shop.dao.GoodsDao;
import com.xawl.shop.dao.OrderDao;
import com.xawl.shop.dao.OrderItemDao;
import com.xawl.shop.domain.Order;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.domain.VO.OrderItemVO;
import com.xawl.shop.domain.VO.OrderVO;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.CartItemService;
import com.xawl.shop.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderDao orderDao;

	@Resource
	private OrderItemDao orderItemDao;

	@Resource
	private CartItemDao cartItemDao;

	@Resource
	private GoodsDao goodsDao;

	@Override
	public List<Order> findPage(Page page) {

		return orderDao.findPage(page);
	}

	@Override
	public List<OrderItemVO> getAll(Serializable oid) {
		return orderDao.getAll(oid);
	}

	@Override
	public void examine(Map map) {
		orderDao.examine(map);
	}
	@Override
	public void insert(List<OrderVO> order) {
		for (OrderVO orderVO : order) {
			orderDao.insertAll(orderVO);
			for (OrderItem item : orderVO.getItemlist()) {
				orderItemDao.insert(item);
				cartItemDao.deleteByGid(item.getGid());
				goodsDao.saleAdd(item.getGid() + "");
			}
		}
	}

	@Override
	public Map<String, Map<String, Integer>> getOrderStatusNumber(
			Serializable uid) {
		return orderDao.getOrderStatusNumberList(uid);
	}

	@Override
	public List<OrderVO> getStatusByCode(Map map) {
		return orderDao.getStatusByCode(map);
	}

	@Override
	public Map<String, Map<String, Integer>> getOrderStatusNumberByBid(
			Serializable bid) {
		return orderDao.getOrderStatusNumberByBid(bid);
	}

	@Override
	public void updateCode(Map map) {
		orderDao.updateCode(map);
	}

	@Override
	public void updateCodeBusiness(Map map) {
		orderDao.updateCodeBusiness(map);
	}

}
