/*
 * cn.touchin.page.flexi.FlexiFilter.java
 * Aug 3, 2012 
 */
package cn.touchin.page.flexi;

import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.util.Assert;

/**
 * Aug 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
/**
 * Aug 4, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FlexiFilter {
    public static final String NAME_GROUPOP_AND = "AND";
    public static final String NAME_GROUPOP_OR = "OR";

    private String groupOp;// 组连接符
    private List<FlexiRule> rules;
    private List<FlexiFilter> groups;//

    public boolean isBlank() {
        return !isNotBlank();
    }

    public boolean isNotBlank() {
        return (rules != null && !rules.isEmpty()) || (groups != null && !groups.isEmpty());
    }

    /**
     * 组装过滤条件到Criteria
     * 
     * @param c
     */
    public void filter(Criteria c) {
        Criterion criterion = filter();
        if (criterion != null) {
            c.add(criterion);
        }
    }

    /**
     * 组装过滤条件
     * 
     * @return
     */
    public Criterion filter() {
        Criterion criterion = null;
        if (this.rules != null) {
            criterion = filterRule(criterion);
        }
        if (this.groups != null) {
            criterion = filterGroup(criterion);
        }
        return criterion;
    }

    /**
     * 组装过滤规则
     * 
     * @param criterion
     * @return
     */
    public Criterion filterRule(Criterion criterion) {
        for (Iterator<FlexiRule> iter = this.rules.iterator(); iter.hasNext();) {
            FlexiRule rule = iter.next();
            if (criterion == null) {
                criterion = rule.flip().criterion();
            } else {
                if (NAME_GROUPOP_AND.equalsIgnoreCase(this.groupOp)) {
                    criterion = Restrictions.and(criterion, rule.flip().criterion());
                } else if (NAME_GROUPOP_OR.equalsIgnoreCase(this.groupOp)) {
                    criterion = Restrictions.or(criterion, rule.flip().criterion());
                }
            }
        }
        return criterion;
    }

    /**
     * 组装过滤组
     * 
     * @param criterion
     * @return
     */
    public Criterion filterGroup(Criterion criterion) {
        if (NAME_GROUPOP_AND.equalsIgnoreCase(this.groupOp)) {
            for (Iterator<FlexiFilter> iter = this.groups.iterator(); iter.hasNext();) {
                if (criterion == null) {
                    criterion = iter.next().filter();
                } else {
                    criterion = Restrictions.and(criterion, iter.next().filter());
                }
            }
        } else if (NAME_GROUPOP_OR.equalsIgnoreCase(this.groupOp)) {
            for (Iterator<FlexiFilter> iter = this.groups.iterator(); iter.hasNext();) {
                if (criterion == null) {
                    criterion = iter.next().filter();
                } else {
                    criterion = Restrictions.or(criterion, iter.next().filter());
                }
            }
        }
        return criterion;
    }

    /**
     * 组装过滤条件，并以and连接返回
     * 
     * @param sql
     * @param params
     */
    public void filterAnd(StringBuffer sql, List<Object> params) {
        sql.append(" AND");
        filter(sql, params);
    }

    /**
     * 组装过滤条件，并以or连接返回
     * 
     * @param sql
     * @param params
     */
    public void filterOr(StringBuffer sql, List<Object> params) {
        sql.append(" OR");
        filter(sql, params);
    }

    /**
     * 组装过滤条件
     * 
     * @param sql
     * @param params
     */
    private void filter(StringBuffer sql, List<Object> params) {
        sql.append(" (");
        int count = 0;
        if (rules != null) {
            count = filterRule(sql, params, count);
        }

        if (groups != null) {
            count = filterGroup(sql, params, count);
        }

        sql.append(")");
    }

    /**
     * 组装过滤规则
     * 
     * @param sql
     * @param params
     * @param count
     * @return
     */
    private int filterRule(StringBuffer sql, List<Object> params, int count) {
        for (Iterator<FlexiRule> iter = this.rules.iterator(); iter.hasNext(); count++) {
            FlexiRule rule = iter.next();
            if (count != 0) {
                sql.append(" ").append(this.groupOp);
            }
            rule.flip().sql(sql, params);
        }
        return count;
    }

    /**
     * 组装过滤组
     * 
     * @param sql
     * @param params
     * @param count
     * @return
     */
    private int filterGroup(StringBuffer sql, List<Object> params, int count) {
        if (NAME_GROUPOP_AND.equalsIgnoreCase(this.groupOp)) {
            for (Iterator<FlexiFilter> iter = this.groups.iterator(); iter.hasNext(); count++) {
                FlexiFilter filter = iter.next();
                if (count != 0) {
                    filter.filterAnd(sql, params);
                } else {
                    filter.filter(sql, params);
                }
            }
        } else if (NAME_GROUPOP_OR.equalsIgnoreCase(this.groupOp)) {
            for (Iterator<FlexiFilter> iter = this.groups.iterator(); iter.hasNext(); count++) {
                FlexiFilter filter = iter.next();
                if (count != 0) {
                    filter.filterOr(sql, params);
                } else {
                    filter.filter(sql, params);
                }
            }
        }
        return count;
    }

    /**
     * @param groupOp
     *            the groupOp to set
     */
    public void setGroupOp(String groupOp) {
        Assert.isTrue(NAME_GROUPOP_AND.equalsIgnoreCase(groupOp) || NAME_GROUPOP_OR.equalsIgnoreCase(groupOp), "无效的连接符");
        this.groupOp = groupOp;
    }

    /**
     * @param rules
     *            the rules to set
     */
    public void setRules(List<FlexiRule> rules) {
        this.rules = rules;
    }

    /**
     * @param groups
     *            the groups to set
     */
    public void setGroups(List<FlexiFilter> groups) {
        this.groups = groups;
    }
}
