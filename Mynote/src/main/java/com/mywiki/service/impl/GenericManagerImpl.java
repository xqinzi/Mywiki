package com.mywiki.service.impl;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.googlecode.ehcache.annotations.Cacheable;
import com.googlecode.ehcache.annotations.TriggersRemove;
import com.mywiki.dao.GenericDao;
import com.mywiki.service.GenericManager;


public class GenericManagerImpl<T, PK extends Serializable> implements
	GenericManager<T, PK> {
    /**
     * Log variable for all child classes. Uses LogFactory.getLog(getClass())
     * from Commons Logging
     */
    protected final Log log = LogFactory.getLog(getClass());

    /**
     * GenericDao instance, set by constructor of child classes
     */
    protected GenericDao<T, PK> dao;

    public GenericManagerImpl() {
    }

    public GenericManagerImpl(GenericDao<T, PK> genericDao) {
	this.dao = genericDao;
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheName="userCache")//缓存数据
    public List<T> getAll() {
	return dao.getAll();
    }

    /**
     * {@inheritDoc}
     */
    @Cacheable(cacheName="userCache")//缓存数据
    public T get(PK id) {
	return dao.get(id);
    }

    /**
     * {@inheritDoc}
     */
    public boolean exists(PK id) {
	return dao.exists(id);
    }

    /**
     * {@inheritDoc}
     */
    @TriggersRemove(cacheName="userCache",removeAll=true)//清除缓存
    public T save(T object) {
	return dao.save(object);
    }

    /**
     * {@inheritDoc}
     */
    @TriggersRemove(cacheName="userCache",removeAll=true)//清除缓存
    public void remove(T object) {
	dao.remove(object);
    }

    /**
     * {@inheritDoc}
     */
    @TriggersRemove(cacheName="userCache",removeAll=true)//清除缓存
    public void remove(PK id) {
	dao.remove(id);
    }

    /**
     * {@inheritDoc}
     */
    public int getCount(String hql) {
	return dao.getCount(hql);
    }

    /**
     * {@inheritDoc}
     */
    public List<T> findByPage(String hql, int offset, int length) {
	return dao.findByPage(hql, offset, length);
    }
}
