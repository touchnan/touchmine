/*
 * cn.touch.imr.hb.HBTest.java
 * Feb 12, 2012 
 */
package test.cn.touch.imr.hb;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.nutz.log.Logs;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.touch.db.Db;
import cn.touch.imr.busi.serv.IQueryService;
import cn.touch.imr.dto.bean.UserDto;
import cn.touch.kit.json.JsonKit;
import cn.touchin.Contexts;
import cn.touchin.page.Pagination;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class HBTest {
    private static JsonKit jsonKit;
    private static IQueryService queryService;
    // private static IUserService userService;
    private static Db db;

    @BeforeClass
    public static void beforeClass() {
        Contexts.init(new ClassPathXmlApplicationContext("spring/context.xml"));
        jsonKit = Contexts.getBean(JsonKit.class);
        queryService = Contexts.getBean(IQueryService.class);
        // userService = Contexts.getBean(IUserService.class);
        db = Contexts.getBean(Db.class);
    }

    @Before
    public void before() {
    }

    @After
    public void down() {
    }

    @AfterClass
    public static void downClass() {
    }

    @Test
    public void findUserHidden() {
        List<? extends Object> objs = queryService.findUsers(null);
        Logs.getLog(this.getClass()).error(jsonKit.stringify(objs));

        Pagination page = new Pagination(5, 1, "EQS_c_loginName", "aaaaa", null, null, null);
        Logs.getLog(this.getClass()).error(jsonKit.stringify(queryService.findUsers(null, page)));

        String sql = "SELECT c_nickname AS nickname, c_loginname AS loginName, c_role AS role, c_type AS type, c_hidden AS hidden ";
        sql += " FROM mr_user Where c_hidden=? ";
        Logs.getLog(this.getClass()).error(jsonKit.stringify(db.findFirst(UserDto.class, sql, true)));
    }
}
