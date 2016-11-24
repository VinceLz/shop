package com.xawl.shop.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.HomeContentDao;
import com.xawl.shop.domain.Home.HomeContent;

@Repository
public class HomeContentDaoImpl extends BaseDaoImpl<HomeContent> implements
		HomeContentDao {
	public HomeContentDaoImpl() {
		super.setNs("com.xawl.shop.HomeContentMapper");
	}

	@Override
	public List<HomeContent> findContent() {
		return getSqlSession().selectList(getNs()+".findContent");
	}

	
}
