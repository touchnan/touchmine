/*
 * cn.wanto.entity.TopicWatch.java
 * Sep 2, 2012 
 */
package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.wanto.dto.Composition;
import cn.wanto.util.AppConstants;

/**
 * Sep 2, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_topic_watch")
@org.hibernate.annotations.Table(appliesTo = "t_topic_watch", comment = "主题查看明细")
public class TopicWatch {

    @EmbeddedId
    private Composition id;

    @Column(name = "is_read", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean read;

    /**
     * @return the id
     */
    public Composition getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Composition id) {
        this.id = id;
    }

    /**
     * @return the read
     */
    public boolean isRead() {
        return read;
    }

    /**
     * @param read
     *            the read to set
     */
    public void setRead(boolean read) {
        this.read = read;
    }

}
