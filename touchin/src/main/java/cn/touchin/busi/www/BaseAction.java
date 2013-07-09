/*
 * cn.touchin.busi.www.BaseAction.java
 * Mar 11, 2012 
 */
package cn.touchin.busi.www;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.nutz.lang.Strings;

import cn.touchin.dto.AppRequest;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.VirtualUser;
import cn.touchin.util.Constants;

import com.opensymphony.xwork2.ActionSupport;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class BaseAction extends ActionSupport {
    private static final long serialVersionUID = 2203365012243033272L;

    public static final String RESULT_DOWN = "down";
    public static final String RESULT_JSON = "json";
    public static final String RESULT_ERROR_JSON = "error-json";
    public static final String RESULT_PAGE_JSON = "page-json";
    public static final String RESULT_OBJECT_JSON = "obj-json";
    public static final String RESULT_LIST_JSON = "list-json";
    public static final String RESULT_FREEMARKER = "ftl";

    public static final String _EMPTY = "_empty";

    private String oper;// 操作类型

    private ErrorVo errorVo;
    private List<? extends Object> lists;
    private Object obj;
    private String id;

    protected HttpServletRequest getHttpServletRequest() {
        return ServletActionContext.getRequest();
    }

    protected HttpServletResponse getHttpServletResponse() {
        return ServletActionContext.getResponse();
    }

    protected ServletContext getServletContext() {
        return ServletActionContext.getServletContext();
    }

    protected HttpSession getHttpSession() {
        return getHttpServletRequest().getSession();
    }

    protected String getIpAddr() {
        return Constants.getClientIpAddr(getHttpServletRequest());
    }

    protected boolean isMSIE() {
        return isBrowser("msie");
    }

    protected boolean isWebKit() {
        return isBrowser("webkit");
    }

    protected boolean isOpera() {
        return isBrowser("opera");
    }

    protected boolean isMozilla() {
        return isBrowser("firefox") && isBrowser("mozilla");
    }

    private boolean isBrowser(String browser) {
        String agent = getUserAgent();
        if (!Strings.isBlank(agent)) {
            return agent.toLowerCase().indexOf(browser) > -1;
        }
        return false;
    }

    protected String getUserAgent() {
        return getHttpServletRequest().getHeader(Constants.USER_AGENT);
    }

    /**
     * 得到虚拟用户的关键值
     * 
     * @return
     */
    protected String getKey4VirtualUser() {
        return Constants._KEY_VIRTUAL_USER_;
    }

    /**
     * 得到登录后的虚拟用户
     * 
     * @param req
     * @return
     */
    protected VirtualUser fetchVirtualUser() {
        return (VirtualUser) getHttpSession().getAttribute(getKey4VirtualUser());
    }

    /**
     * 备份登录后的虚拟用户
     * 
     * @return
     */
    protected VirtualUser storeVirtualUser(VirtualUser vu) {
        getHttpSession().setAttribute(getKey4VirtualUser(), vu);
        return vu;
    }

    /**
     * 是否登录
     * 
     * @param req
     * @return
     */
    protected boolean isLogined() {
        return fetchVirtualUser() != null;
    }

    /**
     * 得到AppRequest的关键值
     * 
     * @return
     */
    protected String getKey4AppRequest() {
        return Constants._KEY_APP_REQUEST_;
    }

    /**
     * 得到登录后的AppRequest
     * 
     * @param req
     * @return
     */
    protected AppRequest fetchAppRequest() {
        return (AppRequest) getHttpSession().getAttribute(getKey4AppRequest());
    }

    /**
     * 备份登录后的AppRequest
     * 
     * @return
     */
    protected AppRequest storeAppRequest(VirtualUser vu) {
        AppRequest ar = new AppRequest(vu);
        // ar.setIp(getIpAddr());
        getHttpSession().setAttribute(getKey4AppRequest(), ar);
        return ar;
    }

    public boolean isBlankId() {
        return Strings.isBlank(id) || _EMPTY.equals(id);
    }

    public boolean isDel() {
        return "del".equals(oper);
    }

    /**
     * @return the id
     */
    public Long getId() {
        if (!isBlankId() && StringUtils.isNumeric(id)) {
            return Long.valueOf(id);
        }
        return null;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the ids
     */
    public List<Long> ids() {
        if (!isBlankId()) {
            return Arrays.asList(ids_a());
        }
        return Collections.emptyList();
    }

    public Long[] ids_a() {
        if (!isBlankId()) {
            String[] vs = StringUtils.split(id, ",");
            Long[] ids = new Long[vs.length];
            for (int i = vs.length - 1; i >= 0; i--) {
                if (StringUtils.isNumeric(vs[i])) {
                    ids[i] = Long.valueOf(vs[i]);
                }
            }
            return ids;
        }
        return null;
    }
    
    public String ids_s() {
        return isBlankId() ? null : this.id;
    }
    
    /**
     * @return the errorVo
     */
    public ErrorVo getErrorVo() {
        return errorVo;
    }

    /**
     * @param errorVo
     *            the errorVo to set
     */
    public void setErrorVo(ErrorVo errorVo) {
        this.errorVo = errorVo;
    }

    /**
     * @return the lists
     */
    public List<? extends Object> getLists() {
        return lists;
    }

    /**
     * @param lists
     *            the lists to set
     */
    public void setLists(List<? extends Object> lists) {
        this.lists = lists;
    }

    /**
     * @return the obj
     */
    public Object getObj() {
        return obj;
    }

    /**
     * @param obj
     *            the obj to set
     */
    public void setObj(Object obj) {
        this.obj = obj;
    }

    /**
     * @param oper
     *            the oper to set
     */
    public void setOper(String oper) {
        this.oper = oper;
    }

}
