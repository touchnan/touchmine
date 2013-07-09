/*
 * cn.wanto.event.TopicProductEditTask.java
 * Sep 15, 2012 
 */
package cn.wanto.event;

import org.nutz.lang.Strings;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.busi.db.IAsynDao;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.TopicDto;

/**
 * Sep 15, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicProductEditTask implements Runnable {

    private Ar ar;
    private TopicDto topicDto;

    public TopicProductEditTask(Ar ar, TopicDto topicDto) {
        this.ar = ar;
        this.topicDto = topicDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()1
     */
    @Override
    public void run() {

        IAsynDao asynDao = Contexts.getBean(IAsynDao.class);
        // 扩展
        Db db = Contexts.getBean(Db.class);
        
        if (!Strings.isBlank(topicDto.getTitle())) {
            db.update(
                    "UPDATE t_post_text tpt, t_post tp SET tpt.post_subject=? where tpt.post_id=tp.post_id and tp.topic_id=?",
                    topicDto.getTitle(), topicDto.getId());            
        } else if (!Strings.isBlank(topicDto.getText())) {
            db.update(
                    "UPDATE t_post_text tpt, t_post tp SET tpt.post_text=? where tpt.post_id=tp.post_id and tp.topic_id=?",
                    topicDto.getText(), topicDto.getId());            
        }
    }

}
