package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.BusinessDao;
import com.xawl.shop.domain.Business;

@Repository
public class BusinessDaoImpl extends BaseDaoImpl<Business> implements
		BusinessDao {
	public BusinessDaoImpl() {
		super.setNs("com.xawl.shop.BusinessMapper");
	}

	@Override
	public void updateStatus(Map b) {
		this.getSqlSession().update(this.getNs() + ".updateStatus", b);
	}

	@Override
	public Business getBusinessByKey(String bkey) {
	
		return this.getSqlSession().selectOne(
				this.getNs() + ".getBusinessByKey", bkey);
	}

	@Override
	public String getPhoneByBid(Serializable bid) {
		return getSqlSession().selectOne(getNs() + ".getPhoneByBid", bid);
	}

	@Override
	public Integer getUidByBid(String bid) {
		return getSqlSession().selectOne(getNs() + ".getUidByBid", bid);
	}

	@Override
	public void updateGimage(Map map) {
		getSqlSession().update(getNs() + ".updateGimage", map);
	}

	@Override
	public int getcode(Serializable bid) {
		Object code = getSqlSession().selectOne(getNs() + ".getcode", bid);
		if (code == null) {
			return -2;
		} else {
			return (int) code;
		}
	}

	@Override
	public void updateInfo(Business business) {
		getSqlSession().update(getNs() + ".updateInfo", business);
	}

}
