/*
 * cn.touch.imr.busi.www.AppPaginationAction.java
 * May 27, 2012 
 */
package cn.touch.imr.busi.www;

import cn.touch.imr.dto.Ar;
import cn.touch.imr.dto.Vu;
import cn.touchin.busi.www.FlexiPaginationSupport;

/**
 * May 27, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AppPaginationAction extends FlexiPaginationSupport {
    private static final long serialVersionUID = 1042310215989600861L;

    @Override
    protected Ar fetchAppRequest() {
        return (Ar) getHttpSession().getAttribute(getKey4AppRequest());
    }

    @Override
    protected Vu fetchVirtualUser() {
        return (Vu) getHttpSession().getAttribute(getKey4VirtualUser());
    }
}
