/*
 * cn.touchnan.busi.www.mgr.EnumAction.java
 * Aug 11, 2012 
 */
package cn.touchnan.busi.www.mgr;

import java.util.ArrayList;
import java.util.List;

import org.nutz.lang.Mirror;
import org.nutz.log.Logs;

import cn.touchin.busi.www.BaseAction;
import cn.touchin.kit.bean.NameValuePair;
import cn.touchnan.util.enume.UserRole;
import cn.touchnan.util.enume.UserType;

/**
 * Aug 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class EnumAction extends BaseAction {
    private static final long serialVersionUID = -8037193920565836516L;

    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public String userRoles() {
        format2Obj(new UserRole[] { UserRole.NORMALADMIN, UserRole.NORMAL });
        return RESULT_OBJECT_JSON;
    }

    public String userTypes() {
        format2Obj(new UserType[] { UserType.PORTION, UserType.DIGIT, UserType.MAMEGER });
        return RESULT_OBJECT_JSON;
    }

    private void format2Obj(Enum<?>[] values) {
        List<NameValuePair> list = new ArrayList<NameValuePair>(values.length);
        for (Enum<?> value : values) {
            try {
                list.add(new NameValuePair((String) Mirror.me(value.getClass()).getGetter("memo").invoke(value), value
                        .ordinal()));
            } catch (Exception e) {
                Logs.getLog(EnumAction.class).equals(e);
            }
        }
        setObj(list);
    }
}
