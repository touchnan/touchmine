/*
 * cn.touchin.db.jdbc.PageQuery.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class PageQuery {
    private String pageSql;
    private Object[] pageParams;

    private String countSql;
    private Object[] countParams;

    public PageQuery(String pageSql, Object[] pageParams, String countSql, Object[] countParams) {
        super();
        this.pageSql = pageSql;
        this.countSql = countSql;
        this.pageParams = pageParams;
        this.countParams = countParams;
    }

    /**
     * @return the pageSql
     */
    public String getPageSql() {
        return pageSql;
    }

    /**
     * @return the pageParams
     */
    public Object[] getPageParams() {
        return pageParams;
    }

    /**
     * @return the countSql
     */
    public String getCountSql() {
        return countSql;
    }

    /**
     * @return the counmtParams
     */
    public Object[] getCountParams() {
        return countParams;
    }

}
