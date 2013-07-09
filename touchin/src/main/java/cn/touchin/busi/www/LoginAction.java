/*
 * cn.touchin.busi.www.LoginAction.java
 * Mar 3, 2012 
 */
package cn.touchin.busi.www;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.VirtualUser;
import cn.touchin.servlet.image.SecurityCodeImageValidate4Session;
import cn.touchin.servlet.image.SecurityCodeImageValidation;

/**
 * Mar 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class LoginAction extends BaseAction {
    private static final long serialVersionUID = 667667530331625283L;

    private static Log log = Logs.getLog(LoginAction.class);

    private String loginName;
    private String passwd;
    private String imgcode;

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        if (!isLogined()) {
            return "login";
        }
        return SUCCESS;
    }
    
    public boolean isNotHtml5Compatible() {
        return isMSIE();
    }
    
    public String login() throws Exception {
        if (!Strings.isBlank(loginName) && !Strings.isBlank(passwd)) {
            if (validateSecurityCode(imgcode)) {
                VirtualUser vu = auth(loginName, passwd);
                if (vu != null) {
                    this.setObj(vu);
                    storeVirtualUser(vu);
                    storeAppRequest(vu);
                } else {
//                    throw new RuntimeException("用户名或密码错误！");
                    super.setErrorVo(new ErrorVo("用户名或密码错误！",ErrorCodeVo.EXCEPTION_THROWN));
                    return RESULT_ERROR_JSON;
                }
            }
        }
        return RESULT_OBJECT_JSON;
    }

    public String logout() throws Exception {
        super.getHttpSession().invalidate();
        return RESULT_JSON;
    }

    /**
     * 验证账号
     * 
     * @param loginName
     * @param passwd
     * @return
     */
    protected abstract VirtualUser auth(String loginName, String passwd);

    /**
     * 验证校验码是否和session中的一样
     * 
     * @param code
     * @return boolean
     */
    protected boolean validateSecurityCode(String code) {
        if (skipSecurityCodevalidation())
            return true;
        HttpSession session = getHttpServletRequest().getSession();
        if (session != null && !Strings.isBlank(code)) {
            String scode = getOldSecurityCode(getHttpServletRequest(), getHttpServletResponse());
            if (isSecuritycodeCaseSensitive()) {
                return code.equals(scode);
            } else {
                return code.equalsIgnoreCase(scode);
            }
        }
        return false;
    }

    /**
     * 是否跳过验证码
     * 
     * @return
     */
    protected abstract boolean skipSecurityCodevalidation();

    /**
     * 获得之前的code
     * 
     * @param request
     * @param response
     * @return OldSecurityCode
     */
    private String getOldSecurityCode(HttpServletRequest request, HttpServletResponse response) {
        String vclassName = getSecurityCodeImageValidationClassName();
        try {
            SecurityCodeImageValidation sciv = (SecurityCodeImageValidation) Class.forName(vclassName).newInstance();
            return sciv.getOldSecurityCode(request, response);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    /**
     * 得到您的应用中验证码servlet的类型名称，默认SecurityCodeImageValidate4Session
     * 
     * @return String
     */
    public String getSecurityCodeImageValidationClassName() {
        return SecurityCodeImageValidate4Session.class.getName();
    }

    /**
     * 验证码是否大小写敏感
     * 
     * @return boolean
     */
    protected boolean isSecuritycodeCaseSensitive() {
        return true;
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
     * @return the imgcode
     */
    public String getImgcode() {
        return imgcode;
    }

    /**
     * @param imgcode
     *            the imgcode to set
     */
    public void setImgcode(String imgcode) {
        this.imgcode = imgcode;
    }

}
