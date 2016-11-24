package com.xawl.shop.service;

import java.io.Serializable;

import com.xawl.shop.domain.SMS;

public interface SMSSerice {
	void insert(SMS sms);
	SMS getSMS(Serializable phone);
	void deleteById(int id);
	void deletedrop();
}
