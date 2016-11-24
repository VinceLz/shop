package com.xawl.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.HomeContentDao;
import com.xawl.shop.domain.Home.HomeContent;
import com.xawl.shop.service.HomeContentService;

@Service
public class HomeContentServiceImpl implements HomeContentService {
	@Resource
	private HomeContentDao homeContentDao;

	

	@Override
	public List<HomeContent> findContent() {
		return homeContentDao.findContent();
	}

}
