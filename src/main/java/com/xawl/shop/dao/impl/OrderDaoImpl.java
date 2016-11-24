package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.OrderDao;
import com.xawl.shop.domain.Order;
import com.xawl.shop.domain.OrderItem;
import com.xawl.shop.domain.VO.OrderItemVO;
import com.xawl.shop.domain.VO.OrderVO;

@Repository
public class OrderDaoImpl extends BaseDaoImpl<Order> implements OrderDao {

	public OrderDaoImpl() {
		super.setNs("com.xawl.shop.OrderMapper");
	}

	@Override
	public List<OrderItemVO> getAll(Serializable id) {
		return super.getSqlSession().selectList(this.getNs() + ".getall", id);

	}

	@Override
	public void examine(Map map) {
		getSqlSession().update(getNs() + ".examine", map);
	}

	@Override
	public void insertAll(OrderVO order) {
		getSqlSession().insert(getNs() + ".insertAll", order);
	}

	@Override
	public Map<String, Map<String, Integer>> getOrderStatusNumberList(
			Serializable uid) {
		return getSqlSession().selectMap(getNs() + ".getOrderStatusNumber",
				uid, "key");
	}

	@Override
	public List<OrderVO> getStatusByCode(Map map) {
		return getSqlSession().selectList(getNs() + ".getallByCode", map);
	}

	@Override
	public Map<String, Map<String, Integer>> getOrderStatusNumberByBid(
			Serializable bid) {
		return getSqlSession().selectMap(getNs() + ".getallByCode2Business",
				bid, "key");
	}

	@Override
	public void updateCode(Map map) {
			getSqlSession().update(getNs()+".updateCode",map);
	}

	@Override
	public void updateCodeBusiness(Map map) {
			getSqlSession().update(getNs()+".updateCodeBusiness",map);
	}
}
