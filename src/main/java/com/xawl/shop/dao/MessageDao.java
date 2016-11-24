package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.List;

import com.xawl.shop.domain.Message;

public interface MessageDao extends BaseDao<Message> {
	List<Message> getById(Serializable uid);

	void addmore(Message message);

	void addUserMessage(Message message);

	


	Message getByMid(String mid);

	void updateRead(Serializable id);
}
