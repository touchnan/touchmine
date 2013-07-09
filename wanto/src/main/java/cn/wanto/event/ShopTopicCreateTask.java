/*
 * cn.wanto.event.ShopTopicCreateTask.java
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
public class ShopTopicCreateTask implements Runnable {
    private Ar ar;
    // 店铺主题创建或者回复产品
    private Post post;

    public ShopTopicCreateTask(Ar ar, Post post) {
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
        Db db = Contexts.getBean(Db.class);
        // 回贴数增一
        db.update(
                "UPDATE t_topic SET topic_replies=topic_replies+1, topic_floors=topic_floors+1, topic_last_reply_time=? WHERE topic_id=?",
                post.getEditTime(), post.getTopicId());

        // 第一贴，最后一帖
        if (post.getLvl() <= 1) {
            db.update("UPDATE t_topic SET topic_first_post_id=?,topic_last_post_id=? WHERE topic_id=?", post.getId(),
                    post.getId(), post.getTopicId());
        } else {
            db.update("UPDATE t_topic SET topic_last_post_id=? WHERE topic_id=?", post.getId(), post.getTopicId());
        }

    }

}
