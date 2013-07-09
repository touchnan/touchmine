/*
 * cn.wanto.util.AppConstants.java
 * Mar 7, 2012 
 */
package cn.wanto.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.servlet.http.HttpServletRequest;

import org.nutz.lang.Encoding;
import org.nutz.lang.Files;
import org.nutz.lang.Strings;
import org.nutz.log.Logs;
import org.springframework.util.Assert;

import cn.touch.kit.Config;
import cn.touch.kit.Dates;
import cn.wanto.busi.tools.ImagePath;
import cn.wanto.concurrent.ScheduleThreadPool;
import cn.wanto.event.FileDeleteTask;

/**
 * Mar 7, 2012
 * 
 * @author <a href="mailto.wanto.gmail.com">chegnqiang.han</a>
 * 
 */
public class AppConstants {

    public static Random rn = new Random();// 随机函数

    public static final String disable4Cookie = "disabled";
    public static final String key4Cookie = "_c_wanto_";// 不要包含特殊字符

    public static final String DEFINITION_PRIMARY_KEY = "int(11) NOT NULL AUTO_INCREMENT";
    public static final String DEFINITION_INT_DEFAULT = "int(11) NOT NULL DEFAULT '0'";
    public static final String DEFINITION_LONG_DEFAULT = "bigint(20) NOT NULL DEFAULT '0'";
    public static final String DEFINITION_BOOL_DEFAULT = "tinyint(1) DEFAULT '1'";
    public static final String DEFINITION_TEXT_DEFAULT = "text";

    public static final String DATE_YYYYMM = "yyyyMM";

    public static final String SEPARATOR_FILE = "/";
    private static final Config deploy = new Config("deploy");

    public static final boolean SKIP_SECURITY_CODE = deploy.getBoolean("skip.security.code", true);
    public static final String PATH_UPLOAD_ROOT = deploy.getString("path.upload.root", "");// 根目录
    public static final String DIR_TMP = deploy.getString("path.upload.tmp", "pp");//
    public static final String PATH_UPLOAD_ROOT_TEMP = PATH_UPLOAD_ROOT + SEPARATOR_FILE + DIR_TMP;// 临时目录

    public static final String DIR_PICS = deploy.getString("path.upload.pics", "wanto");// 图片相对目录
    public static final String PATH_DISK_PICS = PATH_UPLOAD_ROOT + SEPARATOR_FILE + DIR_PICS;// 正式目录
    public static final String PATH_DISK_PICS_TEMP = PATH_UPLOAD_ROOT_TEMP + SEPARATOR_FILE + DIR_PICS;// 上传临时目录

    public static final String KEY4SIGNATURE = deploy.getString("signature.key", "(&KNGTY^%`(#%#KD");

    public static final int POST_TYPE_POST = 1;//主题
    public static final int POST_TYPE_REPLY = 2;//回应

    public static final int POST_COUNT_TYPE_CLICK = 1;
    public static final int POST_COUNT_TYPE_REPLY = 2;
    public static final int POST_COUNT_TYPE_USEFUL = 3;
    public static final int POST_COUNT_TYPE_UNUSEFUL = 4;
    public static final int POST_COUNT_TYPE_EDIT = 5;

    public static final int TOPIC_SHOP_MASTER = 1;// 主店铺
    public static final int TOPIC_SHOP_MASTER_PIC = 11;// 店铺菜单
    
    public static final int LEN_SHOP_IDENTITY = 10;//店铺ID长度

    public static final int TMP_FILE_DEL_DELAY_MINUTES = 5;// 分钟，临时文件推迟五分钟删除

    public static final int TMP_FILE_EXPIRE_PERIOD = 60 * 60 * 1000;// 临时文件失效周期为半小时

    public static final String THUMBNAIL = "_thumb";// 缩略图后缀

    public static final int DEL_TAG = -99;// 删除标志
    public static final int UNDERCARRIAGE_TAG = 3;//下架
    public static final int SHELFCARRIAGE_TAG = 0;//上架/正常
    public static final int ALL_VALIDATE_TAG = -110;//所有正常

