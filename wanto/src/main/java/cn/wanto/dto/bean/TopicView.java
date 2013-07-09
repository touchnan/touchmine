/*
 * cn.wanto.dto.bean.TopicView.java
 * Sep 10, 2012 
 */
package cn.wanto.dto.bean;

import java.util.List;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicView extends TopicDto {
    private static final long serialVersionUID = 5003651377435844569L;

    private List<? extends Object> tags;// 标签
    private List<TopicView> topicProducts;// 菜式

    private UserDto owner;// 推荐人－店长

    /**
     * @return the tags
     */
    public List<? extends Object> getTags() {
        return tags;
    }

    /**
     * @param tags the tags to set
     */
    public void setTags(List<? extends Object> tags) {
        this.tags = tags;
    }

    /**
     * @return the topicProducts
     */
    public List<TopicView> getTopicProducts() {
        return topicProducts;
    }

    /**
     * @param topicProducts
     *            the topicProducts to set
     */
    public void setTopicProducts(List<TopicView> topicProducts) {
        this.topicProducts = topicProducts;
    }

    /**
     * @return the owner
     */
    public UserDto getOwner() {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

}
