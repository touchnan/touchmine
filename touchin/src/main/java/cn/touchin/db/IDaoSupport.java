/*
 * cn.touchin.db.IDaoSupport.java
 * Feb 12, 2012 
 */
package cn.touchin.db;

import java.io.Serializable;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IDaoSupport<E extends Object, PK extends Serializable> {

    /**
     * 得到实体的类型
     * 
     * @return 实体的类型
     */
    Class<E> getEntityClass();

    /**
     * 设置实体的类型
     * 
     * @param <C>
     * @param classOfT
     */
    void setEntityType(Class<E> classOfT);
}
