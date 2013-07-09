/*
 * cn.wanto.busi.db.impl.TopicDao.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.wanto.busi.db.ITopicDao;
import cn.wanto.entity.Topic;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class TopicDao extends HibernateDao<Topic, Long> implements ITopicDao {

}
