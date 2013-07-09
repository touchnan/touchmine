/*
 * cn.touchnan.dto.UserDto.java
 * May 24, 2012 
 */
package cn.touchnan.dto.bean;

import java.sql.ResultSet;

import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Strings;

import cn.touch.kit.encrypt.MD5;
import cn.touchin.dto.Dto;
import cn.touchnan.entity.User;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class UserDto extends Dto<User> {
    private static final long serialVersionUID = -4100368477318151602L;

    private Long id;

    /**
     * 用户名称
     */
    private String nickName;
    /**
     * 用户登录名
     */
    private String loginName;

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
    @JSON(serialize=false)
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
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the newPasswd
     */
    @JSON(serialize=false)
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

}
