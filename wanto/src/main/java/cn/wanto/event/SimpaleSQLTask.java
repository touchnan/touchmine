/*
 * Sep 10, 2012 
 */
package cn.wanto.event;

import java.sql.SQLException;

import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.touchin.db.jdbc.SimpleSQLQuery;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SimpaleSQLTask implements Runnable {
    private static Log log = Logs.getLog(SimpaleSQLTask.class);
    private SimpleSQLQuery[] pqs;

    public SimpaleSQLTask(SimpleSQLQuery... pqs) {
        this.pqs = pqs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        try {
            if (pqs != null && pqs.length > 0) {
                Db db = Contexts.getBean(Db.class);
                for (SimpleSQLQuery pq : pqs) {
                    db.getQueryRunner().update(pq.getSql(), pq.getParams());
                }
            }
        } catch (SQLException e) {
            log.error(e);
        }
    }

}
