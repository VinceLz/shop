package com.xawl.shop.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.RankDao;
import com.xawl.shop.domain.Rank;

@Repository
public class RankDaoImpl extends BaseDaoImpl<Rank> implements RankDao {

	public RankDaoImpl() {
		super.setNs("com.xawl.shop.RankMapper");
	}

	@Override
	public void updateRname(Map rank) {
		getSqlSession().update(this.getNs()+".updateRname",rank);
	}
}
