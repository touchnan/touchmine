/*
 * cn.wanto.dto.UserDto.java
 * May 24, 2012 
 */
package cn.wanto.dto.bean;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Strings;

import cn.touch.kit.Dates;
import cn.touch.kit.encrypt.MD5;
import cn.touchin.dto.Dto;
import cn.touchin.util.Constants;
import cn.wanto.entity.User;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class UserDto extends Dto<User> {
    private static final long serialVersionUID = -4100368477318151602L;

    private Long id;

    /**
     * 用户名称
     */
    private String nickname;
    /**
     * 用户登录名
     */
    private String username;

    /**
     * 用户密码
     */
    private String passwd;

    /**
     * 新密码
     */
    private String newPasswd;

    /**
     * 用户角色
     */
    private int role;

    /**
     * 用户类型
     */
    private int type;

    private Date lastVisitTime;
    private Date regTime;
    private String avatar;
    private long schoolId;
    private String school;
    private boolean schoolprivate;
    private long hometownId;
    private String hometown;
    private boolean hometownPrivate;
    private boolean searched;
    private String mood;
    private String uploadAvatar;
    private String thumbnail;
    private String label;// 标签
    private String auth;// 认证信息
    private String title;// 联系我们
    private String content;// 联系我们
    
    private List<? extends Object> tags;// 标签 
    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.dto.Dto#invokeForCallback(java.lang.Object)
     */
    @Override
    public Dto<User> invokeForCallback(User entity) {
        copyProperties(entity);
        this.passwd = null;
        return this;
    }

    public Dto<User> init(ResultSet rs) {

        return this;
    }

    public static String encryptPasswd(String passwd) {
        if (!Strings.isBlank(passwd)) {
            return MD5.digest(passwd);
        }
        return "";
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
    @JSON(serialize = false)
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
     * @return the role
     */
    public int getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(int role) {
        this.role = role;
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
     * @return the newPasswd
     */
    @JSON(serialize = false)
    public String getNewPasswd() {
        return newPasswd;
    }

    /**
     * @param newPasswd
     *            the newPasswd to set
     */
    public void setNewPasswd(String newPasswd) {
        this.newPasswd = newPasswd;
    }

    /**
     * @return the lastVisitTime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public String getLastVisitTimeStr() {
        return Dates.format(getRegTime(), Constants.TIME_YYYY_MM_DD_HH_MM_SS);
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
    @JSON(format = Constants.DATE_YYYY_MM_DD)
    public Date getRegTime() {
        return regTime;
    }

    public String getRegTimeStr() {
        return Dates.format(getRegTime(), Constants.DATE_YYYY_MM_DD);
    }

    /**
     * @param regTime
     *            the regTime to set
     */
    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    /**
     * @return the avatar
     */
    public String getAvatar() {
        return avatar;
    }

    public String getAvatarStr() {
        if (Strings.isBlank(getAvatar()))
            return "";
        return "" + getAvatar();
    }

    /**
     * @param avatar
     *            the avatar to set
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getUploadAvatar() {
        return uploadAvatar;
    }

    public void setUploadAvatar(String uploadAvatar) {
        this.uploadAvatar = uploadAvatar;
    }

    public long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(long schoolId) {
        this.schoolId = schoolId;
    }

    public long getHometownId() {
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

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return the tags
     */
    public List<? extends Object> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<? extends Object> tags) {
        this.tags = tags;
    }

}
