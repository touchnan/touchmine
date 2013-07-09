/*
 * cn.touch.imr.busi.www.StatistAction.java
 * Aug 13, 2012 
 */
package cn.touch.imr.busi.www;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.busi.www.BaseAction;

/**
 * Aug 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class StatistAction extends BaseAction {
    private static final long serialVersionUID = -606139411180754335L;
    @Autowired
    SessionFactory sessionFactory;

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public String cache() throws Exception {
        return RESULT_FREEMARKER;
    }

    /**
     * @return the sessionFactory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
