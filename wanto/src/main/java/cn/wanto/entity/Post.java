/*
 * cn.wanto.entity.Post.java
 * Sep 2, 2012 
 */
package cn.wanto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import cn.wanto.util.AppConstants;

/**
 * Sep 2, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_post")
@org.hibernate.annotations.Table(appliesTo = "t_post", comment = "发贴")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "post_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long id;

    @Column(name = "topic_topid", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long topicTopId;// 最顶主题(店铺)

    @Column(name = "topic_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long topicId;// 主题(店铺或散工)

    @Column(name = "user_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long userId;// 发帖人

    @Column(name = "post_time")
    private Date time;// 发帖时间

    @Column(name = "post_last_reply_time")
    private Date lastReplyTime;// 最后回帖时间

    @Column(name = "post_ip", length = 15)
    private String postIp;// 发贴人IP地址

    @Column(name = "post_edit_time")
    private Date editTime;// 帖子编辑时间
    @Column(name = "post_edit_count", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long editCount;// 帖子编辑次数

    @Column(name = "top", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long parentId;// 回复的上层帖子

    @Column(name = "post_status", length = 2)
    private int status;// 状态(删除，定义到AppConstants)

    @Column(name = "post_type", length = 2)
    private int type;// 类型(1-帖子；2-回复，定义到AppConstants)

    @Column(name = "post_click_count", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long clickCount; // 点击次数（浏览次数）

    @Column(name = "post_reply_count", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long replyCount; // 回复次数

    @Column(name = "post_useful_count", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long usefulCount; // 觉得有用的次数

    @Column(name = "post_unuseful_count", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long unusefulCount; // 觉得没用的次数

    @Column(name = "post_lvl", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long lvl;// 自己所在楼层
    
    @Column(name = "post_floors", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long floors;//直接回帖数，盖楼数
    
    @Column(name = "is_top")
    private boolean totop;

    @Column(name = "orderid", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long orderid;// 排序
    
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
     * @return the topicTopId
     */
    public long getTopicTopId() {
        return topicTopId;
    }

    /**
     * @param topicTopId
     *            the topicTopId to set
     */
    public void setTopicTopId(long topicTopId) {
        this.topicTopId = topicTopId;
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
     * @return the time
     */
    public Date getTime() {
        return time;
    }

    /**
     * @param time
     *            the time to set
     */
    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the postIp
     */
    public String getPostIp() {
        return postIp;
    }

    /**
     * @param postIp
     *            the postIp to set
     */
    public void setPostIp(String postIp) {
        this.postIp = postIp;
    }

    /**
     * @return the editTime
     */
    public Date getEditTime() {
        return editTime;
    }

    /**
     * @param editTime
     *            the editTime to set
     */
    public void setEditTime(Date editTime) {
        this.editTime = editTime;
    }

    /**
     * @return the editCount
     */
    public long getEditCount() {
        return editCount;
    }

    /**
     * @param editCount
     *            the editCount to set
     */
    public void setEditCount(long editCount) {
        this.editCount = editCount;
    }

    /**
     * @return the parentId
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the type
     */
    public int getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(int type) {
        this.type = type;
    }

    /**
     * @return the clickCount
     */
    public long getClickCount() {
        return clickCount;
    }

    /**
     * @param clickCount
     *            the clickCount to set
     */
    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * @return the replyCount
     */
    public long getReplyCount() {
        return replyCount;
    }

    /**
     * @param replyCount
     *            the replyCount to set
     */
    public void setReplyCount(long replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * @return the usefulCount
     */
    public long getUsefulCount() {
        return usefulCount;
    }

    /**
     * @param usefulCount
     *            the usefulCount to set
     */
    public void setUsefulCount(long usefulCount) {
        this.usefulCount = usefulCount;
    }

    /**
     * @return the unusefulCount
     */
    public long getUnusefulCount() {
        return unusefulCount;
    }

    /**
     * @param unusefulCount
     *            the unusefulCount to set
     */
    public void setUnusefulCount(long unusefulCount) {
        this.unusefulCount = unusefulCount;
    }

    /**
     * @return the lastReplyTime
     */
    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    /**
     * @param lastReplyTime
     *            the lastReplyTime to set
     */
    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    /**
     * @return the lvl
     */
    public long getLvl() {
        return lvl;
    }

    /**
     * @param lvl
     *            the lvl to set
     */
    public void setLvl(long lvl) {
        this.lvl = lvl;
    }

    /**
     * @return the floors
     */
    public long getFloors() {
        return floors;
    }

    /**
     * @param floors the floors to set
     */
    public void setFloors(long floors) {
        this.floors = floors;
    }
    
    /**
     * @return the orderid
     */
    public long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the totop
     */
    public boolean isTotop() {
        return totop;
    }

    /**
     * @param totop
     *            the totop to set
     */
    public void setTotop(boolean totop) {
        this.totop = totop;
    }    

}
