/*
 * cn.wanto.busi.serv.ITopicService.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.serv;

import cn.touchin.page.Pagination;
import cn.touchin.serv.IService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.ProductsDto;
import cn.wanto.dto.bean.ShopIndexViewDto;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.dto.bean.TopicView;
import cn.wanto.entity.Topic;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface ITopicService extends IService<TopicDto, Topic, Long> {

    /**
     * @param ar
     * @param topicDto
     * @return
     */
    TopicDto saveTopic(Ar ar, TopicDto topicDto);

    /**
     * 店铺index视图
     * 
     * @return
     */
    ShopIndexViewDto findShopIndexView();
    
    String findWordbookNameById(Long wordbookId);
    /**
     * 搜索店铺
     * 
     * @param ar
     * @param page
     * @return
     */
    Pagination searchShopPage(Ar ar, Pagination page);
    Integer searchShopCount(Ar ar, Pagination page);

    /**
     * 消息集合
     * 
     * @return
     */
    Pagination findShopByUserId(Pagination page, Long userId);

    /**
     * @param userId
     * @param type
     * @return
     */
    Long findShopCountByUserId(Long userId);

    /**
     * 店铺视图
     * 
     * @param ar
     * @param topicId
     * @return
     */
    TopicView topicView(Ar ar, Long topicId);

    /**
     * 喜欢
     * 
     * @param ar
     * @param topicId
     */
    void enjoyTopc(Ar ar, Long topicId);

    /**
     * 不喜欢
     * 
     * @param ar
     * @param topicId
     */
    void boredTopic(Ar ar, Long topicId);

    /**
     * 店铺修改信息
     * 
     * @param ar
     * @param topicId
     * @return
     */
    TopicDto fetchTopic4Edit(Ar ar, Long topicId);

    /**
     * 店铺的简单信息
     * 
     * @param ar
     * @param topicId
     * @return
     */
    TopicDto fetchTopicSimpleInfo4Manage(Ar ar, Long topicId);

    /**
     * 创建店铺产品
     * 
     * @param ar
     * @param shopId
     * @param products
     */
    void saveProducts(Ar ar, Long shopId, ProductsDto products);

    /**
     * @param ar
     * @param topicId
     * @param page
     * @return
     */
    Pagination findShopProductsPage(Ar ar, Long shopId, Pagination page);

    /**
     * 取得店铺的产品数
     * 
     * @param shopId
     * @return
     */
    Long findShopProductCount(Ar ar, Long shopId, int status);

    /**
     * @param ar
     * @param topicId
     * @param status
     * @return
     */
    int updateStatus(Ar ar, Long topicId, int status);

    /**
     * 产品视图
     * 
     * @param ar
     * @param productId
     * @return
     */
    TopicView productView(Ar ar, Long productId);

    /**
     * 修改产品基本信息
     * 
     * @param ar
     * @param topicDto
     */
    void updateProductBaseinfo(Ar ar, TopicDto topicDto);

    /**
     * 头像
     * 
     * @param ar
     * @param id
     * @return
     */
    TopicDto fetchTopic4Avatar(Ar ar, Long id);

    /**
     * 修改图片
     * 
     * @param ar
     * @param topicDto
     * @return
     */
    TopicDto saveTopicAvatar(Ar ar, TopicDto topicDto);

    /**
     * 批量修改
     * 
     * @param ar
     * @param topicIds
     * @param status
     * @return
     */
    int updateStatus(Ar ar, Long[] topicIds, int status);

}
