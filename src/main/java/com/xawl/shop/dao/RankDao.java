package com.xawl.shop.dao;

import java.util.Map;

import com.xawl.shop.domain.Rank;

public interface RankDao extends BaseDao<Rank> {

	void updateRname(Map rank);
	
}
