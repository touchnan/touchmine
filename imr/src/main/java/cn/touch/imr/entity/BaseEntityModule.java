package cn.touch.imr.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.apache.struts2.json.annotations.JSON;

import cn.touchin.util.Constants;


/**
 * Oct 10, 2011
 * 
 * @author <a href="mailto:touchnan@gmail.com">chengqiang.han</a>
 * 
 */
@MappedSuperclass
public class BaseEntityModule extends BaseEntityClean {
    @Column(name = "c_create_time")
    private Date createTime = new Date();
    @Column(name = "c_lastedit_time")
    private Date lastEditTime = new Date();

    public BaseEntityModule() {
        super();
    }

    /**
     * @return the createTime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     *            the createTime to set
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return the lastEditTime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getLastEditTime() {
        return lastEditTime;
    }

    /**
     * @param lastEditTime
     *            the lastEditTime to set
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public void setLastEditTime(Date lastEditTime) {
        this.lastEditTime = lastEditTime;
    }
}