package cn.touchin.servlet.image;

public class NumericSecurityCodeImageValidate4SecureCookie extends SecurityCodeImageValidate4SecureCookie {
    private static final long serialVersionUID = 170202965636082644L;

    @Override
    protected String generateSecurityCode() {
        return SecurityCodeRandom.getNumericRandomID(definedSecurityCodeLength());
    }

}
