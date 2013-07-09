/*
 * cn.wanto.busi.www.site.TopicAction.java
 * Aug 30, 2012 
 */
package cn.wanto.busi.www.site;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.page.Pagination;
import cn.touchin.page.flexi.FlexiFilter;
import cn.touchin.page.flexi.FlexiRule;
import cn.touchin.page.flexi.FlexiRule.FilterSopt;
import cn.wanto.busi.serv.IPostService;
import cn.wanto.busi.serv.ITopicService;
import cn.wanto.busi.www.AppPaginationAction;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.ShopIndexViewDto;
import cn.wanto.dto.bean.TopicView;
import cn.wanto.util.AppConstants;

/**
 * Aug 30, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicAction extends AppPaginationAction {
    private static final long serialVersionUID = 3803923674880937738L;

    private TopicView topicView;
    private Long count;

    private ShopIndexViewDto indexView;

    @Autowired
    private ITopicService topicService;
    @Autowired
    private IPostService postService;
    
    private long schRid;//地区商圈
    private String schRname;
    private long schKid;//类型
    private String schKname;
    private String sch; //主题

    public String shops() throws Exception {
        // 店铺
//        hottestShops = topicService.findHottestShops();
//        newestShops = topicService.findNewestShops();
//        bestShops = topicService.findBestProducts();
        indexView = topicService.findShopIndexView();
        return SUCCESS;
    }

    public String shopview() throws Exception {
        // 店铺视图
        Ar ar = fetchAppRequest();
        topicView = topicService.topicView(ar, getId());
        setCount(postService.countShopTopicsByTopicId(ar, getId()));
        return SUCCESS;
    }

     public String qshops() throws Exception {
         //搜索店铺
         setCount(Long.valueOf(topicService.searchShopCount(fetchAppRequest(), assembleSearchPagination())));
         
         if (getSchRid()>0) {
             this.setSchRname(topicService.findWordbookNameById(Long.valueOf(getSchRid())));
         }
         
         if (getSchKid()>0) {
             this.setSchKname(topicService.findWordbookNameById(Long.valueOf(getSchKid())));
         }
         return SUCCESS;
     }

     private Pagination assembleSearchPagination() {
         Pagination page = assemblePaginationInfo();
         if (!Strings.isBlank(sch) || getSchRid()>0 || getSchKid()>0) {
             FlexiFilter ft = new FlexiFilter();
             ft.setGroupOp(FlexiFilter.NAME_GROUPOP_AND);
             List<FlexiRule> rules = new ArrayList<FlexiRule>(); 
             if (!Strings.isBlank(sch)) {
                 //杂志编号 或者 店铺id号，或者 title
                 sch = sch.trim();
                 if (sch.length()==AppConstants.LEN_SHOP_IDENTITY && StringUtils.isNumeric(sch)) {
                     rules.add(new FlexiRule().rule(FilterSopt.eq, "L_tpe.topic_identity", sch));
                 } else if (sch.startsWith("xs") && sch.length()==11) {
                     rules.add(new FlexiRule().rule(FilterSopt.eq, "S_tpe.topic_quota", sch));
                     //rules.add(new FlexiRule().rule(FilterSopt.bw, "S_tpe.topic_quota", sch));
                 } else {
                     rules.add(new FlexiRule().rule(FilterSopt.cn, "S_tp.topic_title", sch));
                 }
             }
             
             if (getSchRid()>0) {
                 rules.add(new FlexiRule().rule(FilterSopt.cn, "S_tpe.topic_region", AppConstants.joinByPound(getSchRid())));
             }
             
             if (getSchKid()>0) {
                 rules.add(new FlexiRule().rule(FilterSopt.cn, "S_tpe.topic_kind", AppConstants.joinByPound(getSchKid())));
             }
             ft.setRules(rules);
             page.setFlexiFilter(ft);
         }
         return page;
     }
     
     public String sshops() throws Exception{
         setPageData(topicService.searchShopPage(fetchAppRequest(), assembleSearchPagination()));
         return RESULT_PAGE_JSON;
     }
     
    public String productview() throws Exception {
        Ar ar = fetchAppRequest();
        topicView = topicService.productView(ar, getId());
        setCount(postService.countReplyByProductId(ar, getId()));
        return SUCCESS;
    }

    public String productposts() throws Exception {
        setPageData(postService.findPostAndRepliesByTopic(fetchAppRequest(), assemblePaginationInfo(), getId()));
        return RESULT_PAGE_JSON;
    }

    // public String shop() throws Exception {
    // return SUCCESS;
    // }
    //
    // public String posts() throws Exception {
    // //
    // return SUCCESS;
    // }
    //
    // public String post() throws Exception {
    // return SUCCESS;
    // }

    /**
     * @return the topicView
     */
    public TopicView getTopicView() {
        return topicView;
    }

    /**
     * @return the count
     */
    public Long getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(Long count) {
        this.count = count;
    }

    /**
     * @return the indexView
     */
    public ShopIndexViewDto getIndexView() {
        return indexView;
    }

    /**
     * @param indexView the indexView to set
     */
    public void setIndexView(ShopIndexViewDto indexView) {
        this.indexView = indexView;
    }

  

    /**
     * @return the schRid
     */
    public long getSchRid() {
        return schRid;
    }

    /**
     * @param schRid the schRid to set
     */
    public void setSchRid(long schRid) {
        this.schRid = schRid;
    }

    /**
     * @return the schKid
     */
    public long getSchKid() {
        return schKid;
    }

    /**
     * @param schKid the schKid to set
     */
    public void setSchKid(long schKid) {
        this.schKid = schKid;
    }

    /**
     * @return the sch
     */
    public String getSch() {
        return sch;
    }

    /**
     * @param sch the sch to set
     */
    public void setSch(String sch) {
        this.sch = sch;
    }

    /**
     * @return the schRname
     */
    public String getSchRname() {
        return schRname;
    }

    /**
     * @param schRname the schRname to set
     */
    public void setSchRname(String schRname) {
        this.schRname = schRname;
    }

    /**
     * @return the schKname
     */
    public String getSchKname() {
        return schKname;
    }

    /**
     * @param schKname the schKname to set
     */
    public void setSchKname(String schKname) {
        this.schKname = schKname;
    }
    
}
