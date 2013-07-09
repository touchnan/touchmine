/*
 * cn.touch.imr.busi.www.mgr.UserAction.java
 * May 25, 2012 
 */
package cn.touch.imr.busi.www.mgr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.touch.imr.busi.serv.IUserService;
import cn.touch.imr.busi.www.AppPaginationAction;
import cn.touch.imr.dto.bean.UserDto;
import cn.touchin.page.Pagination;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class UserAction extends AppPaginationAction {
    private static final long serialVersionUID = 8691019083802273467L;

    private UserDto user;

    @Autowired
    private IUserService userService;

    // @Autowired
    // private IQueryService queryService;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.FlexiPaginationSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String users() throws Exception {
        return SUCCESS;
    }
    
    public String listUsers() throws Exception {
        this.setPageData(userService.findPage(fetchAppRequest(), assemblePaginationInfo()));
        return RESULT_PAGE_JSON;
    }
    
    
    public String cacheUsers() throws Exception {
        return SUCCESS;
    }
    
    public String listCacheUsers() throws Exception {
        Pagination p = assemblePaginationInfo();
        List<UserDto> rows = userService.findCacheUser(fetchAppRequest());
        p.setTotal(rows.size());
        p.setRows(rows);
        this.setPageData(p);
        return RESULT_PAGE_JSON;
    }
    
    public String saveUser() throws Exception {
        if (isDel()) {
            userService.deletes(fetchAppRequest(), ids_a());
        } else {
            userService.save(fetchAppRequest(), user);
        }
        return RESULT_JSON;
    }

    public String msp() throws Exception {
        int count = userService.resetSelfPasswd(fetchAppRequest(), user.getPasswd(), user.getNewPasswd());
        if (count < 1) {
            throw new RuntimeException("旧密码错误, 无权修改密码!");
        }
        return RESULT_JSON;
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

}
