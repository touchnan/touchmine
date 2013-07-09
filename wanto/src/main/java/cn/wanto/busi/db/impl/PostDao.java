package cn.wanto.busi.db.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import cn.touchin.db.hibernate.HibernateDao;
import cn.wanto.busi.db.IPostDao;
import cn.wanto.entity.Post;
import cn.wanto.entity.PostText;

@Component
public class PostDao extends HibernateDao<Post, Long> implements IPostDao {

	@Override
	public int insertPost(Post post) {

		
		return 0;
	}

	@Override
	public int insertPostText(PostText postText) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deletePostText(PostText postText) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePost(Post post) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updatePostText(PostText postText) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Post> queryPostList(Post post) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PostText> queryPostTextList(PostText postText) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
