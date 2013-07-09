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
public enum WordbookType {
    PLACEHOLDER("零索引占位"),SHOP("店铺"), REGION("省份、地区、地址"), SCHOOL("学校");

    private WordbookType(String memo) {
        this.memo = memo;
    }

    private String memo;

    public String memo() {
        return memo;
    }

}
