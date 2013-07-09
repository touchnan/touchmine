/*
 * cn.wanto.dto.Composition.java
 * Sep 14, 2012 
 */
package cn.wanto.dto;

import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Composition implements Serializable {
    private static final long serialVersionUID = -4997266228637368183L;
    private Long userId;
    private Long targetId;

    public Composition() {
        super();
    }

    public Composition(long targetId, long userId) {
        super();
        this.userId = userId;
        this.targetId = targetId;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Composition)) {
            return false;
        }
        Composition user = (Composition) obj;
        return new EqualsBuilder().append(this.targetId, user.getTargetId()).append(this.userId, user.getUserId())
                .isEquals();

    }

    public int hashCode() {
        return new HashCodeBuilder().append(this.getTargetId()).append(this.getUserId()).toHashCode();
    }

    /**
     * @return the userId
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return the targetId
     */
    public Long getTargetId() {
        return targetId;
    }

    /**
     * @param targetId
     *            the targetId to set
     */
    public void setTargetId(Long targetId) {
        this.targetId = targetId;
    }

}
