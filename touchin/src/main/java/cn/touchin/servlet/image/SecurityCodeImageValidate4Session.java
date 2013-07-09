package cn.touchin.servlet.image;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityCodeImageValidate4Session extends SecurityCodeImageValidation {
    private static final long serialVersionUID = -5720142885127446002L;

    @Override
    protected String generateSecurityCode() {
        return super.generateSecurityCode();
    }

    @Override
    public String getOldSecurityCode(HttpServletRequest request, HttpServletResponse response) {
        return super.getOldSecurityCodeFromSession(request, response);
    }

}
