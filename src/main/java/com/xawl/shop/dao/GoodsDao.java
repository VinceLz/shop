package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.VO.GoodsVO;
import com.xawl.shop.pagination.Page;

public interface GoodsDao extends BaseDao<Goods> {
	void updateStatus(Map goods);

	void updateCname(Map map);

	GoodsVO get2comment(String gid);

	void updateLoad(Map map);

	void upload(Map map);

	List<Goods> findPage2(Page<Goods> page);

	void saleAdd(String gid);

	List<Goods> getBusinessGoods(Serializable bid);

	String getUidByGid(Serializable gid);
}
