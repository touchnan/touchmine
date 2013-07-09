/*
 * cn.touchnan.busi.serv.IUserService.java
 * May 25, 2012 
 */
package cn.touchnan.busi.serv;

import java.util.List;

import cn.touchin.serv.IService;
import cn.touchnan.dto.Ar;
import cn.touchnan.dto.Vu;
import cn.touchnan.dto.bean.UserDto;
import cn.touchnan.entity.User;

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
