package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.VO.GoodsVO;
import com.xawl.shop.pagination.Page;

public interface GoodsService {
	List<Goods> findPage(Page page);

	void insert(Goods goods);

	Goods get(String gid);

	void update(Goods goods);

	void updateCname(Map map);

	GoodsVO get2comment(String gid);

	void updateLoad(Map map);

	void updateGimage(Map map);

	List<Goods> findPage2(Page<Goods> page);

	List<Goods> getBusinessGoods(Serializable bid);

	void updateStatus(Map goods, String msg);

	String getUidByGid(Serializable gid);

	void deleteAll(String[] gid, String msg);
}
