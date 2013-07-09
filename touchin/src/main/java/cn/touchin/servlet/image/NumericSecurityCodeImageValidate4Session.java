package cn.touchin.servlet.image;

public class NumericSecurityCodeImageValidate4Session extends SecurityCodeImageValidate4Session {
    private static final long serialVersionUID = 1997454861535881193L;

    @Override
    protected String generateSecurityCode() {
        return SecurityCodeRandom.getNumericRandomID(definedSecurityCodeLength());
    }

}
