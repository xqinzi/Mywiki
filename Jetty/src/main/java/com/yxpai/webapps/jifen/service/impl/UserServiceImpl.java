package com.yxpai.webapps.jifen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yxpai.webapps.jifen.dao.UserDao;
import com.yxpai.webapps.jifen.model.User;
import com.yxpai.webapps.jifen.service.UserService;


@Service("userService")
public class UserServiceImpl extends GenericManagerImpl<User, String> implements UserService{
    @SuppressWarnings("unused")
	private UserDao userDao;

    @Autowired
    public void setUserDao(final UserDao userDao) {
        this.dao = userDao;
        this.userDao = userDao;
    }
}
