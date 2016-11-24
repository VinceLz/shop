package com.xawl.shop.dao;

import java.util.List;

import com.xawl.shop.domain.Home.HomeTop;

public interface HomeTopDao extends BaseDao<HomeTop> {
	List<HomeTop> findByType(String type);
}
