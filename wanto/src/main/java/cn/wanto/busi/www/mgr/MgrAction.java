/*
 * cn.wanto.busi.www.mgr.MgrAction.java
 * Sep 3, 2012 
 */
package cn.wanto.busi.www.mgr;

import org.springframework.beans.factory.annotation.Autowired;

import cn.wanto.busi.serv.IUserService;
import cn.wanto.busi.www.AppPaginationAction;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.UserDto;

/**
 * Sep 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class MgrAction extends AppPaginationAction {
    private static final long serialVersionUID = -5911059313040400535L;
    
    protected Ar ar;
    protected UserDto user;
    private Long count ;

    @Autowired
    protected IUserService userService;

    /**
     * @return the ar
     */
    public Ar getAr() {
        return ar;
    }

    /**
     * @return the user
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }

}
