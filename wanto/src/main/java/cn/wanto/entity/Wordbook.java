package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import cn.wanto.util.AppConstants;

/**
 * Sep 9, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_wordbook")
@org.hibernate.annotations.Table(appliesTo = "t_wordbook", comment = "字数字典、类型、地区等")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Wordbook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "w_name", length = 100)
    private String name;//

    @Column(name = "w_parent_id", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long parentId;// 上层数据字典的ID

    @Column(name = "hot")
    private long hot;// 关联数、热点

    @Column(name = "w_kind", length = 3)
    private int kind;// 类型

    @Column(name = "orderid", columnDefinition = AppConstants.DEFINITION_INT_DEFAULT)
    private int orderid;// 排序

    /**
     * 是否隐藏
     * 
     */
    @Column(name = "is_hidden", columnDefinition = AppConstants.DEFINITION_BOOL_DEFAULT)
    private boolean hidden;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the parentId
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the hot
     */
    public long getHot() {
        return hot;
    }

    /**
     * @param hot
     *            the hot to set
     */
    public void setHot(long hot) {
        this.hot = hot;
    }

    /**
     * @return the kind
     */
    public int getKind() {
        return kind;
    }

    /**
     * @param kind
     *            the kind to set
     */
    public void setKind(int kind) {
        this.kind = kind;
    }

    /**
     * @return the orderid
     */
    public int getOrderid() {
        return orderid;
    }

    /**
     * @param orderid
     *            the orderid to set
     */
    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    /**
     * @return the hidden
     */
    public boolean isHidden() {
        return hidden;
    }

    /**
     * @param hidden
     *            the hidden to set
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
