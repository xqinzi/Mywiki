package com.mywiki.dao.hibernate;

import org.springframework.stereotype.Repository;

import com.mywiki.dao.UserDao;
import com.mywiki.model.User;

/**
 * This class interacts with Hibernate session to save/delete and
 * retrieve OrderRecord objects.
*/
@Repository("userDao")
public class UserDaoHibernate extends GenericDaoHibernate<User, Long> implements UserDao {

    /**
     * Constructor that sets the entity to OrderRecord.class.
     */
    public UserDaoHibernate() {
        super(User.class);
    }
}
