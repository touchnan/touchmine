/*
 * cn.touchin.serv.CommonQueryTemplate.java
 * Mar 11, 2012 
 */
package cn.touchin.serv;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import cn.touch.db.component.NativeQueryTemplate;
import cn.touch.db.component.ObjectProcessor;
import cn.touchin.db.jdbc.ISQLParser;
import cn.touchin.db.jdbc.PageQuery;
import cn.touchin.db.jdbc.SimpleSQLQuery;
import cn.touchin.page.Pagination;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class CommonQueryTemplate extends NativeQueryTemplate {
    protected abstract ISQLParser getSQLParser();

    protected <T extends Object> List<T> find(SimpleSQLQuery sq, ObjectProcessor<T> processor) {
        return nativeQueryList(processor, sq.getSql(), sq.getParams());
    }

    /**
     * 以key-value对的map返回
     * 
     * @param pq
     * @param page
     * @return
     */
    protected Pagination findPage(PageQuery pq, Pagination page) {
        page.setRows(db().find(pq.getPageSql(), pq.getPageParams()));
        page.setTotal(count(pq));
        return page;
    }
    
    protected Pagination findPage(Class<?> clazz, PageQuery pq, Pagination page) {
        page.setRows(db().find(clazz, pq.getPageSql(), pq.getPageParams()));
        page.setTotal(count(pq));
        return page;
    }    

    protected <T extends Object> Pagination findPage(PageQuery pq, Pagination page, ObjectProcessor<T> processor) {
        page.setRows(findPage(pq, processor));
        page.setTotal(count(pq));
        return page;
    }

    protected <T extends Object> List<T> findPage(PageQuery pq, ObjectProcessor<T> processor) {
        return nativeQueryList(processor, pq.getPageSql(), pq.getPageParams());
    }

    protected int count(PageQuery pq) {
        Integer count = nativeQueryObject(new ObjectProcessor<Integer>() {
            @Override
            public Integer invoke(ResultSet rs) {
                try {
                    return rs.getInt(1);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
//                return 0;
            }

        }, pq.getCountSql(), pq.getCountParams());
        return count == null ? 0 : count.intValue();
    }
}
