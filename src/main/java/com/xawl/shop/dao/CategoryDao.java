package com.xawl.shop.dao;

import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Category;
import com.xawl.shop.domain.VO.CategoryVO;

public interface CategoryDao extends BaseDao<Category>{
	List<CategoryVO> findmenu();

	void insertByPid(Category map);
	void updateCname(Map map);
	void deleteByIdAll(String cid);
	List<Category> getChile(String cid);
}
