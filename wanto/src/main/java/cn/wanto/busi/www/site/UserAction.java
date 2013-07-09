/*
 * cn.wanto.busi.www.site.UserAction.java
 * Sep 2, 2012 
 */
package cn.wanto.busi.www.site;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import cn.touch.kit.Config;
import cn.touch.kit.encrypt.EncodeUtils;
import cn.touchin.dto.ErrorCodeVo;
import cn.touchin.dto.ErrorVo;
import cn.touchin.dto.VirtualUser;
import cn.touchin.page.Pagination;
import cn.touchin.page.PropertyFilter;
import cn.wanto.busi.serv.IMessageService;
import cn.wanto.busi.serv.IUserService;
import cn.wanto.busi.tools.ImagePath;
import cn.wanto.busi.www.AppPaginationAction;
import cn.wanto.concurrent.ThreadPool;
import cn.wanto.dto.Vu;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.entity.User;
import cn.wanto.event.SimpleMailTask;
import cn.wanto.filter.WantoFilter;
import cn.wanto.util.AppConstants;
import cn.wanto.util.enume.MessageKind;
import cn.wanto.util.enume.MessageType;

import com.opensymphony.xwork2.ActionContext;

/**
 * Sep 2, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class UserAction extends AppPaginationAction {
    private static final long serialVersionUID = 7309540329029495038L;
    // private static final Config deploy = new Config("email");
    private String email_master = new Config("email").getString("email.username");

    private UserDto user;
    private String url;

    private Object[] validateReturn = new Object[3];

    @Autowired
    private IUserService userService;
    @Autowired
    private IMessageService messageService;
    @Autowired
    private SimpleMailMessage templateMessage;
    @Autowired
    private MailSender mailSender;

    public String contactus() throws Exception {
        // 联系我们
        url = AppConstants.encodeReqUrl(url);
        return SUCCESS;
    }

    public String sendContactus() throws Exception {
        if (user != null) {
            SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
            msg.setTo(email_master);
            msg.setSubject(user.getTitle() + " -- " + user.getUsername().trim());
            msg.setText(user.getContent());
            sendMail(msg);
        }
        return "obj-json";
    }

    private void sendMail(SimpleMailMessage msg) {
        ThreadPool.execute(new SimpleMailTask(this.mailSender, msg));
    }

    public String forgetPwd() throws Exception {
        // 忘记密码
        return SUCCESS;
    }

    public String sendPwdUrl() throws Exception {

        String email = user.getUsername().trim();
        PropertyFilter p = new PropertyFilter("EQS_username", email);
        User u = userService.fetchEntity(null, p);
        if (u == null) {
            super.setErrorVo(new ErrorVo("您输入的邮箱有误,请检查!", ErrorCodeVo.EXCEPTION_THROWN));
            return "error-json";
        }

        String root = (String) getHttpServletRequest().getAttribute("context.path.root");
        String parm = email + "," + new Date().getTime();
        String url = root + "/site/User-authPwd.htm?user.auth=" + EncodeUtils.urlEncode(parm);
        SimpleMailMessage msg = new SimpleMailMessage(templateMessage);
        msg.setTo(email);
        String content =
                "亲爱的用户  " + email + " 您好！" +

                " 您收到这封这封电子邮件是因为您 (也可能是某人冒充您的名义) 申请了一个新的密码。" + "假如这不是您本人所申请, 请不用理会这封电子邮件, 但是如果您持续收到这类的信件骚扰, 请您尽快联络管理员。"
                        + "要使用新的密码, 请使用以下链接启用密码。";
        msg.setText(content + "    " + url);
        sendMail(msg);
        u.setAtuhUrl(url);
        userService.update(null, u);

        return "obj-json";
    }

    public String authPwd() throws Exception {
        if (user == null || Strings.isBlank(user.getAuth())) {
            return "logout";
        }
        return SUCCESS;
    }

    public String authChangePwd() throws Exception {
        if (user == null || Strings.isBlank(user.getAuth())) {
            return "logout";
        }
        PropertyFilter p = new PropertyFilter("EQS_username", user.getAuth().split(",")[0]);
        User u = userService.fetchEntity(null, p);
        if (u == null || Strings.isBlank(u.getAtuhUrl()) || u.getAtuhUrl().equals(user.getAuth())) {
            super.setErrorVo(new ErrorVo("该链接已失效!", ErrorCodeVo.EXCEPTION_THROWN));
            return "error-json";
        }
        u.setPasswd(UserDto.encryptPasswd(user.getPasswd()));
        u.setAtuhUrl(null);
        userService.update(null, u);
        return "obj-json";
    }

    public String auth() throws Exception {
        // 登录页面
        return SUCCESS;
    }

    public String registers() throws Exception {
        // 注册页面
        return SUCCESS;
    }

    public String registered() throws Exception {
        // 注册用户
        user.setUsername(user.getUsername().trim());
        user.setPasswd(user.getPasswd().trim());
        boolean exist = userService.emailExist(user.getUsername());
        if (!exist) {
            super.setErrorVo(new ErrorVo("此名称已被其他人使用!", ErrorCodeVo.EXCEPTION_THROWN));
            // this.addFieldError("username", "此名称已被其他人使用!");
            return "error-json";
        }
        if (!checkEmail(user.getUsername())) {
            super.setErrorVo(new ErrorVo("用户名不是email格式,请检查!", ErrorCodeVo.EXCEPTION_THROWN));
            // this.addFieldError("username", "用户名不是email格式,请检查!");
            return "error-json";
        }

        User u = user.toEntity();
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
        } else {
            u.setAvatar(null);
        }
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
        u.setPasswd(UserDto.encryptPasswd(user.getPasswd()));
        u.setRegTime(new Date());
        // u.setType(UserRole.NORMAL.ordinal());
        userService.save(null, u);
        messageService.insertMessage(null, u.getId(), MessageType.SYSTEMNEWS.ordinal(), MessageKind.WELCOME.ordinal(),
                null, null, null);
        VirtualUser vu = userService.auth(user.getUsername(), user.getPasswd());
        if (vu != null) {
            setObj(vu);
            storeVirtualUser(vu);
            storeAppRequest(vu);
            WantoFilter.store2Cookie(getHttpServletRequest(), getHttpServletResponse(), (Vu) vu, -1);
        }
        return "obj-json";
    }

    private boolean checkEmail(String mail) {
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(mail);
        return m.find();
    }

    public String emailExist() throws Exception {
        // 检查email 是否存在
        ActionContext ac = ActionContext.getContext();
        HttpServletRequest request = (HttpServletRequest) ac.get(ServletActionContext.HTTP_REQUEST);
        String validateId = request.getParameter("fieldId");// 获取验证的域
        String fieldValue = request.getParameter("fieldValue");
        boolean exist = userService.emailExist(fieldValue);

        validateReturn[0] = validateId;
        if (exist) {
            validateReturn[1] = true;
            validateReturn[2] = "此帐号名称可以使用";
        }

        else {
            validateReturn[1] = false;
            validateReturn[2] = "此名称已被其他人使用";
        }

        return "return-json";
    }

    public String register() throws Exception {

        return RESULT_JSON;
    }

    // @Autowired
    // private IQueryService queryService;

    public String users() throws Exception {
        return SUCCESS;
    }

    public String listUsers() throws Exception {
        this.setPageData(userService.findPage(fetchAppRequest(), assemblePaginationInfo()));
        return RESULT_PAGE_JSON;
    }

    public String cacheUsers() throws Exception {
        return SUCCESS;
    }

    public String listCacheUsers() throws Exception {
        Pagination p = assemblePaginationInfo();
        List<UserDto> rows = userService.findCacheUser(fetchAppRequest());
        p.setTotal(rows.size());
        p.setRows(rows);
        this.setPageData(p);
        return RESULT_PAGE_JSON;
    }

    public String saveUser() throws Exception {
        if (isDel()) {
            userService.deletes(fetchAppRequest(), ids_a());
        } else {
            userService.save(fetchAppRequest(), user);
        }
        return RESULT_JSON;
    }

    public String msp() throws Exception {
        int count = userService.resetSelfPasswd(fetchAppRequest(), user.getPasswd(), user.getNewPasswd());
        if (count < 1) {
            throw new RuntimeException("旧密码错误, 无权修改密码!");
        }
        return RESULT_JSON;
    }

    /**
     * @return the user
     */
    public UserDto getUser() {
        return user;
    }

    /**
     * @param user
     *            the user to set
     */
    public void setUser(UserDto user) {
        this.user = user;
    }

    public Object[] getValidateReturn() {
        return validateReturn;
    }

    public void setValidateReturn(Object[] validateReturn) {
        this.validateReturn = validateReturn;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

}
