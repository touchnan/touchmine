/*
 * cn.touchin.Contexts.java
 * Sep 15, 2011 
 */
package cn.touchin;

import org.springframework.context.ApplicationContext;

/**
 * Sep 15, 2011
 * 
 * @author <a href="mailto:touchnan@gmail.com">chengqiang.han</a>
 * 
 */
public class Contexts {
    private static ApplicationContext context;

    private Contexts() {
        super();
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }

    public static Object getBean(String bean) {
        return context.getBean(bean);
    }

    public static <T> T getBean(String bean, Class<T> clazz) {
        return context.getBean(bean, clazz);
    }

    public static Object getBean(String bean, Object... objs) {
        return context.getBean(bean, objs);
    }

    public static void init(ApplicationContext applicationContext) {
        context = applicationContext;
    }
}
