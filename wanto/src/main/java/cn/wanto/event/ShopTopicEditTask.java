/*
 * cn.wanto.event.ShopTopicEditTask.java
 * Sep 22, 2012 
 */
package cn.wanto.event;

import org.nutz.lang.Strings;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.busi.db.IAsynDao;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.PostDto;

/**
 * Sep 22, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ShopTopicEditTask implements Runnable {

    private Ar ar;
    private PostDto postDto;

    public ShopTopicEditTask(Ar ar, PostDto postDto) {
        this.ar = ar;
        this.postDto = postDto;
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

        if (!Strings.isBlank(postDto.getSubject())) {
            db.update(
                    "UPDATE t_post_text tpt, t_post tp SET tpt.post_subject=?, tp.post_edit_count=tp.post_edit_count+1  where tpt.post_id=tp.post_id and tp.post_id=?",
                    postDto.getSubject(), postDto.getId());
        } else if (!Strings.isBlank(postDto.getText())) {
            db.update(
                    "UPDATE t_post_text tpt, t_post tp SET tpt.post_text=?, tp.post_edit_count=tp.post_edit_count+1 where tpt.post_id=tp.post_id and tp.post_id=?",
                    postDto.getText(), postDto.getId());
        }
    }

}
