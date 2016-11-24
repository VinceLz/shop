package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;

import com.xawl.shop.domain.Message;

public interface MessageService {

	List<Message> get(Serializable uid);

	void insertUserMessage(Message message);

	Message updateAndgetByMid(String mid);

}
