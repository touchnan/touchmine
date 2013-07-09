/*
 * cn.wanto.busi.www.bg.BackMangeAction.java
 * Sep 9, 2012 
 */
package cn.wanto.busi.www.bg;

import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.page.Pagination;
import cn.wanto.busi.serv.IMessageService;
import cn.wanto.busi.serv.IWordbookService;
import cn.wanto.busi.www.mgr.MgrAction;
import cn.wanto.dto.bean.WordbookDto;

/**
 * Sep 9, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class BgMgrAction extends MgrAction {
    private static final long serialVersionUID = 2840846816618447189L;

    private WordbookDto wordbook;

    @Autowired
    private IWordbookService wordbookService;

    @Autowired
    private IMessageService messageService;

    public String messages() throws Exception {
        return SUCCESS;
    }

    public String listMessages() throws Exception {
        Pagination page = assemblePaginationInfo();
        this.setPageData(messageService.findMessages2BgMess(page));
        return RESULT_PAGE_JSON;
    }

    public String wordbooks() throws Exception {
        return SUCCESS;
    }

    public String listWordbooks() throws Exception {
        this.setPageData(wordbookService.findPage(fetchAppRequest(), assemblePaginationInfo()));
        return RESULT_PAGE_JSON;
    }

    public String saveWordbook() throws Exception {
        if (isDel()) {
            wordbookService.deletes(fetchAppRequest(), ids_a());
        } else {
            wordbookService.save(fetchAppRequest(), wordbook);
        }
        return RESULT_JSON;
    }

    /**
     * @return the wordbook
     */
    public WordbookDto getWordbook() {
        return wordbook;
    }

    /**
     * @param wordbook
     *            the wordbook to set
     */
    public void setWordbook(WordbookDto wordbook) {
        this.wordbook = wordbook;
    }

}
