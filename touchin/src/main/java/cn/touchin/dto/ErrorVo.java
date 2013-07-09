/*
 * cn.touchin.dto.ErrorVo.java
 * May 14, 2012 
 */
package cn.touchin.dto;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * May 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ErrorVo {
    private int code;
    private String name;
    private String message;
    private String stack;

    public ErrorVo(String message, int code) {
        this.code = code;
        this.message = message;
    }

    public ErrorVo(ErrorCodeVo code) {
        this(code.message(), code.code());
    }
    
    public ErrorVo(String message, ErrorCodeVo code) {
        this(message, code.code());
    }

    public ErrorVo(Throwable t, int code, boolean debug) {
        while (t.getCause() != null) {
            t = t.getCause();
        }

        this.code = code;
        this.message = t.getMessage();
        this.name = t.getClass().getName();

        if (debug) {
            StringWriter s = new StringWriter();
            PrintWriter w = new PrintWriter(s);
            t.printStackTrace(w);
            w.flush();
            this.stack = s.toString();
        }

    }

    public ErrorVo(Throwable t, ErrorCodeVo code, boolean debug) {
        this(t, code.code(), debug);
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * @param code
     *            the code to set
     */
    public void setCode(int code) {
        this.code = code;
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
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message
     *            the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the stack
     */
    public String getStack() {
        return stack;
    }

    /**
     * @param stack
     *            the stack to set
     */
    public void setStack(String stack) {
        this.stack = stack;
    }

}
