package com.xawl.shop.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.AdminDao;
import com.xawl.shop.domain.Admin;
import com.xawl.shop.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService {

	@Resource
	private AdminDao adminDao;

	@Override
	public Admin getAdmin(String usernmae, String password) {
		Map map = new HashMap<String, String>();
		map.put("ausername", usernmae);
		map.put("apassword", password);
		return adminDao.getAdmin(map);
	}

}
