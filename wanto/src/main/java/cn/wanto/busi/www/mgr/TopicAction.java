/*
 * cn.wanto.busi.www.TopicAction.java
 * Sep 3, 2012 
 */
package cn.wanto.busi.www.mgr;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.page.Pagination;
import cn.touchin.page.flexi.FlexiFilter;
import cn.touchin.page.flexi.FlexiRule;
import cn.touchin.page.flexi.FlexiRule.FilterSopt;
import cn.wanto.busi.serv.IPostService;
import cn.wanto.busi.serv.ITopicService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.ProductsDto;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.util.AppConstants;

/**
 * Sep 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicAction extends MgrAction {
    private static final long serialVersionUID = 6070997952371467498L;

    private TopicDto topic;
    private Long topicId;
    private int status = AppConstants.ALL_VALIDATE_TAG;

    // private List<TopicDto> products;

    private ProductsDto products;

    @Autowired
    private ITopicService topicService;
    @Autowired
    private IPostService postService;

    public String shopmanage() throws Exception {
        ar = fetchAppRequest();
        setCount(topicService.findShopCountByUserId(ar.getLoginId()));
        // 店铺管理
        return SUCCESS;
    }

    public String myShops() {
        // 我的店铺
        if (getPageData() == null)
            setPageData(assemblePaginationInfo());
        ar = fetchAppRequest();
        setPageData(topicService.findShopByUserId(getPageData(), ar.getLoginId()));
        return RESULT_PAGE_JSON;

    }

    public String shopcreate() throws Exception {
        // 新增
        return SUCCESS;
    }

    public String shopedit() throws Exception {
        // 修改店铺
        topic = topicService.fetchTopic4Edit(fetchAppRequest(), getId());
        return SUCCESS;
    }

    public String shopavatar() throws Exception {
        // 修改店铺图片
        topic = topicService.fetchTopic4Avatar(fetchAppRequest(), getId());
        return SUCCESS;
    }

    public String shopsave() throws Exception {
        // 店铺保存
        topic = topicService.saveTopic(fetchAppRequest(), topic);
        this.setObj(topic.getId());
        return RESULT_OBJECT_JSON;
    }

    public String avatarsave() throws Exception {
        // 修改店铺或产品头像
        topic = topicService.saveTopicAvatar(fetchAppRequest(), topic);
        return RESULT_OBJECT_JSON;
    }
    
    public String shoptoipcmanage() throws Exception {
        // 店铺主题管理
        Ar ar = fetchAppRequest();
        topic = topicService.fetchTopicSimpleInfo4Manage(ar, getId());
        setCount(postService.countShopTopicsByTopicId(ar, getId()));
        return SUCCESS;
    }
    
    public String delshoptopic() throws Exception {
        //删除话题主题
        postService.updateShoptopicStatus(fetchAppRequest(), super.ids_a(), AppConstants.DEL_TAG);
        return RESULT_OBJECT_JSON;
    }
    
    public String topshoptopic() throws Exception {
        //主题置顶
        postService.topshoptopic(fetchAppRequest(), super.getId(), true);
        return RESULT_OBJECT_JSON;
    }
    
    public String untopshoptopic() throws Exception {
        //取消置顶
        postService.topshoptopic(fetchAppRequest(), super.getId(), false);
        return RESULT_OBJECT_JSON;
    }
    
    public String upshoptopic() throws Exception {
        //主题上移
        postService.upshoptopic(fetchAppRequest(), getTopicId(), super.getId());
        return RESULT_OBJECT_JSON;
    }
    
    public String downshoptopic() throws Exception {
        //主题下移
        postService.downshoptopic(fetchAppRequest(), getTopicId(), super.getId());
        return RESULT_OBJECT_JSON;
    }
    
    
    public String shoptopics() {
        // 店铺主题
        setPageData(postService.findShopTopics(fetchAppRequest(), assemblePaginationInfo(), getTopicId()));
        return RESULT_PAGE_JSON;
    }    
    
    public String productmanage() throws Exception {
        // 产品管理
        Ar ar = fetchAppRequest();
        topic = topicService.fetchTopicSimpleInfo4Manage(ar, getId());
        setCount(topicService.findShopProductCount(ar, getId(), getStatus()));
        return SUCCESS;
    }

    public String productcount() throws Exception {
        // 产品个数
        setObj(topicService.findShopProductCount(ar, getId(), getStatus()));
        return RESULT_OBJECT_JSON;
    }

    public String products() {
        // 店铺的产品列表
        ar = fetchAppRequest();

        Pagination page = assemblePaginationInfo();
        switch (getStatus()) {
        case AppConstants.ALL_VALIDATE_TAG:
            break;
        default:
            /*-
            // filters:'{"groupOp":"AND","rules":[{"field":"xxx","op":"eq","data":"45"}]}'
            this.setFilters("{\"groupOp\":\"AND\",\"rules\":[{\"field\":\"I_tp.topic_status\",\"op\":\"eq\",\"data\":\""
                    + getStatus() + "\"}]}");
            */
            FlexiFilter ft = new FlexiFilter();
            ft.setGroupOp(FlexiFilter.NAME_GROUPOP_AND);
            List<FlexiRule> rules = new ArrayList<FlexiRule>(1); 
            rules.add(new FlexiRule().rule(FilterSopt.eq, "I_tp.topic_status", Integer.toString(getStatus())));
            ft.setRules(rules);
            page.setFlexiFilter(ft);
            break;
        }
        
        setPageData(topicService.findShopProductsPage(ar, getTopicId(), page));
        return RESULT_PAGE_JSON;

    }

    public String productcreate() throws Exception {
        // 产品新建
        return SUCCESS;
    }

    public String productedit() throws Exception {
        // 产品修改
        return SUCCESS;
    }

    public String productavatar() throws Exception {
        // 修改产品图片
        topic = topicService.fetchTopic4Avatar(fetchAppRequest(), getId());
        return SUCCESS;
    }

    public String productssave() {
        // 产品新增
        if (products != null)
            topicService.saveProducts(fetchAppRequest(), getTopicId(), products);
        return RESULT_OBJECT_JSON;
    }

    public String producteditbaseinfo() {
        // 修改产品标题或内容或价格
        topicService.updateProductBaseinfo(fetchAppRequest(), topic);
        return RESULT_JSON;
    }

    public String shelfcarriage() {
        // 上架
        topicService.updateStatus(fetchAppRequest(), getId(), AppConstants.SHELFCARRIAGE_TAG);
        return RESULT_OBJECT_JSON;
    }

    public String undercarriage() {
        // 下架
        topicService.updateStatus(fetchAppRequest(), getId(), AppConstants.UNDERCARRIAGE_TAG);
        return RESULT_OBJECT_JSON;
    }

    public String undercarriages() {
        // 批量下架
        topicService.updateStatus(fetchAppRequest(), super.ids_a(), AppConstants.UNDERCARRIAGE_TAG);
        return RESULT_OBJECT_JSON;
    }

    public String delproducts() {
        // 批量删除
        topicService.updateStatus(fetchAppRequest(), super.ids_a(), AppConstants.DEL_TAG);
        return RESULT_OBJECT_JSON;
    }

    public String enjoyTopic() {
        topicService.enjoyTopc(fetchAppRequest(), getId());
        return RESULT_OBJECT_JSON;
    }

    public String boredTopic() {
        topicService.boredTopic(fetchAppRequest(), getId());
        return RESULT_OBJECT_JSON;
    }

    public String showlist() throws Exception {
        return SUCCESS;
    }

    /**
     * @return the topic
     */
    public TopicDto getTopic() {
        return topic;
    }

    /**
     * @param topic
     *            the topic to set
     */
    public void setTopic(TopicDto topic) {
        this.topic = topic;
    }

    /**
     * @return the topicId
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the products
     */
    public ProductsDto getProducts() {
        return products;
    }

    /**
     * @param products
     *            the products to set
     */
    public void setProducts(ProductsDto products) {
        this.products = products;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

}
