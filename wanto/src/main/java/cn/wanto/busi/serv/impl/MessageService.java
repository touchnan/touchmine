/*
 * cn.wanto.busi.serv.impl.TopicService.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.serv.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touch.db.Db;
import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.db.jdbc.ISQLParser;
import cn.touchin.db.jdbc.PageQuery;
import cn.touchin.db.jdbc.SQLRunner;
import cn.touchin.dto.AppRequest;
import cn.touchin.page.Pagination;
import cn.touchin.serv.BaseService;
import cn.wanto.busi.db.IMessageDao;
import cn.wanto.busi.serv.IMessageService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.MessageDto;
import cn.wanto.entity.Message;
import cn.wanto.util.AppConstants;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class MessageService extends BaseService<MessageDto, Message, Long> implements IMessageService {

    @Autowired
    private Db db;

    @Autowired
    private ISQLParser sqlParser;

    @Autowired
    private IMessageDao messageDao;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#dao()
     */
    @Override
    public IHibernateDao<Message, Long> dao() {
        return messageDao;
    }

    // public void setSqlParser(ISQLParser sqlParser) {
    // this.sqlParser = sqlParser;
    // }
    //
    // public void setMessageDao(IMessageDao messageDao) {
    // this.messageDao = messageDao;
    // }

    public void setDb(Db db) {
        this.db = db;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#addMessage(java.lang.Long, int, int, java.lang.Long, java.lang.Long,
     * java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertMessage(AppRequest ar, Long userId, Integer type, Integer kind, Long shopId, Long topicId,
            Long postId) {
        if (userId == null || userId == 0) {
            throw new RuntimeException("用户ID不存在添加消息错误");
        }
        Message message = new Message();
        message.setUserId(userId);
        message.setType(type);
        message.setKind(kind);
        message.setShopId(shopId);
        message.setTopicId(topicId);
        message.setPostId(postId);
        message.setCreateTime(new Date());
        message.setStatus(0);
        message.setReading(false);
        messageDao.save(ar, message);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#findMessages()
     */
    @Override
    public Pagination findMessages2BgMess(Pagination page) {
        // page.setRows(db
        // .find(MessageDto.class,
        // " SELECT info.id, info.topicId , info.postId , info.shopId,tp.topic_title as shopTitle,info.type,info.kind,info.userId ,info.createTime from ( "
        // +
        // " (SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime "
        // + " FROM t_message m,t_user u ,t_topic t "
        // + " WHERE m.id = u.user_id and m.m_shop_id = t.topic_id) info "
        // + " left join t_topic tp on  tp.topic_id=info.topicId "
        // + " left join t_post pt on pt.post_id=info.postId  ) WHERE userId = ? ORDER BY createTime DESC LIMIT ?,?",
        // new Object[] { userId, page.getFirst(),page.getPage() }));
        
        PageQuery pq =
            sqlParser
            .createPageQuery(new SQLRunner(page)
            .select(" info.id, info.topicId , info.postId , info.shopId,tp.topic_title as shopTitle,info.type,info.kind,info.userId ,info.createTime,pte.post_subject as postTitle,info.status ,info.reading,info.opinionState ")
            .from("((SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading,m.m_opinion_state as opinionState"
                    + " FROM t_message m,t_user u   WHERE m.m_user_id = u.user_id) info  "
                    + " left join t_topic tp on  tp.topic_id=info.topicId OR tp.topic_id =info.shopId "
                    + " left join t_post_text pte on pte.post_id=info.postId  )")
                    .where(" info.opinionState<>? ")
                    .setParameters(0));
        
        page.setRows(db.find(MessageDto.class, pq.getPageSql(), pq.getPageParams()));
        page.setTotal(findMessagesCount2BgMess());
        return page;
    }
    @Override
    public Integer findMessagesCount2BgMess() {
        List<MessageDto> count =
                db.find(MessageDto.class,
                        " SELECT count(info.id) as id from ( "
                                + " (SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading,m.m_opinion_state as opinionState"
                                + " FROM t_message m,t_user u  WHERE m.m_user_id = u.user_id) info  "
                                + " left join t_topic tp on  tp.topic_id=info.topicId OR tp.topic_id =info.shopId"
                                + " left join t_post pt on pt.post_id=info.postId  ) WHERE info.opinionState<>? ",
                        new Object[] { 0});
        if (count == null || count.size() == 0) {
            return 0;
        }
        return Integer.valueOf(count.get(0).getId().toString());
    }
    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#findMessages()
     */
    @Override
    public Pagination findMessagesByUserId(Pagination page, Long userId, int type) {
        // page.setRows(db
        // .find(MessageDto.class,
        // " SELECT info.id, info.topicId , info.postId , info.shopId,tp.topic_title as shopTitle,info.type,info.kind,info.userId ,info.createTime from ( "
        // +
        // " (SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime "
        // + " FROM t_message m,t_user u ,t_topic t "
        // + " WHERE m.id = u.user_id and m.m_shop_id = t.topic_id) info "
        // + " left join t_topic tp on  tp.topic_id=info.topicId "
        // + " left join t_post pt on pt.post_id=info.postId  ) WHERE userId = ? ORDER BY createTime DESC LIMIT ?,?",
        // new Object[] { userId, page.getFirst(),page.getPage() }));

        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" info.id, info.topicId , info.postId , info.shopId,tp.topic_title as shopTitle,info.type,info.kind,info.userId ,info.createTime,pte.post_subject as postTitle,info.status ,info.reading ")
                                .from("((SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading"
                                        + " FROM t_message m,t_user u   WHERE m.m_user_id = u.user_id) info  "
                                        + " left join t_topic tp on  tp.topic_id=info.topicId OR tp.topic_id =info.shopId "
                                        + " left join t_post_text pte on pte.post_id=info.postId  )")
                                .where(" userId =? AND info.status<> ? ORDER BY createTime DESC ")
                                .setParameters(userId, AppConstants.DEL_TAG));

        page.setRows(db.find(MessageDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(findMessagesCountByUserId(userId, type));
        return page;
    }

    @Override
    public Long findMessagesCountByUserId(Long userId, Integer type) {
        List<MessageDto> count =
                db.find(MessageDto.class,
                        " SELECT count(info.id) as id from ( "
                                + " (SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading"
                                + " FROM t_message m,t_user u  WHERE m.m_user_id = u.user_id) info  "
                                + " left join t_topic tp on  tp.topic_id=info.topicId OR tp.topic_id =info.shopId"
                                + " left join t_post pt on pt.post_id=info.postId  ) WHERE userId =? AND info.status<> ? ORDER BY createTime DESC",
                        new Object[] { userId, AppConstants.DEL_TAG });
        if (count == null || count.size() == 0) {
            return 0L;
        }
        return count.get(0).getId();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#batchDelete(cn.wanto.dto.bean.MessageDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void batchDelete(MessageDto message) {
        if (message != null && message.getIds().length > 0) {
            for (Long id : message.getIds()) {
                db.update("delete from t_message where id = ?", id);
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#findMessagesNewCountByUserId(java.lang.Long, java.lang.Object)
     */
    @Override
    public Integer findMessagesNewCountByUserId(Long userId, Integer type) {

        List<MessageDto> count =
                db.find(MessageDto.class,
                        " SELECT count(info.id) as id from ( "
                                + " (SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type,m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading"
                                + " FROM t_message m,t_user u  WHERE m.m_user_id = u.user_id) info  "
                                + " left join t_topic tp on  tp.topic_id=info.topicId OR tp.topic_id =info.shopId"
                                + " left join t_post pt on pt.post_id=info.postId  ) WHERE userId =? AND info.status<> ? AND (info.reading is null or info.reading = 0) ORDER BY createTime DESC",
                        new Object[] { userId, AppConstants.DEL_TAG });

        if (count == null || count.size() == 0) {
            return 0;
        }
        return Integer.valueOf(count.get(0).getId().toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#batchMark(cn.wanto.dto.bean.MessageDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void batchMark(MessageDto message) {
        if (message != null && message.getIds().length > 0) {
            for (Long id : message.getIds()) {
                db.update("update t_message set m_reading = ? where id = ?", new Object[] { Boolean.TRUE, id });
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IMessageService#findMessage(cn.wanto.dto.bean.MessageDto)
     */
    @Override
    public MessageDto findMessage(Ar ar, MessageDto message) {
        String sql =
                "SELECT info.id, info.topicId , info.postId , info.shopId,tp.topic_title as shopTitle,info.type,info.kind,info.userId ,info.createTime,pte.post_subject as postTitle,info.status ,info.reading "
                        + " FROM ((SELECT m.id as id, m.m_topic_id as topicId , m.m_post_id as postId , m.m_shop_id as shopId,m.m_type as type, m.m_kind as kind ,m.m_user_id as userId ,m.user_createtime as createTime,m.m_status as status,m.m_reading as reading "
                        + " FROM t_message m,t_user u ,t_topic t WHERE m.m_user_id = u.user_id ) info  "
                        + " left join t_topic tp on  (tp.topic_id=info.topicId OR tp.topic_id =info.shopId) " 
                        + "left join t_post pt on pt.post_id=info.postId "
                        + " left join t_post_text pte on pte.post_id=info.postId  )" 
                        + "WHERE userId = ? AND info.status<>? and info.id = ?";
        MessageDto topic =
                db.findFirst(MessageDto.class, sql,
                        new Object[] { ar.getLoginId(), AppConstants.DEL_TAG, message.getId() });

        return topic;
    }

}
