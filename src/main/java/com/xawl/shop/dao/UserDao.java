package com.xawl.shop.dao;

import java.io.Serializable;
import java.util.Map;

import com.xawl.shop.domain.Business;
import com.xawl.shop.domain.User;
import com.xawl.shop.domain.VO.UserVO;

public interface UserDao extends BaseDao<User> {

	User getUser(Map user);

	UserVO getUser2Business(Serializable id);

	void updateStatus(Map u);

	void updateLast(Map s);

	void updateImage(Map map);

	void updateonli(Map map);

	void updateEncode(Map map);

	void regist(User user);

	void updateBid(User user);

	void updateInfo(User user);

	User getUserByUlogin(Serializable ulogin);

}
