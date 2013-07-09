/*
 * cn.touchin.page.HSql.java
 * Feb 12, 2012 
 */
package cn.touchin.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Strings;

import cn.touchin.kit.SqlUtil;

/**
 * Feb 12, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class HSql {

    private StringBuffer sql = new StringBuffer();// 查询条件
    private List<Object> args = new ArrayList<Object>();// 查询参数

    // private Object[] args;

    public HSql() {
        super();
    }

    public boolean isBlankSql() {
        return sql.length() <= 0;
    }

    public HSql appendSql(Object s) {
        sql.append(s);
        return this;
    }

    public HSql appendArg(Object arg) {
        args.add(arg);
        return this;
    }

    public HSql appendArgs(List<Object> args) {
        if (null != args && !args.isEmpty()) {
            this.args.addAll(args);
        }
        return this;
    }

    /**
     * @return the sql
     */
    public StringBuffer getSql() {
        return sql;
    }

    public String getNoOrderbyCndSql() {
        String s = null;
        if (sql.length() > 0) {
            String whereCnd = sql.toString();
            String orderby = SqlUtil.fetchOrderby(whereCnd);
            if (!Strings.isBlank(orderby)) {
                s = StringUtils.substringBefore(whereCnd, orderby);
            }
        }
        return s;
    }

    /**
     * @param sql
     *            the sql to set
     */
    public void setSql(StringBuffer sql) {
        this.sql = sql;
    }

    public Object[] getArgsArray() {
        if (args != null && args.size() > 0) {
            return args.toArray();
        } else {
            return null;
        }
    }

    /**
     * @return the args
     */
    public List<Object> getArgs() {
        return args;
    }

    /**
     * @param args
     *            the args to set
     */
    public void setArgs(List<Object> args) {
        this.args = args;
    }



}
