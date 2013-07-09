/*
 * cn.wanto.event.ShoptopicViewTask.java
 * Sep 22, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;

/**
 * Sep 22, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ShoptopicViewTask implements Runnable {
    private Long shopTopicId;

    public ShoptopicViewTask(Long shopTopicId) {
        this.shopTopicId = shopTopicId;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (shopTopicId != null) {
            Db db = Contexts.getBean(Db.class);
            db.update("UPDATE t_post SET post_click_count=post_click_count+1 WHERE post_id=?", shopTopicId);
        }
    }

}
