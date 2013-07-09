/*
 * cn.touchnan.util.AppConstants.java
 * Mar 7, 2012 
 */
package cn.touchnan.util;

import cn.touch.kit.Config;

/**
 * Mar 7, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AppConstants {
    private static final Config deploy = new Config("deploy");

    public static final boolean SKIP_SECURITY_CODE = deploy.getBoolean("skip.security.code", true);
    
}
