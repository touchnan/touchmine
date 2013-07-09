/*
 * cn.wanto.busi.tools.ImageFactory.java
 * Sep 7, 2012 
 */
package cn.wanto.busi.tools;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.touch.kit.MethodResult;
import cn.touch.kit.image.ImageProcessor;
import cn.wanto.concurrent.ScheduleThreadPool;
import cn.wanto.event.FileDeleteTask;
import cn.wanto.util.AppConstants;

/**
 * Sep 7, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
public class ImageFactory {
    private static Log log = Logs.getLog(ImageFactory.class);

    @Autowired
    private ImageProcessor imageProcessor;

    public ImagePath createAvatar(File srcFile, String suffixName) {
        if (Strings.isBlank(suffixName)) {
            suffixName = Files.getSuffixName(srcFile);
        }
        ImagePath image = AppConstants.genTempDiskPicName4Upload(suffixName);
        ImageSpec imageSpec = ImageSpec.initAvatarImage(image.getDiskFilename());// 头像

        // 缩略图的文件
        ImageSpec thumbnailSpec = ImageSpec.initAvatarImageThumbnail(image.getThumbnailDiskFilenameFilename());

        MethodResult[] mr = imageProcessor.creatMiniatureCommand(srcFile, imageSpec, thumbnailSpec);

        // 上传的文件半小时后失效
        ScheduleThreadPool.schedule(new FileDeleteTask(image.getDiskFilename()), AppConstants.TMP_FILE_EXPIRE_PERIOD,
                TimeUnit.MILLISECONDS);

        return image.initMethodResult(mr[0]);

    }

    /**
     * 创建主题和缩略图
     * 
     * @param srcFile
     * @param id
     * @return
     */
    public ImagePath createTopicicon(File srcFile, String suffixName) {
        // 返回磁盘地址和相对路径
        if (Strings.isBlank(suffixName)) {
            suffixName = Files.getSuffixName(srcFile);
        }
        ImagePath image = AppConstants.genTempDiskPicName4Upload(suffixName);
        ImageSpec imageSpec = ImageSpec.initTopicImage(image.getDiskFilename());

        // 缩略图的文件
        ImageSpec thumbnailSpec = ImageSpec.initTopicThumbnail(image.getThumbnailDiskFilenameFilename());

        MethodResult[] mr = imageProcessor.creatMiniatureCommand(srcFile, imageSpec, thumbnailSpec);

        // 上传的文件半小时后失效
        ScheduleThreadPool.schedule(new FileDeleteTask(image.getDiskFilename()), AppConstants.TMP_FILE_EXPIRE_PERIOD,
                TimeUnit.MILLISECONDS);

        return image.initMethodResult(mr[0]);
    }

    private ImagePath create(File srcFile, String suffixName, int w, int h, int quality) {
        // 返回磁盘地址和相对路径
        if (Strings.isBlank(suffixName)) {
            suffixName = Files.getSuffixName(srcFile);
        }
        ImagePath image = AppConstants.genTempDiskPicName4Upload(suffixName);
        MethodResult mr = imageProcessor.creatMiniatureCommand(srcFile, image.getDiskFilename(), w, h, quality);

        // 上传的文件半小时后失效
        ScheduleThreadPool.schedule(new FileDeleteTask(image.getDiskFilename()), AppConstants.TMP_FILE_EXPIRE_PERIOD,
                TimeUnit.MILLISECONDS);

        return image.initMethodResult(mr);
    }

    /**
     * @param upfile
     * @param suffixName
     * @return
     */
    public ImagePath createProducticon(File srcFile, String suffixName) {
        // 返回磁盘地址和相对路径
        if (Strings.isBlank(suffixName)) {
            suffixName = Files.getSuffixName(srcFile);
        }
        ImagePath image = AppConstants.genTempDiskPicName4Upload(suffixName);
        ImageSpec imageSpec = ImageSpec.initTopicSlaveImage(image.getDiskFilename());// 产品

        // 缩略图的文件
        ImageSpec thumbnailSpec = ImageSpec.initTopicSlaveThumbnail(image.getThumbnailDiskFilenameFilename());

        MethodResult[] mr = imageProcessor.creatMiniatureCommand(srcFile, imageSpec, thumbnailSpec);

        // 上传的文件半小时后失效
        ScheduleThreadPool.schedule(new FileDeleteTask(image.getDiskFilename()), AppConstants.TMP_FILE_EXPIRE_PERIOD,
                TimeUnit.MILLISECONDS);

        return image.initMethodResult(mr[0]);
    }

}
