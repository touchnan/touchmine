/*
 * cn.wanto.dto.TreeDto.java
 * Sep 12, 2012 
 */
package cn.wanto.dto;

import java.util.ArrayList;
import java.util.List;

import cn.wanto.entity.Wordbook;

/**
 * Sep 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TreeDto {
    private Long id;
    private String name;
    private List<TreeDto> children = new ArrayList<TreeDto>(0);

    public TreeDto() {
        super();
    }

    public TreeDto(Wordbook wb) {
        super();
        this.id = wb.getId();
        this.name = wb.getName();
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
     * @return the children
     */
    public List<TreeDto> getChildren() {
        return children;
    }

    /**
     * @param children
     *            the children to set
     */
    public void setChildren(List<TreeDto> children) {
        this.children = children;
    }

}
