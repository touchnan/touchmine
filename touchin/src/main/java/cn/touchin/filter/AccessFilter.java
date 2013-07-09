/*
 * cn.touchin.filter.AccessFilter.java
 * May 4, 2012 
 */
package cn.touchin.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.json.JSONException;
import org.apache.struts2.json.JSONUtil;
import org.apache.struts2.json.SerializationParams;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.touch.kit.match.MatcherKit;
import cn.touchin.dto.DispatcherVo;
import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.ResponseVo;
import cn.touchin.dto.VirtualUser;
import cn.touchin.util.Constants;

/**
 * May 4, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AccessFilter implements Filter {
    private static Log log = Logs.getLog(AccessFilter.class);

    protected String loginUrl = "c/main.action";
    protected String errorUrl = "template/sys/error.jsp";

    private MatcherKit accessMatcher;
    private boolean reverseMatch = false;

    private boolean wrapWithComments = false;
    private boolean prefix;
    private boolean enableSMD = false;
    private boolean enableGZIP = true;
    private boolean noCache = false;
    private int statusCode;
    private int errorCode;
    private String callbackParameter;
    private String wrapPrefix;
    private String wrapSuffix;

    public AccessFilter(String loginUrl, String errorUrl, MatcherKit accessMatcher) {
        super();
        this.loginUrl = loginUrl;
        this.errorUrl = errorUrl;
        this.accessMatcher = accessMatcher;
    }

    public AccessFilter(String loginUrl, String errorUrl, MatcherKit accessMatcher, boolean reverseMatch) {
        this(loginUrl, errorUrl, accessMatcher);
        this.reverseMatch = reverseMatch;

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#destroy()
     */
    @Override
    public void destroy() {

    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest,
     * javax.servlet.ServletResponse, javax.servlet.FilterChain)
     */
    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException,
            ServletException {
        doFilterInternal((HttpServletRequest) req, (HttpServletResponse) resp, chain);
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
     */
    @Override
    public void init(FilterConfig arg0) throws ServletException {
    }

    /**
     * @param req
     * @param resp
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse resp, FilterChain chain)
            throws IOException, ServletException {
        if (isAccessibleRequest(req)) {
            chain.doFilter(req, resp);
            return;
        }

        if (!isLogined(req)) {
            go2dispatcher(req, resp, loginUrl);
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

    protected void go2alert(HttpServletRequest req, HttpServletResponse resp, String url, Throwable e)
            throws IOException, ServletException {
        String contentType = getContentType(req);
        // if (isAjax4Json(req, contentType)) {
        if (isAjax(req)) {
            ResponseVo vo = new ResponseVo();
            vo.setResult(new DispatcherVo(url));
            vo.setErrorVo(new ErrorVo(e, ErrorCodeVo.EXCEPTION_THROWN, log.isDebugEnabled()));
            writeJSONToResponse(req, resp, vo);
        } else {
            setContentType(resp, contentType);
            String error = e.getMessage();
            if (Strings.isBlank(error)) {// 抛出堆栈信息
                error = e.fillInStackTrace().toString();
            }
            resp.getWriter().write("<script>alert('" + error + "')</script>");
        }
    }

    protected String warpUrl(HttpServletRequest req, String url) {
        String contextPath = req.getContextPath();
        if (!Strings.isBlank(contextPath)) {
            return contextPath + (contextPath.endsWith("/") ? "" : "/") + url;
        }
        return url;
    }

    /**
     * 是否可访问
     * 
     * @param req
     * @return
     */
    protected boolean isAccessibleRequest(HttpServletRequest req) {
        String sp = req.getServletPath();
        if (reverseMatch) {// 反匹配
            return !accessMatcher.isMatched(sp);
        }
        return accessMatcher.isMatched(sp);
    }

    /**
     * 是否登录
     * 
     * @param req
     * @return
     */
    protected boolean isLogined(HttpServletRequest req) {
        return fetchVirtualUser(req) != null;
    }

    /**
     * 得到登录后的虚拟用户
     * 
     * @param req
     * @return
     */
    protected VirtualUser fetchVirtualUser(HttpServletRequest req) {
        HttpSession session = req.getSession();
        return (VirtualUser) session.getAttribute(getKey4VirtualUser());
    }

    /**
     * 得到虚拟用户的关键值
     * 
     * @return
     */
    protected String getKey4VirtualUser() {
        return Constants._KEY_VIRTUAL_USER_;
    }

    protected boolean isAjax(HttpServletRequest req) {
        return Constants.X_Requested_With_AJAX.equals(req.getHeader(Constants.X_Requested_With));
    }

    // protected boolean isAjax4Json(HttpServletRequest req, String contentType)
    // {
    // if (isAjax(req)) {
    // if (Strings.isBlank(contentType) ||
    // Constants.CONTENTTYPE_DEFAULT.equals(contentType)) {
    // return true;// 默认为JSON
    // }
    // return isJson(contentType) || isJsonRpc(contentType);
    // }
    // return false;
    // }

    protected boolean isJson(String contentType) {
        return Constants.CONTENTTYPE_JSON.equals(contentType);
    }

    protected boolean isJsonRpc(String contentType) {
        return Constants.CONTENTTYPE_JSON_RPC.equals(contentType);
    }

    protected String getContentType(HttpServletRequest req) {
        String contentType = req.getHeader("content-type");
        if (contentType != null) {
            int iSemicolonIdx;
            if ((iSemicolonIdx = contentType.indexOf(";")) != -1)
                contentType = contentType.substring(0, iSemicolonIdx);
        }
        return contentType;
    }

    protected void setContentType(HttpServletResponse resp, String contentType) {
        resp.setContentType(contentType + ";charset=" + Constants.DEFAULT_ENCODING);
    }

    protected void writeJSONToResponse(HttpServletRequest req, HttpServletResponse resp, Object rootObject)
            throws IOException {
        try {
            writeJSONToResponse(req, resp, createJSONString(req, rootObject));
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw e;
        } catch (JSONException e) {
            log.error(e.getMessage(), e);
            throw new IOException(e);
        }
    }

    protected void writeJSONToResponse(HttpServletRequest req, HttpServletResponse resp, String serializedJSON)
            throws IOException {
        JSONUtil.writeJSONToResponse(new SerializationParams(resp, Constants.DEFAULT_ENCODING, wrapWithComments,
                serializedJSON, enableSMD, enableGzip(req), noCache, statusCode, errorCode, prefix,
                Constants.CONTENTTYPE_JSON, wrapPrefix, wrapSuffix));
    }

    private String createJSONString(HttpServletRequest request, Object rootObject) throws JSONException {
        String json = JSONUtil.serialize(rootObject);
        json = addCallbackIfApplicable(request, json);
        return json;
    }

    private boolean enableGzip(HttpServletRequest request) {
        return enableGZIP && JSONUtil.isGzipInRequest(request);
    }

    protected String addCallbackIfApplicable(HttpServletRequest request, String json) {
        if (!Strings.isBlank(callbackParameter)) {
            String callbackName = request.getParameter(callbackParameter);
            if ((callbackName != null) && (callbackName.length() > 0))
                json = callbackName + "(" + json + ")";
        }
        return json;
    }
}
