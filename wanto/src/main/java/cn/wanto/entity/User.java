/*
 * cn.wanto.entity.User.java
 * May 24, 2012 
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
 * May 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_user")
@org.hibernate.annotations.Table(appliesTo = "t_user", comment = "系统用户")
// @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long id;

    /**
     * 用户名称
     */
    @Column(name = "nickname", length = 100)
    private String nickname;
    /**
     * 用户名
     */
    @Column(name = "username", length = 100, unique = true)
    private String username;

    /**
     * 用户密码
     */
    @Column(name = "user_password", length = 32)
    private String passwd;

    /**
     * 用户类型
     */
    @Column(name = "c_type", length = 4)
    private int type;

    /**
     * 是否隐藏
     * 
     */
    @Column(name = "is_hidden")
    private boolean hidden;

    // @Column(name = "deleted")
    // private boolean active;//

    @Column(name = "is_active", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean deleted;// 是否被删除

    @Column(name = "user_lastvisit")
    private Date lastVisitTime;
    @Column(name = "user_regtime")
    private Date regTime;

    @Column(name = "user_posts", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long posts;//

    @Column(name = "user_level", length = 4)
    private int level;

    @Column(name = "user_avatar", length = 100)
    private String avatar;

    @Column(name = "user_thumb", length = 100)
    private String thumbnail;

    @Column(name = "user_mood")
    private String mood;// 心情

    @Column(name = "user_label")
    private String label;// 标签
    
    @Column(name = "user_atuh_url")
    private String atuhUrl;// 重置密码Url

    @Column(name = "user_post_deletes", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long postdeletes;// 删贴数

    @Column(name = "user_school_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long schoolId;
    @Column(name = "user_school", length = 100)
    private String school;

    @Column(name = "user_school_private", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean schoolprivate;// 学校仅对自己可见

    @Column(name = "user_hometown_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long hometownId;
    @Column(name = "user_hometown", length = 100)
    private String hometown;
    @Column(name = "user_hometown_private", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean hometownPrivate;// 家乡仅对自己可见

    @Column(name = "is_searched", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean searched;// 可否被搜索

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
     * @return the nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     *            the nickname to set
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     *            the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the passwd
     */
    public String getPasswd() {
        return passwd;
    }

    /**
     * @param passwd
     *            the passwd to set
     */
    public void setPasswd(String passwd) {
        this.passwd = passwd;
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
     * @return the hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @param hidden
     *            the hidden to set
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    /**
     * @return the deleted
     */
    public boolean isDeleted() {
        return deleted;
    }

    /**
     * @param deleted
     *            the deleted to set
     */
    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    /**
     * @return the lastVisitTime
     */
    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    /**
     * @param lastVisitTime
     *            the lastVisitTime to set
     */
    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    /**
     * @return the regTime
     */
    public Date getRegTime() {
        return regTime;
    }

    /**
     * @param regTime
     *            the regTime to set
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * @param level
     *            the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * @param avatar
     *            the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * @return the posts
     */
    public long getPosts() {
        return posts;
    }

    /**
     * @param posts
     *            the posts to set
     */
    public void setPosts(long posts) {
        this.posts = posts;
    }

    /**
     * @return the postdeletes
     */
    public long getPostdeletes() {
        return postdeletes;
    }

    /**
     * @param postdeletes
     *            the postdeletes to set
     */
    public void setPostdeletes(long postdeletes) {
        this.postdeletes = postdeletes;
    }

    /**
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * @param school
     *            the school to set
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * @return the schoolprivate
     */
    public boolean isSchoolprivate() {
        return schoolprivate;
    }

    /**
     * @param schoolprivate
     *            the schoolprivate to set
     */
    public void setSchoolprivate(boolean schoolprivate) {
        this.schoolprivate = schoolprivate;
    }

    /**
     * @return the hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * @param hometown
     *            the hometown to set
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * @return the hometownPrivate
     */
    public boolean isHometownPrivate() {
        return hometownPrivate;
    }

    /**
     * @param hometownPrivate
     *            the hometownPrivate to set
     */
    public void setHometownPrivate(boolean hometownPrivate) {
        this.hometownPrivate = hometownPrivate;
    }

    /**
     * @return the searched
     */
    public boolean isSearched() {
        return searched;
    }

    /**
     * @param searched
     *            the searched to set
     */
    public void setSearched(boolean searched) {
        this.searched = searched;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public Long getHometownId() {
        return hometownId;
    }

    public void setHometownId(long hometownId) {
        this.hometownId = hometownId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
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

    public String getAtuhUrl() {
        return atuhUrl;
    }

    public void setAtuhUrl(String atuhUrl) {
        this.atuhUrl = atuhUrl;
    }

}
