/*
 * cn.touchin.dto.Vo.java
 * Feb 13, 2012 
 */
package cn.touchin.dto;

import java.io.Serializable;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Vo implements Serializable {
    private static final long serialVersionUID = -7362971096488367281L;

    public Vo() {
        super();
    }

    public Vo(Object o) {
        if (o != null) {
            try {
                BeanUtils.copyProperties(this, o);
            } catch (Throwable e) {
                Log log = Logs.getLog(getClass());
                if (log.isWarnEnabled()) {
                    log.warn("Fail to copy properties!", e);
                }
            }
        }
    }
}
