/*
 * cn.touchin.db.hibernate.HibernateDao.java
 * Feb 12, 2012 
 */
package cn.touchin.db.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.impl.CriteriaImpl;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.transform.ResultTransformer;
import org.nutz.castor.Castors;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import cn.touchin.db.BaseDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.kit.SqlUtil;
import cn.touchin.page.Orderby;
import cn.touchin.page.Page;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;
import cn.touchin.page.PropertyFilter.MatchType;
import cn.touchin.page.SimpleHQuery;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class HibernateDao<E extends Object, PK extends Serializable> extends BaseDao<E, PK>  implements
        IHibernateDao<E, PK> {
    protected final Log log = Logs.getLog(getClass());
    private static final String ORDER_ENTRIES = "orderEntries";
    

    /**
     * 用于Dao层子类使用的构造函数. 通过子类的泛型定义取得对象类型Class.
     * 
     * eg. public class UserDao extends HibernateDao<User, Long>
     */
    public HibernateDao() {
        super();
    }

    /**
     * 采用@Autowired按类型注入SessionFactory
     */
    @Autowired
    public void setSessionFactoryOverride(final SessionFactory sessionFactory) {
        super.setSessionFactory(sessionFactory);
    }

    /**
     * 取得当前currSession.
     * 
     * @return currSession
     */
    public Session currSession() {
        return getSessionFactory().getCurrentSession();
    }

    @Override
    public void delete(AppRequest appRequest, E entity) {
        currSession().delete(entity);
    }

    @Override
    public void delete(AppRequest appRequest, PK id) {
        E entity = fetch(appRequest, id);
        currSession().delete(entity);
    }

    @Override
    public void deleteAll(AppRequest appRequest) {
        deleteEntities(appRequest, findAll(appRequest));
    }

    @SuppressWarnings("unchecked")
    @Override
    public E fetch(AppRequest appRequest, PK id) {
        E entity = (E) currSession().load(getEntityClass(), id);
        initEntity(entity);
        return entity;
    }

    @Override
    public E fetch(AppRequest appRequest, PropertyFilter... filters) {
        return fetch(appRequest, false, filters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public E fetch(AppRequest appRequest, boolean distinct, PropertyFilter... filters) {
        SimpleHQuery shq = new SimpleHQuery(getEntityClass(), filters);
        shq.setDistinct(distinct);
        Criteria c = shq.createCriteria(currSession());
        return (E) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findByIds(AppRequest appRequest, PK[] ids) {
        Criteria c = createCriteria(getEntityClass(), Restrictions.in(getIdName(), ids));
        return c.list();
    }

    @Override
    public List<E> find(AppRequest appRequest, PropertyFilter[] filters) {
        return find(appRequest, false, filters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> find(AppRequest appRequest, boolean distinct, PropertyFilter[] filters) {
        SimpleHQuery shq = new SimpleHQuery(getEntityClass(), filters);
        shq.setDistinct(distinct);
        Criteria c = shq.createCriteria(currSession());
        return c.list();
    }

    @Override
    public List<E> find(AppRequest appRequest, Orderby orderby, PropertyFilter[] filters) {
        return find(appRequest, orderby, false, filters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> find(AppRequest appRequest, Orderby orderby, boolean distinct, PropertyFilter[] filters) {
        SimpleHQuery shq = new SimpleHQuery(getEntityClass(), filters);
        shq.setDistinct(distinct);
        Criteria c = shq.createCriteria(currSession());
        setOrderby(c, orderby);
        return c.list();
    }

    @Override
    public Pagination findPage(AppRequest appRequest, Pagination page) {
        SimpleHQuery shq = new SimpleHQuery(getEntityClass(), page.getPropertyFilterConditions());
        shq.setDistinct(page.isDistinct());
        Criteria c = shq.createCriteria(currSession());

        if (page.getFlexiFilter() != null) {
            page.getFlexiFilter().filter(c);
        }

        if (page.isAutoCount()) {
            int totalCount = countCriteriaResult(c);
            page.setTotal(totalCount);
        }
        setOrderby(c, page.getOrderby());
        setPageParameter(c, page);
        page.setRows(c.list());
        return page;
    }

    /**
     * 设置分页参数到Query对象,辅助函数.
     * 
     * @param q
     * @param page
     * @return Query
     */
    protected Query setPageParameter(final Query q, final Pagination page) {
        // hibernate的firstResult的序号从0开始
        q.setFirstResult(page.getFirst() - 1);
        q.setMaxResults(page.getRp());
        return q;
    }

    /**
     * 设置分页参数到Criteria对象,辅助函数.hibernate的firstResult的序号从0开始
     * 
     * @param c
     * @param page
     * @return Criteria
     */
    protected Criteria setPageParameter(final Criteria c, final Page page) {
        c.setFirstResult(page.getFirst() - 1);
        c.setMaxResults(page.getRp());
        return c;
    }

    /**
     * 设置排序条件
     * 
     * @param c
     * @param orderby
     */
    protected void setOrderby(Criteria c, Orderby orderby) {
        Criteria result = c;
        if (result == null) {
            result = currSession().createCriteria(getEntityClass());
        }
        if (orderby != null && orderby.isOrderBySetted()) {
            String[] orderByArray = StringUtils.split(orderby.getSortname(), ',');
            String[] orderArray = StringUtils.split(orderby.getSortorder(), ',');

            Assert.isTrue(orderByArray.length == orderArray.length, "分页多重排序参数中,排序字段与升降序的个数不相等");

            for (int i = 0; i < orderByArray.length; i++) {
                if (Orderby.ASC.equals(orderArray[i])) {
                    c.addOrder(Order.asc(orderByArray[i]));
                } else if (Orderby.DESC.equals(orderArray[i])) {
                    c.addOrder(Order.desc(orderByArray[i]));
                }
            }
        }
    }

    /**
     * 执行count查询获得本次Criteria查询所能获得的对象总数.
     * 
     * @param c
     * @return count value
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected int countCriteriaResult(final Criteria c) {
        CriteriaImpl impl = (CriteriaImpl) c;

        // 先把Projection、ResultTransformer、OrderBy取出来,清空三者后再执行Count操作
        Projection projection = impl.getProjection();
        ResultTransformer transformer = impl.getResultTransformer();

        List<CriteriaImpl.OrderEntry> orderEntries = null;
        Mirror<?> me = Mirror.me(impl.getClass());
        try {
            orderEntries = (List) me.getValue(impl, ORDER_ENTRIES);
            me.setValue(impl, ORDER_ENTRIES, new ArrayList<CriteriaImpl.OrderEntry>());
        } catch (Throwable e) {
            log.error(e.getMessage());
        }

        // 执行Count查询
        Long totalCount = (Long) c.setProjection(Projections.rowCount()).uniqueResult();

        // 将之前的Projection,ResultTransformer和OrderBy条件重新设回去
        c.setProjection(projection);

        if (projection == null) {
            c.setResultTransformer(CriteriaSpecification.ROOT_ENTITY);
        }
        if (transformer != null) {
            c.setResultTransformer(transformer);
        }
        try {
            me.setValue(impl, ORDER_ENTRIES, orderEntries);
        } catch (Throwable e) {
            log.error(e.getMessage());
        }
        return totalCount.intValue();
    }

    @Override
    public List<E> findAll(AppRequest appRequest) {
        return findAll(appRequest, null);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<E> findAll(AppRequest appRequest, Orderby orderby) {
        Criteria c = currSession().createCriteria(getEntityClass());
        setOrderby(c, orderby);
        return c.list();
    }

    @Override
    public E insert(AppRequest appRequest, E entity) {
        currSession().save(entity);
        return entity;
    }

    @Override
    public boolean isPropertyUnique(AppRequest appRequest, final String propertyName, final Object newValue,
            final Object oldValue) {
        if (newValue == null || newValue.equals(oldValue)) {
            return true;
        }
        PropertyFilter filter = new PropertyFilter().init(propertyName, newValue, MatchType.EQ);

        // Object object = fetch(filter, appRequest);
        List<E> list = find(appRequest, new PropertyFilter[] { filter });
        return list == null || list.isEmpty();
    }

    @Override
    public E update(AppRequest appRequest, E entity) {
        currSession().update(entity);
        return entity;
    }

    /**
     * {@inheritDoc}
     * 
     * @see cn.touchin.db.BaseDao#getIdName()
     */
    @Override
    public String getIdName() {
        ClassMetadata meta = getSessionFactory().getClassMetadata(getEntityClass());
        return meta.getIdentifierPropertyName();
    }

    /**
     * 按Criteria查询对象列表.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param criterions
     *            数量可变的Criterion.
     */
    @SuppressWarnings("unchecked")
    public List<E> find(AppRequest appRequest, final Criterion... criterions) {
        return createCriteria(getEntityClass(), criterions).list();
    }

    /**
     * 按Criteria查询唯一对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param criterions
     *            数量可变的Criterion.
     */
    @SuppressWarnings("unchecked")
    public E fetch(AppRequest appRequest, final Criterion... criterions) {
        return (E) createCriteria(getEntityClass(), criterions).uniqueResult();
    }

    /**
     * 按Criteria查询对象列表.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param orderby
     * @param criterions
     *            数量可变的Criterion.
     */
    @SuppressWarnings("unchecked")
    public List<E> find(AppRequest appRequest, final Orderby orderby, final Criterion... criterions) {
        return createCriteria(getEntityClass(), orderby, criterions).list();
    }

    /**
     * 按Criteria查询唯一对象.
     * 
     * @param appRequest
     *            应用请求方的信息
     * @param criterions
     *            数量可变的Criterion.
     */
    @SuppressWarnings("unchecked")
    public E fetch(AppRequest appRequest, final Orderby orderby, final Criterion... criterions) {
        return (E) createCriteria(getEntityClass(), orderby, criterions).uniqueResult();
    }

    /**
     * 根据Criterion条件创建Criteria.
     * 
     * 本类封装的find()函数全部默认返回对象类型为T,当不为T时使用本函数.
     * 
     * @param criterions
     *            数量可变的Criterion.
     */
    public Criteria createCriteria(Class<?> classOfE, final Criterion... criterions) {
        Criteria criteria = currSession().createCriteria(classOfE);
        if (null != criterions && criterions.length > 0) {
            for (Criterion c : criterions) {
                criteria.add(c);
            }
        }
        return criteria;
    }

    /**
     * 根据Criterion条件和排序条件创建Criteria.
     * 
     * @param orderby
     *            排序条件
     * @param criterions
     *            数量可变的Criterion.
     * @return Criteria
     */
    public Criteria createCriteria(Class<?> classOfE, final Orderby orderby, final Criterion[] criterions) {
        Criteria criteria = createCriteria(classOfE, criterions);
        setOrderby(criteria, orderby);
        return criteria;
    }

    /**
     * 初始化对象. 使用load()方法得到的仅是对象Proxy, 在传到View层前需要进行初始化.
     * 只初始化entity的直接属性,但不会初始化延迟加载的关联集合和属性. 如需初始化关联属性,可实现新的函数,执行:
     * Hibernate.initialize(user.getRoles())，初始化User的直接属性和关联集合.
     * Hibernate.initialize
     * (user.getDescription())，初始化User的直接属性和延迟加载的Description属性.
     * 
     * @param entity
     */
    public void initEntity(Object entity) {
        Hibernate.initialize(entity);
    }

    /**
     * 
     * @param entityList
     */
    public void initEntity(List<E> entityList) {
        for (E entity : entityList) {
            Hibernate.initialize(entity);
        }
    }

    /**
     * Flush当前Session.
     */
    public void flush() {
        currSession().flush();
    }

    /**
     * 为Query添加distinct transformer.
     * 
     * @param query
     * @return Query
     */
    public Query distinct(Query query) {
        query.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return query;
    }

    /**
     * 为Criteria添加distinct transformer.
     * 
     * @param criteria
     * @return Criteria
     */
    public Criteria distinct(Criteria criteria) {
        criteria.setResultTransformer(CriteriaSpecification.DISTINCT_ROOT_ENTITY);
        return criteria;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.IDao#queryForInt(cn.touchin.dto.AppRequest,
     * java.lang.String, java.lang.Object[])
     */
    @Override
    public Integer queryForInt(AppRequest appRequest, String sql, Object[] args) {
        Object result = createQuery(sql, args).uniqueResult();
        return Castors.me().castTo(result, Integer.class);
    }

    public Integer queryForInt(AppRequest appRequest, final String hql, final Map<String, ?> values) {
        Object result = createQuery(hql, values).uniqueResult();
        return Castors.me().castTo(result, Integer.class);
    }

    @Override
    public Long queryForLong(AppRequest appRequest, String sql, Object[] args) {
        Object result = createQuery(sql, args).uniqueResult();
        return Castors.me().castTo(result, Long.class);
    }

    public Long queryForLong(AppRequest appRequest, String sql, Map<String, ?> values) {
        Object result = createQuery(sql, values).uniqueResult();
        return Castors.me().castTo(result, Long.class);
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * @param hql
     * @param values
     *            数量可变的参数,按顺序绑定.
     */
    public Query createQuery(final String hql, final Object... values) {
        Query query = currSession().createQuery(hql);
        if (values != null) {
            for (int i = 0; i < values.length; i++) {
                query.setParameter(i, values[i]);
            }
        }
        return query;
    }

    /**
     * 根据查询HQL与参数列表创建Query对象.
     * 
     * @param hql
     * @param values
     *            命名参数,按名称绑定.
     */
    public Query createQuery(final String hql, final Map<String, ?> values) {
        Query query = currSession().createQuery(hql);
        if (values != null && values.size() > 0) {
            query.setProperties(values);
        }
        return query;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.IDao#saveOrUpdate(cn.touchin.dto.AppRequest,
     * java.lang.Object)
     */
    @Override
    public E saveOrUpdate(AppRequest appRequest, E entity) {
        getHibernateTemplate().saveOrUpdate(entity);
        return entity;
    }    
    

    @Override
    public int batchExecute(AppRequest appRequest, final String hql, final Object... values) {
        return createQuery(hql, values).executeUpdate();
    }

    @Override
    public int batchExecute(AppRequest appRequest, final String hql, final Map<String, ?> values) {
        return createQuery(hql, values).executeUpdate();
    }

    @Override
    public void delete(AppRequest appRequest, Class<? extends Object> classOfX, Serializable id) {
        Object Object = fetch(appRequest, classOfX, id);
        if (null != Object) {
            currSession().delete(Object);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X fetch(AppRequest appRequest, final String hql, final Object... values) {
        return (X) createQuery(hql, values).uniqueResult();
    }

    /* (non-Javadoc)
     * @see cn.touchin.db.hibernate.IHibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.String, java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public <X> X fetch(AppRequest appRequest, final String hql, final Map<String, ?> values) {
        return (X) createQuery(hql, values).uniqueResult();
    }

    @SuppressWarnings("unchecked")
    public <X> X fetch(AppRequest appRequest, Criteria c) {
        return (X) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> X fetch(AppRequest appRequest, Class<X> classOfX, Serializable id) {
        X Object = (X) currSession().load(classOfX, id);
        initEntity(Object);
        return Object;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.db.hibernate.IHibernateDao#fetch(cn.touchin.dto.AppRequest, java.lang.Class,
     * cn.touchin.page.PropertyFilter[])
     */
    @SuppressWarnings("unchecked")
    @Override
    public <X> X fetch(AppRequest appRequest, Class<X> classOfX, PropertyFilter... filters) {
        SimpleHQuery shq = new SimpleHQuery(classOfX, filters);
        Criteria c = shq.createCriteria(currSession());

        return (X) c.uniqueResult();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> find(AppRequest appRequest, final String hql, final Object... values) {
        return createQuery(hql, values).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> find(AppRequest appRequest, final String hql, final Map<String, ?> values) {
        return createQuery(hql, values).list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> findByIds(AppRequest appRequest, Class<X> classOfX, Serializable[] ids) {
        Criteria c = createCriteria(classOfX, Restrictions.in(getIdName(), ids));
        return c.list();
    }

    @Override
    public <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, PropertyFilter... filters) {
        return find(appRequest, classOfX, orderby, false, filters);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> find(AppRequest appRequest, Class<X> classOfX, Orderby orderby, boolean distinct,
            PropertyFilter... filters) {
        SimpleHQuery shq = new SimpleHQuery(classOfX, filters);
        shq.setDistinct(distinct);
        Criteria c = shq.createCriteria(currSession());
        setOrderby(c, orderby);
        return c.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> find(AppRequest appRequest, Criteria c) {
        return c.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <X> List<X> findAll(AppRequest appRequest, Class<X> classOfX, Orderby orderby) {
        Criteria c = currSession().createCriteria(classOfX);
        setOrderby(c, orderby);
        return c.list();
    }

    @Override
    public Pagination findPage(AppRequest appRequest, Page page, Criteria c) {
        Pagination result = new Pagination().copyFrom(page);
        if (page.isAutoCount()) {
            int totalCount = countCriteriaResult(c);
            result.setTotal(totalCount);
        }
        // setOrderby(c, page.getOrderby());
        setPageParameter(c, page);
        result.setRows(c.list());
        return result;
    }

    @Override
    public Pagination findPage(AppRequest appRequest, Page page, final String hql, final Object... values) {
        Pagination result = new Pagination().copyFrom(page);
        Query q = createQuery(hql, values);
        if (result.isAutoCount()) {
            int totalCount = countHqlResult(hql, values);
            result.setTotal(totalCount);
        }
        setPageParameter(q, page);
        result.setRows(q.list());
        return result;
    }

    @Override
    public Pagination findPage(AppRequest appRequest, Page page, final String hql, final Map<String, ?> values) {
        Pagination result = new Pagination().copyFrom(page);
        Query q = createQuery(hql, values);
        if (result.isAutoCount()) {
            int totalCount = countHqlResult(hql, values);
            result.setTotal(totalCount);
        }
        setPageParameter(q, page);
        result.setRows(q.list());
        return result;
    }

    /**
     * 设置分页参数到Query对象,辅助函数. hibernate的firstResult的序号从0开始
     * 
     * @param q
     * @param page
     * @return Query
     */
    protected Query setPageParameter(final Query q, final Page page) {
        q.setFirstResult(page.getFirst() - 1);
        q.setMaxResults(page.getRp());
        return q;
    }

    /**
     * 执行count查询获得本次Hql查询所能获得的对象总数.
     * 
     * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. select子句与order by子句会影响count查询,进行简单的排除.
     * 
     * @param hql
     * @param values
     * @return count value
     */
    protected int countHqlResult(final String hql, final Object... values) {
        String countHql = SqlUtil.parseCountHsqlFromSourceHsql(hql);
        try {
            return queryForLong(null, countHql, values).intValue();
        } catch (Throwable e) {
            throw countHqlException(hql, e);
        }
    }

    /**
     * 执行count查询获得本次Hql查询所能获得的对象总数.
     * 
     * 本函数只能自动处理简单的hql语句,复杂的hql查询请另行编写count语句查询. select子句与order by子句会影响count查询,进行简单的排除.
     * 
     * @param hql
     * @param values
     * @return count value
     */
    protected int countHqlResult(final String hql, final Map<String, ?> values) {
        String countHql = SqlUtil.parseCountHsqlFromSourceHsql(hql);
        try {
            return queryForLong(null, countHql, values).intValue();
        } catch (Throwable e) {
            throw countHqlException(hql, e);
        }
    }

    private RuntimeException countHqlException(String hql, Throwable e) {
        return new RuntimeException("HQL can't be auto count, hql is:" + hql, e);
    }

    @Override
    public <X> X save(AppRequest appRequest, X object) {
        // entityBeforeSave(object, appRequest);
        currSession().saveOrUpdate(object);
        return object;
    }
}
