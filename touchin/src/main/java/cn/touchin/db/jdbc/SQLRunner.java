/*
 * cn.touchin.db.jdbc.SQLRunner.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

import cn.touchin.page.Pagination;


/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SQLRunner {
    protected String[] columns = null;
    protected String[] tabs = null;
    protected String[] conditions = null;
    protected String[] orders = null;
    protected String[] groups = null;
    protected Object[] params = null;

    protected Pagination flexiPage;

    public SQLRunner(Pagination flexiPage) {
        super();
        this.flexiPage = flexiPage;
    }

    public SQLRunner select(String... columns) {
        this.columns = columns;
        return this;
    }

    public SQLRunner from(String... tabs) {
        this.tabs = tabs;
        return this;
    }

    public SQLRunner where(String... conditions) {
        this.conditions = conditions;
        return this;
    }

    public SQLRunner orderBy(String... orders) {
        this.orders = orders;
        return this;
    }

    public SQLRunner groupBy(String... groups) {
        this.groups = groups;
        return this;
    }

    public SQLRunner setParameters(Object... params) {
        this.params = params;
        return this;
    }
}
