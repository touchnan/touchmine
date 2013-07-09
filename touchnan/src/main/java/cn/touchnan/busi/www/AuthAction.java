/*
 * cn.touchnan.busi.www.MainAction.java
 * Mar 7, 2012 
 */
package cn.touchnan.busi.www;

import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.busi.www.LoginAction;
import cn.touchin.dto.VirtualUser;
import cn.touchnan.busi.serv.IUserService;
import cn.touchnan.dto.Ar;
import cn.touchnan.dto.Vu;
import cn.touchnan.util.AppConstants;

/**
 * Mar 7, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AuthAction extends LoginAction {
    private static final long serialVersionUID = 7208888495258477333L;

    @Autowired
    private IUserService userService;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.LoginAction#auth(java.lang.String,
     * java.lang.String)
     */
    @Override
    protected Vu auth(String loginName, String passwd) {
        return userService.auth(loginName, passwd);
    }

    @Override
    protected Ar storeAppRequest(VirtualUser vu) {
        Ar ar = new Ar((Vu) vu);
        ar.setIp(getIpAddr());
        getHttpSession().setAttribute(getKey4AppRequest(), ar);
        return ar;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.LoginAction#skipSecurityCodevalidation()
     */
    @Override
    protected boolean skipSecurityCodevalidation() {
        return AppConstants.SKIP_SECURITY_CODE;
    }

}
