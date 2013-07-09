/*
 * cn.touchin.born.DefaultDtoBorning.java
 * Feb 13, 2012 
 */
package cn.touchin.born;

import org.nutz.lang.Mirror;

import cn.touchin.dto.Dto;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class DefaultDtoBorning implements DtoBorning {

    private Mirror<?> mirror;

    public DefaultDtoBorning(Mirror<?> mirror) {
        this.mirror = mirror;
    }

    /* (non-Javadoc)
     * @see cn.touchin.born.DtoBorning#born(java.lang.Object[])
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public Object born(Object[] args) throws Exception {
        Object result = mirror.born();
        Object entity = matchArgs(args);
        if (result instanceof Dto) {
            ((Dto) result).fromEntity(entity);
        }
        return result;
    }

    /**
     * 检查参数中是否匹配到参数Entity
     * 
     * @param args
     * @return
     */
    private Object matchArgs(Object[] args) {
        if (null == args || args.length <= 0) {
            return null;
        }
        return args[0];
//        for (Object arg : args) {
//            if (arg instanceof Entity) {
//                return (Entity) arg;
//            }
//        }
//        return null;
    }

}
