/*
 * cn.touchin.dto.ResponseVo.java
 * May 14, 2012 
 */
package cn.touchin.dto;

/**
 * May 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ResponseVo {
    private Object result;
    private ErrorVo errorVo;

    /**
     * @return the result
     */
    public Object getResult() {
        return result;
    }

    /**
     * @param result
     *            the result to set
     */
    public void setResult(Object result) {
        this.result = result;
    }

    /**
     * @return the errorVo
     */
    public ErrorVo getErrorVo() {
        return errorVo;
    }

    /**
     * @param errorVo
     *            the errorVo to set
     */
    public void setErrorVo(ErrorVo errorVo) {
        this.errorVo = errorVo;
    }

}
