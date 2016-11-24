package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Business;
import com.xawl.shop.pagination.Page;

public interface BusinessService {
	List<Business> findPage(Page page);
	Business get(Serializable bid);
	void insert(Business business);
	Business getBusinessByKey(String bkey);
	String getPhoneByBid(Serializable id);
	Integer getUidByBid(String bid);
	void updateGimage(Map map);
	int getcode(Serializable bid);
	void updateStatus(Map map, String msg);
	void updateInfo(Business business);
	
}
