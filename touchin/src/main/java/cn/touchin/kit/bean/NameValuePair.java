/*
 * cn.touchin.util.NameValuePair.java
 * Feb 12, 2012 
 */
package cn.touchin.kit.bean;

import java.io.Serializable;

/**
 * Feb 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class NameValuePair implements Serializable {
    private static final long serialVersionUID = 6892294077932016970L;

    private String name;
    private Object value;

    public NameValuePair(String name, Object value) {
        super();
        this.name = name;
        this.value = value;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value
     *            the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }
}
