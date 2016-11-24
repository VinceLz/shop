package com.xawl.shop.dao.impl;

import java.io.Serializable;
import java.util.Map;

import org.junit.Test;
import org.springframework.stereotype.Repository;

import com.xawl.shop.dao.UserDao;
import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.User;
import com.xawl.shop.domain.VO.UserVO;

@Repository
public class UserDaoImpl extends BaseDaoImpl<User> implements UserDao {

	public UserDaoImpl() {
		super.setNs("com.xawl.shop.UserMapper");
	}

	@Override
	public User getUser(Map user) {

		User result = super.getSqlSession().selectOne(
				this.getNs() + ".getUser", user);
		System.out.println("dao " + result);
		return result;
	}

	@Override
	public UserVO getUser2Business(Serializable id) {
		UserVO userVO = this.getSqlSession().selectOne(
				this.getNs() + ".getUserToBusiness", id);
		return userVO;
	}

	@Override
	public void updateStatus(Map u) {
		this.getSqlSession().update(this.getNs() + ".updateStatus", u);
	}

	@Override
	public void updateBid(User u) {
		this.getSqlSession().update(this.getNs() + ".updateBid", u);
	}

	@Override
	public void updateLast(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateLast", map);
	}

	@Override
	public void updateImage(Map map) {
		this.getSqlSession().update(this.getNs() + ".updateImage", map);
	}

	@Override
	public void updateEncode(Map map) {
		this.getSqlSession().update(getNs() + ".updateEncode", map);
	}

	@Override
	public void regist(User user) {
		getSqlSession().insert(getNs() + ".regist", user);
	}

	@Override
	public void updateonli(Map map) {
		this.getSqlSession().update(getNs() + ".updateonli", map);
	}

	@Override
	public void updateInfo(User user) {

		this.getSqlSession().update(getNs() + ".updateInfo", user);
	}

	@Override
	public User getUserByUlogin(Serializable ulogin) {
		return getSqlSession().selectOne(getNs() + ".getUserByUlogin", ulogin);
	}

}
