/*
 * cn.touchin.db.IDao.java
 * Feb 12, 2012 
 */
package cn.touchin.db;

import java.io.Serializable;
import java.util.List;

import cn.touchin.dto.AppRequest;
import cn.touchin.page.Orderby;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IDao<E extends Object, PK extends Serializable> extends IDaoSupport<E, PK> {

    // /**
    // * 得到实体的类型
    // *
    // * @return 实体的类型
    // */
    // Class<E> getEntityClass();
    //
    // /**
    // * 设置实体的类型
    // *
    // * @param <C>
    // * @param classOfT
    // */
    // void setEntityType(Class<E> classOfT);

    E saveOrUpdate(AppRequest appRequest, E entity);

    /**
     * 插入一个实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entity
     *            实体对象
     * @return 实体本身
     */
    E insert(AppRequest appRequest, E entity);

    /**
     * 一次性插入多个实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entities
     *            实体对象列表
     */
    void insertEntities(AppRequest appRequest, List<E> entities);

    /**
     * 更新一个实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entity
     *            实体对象
     * @return 实体
     */
    E update(AppRequest appRequest, E entity);

    /**
     * 一次性更新多个实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entities
     *            实体对象列表
     * @return 实体列表
     */
    List<E> updates(AppRequest appRequest, List<E> entities);

    /**
     * 从数据库中删除类型为<E>的所有实体对象
     * 
     * @param appRequest
     */
    void deleteAll(AppRequest appRequest);

    /**
     * 根据实体的id，删除实体对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param id
     *            实体id
     */
    void delete(AppRequest appRequest, final PK id);

    /**
     * 删除实体对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entity
     *            对象必须是session中的对象或含id属性的transient对象.
     */
    void delete(AppRequest appRequest, E entity);

    /**
     * 根据实体的id列表，删除对性的应用
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param ids
     *            实体的id列表
     */
    void deletes(AppRequest appRequest, final PK[] ids);

    /**
     * 批量删除实体对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param entities
     *            实体列表
     */
    void deleteEntities(AppRequest appRequest, List<E> entities);

    /**
     * 根据实体id，找到匹配的一个实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param id
     *            实体id
     * @return 实体
     */
    E fetch(AppRequest appRequest, final PK id);

    /**
     * 根据条件，找到一个匹配的实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param filters
     *            查询条件
     * @return 实体
     */
    E fetch(AppRequest appRequest, PropertyFilter... filters);

    /**
     * 根据条件，找到一个匹配的实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param distinct
     * @param filters
     *            查询条件
     * @return 实体
     */
    E fetch(AppRequest appRequest, boolean distinct, PropertyFilter... filters);

    /**
     * 根据条件，找到一个匹配的实体
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param condition
     *            查询条件
     * @return 实体
     */
    // E fetch(AppRequest appRequest, Condition condition);

    /**
     * 查找类型为<E>的所有实体对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @return 实体列表
     */
    List<E> findAll(AppRequest appRequest);

    /**
     * 查找类型为<E>的所有实体对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     *            排序条件
     * @return 实体列表
     */
    List<E> findAll(AppRequest appRequest, Orderby orderby);

    /**
     * 根据实体id数组，查找对应的实体对象列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param ids
     *            实体id数组
     * @return 实体列表
     */
    List<E> findByIds(AppRequest appRequest, final PK[] ids);

    /**
     * 根据查询条件和排序条件，得到实体列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param filters
     *            查询条件
     * @return 实体列表
     */
    List<E> find(AppRequest appRequest, PropertyFilter[] filters);

    /**
     * 根据查询条件和排序条件，得到实体列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param distinct
     * @param filters
     *            查询条件
     * @return 实体列表
     */
    List<E> find(AppRequest appRequest, boolean distinct, PropertyFilter[] filters);

    /**
     * 根据查询条件和排序条件，得到实体列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param condition
     *            查询条件
     * @return Entities list
     * 
     */
    // List<E> find(AppRequest appRequest, Condition condition);

    /**
     * 根据查询条件和排序条件，得到实体列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     *            排序条件
     * @param filters
     *            查询条件
     * @return 实体列表
     */
    List<E> find(AppRequest appRequest, Orderby orderby, PropertyFilter[] filters);

    /**
     * 根据查询条件和排序条件，得到实体列表
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     *            排序条件
     * @param distinct
     * @param filters
     *            查询条件
     * @return 实体列表
     */
    List<E> find(AppRequest appRequest, Orderby orderby, boolean distinct, PropertyFilter[] filters);

    /**
     * 根据分页条件，查找分页数据对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param page
     *            分页条件
     * @return 分页对象
     */
    Pagination findPage(AppRequest appRequest, Pagination page);

    /**
     * 根据分页条件，查找分页数据对象
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param page
     *            分页条件
     * @param condition
     *            查询条件
     * @return 分页对象
     */
    // Pagination findPage(AppRequest appRequest, Pagination page, Condition condition);

    /**
     * 判断对象的属性值在数据库内是否唯一.
     * 
     * 在修改对象的情景下,如果属性新修改的值(value)等于属性原来的值(orgValue)则不作比较.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param propertyName
     *            属性名称
     * @param newValue
     *            属性的新值
     * @param oldValue
     *            属性原来的值
     * @return boolean
     */
    boolean isPropertyUnique(AppRequest appRequest, final String propertyName, final Object newValue,
            final Object oldValue);

    /**
     * 查询一个整形值结果
     * 
     * @param appRequest
     * @param sql
     * @param args
     * @return int
     */
    Integer queryForInt(AppRequest appRequest, String sql, Object[] args);

    /**
     * 查询一个长整形值结果
     * 
     * @param appRequest
     * @param sql
     * @param args
     * @return long
     */
    Long queryForLong(AppRequest appRequest, String sql, Object[] args);

}
