/*
 * cn.touch.imr.util.UserName.java
 * May 24, 2012 
 */
package cn.touch.imr.util.enume;

/**
 * May 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public enum UserRole {
    NORMAL("普通用户"), NORMALADMIN("管理员"), SUPERADMIN("超级管理员");

    private UserRole(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

    public static boolean isSuperAdmin(int role) {
        return role == UserRole.SUPERADMIN.ordinal();
    }

    public static boolean isNormalAdmin(int role) {
        return role == UserRole.NORMALADMIN.ordinal();
    }

    public static boolean isAdmin(int role) {
        return role >= UserRole.NORMALADMIN.ordinal();
    }

    public static UserRole parse2UserRole(int role) {
        try {
            return UserRole.values()[role];
        } catch (Exception e) {
        }
        return null;
    }
}
