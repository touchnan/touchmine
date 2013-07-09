/*
 * cn.touch.imr.busi.serv.impl.UserService.java
 * May 25, 2012 
 */
package cn.touch.imr.busi.serv.impl;

import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touch.imr.busi.db.IUserDao;
import cn.touch.imr.busi.serv.IUserService;
import cn.touch.imr.dto.Ar;
import cn.touch.imr.dto.Vu;
import cn.touch.imr.dto.bean.UserDto;
import cn.touch.imr.entity.User;
import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.kit.Entities;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;
import cn.touchin.serv.BaseService;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class UserService extends BaseService<UserDto, User, Long> implements IUserService {

    @Autowired
    private IUserDao userDao;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#dao()
     */
    @Override
    public IHibernateDao<User, Long> dao() {
        return userDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#save(cn.touchin.dto.AppRequest,
     * cn.touchin.dto.Dto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public UserDto save(AppRequest ar, UserDto dto) {
        if (!Strings.isBlank(dto.getNewPasswd())) {
            dto.setPasswd(UserDto.encryptPasswd(dto.getNewPasswd()));
        }
        if (dto.getId() == null || dto.getId() <= 0) {
            return super.insert(ar, dto);
        } else {
            // 更新
            User u = super.fetchEntity(ar, dto.getId());
            if (Strings.isBlank(dto.getNewPasswd())) {
                // 保留密码
                dto.setPasswd(u.getPasswd());
            }
            return super.update(ar, dto, u);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#findPage(cn.touchin.dto.AppRequest,
     * cn.touchin.page.Pagination)
     */
    @Override
    public Pagination findPage(AppRequest ar, Pagination page) {
        page.addCondition("EQB_hidden", "false");
        return super.findPage(ar, page);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * cn.touch.imr.busi.serv.IUserService#findCacheUser(cn.touch.imr.dto.Ar)
     */
    @Override
    public List<UserDto> findCacheUser(Ar ar) {
        @SuppressWarnings("unchecked")
        List<User> entities = userDao.currSession().createCriteria(getEntityClass()).add(Restrictions.eq("hidden", true))
                .setCacheable(true).list();
        return Entities.castEntities2DTOs(entities, getDTOClass());
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.imr.busi.serv.IUserService#resetPasswd(java.lang.Long,
     * java.lang.String, java.lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int resetPasswd(Long id, String oPasswd, String nPasswd) {
        if (id == null || Strings.isBlank(oPasswd) || Strings.isBlank(nPasswd))
            return 0;
        return userDao
                .currSession()
                .createQuery(
                        "UPDATE " + User.class.getName()
                                + " o SET o.passwd=:npasswd WHERE o.id=:id AND o.passwd=:opasswd")
                .setParameter("npasswd", UserDto.encryptPasswd(nPasswd)).setParameter("id", id)
                .setParameter("opasswd", UserDto.encryptPasswd(oPasswd)).executeUpdate();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.imr.busi.serv.IUserService#resetSelfPasswd(cn.chuzheng.sgmr
     * .dto.Ar, java.lang.String, java.lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int resetSelfPasswd(Ar ar, String oPasswd, String nPasswd) {
        return resetPasswd(ar.getLoginId(), oPasswd, nPasswd);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.imr.busi.serv.IUserService#resetPasswd(cn.touch.imr.
     * dto.Ar, java.util.List, java.lang.String)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int resetPasswd(Ar ar, List<Long> uIds, String nPasswd) {
        if (uIds != null && !uIds.isEmpty() && !Strings.isBlank(nPasswd)) {
            return userDao.currSession()
                    .createQuery("UPDATE " + User.class.getName() + " o SET o.passwd=:passwd WHERE o.id in (:ids)")
                    .setParameter("passwd", UserDto.encryptPasswd(nPasswd)).setParameterList("ids", uIds)
                    .executeUpdate();
        }
        return 0;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.touch.imr.busi.serv.IUserService#auth(java.lang.String,
     * java.lang.String)
     */
    @Override
    public Vu auth(String loginName, String passwd) {
        if (!Strings.isBlank(loginName) && !Strings.isBlank(passwd)) {
            User u = userDao.fetch(null, new PropertyFilter("EQS_loginName", loginName), new PropertyFilter(
                    "EQS_passwd", UserDto.encryptPasswd(passwd)));
            if (u != null) {
                return new Vu(u);
            }
        }
        return null;
    }

}
