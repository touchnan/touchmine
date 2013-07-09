/*
 * cn.touch.imr.busi.db.impl.UserDao.java
 * May 25, 2012 
 */
package cn.touch.imr.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touch.imr.busi.db.IUserDao;
import cn.touch.imr.entity.User;
import cn.touchin.db.hibernate.HibernateDao;

/**
 * May 25, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class UserDao extends HibernateDao<User, Long> implements IUserDao {

}
