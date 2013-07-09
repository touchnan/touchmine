/*
 * cn.touchin.born.StaticMethodBorning.java
 * Feb 13, 2012 
 */
package cn.touchin.born;

import java.lang.reflect.Method;

/**
 * Feb 13, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class StaticMethodBorning implements DtoBorning {


    private Method method;

    /**
     * 
     */
    public StaticMethodBorning(Method method) {
        this.method = method;
    }

    /* (non-Javadoc)
     * @see cn.touchin.born.DtoBorning#born(java.lang.Object[])
     */
    @Override
    public Object born(Object[] args) throws Exception {
        return method.invoke(null, args);
    }


}
