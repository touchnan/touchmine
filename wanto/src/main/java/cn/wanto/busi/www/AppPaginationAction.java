/*
 * cn.wanto.busi.www.AppPaginationAction.java
 * May 27, 2012 
 */
package cn.wanto.busi.www;

import cn.touchin.busi.www.FlexiPaginationSupport;
import cn.wanto.dto.Ar;
import cn.wanto.dto.Vu;
import cn.wanto.filter.WantoFilter;

/**
 * May 27, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class AppPaginationAction extends FlexiPaginationSupport {
    private static final long serialVersionUID = 1042310215989600861L;

    @Override
    protected Ar fetchAppRequest() {
        Ar ar = new Ar(fetchVirtualUser());
        ar.setIp(getIpAddr());
        return ar;
    }

    @Override
    protected Vu fetchVirtualUser() {
        Vu vu = WantoFilter.fetchTicketOnCookie(getHttpServletRequest());
        return vu;
    }

    /**
     * @return the vu
     */
    public Vu getVu() {
        return fetchVirtualUser();
    }

}
