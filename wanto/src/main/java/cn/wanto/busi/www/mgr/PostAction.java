package cn.wanto.busi.www.mgr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.wanto.busi.serv.IMessageService;
import cn.wanto.busi.serv.IPostService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.PostDto;

public class PostAction extends MgrAction {

    private static final long serialVersionUID = -7896844504965871706L;

    @Autowired
    private IMessageService messageService;
    @Autowired
    private IPostService postService;

    private PostDto post;
    private long topicId;

    private PostDto postDto;
    private List<PostDto> list_post;
    private Long postId;
    private int topicType;

    public String shoptopicbaseinfo() {
        // 店铺主题修改标题或内容
        postService.updateShoptopicBaseinfo(fetchAppRequest(), post);
        return RESULT_JSON;
    }

    public String shoptopiccreate() throws Exception {
        // 店铺主题新增
        return SUCCESS;
    }

    public String shoptopicSave() throws Exception {
        // 主题发帖
        post = postService.savePost(fetchAppRequest(), post);
        this.setObj(post.getId());
        return RESULT_OBJECT_JSON;
    }

    public String reply() throws Exception {
        // 回复
        post = postService.saveReply(fetchAppRequest(), post);
        return RESULT_OBJECT_JSON;
    }

    public String enjoyTopic() {
        //顶
        postService.enjoyShopTopc(fetchAppRequest(), getId());
        return RESULT_OBJECT_JSON;
    }

    public String boredTopic() {
        //踩
        postService.boredShopTopic(fetchAppRequest(), getId());
        return RESULT_OBJECT_JSON;
    }

    // /**
    // * 发帖
    // *
    // * @return
    // * @throws Exception
    // */
    // public String postCreate() throws Exception {
    // return SUCCESS;
    // }

    // /**
    // * 帖子编辑
    // *
    // * @return
    // * @throws Exception
    // */
    // public String postEdit() throws Exception {
    //
    // Long id = Long.parseLong(this.getHttpServletRequest().getParameter("id"));
    // postDto.setId(id);
    // postDto = postService.queryPostObject(postDto);
    // postDto.setParentId(id);
    // this.setCount(postService.countPost4Reply(getAr(), postDto));
    // list_post = postService.queryPostList4Other(postDto, AppConstants.POST_SHOW_COUNT);
    // return SUCCESS;
    // }

    // /**
    // * 帖子回复
    // *
    // * @return
    // * @throws Exception
    // */
    // public String postReplySubmit() throws Exception {
    //
    // ar = fetchAppRequest();
    // postDto.setTime(new Date());
    // postDto = postService.savePost(ar, postDto);
    //
    // this.setObj(postDto.getId());
    // return RESULT_OBJECT_JSON;
    // }

    /**
     * 删除帖子
     * 
     * @return
     * @throws Exception
     */
    public String deletePost() {

        Long postId = Long.parseLong(this.getHttpServletRequest().getParameter("postId"));
        postDto.setId(postId);

        ar = fetchAppRequest();

        try {
            // postDto = postService.deletePost(ar, postDto);
            postService.deletePost(ar, postDto);
        } catch (Exception e) {
            return RESULT_ERROR_JSON;
        }
        // try {
        // for (Long userId : postDto.getIds()) {
        // messageService.insertMessage(ar, userId, MessageType.SYSTEMNEWS.ordinal(),
        // MessageKind.POSTDELETE.ordinal(), null, null, postId);
        // }
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        return RESULT_OBJECT_JSON;
    }

    /**
     * 删除回复的帖子
     * 
     * @return
     */

    public String deleteReplyPost() {
        ar = fetchAppRequest();
        postService.deleteReplyPost(ar, postId);
        return RESULT_OBJECT_JSON;
    }

    // /**
    // * 更新贴子
    // *
    // * @return
    // */
    // public String updatePost() {
    //
    // ar = fetchAppRequest();
    // postService.updatePost(ar, postDto);
    //
    // return RESULT_OBJECT_JSON;
    // }

    public String myPosts() {
        ar = fetchAppRequest();
        setPageData(postService.findMyPostTexts(ar, getPageData(), topicType));
        return RESULT_PAGE_JSON;
    }

    public String myReplys() {
        ar = fetchAppRequest();
        setPageData(postService.findMyReplys(ar, getPageData(), topicType));
        return RESULT_PAGE_JSON;
    }

    public PostDto getPostDto() {
        return postDto;
    }

    public void setPostDto(PostDto postDto) {
        this.postDto = postDto;
    }

    public IPostService getPostService() {
        return postService;
    }

    public void setPostService(IPostService postService) {
        this.postService = postService;
    }

    public List<PostDto> getList_post() {
        return list_post;
    }

    public void setList_post(List<PostDto> list_post) {
        this.list_post = list_post;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the postId
     */
    public Long getPostId() {
        return postId;
    }

    /**
     * @param postId
     *            the postId to set
     */
    public void setPostId(Long postId) {
        this.postId = postId;
    }

    /**
     * @return the topicType
     */
    public int getTopicType() {
        return topicType;
    }

    /**
     * @param topicType
     *            the topicType to set
     */
    public void setTopicType(int topicType) {
        this.topicType = topicType;
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

}
