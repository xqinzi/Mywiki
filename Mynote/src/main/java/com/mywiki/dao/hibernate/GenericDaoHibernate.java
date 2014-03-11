package com.mywiki.dao.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.HibernateException;
import org.hibernate.IdentifierLoadAccess;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.transaction.annotation.Transactional;

import com.mywiki.dao.GenericDao;



/**
 * This class serves as the Base class for all other DAOs - namely to hold
 * common CRUD methods that they might all use. You should only need to extend
 * this class when your require custom CRUD logic.
 * <p/>
 * <p>To register this class in your Spring context file, use the following XML.
 * <pre>
 *      &lt;bean id="fooDao" class="com.yxpai.user.dao.hibernate.GenericDaoHibernate"&gt;
 *          &lt;constructor-arg value="com.yxpai.user.model.Foo"/&gt;
 *      &lt;/bean&gt;
 * </pre>
 * @param <T> a type variable
 * @param <PK> the primary key for that type
 */
@Transactional("transactionManager")
public class GenericDaoHibernate<T, PK extends Serializable> implements GenericDao<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass()) from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());
    private Class<T> persistentClass;
    
    @Resource
    @Qualifier("sessionFactory")
    private SessionFactory sessionFactory;

    /**
     * Constructor that takes in a class to see which type of entity to persist.
     * Use this constructor when subclassing.
     *
     * @param persistentClass the class type you'd like to persist
     */
    public GenericDaoHibernate(final Class<T> persistentClass) {
        this.persistentClass = persistentClass;
    }

    /**
     * Constructor that takes in a class and sessionFactory for easy creation of DAO.
     *
     * @param persistentClass the class type you'd like to persist
     * @param sessionFactory  the pre-configured Hibernate SessionFactory
     */
    public GenericDaoHibernate(final Class<T> persistentClass, SessionFactory sessionFactory) {
        this.persistentClass = persistentClass;
        this.sessionFactory = sessionFactory;
    }

    public SessionFactory getSessionFactory() {
        return this.sessionFactory;
    }

    public Session getSession() throws HibernateException {
        Session sess = getSessionFactory().getCurrentSession();
        if (sess == null) {
            sess = getSessionFactory().openSession();
        }
        return sess;
    }

    @Autowired
    @Required
    @Qualifier("sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> getAll() {
        Session sess = getSession();
        return sess.createCriteria(persistentClass).list();
    }

    /**
     * {@inheritDoc}
     */
    public List<T> getAllDistinct() {
        Collection<T> result = new LinkedHashSet<T>(getAll());
        return new ArrayList<T>(result);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T get(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);

        if (entity == null) {
            log.warn("Uh oh, '" + this.persistentClass + "' object with id '" + id + "' not found...");
            throw new ObjectRetrievalFailureException(this.persistentClass, id);
        }

        return entity;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public boolean exists(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        return entity != null;
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public T save(T object) {
        Session sess = getSession();
        return (T) sess.merge(object);
    }

    /**
     * {@inheritDoc}
     */
    public void remove(T object) {
        Session sess = getSession();
        sess.delete(object);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
	public void remove(PK id) {
        Session sess = getSession();
        IdentifierLoadAccess byId = sess.byId(persistentClass);
        T entity = (T) byId.load(id);
        sess.delete(entity);
    }

    /**
     * {@inheritDoc}
     */
    @SuppressWarnings("unchecked")
    public List<T> findByNamedQuery(String queryName, Map<String, Object> queryParams) {
        Session sess = getSession();
        Query namedQuery = sess.getNamedQuery(queryName);

        for (String s : queryParams.keySet()) {
            namedQuery.setParameter(s, queryParams.get(s));
        }
        return namedQuery.list();
    }
    /**
     * {@inheritDoc}
     */
	public int getCount(String hql) {
		Session sess = getSession();
		Query q = sess.createQuery(hql);
		return Integer.parseInt(q.list().get(0).toString());
	}
	
	/**
     * {@inheritDoc}
     */
	@SuppressWarnings("unchecked")
	public List<T> findByPage(String hql,int offset, int length) {
		Query qry = getSession().createQuery(hql).setFirstResult(offset).setMaxResults(length);
    	return qry.list();
	}
}
