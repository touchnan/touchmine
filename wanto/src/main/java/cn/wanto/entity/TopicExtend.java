/*
 * cn.wanto.entity.TopicExtend.java
 * Sep 2, 2012 
 */
package cn.wanto.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.nutz.lang.Strings;

import cn.wanto.util.AppConstants;

/**
 * Sep 2, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Entity
@Table(name = "t_topic_extend")
@org.hibernate.annotations.Table(appliesTo = "t_topic_extend", comment = "主题扩展-店铺")
public class TopicExtend {
    @Id
    @Column(name = "topic_id", columnDefinition = AppConstants.DEFINITION_PRIMARY_KEY)
    private Long topicId;

    @Column(name = "topic_identity", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long identity;// 商铺ID

    @Column(name = "topic_text", length = 150)
    private String text;// 详细地址

    @Column(name = "topic_addr", length = 150)
    private String addr;// 详细地址

    @Column(name = "topic_region", length = 100)
    private String region;// 地区
    @Transient
    private long region1;
    @Transient
    private long region2;
    @Transient
    private long region3;
    @Transient
    private long region4;

    @Column(name = "topic_kind", length = 50)
    private String kind;// 类型
    @Transient
    private long kind1;// 大类
    @Transient
    private long kind2;// 细类

    @Column(name = "is_vip")
    private boolean vip;// 是否加精

    @Column(name = "topic_quota", length = 30)
    private String magazineQuota;// 杂志编号

    @Column(name = "topic_presenter", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long presenter;// 推荐者

    @Column(name = "vip_quota")
    private String vipQuota;

    @Column(name = "keeper_said", length = 150)
    private String said;// 店长说

    @Column(name = "topic_phone", length = 30)
    private String phone;// 电话
    
    @Column(name = "topic_price", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long price;// 单价

    @Column(name = "orderid", columnDefinition = AppConstants.DEFINITION_LONG_DEFAULT)
    private long orderid;// 排序    
    
    /**
     * 准备转换数据
     * 
     * @return
     */
    public TopicExtend prepare() {
        return prepareregion().prepareKind();
    }

    private TopicExtend prepareregion() {
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

    private TopicExtend prepareKind() {
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
     * @return the topicId
     */
    public Long getTopicId() {
        return topicId;
    }

    /**
     * @param topicId the topicId to set
     */
    public void setTopicId(Long topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the identity
     */
    public long getIdentity() {
        return identity;
    }

    /**
     * @param identity the identity to set
     */
    public void setIdentity(long identity) {
        this.identity = identity;
    }

    /**
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * @param text the text to set
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * @return the addr
     */
    public String getAddr() {
        return addr;
    }

    /**
     * @param addr the addr to set
     */
    public void setAddr(String addr) {
        this.addr = addr;
    }

    /**
     * @return the region
     */
    public String getRegion() {
        return region;
    }

    /**
     * @param region the region to set
     */
    public void setRegion(String region) {
        this.region = region;
    }

    /**
     * @return the region1
     */
    public long getRegion1() {
        return region1;
    }

    /**
     * @param region1 the region1 to set
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
     * @param region2 the region2 to set
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
     * @param region3 the region3 to set
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
     * @param region4 the region4 to set
     */
    public void setRegion4(long region4) {
        this.region4 = region4;
    }

    /**
     * @return the kind
     */
    public String getKind() {
        return kind;
    }

    /**
     * @param kind the kind to set
     */
    public void setKind(String kind) {
        this.kind = kind;
    }

    /**
     * @return the kind1
     */
    public long getKind1() {
        return kind1;
    }

    /**
     * @param kind1 the kind1 to set
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
     * @param kind2 the kind2 to set
     */
    public void setKind2(long kind2) {
        this.kind2 = kind2;
    }

    /**
     * @return the vip
     */
    public boolean isVip() {
        return vip;
    }

    /**
     * @param vip the vip to set
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
     * @param magazineQuota the magazineQuota to set
     */
    public void setMagazineQuota(String magazineQuota) {
        this.magazineQuota = magazineQuota;
    }

    /**
     * @return the presenter
     */
    public long getPresenter() {
        return presenter;
    }

    /**
     * @param presenter the presenter to set
     */
    public void setPresenter(long presenter) {
        this.presenter = presenter;
    }

    /**
     * @return the vipQuota
     */
    public String getVipQuota() {
        return vipQuota;
    }

    /**
     * @param vipQuota the vipQuota to set
     */
    public void setVipQuota(String vipQuota) {
        this.vipQuota = vipQuota;
    }

    /**
     * @return the said
     */
    public String getSaid() {
        return said;
    }

    /**
     * @param said the said to set
     */
    public void setSaid(String said) {
        this.said = said;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the price
     */
    public long getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(long price) {
        this.price = price;
    }

    /**
     * @return the orderid
     */
    public long getOrderid() {
        return orderid;
    }

    /**
     * @param orderid the orderid to set
     */
    public void setOrderid(long orderid) {
        this.orderid = orderid;
    }
    
}
