package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Rank;
import com.xawl.shop.pagination.Page;

public interface RankService {
	List<Rank> findPage(Page page);
	void update(Map rank);
	Rank get(Serializable id);
	
}
