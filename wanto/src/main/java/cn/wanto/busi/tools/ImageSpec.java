/*
 * cn.wanto.busi.tools.ImageSpec.java
 * Sep 13, 2012 
 */
package cn.wanto.busi.tools;

import cn.touch.kit.image.ImageSpecification;
import cn.wanto.util.AppConstants;

/**
 * Sep 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ImageSpec extends ImageSpecification {

    /**
     * 
     */
    public ImageSpec() {
        super();
    }

    /**
     * @param maxW
     * @param maxH
     * @param pw
     * @param ph
     * @param quality
     */
    public ImageSpec(int maxW, int maxH, int pw, int ph, int quality) {
        super(maxW, maxH, pw, ph, quality);
    }

    /**
     * @param imgFile
     * @param maxW
     * @param maxH
     * @param pw
     * @param ph
     * @param quality
     */
    public ImageSpec(String imgFile, int maxW, int maxH, int pw, int ph, int quality) {
        super(imgFile, maxW, maxH, pw, ph, quality);
    }

    public static ImageSpec initTopicImage(String name) {
        return new ImageSpec(name, AppConstants.TopicImage.maxW, AppConstants.TopicImage.maxH,
                AppConstants.TopicImage.w, AppConstants.TopicImage.h, AppConstants.TopicImage.quality);
    }

    public static ImageSpec initTopicThumbnail(String name) {
        return new ImageSpec(name, AppConstants.TopicThumbnail.maxW, AppConstants.TopicThumbnail.maxH,
                AppConstants.TopicThumbnail.w, AppConstants.TopicThumbnail.h, AppConstants.TopicThumbnail.quality);
    }

    public static ImageSpec initAvatarImage(String name) {
        return new ImageSpec(name, AppConstants.AvatarImage.maxW, AppConstants.AvatarImage.maxH,
                AppConstants.AvatarImage.w, AppConstants.AvatarImage.h, AppConstants.AvatarImage.quality);
    }
    
    public static ImageSpec initAvatarImageThumbnail(String name) {
        return new ImageSpec(name, AppConstants.AvatarImageThumbnail.maxW, AppConstants.AvatarImageThumbnail.maxH,
                AppConstants.AvatarImageThumbnail.w, AppConstants.AvatarImageThumbnail.h, AppConstants.AvatarImageThumbnail.quality);
    }

    public static ImageSpec initTopicSlaveImage(String name) {
        return new ImageSpec(name, AppConstants.TopicSlaveImage.maxW, AppConstants.TopicSlaveImage.maxH,
                AppConstants.TopicSlaveImage.w, AppConstants.TopicSlaveImage.h, AppConstants.TopicSlaveImage.quality);
    }

    public static ImageSpec initTopicSlaveThumbnail(String name) {
        return new ImageSpec(name, AppConstants.TopicSlaveThumbnail.maxW, AppConstants.TopicSlaveThumbnail.maxH,
                AppConstants.TopicSlaveThumbnail.w, AppConstants.TopicSlaveThumbnail.h,
                AppConstants.TopicSlaveThumbnail.quality);
    }

}
