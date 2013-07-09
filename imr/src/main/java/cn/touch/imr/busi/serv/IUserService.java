/*
 * cn.touch.imr.busi.serv.IUserService.java
 * May 25, 2012 
 */
package cn.touch.imr.busi.serv;

import java.util.List;

import cn.touch.imr.dto.Ar;
import cn.touch.imr.dto.Vu;
import cn.touch.imr.dto.bean.UserDto;
import cn.touch.imr.entity.User;
import cn.touchin.serv.IService;

/**
 * May 25, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IUserService extends IService<UserDto, User, Long> {
    
    public int resetPasswd(Long id, String oPasswd, String nPasswd);
    
    public int resetSelfPasswd(Ar ar, String oPasswd, String nPasswd);
    
    public int resetPasswd(Ar ar, List<Long> uIds,String nPasswd);

    /**
     * @param loginName
     * @param passwd
     * @return
     */
    public Vu auth(String loginName, String passwd);

    /**
     * @param ar
     * @param page
     * @return
     */
    public List<UserDto> findCacheUser(Ar ar);
}
