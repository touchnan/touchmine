/*
 * cn.touchnan.busi.www.AppPaginationAction.java
 * May 27, 2012 
 */
package cn.touchnan.busi.www;

import cn.touchin.busi.www.FlexiPaginationSupport;
import cn.touchnan.dto.Ar;
import cn.touchnan.dto.Vu;

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
