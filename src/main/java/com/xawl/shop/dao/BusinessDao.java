package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.Map;

import com.xawl.shop.domain.Business;

public interface BusinessDao extends BaseDao<Business> {
	void updateStatus(Map b);

	Business getBusinessByKey(String bkey);

	String getPhoneByBid(Serializable bid);

	Integer getUidByBid(String bid);

	void updateGimage(Map map);

	int getcode(Serializable bid);

	void updateInfo(Business business);
}
