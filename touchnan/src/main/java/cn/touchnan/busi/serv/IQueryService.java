/*
 * cn.touchnan.busi.serv.ICronService.java
 * May 4, 2012 
 */
package cn.touchnan.busi.serv;

import java.util.List;

import cn.touchin.page.Pagination;
import cn.touchnan.dto.Ar;

/**
 * May 4, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IQueryService {
    public Pagination findUsers(Ar ar, Pagination page);

    public List<? extends Object> findUsers(Ar ar);
}
