/*
 * cn.wanto.event.TopicVoteTask.java
 * Sep 14, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.entity.ShopTopicVote;
import cn.wanto.util.AppConstants;

/**
 * Sep 14, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ShopTopicVoteTask implements Runnable {
    private ShopTopicVote vote;

    public ShopTopicVoteTask(ShopTopicVote vote) {
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
                db.update("UPDATE t_post SET post_useful_count=post_useful_count+1 WHERE post_id=?", vote.getId().getTargetId());
                break;
            case AppConstants.VOTE_BORED:
                db.update("UPDATE t_post SET post_unuseful_count=post_unuseful_count+1 WHERE post_id=?", vote.getId().getTargetId());
                break;
            default:
                break;
            }
        }
    }

}
