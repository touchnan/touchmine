/*
 * cn.touchin.page.Page.java
 * Feb 12, 2012 
 */
package cn.touchin.page;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import cn.touchin.dto.Dto;
import cn.touchin.kit.Entities;

/**
 * Feb 12, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Page {
    
    protected Integer rp=20;//每页多少条纪录
    protected Integer page=0;//页码(当前页)
    protected Integer total=0;//纪录总条数
    
    protected boolean autoCount = true;

    // -- 返回结果 --//
    protected List<?> rows = new ArrayList<Object>();
    

    public Page() {
        super();
    }

    public Page(int rp) {
        this.setRp(rp);
    }

    public Page(int page, int rp) {
        this.setPage(page);
        this.setRp(rp);
    }

    public Page(int page, int rp, Integer total) {
        this.setPage(page);
        this.setRp(rp);
        this.setTotal(total);
    }

    /**
     * 把实体 page 转换成 DTO page
     * 
     * @param <E>
     * @param <D>
     * @param classOfDTO
     * @return DTO page
     */
    @SuppressWarnings("unchecked")
    public <E extends Object, D extends Dto<E>> Page castResultEntities2DTOs(Class<D> classOfDTO) {
//        if (null == elements || elements.isEmpty() || !(elements.get(0) instanceof Object)) {
        if (null == rows || rows.isEmpty()) {
            return this;
        }
        List<?> dtos = Entities.castEntities2DTOs((List<E>) rows, classOfDTO);
        this.setRows(dtos);
        return this;
    }

    /**
     * 把实体 page 转换成 DTO page
     * 
     * @param <E>
     * @param <D>
     * @param classOfDTO
     * @param createArgs
     * @return shis page
     */
    @SuppressWarnings("unchecked")
    public <E extends Object, D extends Dto<E>> Page castResultEntities2DTOs(Class<D> classOfDTO, Object[] createArgs) {
//        if (null == elements || elements.isEmpty() || !(elements.get(0) instanceof Entity)) {
        if (null == rows || rows.isEmpty()) {
            return this;
        }
        List<?> dtos = Entities.castEntities2DTOs((List<E>) rows, classOfDTO, createArgs);
        this.setRows(dtos);
        return this;
    }

    /**
     * 把实体 page 转换成 DTO page
     * 
     * @param <E>
     * @param <D>
     * @param classOfDTO
     * @param methodName
     * @param methodArgs
     * @return this page
     */
    @SuppressWarnings("unchecked")
    public <E extends Object, D extends Dto<E>> Page castResultEntities2DTOs(Class<D> classOfDTO, String methodName,
            Object[] methodArgs) {
//        if (null == elements || elements.isEmpty() || !(elements.get(0) instanceof Entity)) {
        if (null == rows || rows.isEmpty()) {
            return this;
        }
        List<?> dtos = Entities.castEntities2DTOs((List<E>) rows, classOfDTO, methodName, methodArgs);
        this.setRows(dtos);
        return this;
    }

    public Page copyFrom(Page src) {
        Page result = new Page();
        result.copyProperties(src);
        return result;
    }
    
    public Page clone() {
        return copyFrom(this);
    }

    /**
     * copy基本属性
     * 
     * @param src
     */
    protected void copyProperties(Page src) {
        this.setAutoCount(src.isAutoCount());
        // this.setConditions(src.getConditions());
        // this.setOrderby(src.getOrderby());
        this.setPage(src.getPage());
        this.setRp(src.getRp());
        this.setTotal(src.getTotal());
        // this.setResult(src.getResult());
    }

    // -- 访问查询参数函数 --//
    /**
     * 获得当前页的页号,序号从1开始,默认为1.
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
     */
    public void setPage(final int pageNo) {
        this.page = pageNo;

        if (pageNo < 1) {
            this.page = 1;
        }
    }

    public Page page(final int thePageNo) {
        setPage(thePageNo);
        return this;
    }

    /**
     * 获得每页的记录数量,默认为1.
     */
    public int getRp() {
        return rp;
    }

    /**
     * 设置每页的记录数量,低于1时自动调整为1.
     */
    public void setRp(final int rp) {
        this.rp = rp;

        if (rp < 1) {
            this.rp = 1;
        }
    }

    public Page pageSize(final int thePageSize) {
        setRp(thePageSize);
        return this;
    }

    /**
     * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
     */
    @JSON(serialize = false)
    public int getFirst() {
        return ((page - 1) * rp);
    }

    /**
     * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
     */
    @JSON(serialize = false)
    public boolean isAutoCount() {
        return autoCount;
    }

    /**
     * 查询对象时是否自动另外执行count查询获取总记录数.
     */
    public void setAutoCount(final boolean autoCount) {
        this.autoCount = autoCount;
    }

    public Page autoCount(final boolean theAutoCount) {
        setAutoCount(theAutoCount);
        return this;
    }

    // -- 访问查询结果函数 --//

    /**
     * 取得页内的记录列表.
     */
    public List<?> getRows() {
        return rows;
    }

    /**
     * 设置页内的记录列表.
     */
    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * 取得总记录数, 默认值为-1.
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * 设置总记录数.
     */
    public void setTotal(final Integer totalCount) {
        this.total = totalCount;
    }

    /**
     * 根据pageSize与totalCount计算总页数, 默认值为-1.
     */
    public long getTotalPages() {
        if (total < 0) {
            return 0;
        }

        long count = total / rp;
        if (total % rp > 0) {
            count++;
        }
        return count;
    }
    
}
