/*
 * cn.wanto.entity.TopicSaid.java
 * Sep 14, 2012 
 */
package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.wanto.util.AppConstants;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_keeper_said")
@org.hibernate.annotations.Table(appliesTo = "t_keeper_said", comment = "店长有话说")
public class TopicSaid {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "said_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long id;

    @Column(name = "topic_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long topicId;

    @Column(name = "keeper_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long userId;

    @Column(name = "keeper_said", length = 100)
    private String said;// 店长说

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the topicId
     */
    public long getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the said
     */
    public String getSaid() {
        return said;
    }

    /**
     * @param said
     *            the said to set
     */
    public void setSaid(String said) {
        this.said = said;
    }

}
