/*
 * cn.wanto.util.enume.WordbookType.java
 * Sep 9, 2012 
 */
package cn.wanto.util.enume;

/**
 * Sep 9, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public enum MessageKind {
    PLACEHOLDER("零索引占位"), WELCOME("欢迎加入"), POSTDELETE("帖子被删"), LEAVEWORDDELETE("帖子回复被删");

    private MessageKind(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

}
