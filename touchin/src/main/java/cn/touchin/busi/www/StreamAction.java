/*
 * cn.touchin.busi.www.StreamAction.java
 * May 27, 2012 
 */
package cn.touchin.busi.www;

import java.io.InputStream;

import org.nutz.lang.Files;

/**
 * May 27, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class StreamAction extends BaseAction {
    private static final long serialVersionUID = 1281094358846631326L;
    private String fileName;
    private String showName;

    public String down() throws Exception {
        return RESULT_DOWN;
    }

    public InputStream getStream() {
        InputStream is = Files.findFileAsStream(fileName);
        return is;
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

}
