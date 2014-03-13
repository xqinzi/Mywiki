package com.yxpai.webapps.jifen.service;

import com.yxpai.webapps.jifen.dao.UserDao;
import com.yxpai.webapps.jifen.model.User;


public interface UserService extends GenericManager<User, String> {
    void setUserDao(UserDao userDao);
}
