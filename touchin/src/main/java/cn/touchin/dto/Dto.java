/*
 * cn.touchin.dto.Dto.java
 * Feb 13, 2012 
 */
package cn.touchin.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Feb 13, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class Dto<T extends Object> extends Vo {
    /**
     * 
     */
    private static final long serialVersionUID = 1717818057150269096L;
    private Mirror<T> mirror;

    @SuppressWarnings("unchecked")
    public Dto() {
        try {
            mirror = Mirror.me((Class<T>) Mirror.getTypeParams(getClass())[0]);
        } catch (Throwable e) {
            fail2GetTypeParameters4Self(e);
        }
    }

    private void fail2GetTypeParameters4Self(Throwable e) {
        Log log = Logs.getLog(getClass());
        if (log.isWarnEnabled()) {
            log.warn("!!!Fail to get TypeParams for self!", e);
        }
        throw new RuntimeException(e);
    }

    @SuppressWarnings("unchecked")
    public Dto(T entity) {
        super(entity);
        if (entity != null) {
            this.mirror = (Mirror<T>) Mirror.me(entity.getClass());
        }
    }

    @JSON(serialize = false)
    public Class<T> getEntityClass() {
        return mirror.getType();
    }

    @SuppressWarnings("unchecked")
    public <C extends T> void setEntityType(Class<C> classOfT) {
        mirror = (Mirror<T>) Mirror.me(classOfT);
    }

    /**
     * 功能同构造器，把Entity转换成DTO
     * 
     * @param entity
     */
    @SuppressWarnings("unchecked")
    public void fromEntity(T entity) {
        if (null != entity) {
            try {
                if (null == this.mirror) {
                    this.mirror = (Mirror<T>) Mirror.me(entity.getClass());
                }
                BeanUtils.copyProperties(this, entity);
            } catch (Throwable e) {
                Logs.getLog(getClass()).error(e.getMessage(), e);
            }
        }
    }

    /**
     * DTO 转换成 Entity
     * 
     * @return Entity
     */
    public T toEntity() {
        if (this.mirror == null) {
            return null;
        }
        T entity = null;
        try {
            entity = this.mirror.born();
            BeanUtils.copyProperties(entity, this);
        } catch (Throwable e) {
            fail2GetTypeParameters4Self(e);
        }
        return entity;
    }
    
    /**
     * 拷贝基本属性
     * 
     * @param sourceEntity
     * @return
     */
    public Dto<T> copyProperties(T sourceEntity) {
        try {
            BeanUtils.copyProperties(this, sourceEntity);
        } catch (Throwable e) {
            fail2GetTypeParameters4Self(e);
        }
        return this;
    }
    
    /**
     * 回调
     * 
     * @param entity
     * @return
     */
    public abstract Dto<T> invokeForCallback(T entity);
    
}
