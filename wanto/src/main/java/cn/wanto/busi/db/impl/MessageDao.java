/*
 * cn.wanto.busi.db.impl.TopicDao.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.wanto.busi.db.IMessageDao;
import cn.wanto.entity.Message;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class MessageDao extends HibernateDao<Message, Long> implements IMessageDao {

}
