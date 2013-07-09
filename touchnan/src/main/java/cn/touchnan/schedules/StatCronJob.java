/*
 * cn.touchnan.schedules.StatCronJob.java
 * May 4, 2012 
 */
package cn.touchnan.schedules;

import org.nutz.log.Logs;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.touch.kit.json.JsonKit;
import cn.touchnan.busi.serv.IQueryService;

/**
 * May 4, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class StatCronJob extends QuartzJobBean {
    private IQueryService queryService;

    private JsonKit jsonKit;

    /**
     * @param queryService
     *            the queryService to set
     */
    public void setQueryService(IQueryService queryService) {
        this.queryService = queryService;
    }

    /**
     * @param jsonKit
     *            the jsonKit to set
     */
    public void setJsonKit(JsonKit jsonKit) {
        this.jsonKit = jsonKit;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.scheduling.quartz.QuartzJobBean#executeInternal(org
     * .quartz.JobExecutionContext)
     */
    @Override
    protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
        Logs.getLog(this.getClass()).error(jsonKit.stringify(queryService.findUsers(null)));
    }

}
