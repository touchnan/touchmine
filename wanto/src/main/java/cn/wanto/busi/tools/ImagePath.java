/*
 * cn.wanto.busi.tools.ImagePath.java
 * Sep 13, 2012 
 */
package cn.wanto.busi.tools;

import org.nutz.lang.Files;
import org.nutz.lang.Strings;

import cn.touch.kit.MethodResult;
import cn.wanto.util.AppConstants;

/**
 * Sep 13, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class ImagePath extends MethodResult {
    private String suffixName;// 后缀名
    private String relativeFilename;// 相对目录名
    private String diskFilename;// 物理目录名
    private String showname;// 展示名称

    public ImagePath() {
        super(true);
    }

    public ImagePath(String diskFilename, String relativeFilename) {
        this(diskFilename, relativeFilename, null);

    }

    public ImagePath(String diskFilename, String relativeFilename, String suffixName) {
        super();
        this.diskFilename = diskFilename;
        this.relativeFilename = relativeFilename;
        if (!Strings.isBlank(suffixName)) {
            this.suffixName = suffixName;
        } else {
            this.suffixName =
                    !Strings.isBlank(diskFilename) ? Files.getSuffixName(diskFilename) : (!Strings
                            .isBlank(relativeFilename) ? Files.getSuffixName(relativeFilename) : "");
        }
    }

    public String getThumbnailRelativeFilename() {
        return getThumbnailRelativeFilename(AppConstants.THUMBNAIL);
    }

    public String getThumbnailRelativeFilename(String thumbnail) {
        return AppConstants.thumbnailName(this.relativeFilename);
    }

    public String getThumbnailDiskFilenameFilename() {
        return getThumbnailDiskFilenameFilename(AppConstants.THUMBNAIL);
    }

    public String getThumbnailDiskFilenameFilename(String thumbnail) {
        return AppConstants.thumbnailName(this.diskFilename);
    }

    /**
     * @return the suffixName
     */
    public String getSuffixName() {
        return suffixName;
    }

    /**
     * @param suffixName
     *            the suffixName to set
     */
    public void setSuffixName(String suffixName) {
        this.suffixName = suffixName;
    }

    /**
     * @return the relativeFilename
     */
    public String getRelativeFilename() {
        return relativeFilename;
    }

    /**
     * @param relativeFilename
     *            the relativeFilename to set
     */
    public void setRelativeFilename(String relativeFilename) {
        this.relativeFilename = relativeFilename;
    }

    /**
     * @return the diskFilename
     */
    public String getDiskFilename() {
        return diskFilename;
    }

    /**
     * @param diskFilename
     *            the diskFilename to set
     */
    public void setDiskFilename(String diskFilename) {
        this.diskFilename = diskFilename;
    }

    /**
     * @param mr
     * @return
     */
    public ImagePath initMethodResult(MethodResult mr) {
        this.setSuccess(mr.isSuccess());
        this.setMsg(mr.getMsg());
        return this;
    }

    /**
     * @return the showname
     */
    public String getShowname() {
        return showname;
    }

    /**
     * @param showname
     *            the showname to set
     */
    public void setShowname(String showname) {
        this.showname = showname;
    }

}
