/*
 * cn.wanto.dto.bean.TagDto.java
 * Sep 24, 2012 
 */
package cn.wanto.dto.bean;

import cn.touchin.dto.Dto;
import cn.wanto.entity.Wordbook;

/**
 * Sep 24, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TagDto extends Dto<Wordbook> {
    private static final long serialVersionUID = 5425733531625984895L;
    public static String schRid = "schRid";// 地区搜索参数
    public static String schKid = "schKid";// 店铺类型搜索参数
    private Long id;
    private String name;// 名称
    private String qname;// 查询字段

    public TagDto(Wordbook wb, String searchField) {
        super(wb);
        this.qname = searchField;
    }

    public TagDto(String name) {
        this.name = name;
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
     * @return the qname
     */
    public String getQname() {
        return qname;
    }

    /**
     * @param qname
     *            the qname to set
     */
    public void setQname(String qname) {
        this.qname = qname;
    }

}
