/*
 * cn.touchnan.busi.serv.impl.CronService.java
 * May 4, 2012 
 */
package cn.touchnan.busi.serv.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.touch.db.Db;
import cn.touchin.db.jdbc.ISQLParser;
import cn.touchin.db.jdbc.PageQuery;
import cn.touchin.db.jdbc.SQLRunner;
import cn.touchin.page.Pagination;
import cn.touchin.serv.CommonQueryTemplate;
import cn.touchnan.busi.serv.IQueryService;
import cn.touchnan.dto.Ar;
import cn.touchnan.dto.bean.UserDto;

/**
 * May 4, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class QueryService extends CommonQueryTemplate implements IQueryService {
    @Autowired
    private Db db;

    @Autowired
    private ISQLParser sqlParser;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.CommonQueryTemplate#getSQLParser()
     */
    @Override
    protected ISQLParser getSQLParser() {
        return sqlParser;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.db.component.NativeQueryTemplate#db()
     */
    @Override
    protected Db db() {
        return db;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchnan.busi.serv.IQueryService#findUsers(cn.touchnan.dto.Ar,
     * cn.touchin.page.Pagination)
     */
    @Override
    public Pagination findUsers(Ar ar, Pagination page) {
        PageQuery pq = getSQLParser()
                .createPageQuery(
                        new SQLRunner(page)
                                .select(" c_nickname AS nickname, c_loginname AS loginName, c_role AS role, c_type AS type, c_hidden AS hidden ")
                                .from(" mr_user ").where(" c_hidden=? ").setParameters(0));
        return super.findPage(pq, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchnan.busi.serv.IQueryService#findUsers(cn.touchnan.dto.Ar)
     */
    @Override
    public List<? extends Object> findUsers(Ar ar) {
        String sql = "SELECT c_nickname AS nickname, c_loginname AS loginName, c_role AS role, c_type AS type, c_hidden AS hidden ";
        sql += " FROM mr_user Where c_hidden=? ";
        return db().find(UserDto.class, sql, false);
    }

}
