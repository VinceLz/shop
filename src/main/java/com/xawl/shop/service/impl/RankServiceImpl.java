package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.RankDao;
import com.xawl.shop.domain.Rank;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.RankService;

@Service
public class RankServiceImpl implements RankService {

	@Resource
	private RankDao rankDao;

	@Override
	public List<Rank> findPage(Page page) {

		return rankDao.findPage(page);
	}

	@Override
	public void update(Map rank) {
		rankDao.updateRname(rank);
	}

	@Override
	public Rank get(Serializable id) {

		return rankDao.get(id);
	}

	
}
