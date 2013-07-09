/*
 * test.cn.touch.init.Init.java
 * Aug 23, 2012 
 */
package test.cn.touch.init;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.touch.imr.busi.serv.IUserService;
import cn.touch.imr.dto.bean.UserDto;
import cn.touch.imr.entity.User;
import cn.touch.imr.util.enume.UserRole;
import cn.touch.imr.util.enume.UserType;
import cn.touchin.Contexts;

/**
 * Aug 23, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Init {
    static {
        Contexts.init(new ClassPathXmlApplicationContext("spring/context.xml"));
    }
    /**
     * @param args
     */
    public static void main(String[] args) {
        initAdmin();
        initUsers();
        System.exit(0);
    }
    
    public static void initAdmin() {
        User u = new User();
        u.setHidden(true);
        u.setLoginName("hancq");
        u.setNickName("韩成强");
        u.setPasswd(UserDto.encryptPasswd("123456"));
        u.setType(UserType.MAMEGER.ordinal());
        u.setRole(UserRole.SUPERADMIN.ordinal());

        Contexts.getBean(IUserService.class).save(null, u);
    }
    
    public static void initUsers() {
        IUserService service = Contexts.getBean(IUserService.class);
        for (int i = 0; i <= 400; i++) {
            User u = new User();
            u.setHidden(false);
            u.setLoginName("u" + i);
            u.setNickName("韩" + i);
            u.setPasswd(UserDto.encryptPasswd("123456"));
            u.setRole(UserRole.NORMAL.ordinal());
            u.setType(UserType.PORTION.ordinal());
            service.save(null, u);
        }

    }

}
