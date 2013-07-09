/*
 * cn.wanto.busi.www.mgr.UserAction.java
 * Sep 3, 2012 
 */
package cn.wanto.busi.www.mgr;

import org.springframework.beans.factory.annotation.Autowired;

import cn.wanto.busi.serv.IMessageService;
import cn.wanto.dto.bean.MessageDto;

/**
 * Sep 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class MessageAction extends MgrAction {
    private static final long serialVersionUID = -2802272636885752955L;
    private MessageDto message;
    // @Autowired
    // private IUserService userService;
    @Autowired
    private IMessageService messageService;

    public String messageView() {
        ar = fetchAppRequest();
        message = messageService.findMessage(ar, message);
        return SUCCESS;

    }

    public String myMessages() {
        if (getPageData() == null)
            setPageData(assemblePaginationInfo());
        ar = fetchAppRequest();
        setPageData(messageService.findMessagesByUserId(getPageData(), ar.getLoginId(), 1));// FIXME type
        return RESULT_PAGE_JSON;

    }

    public String batchDelete() {
        messageService.batchDelete(message);
        return RESULT_OBJECT_JSON;

    }

    public String batchMark() {
        messageService.batchMark(message);
        return RESULT_OBJECT_JSON;

    }

    public MessageDto getMessage() {
        return message;
    }

    public void setMessage(MessageDto message) {
        this.message = message;
    }

}
