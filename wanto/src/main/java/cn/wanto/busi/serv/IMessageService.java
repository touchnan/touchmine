/*
 * cn.wanto.busi.serv.ITopicService.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.serv;

import cn.touchin.dto.AppRequest;
import cn.touchin.page.Pagination;
import cn.touchin.serv.IService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.MessageDto;
import cn.wanto.entity.Message;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IMessageService extends IService<MessageDto, Message, Long> {

    /**
     * 添加消息
     * 
     * @param userId
     *            用户ID
     * @param type
     *            消息类型 （参见枚举：MessageType.java)
     * @param kind
     *            删除类型 （参见枚举：MessageKind.java)
     * @param shopId
     *            店铺ID
     * @param topicId
     *            主题ID
     * @param postId
     *            帖子或回复ID
     */
    void insertMessage(AppRequest ar, Long userId, Integer type, Integer kind, Long shopId, Long topicId, Long postId);

    /**
     * 消息集合
     * 
     * @return
     */
    Pagination findMessagesByUserId(Pagination page,Long userId, int type);

    /**
     * @param userId
     * @param type
     * @return
     */
    Long findMessagesCountByUserId(Long userId, Integer type);

    /**
     * @param message
     */
    void batchDelete(MessageDto message);

    /**
     * @param loginId
     * @param object
     * @return
     */
    Integer findMessagesNewCountByUserId(Long userId, Integer type);

    /**
     * @param message
     */
    void batchMark(MessageDto message);

    /**
     * @param ar 
     * @param message
     * @return
     */
    MessageDto findMessage(Ar ar, MessageDto message);

    /**
     * @return
     */
    Integer findMessagesCount2BgMess();

    /**
     * @param page
     * @return
     */
    Pagination findMessages2BgMess(Pagination page);

}
