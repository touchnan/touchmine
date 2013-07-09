/*
 * cn.wanto.event.TopicVoteTask.java
 * Sep 14, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.entity.TopicVote;
import cn.wanto.util.AppConstants;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicVoteTask implements Runnable {
    private TopicVote vote;

    public TopicVoteTask(TopicVote vote) {
        this.vote = vote;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (vote != null && vote.getId().getTargetId() != null && vote.getId().getUserId() != null) {
            Db db = Contexts.getBean(Db.class);
            switch (vote.getVote()) {
            case AppConstants.VOTE_ENJOY:
                db.update("UPDATE t_topic SET topic_enjoyments=topic_enjoyments+1 WHERE topic_id=?", vote.getId().getTargetId());
                break;
            case AppConstants.VOTE_BORED:
                db.update("UPDATE t_topic SET topic_boredoms=topic_boredoms+1 WHERE topic_id=?", vote.getId().getTargetId());
                break;
            default:
                break;
            }
        }
    }

}
