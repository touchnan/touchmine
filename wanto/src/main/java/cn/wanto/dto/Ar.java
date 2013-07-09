/*
 * cn.wanto.dto.Ar.java
 * May 24, 2012 
 */
package cn.wanto.dto;

import cn.touchin.dto.AppRequest;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.util.enume.UserType;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
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
    private String nickname;
    /**
     * 操作者的登录名
     */
    private String username;

    /**
     * 类型
     */
    private int type;

    /**
     * 操作者的登陆ip
     */
    private String ip;

    public Ar() {
        super();
    }

    public Ar(Vu u) {
        super(u);
    }

    public Ar(UserDto u) {
        super(u);
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

    public long dirNum() {
        return this.loginId / 2000;
    }

    public boolean isSuperAdmin() {
        return UserType.isSuperAdmin(getType());
    }

    public boolean isNormalAdmin() {
        return UserType.isNormalAdmin(getType());
    }

    public boolean isAdmin() {
        return UserType.isAdmin(getType());
    }
}
