/*
 * cn.wanto.busi.www.mgr.UserAction.java
 * Sep 3, 2012 
 */
package cn.wanto.busi.www.mgr;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.page.PropertyFilter;
import cn.wanto.busi.serv.IMessageService;
import cn.wanto.busi.serv.IPostService;
import cn.wanto.busi.serv.IUserService;
import cn.wanto.busi.tools.ImagePath;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.entity.User;
import cn.wanto.util.AppConstants;

/**
 * Sep 3, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class UserAction extends MgrAction {
    private static final long serialVersionUID = -2802272636885752955L;

    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private IPostService postService;

    private UserDto user;
    private Integer num;
    private int topicType;

    public String profile() throws Exception {
        // 设置

        ar = fetchAppRequest();
        user = userService.self(ar);

        return SUCCESS;
    }

    public String updateProfile() throws Exception {
        ar = fetchAppRequest();
        User u = userService.fetchEntity(ar, new PropertyFilter("EQS_username", ar.getUsername()));
        u.setNickname(user.getNickname());
        u.setSchoolId(user.getSchoolId());
        u.setSchoolprivate(user.isSchoolprivate());
        u.setHometownId(user.getHometownId());
        u.setHometownPrivate(user.isHometownPrivate());
        u.setSearched(user.isSearched());
        u.setMood(user.getMood());
        String[] list = user.getLabel().split(" ");
        String label = "";
        for (String s : list) {
            if ("".equals(s.trim()))
                continue;
            if (s.trim().length() > 10) {
                super.setErrorVo(new ErrorVo("单个标签长度不超过10个字!请检查!", ErrorCodeVo.EXCEPTION_THROWN));
                return "error-json";
            }
            if (label.length() == 0) {
                label += s.trim();
            } else {
                label = label + " " + s.trim();
            }
        }
        u.setLabel(label);

        userService.update(ar, u);
        return "obj-json";
    }

    public String news() throws Exception {
        // 最新动态
        ar = fetchAppRequest();
        setCount(messageService.findMessagesCountByUserId(ar.getLoginId(), null));
        num = messageService.findMessagesNewCountByUserId(ar.getLoginId(), null);
        return SUCCESS;
    }

    public String topic() throws Exception {
        // 我的话题
        ar = fetchAppRequest();
        this.setCount(postService.countMyPostTexts(ar, getPageData(), topicType));
        return SUCCESS;
    }

    public String reply() throws Exception {
        // 我的回复
        ar = fetchAppRequest();
        this.setCount(postService.countMyReplys(ar, getPageData(), topicType));
        return SUCCESS;
    }

    public String avatar() throws Exception {
        ar = fetchAppRequest();
        user = userService.self(ar);

        return SUCCESS;
    }

    public String avatarSave() throws Exception {
        ar = fetchAppRequest();
        User u = userService.fetchEntity(ar, new PropertyFilter("EQS_username", ar.getUsername()));
        if (!Strings.isBlank(user.getUploadAvatar())) {
            try {
                ImagePath image = AppConstants.copyTempFile2StorePath(user.getUploadAvatar(), "原上传的头像图片已失效,请重新上传头像图片");
                u.setAvatar(image.getRelativeFilename());
                
                ImagePath thumbnail =
                    AppConstants.copyTempFile2StorePath(AppConstants.thumbnailName(user.getUploadAvatar()),
                            "原上传的头像图片已失效,请重新上传头像图片");
                u.setThumbnail(thumbnail.getRelativeFilename());                
            } catch (Exception e) {
                super.setErrorVo(new ErrorVo("原上传的头像图片已失效,请重新上传头像图片!", ErrorCodeVo.EXCEPTION_THROWN));
                return "error-json";
            }

        }
        userService.update(ar, u);
        return "obj-json";
    }

    public String passwd() throws Exception {
        return SUCCESS;
    }

    public String msp() throws Exception {
        // 密码修改
        int count = userService.resetSelfPasswd(fetchAppRequest(), user.getPasswd(), user.getNewPasswd());
        if (count < 1) {
            throw new RuntimeException("旧密码错误, 无权修改密码!");
        }
        return RESULT_JSON;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    /**
     * @return the topicType
     */
    public int getTopicType() {
        return topicType;
    }

    /**
     * @param topicType
     *            the topicType to set
     */
    public void setTopicType(int topicType) {
        this.topicType = topicType;
    }

}
