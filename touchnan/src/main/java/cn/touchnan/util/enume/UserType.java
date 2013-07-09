/*
 * cn.touchnan.util.UserType.java
 * May 24, 2012 
 */
package cn.touchnan.util.enume;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public enum UserType {
    PLACEHOLDER("零索引占位"),PORTION("分成"), DIGIT("计件"), MAMEGER("管理");

    private UserType(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

    public static boolean isPortion(int type) {
        return type == UserType.PORTION.ordinal();
    }

    public static boolean isDigit(int type) {
        return type == UserType.DIGIT.ordinal();
    }

    public static boolean isMameger(int type) {
        return type == UserType.MAMEGER.ordinal();
    }

    public static UserType parse2UserType(int type) {
        try {
            return UserType.values()[type];
        } catch (Exception e) {
        }
        return null;
    }
}
