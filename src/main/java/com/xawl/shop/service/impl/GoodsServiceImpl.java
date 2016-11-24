package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.controller.MessageController;
import com.xawl.shop.dao.GoodsDao;
import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.Message;
import com.xawl.shop.domain.VO.GoodsVO;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.GoodsService;
import com.xawl.shop.service.MessageService;
import com.xawl.shop.util.MessageUtil;
import com.xawl.shop.util.PropertiesUtil;
import com.xawl.shop.util.PushMessageUtil;

@Service
public class GoodsServiceImpl implements GoodsService {

	@Resource
	private GoodsDao goodsDao;

	@Resource
	private MessageService messageService;

	@Override
	public List<Goods> findPage(Page page) {
		return goodsDao.findPage(page);
	}

	@Override
	public void updateStatus(Map goods, String msg) {
		if (msg != null) {
			goodsDao.updateStatus(goods);
			Goods goods2 = get(goods.get("gid") + "");
			Message message = MessageUtil.createMessage();
			int uid = Integer.parseInt(getUidByGid(goods2.getGid()));
			message.setUid(uid);
			message.setMessage_id(UUID.randomUUID().toString());
			message.setGid(goods2.getGid() + "");
			message.setGname(goods2.getGname());
			message.setType(MessageController.MESSAGE_SEND_BUSINESS);
			message.setMessage(msg);
			messageService.insertUserMessage(message);
			PushMessageUtil.send(uid + "", false, null);
		} else {
			goodsDao.updateStatus(goods);
		}
	}

	@Override
	public void insert(Goods goods) {
		goodsDao.insert(goods);
	}

	@Override
	public void deleteAll(String[] gid, String msg) {
		if (msg != null) {
			Goods goods = null;
			for (int i = 0; i < gid.length; i++) {
				goods = get(gid[i]);
				Message message = MessageUtil.createMessage();
				int uid = Integer.parseInt(getUidByGid(goods.getGid()));
				message.setUid(uid);
				message.setMessage(msg);
				message.setMessage_id(UUID.randomUUID().toString());
				message.setGid(gid[i]);
				message.setGname(goods.getGname());
				message.setType(MessageController.MESSAGE_SEND_BUSINESS);
				messageService.insertUserMessage(message);
				goodsDao.deleteById(gid);
				PushMessageUtil.send(uid + "", false, null);
			}
		} else {
			goodsDao.delete(gid);
		}

	}

	@Override
	public Goods get(String gid) {

		return goodsDao.get(gid);
	}

	@Override
	public void update(Goods goods) {
		goodsDao.update(goods);
	}

	@Override
	public void updateCname(Map map) {
		goodsDao.updateCname(map);
	}

	@Override
	public GoodsVO get2comment(String gid) {
		return goodsDao.get2comment(gid);
	}

	@Override
	public void updateLoad(Map map) {
		goodsDao.updateLoad(map);
	}

	@Override
	public void updateGimage(Map map) {
		goodsDao.upload(map);
	}

	@Override
	public List<Goods> findPage2(Page<Goods> page) {
		return goodsDao.findPage2(page);
	}

	@Override
	public List<Goods> getBusinessGoods(Serializable bid) {
		return goodsDao.getBusinessGoods(bid);
	}

	@Override
	public String getUidByGid(Serializable gid) {
		return goodsDao.getUidByGid(gid);
	}

}
