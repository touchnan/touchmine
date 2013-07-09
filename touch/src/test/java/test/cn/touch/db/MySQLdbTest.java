/*
 * cn.touch.db.MySQLdbTest.java
 * Sep 15, 2011 
 */
package test.cn.touch.db;

import java.beans.PropertyVetoException;

import org.junit.BeforeClass;
import org.junit.Test;

import cn.touch.db.MySQLdb;

import com.jolbox.bonecp.BoneCPDataSource;

/**
 * Sep 15, 2011
 * 
 * @author <a href="mailto:touchnan@gmail.com">chengqiang.han</a>
 * 
 */
public class MySQLdbTest extends DbTest {
    @BeforeClass
    public static void beforeClass() throws PropertyVetoException {
//        BasicDataSource bds = new BasicDataSource();
//        bds.setDriverClassName("com.mysql.jdbc.Driver");
//        bds.setUsername("root");
//        bds.setPassword("");
//        bds.setUrl("jdbc:mysql://localhost:3306/mydb");
//        MySQLdbTest.db = new MySQLdb(bds);
        
//        ComboPooledDataSource c3p0 = new ComboPooledDataSource();
//        c3p0.setDriverClass("com.mysql.jdbc.Driver");
//        c3p0.setUser("root");
//        c3p0.setPassword("");
//        c3p0.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
//        MySQLdbTest.db = new MySQLdb(c3p0);
        
        BoneCPDataSource boneCP = new BoneCPDataSource();
        boneCP.setDriverClass("com.mysql.jdbc.Driver");
        boneCP.setJdbcUrl("jdbc:mysql://localhost:3306/mydb");
        boneCP.setUsername("root");
        boneCP.setPassword("");
        MySQLdbTest.db = new MySQLdb(boneCP);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.db.DbTest#init()
     */
    @Override
    protected void init() {
        db.update("DROP  TABLE IF EXISTS `tab_u`");
        db.update("CREATE  TABLE `tab_u` (`id` INT NOT NULL ,`name` VARCHAR(45) NOT NULL, `state` INT(8),PRIMARY KEY (`id`) )");
    }

    @Test
    public void insert() {
         init();
         db.update("INSERT INTO `tab_u` (`id`, `name`, `state`) VALUES (1, '红', 0)");
    }
}