    public static final int VOTE_ENJOY = 1;// 投票，喜欢
    public static final int VOTE_BORED = -1;// 投票，不喜欢
    

    public static final int OPINION_STATE_APPLY = 1;// 申请申述
    public static final int OPINION_STATE_FAIL = 2;// 申述失败
    public static final int OPINION_STATE_SUCC = 3;// 申述成功
    public static final int OPINION_STATE_REPORT = 4;// 举报
    
    
    /*-
     * 不定义为final, 可以热改变
     */
    public static int TOPIC_SHOPS_MAIN_SHOW_COUNT = deploy.getInt("topic.shops.main.show.count", 8);// 店铺主页展示的个数
    public static int TOPIC_SHOPS_PRODUCT_SHOW_HOT_COUNT = deploy.getInt("topic.shops.product.show.hot.count", 3);// 本店最热产品
    public static int TOPIC_SHOPS_TAGS_HOT_COUNT = deploy.getInt("topic.shops.tags.hot.count", 15);// 最热标签产品
    public static int TOPIC_SHOPTOPICS_OTHERHOTS_COUNT = deploy.getInt("topic.shoptopics.otherhots.count", 20);// 店铺其他最热话题数
    public static int POST_SHOW_COUNT = deploy.getInt("post.main.show.count", 20);
    
