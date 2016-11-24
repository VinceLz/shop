package com.xawl.shop.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.xawl.shop.dao.UserDao;
import com.xawl.shop.domain.User;
import com.xawl.shop.domain.VO.UserVO;
import com.xawl.shop.pagination.Page;
import com.xawl.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserDao userDao;

	@Override
	public User get(Serializable id) {
		return userDao.get(id);
	}

	@Override
	public void insertregist(User entity) {
		userDao.regist(entity);
	}

	@Override
	public void update(User entity) {
		userDao.update(entity);
	}

	@Override
	public void deleteById(Serializable id) {

	}

	@Override
	public void delete(Serializable[] ids) {

	}

	@Override
	public User getUser(Map user) {
		return userDao.getUser(user);
	}

	@Override
	public UserVO getUser2Business(Serializable id) {
		return userDao.getUser2Business(id);
	}

	@Override
	public List<User> find() {

		return userDao.find(null);
	}

	@Override
	public List<User> findPage(Page page) {
		return userDao.findPage(page);
	}

	@Override
	public void updateStatus(Map u) {
		userDao.updateStatus(u);
	}

	@Override
	public void updateLast(Map map) {
		userDao.updateLast(map);
	}

	@Override
	public void updateImage(Map map) {
		userDao.updateImage(map);
	}

	@Override
	public void updateEncode(Map map) {
		userDao.updateEncode(map);
	}

	@Override
	public void updateonli(Map map) {
		userDao.updateonli(map);
	}

	@Override
	public void updateBid(User user) {
		userDao.updateBid(user);
	}

	@Override
	public void updateInfo(User user) {
		userDao.updateInfo(user);
	}

	@Override
	public User getUserByUlogin(Serializable ulogin) {
		return userDao.getUserByUlogin(ulogin);
	}

	

}
