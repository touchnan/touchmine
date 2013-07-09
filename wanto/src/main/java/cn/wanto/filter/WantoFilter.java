/*
 * cn.wanto.filter.WantoFilter.java
 * Aug 29, 2012 
 */
package cn.wanto.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.touch.kit.match.MatcherKit;
import cn.touchin.dto.DispatcherVo;
import cn.touchin.dto.ResponseVo;
import cn.touchin.dto.VirtualUser;
import cn.touchin.filter.AccessFilter;
import cn.wanto.dto.Vu;
import cn.wanto.util.AppConstants;

/**
 * Aug 29, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class WantoFilter extends AccessFilter {

    /**
     * @param loginUrl
     * @param errorUrl
     * @param accessMatcher
     */
    public WantoFilter(String loginUrl, String errorUrl, MatcherKit accessMatcher) {
        super(loginUrl, errorUrl, accessMatcher);
    }

    /**
     * @param loginUrl
     * @param errorUrl
     * @param accessMatcher
     * @param reverseMatch
     */
    public WantoFilter(String loginUrl, String errorUrl, MatcherKit accessMatcher, boolean reverseMatch) {
        super(loginUrl, errorUrl, accessMatcher, reverseMatch);
    }

    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {

        if (isAccessibleRequest(req)) {
            chain.doFilter(req, resp);
            return;
        }

        if (fetchVirtualUser(req) == null) {// 需要登录
            go2dispatcher(req, resp, loginUrl+"?url="+AppConstants.encodeReqUrl(req));
            return;
        }

        // if (!isVisited()) {//没权限
        // go2alert(req, resp, url, e);
        // }

        chain.doFilter(req, resp);
    }

    protected void go2dispatcher(HttpServletRequest req, HttpServletResponse resp, String url) throws IOException {
        String contentType = getContentType(req);
        // if (isAjax4Json(req, contentType)) {
        if (isAjax(req)) {
            ResponseVo vo = new ResponseVo();
            vo.setResult(new DispatcherVo(url));
            writeJSONToResponse(req, resp, vo);
        } else {
            setContentType(resp, contentType);
            resp.getWriter().write(
                    "<script type=\"text/javascript\">window.top.location.href='" + warpUrl(req, url) + "'</script>");
        }
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.filter.AccessFilter#fetchVirtualUser(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected VirtualUser fetchVirtualUser(HttpServletRequest req) {
        return fetchTicketOnCookie(req);
    }

    /* (non-Javadoc)
     * @see cn.touchin.filter.AccessFilter#isAccessibleRequest(javax.servlet.http.HttpServletRequest)
     */
    @Override
    protected boolean isAccessibleRequest(HttpServletRequest req) {
        String sp = req.getServletPath();     
        return super.isAccessibleRequest(req) || sp.indexOf("upload-")>-1;
    }

    /**
     * 取得有效的Ticket
     * 
     * @param req
     * @return
     */
    public static Vu fetchTicketOnCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                if (AppConstants.key4Cookie.equals(cookies[i].getName())) {
                    Vu cu = Vu.parse(cookies[i].getValue());
                    if (cu != null && cu.isValid()) {

                        return cu;
                    }
                    break;// 退出循环
                }
            }
        }
        return null;
    }

    public static void store2Cookie(HttpServletRequest req, HttpServletResponse resp, Vu vu, int maxAge) {
        Cookie cookie = new Cookie(AppConstants.key4Cookie, vu.getTicket());
        if (maxAge < 0) {
            cookie.setMaxAge(-1);
        } else if (maxAge > 0) {
            cookie.setMaxAge(maxAge);
        }
        pushCookie(req, resp, cookie);
    }

    public static void cleanOnCookie(HttpServletRequest req, HttpServletResponse resp) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (AppConstants.key4Cookie.equals(cookie.getName())) {
                    cookie.setValue(AppConstants.disable4Cookie);
                    cookie.setMaxAge(0);
                    pushCookie(req, resp, cookie);
                }
            }
        }
    }    
    
    private static void pushCookie(HttpServletRequest req, HttpServletResponse resp, Cookie cookie) {
        cookie.setPath(req.getContextPath());
        resp.addCookie(cookie);
    }

}
