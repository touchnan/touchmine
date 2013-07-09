/*
 * cn.touchin.util.Entities.java
 * Feb 13, 2012 
 */
package cn.touchin.kit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.nutz.lang.Mirror;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import cn.touchin.born.DtoBorning;
import cn.touchin.born.DtoBorns;
import cn.touchin.dto.Dto;

/**
 * Feb 13, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Entities {

    private static Log log = Logs.getLog(Entities.class);
    

    /**
     * 自动查找合适的构造器或者合适的静态构造方法，把Entity转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entity
     *            实体对象
     * @param classOfDTO
     *            DTO类型
     * @return DTO
     */
    public static <E extends Object, T extends Dto<E>> T castEntity2DTO(
            E entity, Class<T> classOfDTO) {
        return castEntity2DTO(entity, classOfDTO, new Object[] { entity });
    }

    /**
     * 根据提供的构造参数，自动查找合适的构造器或者合适的静态构造方法，把Entity转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entity
     *            实体对象
     * @param classOfDTO
     *            DTO类型
     * @param createArgs
     *            构造参数,至少有一个参数是entity
     * @return DTO
     */
    public static <E extends Object, T extends Dto<E>> T castEntity2DTO(
            E entity, Class<T> classOfDTO, Object[] createArgs) {
        return castEntity2DTO(entity, classOfDTO, null, createArgs);
    }

    /**
     * 根据提供的方法和构造参数，自动查找合适的静态构造方法或者构造器，把Entity转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entity
     * @param classOfDTO
     * @param methodName
     * @param methodArgs
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Object, T extends Dto<E>> T castEntity2DTO(
            E entity, Class<T> classOfDTO, String methodName,
            Object[] methodArgs) {
        if (matchEntityArg(methodArgs, entity)) {
            Class<?>[] createArgTypes = Mirror.evalToTypes(methodArgs);
            DtoBorning borning = DtoBorns.evalBorning(classOfDTO, entity,
                    methodName, createArgTypes);
            if (null != borning) {
                try {
                    return (T) borning.born(methodArgs);
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return null;
    }

    /**
     * 把DTO转换成Entity
     * 
     * @param <E>
     * @param <T>
     * @param dto
     * @return Entity
     */
    public static <E extends Object, T extends Dto<E>> E castDTO2Entity(T dto) {
        return dto.toEntity();
    }

    /**
     * 自动查找合适的构造器或者合适的静态构造方法，把entities转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entities
     * @param classOfDTO
     * @return DTO列表
     */
    public static <E extends Object, T extends Dto<E>> List<T> castEntities2DTOs(
            List<E> entities, Class<T> classOfDTO) {
        return castEntities2DTOs(entities, classOfDTO, new Object[1]);
    }

    /**
     * 根据提供的构造参数，自动查找合适的构造器或者合适的静态构造方法，把entities转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entities
     * @param classOfDTO
     * @param createArgs
     *            构造参数数组，createArgs[0]预留给entity。即构造参数数组的第一个参数约定为entity
     * @return
     */
    public static <E extends Object, T extends Dto<E>> List<T> castEntities2DTOs(
            List<E> entities, Class<T> classOfDTO, Object[] createArgs) {
        return castEntities2DTOs(entities, classOfDTO, null, createArgs);
    }

    /**
     * 根据提供的静态构造方法名和构造参数，自动查找合适的静态构造方法或构造器，把entities转换成DTO
     * 
     * @param <E>
     * @param <T>
     * @param entities
     * @param classOfDTO
     * @param methodName
     * @param createArgs
     *            构造参数数组，createArgs[0]预留给entity。即构造参数数组的第一个参数约定为entity
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <E extends Object, T extends Dto<E>> List<T> castEntities2DTOs(
            List<E> entities, Class<T> classOfDTO, String methodName,
            Object[] createArgs) {
        List<T> dtos = null;
        if (null != createArgs && createArgs.length > 0 && null != entities
                && entities.size() > 0) {
            dtos = new ArrayList<T>();
            createArgs[0] = entities.get(0);
            Class<?>[] createArgTypes = Mirror.evalToTypes(createArgs);
            DtoBorning borning = DtoBorns.evalBorning(classOfDTO,
                    entities.get(0), methodName, createArgTypes);
            if (null != borning) {
                try {
                    for (E entity : entities) {
                        createArgs[0] = entity;
                        T dto = (T) borning.born(createArgs);
                        dtos.add(dto);
                    }
                } catch (Throwable e) {
                    log.error(e.getMessage(), e);
                }
            }
        }
        return dtos;
    }

    /**
     * @param <E>
     * @param <T>
     * @param dtos
     * @return Entity列表
     */
    public static <E extends Object, T extends Dto<E>> List<E> castDTOs2Entities(
            List<T> dtos) {
        List<E> entities = new ArrayList<E>();
        if (dtos != null && dtos.size() > 0) {
            for (Iterator<T> itr = dtos.iterator(); itr.hasNext();) {
                entities.add((E) itr.next().toEntity());
            }
        }
        return entities;
    }

    /**
     * 检查参数中是否匹配到参数obj
     * 
     * @param args
     * @param obj
     * @return true or false
     */
    private static boolean matchEntityArg(Object[] args, Object obj) {
        if (null == obj || null == args || args.length <= 0) {
            return false;
        }
        for (Object arg : args) {
            if (arg == obj) {
                return true;
            }
        }
        return false;
    }


}
