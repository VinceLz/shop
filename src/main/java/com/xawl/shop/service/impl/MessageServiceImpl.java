package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.annotation.Resources;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.MessageDao;
import com.xawl.shop.domain.Message;
import com.xawl.shop.service.MessageService;

@Service
public class MessageServiceImpl implements MessageService {

	@Resource
	private MessageDao messageDao;

	@Override
	public List<Message> get(Serializable uid) {
		return messageDao.getById(uid);
	}

	@Override
	public void insertUserMessage(Message message) {
		//
		messageDao.addmore(message);// 附表添加
		messageDao.addUserMessage(message); // 主表添加
	}

	

	@Override
	public Message updateAndgetByMid(String mid) {
		messageDao.updateRead(mid);
		return messageDao.getByMid(mid);
	}

}
