/*
 * cn.touchin.db.jdbc.ISQLParser.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface ISQLParser {
    SimpleSQLQuery createSQLQuery(SQLRunner runner);
    PageQuery createPageQuery(SQLRunner runner);
}
