package com.xawl.shop.dao.impl;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.SMSDao;
import com.xawl.shop.domain.SMS;

@Repository
public class SMSDaoImpl extends BaseDaoImpl<SMS> implements SMSDao {

	public SMSDaoImpl() {
		setNs("com.xawl.shop.SMSMapper");
	}

	@Override
	public SMS getSMS(Serializable phone) {
		return getSqlSession().selectOne(getNs() + ".getSMS", phone);
	}

	@Override
	public void deletedrop() {
		getSqlSession().delete(getNs() + ".deleteAll");
	}

}
