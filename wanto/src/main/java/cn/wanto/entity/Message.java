package cn.wanto.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Sep 9, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_message")
@org.hibernate.annotations.Table(appliesTo = "t_message", comment = "消息")
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "m_type")
    private int type;//消息类型

    @Column(name = "m_user_id")
    private Long userId;// 用户Id

    @Column(name = "m_shop_id")
    private Long shopId;// 店铺Id

    @Column(name = "m_topic_id")
    private Long topicId;// 主题Id

    @Column(name = "m_post_id")
    private Long postId;// 帖子或回复Id

    @Column(name = "m_kind", length = 3)
    private int kind;// 删除类型
    @Column(name = "user_createtime")
    private Date createTime;
    
    @Column(name = "m_reading")
    private Boolean reading;
    
    @Column(name = "m_status")
    private Integer status;// 状态(定义到AppConstants)

    @Column(name = "m_reason")
    private String reason;// 删除理由
  
    @Column(name = "m_opinion_state")
    private int opinionState;// 管理状态 状态(定义到AppConstants)


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

    public Boolean getReading() {
        return reading;
    }

    public void setReading(Boolean reading) {
        this.reading = reading;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
