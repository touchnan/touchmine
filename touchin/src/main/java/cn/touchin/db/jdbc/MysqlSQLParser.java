/*
 * cn.touchin.db.jdbc.MysqlSQLParser.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

import java.util.List;

import cn.touchin.page.Pagination;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class MysqlSQLParser extends SQLParser {

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.touchin.db.jdbc.ISQLParser#createSQLQuery(cn.touchin.db.jdbc.SQLRunner
     * )
     */
    @Override
    public SimpleSQLQuery createSQLQuery(SQLRunner runner) {
        StringBuffer sql = new StringBuffer("SELECT ");
        List<Object> params = createSql(runner, sql);
        Object[] pageParams = null;
        if (params != null && !params.isEmpty()) {
            pageParams = params.toArray(new Object[params.size()]);
        }
        return new SimpleSQLQuery(sql.toString(), pageParams);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.touchin.db.jdbc.ISQLParser#createPageQuery(cn.touchin.db.jdbc.SQLRunner
     * )
     */
    @Override
    public PageQuery createPageQuery(SQLRunner runner) {
        StringBuffer sql = new StringBuffer("SELECT ");
        StringBuffer countSql = new StringBuffer("SELECT count(0)");
        List<Object> params = createPageSql(runner, sql, countSql);
        sql.append(" LIMIT ?, ?");
        Object[] pageParams = null;
        Object[] countParams = null;
        if (params != null && !params.isEmpty()) {
            countParams = params.toArray(new Object[params.size()]);
            pageParams = params.toArray(new Object[params.size() + 2]);
        } else {
            pageParams = new Object[2];
        }
        pageParams[pageParams.length - 2] = first(runner.flexiPage);
        pageParams[pageParams.length - 1] = second(runner.flexiPage);

        return new PageQuery(sql.toString(), pageParams, countSql.toString(), countParams);
    }

    private long first(Pagination page) {
        return (page.getPage() - 1) * page.getRp();
    }

    private long second(Pagination page) {
        return page.getRp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.touchin.db.jdbc.ISQLParser#getSQLPageParams(cn.touchin.page.Pagination
     * )
     */
    public long[] getSQLPageParams(Pagination page) {
        return new long[] { page.getFirst() - 1, page.getRp() };
    }

}
