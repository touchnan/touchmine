/*
 * cn.wanto.concurrent.ThreadPool.java
 * Sep 11, 2012 
 */
package cn.wanto.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import net.sf.ehcache.hibernate.HibernateUtil;

/**
 * Sep 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ThreadPool {
    // private static Log log = Logs.getLog(ThreadPool.class);

    // private static ExecutorService executor = Executors.newCachedThreadPool();
    /*-
     * 最大100个线程,60秒空闲的线程释放
     */
    private static ExecutorService executor = new ThreadPoolExecutor(0, 100, 60L, TimeUnit.SECONDS,
            new SynchronousQueue<Runnable>());

    public static void execute(Runnable task) {
        executor.execute(task);
    }

}
