package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.GoodsDao;
import com.xawl.shop.domain.Goods;
import com.xawl.shop.domain.VO.GoodsVO;
import com.xawl.shop.pagination.Page;

@Repository
public class GoodsDaoImpl extends BaseDaoImpl<Goods> implements GoodsDao {

	public GoodsDaoImpl() {
		super.setNs("com.xawl.shop.GoodsMapper");

	}

	@Override
	public void updateStatus(Map goods) {
		this.getSqlSession().update(this.getNs() + ".updateStatus", goods);

	}

	@Override
	public void updateCname(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateCname", map);
	}

	@Override
	public GoodsVO get2comment(String gid) {

		return this.getSqlSession()
				.selectOne(this.getNs() + "get2comment", gid);
	}

	@Override
	public void updateLoad(Map map) {
		this.getSqlSession().update(this.getNs() + ".upload", map);
	}

	@Override
	public void upload(Map map) {
		this.getSqlSession().update(this.getNs() + ".upload", map);
	}

	@Override
	public List<Goods> findPage2(Page<Goods> page) {
		return this.getSqlSession().selectList(this.getNs() + ".findPage2",
				page);
	}

	@Override
	public void saleAdd(String gid) {
		this.getSqlSession().update(this.getNs() + ".saleAdd", gid);
	}

	@Override
	public List<Goods> getBusinessGoods(Serializable bid) {
		return getSqlSession().selectList(getNs() + ".getByBid", bid);
	}

	@Override
	public String getUidByGid(Serializable gid) {
		return getSqlSession().selectOne(getNs() + ".getUidByGid", gid);
	}

}
