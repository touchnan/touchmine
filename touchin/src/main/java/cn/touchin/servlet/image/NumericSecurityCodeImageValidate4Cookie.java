package cn.touchin.servlet.image;

public class NumericSecurityCodeImageValidate4Cookie extends SecurityCodeImageValidate4Cookie {

    private static final long serialVersionUID = -1565222967558937374L;

    @Override
    protected String generateSecurityCode() {
        return SecurityCodeRandom.getNumericRandomID(definedSecurityCodeLength());
    }

}
