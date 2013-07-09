/*
 * cn.wanto.entity.Circle.java
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
@Table(name = "t_topic")
@org.hibernate.annotations.Table(appliesTo = "t_topic", comment = "主题")
public class Topic {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "topic_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long id;

    @Column(name = "topic_title", length = 100)
    private String title;// 名称

    // @Column(name = "user_id", columnDefinition = AppConstants.DEFINITION_INT_DEFAULT)
    // private Long userId;

    @Column(name = "topic_time")
    private Date time;// 创建时间
    
    @Column(name = "topic_last_reply_time")
    private Date lastReplyTime;// 最后回复时间    

    @Column(name = "topic_edit_time")
    private Date etime;// 最后编辑时间

    @Column(name = "post_ip", length = 15)
    private String postIp;// IP地址

    @Column(name = "topic_status")
    private int status;// 状态(定义到AppConstants)

    @Column(name = "topic_type", length = 3)
    private int type;// 类型(定义到AppConstants)

    @Column(name = "topic_parent_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long parentId;// 父主题

    @Column(name = "topic_thumb1", length = 100)
    private String thumb1;

    @Column(name = "topic_thumb2", length = 100)
    private String thumb2;

    @Column(name = "topic_thumb3", length = 100)
    private String thumb3;

    @Column(name = "topic_icon", length = 100)
    private String icon;// 店铺图标

    @Column(name = "topic_thumb", length = 100)
    private String thumbnail;

    @Column(name = "topic_first_post_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long firstPostId;// 第一贴

    @Column(name = "topic_last_post_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long lastPostId;// 最后一贴

    @Column(name = "topic_views", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long views;// 查看数

    @Column(name = "topic_replies", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long replies;// 回贴数

    @Column(name = "topic_enjoyments", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long enjoyments;// 喜欢数

    @Column(name = "topic_boredoms", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long boredoms;// 不喜欢数
    
    @Column(name = "topic_floors", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long floors;//直接回帖数，盖楼数
    
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
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
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
     * @return the thumb1
     */
    public String getThumb1() {
        return thumb1;
    }

    /**
     * @param thumb1
     *            the thumb1 to set
     */
    public void setThumb1(String thumb1) {
        this.thumb1 = thumb1;
    }

    /**
     * @return the thumb2
     */
    public String getThumb2() {
        return thumb2;
    }

    /**
     * @param thumb2
     *            the thumb2 to set
     */
    public void setThumb2(String thumb2) {
        this.thumb2 = thumb2;
    }

    /**
     * @return the thumb3
     */
    public String getThumb3() {
        return thumb3;
    }

    /**
     * @param thumb3
     *            the thumb3 to set
     */
    public void setThumb3(String thumb3) {
        this.thumb3 = thumb3;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail
     *            the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    /**
     * @return the firstPostId
     */
    public long getFirstPostId() {
        return firstPostId;
    }

    /**
     * @param firstPostId
     *            the firstPostId to set
     */
    public void setFirstPostId(long firstPostId) {
        this.firstPostId = firstPostId;
    }

    /**
     * @return the lastPostId
     */
    public long getLastPostId() {
        return lastPostId;
    }

    /**
     * @param lastPostId
     *            the lastPostId to set
     */
    public void setLastPostId(long lastPostId) {
        this.lastPostId = lastPostId;
    }

    /**
     * @return the views
     */
    public long getViews() {
        return views;
    }

    /**
     * @param views
     *            the views to set
     */
    public void setViews(long views) {
        this.views = views;
    }

    /**
     * @return the replies
     */
    public long getReplies() {
        return replies;
    }

    /**
     * @param replies
     *            the replies to set
     */
    public void setReplies(long replies) {
        this.replies = replies;
    }

    /**
     * @return the enjoyments
     */
    public long getEnjoyments() {
        return enjoyments;
    }

    /**
     * @param enjoyments
     *            the enjoyments to set
     */
    public void setEnjoyments(long enjoyments) {
        this.enjoyments = enjoyments;
    }

    /**
     * @return the boredoms
     */
    public long getBoredoms() {
        return boredoms;
    }

    /**
     * @param boredoms
     *            the boredoms to set
     */
    public void setBoredoms(long boredoms) {
        this.boredoms = boredoms;
    }

    /**
     * @return the etime
     */
    public Date getEtime() {
        return etime;
    }

    /**
     * @param etime
     *            the etime to set
     */
    public void setEtime(Date etime) {
        this.etime = etime;
    }

    /**
     * @return the lastReplyTime
     */
    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    /**
     * @param lastReplyTime the lastReplyTime to set
     */
    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
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

}
