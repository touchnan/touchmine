package cn.wanto.busi.www.site;

import org.springframework.beans.factory.annotation.Autowired;

import cn.wanto.busi.serv.IPostService;
import cn.wanto.busi.www.AppPaginationAction;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.PostDto;

public class PostAction extends AppPaginationAction {
    private static final long serialVersionUID = -4051218699502177982L;

    @Autowired
    private IPostService postService;

    private PostDto post;
    private long topicId;
    private long count;

    public String shopTopics() {
        // 店铺主题
        // setPageData(postService.findPostTextsByTopic(fetchAppRequest(), assemblePaginationInfo(), get));
        setPageData(postService.findSimpleShopTopics(fetchAppRequest(), assemblePaginationInfo(), getTopicId()));
        return RESULT_PAGE_JSON;
    }

    public String shoptopic() {
        // 店铺主题视图
        Ar ar = fetchAppRequest();
        post = postService.findShoptopicView(ar, getId());
        // 主题回复数
        this.setCount(postService.countRepies4ShopTopics(ar, getId()));
        return SUCCESS;

    }

    public String shoptopicReplis() {
        // 话题的回复
        setPageData(postService.findRepliesByShoptopic(fetchAppRequest(), assemblePaginationInfo(), getId()));
        return RESULT_PAGE_JSON;
    }

    /**
     * @return the topicId
     */
    public long getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    // /**
    // * 帖子展示页面
    // * @return
    // * @throws Exception
    // */
    // public String postShow() throws Exception{
    //
    // Long postId = Long.parseLong(this.getHttpServletRequest().getParameter("postId"));
    // post.setId(postId);
    // postService.incresPostParaCount(postId, AppConstants.POST_COUNT_TYPE_CLICK);
    //
    // post = postService.queryPostObject(post);
    // PostDto dto = new PostDto();
    // dto.setParentId(postId);
    //
    // dto.setType(AppConstants.POST_TYPE_REPLY);
    // return SUCCESS;
    // }
    //
    // /***
    // * 增加帖子点击数等等
    // * @return
    // */
    // public String incresPostParaCount() throws Exception{
    //
    // String type = this.getHttpServletRequest().getParameter("flag");
    // int flag = 0;
    // if(type != null && !"".equals(type)){
    // flag = Integer.parseInt(type);
    // }
    // String post_id = this.getHttpServletRequest().getParameter("postId");
    // long postId = 0;
    // if(post_id != null && !"".equals(post_id)){
    // postId = Long.parseLong(post_id);
    // }
    // long count = postService.incresPostParaCount(postId,flag);
    // this.setObj(count);
    // return RESULT_OBJECT_JSON;
    // }

    // public String post4Reply(){
    // // this.setPageData(postService.findPostPage4Reply(getPageData(), getPostDto()));
    // return RESULT_PAGE_JSON ;
    // }

    /**
     * @return the count
     */
    public long getCount() {
        return count;
    }

    /**
     * @param count
     *            the count to set
     */
    public void setCount(long count) {
        this.count = count;
    }

    /**
     * @return the post
     */
    public PostDto getPost() {
        return post;
    }

    /**
     * @param post
     *            the post to set
     */
    public void setPost(PostDto post) {
        this.post = post;
    }

}
