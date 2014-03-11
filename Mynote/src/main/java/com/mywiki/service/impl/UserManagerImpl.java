package com.mywiki.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mywiki.dao.UserDao;
import com.mywiki.model.User;
import com.mywiki.service.UserManager;

@Service("userManager")
public class UserManagerImpl extends GenericManagerImpl<User, Long>
		implements UserManager {
	
	private UserDao userDao;

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.dao = userDao;
		this.userDao = userDao;
	}
}
