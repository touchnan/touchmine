/*
 * cn.touchin.page.SimpleHQuery.java
 * Feb 12, 2012 
 */
package cn.touchin.page;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.nutz.lang.Lang;
import org.nutz.lang.Strings;
import org.springframework.util.Assert;

import cn.touchin.kit.AliasGenerator;
import cn.touchin.kit.bean.NameValuePair;
import cn.touchin.page.PropertyFilter.MatchType;
import cn.touchin.util.Constants;

/**
 * Feb 12, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class SimpleHQuery {
    private static final String PROPERTY_SPLIT_REG = "\\.";
    private static final String ALIAS_CONNECTOR = "@";
    private static final String DOLLAR_ID = "$id";
    private static final String POINT_ID = ".id";

    private Class<?> entityClass;
    // private Map<String, Object> properties = new HashMap<String, Object>();
    List<NameValuePair> properties = new ArrayList<NameValuePair>();
    private boolean distinct;

    /**
     * 构造器
     * 
     * @param entityClass
     * @param pfs
     *            PropertyFilter若是MultiProperty，不能带id属性，且_OR_后面的属性不能带点，可以： EQI_xxx.YY_OR_ZZ=10（表示xxx.YY=10 or
     *            xxx.ZZ=10），不能：EQI_YY_OR_xxx.ZZ=10
     */
    public SimpleHQuery(Class<?> entityClass, PropertyFilter[] pfs) {
        this.entityClass = entityClass;
        if (null != pfs && pfs.length > 0) {
            for (PropertyFilter pf : pfs) {
                if (pf.isMultiProperty()) {
                    appendMultiProperty(pf);
                } else {
                    appendProperty(pf);
                }
            }
        }
    }

    /**
     * 构造属性(MultiProperty)的别名的数据结构， eg:LIKES_name_OR_loginname=hcq， 最复杂可以支持到：LIKES_roles.users.name_OR_loginname=
     * hcq，但是目前不支持：LIKES_name_OR_roles.users.loginname=hcq，也就是说目前只支持多个属性是同一个表连接的情况。
     * 
     * @param pf
     */
    private void appendMultiProperty(PropertyFilter pf) {
        if (pf.isMultiProperty()) {
            String orKey = parseOrKey(pf);
            List<NameValuePair> orMap = appendMultiFirstProperty(orKey, pf.getPropertyNames()[0], pf);
            for (int i = 1; i < pf.getPropertyNames().length; i++) {
                putAliasVlaue2Map(orMap, pf.getPropertyNames()[i], pf);
            }
        }
    }

    /**
     * 构造OR节点的key
     * 
     * @param pf
     * @return Key
     */
    private String parseOrKey(PropertyFilter pf) {
        if (pf.isMultiProperty()) {
            StringBuffer key = new StringBuffer();
            for (String pName : pf.getPropertyNames()) {
                String[] alias = pName.split(PROPERTY_SPLIT_REG);
                key.append(ALIAS_CONNECTOR).append(alias[alias.length - 1]);
            }
            return key.toString();
        }
        return null;
    }

    /**
     * 构造属性(MultiProperty中的第一个属性)的别名的数据结构
     * 
     * @param orKey
     * @param pName
     * @param pf
     * @return List
     */
    private List<NameValuePair> appendMultiFirstProperty(String orKey, String pName, PropertyFilter pf) {
        String[] alias = pName.split(PROPERTY_SPLIT_REG);
        if (null != alias && alias.length > 0) {
            List<NameValuePair> aliasList = properties;
            for (int i = 0; i < alias.length - 1; i++) {
                aliasList = putAlias2Map(aliasList, alias[i]);
            }

            List<NameValuePair> result = new ArrayList<NameValuePair>();
            aliasList.add(new NameValuePair(orKey, result));

            putAliasVlaue2Map(result, alias[alias.length - 1], pf);
            return result;
        } else {
            List<NameValuePair> result = new ArrayList<NameValuePair>();
            properties.add(new NameValuePair(orKey, result));

            putAliasVlaue2Map(result, pName, pf);
            return result;
        }
    }

    /**
     * 构造查询属性(Property)的别名的数据结构
     * 
     * @param pf
     */
    private void appendProperty(PropertyFilter pf) {
        String pName = pf.getPropertyName();
        int count = Constants.countAlias(pName, Constants.POINT);
        if (count <= 0) {// eg:EQS_name=hcq, or EQS_roles$id=10
            if (pName.endsWith(DOLLAR_ID)) {// eg:EQS_roles$id=10
                List<NameValuePair> aliasList = properties;
                aliasList = putAlias2Map(aliasList, pName.substring(0, pName.length() - DOLLAR_ID.length()));
                putAliasVlaue2Map(aliasList, Constants.ID, pf);
            } else {// eg:EQS_name=hcq
                // properties.put(pName, pf);
                properties.add(new NameValuePair(pName, pf));
            }
        } else if (count == 1 && pName.endsWith(POINT_ID)) {// eg:EQL_category.id=100
            // properties.put(pName, pf);
            properties.add(new NameValuePair(pName, pf));
        } else {
            if (pName.endsWith(POINT_ID)) {// eg:EQL_users.org.id=100
                String[] alias = pName.substring(0, pName.length() - POINT_ID.length()).split(PROPERTY_SPLIT_REG);
                if (null != alias && alias.length > 0) {
                    List<NameValuePair> aliasList = properties;
                    for (int i = 0; i < alias.length - 1; i++) {
                        aliasList = putAlias2Map(aliasList, alias[i]);
                    }
                    putAliasVlaue2Map(aliasList, alias[alias.length - 1] + POINT_ID, pf);
                }
            } else if (pName.endsWith(DOLLAR_ID)) {// eg:EQL_users.roles$id=100
                String[] alias = pName.substring(0, pName.length() - DOLLAR_ID.length()).split(PROPERTY_SPLIT_REG);
                if (null != alias && alias.length > 0) {
                    List<NameValuePair> aliasList = properties;
                    for (int i = 0; i < alias.length; i++) {
                        aliasList = putAlias2Map(aliasList, alias[i]);
                    }
                    putAliasVlaue2Map(aliasList, Constants.ID, pf);
                }
            } else {// eg:EQS_users.roles$name=admin
                String[] alias = pName.split(PROPERTY_SPLIT_REG);
                if (null != alias && alias.length > 0) {
                    List<NameValuePair> aliasList = properties;
                    for (int i = 0; i < alias.length - 1; i++) {
                        aliasList = putAlias2Map(aliasList, alias[i]);
                    }
                    putAliasVlaue2Map(aliasList, alias[alias.length - 1], pf);
                }
            }
        }
    }

    /**
     * 构造查询属性的别名的数据结构的叶子节点
     * 
     * @param aliasList
     * @param alias
     * @param pf
     */
    private void putAliasVlaue2Map(List<NameValuePair> aliasList, String alias, PropertyFilter pf) {
        aliasList.add(new NameValuePair(alias, pf));
    }

    /**
     * 构造查询属性的别名的数据结构
     * 
     * @param aliasList
     * @param alias
     * @return
     */
    @SuppressWarnings("unchecked")
    private List<NameValuePair> putAlias2Map(List<NameValuePair> aliasList, String alias) {
        List<NameValuePair> result = null;
        NameValuePair aliasObj = containsAlias(aliasList, alias);
        if (null != aliasObj) {
            if (null == aliasObj.getValue()) {
                aliasObj.setValue(new ArrayList<NameValuePair>());
            } else {
                if (!(aliasObj.getValue() instanceof List)) {
                    throw new RuntimeException("Alias conflict,please check you search condition.");
                }
            }
            result = (List<NameValuePair>) aliasObj.getValue();
        } else {
            result = new ArrayList<NameValuePair>();
            aliasList.add(new NameValuePair(alias, result));
        }
        return result;
    }

    private NameValuePair containsAlias(List<NameValuePair> aliasList, String alias) {
        if (null != aliasList) {
            for (NameValuePair nv : aliasList) {
                if (nv.getName().equals(alias)) {
                    return nv;
                }
            }
        }
        return null;
    }

    /**
     * @return the entityClass
     */
    public Class<?> getEntityClass() {
        return entityClass;
    }

    /**
     * @param entityClass
     *            the entityClass to set
     */
    public void setEntityClass(Class<?> entityClass) {
        this.entityClass = entityClass;
    }

    /**
     * @return the properties
     */
    public List<NameValuePair> getProperties() {
        return properties;
    }

    /**
     * @param properties
     *            the properties to set
     */
    public void setProperties(List<NameValuePair> properties) {
        this.properties = properties;
    }

    public NameValuePair getProperty(String propertyName) {
        if (null != properties && properties.size() > 0) {
            for (NameValuePair nv : properties) {
                if (nv.getName().equals(propertyName)) {
                    return nv;
                }
            }
        }
        return null;
    }

    /**
     * @return the distinct
     */
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
     * 得到WHERE后面的查询条件sql和变量的值
     * 
     * @param mainAlias
     * @return HSql
     */
    // public HSql toConditionHSql(String mainAlias) {
    // HSql hsql = null;
    // AliasGenerator generator = new AliasGenerator();
    // if (this.properties != null && this.properties.size() > 0) {
    // hsql = new HSql();
    // Iterator<String> itr = this.properties.keySet().iterator();
    // while (itr.hasNext()) {
    // String property = itr.next();
    // Object value = this.properties.get(property);
    // if (value instanceof HashMap) {
    // String joinAlias = generator.generate(property);
    // } else if (value instanceof PropertyFilter) {
    // if (hsql.isBlankSql()) {
    // hsql.appendSql(mainAlias).appendSql(".")
    // .appendSql(property);
    // }
    // hsql.appendSql("").appendSql(mainAlias);
    // }
    // }
    // }
    // return hsql;
    // }
    //
    // public void makeHSql(HSql hsql, AliasGenerator generator,
    // Map<String, Object> peopertyTable, String parentAliasPath,
    // String parentAlias) {
    // if (peopertyTable != null && peopertyTable.size() > 0) {
    // Iterator<String> itr = peopertyTable.keySet().iterator();
    // while (itr.hasNext()) {
    // String property = itr.next();
    // Object value = peopertyTable.get(property);
    // if (value instanceof HashMap) {
    // String aliasPath = Strings.isBlank(parentAliasPath) ? property
    // : (parentAliasPath
    // + AliasGenerator.ALIAS_PATH_SEPERATOR + property);
    // String alias = generator.generate(aliasPath);
    // if (!hsql.isBlankSql()) {
    // hsql.appendSql(" and ");
    // }
    // hsql.appendSql(parentAlias).appendSql(".")
    // .appendSql(property);
    // } else if (value instanceof PropertyFilter) {
    // if (!hsql.isBlankSql()) {
    // hsql.appendSql(" and ");
    // }
    // hsql.appendSql(parentAlias).appendSql(".")
    // .appendSql(property);
    // }
    // }
    // }
    // }
    //
    // public Query createQuery(Session session) {
    // Assert.notNull(session, "Session不能为空。");
    // HSql hsql = toConditionHSql("o");
    // StringBuffer sql = new StringBuffer();
    // sql.append("FROM ").append(getEntityClass().getName()).append(" o ");
    // if (hsql.getSql().length() > 0) {
    // sql.append(" WHERE ").append(hsql.getSql());
    // }
    // return null;
    // }

    /**
     * 构造Criteria对象
     * 
     * @param session
     * @return Criteria
     */
    public Criteria createCriteria(Session session) {
        Assert.notNull(session, "Session不能为空。");
        Criteria criteria = session.createCriteria(getEntityClass());
        AliasGenerator generator = new AliasGenerator();
        createHCriteria(criteria, null, null, this.properties, generator);
        if (isDistinct()) {
            criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        }
        return criteria;
    }

    /**
     * 构造Criteria对象，自动创建表连接
     * 
     * @param c
     * @param parentAliasPath
     * @param parentAlias
     * @param peopertyMap
     * @param generator
     */
    @SuppressWarnings("unchecked")
    public void createHCriteria(Criteria c, String parentAliasPath, String parentAlias,
            List<NameValuePair> peopertyMap, AliasGenerator generator) {
        Criteria criteria = c;
        if (peopertyMap != null && peopertyMap.size() > 0) {
            Iterator<NameValuePair> pitr = peopertyMap.iterator();
            while (pitr.hasNext()) {
                NameValuePair pair = pitr.next();
                String property = pair.getName();
                Object value = pair.getValue();
                if (value instanceof PropertyFilter) {
                    PropertyFilter pf = (PropertyFilter) value;
                    Criterion criterion =
                            buildPropertyFilterCriterion(parentAlias, property, pf.getPropertyValue(),
                                    pf.getMatchType());
                    criteria.add(criterion);
                } else if (value instanceof List) {
                    if (property.startsWith(ALIAS_CONNECTOR)) {
                        Disjunction disjunction =
                                createDisjunction(parentAliasPath, parentAlias, (List<NameValuePair>) value, generator);
                        criteria.add(disjunction);
                    } else {
                        String aliasPath =
                                Strings.isBlank(parentAliasPath) ? property : (parentAliasPath
                                        + AliasGenerator.ALIAS_PATH_SEPERATOR + property);
                        String alias = generator.generate(aliasPath);
                        String joinProperty =
                                Strings.isBlank(parentAlias) ? property : (parentAlias + Constants.POINT + property);
                        criteria = criteria.createAlias(joinProperty, alias);
                        createHCriteria(criteria, aliasPath, alias, (List<NameValuePair>) value, generator);
                    }
                }
            }
        }
    }

    /**
     * 创建MultiProperty条件之间的or查询对象
     * 
     * @param parentAliasPath
     * @param parentAlias
     * @param peopertyMap
     * @param generator
     * @return Disjunction
     */
    private Disjunction createDisjunction(String parentAliasPath, String parentAlias, List<NameValuePair> peopertyMap,
            AliasGenerator generator) {
        Disjunction disjunction = null;
        if (peopertyMap != null && peopertyMap.size() > 0) {
            disjunction = Restrictions.disjunction();

            Iterator<NameValuePair> pitr = peopertyMap.iterator();
            while (pitr.hasNext()) {
                NameValuePair nv = pitr.next();
                String property = nv.getName();
                Object value = nv.getValue();
                if (value instanceof PropertyFilter) {
                    PropertyFilter pf = (PropertyFilter) value;
                    Criterion criterion =
                            buildPropertyFilterCriterion(parentAlias, property, pf.getPropertyValue(),
                                    pf.getMatchType());
                    disjunction.add(criterion);
                }
            }
        }
        return disjunction;
    }

    /**
     * 按属性条件创建Criterion,辅助函数.
     * 
     * @param alias
     *            别名
     * @param propertyName
     *            属性名
     * @param propertyValue
     *            属性的值
     * @param matchType
     *            匹配方式
     * @return Criterion
     */
    protected Criterion buildPropertyFilterCriterion(String alias, String propertyName, Object propertyValue,
            MatchType matchType) {
        String aliasProName = Strings.isBlank(alias) ? propertyName : (alias + Constants.POINT + propertyName);
        Criterion criterion = null;
        try {
            // 根据MatchType构造criterion
            if (MatchType.EQ.equals(matchType)) {
                criterion = Restrictions.eq(aliasProName, propertyValue);
            } else if (MatchType.NC.equals(matchType)) {
                criterion =
                        Restrictions.not(Restrictions.like(aliasProName, (String) propertyValue, MatchMode.ANYWHERE));
            } else if (MatchType.CN.equals(matchType)) {
                criterion = Restrictions.like(aliasProName, (String) propertyValue, MatchMode.ANYWHERE);
            } else if (MatchType.LE.equals(matchType)) {
                criterion = Restrictions.le(aliasProName, propertyValue);
            } else if (MatchType.LT.equals(matchType)) {
                criterion = Restrictions.lt(aliasProName, propertyValue);
            } else if (MatchType.GE.equals(matchType)) {
                criterion = Restrictions.ge(aliasProName, propertyValue);
            } else if (MatchType.GT.equals(matchType)) {
                criterion = Restrictions.gt(aliasProName, propertyValue);
            } else if (MatchType.NE.equals(matchType)) {
                criterion = Restrictions.ne(aliasProName, propertyValue);
            } else if (MatchType.NU.equals(matchType)) {
                criterion = Restrictions.isNull(aliasProName);
            } else if (MatchType.NN.equals(matchType)) {
                criterion = Restrictions.isNotNull(aliasProName);
            } else if (MatchType.IN.equals(matchType)) {
                criterion = buildInCriterion(aliasProName, propertyValue, matchType);
            } else if (MatchType.NI.equals(matchType)) {
                criterion = Restrictions.not(buildInCriterion(aliasProName, propertyValue, MatchType.IN));
            }
        } catch (Throwable e) {
            throw new RuntimeException(Lang.unwrapThrow(e));
        }
        return criterion;
    }

    /**
     * 生成IN条件
     * 
     * @param inPropertyName
     *            属性名
     * @param propertyValue
     *            属性的值
     * @param matchType
     *            匹配方式
     * @return Criterion
     */
    private Criterion buildInCriterion(String inPropertyName, Object propertyValue, MatchType matchType) {
        Criterion criterion = null;
        if (MatchType.IN.equals(matchType)) {
            if (propertyValue instanceof Collection<?>) {
                criterion = Restrictions.in(inPropertyName, (Collection<?>) propertyValue);
            } else if (propertyValue instanceof Object[]) {
                criterion = Restrictions.in(inPropertyName, (Object[]) propertyValue);
            } else {
                criterion = Restrictions.in(inPropertyName, new Object[] { propertyValue });
            }
        }
        return criterion;
    }



}
