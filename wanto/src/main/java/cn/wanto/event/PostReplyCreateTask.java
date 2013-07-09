/*
 * cn.wanto.event.PostReplyCreateTask.java
 * Sep 22, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.dto.Ar;
import cn.wanto.entity.Post;

/**
 * Sep 22, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class PostReplyCreateTask implements Runnable {
    private Ar ar;
    // 回复创建
    private Post post;

    public PostReplyCreateTask(Ar ar, Post post) {
        this.ar = ar;
        this.post = post;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        runUpdate(post.getParentId());
    }

    void runUpdate(long parentId) {
        if (parentId > 0) {
            Db db = Contexts.getBean(Db.class);
            // 父更新
            db.update(
                    "UPDATE t_post SET post_reply_count=post_reply_count+1, post_floors=post_floors+1 WHERE post_id=?",
                    parentId);

            Post parent =
                    db.findFirst(Post.class,
                            "SELECT post_id AS id, topic_id AS topicId, top AS parentId FROM t_post WHERE post_id=?",
                            parentId);
            if (parent != null && parent.getId() > 0) {
                if (parent.getTopicId() > 0) {
                    db.update(
                            "UPDATE t_topic SET topic_replies=topic_replies+1, topic_last_reply_time=? WHERE topic_id=?",
                            post.getEditTime(), parent.getTopicId());
                }
                if (parent.getParentId() > 0) {
                    runUpdate(parent.getParentId());
                }
            }
        }
    }

}
