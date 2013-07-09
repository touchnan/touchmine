package cn.wanto.busi.db.impl;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.wanto.busi.db.IWordbookDao;
import cn.wanto.entity.Wordbook;

/**
 * Sep 9, 2012
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class WordbookDao extends HibernateDao<Wordbook, Long> implements IWordbookDao {

}
