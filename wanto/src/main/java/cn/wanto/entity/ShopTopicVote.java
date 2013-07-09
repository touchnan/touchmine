/*
 * cn.wanto.entity.TopicVote.java
 * Sep 14, 2012 
 */
package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import cn.wanto.dto.Composition;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_shoptopic_vote")
@org.hibernate.annotations.Table(appliesTo = "t_shoptopic_vote", comment = "主题投票")
public class ShopTopicVote {

    @EmbeddedId
    private Composition id;

    @Column(name = "vote", length = 3)
    private int vote;

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
     * @return the vote
     */
    public int getVote() {
        return vote;
    }

    /**
     * @param vote
     *            the vote to set
     */
    public void setVote(int vote) {
        this.vote = vote;
    }

}
