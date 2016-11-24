package com.xawl.shop.util;

import com.xawl.shop.domain.Message;

public class MessageUtil {

	public static Message createMessage() {
		Message message = new Message();
		message.setPdate(DateUtil.getSqlDate());
		message.setSend_name(keyUtil.ADMIN_NAME);
		message.setStatus(keyUtil.NO_READ);
		return message;
	}

}
