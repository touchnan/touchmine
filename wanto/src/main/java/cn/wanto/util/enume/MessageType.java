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
public enum MessageType {
    PLACEHOLDER("零索引占位"), SYSTEMNEWS("系统消息"), SHOP("店铺"), ACTIVITIES("活动"),CIRCLE("圈子");

    private MessageType(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

}
