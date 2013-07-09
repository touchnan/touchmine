/*
 * cn.wanto.busi.www.mgr.FileAction.java
 * Sep 7, 2012 
 */
package cn.wanto.busi.www.mgr;

import java.io.File;

import org.nutz.lang.Files;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touch.kit.MethodResult;
import cn.wanto.busi.tools.ImageFactory;
import cn.wanto.busi.tools.ImagePath;
import cn.wanto.busi.www.AppPaginationAction;

/**
 * Sep 7, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FileAction extends AppPaginationAction {
    private static final long serialVersionUID = -2927640474883580420L;

    private Log log = Logs.getLog(FileAction.class);

    private File upfile;
    private String upfileContentType;
    private String upfileFileName;

    @Autowired
    private ImageFactory imageFactory;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.busi.www.FlexiPaginationSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        // imageFactory.createAvatar(upfile);
        // setObj(new ImageResult(false,"出错啦"));
        return RESULT_OBJECT_JSON;
    }

    public String avatar() throws Exception {
        try {
            ImagePath ir = imageFactory.createAvatar(upfile, Files.getSuffixName(upfileFileName));
            ir.setShowname(upfileFileName);
            setObj(ir);
        } catch (Exception e) {
            setObj(new MethodResult(false, e.getMessage()));
            log.error(e);
        }
        return RESULT_OBJECT_JSON;
    }

    public String topicicon() throws Exception {
        try {
            ImagePath ir = imageFactory.createTopicicon(upfile, Files.getSuffixName(upfileFileName));
            ir.setShowname(upfileFileName);
            setObj(ir);
        } catch (Exception e) {
            setObj(new MethodResult(false, e.getMessage()));
            log.error(e);
        }
        return RESULT_OBJECT_JSON;
    }
    
    public String producticon() throws Exception {
        try {
            ImagePath ir = imageFactory.createProducticon(upfile, Files.getSuffixName(upfileFileName));
            ir.setShowname(upfileFileName);
            setObj(ir);
        } catch (Exception e) {
            setObj(new MethodResult(false, e.getMessage()));
            log.error(e);
        }
        return RESULT_OBJECT_JSON;
    }    

    /**
     * @return the upfile
     */
    public File getUpfile() {
        return upfile;
    }

    /**
     * @param upfile
     *            the upfile to set
     */
    public void setUpfile(File upfile) {
        this.upfile = upfile;
    }

    /**
     * @return the upfileContentType
     */
    public String getUpfileContentType() {
        return upfileContentType;
    }

    /**
     * @param upfileContentType
     *            the upfileContentType to set
     */
    public void setUpfileContentType(String upfileContentType) {
        this.upfileContentType = upfileContentType;
    }

    /**
     * @return the upfileFileName
     */
    public String getUpfileFileName() {
        return upfileFileName;
    }

    /**
     * @param upfileFileName
     *            the upfileFileName to set
     */
    public void setUpfileFileName(String upfileFileName) {
        this.upfileFileName = upfileFileName;
    }

}
