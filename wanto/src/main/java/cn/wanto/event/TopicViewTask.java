/*
 * cn.wanto.event.TopicVoteTask.java
 * Sep 14, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicViewTask implements Runnable {
    private Long topicId;

    public TopicViewTask(Long topicId) {
        this.topicId = topicId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (topicId != null) {
            Db db = Contexts.getBean(Db.class);
            db.update("UPDATE t_topic SET topic_views=topic_views+1 WHERE topic_id=?", topicId);
        }
    }

}
