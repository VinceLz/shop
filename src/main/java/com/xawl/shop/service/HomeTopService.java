package com.xawl.shop.service;

import java.util.List;

import com.xawl.shop.domain.Home.HomeTop;

public interface HomeTopService {
	List<HomeTop> findByType(String type);
	
}
