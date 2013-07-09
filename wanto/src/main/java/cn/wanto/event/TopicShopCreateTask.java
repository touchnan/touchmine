/*
 * cn.wanto.event.TopicInsertTask.java
 * Sep 11, 2012 
 */
package cn.wanto.event;

import cn.touchin.Contexts;
import cn.wanto.busi.db.IAsynDao;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.entity.Post;
import cn.wanto.entity.PostText;
import cn.wanto.entity.Topic;
import cn.wanto.entity.Wordbook;

/**
 * Sep 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicShopCreateTask implements Runnable {
    /*-
     * 店铺创建任务
     */
    private Ar ar;
    private Topic topic;
    private TopicDto topicDto;

    public TopicShopCreateTask(Ar ar, Topic topic, TopicDto topicDto) {
        this.ar = ar;
        this.topic = topic;
        this.topicDto = topicDto;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {

        IAsynDao asynDao = Contexts.getBean(IAsynDao.class);
        // 扩展
        Post post = new Post();
        post.setPostIp(ar.getIp());
        post.setTime(topic.getTime());
        post.setTopicId(topic.getId());
        post.setTopicTopId(topic.getId());
        post.setUserId(ar.getLoginId());
        asynDao.save(ar, post);

        if (topicDto.getKind2() > 0) {
            Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(topicDto.getKind2()));
            wb.setHot(wb.getHot() + 1);
            asynDao.save(ar, wb);
        }
        
        if (topicDto.getRegion4() > 0) {
            Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(topicDto.getRegion4()));
            wb.setHot(wb.getHot() + 1);
            asynDao.save(ar, wb);
        }

        PostText pt = new PostText();
        pt.setPostId(post.getId());
        pt.setSubject(topicDto.getTitle());
        pt.setText(topicDto.getText());

        asynDao.save(ar, pt);
    }

}
