/*
 * cn.touchin.serv.BaseService.java
 * Feb 13, 2012 
 */
package cn.touchin.serv;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.ObjectNotFoundException;
import org.hibernate.metadata.ClassMetadata;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touchin.db.IDao;
import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.dto.Dto;
import cn.touchin.kit.Entities;
import cn.touchin.page.Orderby;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Transactional(propagation = Propagation.REQUIRED, readOnly = true)
public abstract class BaseService<T extends Dto<E>, E extends Object, PK extends Serializable> implements
        IService<T, E, PK> {

    protected Log log = Logs.getLog(getClass());

    protected Mirror<T> dtoMirror;
    protected Mirror<E> entityMirror;

    @SuppressWarnings("unchecked")
    public BaseService() {
        try {
            dtoMirror = Mirror.me((Class<T>) Mirror.getTypeParams(getClass())[0]);
            entityMirror = Mirror.me((Class<E>) Mirror.getTypeParams(getClass())[1]);
        } catch (Throwable e) {
            if (log.isWarnEnabled()) {
                Logs.getLog(getClass()).warn("!!!Fail to get TypeParams for self!", e);
            }
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#dao()
     */
    @Override
    public abstract IDao<E, PK> dao();

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#save(cn.touchin.dto.AppRequest,
     * java.lang.Object)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public E save(AppRequest ar, E entity) {
        return dao().saveOrUpdate(ar, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#save(cn.touchin.dto.AppRequest,
     * cn.touchin.dto.Dto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T save(AppRequest ar, T dto) {
        if (dao() instanceof IHibernateDao) {
            IHibernateDao<E, PK> dao = (IHibernateDao<E, PK>) dao();
            ClassMetadata md = dao.currSession().getSessionFactory().getClassMetadata(dto.getEntityClass());
            try {
                @SuppressWarnings("unchecked")
                PK identifierValue = (PK) Mirror.me(dto.getClass()).getGetter(md.getIdentifierPropertyName())
                        .invoke(dto);
                if (identifierValue != null) {
                    try {
                        E entity = dao().fetch(ar, identifierValue);
                        return update(ar, dto, entity);
                    } catch (ObjectNotFoundException e) {
                        //主键是Assigned的情况
                        return insert(ar, dto);
                    }

                } else {
                    return insert(ar, dto);
                }
            } catch (Exception e) {
                log.error(e);
                throw new RuntimeException(e);
            }

        }
        throw new RuntimeException("此情景的保存方法需要自己实现！");
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#update(cn.touchin.dto.AppRequest,
     * cn.touchin.dto.Dto, java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public T update(AppRequest ar, T dto, E dbEntity) {
        try {
            BeanUtils.copyProperties(dbEntity, dto);
            dbEntity = save(ar, dbEntity);
            dto.invokeForCallback(dbEntity);
            return dto;
        } catch (Throwable e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#delete(cn.touchin.dto.AppRequest,
     * java.lang.Object)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(AppRequest ar, E entity) {
        dao().delete(ar, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#delete(cn.touchin.dto.AppRequest,
     * java.io.Serializable)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(AppRequest ar, PK id) {
        dao().delete(ar, id);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#deleteAll(cn.touchin.dto.AppRequest)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAll(AppRequest ar) {
        dao().deleteAll(ar);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#deleteEntities(cn.touchin.dto.AppRequest,
     * java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteEntities(AppRequest ar, List<E> entities) {
        dao().deleteEntities(ar, entities);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#deletes(cn.touchin.dto.AppRequest, PK[])
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deletes(AppRequest ar, PK[] ids) {
        dao().deletes(ar, ids);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#fetch(cn.touchin.dto.AppRequest,
     * java.io.Serializable)
     */
    @Override
    public T fetch(AppRequest ar, PK id) {
        E entity = dao().fetch(ar, id);
        return Entities.castEntity2DTO(entity, getDTOClass());
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#fetch(cn.touchin.dto.AppRequest,
     * cn.touchin.page.PropertyFilter[])
     */
    @Override
    public T fetch(AppRequest ar, PropertyFilter... filters) {
        E entity = dao().fetch(ar, filters);
        return Entities.castEntity2DTO(entity, getDTOClass());
    }

    @Override
    public T fetch(AppRequest ar, boolean distinct, PropertyFilter... filters) {
        E entity = dao().fetch(ar, distinct, filters);
        return Entities.castEntity2DTO(entity, getDTOClass());
    }

    @Override
    public E fetchEntity(AppRequest ar, PK id) {
        return dao().fetch(ar, id);
    }

    @Override
    public E fetchEntity(AppRequest ar, PropertyFilter... filters) {
        return dao().fetch(ar, filters);
    }

    @Override
    public E fetchEntity(AppRequest ar, boolean distinct, PropertyFilter... filters) {
        return dao().fetch(ar, distinct, filters);
    }

    public List<T> find(AppRequest ar, Orderby orderby, PropertyFilter... filters) {
        List<E> entities = dao().find(ar, orderby, filters);
        return (List<T>) Entities.castEntities2DTOs(entities, getDTOClass());
    }

    @Override
    public List<T> find(AppRequest ar, Orderby orderby, boolean distinct, PropertyFilter... filters) {
        List<E> entities = dao().find(ar, orderby, distinct, filters);
        return (List<T>) Entities.castEntities2DTOs(entities, getDTOClass());
    }

    public List<T> findByIds(AppRequest ar, PK[] ids) {
        List<E> entities = dao().findByIds(ar, ids);
        return (List<T>) Entities.castEntities2DTOs(entities, getDTOClass());
    }

    public List<E> findEntities(AppRequest ar, Orderby orderby, PropertyFilter... filters) {
        return dao().find(ar, orderby, filters);
    }

    @Override
    public List<E> findEntities(AppRequest ar, Orderby orderby, boolean distinct, PropertyFilter... filters) {
        return dao().find(ar, orderby, distinct, filters);
    }

    public List<E> findEntitiesByIds(AppRequest ar, PK[] ids) {
        return dao().findByIds(ar, ids);
    }

    @Override
    public Pagination findEntitiesPage(AppRequest ar, Pagination page) {
        return dao().findPage(ar, page);
    }

    @Override
    public Pagination findPage(AppRequest ar, Pagination page) {
        Pagination p = dao().findPage(ar, page);
        return (Pagination) p.castResultEntities2DTOs(getDTOClass());
    }

    public List<T> findAll(AppRequest ar) {
        List<E> entities = dao().findAll(ar);
        return Entities.castEntities2DTOs(entities, getDTOClass());
    }

    public List<T> findAll(AppRequest ar, Orderby orderby) {
        List<E> entities = dao().find(ar, orderby, null);
        return Entities.castEntities2DTOs(entities, getDTOClass());
    }

    public List<E> findAllEntities(AppRequest ar, Orderby orderby) {
        return dao().find(ar, orderby, null);
    }

    public List<E> findAllEntities(AppRequest ar) {
        return dao().findAll(ar);
    }

    public Class<T> getDTOClass() {
        return (null == dtoMirror) ? null : dtoMirror.getType();
    }

    public Class<E> getEntityClass() {
        return (null == entityMirror) ? null : entityMirror.getType();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#insert(cn.touchin.dto.AppRequest,
     * java.lang.Object)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public E insert(AppRequest ar, E entity) {
        return dao().insert(ar, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#insert(cn.touchin.dto.AppRequest,
     * cn.touchin.dto.Dto)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public T insert(AppRequest ar, T dto) {
        E entity = dto.toEntity();
        entity = insert(ar, entity);
        dto.invokeForCallback(entity);
        return dto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#insertEntities(cn.touchin.dto.AppRequest,
     * java.util.List)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertEntities(AppRequest ar, List<E> entities) {
        dao().insertEntities(ar, entities);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#inserts(cn.touchin.dto.AppRequest,
     * java.util.List)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void inserts(AppRequest ar, List<T> dtos) {
        Iterator<T> itr = dtos.iterator();
        while (itr.hasNext()) {
            insert(ar, itr.next());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#update(cn.touchin.dto.AppRequest,
     * java.lang.Object)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public E update(AppRequest ar, E entity) {
        return dao().update(ar, entity);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.IService#updates(cn.touchin.dto.AppRequest,
     * java.util.List)
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public List<E> updates(AppRequest ar, List<E> entities) {
        return dao().updates(ar, entities);
    }
}
