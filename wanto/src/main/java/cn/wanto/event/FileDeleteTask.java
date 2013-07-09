/*
 * cn.wanto.event.FileDeleteTask.java
 * Sep 12, 2012 
 */
package cn.wanto.event;

import java.io.File;

import org.nutz.lang.Files;

/**
 * Sep 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class FileDeleteTask implements Runnable {
    private File[] files;
    private String[] fileNames;

    public FileDeleteTask(File... files) {
        this.files = files;
    }

    public FileDeleteTask(String... fileNames) {
        this.fileNames = fileNames;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    @Override
    public void run() {
        if (files != null) {
            for (File file : files) {
                if (file.exists() && file.isFile()) {
                    Files.deleteFile(file);
                }
            }
        }

        if (fileNames != null) {
            for (String name : fileNames) {
                File file = new File(name);
                if (file.exists() && file.isFile()) {
                    Files.deleteFile(file);
                }
            }
        }
    }

}
