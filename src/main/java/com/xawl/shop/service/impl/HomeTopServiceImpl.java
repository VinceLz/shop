package com.xawl.shop.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.HomeTopDao;
import com.xawl.shop.domain.Home.HomeTop;
import com.xawl.shop.service.HomeTopService;

@Service
public class HomeTopServiceImpl implements HomeTopService{

	@Resource
	private HomeTopDao homeTopDao;
	@Override
	public List<HomeTop> findByType(String type) {
		return homeTopDao.findByType(type);
	}

}
