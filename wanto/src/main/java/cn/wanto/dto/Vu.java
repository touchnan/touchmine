/*
 * cn.wanto.dto.Vu.java
 * May 24, 2012 
 */
package cn.wanto.dto;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Strings;

import com.fasterxml.jackson.annotation.JsonIgnore;

import cn.touch.kit.encrypt.IEncryptor;
import cn.touch.kit.encrypt.MD5;
import cn.touch.kit.json.JsonKit;
import cn.touchin.Contexts;
import cn.touchin.dto.VirtualUser;
import cn.touchin.util.Constants;
import cn.wanto.entity.User;
import cn.wanto.util.AppConstants;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class Vu extends VirtualUser {
    private static final long serialVersionUID = 6879951902362672130L;
    
    private static final IEncryptor encryptor = Contexts.getBean(IEncryptor.class);
    private static final JsonKit jsonkit = Contexts.getBean(JsonKit.class);
    
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
    
    private Date activeTime = new Date();

    protected long validPeriod;// ticket timeout period

    /**
     * 类型
     */
    private int type;

    public Vu() {
        super();
    }

    public Vu(User u) {
        super(u);
        this.setLoginId(u.getId());
    }

    @JsonIgnore
    @JSON(deserialize = false, serialize = false)
    public boolean isValid() {
        if (validPeriod <= 0) {
            return true;
        }
        return new Date().getTime() - activeTime.getTime() <= validPeriod * 1000;
    }

    /**
     * @param value
     * @return
     */
    public static Vu parse(String ticket) {
        if (!Strings.isBlank(ticket) && !AppConstants.disable4Cookie.equals(ticket)) {
            String ticketSrc = encryptor.decrypt(ticket);
            if (!Strings.isBlank(ticketSrc)) {
                String[] data = ticketSrc.split(String.valueOf(Constants.POUND));
                if (null != data && data.length == 2) {
                    String ticketJson = data[0];
                    String signature = data[1];
                    String signatureV = MD5.digest(ticketJson + AppConstants.KEY4SIGNATURE);
                    if (signature.equals(signatureV)) {
                        return jsonkit.parse(Vu.class, ticketJson);
                    }
                }
            }
        }
        return null;
    }

    @JsonIgnore
    @JSON(deserialize = false, serialize = false)
    public String getTicket() {
        String ticket = jsonkit.stringify(this);
        String signature = MD5.digest(ticket + AppConstants.KEY4SIGNATURE);
        return encryptor.encrypt(ticket + Constants.POUND + signature);
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
     * @return the activeTime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getActiveTime() {
        return activeTime;
    }

    /**
     * @param activeTime
     *            the activeTime to set
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public void setActiveTime(Date activeTime) {
        this.activeTime = activeTime;
    }

    /**
     * @return the validPeriod
     */
    public long getValidPeriod() {
        return validPeriod;
    }

    /**
     * @param validPeriod
     *            the validPeriod to set
     */
    public void setValidPeriod(long validPeriod) {
        this.validPeriod = validPeriod;
    }    
}
