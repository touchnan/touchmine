/*
 * cn.wanto.busi.db.impl.AsynDao.java
 * Sep 11, 2012 
 */
package cn.wanto.busi.db.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touchin.db.hibernate.HibernateDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.page.Orderby;
import cn.touchin.page.Page;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;
import cn.wanto.busi.db.IAsynDao;

/**
 * Sep 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class AsynDao extends HibernateDao<Object, Long> implements IAsynDao {

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#save(cn.touchin.dto.AppRequest, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public <X> X save(AppRequest appRequest, X object) {
        return super.save(appRequest, object);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#batchExecute(cn.touchin.dto.AppRequest, java.lang.String,
     * java.util.Map)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int batchExecute(AppRequest appRequest, String hql, Map<String, ?> values) {
        return super.batchExecute(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#batchExecute(cn.touchin.dto.AppRequest, java.lang.String,
     * java.lang.Object[])
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int batchExecute(AppRequest appRequest, String hql, Object... values) {
        return super.batchExecute(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#createCriteria(java.lang.Class, org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public Criteria createCriteria(Class<?> classOfE, Criterion... criterions) {
        return super.createCriteria(classOfE, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#createCriteria(java.lang.Class, cn.touchin.page.Orderby,
     * org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public Criteria createCriteria(Class<?> classOfE, Orderby orderby, Criterion[] criterions) {
        return super.createCriteria(classOfE, orderby, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#createQuery(java.lang.String, java.util.Map)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Query createQuery(String hql, Map<String, ?> values) {
        return super.createQuery(hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#createQuery(java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Query createQuery(String hql, Object... values) {
        return super.createQuery(hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#currSession()
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Session currSession() {
        return super.currSession();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#delete(cn.touchin.dto.AppRequest, java.lang.Class,
     * java.io.Serializable)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(AppRequest appRequest, Class<? extends Object> classOfX, Serializable id) {
        super.delete(appRequest, classOfX, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#delete(cn.touchin.dto.AppRequest, java.io.Serializable)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(AppRequest appRequest, Long id) {
        super.delete(appRequest, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#delete(cn.touchin.dto.AppRequest, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(AppRequest appRequest, Object entity) {
        super.delete(appRequest, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#deleteAll(cn.touchin.dto.AppRequest)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAll(AppRequest appRequest) {
        super.deleteAll(appRequest);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#distinct(org.hibernate.Criteria)
     */
    @Override
    @Transactional(readOnly = true)
    public Criteria distinct(Criteria criteria) {
        return super.distinct(criteria);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#distinct(org.hibernate.Query)
     */
    @Override
    @Transactional(readOnly = true)
    public Query distinct(Query query) {
        return super.distinct(query);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, boolean,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public Object fetch(AppRequest appRequest, boolean distinct, PropertyFilter... filters) {
        return super.fetch(appRequest, distinct, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.Class,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> X fetch(AppRequest appRequest, Class<X> classOfX, PropertyFilter... filters) {
        return super.fetch(appRequest, classOfX, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.Class, java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> X fetch(AppRequest appRequest, Class<X> classOfX, Serializable id) {
        return super.fetch(appRequest, classOfX, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, org.hibernate.Criteria)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> X fetch(AppRequest appRequest, Criteria c) {
        return super.fetch(appRequest, c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public Object fetch(AppRequest appRequest, Criterion... criterions) {
        return super.fetch(appRequest, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, java.io.Serializable)
     */
    @Override
    @Transactional(readOnly = true)
    public Object fetch(AppRequest appRequest, Long id) {
        return super.fetch(appRequest, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, cn.touchin.page.Orderby,
     * org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public Object fetch(AppRequest appRequest, Orderby orderby, Criterion... criterions) {
        return super.fetch(appRequest, orderby, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public Object fetch(AppRequest appRequest, PropertyFilter... filters) {
        return super.fetch(appRequest, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.String, java.util.Map)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> X fetch(AppRequest appRequest, String hql, Map<String, ?> values) {
        return super.fetch(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> X fetch(AppRequest appRequest, String hql, Object... values) {
        return super.fetch(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, boolean,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, boolean distinct, PropertyFilter[] filters) {
        return super.find(appRequest, distinct, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, java.lang.Class,
     * cn.touchin.page.Orderby, boolean, cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, boolean distinct,
            PropertyFilter... filters) {
        return super.find(appRequest, classOfX, orderby, distinct, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, java.lang.Class,
     * cn.touchin.page.Orderby, cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, PropertyFilter... filters) {
        return super.find(appRequest, classOfX, orderby, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, org.hibernate.Criteria)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> find(AppRequest appRequest, Criteria c) {
        return super.find(appRequest, c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, Criterion... criterions) {
        return super.find(appRequest, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, cn.touchin.page.Orderby, boolean,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, Orderby orderby, boolean distinct, PropertyFilter[] filters) {
        return super.find(appRequest, orderby, distinct, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, cn.touchin.page.Orderby,
     * org.hibernate.criterion.Criterion[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, Orderby orderby, Criterion... criterions) {
        return super.find(appRequest, orderby, criterions);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, cn.touchin.page.Orderby,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, Orderby orderby, PropertyFilter[] filters) {
        return super.find(appRequest, orderby, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, cn.touchin.page.PropertyFilter[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> find(AppRequest appRequest, PropertyFilter[] filters) {
        return super.find(appRequest, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, java.lang.String, java.util.Map)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> find(AppRequest appRequest, String hql, Map<String, ?> values) {
        return super.find(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#find(cn.touchin.dto.AppRequest, java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> find(AppRequest appRequest, String hql, Object... values) {
        return super.find(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findAll(cn.touchin.dto.AppRequest, java.lang.Class,
     * cn.touchin.page.Orderby)
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> findAll(AppRequest appRequest, Class<X> classOfX, Orderby orderby) {
        return super.findAll(appRequest, classOfX, orderby);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findAll(cn.touchin.dto.AppRequest, cn.touchin.page.Orderby)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> findAll(AppRequest appRequest, Orderby orderby) {
        return super.findAll(appRequest, orderby);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findAll(cn.touchin.dto.AppRequest)
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> findAll(AppRequest appRequest) {
        return super.findAll(appRequest);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findByIds(cn.touchin.dto.AppRequest, java.lang.Class,
     * java.io.Serializable[])
     */
    @Override
    @Transactional(readOnly = true)
    public <X> List<X> findByIds(AppRequest appRequest, Class<X> classOfX, Serializable[] ids) {
        return super.findByIds(appRequest, classOfX, ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findByIds(cn.touchin.dto.AppRequest, PK[])
     */
    @Override
    @Transactional(readOnly = true)
    public List<Object> findByIds(AppRequest appRequest, Long[] ids) {
        return super.findByIds(appRequest, ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findPage(cn.touchin.dto.AppRequest, cn.touchin.page.Page,
     * org.hibernate.Criteria)
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination findPage(AppRequest appRequest, Page page, Criteria c) {
        return super.findPage(appRequest, page, c);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findPage(cn.touchin.dto.AppRequest, cn.touchin.page.Page,
     * java.lang.String, java.util.Map)
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination findPage(AppRequest appRequest, Page page, String hql, Map<String, ?> values) {
        return super.findPage(appRequest, page, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findPage(cn.touchin.dto.AppRequest, cn.touchin.page.Page,
     * java.lang.String, java.lang.Object[])
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination findPage(AppRequest appRequest, Page page, String hql, Object... values) {
        return super.findPage(appRequest, page, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#findPage(cn.touchin.dto.AppRequest, cn.touchin.page.Pagination)
     */
    @Override
    @Transactional(readOnly = true)
    public Pagination findPage(AppRequest appRequest, Pagination page) {
        return super.findPage(appRequest, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#insert(cn.touchin.dto.AppRequest, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object insert(AppRequest appRequest, Object entity) {
        return super.insert(appRequest, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#isPropertyUnique(cn.touchin.dto.AppRequest, java.lang.String,
     * java.lang.Object, java.lang.Object)
     */
    @Override
    @Transactional(readOnly = true)
    public boolean isPropertyUnique(AppRequest appRequest, String propertyName, Object newValue, Object oldValue) {
        return super.isPropertyUnique(appRequest, propertyName, newValue, oldValue);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#queryForInt(cn.touchin.dto.AppRequest, java.lang.String, java.util.Map)
     */
    @Override
    @Transactional(readOnly = true)
    public Integer queryForInt(AppRequest appRequest, String hql, Map<String, ?> values) {
        return super.queryForInt(appRequest, hql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#queryForInt(cn.touchin.dto.AppRequest, java.lang.String,
     * java.lang.Object[])
     */
    @Override
    @Transactional(readOnly = true)
    public Integer queryForInt(AppRequest appRequest, String sql, Object[] args) {
        return super.queryForInt(appRequest, sql, args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#queryForLong(cn.touchin.dto.AppRequest, java.lang.String,
     * java.util.Map)
     */
    @Override
    @Transactional(readOnly = true)
    public Long queryForLong(AppRequest appRequest, String sql, Map<String, ?> values) {
        return super.queryForLong(appRequest, sql, values);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#queryForLong(cn.touchin.dto.AppRequest, java.lang.String,
     * java.lang.Object[])
     */
    @Override
    @Transactional(readOnly = true)
    public Long queryForLong(AppRequest appRequest, String sql, Object[] args) {
        return super.queryForLong(appRequest, sql, args);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#saveOrUpdate(cn.touchin.dto.AppRequest, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object saveOrUpdate(AppRequest appRequest, Object entity) {
        return super.saveOrUpdate(appRequest, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.HibernateDao#update(cn.touchin.dto.AppRequest, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Object update(AppRequest appRequest, Object entity) {
        return super.update(appRequest, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.BaseDao#deleteEntities(cn.touchin.dto.AppRequest, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEntities(AppRequest appRequest, List<Object> entities) {
        super.deleteEntities(appRequest, entities);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.BaseDao#deletes(cn.touchin.dto.AppRequest, PK[])
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletes(AppRequest appRequest, Long[] ids) {
        super.deletes(appRequest, ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.BaseDao#insertEntities(cn.touchin.dto.AppRequest, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertEntities(AppRequest appRequest, List<Object> entities) {
        super.insertEntities(appRequest, entities);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.BaseDao#updates(cn.touchin.dto.AppRequest, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Object> updates(AppRequest appRequest, List<Object> entities) {
        return super.updates(appRequest, entities);
    }

}
