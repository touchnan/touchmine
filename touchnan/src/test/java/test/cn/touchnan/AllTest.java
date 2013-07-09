/*
 * cn.touchnan.AllTest.java
 * Feb 12, 2012 
 */
package test.cn.touchnan;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.cn.touchnan.hb.HBDAOTest;
import test.cn.touchnan.mail.MailTest;


/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({HBDAOTest.class,MailTest.class})
public class AllTest {

}
