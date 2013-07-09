/*
 * cn.wanto.event.TopicInsertTask.java
 * Sep 11, 2012 
 */
package cn.wanto.event;

import cn.touch.db.Db;
import cn.touchin.Contexts;
import cn.wanto.busi.db.IAsynDao;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.entity.Wordbook;

/**
 * Sep 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicShopEditTask implements Runnable {
    /*-
     * 店铺修改任务
     */
    private Ar ar;
    private long oldKind;
    private long oldRegion;
    private TopicDto topicDto;

    public TopicShopEditTask(Ar ar, long oldKind, long oldRegion, TopicDto topicDto) {
        this.ar = ar;
        this.oldKind = oldKind;
        this.oldRegion = oldRegion;
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
        if (topicDto.getKind2() != oldKind) {
            if (topicDto.getKind2() > 0) {
                Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(topicDto.getKind2()));
                wb.setHot(wb.getHot() + 1);
                asynDao.save(ar, wb);
            }

            if (oldKind > 0) {
                Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(oldKind));
                wb.setHot(wb.getHot() - 1);
                asynDao.save(ar, wb);
            }
        }

        if (topicDto.getRegion4() != oldRegion) {
            if (topicDto.getRegion4() > 0) {
                Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(topicDto.getRegion4()));
                wb.setHot(wb.getHot() + 1);
                asynDao.save(ar, wb);
            }

            if (oldRegion > 0) {
                Wordbook wb = asynDao.fetch(ar, Wordbook.class, Long.valueOf(oldRegion));
                wb.setHot(wb.getHot() - 1);
                asynDao.save(ar, wb);
            }
        }

        Db db = Contexts.getBean(Db.class);
        db.update(
                "UPDATE t_post_text tpt, t_post tp SET tpt.post_subject=?, tpt.post_text=? where tpt.post_id=tp.post_id and tp.topic_id=?",
                topicDto.getTitle(), topicDto.getText(), topicDto.getId());
    }

}
