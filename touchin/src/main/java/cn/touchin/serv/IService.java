/*
 * cn.touchin.serv.IService.java
 * Feb 13, 2012 
 */
package cn.touchin.serv;

import java.io.Serializable;
import java.util.List;

import cn.touchin.db.IDaoSupport;
import cn.touchin.dto.AppRequest;
import cn.touchin.dto.Dto;
import cn.touchin.page.Orderby;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IService<T extends Dto<E>, E extends Object, PK extends Serializable> {
    /**
     * 得到的DTO的类型
     * 
     * @return DTO的类型
     */
    Class<T> getDTOClass();

    /**
     * 得到实体的类型
     * 
     * @return 实体的类型
     */
    Class<E> getEntityClass();

    /**
     * 得到DAO实例
     * 
     * @param <D>
     * @return DAO实例
     */
    IDaoSupport<E, PK> dao();

    /**
     * 保存
     * 
     * @param ar
     * @param entity
     */
    E save(AppRequest ar, E entity);
    
    
    /**
     * 保存
     * 
     * @param ar
     * @param dto
     * @return
     */
    T save(AppRequest ar, T dto);

    /**
     * 新增
     * 
     * @param ar
     * @param dto
     * @return DTO本身
     */
    T insert(AppRequest ar, T dto);
    
    /**
     * 更新
     * 
     * @param ar
     * @param dto
     * @param dbEntity
     * @return
     */
    T update(AppRequest ar, T dto, E dbEntity); 

    /**
     * 新增
     * 
     * @param ar
     * @param entity
     * @return 实体本身
     */
    E insert(AppRequest ar, E entity);

    /**
     * 批量新增
     * 
     * @param ar
     * @param dtos
     */
    void inserts(AppRequest ar, List<T> dtos);

    /**
     * 批量新增
     * 
     * @param ar
     * @param entities
     */
    void insertEntities(AppRequest ar, List<E> entities);

    /**
     * 更新
     * 
     * @param ar
     * @param entity
     * @return 实体本身
     */
    E update(AppRequest ar, E entity);

    /**
     * 批量更新
     * 
     * @param ar
     * @param entities
     * @return 实体列表
     */
    List<E> updates(AppRequest ar, List<E> entities);

    /**
     * 根据id删除实体
     * 
     * @param ar
     * @param id
     */
    void delete(AppRequest ar, final PK id);

    /**
     * 删除
     * 
     * @param ar
     * @param entity
     */
    void delete(AppRequest ar, E entity);

    /**
     * 删除多个实体
     * 
     * @param ar
     * @param ids
     */
    void deletes(AppRequest ar, final PK[] ids);

    /**
     * 删除多个实体
     * 
     * @param ar
     * @param entities
     */
    void deleteEntities(AppRequest ar, List<E> entities);

    /**
     * 删除此类型的所有实体
     * 
     * @param ar
     */
    void deleteAll(AppRequest ar);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param id
     * @return DTO
     */
    T fetch(AppRequest ar, final PK id);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param id
     * @return 实体
     */
    E fetchEntity(AppRequest ar, final PK id);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param filters
     * @return DTO
     */
    T fetch(AppRequest ar, PropertyFilter... filters);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param distinct
     * @param filters
     * @return DTO
     */
    T fetch(AppRequest ar, boolean distinct, PropertyFilter... filters);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param filters
     * @return 实体
     */
    E fetchEntity(AppRequest ar, PropertyFilter... filters);

    /**
     * 匹配一个对象
     * 
     * @param ar
     * @param distinct
     * @param filters
     * @return 实体
     */
    E fetchEntity(AppRequest ar, boolean distinct, PropertyFilter... filters);

    /**
     * 查找所有的实体，转换成DTO
     * 
     * @param ar
     * @return DTO列表
     */
    List<T> findAll(AppRequest ar);

    /**
     * 查找所有实体，按照排序条件排序
     * 
     * @param ar
     * @param orderby
     * @return DTO列表
     */
    List<T> findAll(AppRequest ar, Orderby orderby);

    /**
     * 根据实体id数组，查找对应的实体
     * 
     * @param ar
     * @param ids
     * @return DTO列表
     */
    List<T> findByIds(AppRequest ar, final PK[] ids);

    /**
     * 根据查询条件和排序条件，查找实体列表
     * 
     * @param ar
     * @param orderby
     * @param filters
     * @return DTO列表
     */
    List<T> find(AppRequest ar, Orderby orderby, PropertyFilter... filters);

    /**
     * 根据查询条件和排序条件，查找实体列表
     * 
     * @param ar
     * @param orderby
     * @param distinct
     * @param filters
     * @return DTO列表
     */
    List<T> find(AppRequest ar, Orderby orderby, boolean distinct, PropertyFilter... filters);

    /**
     * 查找所有的实体
     * 
     * @param ar
     * @return 实体列表
     */
    List<E> findAllEntities(AppRequest ar);

    /**
     * 查找所有实体，按照排序条件排序
     * 
     * @param ar
     * @param orderby
     * @return 实体列表
     */
    List<E> findAllEntities(AppRequest ar, Orderby orderby);

    /**
     * 根据实体id数组，查找对应的实体
     * 
     * @param ar
     * @param ids
     * @return 实体列表
     */
    List<E> findEntitiesByIds(AppRequest ar, final PK[] ids);

    /**
     * 根据查询条件和排序条件，查找实体列表
     * 
     * @param ar
     * @param orderby
     * @param filters
     * @return 实体列表
     */
    List<E> findEntities(AppRequest ar, Orderby orderby, PropertyFilter... filters);

    /**
     * 根据查询条件和排序条件，查找实体列表
     * 
     * @param ar
     * @param orderby
     * @param distinct
     * @param filters
     * @return 实体列表
     */
    List<E> findEntities(AppRequest ar, Orderby orderby, boolean distinct, PropertyFilter... filters);

    /**
     * 分页查询
     * 
     * @param ar
     * @param page
     * @return 分页对象
     */
    Pagination findPage(AppRequest ar, Pagination page);

    /**
     * 分页查询
     * 
     * @param ar
     * @param page
     * @return 分页对象
     */
    Pagination findEntitiesPage(AppRequest ar, Pagination page);

}
