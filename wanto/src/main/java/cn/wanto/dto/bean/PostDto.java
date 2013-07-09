package cn.wanto.dto.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.json.annotations.JSON;

import cn.touch.kit.Dates;
import cn.touchin.dto.Dto;
import cn.touchin.util.Constants;
import cn.wanto.entity.Post;

public class PostDto extends Dto<Post> {

    private static final long serialVersionUID = -8949904058422611218L;

    private Long id;
    private long parentId;
    private String subject; // 帖子主题
    private String text; // 帖子内容
    private String bookMark; // 帖子标签
    private String postUser; // 发帖人
    private String postIp; // 发帖人IP
    private Date time; // 发帖时间
    private Date editTime; // 最后编辑时间
    private Date lastReplyTime;

    private int type; // 类型：1-帖子；2-回复

    private long topicId;

    private long editCount; // 编辑次数
    private long replyCount; // 回复次数

    private long clickCount; // 点击次数（浏览次数）
    private long usefulCount; // 觉得有用的次数
    private long unusefulCount; // 觉得没用的次数

    private long countTemp;

    private String userAvatar; // 发帖人头像
    private String userNickname;
    private long userId;// 发帖人

    private long textId;

    private String topicTitle;

    public List<PostDto> replies;// 回复
    private UserDto owner;//
    private long lvl;// 自己所在楼层

    private boolean vipShop;// 是否加V店铺,供话题使用
    private long shopPresenter;// 店铺推荐人ID
    
    private boolean totop;

    @Override
    public Dto<Post> invokeForCallback(Post obj) {
        return this.copyProperties(obj);
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getBookMark() {
        return bookMark;
    }

    public void setBookMark(String bookMark) {
        this.bookMark = bookMark;
    }

    public String getPostUser() {
        return postUser;
    }

    public void setPostUser(String postUser) {
        this.postUser = postUser;
    }

    public String getPostIp() {
        return postIp;
    }

    public void setPostIp(String postIp) {
        this.postIp = postIp;
    }

    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getTime() {
        return time;
    }

    public String getTimeStr() {
        return Dates.format(getTime(), Constants.TIME_YYYY_MM_DD_HH_MM_SS);
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @JSON(format = Constants.TIME_YYYY_MM_DD_HH_MM_SS)
    public Date getEditTime() {
        return editTime;
    }

    public String getEditTimeStr() {
        return Dates.format(getEditTime(), Constants.TIME_YYYY_MM_DD_HH_MM_SS);
    }

    public void setEditTime(Date editTime) {
        this.editTime = editTime;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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
     * @return the topicId
     */
    public long getTopicId() {
        return topicId;
    }

    /**
     * @param topicId
     *            the topicId to set
     */
    public void setTopicId(long topicId) {
        this.topicId = topicId;
    }

    /**
     * @return the editCount
     */
    public long getEditCount() {
        return editCount;
    }

    /**
     * @param editCount
     *            the editCount to set
     */
    public void setEditCount(long editCount) {
        this.editCount = editCount;
    }

    /**
     * @return the clickCount
     */
    public long getClickCount() {
        return clickCount;
    }

    /**
     * @param clickCount
     *            the clickCount to set
     */
    public void setClickCount(long clickCount) {
        this.clickCount = clickCount;
    }

    /**
     * @return the replyCount
     */
    public long getReplyCount() {
        return replyCount;
    }

    /**
     * @param replyCount
     *            the replyCount to set
     */
    public void setReplyCount(long replyCount) {
        this.replyCount = replyCount;
    }

    /**
     * @return the usefulCount
     */
    public long getUsefulCount() {
        return usefulCount;
    }

    /**
     * @param usefulCount
     *            the usefulCount to set
     */
    public void setUsefulCount(long usefulCount) {
        this.usefulCount = usefulCount;
    }

    /**
     * @return the unusefulCount
     */
    public long getUnusefulCount() {
        return unusefulCount;
    }

    /**
     * @param unusefulCount
     *            the unusefulCount to set
     */
    public void setUnusefulCount(long unusefulCount) {
        this.unusefulCount = unusefulCount;
    }

    /**
     * @return the countTemp
     */
    public long getCountTemp() {
        return countTemp;
    }

    /**
     * @param countTemp
     *            the countTemp to set
     */
    public void setCountTemp(long countTemp) {
        this.countTemp = countTemp;
    }

    /**
     * @return the userAvatar
     */
    public String getUserAvatar() {
        return userAvatar;
    }

    /**
     * @param userAvatar
     *            the userAvatar to set
     */
    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    /**
     * @return the textId
     */
    public long getTextId() {
        return textId;
    }

    /**
     * @param textId
     *            the textId to set
     */
    public void setTextId(long textId) {
        this.textId = textId;
    }

    /**
     * @return the topicTitle
     */
    public String getTopicTitle() {
        return topicTitle;
    }

    /**
     * @param topicTitle
     *            the topicTitle to set
     */
    public void setTopicTitle(String topicTitle) {
        this.topicTitle = topicTitle;
    }

    /**
     * @return the replies
     */
    public List<PostDto> getReplies() {
        return replies;
    }

    public void addReply(PostDto reply) {
        if (reply != null) {
            if (replies == null) {
                this.setReplies(new ArrayList<PostDto>(8));
            }
            this.getReplies().add(reply);
        }
    }

    /**
     * @param replies
     *            the replies to set
     */
    public void setReplies(List<PostDto> replies) {
        this.replies = replies;
    }

    /**
     * @return the userId
     */
    public long getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(long userId) {
        this.userId = userId;
    }

    /**
     * @return the owner
     */
    public UserDto getOwner() {
        return owner;
    }

    /**
     * @param owner
     *            the owner to set
     */
    public void setOwner(UserDto owner) {
        this.owner = owner;
    }

    /**
     * @return the lvl
     */
    public long getLvl() {
        return lvl;
    }

    /**
     * @param lvl
     *            the lvl to set
     */
    public void setLvl(long lvl) {
        this.lvl = lvl;
    }

    /**
     * @return the userNickname
     */
    public String getUserNickname() {
        return userNickname;
    }

    /**
     * @param userNickname
     *            the userNickname to set
     */
    public void setUserNickname(String userNickname) {
        this.userNickname = userNickname;
    }



    /**
     * @return the vipShop
     */
    public boolean isVipShop() {
        return vipShop;
    }

    /**
     * @param vipShop the vipShop to set
     */
    public void setVipShop(boolean vipShop) {
        this.vipShop = vipShop;
    }

    /**
     * @return the shopPresenter
     */
    public long getShopPresenter() {
        return shopPresenter;
    }

    /**
     * @param shopPresenter
     *            the shopPresenter to set
     */
    public void setShopPresenter(long shopPresenter) {
        this.shopPresenter = shopPresenter;
    }

    /**
     * @return the totop
     */
    public boolean isTotop() {
        return totop;
    }

    /**
     * @param totop the totop to set
     */
    public void setTotop(boolean totop) {
        this.totop = totop;
    }

}
