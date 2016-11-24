package com.xawl.shop.dao;

import java.util.Map;

import com.xawl.shop.domain.Admin;

public interface AdminDao extends BaseDao<Admin>{
	Admin getAdmin(Map map);

}
