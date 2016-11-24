package com.xawl.shop.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.CategoryDao;
import com.xawl.shop.domain.Category;
import com.xawl.shop.domain.VO.CategoryVO;

@Repository
public class CategoryDaoImpl extends BaseDaoImpl<Category> implements
		CategoryDao {
	public CategoryDaoImpl() {
		super.setNs("com.xawl.shop.CategoryMapper");
	}

	@Override
	public List<CategoryVO> findmenu() {

		return this.getSqlSession().selectList(this.getNs() + ".findmenu");
	}

	@Override
	public void insertByPid(Category map) {
		this.getSqlSession().insert(this.getNs() + ".insertByPid", map);
	}

	@Override
	public void updateCname(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateCname", map);
	}

	@Override
	public void deleteByIdAll(String cid) {
		// 这里实现多级删除 使用递归算法实现多级删除 cid
		List<Category> chile = getChile(cid);// 获得孩子
		deleteById(cid);
		for (Category c : chile) {
			deleteById(c.getCid());
			deleteByIdAll(c.getCid());
		}
	}

	@Override
	public List<Category> getChile(String cid) {
		return this.getSqlSession().selectList(this.getNs() + ".getChile", cid);
	}

}
