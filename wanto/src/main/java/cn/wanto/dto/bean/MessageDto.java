/*
 * cn.wanto.dto.bean.TopicDto.java
 * Sep 10, 2012 
 */
package cn.wanto.dto.bean;

import java.util.Date;

import cn.touchin.dto.Dto;
import cn.wanto.entity.Message;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class MessageDto extends Dto<Message> {
    private static final long serialVersionUID = -173988370218102350L;
    private Long id;
    private int type;// 消息类型
    private Long userId;// 用户Id
    private Long shopId;// 店铺Id
    private String shopTitle;// 店铺tile
    private Long topicId;// 主题Id
    private Long postId;// 帖子或回复Id
    private String postTitle;// 帖子或回复Id
    private int kind;// 删除类型
    private Date createTime;
    private Integer status;
    private Boolean reading;
    private Long[] ids;
    private String reason;// 删除理由
    private int opinionState;
    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.dto.Dto#invokeForCallback(java.lang.Object)
     */
    @Override
    public Dto<Message> invokeForCallback(Message obj) {
        this.copyProperties(obj);
        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getTopicId() {
        return topicId;
    }

    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getShopTitle() {
        return shopTitle;
    }

    public void setShopTitle(String shopTitle) {
        this.shopTitle = shopTitle;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Boolean getReading() {
        return reading;
    }

    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOpinionState() {
        return opinionState;
    }

    public void setOpinionState(int opinionState) {
        this.opinionState = opinionState;
    }

}
