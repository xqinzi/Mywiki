package com.yxpai.webapps.jifen.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.yxpai.webapps.jifen.dao.UserDao;
import com.yxpai.webapps.jifen.model.User;

@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, String> implements UserDao {

    /**
     * Constructor that sets the entity to User.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }
}
