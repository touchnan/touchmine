/*
 * cn.touchin.born.ConstructorBorning.java
 * Feb 13, 2012 
 */
package cn.touchin.born;

import java.lang.reflect.Constructor;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ConstructorBorning implements DtoBorning {

    private Constructor<?> c;

    public ConstructorBorning(Constructor<?> c) {
        this.c = c;
    }

    /* (non-Javadoc)
     * @see cn.touchin.born.DtoBorning#born(java.lang.Object[])
     */
    @Override
    public Object born(Object[] args) throws Exception {
        return c.newInstance(args);
    }

}
