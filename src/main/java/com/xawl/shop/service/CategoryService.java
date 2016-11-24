package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Category;
import com.xawl.shop.domain.VO.CategoryVO;

public interface CategoryService {
	List<CategoryVO> find();
	Category get(Serializable id);
	void insertByPid(Category map);
	void updateCname(Map map);
	void deleteByIdAll(String cid);
}
