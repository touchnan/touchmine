/*
 * cn.touch.imr.hb.HBDAOTest.java
 * Feb 12, 2012 
 */
package test.cn.touch.imr.hb;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.touch.db.datasource.ComposeBoneCPDataSource;
import cn.touch.db.datasource.DataSourceConfig;
import cn.touch.imr.db.ProxyComposeBoneCPDataSource;
import cn.touchin.Contexts;
import cn.touchin.page.Pagination;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class HBDAOTest {
    private static ProxyComposeBoneCPDataSource db;
    private static String driver = "com.mysql.jdbc.Driver";
    private static String name = "root";
    private static String passwd = "";
    private static String url = "jdbc:mysql://localhost:3306/";

    @BeforeClass
    public static void beforeClass() {
        Contexts.init(new ClassPathXmlApplicationContext("spring/context.xml"));
        db = Contexts.getBean(ProxyComposeBoneCPDataSource.class);
        db.add(new DataSourceConfig().load("test", driver, name, passwd, url + "itdb"));
        db.add(new DataSourceConfig().load("sanguo", driver, name, passwd, url + "testhb"));
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
    public void fetchArea() {
        // Contexts.getBean(MockService.class).roll();

        ComposeBoneCPDataSource.setIdentity("test");
        // IAreaService serv = Contexts.getBean(IAreaService.class);
        // Area area = serv.fetchEntity(null, 7L);
        // System.out.println(area);

        // AreaDto dto = serv.fetch(null, 1L);
        // System.out.println(dto);
        // Area area = new Area();
        // area.setId(7L);
        // area.setName("7kdkd");
        // System.out.println(serv.save(null, area));

        // area = dao.fetch(null, new PropertyFilter("EQL_id","2"));
        Pagination page = new Pagination();
        // page = serv.findPage(null, page);
        // System.out.println(page.getTotalCount());
    }

    @Test
    public void testThreadSafe() throws InterruptedException, ExecutionException {
        List<Future<Boolean>> result = new ArrayList<Future<Boolean>>();
        ExecutorService exec = Executors.newFixedThreadPool(30);
        int count = 1000;
        for (int i = count; i > 0; i--) {
            result.add(exec.submit(new Task(i)));
        }
        exec.shutdown();
        while (!exec.isTerminated()) {
            Thread.yield();
        }

        for (int i = 0; i < result.size(); i++) {
            Assert.assertTrue(i + "行错误", (Boolean) result.get(i).get());
        }
        System.out.println("finished!");
    }

    class Task implements Callable<Boolean> {
        private int id;

        Task(int id) {
            this.id = id;
        }

        @Override
        public Boolean call() throws Exception {
            fetchArea();
            return true;
        }

    }
}
