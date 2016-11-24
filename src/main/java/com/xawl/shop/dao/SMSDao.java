package com.xawl.shop.dao;

import java.io.Serializable;

import com.xawl.shop.domain.SMS;

public interface SMSDao extends BaseDao<SMS> {
	SMS getSMS(Serializable phone);

	void deletedrop();
}
