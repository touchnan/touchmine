/*
 * cn.wanto.dto.bean.TopicDto.java
 * Sep 10, 2012 
 */
package cn.wanto.dto.bean;

import java.util.Date;

import org.apache.struts2.json.annotations.JSON;
import org.nutz.lang.Strings;

import cn.touch.kit.Dates;
import cn.touch.kit.Moneys;
import cn.touchin.dto.Dto;
import cn.touchin.util.Constants;
import cn.wanto.entity.Topic;
import cn.wanto.util.AppConstants;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class TopicDto extends Dto<Topic> {
    private static final long serialVersionUID = -173988370218102350L;

    private Long id;

    private long identity;

    private String title;// 主题名称

    private String kind;// 类型
    private long kind1;// 大类
    private long kind2;// 细类

    private String kind1name;
    private String kind2name;

    private String region;// 地区
    private long region1;
    private long region2;
    private long region3;
    private long region4;

    private String region1name;
    private String region2name;
    private String region3name;
    private String region4name;

    private String addr;// 详细地址
    private String text;// 店铺简介
    private String icon;// 店铺图标
    private String thumbnail;// 店铺缩略图
    private String iconname;// 店铺图标文件名

    private String vipQuota;// 加V申请内容

    private long views;// 查看数

    private long enjoyments;// 喜欢数
    private long boredoms;// 不喜欢数
    private Date time;// 创建时间
    private Date etime;// 最后修改时间
    private Date lastReplyTime;

    private long presenter;// 推荐人
    private String presenterName;//
    private String said;// 店长说

    private boolean keeper;// 店长
    private String phone;// 电话

    private boolean vip;// 是否加精
    private String magazineQuota;// 杂志

    private int status;// 状态
    private long price;// 价格
    private String pricedecimal;// 价格

    private long parentId;//

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.dto.Dto#invokeForCallback(java.lang.Object)
     */
    @Override
    public Dto<Topic> invokeForCallback(Topic obj) {
        this.copyProperties(obj);
        return this.prepare();
    }

    /**
     * 准备转换数据
     * 
     * @return
     */
    public TopicDto prepare() {
        return prepareregion().prepareKind();
    }

    private TopicDto prepareregion() {
        if (Strings.isBlank(region)) {
            this.setRegion(AppConstants.joinByPound(this.getRegion1(), this.getRegion2(), this.getRegion3(),
                    this.getRegion4()));
        } else {
            String[] rs = AppConstants.splitByPound(region);
            if (rs != null) {
                switch (rs.length) {
                case 1:
                    this.region1 = Long.parseLong(rs[0]);
                    break;
                case 2:
                    this.region1 = Long.parseLong(rs[0]);
                    this.region2 = Long.parseLong(rs[1]);
                    break;
                case 3:
                    this.region1 = Long.parseLong(rs[0]);
                    this.region2 = Long.parseLong(rs[1]);
                    this.region3 = Long.parseLong(rs[2]);
                    break;
                case 4:
                    this.region1 = Long.parseLong(rs[0]);
                    this.region2 = Long.parseLong(rs[1]);
                    this.region3 = Long.parseLong(rs[2]);
                    this.region4 = Long.parseLong(rs[3]);
                    break;
                default:
                    break;
                }
            }
        }
        return this;
    }

    private TopicDto prepareKind() {
        if (Strings.isBlank(kind)) {
            this.setKind(AppConstants.joinByPound(this.getKind1(), this.getKind2()));
        } else {
            String[] ks = AppConstants.splitByPound(kind);
            if (ks != null) {
                switch (ks.length) {
                case 1:
                    this.kind1 = Long.parseLong(ks[0]);
                    break;
                case 2:
                    this.kind1 = Long.parseLong(ks[0]);
                    this.kind2 = Long.parseLong(ks[1]);
                    break;
                default:
                    break;
                }
            }
        }
        return this;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     *            the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind
     *            the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region
     *            the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr
     *            the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text
     *            the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the icon
     */
    public String getIcon() {
        return icon;
    }

    /**
     * @param icon
     *            the icon to set
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * @return the vipQuota
     */
    public String getVipQuota() {
        return vipQuota;
    }

    /**
     * @param vipQuota
     *            the vipQuota to set
     */
    public void setVipQuota(String vipQuota) {
        this.vipQuota = vipQuota;
    }

    /**
     * @return the iconname
     */
    public String getIconname() {
        return iconname;
    }

    /**
     * @param iconname
     *            the iconname to set
     */
    public void setIconname(String iconname) {
        this.iconname = iconname;
    }

    /**
     * @return the thumbnail
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * @param thumbnail
     *            the thumbnail to set
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    /**
     * @return the presenterName
     */
    public String getPresenterName() {
        return presenterName;
    }

    /**
     * @param presenterName
     *            the presenterName to set
     */
    public void setPresenterName(String presenterName) {
        this.presenterName = presenterName;
    }

    /**
     * @return the said
     */
    public String getSaid() {
        return said;
    }

    /**
     * @param said
     *            the said to set
     */
    public void setSaid(String said) {
        this.said = said;
    }

    /**
     * @return the keeper
     */
    public boolean isKeeper() {
        return keeper;
    }

    /**
     * @param keeper
     *            the keeper to set
     */
    public void setKeeper(boolean keeper) {
        this.keeper = keeper;
    }

    /**
     * @return the vip
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * @param vip
     *            the vip to set
     */
    public void setVip(boolean vip) {
        this.vip = vip;
    }

    /**
     * @return the magazineQuota
     */
    public String getMagazineQuota() {
        return magazineQuota;
    }

    /**
     * @param magazineQuota
     *            the magazineQuota to set
     */
    public void setMagazineQuota(String magazineQuota) {
        this.magazineQuota = magazineQuota;
    }

    /**
     * @param identity
     *            the identity to set
     */
    public void setIdentity(int identity) {
        this.identity = identity;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     *            the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the identity
     */
    public long getIdentity() {
        return identity;
    }

    /**
     * @param identity
     *            the identity to set
     */
    public void setIdentity(long identity) {
        this.identity = identity;
    }

    /**
     * @return the kind1
     */
    public long getKind1() {
        return kind1;
    }

    /**
     * @param kind1
     *            the kind1 to set
     */
    public void setKind1(long kind1) {
        this.kind1 = kind1;
    }

    /**
     * @return the kind2
     */
    public long getKind2() {
        return kind2;
    }

    /**
     * @param kind2
     *            the kind2 to set
     */
    public void setKind2(long kind2) {
        this.kind2 = kind2;
    }

    /**
     * @return the region1
     */
    public long getRegion1() {
        return region1;
    }

    /**
     * @param region1
     *            the region1 to set
     */
    public void setRegion1(long region1) {
        this.region1 = region1;
    }

    /**
     * @return the region2
     */
    public long getRegion2() {
        return region2;
    }

    /**
     * @param region2
     *            the region2 to set
     */
    public void setRegion2(long region2) {
        this.region2 = region2;
    }

    /**
     * @return the region3
     */
    public long getRegion3() {
        return region3;
    }

    /**
     * @param region3
     *            the region3 to set
     */
    public void setRegion3(long region3) {
        this.region3 = region3;
    }

    /**
     * @return the region4
     */
    public long getRegion4() {
        return region4;
    }

    /**
     * @param region4
     *            the region4 to set
     */
    public void setRegion4(long region4) {
        this.region4 = region4;
    }

    /**
     * @return the views
     */
    public long getViews() {
        return views;
    }

    /**
     * @param views
     *            the views to set
     */
    public void setViews(long views) {
        this.views = views;
    }

    /**
     * @return the enjoyments
     */
    public long getEnjoyments() {
        return enjoyments;
    }

    /**
     * @param enjoyments
     *            the enjoyments to set
     */
    public void setEnjoyments(long enjoyments) {
        this.enjoyments = enjoyments;
    }

    /**
     * @return the boredoms
     */
    public long getBoredoms() {
        return boredoms;
    }

    /**
     * @param boredoms
     *            the boredoms to set
     */
    public void setBoredoms(long boredoms) {
        this.boredoms = boredoms;
    }

    /**
     * @return the presenter
     */
    public long getPresenter() {
        return presenter;
    }

    /**
     * @param presenter
     *            the presenter to set
     */
    public void setPresenter(long presenter) {
        this.presenter = presenter;
    }

    /**
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * @return the etime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getEtime() {
        return etime;
    }

    /**
     * @param etime
     *            the etime to set
     */
    public void setEtime(Date etime) {
        this.etime = etime;
    }

    /**
     * @return the price
     */
    public long getPrice() {
        return price;
    }

    /**
     * @param price
     *            the price to set
     */
    public void setPrice(long price) {
        this.price = price;
    }

    public String getPriceYuan() {
        return Moneys.castMoney2Yuan(this.getPrice());
    }

    /**
     * @return the parentId
     */
    public long getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the lastReplyTime
     */
    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getLastReplyTime() {
        return lastReplyTime;
    }

    public String getLastReplyTimeStr() {
        return Dates.format(getLastReplyTime(), Constants.TIME_YYYY_MM_DD_HH_MM_SS);
    }

    /**
     * @param lastReplyTime
     *            the lastReplyTime to set
     */
    public void setLastReplyTime(Date lastReplyTime) {
        this.lastReplyTime = lastReplyTime;
    }

    /**
     * @return the pricedecimal
     */
    public String getPricedecimal() {
        return pricedecimal;
    }

    /**
     * @param pricedecimal
     *            the pricedecimal to set
     */
    public void setPricedecimal(String pricedecimal) {
        this.pricedecimal = pricedecimal;
    }

    /**
     * @return the kind1name
     */
    public String getKind1name() {
        return kind1name;
    }

    /**
     * @param kind1name
     *            the kind1name to set
     */
    public void setKind1name(String kind1name) {
        this.kind1name = kind1name;
    }

    /**
     * @return the kind2name
     */
    public String getKind2name() {
        return kind2name;
    }

    /**
     * @param kind2name
     *            the kind2name to set
     */
    public void setKind2name(String kind2name) {
        this.kind2name = kind2name;
    }

    /**
     * @return the region1name
     */
    public String getRegion1name() {
        return region1name;
    }

    /**
     * @param region1name
     *            the region1name to set
     */
    public void setRegion1name(String region1name) {
        this.region1name = region1name;
    }

    /**
     * @return the region2name
     */
    public String getRegion2name() {
        return region2name;
    }

    /**
     * @param region2name
     *            the region2name to set
     */
    public void setRegion2name(String region2name) {
        this.region2name = region2name;
    }

    /**
     * @return the region3name
     */
    public String getRegion3name() {
        return region3name;
    }

    /**
     * @param region3name
     *            the region3name to set
     */
    public void setRegion3name(String region3name) {
        this.region3name = region3name;
    }

    /**
     * @return the region4name
     */
    public String getRegion4name() {
        return region4name;
    }

    /**
     * @param region4name
     *            the region4name to set
     */
    public void setRegion4name(String region4name) {
        this.region4name = region4name;
    }

}
