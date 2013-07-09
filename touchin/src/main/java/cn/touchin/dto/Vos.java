/*
 * cn.touchin.dto.Vos.java
 * Feb 13, 2012 
 */
package cn.touchin.dto;

import org.apache.commons.beanutils.BeanUtils;
import org.nutz.json.Json;
import org.nutz.json.JsonFormat;
import org.nutz.lang.Lang;
import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

/**
 * Feb 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Vos {

    private static Log log = Logs.getLog(Vos.class);

    /**
     * 把一个任意类型的java对象转换成Vo对象。
     * 
     * 1、Vo对象需要有一个构造器，此构造器只有唯一的Object类型参数；
     * 
     * 2、若没有，采用默认构造器创建Vo，并拷贝Object对象的同名属性
     * 
     * @param <T>
     * @param classOfT
     * @param o
     * @return VO对象
     */
    public static <T extends Vo> T from(Class<T> classOfT, Object o) {
        if (classOfT != null && o != null) {
            Mirror<T> mirror = Mirror.me(classOfT);
            try {
                return mirror.born(o);
            } catch (Throwable e) {
                T vo = mirror.born();
                try {
                    BeanUtils.copyProperties(vo, o);
                } catch (Throwable e1) {
                    log.error(e1.getMessage(), e1);
                }
            }
        }
        return null;
    }

    /**
     * 把一个json字符串转换成Vo对象
     * 
     * @param <T>
     * @param classOfT
     * @param json
     * @return VO对象
     */
    public static <T extends Vo> T fromJson(Class<T> classOfT, String json) {
        return Json.fromJson(classOfT, Lang.inr(json));
    }

    /**
     * 将一个 JAVA 对象转换成 JSON 字符串
     * 
     * @param <T>
     * @param vo
     * @return json string
     */
    public static <T extends Vo> String toJson(T vo) {
        return Json.toJson(vo);
    }

    /**
     * 将一个 JAVA 对象转换成 JSON 字符串，并且可以设定 JSON 字符串的格式化方式
     * 
     * @param <T>
     * @param vo
     * @param format
     * @return json string
     */
    public static <T extends Vo> String toJson(T vo, JsonFormat format) {
        return Json.toJson(vo, format);
    }

}
