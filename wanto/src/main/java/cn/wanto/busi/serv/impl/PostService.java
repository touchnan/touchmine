package cn.wanto.busi.serv.impl;

import java.util.Date;
import java.util.List;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.db.jdbc.PageQuery;
import cn.touchin.db.jdbc.SQLRunner;
import cn.touchin.dto.AppRequest;
import cn.touchin.page.Pagination;
import cn.wanto.busi.db.IPostDao;
import cn.wanto.busi.serv.IPostService;
import cn.wanto.concurrent.ThreadPool;
import cn.wanto.dto.Ar;
import cn.wanto.dto.Composition;
import cn.wanto.dto.bean.PostDto;
import cn.wanto.entity.Post;
import cn.wanto.entity.PostText;
import cn.wanto.entity.ShopTopicVote;
import cn.wanto.event.PostReplyCreateTask;
import cn.wanto.event.ShopTopicCreateTask;
import cn.wanto.event.ShopTopicEditTask;
import cn.wanto.event.ShopTopicVoteTask;
import cn.wanto.event.ShoptopicViewTask;
import cn.wanto.util.AppConstants;

@Component
@Transactional(readOnly = true)
public class PostService extends AppService<PostDto, Post, Long> implements IPostService {

    @Autowired
    private IPostDao postDao;

