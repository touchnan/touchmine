/*
 * cn.wanto.event.FileExpiredTask.java
 * Nov 19, 2012 
 */
package cn.wanto.event;

import java.io.File;
import java.util.Date;

import org.nutz.lang.Files;
import org.nutz.log.Logs;

import cn.wanto.util.AppConstants;

/**
 * Nov 19, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FileDayExpiredTask implements Runnable {
    private long period = 1000 * 60 * 60 * 24;

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        while (true) {
            recursive(new File(AppConstants.PATH_DISK_PICS_TEMP));
            try {
                Thread.sleep(period);
            } catch (Exception e) {
                Logs.getLog(FileDayExpiredTask.class).error(e);
            }
        }
    }

    private void recursive(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                File[] fs = file.listFiles();
                if (fs.length > 0) {
                    for (File f : fs) {
                        recursive(f);
                    }
                } else {
                    // 删除空文件夹
                    Files.deleteDir(file);
                }
            } else if (file.isFile()) {
                if ((new Date().getTime() - file.lastModified()) / period > 1) {
                    Files.deleteFile(file);
                }
            }
        }
    }

}
