package com.xawl.shop.dao.impl;

import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.AdminDao;
import com.xawl.shop.domain.Admin;

@Repository
public class AdminDaoImpl extends BaseDaoImpl<Admin> implements AdminDao {

	public AdminDaoImpl() {
		super.setNs("com.xawl.shop.AdminMapper");
	}

	@Override
	public Admin getAdmin(Map map) {
		return this.getSqlSession().selectOne(this.getNs() + ".getAdmin", map);
	}
}
