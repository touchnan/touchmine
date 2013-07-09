package cn.wanto.busi.serv;

import java.util.List;

import cn.touchin.dto.AppRequest;
import cn.touchin.page.Pagination;
import cn.touchin.serv.IService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.PostDto;
import cn.wanto.entity.Post;

public interface IPostService extends IService<PostDto, Post, Long>{
    /**
     * 店铺话题(简单)
     * @param ar
     * @param page
     * @param topicId
     * @return
     */
    public Pagination findSimpleShopTopics(Ar ar, Pagination page, long topicId);
    
    /**
     * 店铺话题
     * @param ar
     * @param page
     * @param topicId
     * @return
     */
    public Pagination findShopTopics(Ar ar, Pagination page, long topicId);
    /**
     * 店铺话题数
     * @param ar
     * @param page
     * @param topicId
     * @return
     */
    public long countShopTopicsByTopicId(AppRequest ar,Pagination page, long topicId);
    
    /**
     * 店铺话题数
     * @param ar
     * @param topicId
     * @return
     */
    public long countShopTopicsByTopicId(AppRequest ar,long topicId);
    
    /**
     * 产品回复数
     * @param ar
     * @param topicId
     * @return
     */
    public long countReplyByProductId(AppRequest ar,long productId);
    
    /**
     * 店铺话题视图
     * @param ar
     * @param shoptopicId 店铺话题Id
     * @return
     */
    public PostDto findShoptopicView(Ar ar, Long shoptopicId);    
    
    /**
     * 店铺话题状态更新
     * 
     * @param ar
     * @param shoptopicIds
     * @param status
     */
    public int updateShoptopicStatus(Ar ar, Long[] shoptopicIds, int status);    
    
    /**
     * 话题置顶
     * @param ar
     * @param shoptopicId
     * @param top
     */
    public void topshoptopic(Ar ar, Long shoptopicId, boolean top);
    
    /**
     * 上移话题
     * @param ar
     * @param topicId
     * @param shoptopicId
     */
    public void upshoptopic(Ar ar, Long topicId, Long shoptopicId);

    /**
     * 下移话题
     * @param ar
     * @param topicId
     * @param shoptopicId
     */
    public void downshoptopic(Ar ar, Long topicId,  Long shoptopicId);    
    
    /**
     * 查找帖子和回复
     * 
     * @param ar
     * @param page
     * @param topicId
     * @return
     */
    public Pagination findPostAndRepliesByTopic(AppRequest ar , Pagination page ,long topicId);    
    
    /**
     * 店铺主题基本信息更新
     * @param ar
     * @param post
     */
    public void updateShoptopicBaseinfo(Ar ar, PostDto post);
    
    
    /**
     * 话题的回复数
     * 
     * @param ar
     * @param shoptopicId
     * @return
     */
    public long countRepies4ShopTopics(AppRequest ar,long shoptopicId);
    
    /**
     * 话题的回复
     * @param ar
     * @param page
     * @param shoptopicId
     * @return
     */
    public Pagination findRepliesByShoptopic(Ar ar, Pagination page, Long shoptopicId);    
    
    /**
     * 回复新增
     * @param ar
     * @param replyDto
     * @return
     */
    public PostDto saveReply(Ar ar, PostDto replyDto);    
    
    /**
     * @param ar
     * @param shoptopicId
     */
    public void enjoyShopTopc(Ar ar, Long shoptopicId);
    
    /**
     * @param ar
     * @param shoptopicId
     */
    public void boredShopTopic(Ar ar, Long shoptopicId);
    
    
	/**
	 * 新增
	 * @param ar
	 * @param dto
	 * @return
	 */
	public PostDto savePost(Ar ar,PostDto dto);
	
	/**
	 * 修改
	 * @param ar
	 * @param dto
	 * @return
	 */
	public PostDto updatePost(Ar ar,PostDto dto);
	
	public Pagination findPostPage4Reply(Pagination pagination , PostDto postDto);
	
	public PostDto queryPostObject(PostDto postDto);
	
	public PostDto deletePost(Ar ar,PostDto dto);
	
	public long incresPostParaCount(long postId,int flag);
	
    public Pagination findPostTextsByTopic(AppRequest ar , Pagination page ,long topicId) ;
    
//    public long countPost4Reply(AppRequest ar , PostDto dto);
    
    public List<PostDto> queryPostList4Other( PostDto dto , long num );
    
    
    
    public Pagination findMyPostTexts(Ar ar , Pagination page , int topicType) ;
    
    public long countMyPostTexts(Ar ar , Pagination page , int topicType);
    
    public void deleteReplyPost(Ar ar , Long parentPostId) ;

    /**
     * @param ar
     * @param pageData
     * @param topicType
     * @return
     */
    public Long countMyReplys(Ar ar, Pagination pageData, int topicType);

    /**
     * @param ar
     * @param pageData
     * @param postTypeReply
     * @return
     */
    public Pagination findMyReplys(Ar ar, Pagination pageData, int postTypeReply);

//    /**
//     * 
//     */
//    public Long[] findReply2GroupUser(Long userId);
    
	
}
