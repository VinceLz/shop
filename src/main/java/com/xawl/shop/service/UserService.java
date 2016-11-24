package com.xawl.shop.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.xawl.shop.domain.User;
import com.xawl.shop.domain.VO.UserVO;
import com.xawl.shop.pagination.Page;

public interface UserService {
	public User get(Serializable id); // 只查询一个，常用于修改

	public void update(User entity); // 修改，用实体作为参数

	public void deleteById(Serializable id); // 按id删除，删除一条；支持整数型和字符串类型ID

	public void delete(Serializable[] ids); // 批量删除；支持整数型和字符串类型ID

	User getUser(Map user);

	UserVO getUser2Business(Serializable id);

	List<User> find();

	List<User> findPage(Page page);

	void updateStatus(Map map);

	void updateLast(Map map);

	void updateImage(Map map);

	void updateEncode(Map map);

	void insertregist(User entity);

	void updateonli(Map map);

	public void updateBid(User user);

	public void updateInfo(User user);

	public User getUserByUlogin(Serializable ulogin);

}
