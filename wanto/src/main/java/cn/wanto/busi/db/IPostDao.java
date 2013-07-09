package cn.wanto.busi.db;

import java.util.List;

import cn.touchin.db.hibernate.IHibernateDao;
import cn.wanto.entity.Post;
import cn.wanto.entity.PostText;

public interface IPostDao extends IHibernateDao<Post, Long>{
	
	public int insertPost(Post post);
	
	public int insertPostText(PostText postText);
	
	public int deletePost(Post post);
	
	public int deletePostText(PostText postText);
	
	public int updatePost(Post post);
	
	public int updatePostText(PostText postText);
	
	public List<Post> queryPostList(Post post);
	
	public List<PostText> queryPostTextList(PostText postText);
	

}
