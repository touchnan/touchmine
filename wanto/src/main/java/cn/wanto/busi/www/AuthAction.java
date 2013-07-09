/*
 * cn.wanto.busi.www.MainAction.java
 * Mar 7, 2012 
 */
package cn.wanto.busi.www;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.busi.www.LoginAction;
import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.VirtualUser;
import cn.wanto.busi.serv.IUserService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.Vu;
import cn.wanto.filter.WantoFilter;
import cn.wanto.util.AppConstants;

/**
 * Mar 7, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class AuthAction extends LoginAction {
    private static final long serialVersionUID = 7208888495258477333L;

    @Autowired
    private IUserService userService;

    private int maxAge = -1;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.LoginAction#auth(java.lang.String, java.lang.String)
     */
    @Override
    protected Vu auth(String loginName, String passwd) {
        Vu vu = userService.auth(loginName, passwd);
        return vu;
    }

    @Override
    protected VirtualUser storeVirtualUser(VirtualUser vu) {
        WantoFilter.store2Cookie(getHttpServletRequest(), getHttpServletResponse(), (Vu) vu, maxAge);
        return vu;
    }

    @Override
    protected Ar storeAppRequest(VirtualUser vu) {
        // pass
        return null;
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

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.LoginAction#logout()
     */
    @Override
    public String logout() throws Exception {
        WantoFilter.cleanOnCookie(getHttpServletRequest(), getHttpServletResponse());
        return super.logout();
    }

    /**
     * @param maxAge
     *            the maxAge to set
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public String login() throws Exception {
        if ((!(Strings.isBlank(getLoginName()))) && (!(Strings.isBlank(getPasswd())))
                && (validateSecurityCode(getImgcode()))) {
            VirtualUser vu = auth(getLoginName(), getPasswd());
            if (vu != null) {
                setObj(vu);
                storeVirtualUser(vu);
                storeAppRequest(vu);
                WantoFilter.store2Cookie(getHttpServletRequest(), getHttpServletResponse(), (Vu) vu,
                        -1);
            } else {
                super.setErrorVo(new ErrorVo("用户名或密码错误！", ErrorCodeVo.EXCEPTION_THROWN));
                return "error-json";
            }
        }

        return "obj-json";
    }
}
