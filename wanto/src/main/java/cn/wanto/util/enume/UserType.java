/*
 * cn.wanto.util.UserName.java
 * May 24, 2012 
 */
package cn.wanto.util.enume;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public enum UserType {
    NORMAL("普通用户"), NORMALADMIN("网站管理员"), SUPERADMIN("超级管理员");

    private UserType(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

    public static boolean isSuperAdmin(int role) {
        return role == UserType.SUPERADMIN.ordinal();
    }

    public static boolean isNormalAdmin(int role) {
        return role == UserType.NORMALADMIN.ordinal();
    }

    public static boolean isAdmin(int role) {
        return role >= UserType.NORMALADMIN.ordinal();
    }

    public static UserType parse2UserRole(int role) {
        try {
            return UserType.values()[role];
        } catch (Exception e) {
        }
        return null;
    }
}
