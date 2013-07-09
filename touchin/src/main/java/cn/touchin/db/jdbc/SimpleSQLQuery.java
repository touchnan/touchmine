/*
 * cn.touchin.db.jdbc.SimpleSQLQuery.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SimpleSQLQuery {
    private String sql;
    private Object[] params;

    public SimpleSQLQuery(String sql, Object[] params) {
        super();
        this.sql = sql;
        this.params = params;
    }

    /**
     * @return the sql
     */
    public String getSql() {
        return sql;
    }

    /**
     * @return the params
     */
    public Object[] getParams() {
        return params;
    }

}
