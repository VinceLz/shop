package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.xawl.shop.dao.CategoryDao;
import com.xawl.shop.domain.Category;
import com.xawl.shop.domain.VO.CategoryVO;
import com.xawl.shop.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Resource
	private CategoryDao categoryDao;

	@Override
	public List<CategoryVO> find() {
		return categoryDao.findmenu();
	}

	@Override
	public Category get(Serializable id) {
		return categoryDao.get(id);
	}

	

	@Override
	public void updateCname(Map cateogry) {
		categoryDao.updateCname(cateogry);
	}

	


	@Override
	public void insertByPid(Category map) {
		categoryDao.insertByPid(map);
	}

	@Override
	public void deleteByIdAll(String cid) {
		categoryDao.deleteByIdAll(cid);
	}

}