    @Override
    public IHibernateDao<Post, Long> dao() {
        return postDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findShopTopics(cn.wanto.dto.Ar, cn.touchin.page.Pagination, long)
     */
    @Override
    public Pagination findSimpleShopTopics(Ar ar, Pagination page, long topicId) {
        PageQuery pq =
                sqlParser.createPageQuery(new SQLRunner(page)
                        .select(" p.post_id  as id,p.is_top AS totop, p.post_reply_count as replyCount, pt.post_subject as subject  ")
                        .from(" t_post p, t_post_text pt")
                        .where(" p.post_id = pt.post_id AND p.topic_id = ? and p.post_type=?  and p.post_status<> ? ")
                        .orderBy(sql_order_shoptopic())
                        .setParameters(topicId, AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG));
        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findShopTopics(cn.wanto.dto.Ar, cn.touchin.page.Pagination, long)
     */
    @Override
    public Pagination findShopTopics(Ar ar, Pagination page, long topicId) {
        // pt.post_text AS text,
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" p.post_id as id , p.post_time AS time, p.post_reply_count as replyCount, p.post_click_count AS clickCount, p.post_useful_count AS usefulCount, p.post_unuseful_count AS unusefulCount, pt.post_subject as subject,u.user_id AS userId, u.user_thumb AS userAvatar,u.nickname AS userNickname,p.is_top AS totop")
                                .from(" t_post p, t_post_text pt, t_user u")
                                .where(" p.post_id = pt.post_id AND u.user_id=p.user_id AND p.topic_id = ? and p.post_type=?  and p.post_status<> ? ")
                                .orderBy(sql_order_shoptopic())
                                .setParameters(topicId, AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG));
        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countfindShopTopics(cn.touchin.dto.AppRequest, cn.touchin.page.Pagination,
     * long)
     */
    @Override
    public long countShopTopicsByTopicId(AppRequest ar, Pagination page, long topicId) {
        PageQuery pq =
                sqlParser.createPageQuery(new SQLRunner(page).select(" count(p.post_id)  ")
                        .from(" t_post p, t_post_text pt")
                        .where(" p.post_id = pt.post_id AND p.topic_id = ? and p.post_type=?  and p.post_status<> ? ")
                        .setParameters(topicId, AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG));
        return (Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams());
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countShopTopicsByTopicId(cn.touchin.dto.AppRequest, long)
     */
    @Override
    public long countShopTopicsByTopicId(AppRequest ar, long topicId) {
        return (Long) db.findBy(
                "SELECT count(post_id) FROM t_post WHERE topic_id=? and post_type=? and post_status<> ?", 1, topicId,
                AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countReplyByProductId(cn.touchin.dto.AppRequest, long)
     */
    @Override
    public long countReplyByProductId(AppRequest ar, long productId) {
        return (Long) db.findBy(
                "SELECT count(post_id) FROM t_post WHERE topic_id=? and post_type=? and post_status<> ?", 1, productId,
                AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countRepies4ShopTopics(cn.touchin.dto.AppRequest, long)
     */
    @Override
    public long countRepies4ShopTopics(AppRequest ar, long shoptopicId) {
        return (Long) db.findBy("SELECT count(post_id) FROM t_post WHERE top=? and post_type=? and post_status<> ?", 1,
                shoptopicId, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findPostAndRepliesByTopic(cn.touchin.dto.AppRequest,
     * cn.touchin.page.Pagination, long)
     */
    @Override
    public Pagination findPostAndRepliesByTopic(AppRequest ar, Pagination page, long topicId) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" p.post_id as id , p.post_time AS time, p.post_lvl AS lvl, pt.post_text AS text,u.user_id AS userId, u.user_thumb AS userAvatar,u.nickname AS userNickname")
                                .from(" t_post p, t_post_text pt, t_user u")
                                .where(" p.post_id = pt.post_id and u.user_id=p.user_id AND p.top=? AND p.topic_id=? AND p.post_type=?  AND p.post_status<>? ")
                                .orderBy(" p.post_time ASC")
                                .setParameters(0, topicId, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG));
        List<PostDto> posts = db.find(PostDto.class, pq.getPageSql(), pq.getPageParams());
        // page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        if (posts != null && !posts.isEmpty()) {
            // 查出回复并组装到帖子里
            PageQuery subpq =
                    sqlParser
                            .createPageQuery(new SQLRunner(page)
                                    .select(" p.post_id as id ")
                                    .from(" t_post p, t_post_text pt")
                                    .where(" p.post_id = pt.post_id AND p.top=? AND p.topic_id=? and p.post_type=?  and p.post_status<>? ")
                                    .setParameters(0, topicId, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG));

            List<PostDto> replies =
                    db.find(PostDto.class,
                            "SELECT p.top AS parentId, p.post_id as id, p.post_time AS time,p.post_lvl AS lvl, pt.post_text AS text,u.user_id AS userId, u.user_thumb AS userAvatar ,u.nickname AS userNickname FROM  t_post p, t_post_text pt, t_user u,("
                                    + subpq.getPageSql()
                                    + ") pp WHERE p.top=pp.id AND p.post_id=pt.post_id and u.user_id=p.user_id ORDER BY p.top ,p.post_time ASC",
                            subpq.getPageParams());
            if (replies != null && !replies.isEmpty()) {
                posts = assembleReplis2Post(posts, replies);
            }
        }
        page.setRows(posts);

        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findRepliesByShoptopic(cn.wanto.dto.Ar, cn.touchin.page.Pagination,
     * java.lang.Long)
     */
    @Override
    public Pagination findRepliesByShoptopic(Ar ar, Pagination page, Long shoptopicId) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" p.post_id as id , p.post_time AS time, p.post_lvl AS lvl, pt.post_text AS text,u.user_id AS userId, u.user_thumb AS userAvatar,u.nickname AS userNickname")
                                .from(" t_post p, t_post_text pt, t_user u")
                                .where(" p.post_id = pt.post_id and u.user_id=p.user_id AND p.top=? AND p.post_type=?  AND p.post_status<>? ")
                                .orderBy(" p.post_time ASC ")
                                .setParameters(shoptopicId, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG));
        List<PostDto> posts = db.find(PostDto.class, pq.getPageSql(), pq.getPageParams());
        // page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        if (posts != null && !posts.isEmpty()) {
            // 查出回复并组装到帖子里
            PageQuery subpq =
                    sqlParser.createPageQuery(new SQLRunner(page).select(" p.post_id as id ")
                            .from(" t_post p, t_post_text pt")
                            .where(" p.post_id = pt.post_id AND p.top=? AND p.post_type=?  and p.post_status<>? ")
                            .setParameters(shoptopicId, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG));

            List<PostDto> replies =
                    db.find(PostDto.class,
                            "SELECT p.top AS parentId, p.post_id as id, p.post_time AS time, p.post_lvl AS lvl, pt.post_text AS text,u.user_id AS userId, u.user_thumb AS userAvatar, u.nickname AS userNickname FROM  t_post p, t_post_text pt, t_user u,("
                                    + subpq.getPageSql()
                                    + ") pp WHERE p.top=pp.id AND p.post_id=pt.post_id and u.user_id=p.user_id ORDER BY p.top ,p.post_time ASC",
                            subpq.getPageParams());
            if (replies != null && !replies.isEmpty()) {
                posts = assembleReplis2Post(posts, replies);
            }
        }
        page.setRows(posts);
        return page;
    }

    /**
     * 组装回复到回复中
     * 
     * @param posts
     * @param replies
     * @return
     */
    private List<PostDto> assembleReplis2Post(List<PostDto> posts, List<PostDto> replies) {
        PostDto post = null;
        for (PostDto reply : replies) {
            if (post == null || post.getId() != reply.getParentId()) {
                post = findPostById(posts, reply.getParentId());
            }
            if (post != null && post.getId() == reply.getParentId()) {
                post.addReply(reply);
            } else {
                throw new RuntimeException("此处不应该有异常, 除非程序逻辑有问题，导致展现回复数据混乱");
            }
        }
        return posts;
    }

    /**
     * @param posts
     * @param postId
     * @return
     */
    private PostDto findPostById(List<PostDto> posts, long postId) {
        for (PostDto post : posts) {
            if (post.getId() == postId) {
                return post;
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findShoptopicView(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public PostDto findShoptopicView(Ar ar, Long shoptopicId) {
        String sql =
                "SELECT p.post_id AS id, p.topic_id AS topicId, p.user_id AS userId, p.post_time AS time, p.post_click_count AS clickCount, p.post_useful_count AS usefulCount,p.post_unuseful_count AS unusefulCount,"
                        + " pt.post_subject AS subject, pt.post_text AS text, te.is_vip AS vipShop, te.topic_presenter AS shopPresenter "
                        + " FROM t_post p, t_post_text pt, t_topic_extend te WHERE p.post_id=pt.post_id AND p.post_id=? AND te.topic_id=p.topic_id AND p.post_type=? AND p.post_status<>?";

        PostDto post = db.findFirst(PostDto.class, sql, shoptopicId, AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG);

        // 本店其它最热话题
        if (post.getTopicId() > 0) {

            String otherHotSql =
                    "SELECT p.post_id as id,p.is_top AS totop, p.post_reply_count as replyCount, pt.post_subject as subject"
                            + " FROM t_post p, t_post_text pt WHERE  p.post_id=pt.post_id AND p.post_id<>? AND p.topic_id=? and p.post_type=?  and p.post_status<>? "
                            + " ORDER BY "+sql_order_shoptopic()+" ,p.post_reply_count DESC, p.post_time DESC limit ? ";
            List<PostDto> otherPosts =
                    db.find(PostDto.class, otherHotSql, shoptopicId, post.getTopicId(), AppConstants.POST_TYPE_POST,
                            AppConstants.DEL_TAG, AppConstants.TOPIC_SHOPTOPICS_OTHERHOTS_COUNT);
            post.setReplies(otherPosts);
        }

        if (post.getUserId() > 0) {
            post.setOwner(fetchUser(post.getUserId()));
        }
        if (!isMe(ar, post.getUserId())) {
            // 如果不是自己的主题，看过数自动加一
            post.setClickCount(post.getClickCount() + 1);
            ThreadPool.execute(new ShoptopicViewTask(post.getId()));
        }
        return post;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#updateStatus(cn.wanto.dto.Ar, java.lang.Long[], int)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateShoptopicStatus(Ar ar, Long[] shoptopicIds, int status) {
        if (ar.isAdmin()) {
            return dao().currSession().createSQLQuery("UPDATE t_post SET post_status=? WHERE post_id in (:ids)")
                    .setParameter(0, status).setParameterList("ids", shoptopicIds).executeUpdate();
        } else {
            return dao()
                    .currSession()
                    .createSQLQuery(
                            "UPDATE t_post t, t_topic_extend tpe SET t.post_status=? WHERE t.topic_id=tpe.topic_id AND tpe.topic_presenter=? AND t.post_id in (:ids) ")
                    .setParameter(0, status).setParameter(1, ar.getLoginId()).setParameterList("ids", shoptopicIds)
                    .executeUpdate();
        }
    }

    
    
    /* (non-Javadoc)
     * @see cn.wanto.busi.serv.IPostService#topshoptopic(cn.wanto.dto.Ar, java.lang.Long, boolean)
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void topshoptopic(Ar ar, Long shoptopicId, boolean top) {
        db.update("UPDATE t_post SET is_top=? WHERE post_id=?", top, shoptopicId);
    }
    
    private String sql_order_shoptopic() {
        //主题排序
        return " FIELD(`is_top`,true) DESC, orderid ASC ";
    }
    
    /* (non-Javadoc)
     * @see cn.wanto.busi.serv.IPostService#upshoptopic(cn.wanto.dto.Ar, java.lang.Long, java.lang.Long)
     */
    @Transactional(propagation=Propagation.REQUIRED)
    @Override
    public void upshoptopic(Ar ar, Long topicId, Long shoptopicId) {
        ordershoptopic(ar, topicId, shoptopicId, true);
    }
    
    private void ordershoptopic(Ar ar, Long topicId, Long shoptopicId, boolean up) {
        //上一个
        String sql = "SELECT tp.post_id AS id,tp.is_top AS totop, tp.orderid AS orderid FROM t_post tp,(SELECT orderid AS odid, is_top as odtop FROM  t_post WHERE  post_id=?) tp1 WHERE topic_id=? AND tp.orderid<tp1.odid AND tp.is_top=tp1.odtop ORDER BY  FIELD(`is_top`,true) ASC, orderid DESC ";
        if (!up) {
          //下一个
            sql = "SELECT tp.post_id AS id,tp.is_top AS totop, tp.orderid AS orderid FROM t_post tp,(SELECT orderid AS odid, is_top as odtop FROM  t_post WHERE post_id=?) tp1 WHERE topic_id=? AND tp.orderid>tp1.odid AND tp.is_top=tp1.odtop ORDER BY "+sql_order_shoptopic();
        }
        Post p = db.findFirst(Post.class, sql, shoptopicId, topicId);
        if (p!=null && p.getId()!=null && p.getId()>0) {
            long orderId = (Long) db.findBy("SELECT orderid FROM t_post WHERE post_id=?", 1, shoptopicId);
            db.update("UPDATE t_post SET orderid=? WHERE post_id=?", p.getOrderid(),shoptopicId);
            db.update("UPDATE t_post SET orderid=? WHERE post_id=? ", orderId ,p.getId());        
        } else {
            throw new RuntimeException(up?"已经上移到最前了":"已经下移到最后了");
        }
    }

    /* (non-Javadoc)
     * @see cn.wanto.busi.serv.IPostService#downshoptopic(cn.wanto.dto.Ar, java.lang.Long, java.lang.Long)
     */
    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void downshoptopic(Ar ar, Long topicId, Long shoptopicId) {
        ordershoptopic(ar, topicId, shoptopicId, false);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#updateShoptopicBaseinfo(cn.wanto.dto.Ar, cn.wanto.dto.bean.PostDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateShoptopicBaseinfo(Ar ar, PostDto postDto) {
        if (!Strings.isBlank(postDto.getSubject())) {
            Post p = dao().fetch(ar, Post.class, postDto.getId());
            p.setEditTime(new Date());
            PostText pt = dao().fetch(ar, PostText.class, postDto.getId());
            pt.setSubject(postDto.getSubject());

            dao().save(ar, p);
            dao().save(ar, pt);
            ThreadPool.execute(new ShopTopicEditTask(ar, postDto));
        } else if (!Strings.isBlank(postDto.getText())) {
            Post p = dao().fetch(ar, Post.class, postDto.getId());
            p.setEditTime(new Date());
            PostText pt = dao().fetch(ar, PostText.class, postDto.getId());
            pt.setText(postDto.getText());

            dao().save(ar, p);
            dao().save(ar, pt);
            ThreadPool.execute(new ShopTopicEditTask(ar, postDto));
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PostDto savePost(Ar ar, PostDto dto) {
        return savePost(ar, dto, AppConstants.POST_TYPE_POST);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#saveReply(cn.wanto.dto.Ar, cn.wanto.dto.bean.PostDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PostDto saveReply(Ar ar, PostDto replyDto) {
        return savePost(ar, replyDto, AppConstants.POST_TYPE_REPLY);
    }

    public PostDto savePost(Ar ar, PostDto dto, int type) {
        dto.setType(type);
        Date now = new Date();
        Post post = new Post();
        post.setUserId(ar.getLoginId());
        post.setPostIp(ar.getIp());
        post.setTime(now);
        post.setEditTime(now);
        post.setType(type);
        post.setTopicId(dto.getTopicId());// 如果没有就为0
        post.setParentId(dto.getParentId());// 如果没有就为0

        PostText postText = new PostText();
        postText.setText(dto.getText());
        if (type == AppConstants.POST_TYPE_POST) {
            // 发帖，需要插入post
            postText.setBookMark(dto.getBookMark());
            postText.setSubject(dto.getSubject());
            post.setLvl(getFloorsOnTopicShop(dto.getTopicId()) + 1);// 楼层
        } else if (type == AppConstants.POST_TYPE_REPLY) {
            // 回复
            if (dto.getTopicId() > 0) {// 如果是对产品回应
                post.setLvl(getFloorsOnTopicShop(dto.getTopicId()) + 1);
            }

            if (dto.getParentId() > 0) {
                post.setLvl(getFloorsOnPost(dto.getParentId()) + 1);
            }
        }

        post.setTotop(false);//置顶
        post.setOrderid(post.getLvl());//排序  
        dao().save(ar, post);

        postText.setPostId(post.getId());
        dao().save(ar, postText);

        if (type == AppConstants.POST_TYPE_POST || (dto.getTopicId() > 0)) {
            ThreadPool.execute(new ShopTopicCreateTask(ar, post));
        } else {
            if (dto.getTopicId() > 0) {// 如果是对产品回应,影响跟创建主题一样
                ThreadPool.execute(new ShopTopicCreateTask(ar, post));
            }
            if (dto.getParentId() > 0) {
                ThreadPool.execute(new PostReplyCreateTask(ar, post));
            }
        }

        dto.setId(post.getId());
        return dto;
    }

    /**
     * topic(店铺和产品回复数)
     * 
     * @param topicId
     * @return
     */
    private long getFloorsOnTopicShop(Long topicId) {
        return ((Long) db.findBy("SELECT topic_floors FROM t_topic WHERE topic_id=?", 1, topicId)).longValue();
    }

    /**
     * post(主题和帖子回复数)
     * 
     * @param postId
     * @return
     */
    private long getFloorsOnPost(Long postId) {
        return ((Long) db.findBy("SELECT post_floors FROM t_post WHERE post_id=?", 1, postId)).longValue();
    }

    // private void updatePostAndTopicReplyTime(Ar ar, PostDto dto) {
    // Topic topic = topicDao.fetch(ar, dto.getTopicId());
    // if (topic != null) {
    // topic.setLastReplyTime(new Date());
    // topicDao.update(ar, topic);
    // }
    // if (dto.getParentId() > 0) {
    // Post parentPost = postDao.fetch(ar, dto.getParentId());
    // if (parentPost != null) {
    // parentPost.setLastReplyTime(new Date());
    // postDao.update(ar, parentPost);
    // }
    // }
    // }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#enjoyShopTopc(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void enjoyShopTopc(Ar ar, Long shoptopicId) {
        voteTopc(ar, shoptopicId, AppConstants.VOTE_ENJOY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#boredShopTopic(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void boredShopTopic(Ar ar, Long shoptopicId) {
        voteTopc(ar, shoptopicId, AppConstants.VOTE_BORED);
    }

    /**
     * 投票
     * 
     * @param ar
     * @param topicId
     * @param voteType
     */
    private void voteTopc(Ar ar, Long topicId, int voteType) {
        if (ar != null && ar.getLoginId() != null && topicId != null) {
            Composition id = new Composition(topicId, ar.getLoginId());
            ShopTopicVote vote = dao().getHibernateTemplate().get(ShopTopicVote.class, id);
            if (vote == null) {
                vote = new ShopTopicVote();
                vote.setId(id);
                vote.setVote(voteType);
                dao().save(ar, vote);
                ThreadPool.execute(new ShopTopicVoteTask(vote));
            } else {
                throw new RuntimeException("您已经投过票了!");
            }
        } else {
            throw new RuntimeException("请选择具体对象投票!");
        }
    }

    /**
     * 更新
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PostDto updatePost(Ar ar, PostDto dto) {
        db.update("update t_post_text set post_subject = ?, post_text = ? where post_id = ?",
                new Object[] { dto.getSubject(), dto.getText(), dto.getId() });

        return dto;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PostDto queryPostObject(PostDto postDto) {
        String sql =
                "SELECT u.nickname postUser,u.user_avatar userAvatar,"
                        + "t.post_id  id,t.post_edit_time editTime,t.post_ip postIp,"
                        + "t.post_time time,t.topic_id topicId,t.topic_topid,text.bookMark bookMark,"
                        + "t.post_click_count clickCount,t.post_edit_count editCount,t.post_reply_count replyCount,"
                        + "t.post_useful_count usefulCount,t.post_unuseful_count unusefulCount,"
                        + "text.post_subject subject,text.post_text text,t.post_type type " + "FROM t_post t "
                        + "left join t_user u on u.user_id = t.user_id "
                        + "left join t_post_text text on t.post_id = text.post_id " + "where t.post_id = "
                        + postDto.getId();
        return db.findFirst(PostDto.class, sql);
    }

    @Override
    public Pagination findPostPage4Reply(Pagination page, PostDto dto) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select("  u.nickname as postUser,u.user_avatar as userAvatar,"
                                        + "t.post_id as id ,t.post_edit_time as editTime,"
                                        + "t.post_ip as postIp,t.post_time as time,t.topic_id as topicId,t.topic_topid as topid ,"
                                        + "text.bookMark as bookMark,text.post_subject as subject,text.post_text as text, t.post_type as type ")
                                .from(" t_post t left join t_user u on u.user_id = t.user_id  left join t_post_text text on t.post_id = text.post_id    ")
                                .where(" t.top = ? and t.post_type = ?  and t.post_status != ?   order by t.post_time desc")
                                .setParameters(dto.getParentId(), AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG));
        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(Long.valueOf(countPost4Reply(null, dto)).intValue());
        return page;
    }

    // /*
    // * (non-Javadoc)
    // *
    // * @see cn.wanto.busi.serv.IPostService#countPost4Reply(cn.touchin.dto.AppRequest, cn.wanto.dto.bean.PostDto)
    // */
    // @Override
    // public long countPost4Reply(AppRequest ar, PostDto dto) {
    // String hql = " select count(t) from " + getEntityClass().getName() + " t where t.parentId = ? and t.type = ? ";
    // return this.dao().queryForLong(ar, hql, new Object[] { dto.getParentId(), AppConstants.POST_TYPE_REPLY });
    // }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public PostDto deletePost(Ar ar, PostDto dto) {
        // Long[] ids = findReply2GroupUser( dto.getId());
        // dto.setIds(ids);//返回回复贴人的id 集合
        String sql = " update  t_post  t  set  t.post_status = ?  where t.post_id = ? ";
        String sql4Reply = " update  t_post  t  set  t.post_status = ?  where t.top = ?  ";
        db.update(sql, new Object[] { AppConstants.DEL_TAG, dto.getId() });
        db.update(sql4Reply, new Object[] { AppConstants.DEL_TAG, dto.getId() });

        return dto;
    }

    // /*
    // * (non-Javadoc)
    // *
    // * @see cn.wanto.busi.serv.IPostService#findReply2GroupUser()
    // */
    // @Override
    // public Long[] findReply2GroupUser(Long parentid) {
    //
    // List<PostDto> count =
    // db.find(PostDto.class, "SELECT  u.user_id as userId from  t_post t "
    // + " left join t_user u on u.user_id = t.user_id  "
    // + " left join t_post_text text on t.post_id = text.post_id  "
    // + " WHERE t.top = ? and t.post_type = ?      and t.post_status != ?  GROUP BY t.user_id ",
    // new Object[] { parentid, AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG });
    //
    // Long[] ids = new Long[count.size()];
    // int i = 0;
    // for (PostDto o : count) {
    // ids[i++] = o.getUserId();
    // }
    // return ids;
    // }

    /**
     * 增加帖子的点击量，评论数等等
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public long incresPostParaCount(long postId, int flag) {

        String countType = "";

        switch (flag) {
        case AppConstants.POST_COUNT_TYPE_CLICK:
            countType = "post_click_count";
            break;
        case AppConstants.POST_COUNT_TYPE_REPLY:
            countType = "post_reply_count";
            break;
        case AppConstants.POST_COUNT_TYPE_USEFUL:
            countType = "post_useful_count";
            break;
        case AppConstants.POST_COUNT_TYPE_UNUSEFUL:
            countType = "post_unuseful_count";
            break;
        case AppConstants.POST_COUNT_TYPE_EDIT:
            countType = "post_edit_count";
            break;
        default:
            break;
        }

        db.update("update t_post set " + countType + " = " + countType + " + 1 where post_id = ? ", postId);

        return db.findFirst(PostDto.class, "select " + countType + " countTemp from t_post where post_id = ?", postId)
                .getCountTemp();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostTextService#findPostTextsByTopic(cn.touchin.dto.AppRequest,
     * cn.touchin.page.Pagination)
     */
    @Override
    public Pagination findPostTextsByTopic(AppRequest ar, Pagination page, long topicId) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" t.post_id  as id   , t.post_reply_count as replyCount  , text.post_subject  as  subject  ")
                                .from(" t_post t left join t_post_text text on t.post_id = text.post_id   ")
                                .where(" t.topic_id = ?  and t.post_type=?  and t.post_status != ? ")
                                .setParameters(topicId, AppConstants.POST_TYPE_POST, AppConstants.DEL_TAG));
        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        return page;

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#queryPostList4Other(cn.wanto.dto.bean.PostDto, int)
     */
    @Override
    public List<PostDto> queryPostList4Other(PostDto dto, long num) {
        String sql =
                "SELECT u.nickname postUser,u.user_avatar userAvatar,"
                        + "t.post_id  id,t.post_edit_count editCount,t.post_edit_time editTime,"
                        + "t.post_ip postIp,t.post_time time,t.topic_id topicId,t.topic_topid, t.post_reply_count as replyCount ,"
                        + "text.bookMark bookMark,text.post_subject subject,text.post_text text,"
                        + "t.post_type type FROM t_post t "
                        + "left join t_user u on u.user_id = t.user_id "
                        + "left join t_post_text text on t.post_id = text.post_id "
                        + "where t.post_id != ? and   t.topic_id = ?  and t.post_type=? and t.post_status != ?   order by t.post_time limit ?";
        return db.find(PostDto.class, sql, new Object[] { dto.getId(), dto.getTopicId(), dto.getType(),
                AppConstants.DEL_TAG, num });
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findMyPostTexts(cn.touchin.dto.AppRequest, cn.touchin.page.Pagination)
     */
    @Override
    public Pagination findMyPostTexts(Ar ar, Pagination page, int topicType) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select("  u.nickname as postUser,u.user_avatar as userAvatar,"
                                        + "t.post_id as id ,t.post_edit_time as editTime, t.post_click_count as clickCount, t.post_reply_count as replyCount,"
                                        + "t.post_ip as postIp,t.post_time as time,t.topic_id as topicId,t.topic_topid as topid,"
                                        + "text.bookMark as bookMark,text.post_subject as subject,text.post_text as text, t.post_type as type , "
                                        + " topic.topic_type as topicType , topic.topic_type as topicType ,topic.topic_title as topicTitle ")
                                .from(" t_post t left join t_user u on u.user_id = t.user_id "
                                        + " left join t_topic topic  on  topic.topic_id = t.topic_id  "
                                        + " left join t_post_text text on t.post_id = text.post_id    ")
                                .where(" t.user_id = ? and t.post_type = ? and t.post_status != ? "
                                        + searchMyPostTypeSql("topic", topicType, " and ")
                                        + " order by t.post_time desc")
                                .setParameters(
                                        new Object[] { ar.getLoginId(), AppConstants.POST_TYPE_POST,
                                                AppConstants.DEL_TAG, topicType }));

        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(Long.valueOf(countMyPostTexts(ar, page, topicType)).intValue());
        page.setTotal(((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue());
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#findMyReplys(cn.wanto.dto.Ar, cn.touchin.page.Pagination, int)
     */
    @Override
    public Pagination findMyReplys(Ar ar, Pagination page, int topicType) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" distinct  u.nickname as postUser,u.user_avatar as userAvatar,"
                                        + "t.post_id as id ,t.post_edit_time as editTime, t.post_click_count as clickCount, t.post_reply_count as replyCount,"
                                        + "t.post_ip as postIp,t.post_time as time,t.topic_id as topicId,t.topic_topid as topid,"
                                        + "text.bookMark as bookMark,text.post_subject as subject,text.post_text as text, t.post_type as type , "
                                        + " topic.topic_type as topicType , topic.topic_type as topicType ,topic.topic_title as topicTitle ")
                                .from(" t_post t left join t_user u on u.user_id = t.user_id "
                                        + " left join t_post c on t.post_id = c.top  "
                                        + " left join t_topic topic  on  topic.topic_id = t.topic_id  "
                                        + " left join t_post_text text on t.post_id = text.post_id    ")
                                .where("  c.user_id = ? and t.post_type = ?  and c.post_type  = ?   and c.post_status != ?  and  t.post_status != ?   "
                                        + searchMyPostTypeSql("topic", topicType, " and ")
                                        + " order by t.post_time desc")
                                .setParameters(
                                        new Object[] { ar.getLoginId(), AppConstants.POST_TYPE_POST,
                                                AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG,
                                                AppConstants.DEL_TAG, topicType }));

        page.setRows(db.find(PostDto.class, pq.getPageSql(), pq.getPageParams()));
        page.setTotal(Long.valueOf(countMyReplys(ar, page, topicType)).intValue());
        return page;
    }

    private String searchMyPostTypeSql(String tableName, int topicType, String connector) {
        if (topicType == 0) {
            return connector + tableName + ".topic_type != ? ";
        } else if (topicType == 1) {

            return connector + tableName + ".topic_type = ? ";
        }
        return "";
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countMyPostTexts(cn.wanto.dto.Ar, cn.touchin.page.Pagination, int)
     */
    @Override
    public long countMyPostTexts(Ar ar, Pagination page, int topicType) {
        String hql =
                " select count(t.post_id) from  t_post t left join t_user u on u.user_id = t.user_id   left join t_topic topic  on  topic.topic_id = t.topic_id  "
                        + " where  t.user_id = ? and t.post_type = ?  and t.post_status != ? "
                        + searchMyPostTypeSql("topic", topicType, " and ");
        return (Long) db.findBy(hql, 1, new Object[] { ar.getLoginId(), AppConstants.POST_TYPE_POST,
                AppConstants.DEL_TAG, topicType });
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#countMyReplys(cn.wanto.dto.Ar, cn.touchin.page.Pagination, int)
     */
    @Override
    public Long countMyReplys(Ar ar, Pagination pageData, int topicType) {
        String hql =
                " select count(distinct  t.post_id) from  t_post t "
                        + " left join t_post c on t.post_id = c.top  "
                        + " left join t_topic topic  on  topic.topic_id = t.topic_id  "
                        + " where  c.user_id = ? and t.post_type = ?  and c.post_type  = ?   and c.post_status != ?  and  t.post_status != ?   "
                        + searchMyPostTypeSql("topic", topicType, " and ");
        return (Long) db.findBy(hql, 1, new Object[] { ar.getLoginId(), AppConstants.POST_TYPE_POST,
                AppConstants.POST_TYPE_REPLY, AppConstants.DEL_TAG, AppConstants.DEL_TAG, topicType });
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IPostService#deleteReplyPost(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public void deleteReplyPost(Ar ar, Long parentPostId) {
        String sql =
                " update  t_post  t   set  t.post_status = ?  where  t.user_id = ?  and   t.top = ?   and t.post_type  = ?  ";

        db.update(sql, new Object[] { ar.getLoginId(), parentPostId, AppConstants.POST_TYPE_REPLY });
    }

}
