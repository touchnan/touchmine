/*
 * cn.touchin.dto.DispatcherVo.java
 * May 14, 2012 
 */
package cn.touchin.dto;

/**
 * May 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class DispatcherVo {
    private String url;

    public DispatcherVo() {
        super();
    }

    public DispatcherVo(String url) {
        super();
        this.url = url;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
