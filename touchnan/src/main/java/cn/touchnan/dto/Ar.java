/*
 * cn.touchnan.dto.Ar.java
 * May 24, 2012 
 */
package cn.touchnan.dto;

import cn.touchin.dto.AppRequest;
import cn.touchnan.dto.bean.UserDto;
import cn.touchnan.util.enume.UserRole;
import cn.touchnan.util.enume.UserType;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Ar extends AppRequest {
    private static final long serialVersionUID = -6056321350965885434L;
    /**
     * 操作者的id
     */
    private Long loginId;
    /**
     * 操作者的名称
     */
    private String nickName;
    /**
     * 操作者的登录名
     */
    private String loginName;

    /**
     * 操作者的登陆ip
     */
    private String ip;

    /**
     * 角色
     */
    private int role;

    /**
     * 类型
     */
    private int type;

    /**
     * 合作厂商
     * 
     */
    private int peersite;

    public Ar() {
        super();
    }

    public Ar(Vu u) {
        super(u);
    }

    public Ar(UserDto u) {
        super(u);
    }

    public boolean isTypePortion() {
        return UserType.isPortion(this.type);
    }

    public boolean isTypeDigit() {
        return UserType.isDigit(this.type);
    }

    public boolean isTypeMgr() {
        return UserType.isMameger(this.type);
    }

    public boolean isRoleSuperAdmin() {
        return UserRole.isSuperAdmin(this.role);
    }

    public boolean isRoleNormalAdmin() {
        return UserRole.isNormalAdmin(this.role);
    }

    public boolean isRoleAdmin() {
        return UserRole.isAdmin(this.role);
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
     * @return the loginId
     */
    public Long getLoginId() {
        return loginId;
    }

    /**
     * @param loginId
     *            the loginId to set
     */
    public void setLoginId(Long loginId) {
        this.loginId = loginId;
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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip
     *            the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the peersite
     */
    public int getPeersite() {
        return peersite;
    }

    /**
     * @param peersite the peersite to set
     */
    public void setPeersite(int peersite) {
        this.peersite = peersite;
    }

}
