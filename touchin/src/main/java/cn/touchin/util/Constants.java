/*
 * cn.touchin.page.util.Constants.java
 * Feb 12, 2012 
 */
package cn.touchin.util;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Strings;
import org.springframework.util.Assert;

import cn.touch.kit.Dates;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Constants {
//    public static final int DEFAULT_PAGE_SIZE = 20;

    public static final char EQUAL = '=';
    public static final char MIDDLE_BAR = '-';
    public static final char BOTTOM_BAR = '_';
    public static final char LEFT_PARENTHESIS = '(';
    public static final char RIGHT_PARENTHESIS = ')';
    public static final char QUOTATION_MARK = '\'';
    public static final char WHITE_SPACE = ' ';
    public static final char LIKE_TAG = '%';
    public static final char COLON = ':';
    public static final char COMMA = ',';
    public static final char FULL_STOP = '.';
    public static final char POINT = '.';
    public static final char QUESTION_MARK = '?';
    public static final char ASTERISK = '*';
    public static final char POUND = '#';
    public static final char AND = '&';
    public static final char LEFT_SLASH = '/';
    public static final char RIGHT_SLASH = '\\';
    public static final String LOWER = "LOWER";
    public static final String LIKE = "LIKE";
    public static final String NOT = "NOT";

    public static final String FORMAT_DATE = "DATE";
    public static final String FORMAT_DATETIME = "DATETIME";
    public static final String FORMAT_TIME = "TIME";

    public static final String ID = "id";

    public static final String _KEY_VIRTUAL_USER_ = "vu@touchin";
    public static final String _KEY_APP_REQUEST_ = "ar@touchin";

    public static final String UNKNOWN = "unknown";

    public static final String DEFAULT_ENCODING = "UTF-8";
    public static final String CONTENTTYPE_DEFAULT="application/x-www-form-urlencoded";
    public static final String CONTENTTYPE_JSON = "application/json";
    public static final String CONTENTTYPE_JSON_RPC = "application/json-rpc";
    public static final String X_Requested_With = "X-Requested-With";
    public static final String X_Requested_With_AJAX = "XMLHttpRequest";
    public static final String USER_AGENT = "User-Agent";

    public static final String DATE_YYYY_MM = "yyyy-MM";
    public static final String DATE_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String TIME_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static Date parse4TIME_YYYY_MM_DD_HH_MM_SS(String dateStr) {
        return Dates.parse(dateStr, TIME_YYYY_MM_DD_HH_MM_SS);
    }

    public static Date parse4DATE_YYYY_MM_DD(String dateStr) {
        return Dates.parse(dateStr, DATE_YYYY_MM_DD);
    }

    public static Date parse4DATE_YYYY_MM(String dateStr) {
        return Dates.parse(dateStr, DATE_YYYY_MM);
    }

    public static String format2TIME_YYYY_MM_DD_HH_MM_SS(Date date) {
        return Dates.format(date, TIME_YYYY_MM_DD_HH_MM_SS);
    }

    public static String format2DATE_YYYY_MM_DD(Date date) {
        return Dates.format(date, DATE_YYYY_MM_DD);
    }

    public static String format2DATE_YYYY_MM(Date date) {
        return Dates.format(date, DATE_YYYY_MM);
    }

    /**
     * 计算c在src中出现的次数
     * 
     * @param cs
     * @param c
     * @return count
     */
    public static int countAlias(CharSequence cs, char c) {
        int count = 0;
        if (!Strings.isBlank(cs)) {
            int length = cs.length();
            for (int i = 0; i < length; i++) {
                if (c == cs.charAt(i)) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static String sqlFieldFilter(String str) {
        Assert.isTrue(StringUtils.containsOnly(str, "0123456789_.$abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), "字符不符合规则,存在SQL注入危险");
        return str;
    }

    public static String getClientIpAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (Strings.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (Strings.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (Strings.isBlank(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
