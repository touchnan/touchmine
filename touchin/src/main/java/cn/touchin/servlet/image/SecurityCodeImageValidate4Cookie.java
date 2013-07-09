package cn.touchin.servlet.image;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityCodeImageValidate4Cookie extends SecurityCodeImageValidation {
    private static final long serialVersionUID = -215728072560394488L;

    @Override
    protected String generateSecurityCode() {
        return super.generateSecurityCode();
    }

    /* (non-Javadoc)
     * @see cn.touchin.servlet.image.SecurityCodeImageValidation#sendSecurityCode(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.String)
     */
    @Override
    protected void sendSecurityCode(HttpServletRequest request, HttpServletResponse response, String code) {
        try {
            Cookie cookie = new Cookie(SECURITY_CODE_KEY, code);
            // cookie.setSecure(true);
            cookie.setMaxAge(-1);
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
        }
    }

    /* (non-Javadoc)
     * @see cn.touchin.servlet.image.SecurityCodeImageValidation#getOldSecurityCode(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    public String getOldSecurityCode(HttpServletRequest request, HttpServletResponse response) {
        return super.getOldSecurityCodeFromCookie(request, response);
    }

}
