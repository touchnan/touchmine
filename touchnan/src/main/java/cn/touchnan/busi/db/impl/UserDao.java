/*
 * cn.touchnan.busi.db.impl.UserDao.java
 * May 25, 2012 
 */
package cn.touchnan.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.touchnan.busi.db.IUserDao;
import cn.touchnan.entity.User;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class UserDao extends HibernateDao<User, Long> implements IUserDao {

}
