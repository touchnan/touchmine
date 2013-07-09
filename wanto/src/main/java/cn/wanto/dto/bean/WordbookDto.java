/*
 * cn.wanto.dto.bean.WordbookDto.java
 * Sep 9, 2012 
 */
package cn.wanto.dto.bean;

import cn.touchin.dto.Dto;
import cn.wanto.entity.Wordbook;

/**
 * Sep 9, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class WordbookDto extends Dto<Wordbook> {
    private static final long serialVersionUID = -6830878223247434619L;

    private Long id;
    private String name;//
    private int parentId;// 上层数据字典的ID
    private int hot;// 关联数、热点
    private int kind;// 类型
    private int orderid;// 排序
    private boolean hidden;// 是否隐藏
    
    /**
     * 
     */
    public WordbookDto() {
        super();
    }

    /**
     * @param o
     */
    public WordbookDto(Wordbook o) {
        super(o);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.dto.Dto#invokeForCallback(java.lang.Object)
     */
    @Override
    public Dto<Wordbook> invokeForCallback(Wordbook wb) {
        return super.copyProperties(wb);
    }

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
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the hot
     */
    public int getHot() {
        return hot;
    }

    /**
     * @param hot
     *            the hot to set
     */
    public void setHot(int hot) {
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
