/*
 * cn.wanto.dto.bean.ShopIndexDto.java
 * Sep 21, 2012 
 */
package cn.wanto.dto.bean;

import java.util.List;

/**
 * Sep 21, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ShopIndexViewDto {
    private List<TopicDto> hottestShops;// 热门店铺
    private List<TopicDto> newestShops;// 新店铺
    private List<TopicDto> bestShops;// 好评产品(菜式图片)

    private List<WordbookDto> hotTags;// 热门标签

    private List<WordbookDto> hotAddrs;// 热门商圈

    /**
     * @return the hottestShops
     */
    public List<TopicDto> getHottestShops() {
        return hottestShops;
    }

    /**
     * @param hottestShops
     *            the hottestShops to set
     */
    public void setHottestShops(List<TopicDto> hottestShops) {
        this.hottestShops = hottestShops;
    }

    /**
     * @return the newestShops
     */
    public List<TopicDto> getNewestShops() {
        return newestShops;
    }

    /**
     * @param newestShops
     *            the newestShops to set
     */
    public void setNewestShops(List<TopicDto> newestShops) {
        this.newestShops = newestShops;
    }

    /**
     * @return the bestShops
     */
    public List<TopicDto> getBestShops() {
        return bestShops;
    }

    /**
     * @param bestShops
     *            the bestShops to set
     */
    public void setBestShops(List<TopicDto> bestShops) {
        this.bestShops = bestShops;
    }

    /**
     * @return the hotTags
     */
    public List<WordbookDto> getHotTags() {
        return hotTags;
    }

    /**
     * @param hotTags
     *            the hotTags to set
     */
    public void setHotTags(List<WordbookDto> hotTags) {
        this.hotTags = hotTags;
    }

    /**
     * @return the hotAddrs
     */
    public List<WordbookDto> getHotAddrs() {
        return hotAddrs;
    }

    /**
     * @param hotAddrs
     *            the hotAddrs to set
     */
    public void setHotAddrs(List<WordbookDto> hotAddrs) {
        this.hotAddrs = hotAddrs;
    }

}
