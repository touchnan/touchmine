/*
 * cn.touch.imr.entity.User.java
 * May 24, 2012 
 */
package cn.touch.imr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "mr_user")
@org.hibernate.annotations.Table(appliesTo = "mr_user", comment = "系统用户")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class User extends BaseEntityClean {

    /**
     * 用户名称
     */
    @Column(name = "c_nickname")
    private String nickName;
    /**
     * 用户登录名
     */
    @Column(name = "c_loginname")
    private String loginName;

    /**
     * 用户密码
     */
    @Column(name = "c_passwd")
    private String passwd;

    /**
     * 用户角色
     */
    @Column(name = "c_role")
    private int role;

    /**
     * 用户类型
     */
    @Column(name = "c_type")
    private int type;

    /**
     * 是否隐藏
     * 
     */
    @Column(name = "c_hidden")
    private boolean hidden;

    /**
     * 生日
     */
    @Column(name = "c_birthday")
    private Date birthday;

    /**
     * 性别
     */
    @Column(name = "c_gender")
    private int gender;

    /**
     * 注册时间
     */
    @Column(name = "c_regtime")
    private Date regtime;

    /**
     * @return the nickName
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * @param nickName
     *            the nickName to set
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    /**
     * @return the loginName
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     *            the loginName to set
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
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
     * @return the birthday
     */
    public Date getBirthday() {
        return birthday;
    }

    /**
     * @param birthday
     *            the birthday to set
     */
    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    /**
     * @return the gender
     */
    public int getGender() {
        return gender;
    }

    /**
     * @param gender
     *            the gender to set
     */
    public void setGender(int gender) {
        this.gender = gender;
    }

    /**
     * @return the regtime
     */
    public Date getRegtime() {
        return regtime;
    }

    /**
     * @param regtime
     *            the regtime to set
     */
    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

}
