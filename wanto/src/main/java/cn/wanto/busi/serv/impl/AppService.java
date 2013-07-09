/*
 * cn.wanto.busi.serv.impl.AppService.java
 * Sep 22, 2012 
 */
package cn.wanto.busi.serv.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touch.db.Db;
import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.db.jdbc.ISQLParser;
import cn.touchin.dto.Dto;
import cn.touchin.serv.BaseService;
import cn.wanto.dto.Ar;
import cn.wanto.dto.bean.TagDto;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.entity.Wordbook;

/**
 * Sep 22, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public abstract class AppService<T extends Dto<E>, E, PK extends Serializable> extends BaseService<T, E, PK> {
    @Autowired
    protected Db db;

    @Autowired
    protected ISQLParser sqlParser;

    protected UserDto fetchUser(long userId) {

        String usersql =
                "SELECT u.nickname AS nickname,u.username AS username,u.user_avatar AS avatar,u.user_school_id AS schoolId, u.user_hometown_id AS hometownId, u.user_school_private AS schoolprivate,u.user_hometown_private AS hometownPrivate,u.user_label AS label FROM t_user u WHERE u.user_id=?";
        UserDto owner = db.findFirst(UserDto.class, usersql, userId);
        if (owner != null) {
            List<TagDto> tags = new ArrayList<TagDto>(10);
            owner.setSchool(findWordbookNameById(owner.getSchoolId()));
            owner.setHometown(findWordbookNameById(owner.getHometownId()));
            /*-
            //学校和家乡此处需要根据具体的业务逻辑处理
            if (owner.getSchoolId()>0) {
                tags.add(findTagsOnWordbookDtoById(owner.getSchoolId(), TagDto.schKid));
            }
            
            if (owner.getHometownId()>0) {
                tags.add(findTagsOnWordbookDtoById(owner.getHometownId(), TagDto.schRid));
            }
            **/
            
            if (!Strings.isBlank(owner.getLabel())) {
                String[] labs = owner.getLabel().split(" ");
                for (String lab: labs) {
                    if (!Strings.isBlank(lab)) {
                        tags.add(new TagDto(lab));
                    }
                }
            }
            if (!tags.isEmpty()) {
                owner.setTags(tags);
            }
        }
        return owner;
    }

    protected boolean isMe(Ar ar, long userId) {
        if (ar != null && ar.getLoginId() != null) {// 是否店长
            return ar.getLoginId() == userId;
        }
        return false;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findWordbookNameById(java.lang.Long)
     */
    public String findWordbookNameById(Long id) {
        if (id == null || id == 0) {
            return "";
        }
        Wordbook o = findWordbookById(id);
        return o == null ? "" : o.getName();
    }

    @SuppressWarnings("rawtypes")
    private Wordbook findWordbookById(Long id) {
        return ((IHibernateDao) dao()).getHibernateTemplate().load(Wordbook.class, id);
    }
    
    protected TagDto findTagsOnWordbookDtoById(Long id, String searchField) {
        Wordbook o = findWordbookById(id);
        return o == null ? null : new TagDto(o, searchField);
    }
}
