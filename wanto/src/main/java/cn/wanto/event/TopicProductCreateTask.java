/*
 * cn.wanto.event.TopicProductCreateTask.java
 * Sep 15, 2012 
 */
package cn.wanto.event;

import cn.touchin.Contexts;
import cn.wanto.busi.db.IAsynDao;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.entity.Post;
import cn.wanto.entity.PostText;
import cn.wanto.entity.Topic;

/**
 * Sep 15, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicProductCreateTask implements Runnable {

    private Ar ar;
    private Topic topic;
    private String title;
    private String text;

    public TopicProductCreateTask(Ar ar, Topic topic, String title, String text) {
        this.ar = ar;
        this.topic = topic;
        this.title = title;
        this.text = text;
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

        PostText pt = new PostText();
        pt.setPostId(post.getId());
        pt.setSubject(title);
        pt.setText(text);

        asynDao.save(ar, pt);
    }

}