    public static String encodeReqUrl(HttpServletRequest req) {
        String q = req.getQueryString();
        String url = Strings.isBlank(q) ? req.getServletPath() : req.getServletPath()+"?"+q;
        try {
            return java.net.URLEncoder.encode(url,Encoding.UTF8);
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }
    
    public static String encodeReqUrl(String url) {
        if (!Strings.isBlank(url)) {
            try {
                return java.net.URLEncoder.encode(url,Encoding.UTF8);
            } catch (UnsupportedEncodingException e) {
            }
        }
        return "";
    }
    
    /**
     * 创建目录
     * 
     * @param path
     * @return
     */
    public static String createPath(String path) {
        File f = new File(path);
        if (!f.exists()) {
            Files.makeDir(f);
        }
        try {
            return f.getCanonicalPath();
        } catch (IOException e) {
            Logs.getLog(AppConstants.class).error(e);
        }
        return null;
    }

    /**
     * 得到文件的在磁盘的存储路径
     * 
     * @param relativeFileName
     * @return
     */
    public static String diskFileName(String relativeFileName) {
        return PATH_UPLOAD_ROOT + SEPARATOR_FILE + relativeFileName;
    }

    public static String thumbnailName(String fileName) {
        return thumbnailName(fileName, AppConstants.THUMBNAIL);
    }

    /**
     * 得到缩略文件名称
     * 
     * @param fileName
     * @param thumbnail
     * @return
     */
    public static String thumbnailName(String fileName, String thumbnail) {
        if (!Strings.isBlank(fileName) && !Strings.isBlank(thumbnail)) {
            int idx = fileName.lastIndexOf(".");
            return String.format("%s%s%s", fileName.substring(0, idx), thumbnail, fileName.substring(idx));
        }
        return fileName;
    }

    /**
     * 
     * @param relativeTmpFileName
     * @return 根据上传后保存的临时文件解析出文件物理地址和文件相对路径
     */
    public static ImagePath parseDiskPicNameFromTmpFileName(String relativeTmpFileName) {
        String relativeFileName = relativeTmpFileName.substring(DIR_TMP.length() + 1);
        String dir = relativeFileName.substring(0, relativeFileName.lastIndexOf("/"));
        createPath(PATH_UPLOAD_ROOT + SEPARATOR_FILE + dir);// 创建正式目录
        String diskPath = String.format("%s/%s", PATH_UPLOAD_ROOT, relativeFileName);// 磁盘目录
        return new ImagePath(diskPath, relativeFileName);
    }

    /**
     * 
     * @param suffixName
     * @return 文件上传时，生成临时文件物理地址和临时文件相对路径
     */
    public static ImagePath genTempDiskPicName4Upload(String suffixName) {
        // 相对根目录目录
        Date now = new Date();

        String date_dir = Dates.format(now, DATE_YYYYMM);

        String path = createPath(PATH_DISK_PICS_TEMP + SEPARATOR_FILE + date_dir);// 创建目录

        long time = now.getTime();
        int idx = rn.nextInt(1000);
        String diskPath = String.format("%s/%s-%s.%s", path, time, idx, suffixName);
        String relativeFileName =
                String.format("%s/%s/%s/%s-%s.%s", DIR_TMP, DIR_PICS, date_dir, time, idx, suffixName);
        return new ImagePath(diskPath, relativeFileName, suffixName);
    }

    /**
     * 拷贝上传的临时文件到正式目录
     * 
     * @param relativeTmpFileName
     * @param error
     * @return 返回文件
     */
    public static ImagePath copyTempFile2StorePath(String relativeTmpFileName, String error) {
        String tempDiskFile = AppConstants.diskFileName(relativeTmpFileName);
        ImagePath imagePath = AppConstants.parseDiskPicNameFromTmpFileName(relativeTmpFileName);

        File tempFile = new File(tempDiskFile);
        try {
            Assert.isTrue(tempFile.exists(), error);
            Files.copyFile(tempFile, new File(imagePath.getDiskFilename()));
        } catch (IOException e) {
            Logs.getLog(AppConstants.class).error(e);
            throw new RuntimeException(error);
        } finally {
            // 不管如何，10分钟后删除临时文件
            ScheduleThreadPool.schedule(new FileDeleteTask(tempFile), AppConstants.TMP_FILE_DEL_DELAY_MINUTES,
                    TimeUnit.MINUTES);
        }
        return imagePath;
    }

    public static String[] splitByPound(String a) {
        if (!Strings.isBlank(a)) {
            a = a.substring(1, a.length()-1);
            return a.split("#");
        }
        return null;
    }

    public static String joinByPound(Object... a) {
        if (a != null) {
            switch (a.length) {
            case 1:
                return String.format("#%s#", a);
            case 2:
                return String.format("#%s#%s#", a);
            case 3:
                return String.format("#%s#%s#%s#", a);
            case 4:
                return String.format("#%s#%s#%s#%s#", a);
            case 5:
                return String.format("#%s#%s#%s#%s#%s#", a);
            case 6:
                return String.format("#%s#%s#%s#%s#%s#%s#", a);
            case 7:
                return String.format("#%s#%s#%s#%s#%s#%s#%s#", a);
            case 8:
                return String.format("#%s#%s#%s#%s#%s#%s#%s#%s#", a);
            case 9:
                return String.format("#%s#%s#%s#%s#%s#%s#%s#%s#%s#", a);
            default:
                return null;
            }
        }
        return null;
    }

    public static long parseYuanMoney(String moneyStr) {
        moneyStr = moneyStr.replace(",", "");
        int index = moneyStr.indexOf(".");
        if (index == -1) {
            moneyStr += "00";
        } else {
            moneyStr = moneyStr.substring(0, index) + Strings.alignLeft(moneyStr.substring(index + 1), 2, '0');
        }
        return Long.parseLong(moneyStr, 10);
    }

    /**
     * 头像定义 Sep 11, 2012
     * 
     * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
     * 
     */
    public static class AvatarImage {
        public final static int maxW = 0, maxH = 0, w = 80, h = 80, quality = 60;
    }

    public static class AvatarImageThumbnail {
        public final static int maxW = 0, maxH = 0, w = 50, h = 50, quality = 60;
    }
    
    /**
     * Sep 11, 2012
     * 
     * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
     * 
     */
    public static class TopicImage {// 店铺(视图)定义
        public final static int maxW = 0, maxH = 0, w = 230, h = 230, quality = 60;
    }

    public static class TopicThumbnail {// 店铺(缩略图)定义
        public final static int maxW = 0, maxH = 0, w = 115, h = 115, quality = 60;
    }

    public static class TopicSlaveImage {// 菜式(视图)定义
        public final static int maxW = 636, maxH = 0, w = 0, h = 0, quality = 60;
    }

    public static class TopicSlaveThumbnail {// 菜式定义
        public final static int maxW = 0, maxH = 0, w = 186, h = 0, quality = 60;
    }
}
