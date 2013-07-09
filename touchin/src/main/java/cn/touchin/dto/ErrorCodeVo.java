/*
 * cn.touchin.dto.ErrorCodeVo.java
 * May 14, 2012 
 */
package cn.touchin.dto;

/**
 * May 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public enum ErrorCodeVo {
    METHOD_NOT_FOUND(101, "METHOD not found"), PARAMETERS_MISMATCH(102,
            "Parameters count in request does not patch parameters count on method"), EXCEPTION_THROWN(101,
            "A exception should be thrown"), EXCEPTION_UNCATCHED(103, "An uncatched exception was thrown"), EXCEPTION_UNKNOWN(
            103, "An unknown exception was thrown");

    private int code;
    private String message;

    ErrorCodeVo(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String message() {
        return this.message;
    }
}
