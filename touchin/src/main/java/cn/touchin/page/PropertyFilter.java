/*
 * cn.touchin.page.PropertyFilter.java
 * Feb 12, 2012 
 */
package cn.touchin.page;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.nutz.castor.Castors;
import org.springframework.util.Assert;

import cn.touch.kit.Dates;
import cn.touchin.util.Constants;

/**
 * Feb 12, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class PropertyFilter {

    /**
     * 多个属性间OR关系的分隔符.
     */
    public static final String OR_SEPARATOR = "_OR_";

    /**
     * 属性比较类型
     * 
     */
    public enum MatchType {
        CN /* like 包含*/, NC /* not like 不包含*/, NU /*null*/, NN /*not null*/, IN /* in */, NI /* not in */, EQ /* = */, NE /* ≠ */, LT /* < */, GT /* > */,
        LE /* <= */, GE /* >= */;
    }

    /**
     * 属性数据类型
     * 
     */
    public enum PropertyType {
        S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), T(Date.class), B(
                Boolean.class), O(Object.class), Q(BigDecimal.class),M(Date.class);

        private Class<?> clazz;

        PropertyType(Class<?> clazz) {
            this.clazz = clazz;
        }

        public Class<?> getValue() {
            return clazz;
        }
    }

    private String[] propertyNames;
    private Class<?> propertyType;
    private Object propertyValue;
    private MatchType matchType;

    public PropertyFilter() {
    }

    /**
     * @param filterName
     *            比较属性字符串,含待比较的比较类型、属性值类型及属性列表. eg. LIKES_NAME_OR_LOGIN_NAME
     * @param value
     *            待比较的值.
     */
    public PropertyFilter(final String filterName, final String value) {
        String matchTypeStr = StringUtils.substringBefore(filterName, String.valueOf(Constants.BOTTOM_BAR));
        String matchTypeCode = StringUtils.substring(matchTypeStr, 0, matchTypeStr.length() - 1);
        String propertyTypeCode = StringUtils.substring(matchTypeStr, matchTypeStr.length() - 1, matchTypeStr.length());
        try {
            matchType = Enum.valueOf(MatchType.class, matchTypeCode);
            propertyType = Enum.valueOf(PropertyType.class, propertyTypeCode).getValue();
        } catch (Throwable e) {
            throw filterNameError(filterName, e);
        }

        String propertyNameStr = StringUtils.substringAfter(filterName, "_");
        // propertyNames =
        // StringUtils.split(propertyNameStr,PropertyFilter.OR_SEPARATOR);
        propertyNames = propertyNameStr.split(PropertyFilter.OR_SEPARATOR);

        Assert.isTrue(propertyNames.length > 0, "filterName(" + filterName + ")没有按规则编写,无法得到属性名称.");
        // 按entity property中的类型将字符串转化为实际类型.
        if (matchType.equals(MatchType.NU) || matchType.equals(MatchType.NN)) {
            this.propertyValue = null;
        } else {
            if (null == value) {
                this.propertyValue = null;
            } else if (propertyTypeCode.equals(PropertyType.D.name())) {
                this.propertyValue =
                        Castors.me().cast(value, value.getClass(), propertyType, Constants.FORMAT_DATE);
                /*-特殊小于等于开区间日期, 取第二天*/
                if (matchType.equals(MatchType.LE) && this.propertyValue!=null) {
                    this.propertyValue = Dates.getNextDay((Date ) this.propertyValue);
                }
            } else if (propertyTypeCode.equals(PropertyType.T.name())) {
                this.propertyValue =
                        Castors.me().cast(value, value.getClass(), propertyType, Constants.FORMAT_DATETIME);
            } else if(propertyTypeCode.equals(PropertyType.M.name())) {
                /*-月份特殊处理*/
                this.propertyValue = Constants.parse4DATE_YYYY_MM(value);
                /*-特殊小于等于开区间月份, 取下月的第一天*/
                if (matchType.equals(MatchType.LE) && this.propertyValue!=null) {
                    this.propertyValue = Dates.getFirstDayOnNextMonth((Date ) this.propertyValue);
                }
            } else {
                if (matchType.equals(MatchType.IN) || matchType.equals(MatchType.NI)) {
                    this.propertyValue = Castors.me().castTo(value, getArrayType(propertyType));
                } else {
                    this.propertyValue = Castors.me().castTo(value, propertyType);
                }
            }
        }
    }

    private IllegalArgumentException filterNameError(String filterName, Throwable e) {
        return new IllegalArgumentException("filter名称(" + filterName + ")没有按规则编写.", e);
    }

    private Class<?> getArrayType(Class<?> type) {
        if (Long.class.equals(type)) {
            return Long[].class;
        } else if (Integer.class.equals(type)) {
            return Integer[].class;
        } else if (String.class.equals(type)) {
            return String[].class;
        } else if (Double.class.equals(type)) {
            return Double[].class;
        } else if (Date.class.equals(type)) {
            return Date[].class;
        } else if (Boolean.class.equals(type)) {
            return Boolean[].class;
        } else if (BigDecimal.class.equals(type)) {
            return BigDecimal[].class;
        } else {
            return Object[].class;
        }
    }

    /**
     * 适用于直接初始化的场合，只支持单一属性条件。
     * 
     * @param propertyName
     * @param propertyValue
     * @param matchType
     * @return 本身
     */
    public PropertyFilter init(String propertyName, Object propertyValue, MatchType matchType) {
        this.propertyNames = new String[1];
        this.propertyNames[0] = propertyName;
        this.propertyValue = propertyValue;
        this.matchType = matchType;
        return this;
    }

    /**
     * 是否比较多个属性.
     */
    public boolean isMultiProperty() {
        return propertyNames.length > 1;
    }

    /**
     * 获取比较属性名称列表.
     */
    public String[] getPropertyNames() {
        return propertyNames;
    }

    /**
     * 获取唯一的比较属性名称.
     */
    public String getPropertyName() {
        if (propertyNames.length > 1) {
            throw new IllegalArgumentException("There are not only one property");
        }
        return propertyNames[0];
    }

    /**
     * 获取比较值.
     */
    public Object getPropertyValue() {
        return propertyValue;
    }

    /**
     * 获取比较值的类型.
     */
    public Class<?> getPropertyType() {
        return propertyType;
    }

    /**
     * 获取比较方式类型.
     */
    public MatchType getMatchType() {
        return matchType;
    }

    public String getMatchTypeSymbol() {
        if (MatchType.CN.equals(matchType)) {
            return " LIKE ";
        } else if (MatchType.NC.equals(matchType)) {
            return " NOT LIKE ";
        } else if (MatchType.NU.equals(matchType)) {
            return " IS NULL ";
        } else if (MatchType.NN.equals(matchType)) {
            return " IS NOT NULL ";
        } else if (MatchType.EQ.equals(matchType)) {
            return "=";
        } else if (MatchType.NE.equals(matchType)) {
            return "<>";
        } else if (MatchType.LT.equals(matchType)) {
            return "<";
        } else if (MatchType.GT.equals(matchType)) {
            return ">";
        } else if (MatchType.LE.equals(matchType)) {
            return "<=";
        } else if (MatchType.GE.equals(matchType)) {
            return ">=";
        } else if (MatchType.IN.equals(matchType)) {
            return " IN ";
        } else if (MatchType.NI.equals(matchType)) {
            return " NOT IN ";
        }
        return "";
    }



}
