package com.xawl.shop.dao;

import java.util.List;

import com.xawl.shop.domain.Home.HomeContent;

public interface HomeContentDao extends BaseDao<HomeContent> {

	List<HomeContent> findContent();
}
