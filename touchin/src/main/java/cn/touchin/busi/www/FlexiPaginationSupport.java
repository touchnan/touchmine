/*
 * cn.touchin.busi.www.FlexiPaginationSupport.java
 * Mar 6, 2012 
 */
package cn.touchin.busi.www;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import org.nutz.lang.Strings;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touch.kit.json.JsonKit;
import cn.touchin.page.Pagination;
import cn.touchin.page.flexi.FlexiFilter;
import cn.touchin.util.Constants;

/**
 * Mar 6, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FlexiPaginationSupport extends BaseAction {
    private static final long serialVersionUID = -4585837179557559202L;
    
    @Autowired
    private JsonKit jsonKit;
    
    private FlexiFilter filters;// jqgrid条件

    private int rp;// 每页有多少条
    private int page;// 页码

    private String qtype;// 查询字段
    private String query;// 查询字段值

    private String sortname;// 排序字段
    private String sortorder;// 长降序(升序还是降序)

    private Pagination pageData;

    /**
     * 组装分页查询信息
     * 
     * @return
     */
    protected Pagination assemblePaginationInfo() {
        return new Pagination(rp, page, qtype, query, sortname, sortorder, filters);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    /**
     * @return the rp
     */
    public int getRp() {
        return rp;
    }

    /**
     * @param rp
     *            the rp to set
     */
    public void setRp(int rp) {
        this.rp = rp;
    }

    /**
     * @return the page
     */
    public int getPage() {
        return page;
    }

    /**
     * @param page
     *            the page to set
     */
    public void setPage(int page) {
        this.page = page;
    }

    /**
     * @return the qtype
     */
    public String getQtype() {
        return qtype;
    }

    /**
     * @param qtype
     *            the qtype to set
     */
    public void setQtype(String qtype) {
        this.qtype = qtype;
    }

    /**
     * @return the query
     */
    public String getQuery() {
        return query;
    }

    /**
     * @param query
     *            the query to set
     */
    public void setQuery(String query) {
        try {
            this.query = URLDecoder.decode(query, Constants.DEFAULT_ENCODING);
        } catch (UnsupportedEncodingException e) {
            Logs.getLog(FlexiPaginationSupport.class).error("FlexPagination查询条件[query]解码出错", e);
            this.query = query;
        }

    }

    /**
     * @return the sortname
     */
    public String getSortname() {
        return sortname;
    }

    /**
     * @param sortname
     *            the sortname to set
     */
    public void setSortname(String sortname) {
        this.sortname = sortname;
    }

    /**
     * @return the sortorder
     */
    public String getSortorder() {
        return sortorder;
    }

    /**
     * @param sortorder
     *            the sortorder to set
     */
    public void setSortorder(String sortorder) {
        this.sortorder = sortorder;
    }

    /**
     * @return the pageData
     */
    public Pagination getPageData() {
        return pageData;
    }

    /**
     * @param pageData
     *            the pageData to set
     */
    public void setPageData(Pagination pageData) {
        this.pageData = pageData;
    }

    /**
     * @return the filters
     */
    public FlexiFilter getFilters() {
        return filters;
    }

    /**
     * 设置jqGrid查询条件
     * 
     * @param filters
     */
    public void setFilters(String filters) {
        if (!Strings.isBlank(filters)) {
            this.filters = jsonKit.parse(FlexiFilter.class, filters);
        }
    }

}
