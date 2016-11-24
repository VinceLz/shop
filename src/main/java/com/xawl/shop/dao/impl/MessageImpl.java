package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.MessageDao;
import com.xawl.shop.domain.Message;

@Repository
public class MessageImpl extends BaseDaoImpl<Message> implements MessageDao {

	public MessageImpl() {
		super.setNs("com.xawl.shop.domain.MessageMapper");
	}

	@Override
	public List<Message> getById(Serializable uid) {
		return getSqlSession().selectList(getNs() + ".getById", uid);
	}

	@Override
	public void addUserMessage(Message message) {
		getSqlSession().selectList(getNs() + ".addUserMessage", message);
	}

	@Override
	public void addmore(Message message) {
		getSqlSession().insert(getNs() + ".addmore", message);
	}

	@Override
	public Message getByMid(String mid) {
		return getSqlSession().selectOne(getNs() + ".getByMid", mid);
	}

	@Override
	public void updateRead(Serializable id) {
		getSqlSession().update(getNs() + ".updateRead", id);
	}

}
