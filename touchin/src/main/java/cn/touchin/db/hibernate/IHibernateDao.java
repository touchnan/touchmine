/*
 * cn.touchin.db.hibernate.IHibernateDao.java
 * Feb 12, 2012 
 */
package cn.touchin.db.hibernate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.HibernateTemplate;

import cn.touchin.db.IDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.page.Orderby;
import cn.touchin.page.Page;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IHibernateDao<E extends Object, PK extends Serializable> extends IDao<E, PK> {
    
    Session currSession();

    HibernateTemplate getHibernateTemplate();
    
    /**
     * 执行HQL进行批量修改/删除操作.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     *            命名参数,按名称绑定.
     * @return 影响的记录数
     */
    int batchExecute(AppRequest appRequest, final String hql, final Object... values);

    /**
     * 执行HQL进行批量修改/删除操作.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     *            命名参数,按名称绑定.
     * @return 影响的记录数
     */
    int batchExecute(AppRequest appRequest, final String hql, final Map<String, ?> values);

    /**
     * 删除
     * 
     * @param appRequest
     * @param classOfX
     * @param id
     */
    void delete(AppRequest appRequest, Class<? extends Object> classOfX, Serializable id);

    /**
     * 按HQL查询对象列表,查询唯一对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     * @return 对象
     */
    <X> X fetch(AppRequest appRequest, final String hql, final Object... values);

    /**
     * 按HQL查询对象列表,查询唯一对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     *            数量可变的参数,按顺序绑定.
     * @return 对象
     */
    <X> X fetch(AppRequest appRequest, final String hql, final Map<String, ?> values);

    /**
     * 按Criteria查询唯一对象.
     * 
     * @param appRequest
     * @param criterions
     * @return 对象列表
     */
    E fetch(AppRequest appRequest, final Criterion... criterions);

    /**
     * 查询唯一对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     * @param criterions
     * @return 对象
     */
    E fetch(AppRequest appRequest, final Orderby orderby, final Criterion... criterions);

    /**
     * 查询唯一对象.
     * 
     * @param appRequest
     * @param c
     * @return 对象
     */
    <X> X fetch(AppRequest appRequest, final Criteria c);

    /**
     * 匹配一个实体
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param id
     * @return 实体
     */
    <X> X fetch(AppRequest appRequest, Class<X> classOfX, Serializable id);

    /**
     * 匹配一个实体
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param filters
     * @return 实体
     */
    <X> X fetch(AppRequest appRequest, Class<X> classOfX, PropertyFilter... filters);

    /**
     * 根据条件，找到一个匹配的实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param classOfX
     * @param condition
     *            查询条件
     * @return 实体
     */
    // <X> X fetch(AppRequest appRequest, Class<X> classOfX, Condition condition);

    /**
     * 按HQL查询对象列表,查找多个对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     *            数量可变的参数,按顺序绑定.
     * @return 对象列表
     */
    <X> List<X> find(AppRequest appRequest, final String hql, final Object... values);

    /**
     * 按HQL查询对象列表,查找多个对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param hql
     * @param values
     *            命名参数,按名称绑定.
     * @return 对象列表
     */
    <X> List<X> find(AppRequest appRequest, final String hql, final Map<String, ?> values);

    /**
     * 按Criteria查询对象列表.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param criterions
     * @return 对象列表
     */
    List<E> find(AppRequest appRequest, final Criterion... criterions);

    /**
     * 查询多个对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     * @param criterions
     * @return 对象列表
     */
    List<E> find(AppRequest appRequest, final Orderby orderby, final Criterion... criterions);

    /**
     * 按Criteria查询唯一对象.
     * 
     * @param appRequest
     * @param c
     * @return 实体列表
     */
    <X> List<X> find(AppRequest appRequest, final Criteria c);

    /**
     * 根据id列表，查找的对应的实体列表
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param ids
     * @return 实体列表
     */
    <X> List<X> findByIds(AppRequest appRequest, Class<X> classOfX, Serializable[] ids);

    /**
     * 查找实体列表
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param orderby
     * @param filters
     * @return 实体列表
     */
    <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, PropertyFilter... filters);

    /**
     * 查找实体列表
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param condition
     * @return 实体列表
     */
    // <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Condition condition);

    /**
     * 查找实体列表
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param orderby
     * @param distinct
     * @param filters
     * @return 实体列表
     */
    <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, boolean distinct,
            PropertyFilter... filters);

    /**
     * 查找所有的实体列表
     * 
     * @param <X>
     * @param appRequest
     * @param classOfX
     * @param orderby
     * @return 实体列表
     */
    <X> List<X> findAll(AppRequest appRequest, Class<X> classOfX, Orderby orderby);

    /**
     * 分页查询.
     * 
     * @param appRequest
     * @param page
     * @param c
     * @return page
     */
    Pagination findPage(AppRequest appRequest, Page page, Criteria c);

    /**
     * 按HQL分页查询.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param page
     *            分页参数.不支持其中的orderBy参数.
     * @param hql
     *            hql语句.
     * @param values
     *            数量可变的查询参数,按顺序绑定.
     * 
     * @return 分页查询结果, 附带结果列表及所有查询时的参数.
     */
    Pagination findPage(AppRequest appRequest, Page page, final String hql, final Object... values);

    /**
     * 按HQL分页查询.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param page
     *            分页参数.不支持其中的orderBy参数.
     * @param hql
     *            hql语句.
     * @param values
     *            数量可变的查询参数.
     * 
     * @return 分页查询结果, 附带结果列表及所有查询时的参数.
     */
    Pagination findPage(AppRequest appRequest, Page page, final String hql, final Map<String, ?> values);

    /**
     * 保存
     * 
     * @param <X>
     * @param appRequest
     * @param obj
     * @return 实体
     */
    <X> X save(AppRequest appRequest, X obj);

}
