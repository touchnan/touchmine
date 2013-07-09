/*
 * cn.touchin.page.Pagination.java
 * Feb 12, 2012 
 */
package cn.touchin.page;

import java.util.Iterator;
import java.util.LinkedHashMap;

import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Strings;

import cn.touchin.db.jdbc.SQLParser;
import cn.touchin.page.flexi.FlexiFilter;
import cn.touchin.page.flexi.FlexiRule;
import cn.touchin.page.flexi.FlexiRule.FilterSopt;
import cn.touchin.util.Constants;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Pagination extends Page {
    public static final String FLEXI_SEARCH_TYPE_MUTIL = "flexi";

    // -- 查询条件 --//
    private LinkedHashMap<String, String> conditions;
    private FlexiFilter flexiFilter;// jqgrid条件

    // -- 排序条件 --//
    private Orderby orderby = new Orderby();
    private boolean distinct;

    public Pagination() {
        super();
    }

    /**
     * @param page
     * @param rp
     * @param total
     */
    public Pagination(int page, int rp, Integer total) {
        super(page, rp, total);
    }

    /**
     * @param page
     * @param total
     */
    public Pagination(int page, int total) {
        super(page, total);
    }

    /**
     * @param rp
     */
    public Pagination(int rp) {
        super(rp);
    }

    public Pagination(Integer rp, Integer page, String qtype, String query, String sortname, String sortorder,
            FlexiFilter flexiFilter) {
        this(page, rp);
        this.flexiFilter = flexiFilter;
        if (!Strings.isBlank(qtype) && !Strings.isBlank(query)) {
            if (FLEXI_SEARCH_TYPE_MUTIL.equals(qtype)) {
                String[] pairs = query.split(SQLParser.AND);
                for (int i = 0; i < pairs.length; i++) {
                    String[] ps = pairs[i].split(SQLParser.EQUAL);
                    if (ps.length == 2) {
                        this.addCondition(ps[0], ps[1]);
                        // PropertyFilter p = new PropertyFilter(ps[0], ps[1]);
                    }
                }
            } else {
                this.addCondition(qtype, query);
            }
        }
        if (!Strings.isBlank(sortname) && !Strings.isBlank(sortorder)) {
            Constants.sqlFieldFilter(sortname);
            try {// 带有类型说明的的字段,截取最后的字段
                sortname = new FlexiRule().rule(FilterSopt.bn, sortname, null).getField();
            } catch (Exception e) {// 普通正常字段
                // pass
            }
            this.setOrderby(new Orderby(sortname, sortorder));
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.page.Page#copyFrom(cn.touchin.page.Page)
     */
    @Override
    public Pagination copyFrom(Page src) {
        Pagination result = new Pagination();
        result.copyProperties(src);
        return result;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.page.Page#copyProperties(cn.touchin.page.Page)
     */
    @Override
    protected void copyProperties(Page src) {
        super.copyProperties(src);
        if (src instanceof Pagination) {
            this.setConditions(((Pagination) src).getConditions());
            this.setOrderby(((Pagination) src).getOrderby());
        }
    }

    /**
     * 是否有过滤条件或者排序条件
     * 
     * @return
     */
    public boolean haveConditions() {
        return (conditions != null && conditions.size() > 0) || (orderby != null && orderby.isOrderBySetted());
    }

    /**
     * @return the conditions
     */

    @JSON(serialize = false)
    public LinkedHashMap<String, String> getConditions() {
        return conditions;
    }

    public void addCondition(String filterName, String value) {
        if (null == conditions) {
            conditions = new LinkedHashMap<String, String>();
        }
        conditions.put(filterName, value);
    }

    @JSON(serialize = false)
    public PropertyFilter[] getPropertyFilterConditions() {
        PropertyFilter[] result = null;
        if (null != conditions && !conditions.isEmpty()) {
            result = new PropertyFilter[conditions.size()];
            Iterator<String> itr = conditions.keySet().iterator();
            for (int i = 0; itr.hasNext(); i++) {
                String filterName = itr.next();
                String value = conditions.get(filterName);
                result[i] = new PropertyFilter(filterName, value);
            }
        }
        return result;
    }

    /**
     * @param conditions
     *            the conditions to set
     */
    public void setConditions(LinkedHashMap<String, String> conditions) {
        this.conditions = conditions;
    }

    public boolean hasOrderBy() {
        return getOrderby() != null && getOrderby().isOrderBySetted();
    }

    /**
     * @return the orderby
     */
    @JSON(serialize = false)
    public Orderby getOrderby() {
        return orderby;
    }

    /**
     * @param orderby
     *            the orderby to set
     */
    public void setOrderby(Orderby orderby) {
        this.orderby = orderby;
    }

    /**
     * @return the distinct
     */
    @JSON(serialize = false)
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * @param distinct
     *            the distinct to set
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * @return the flexiFilter
     */
    @JSON(serialize = false)
    public FlexiFilter getFlexiFilter() {
        return flexiFilter;
    }

    /**
     * @param flexiFilter
     *            the flexiFilter to set
     */
    public void setFlexiFilter(FlexiFilter flexiFilter) {
        this.flexiFilter = flexiFilter;
    }

}
