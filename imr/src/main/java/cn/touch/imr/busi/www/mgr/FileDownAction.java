/*
 * cn.touchin.busi.www.FileDownAction.java
 * Mar 6, 2012 
 */
package cn.touch.imr.busi.www.mgr;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touch.imr.busi.serv.IQueryService;
import cn.touch.imr.busi.www.AppPaginationAction;
import cn.touch.kit.template.TemplateKit;

/**
 * Mar 6, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FileDownAction extends AppPaginationAction {
    private static final long serialVersionUID = -1912827502426547333L;

    private InputStream stream;// 数据流
    private String showName;// 文件名
    private String dataKey;// 数据模型的KEY
    private String fileName;// 模板名

    @Autowired
    private IQueryService queryService;
//    @Autowired
//    private IUserService userService;
    
    @Autowired
    private TemplateKit templateKit;    
    
    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.FlexiPaginationSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }
    
    public String toDown() throws Exception {
        this.setLists(queryService.findUsers(fetchAppRequest()));
        return SUCCESS;
    }
    
    public String toFtl() throws Exception {
        this.setLists(queryService.findUsers(fetchAppRequest()));
        return RESULT_FREEMARKER;
    }

    public String down() throws Exception {
        genTemplateStream(queryService.findUsers(fetchAppRequest()));
        return RESULT_DOWN;
    }

    private void genTemplateStream(Object obj) {
        Map<String, Object> data = new HashMap<String, Object>();
        data.put(Strings.isBlank(getDataKey()) ? "data" : getDataKey(), obj);
        stream = templateKit.genInputStream(getFileName(), data);
    }

    /**
     * @return the stream
     */
    public InputStream getStream() {
        return stream;
    }

    /**
     * @return the showName
     */
    public String getShowName() {
        return showName;
    }

    /**
     * @param showName
     *            the showName to set
     */
    public void setShowName(String showName) {
        this.showName = showName;
    }

    /**
     * @return the dataKey
     */
    public String getDataKey() {
        return dataKey;
    }

    /**
     * @param dataKey
     *            the dataKey to set
     */
    public void setDataKey(String dataKey) {
        this.dataKey = dataKey;
    }

    /**
     * @return the fileName
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @param fileName
     *            the fileName to set
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
