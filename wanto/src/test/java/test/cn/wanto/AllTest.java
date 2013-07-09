package test.cn.wanto;

/*
 * cn.wanto.AllTest.java
 * Feb 12, 2012 
 */
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import test.cn.wanto.hb.HBDAOTest;
import test.cn.wanto.mail.MailTest;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ HBDAOTest.class, MailTest.class })
public class AllTest {

}
