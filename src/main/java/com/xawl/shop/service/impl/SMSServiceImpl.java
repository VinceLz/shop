package com.xawl.shop.service.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.SMSDao;
import com.xawl.shop.domain.SMS;
import com.xawl.shop.service.SMSSerice;

@Service
public class SMSServiceImpl implements SMSSerice {

	@Resource
	private SMSDao smsDao;

	@Override
	public void insert(SMS sms) {
		smsDao.insert(sms);
	}

	@Override
	public SMS getSMS(Serializable phone) {
		return smsDao.getSMS(phone);
	}

	@Override
	public void deleteById(int id) {
		smsDao.deleteById(id);
	}

	@Override
	public void deletedrop() {
		smsDao.deletedrop();
	}

}
