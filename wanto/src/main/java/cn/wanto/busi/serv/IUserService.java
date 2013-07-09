/*
 * cn.wanto.busi.serv.IUserService.java
 * May 25, 2012 
 */
package cn.wanto.busi.serv;

import java.util.List;

import cn.touchin.serv.IService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.Vu;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.entity.User;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public interface IUserService extends IService<UserDto, User, Long> {

    public int resetPasswd(Long id, String oPasswd, String nPasswd);

    public int resetSelfPasswd(Ar ar, String oPasswd, String nPasswd);

    public int resetPasswd(Ar ar, List<Long> uIds, String nPasswd);

    /**
     * @param loginName
     * @param passwd
     * @return
     */
    public Vu auth(String loginName, String passwd);

    /**
     * @param loginName
     * @return
     */
    public Vu auth(String loginName);

    /**
     * @param ar
     * @param page
     * @return
     */
    public List<UserDto> findCacheUser(Ar ar);

    /**
     * @param ar
     * @return
     */
    public UserDto self(Ar ar);

    /**
     * @param email
     * @return
     */
    public boolean emailExist(String email);

}
