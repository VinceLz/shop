package com.xawl.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.HomeTopDao;
import com.xawl.shop.domain.Home.HomeTop;

@Repository
public class HomeTopDaoImpl extends BaseDaoImpl<HomeTop> implements HomeTopDao {
	public HomeTopDaoImpl() {
		super.setNs("com.xawl.shop.HomeTopMapper");
	}

	@Override
	public List<HomeTop> findByType(String type) {
		return getSqlSession().selectList(this.getNs() + ".findByType", type);
	}
}
