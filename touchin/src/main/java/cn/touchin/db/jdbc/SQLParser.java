/*
 * cn.touchin.db.jdbc.SQLParser.java
 * Mar 11, 2012 
 */
package cn.touchin.db.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

import cn.touchin.page.Orderby;
import cn.touchin.page.PropertyFilter;
import cn.touchin.page.PropertyFilter.MatchType;

/**
 * Mar 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class SQLParser implements ISQLParser {
    public static final String EQUAL = "=";
    public static final String AND = "&";

    protected List<Object> createPageSql(SQLRunner runner, StringBuffer sql, StringBuffer countSql) {
        return create(runner, sql, true, countSql);
    }

    protected List<Object> createSql(SQLRunner runner, StringBuffer sql) {
        return create(runner, sql, false, null);
    }

    protected List<Object> create(SQLRunner runner, StringBuffer sql, boolean count, StringBuffer countSql) {
        List<Object> params = new ArrayList<Object>(10);

        if (runner.columns != null) {
            sql = append(sql, runner.columns);
        }

        StringBuffer _sql = new StringBuffer(" FROM");

        if (runner.tabs != null) {
            _sql = append(_sql, runner.tabs);
        }

        if (runner.conditions != null
                || (runner.flexiPage.getConditions() != null && !runner.flexiPage.getConditions().isEmpty())
                || (runner.flexiPage.getFlexiFilter() != null && runner.flexiPage.getFlexiFilter().isNotBlank())) {
            _sql.append(" WHERE");
            if (runner.conditions != null) {
                _sql = append(_sql, runner.conditions);
            } else {
                _sql.append(" 1=1");
            }

            if (runner.params != null) {
                for (Object obj : runner.params) {
                    params.add(obj);
                }
            }

            PropertyFilter[] pfs = runner.flexiPage.getPropertyFilterConditions();
            if (pfs != null) {
                for (PropertyFilter p : pfs) {
                    if (p.getMatchType() == MatchType.NN || p.getMatchType() == MatchType.NU) {
                        _sql.append(" AND ").append(p.getPropertyName()).append(" ").append(p.getMatchTypeSymbol());
                    } else {
                        _sql.append(" AND ").append(p.getPropertyName()).append(" ").append(p.getMatchTypeSymbol())
                                .append(" ?");
                        params.add(wrapValue(p));
                    }
                }
            }

            if (runner.flexiPage.getFlexiFilter() != null && runner.flexiPage.getFlexiFilter().isNotBlank()) {
                runner.flexiPage.getFlexiFilter().filterAnd(_sql, params);
            }
        }

        if (runner.groups != null) {
            _sql.append(" GROUP BY");
            _sql = append(_sql, runner.groups);
        }

        if (count) {// 统计sql连接完成
            countSql.append(_sql);
        }

        if (runner.orders != null || runner.flexiPage.hasOrderBy()) {
            _sql.append(" ORDER BY");
            if (runner.orders != null) {
                _sql = append(_sql, runner.orders);
            }

            if (runner.flexiPage.hasOrderBy()) {
                Orderby orderby = runner.flexiPage.getOrderby();
                if (orderby != null && orderby.isOrderBySetted()) {
                    String[] sidxs = StringUtils.split(orderby.getSortname(), ',');
                    String[] sords = StringUtils.split(orderby.getSortorder(), ',');

                    int maxSordLen = sords.length;
                    Assert.isTrue(sidxs.length == sords.length, "分页多重排序参数中,排序字段不能小于升降序的个数");

                    for (int i = 0; i < sidxs.length; i++) {
                        if (i != 0 || (i == 0 && runner.orders != null)) {
                            // 不是第一个或者是第一个但前面已经有排序字段
                            _sql.append(" ,");
                        }
                        _sql.append(" ").append(sidxs[i]);// 排序字段
                        if (i < maxSordLen) {
                            _sql.append(" ").append(sords[i]);// 升降序
                        }
                    }
                }
            }
        }

        // SQL连接完成
        sql.append(_sql);

        return params;
    }

    private Object wrapValue(PropertyFilter p) {
        return p.getMatchType() == MatchType.CN ? "%" + p.getPropertyValue() + "%" : p.getPropertyValue();
    }

    private StringBuffer append(StringBuffer sql, String... params) {
        for (int i = 0; i < params.length; i++) {
            sql.append(i == 0 ? " " : ",").append(params[i]);
        }
        return sql;
    }
}
