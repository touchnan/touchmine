/*
 * cn.touchin.dto.AppRequest.java
 * Feb 12, 2012 
 */
package cn.touchin.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.log.Logs;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class AppRequest extends Vo {
    private static final long serialVersionUID = 2566058209361610198L;

    public AppRequest() {
        super();
    }

    public AppRequest(Object o) {
        super(o);
    }

    public AppRequest copyAppRequest() {
        AppRequest result = new AppRequest();
        try {
            BeanUtils.copyProperties(result, this);
        } catch (Throwable e) {
            Logs.getLog(AppRequest.class).error(e.getMessage(), e);
        }
        return result;
    }

}
