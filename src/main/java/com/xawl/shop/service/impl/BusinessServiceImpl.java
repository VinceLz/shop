package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.controller.BusinessController;
import com.xawl.shop.controller.MessageController;
import com.xawl.shop.dao.BusinessDao;
import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.Message;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.BusinessService;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.util.MessageUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.PushMessageUtil;
import com.xawl.shop.util.Util;

@Service
public class BusinessServiceImpl implements BusinessService {

	@Resource
	private BusinessDao businessDao;

	@Resource
	private MessageService messageService;

	@Override
	public List<Business> findPage(Page page) {

		return businessDao.findPage(page);
	}

	@Override
	public Business get(Serializable bid) {
		return businessDao.get(bid);
	}

	@Override
	public void updateStatus(Map map, String msg) {
		if (msg != null) {
			businessDao.updateStatus(map);
			Integer uid = getUidByBid(map.get("bid") + "");
			Message message = MessageUtil.createMessage();
			message.setUid(uid);
			message.setMessage_id(UUID.randomUUID().toString());
			message.setType(MessageController.MESSAGE_SEND_USER);
			message.setMessage(msg);
			messageService.insertUserMessage(message);
			String status = map.get("status").toString();

			switch (status) {
			case "-1":
				PushMessageUtil.send(uid + "", true, "-1"); // 审核失败
				break;

			case "0":
				PushMessageUtil.send(uid + "", true, "0"); // 待审核
				break;
			case "1":
				PushMessageUtil.send(uid + "", true, "1"); // 禁用
				break;
			case "2":
				PushMessageUtil.send(uid + "", true, "2"); // 正常
				break;
			}

		} else {
			businessDao.updateStatus(map);
		}
	}

	@Override
	public void insert(Business business) {
		businessDao.insert(business);
	}

	@Override
	public Business getBusinessByKey(String bkey) {
		return businessDao.getBusinessByKey(bkey);
	}

	@Override
	public String getPhoneByBid(Serializable id) {
		return businessDao.getPhoneByBid(id);
	}

	@Override
	public Integer getUidByBid(String bid) {
		return businessDao.getUidByBid(bid);
	}

	@Override
	public void updateGimage(Map map) {
		businessDao.updateGimage(map);
	}

	@Override
	public int getcode(Serializable bid) {
		return businessDao.getcode(bid);
	}

	@Override
	public void updateInfo(Business business) {
		businessDao.updateInfo(business);
	}

}
