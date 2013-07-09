/*
 * cn.wanto.entity.PostText.java
 * Sep 2, 2012 
 */
package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "t_post_text")
@org.hibernate.annotations.Table(appliesTo = "t_post_text", comment = "贴子内容")
public class PostText {

    @Id
    @Column(name = "post_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long postId;// 帖子主键

    @Column(name = "post_text", columnDefinition = AppConstants.DEFINITION_TEXT_DEFAULT)
    private String text;// 帖子内容

    @Column(name = "post_subject", length = 100)
    private String subject;// 帖子主题

    @Column(name = "bookMark", length = 100)
    private String bookMark;// 标签

    /**
     * @return the postId
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * @param postId
     *            the postId to set
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * @param subject
     *            the subject to set
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBookMark() {
        return bookMark;
    }

    public void setBookMark(String bookMark) {
        this.bookMark = bookMark;
    }

}
