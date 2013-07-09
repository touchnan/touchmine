/*
 * cn.wanto.busi.db.impl.UserDao.java
 * May 25, 2012 
 */
package cn.wanto.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.wanto.busi.db.IUserDao;
import cn.wanto.entity.User;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class UserDao extends HibernateDao<User, Long> implements IUserDao {

}
