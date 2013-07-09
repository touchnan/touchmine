/*
 * cn.touchin.db.BaseDao.java
 * Feb 12, 2012 
 */
package cn.touchin.db;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.touchin.dto.AppRequest;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class BaseDao<E extends Object, PK extends Serializable> extends HibernateDaoSupport implements
        IDao<E, PK> {
    protected Log log = Logs.getLog(getClass());
    protected Mirror<E> mirror;

    /**
     * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class.
     * 
     */
    @SuppressWarnings("unchecked")
    public BaseDao() {
        try {
            mirror = Mirror.me((Class<E>) Mirror.getTypeParams(getClass())[0]);
        } catch (Throwable e) {
            if (log.isWarnEnabled()) {
                Logs.getLog(getClass()).warn("!!!Fail to get TypeParams for self!", e);
            }
        }
    }

    public BaseDao(final Class<E> entityClass) {
        mirror = (Mirror<E>) Mirror.me(entityClass);
    }

    /* (non-Javadoc)
     * @see cn.touchin.db.IDaoSupport#getEntityClass()
     */
    @Override
    public Class<E> getEntityClass() {
        return mirror.getType();
    }

    /* (non-Javadoc)
     * @see cn.touchin.db.IDaoSupport#setEntityType(java.lang.Class)
     */
    @Override
    public void setEntityType(Class<E> classOfT) {
        mirror = Mirror.me(classOfT);
    }

    /**
     * 取得对象的主键名.
     * 
     * @return id name
     */
    public abstract String getIdName();

    @Override
    public void deleteEntities(AppRequest appRequest, List<E> entities) {
        if (entities != null && !entities.isEmpty()) {
            Iterator<E> itr = entities.iterator();
            while (itr.hasNext()) {
                delete(appRequest, itr.next());
            }
        }
    }

    @Override
    public void deletes(AppRequest appRequest, PK[] ids) {
        if (ids != null && ids.length > 0) {
            for (int i = 0; i < ids.length; i++) {
                delete(appRequest, ids[i]);
            }
        }
    }

    @Override
    public void insertEntities(AppRequest appRequest, List<E> entities) {
        if (entities != null && entities.size() > 0) {
            Iterator<E> itr = entities.iterator();
            while (itr.hasNext()) {
                insert(appRequest, itr.next());
            }
        }
    }

    @Override
    public List<E> updates(AppRequest appRequest, List<E> entities) {
        if (entities != null && entities.size() > 0) {
            Iterator<E> itr = entities.iterator();
            while (itr.hasNext()) {
                update(appRequest, itr.next());
            }
        }
        return entities;
    }
}
