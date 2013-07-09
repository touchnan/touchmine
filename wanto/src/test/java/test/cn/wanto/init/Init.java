/*
 * test.cn.wanto.init.Init.java
 * Aug 30, 2012 
 */
package test.cn.wanto.init;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.touch.kit.json.JackJsonKit;
import cn.touchin.Contexts;
import cn.wanto.busi.serv.IUserService;
import cn.wanto.busi.serv.IWordbookService;
import cn.wanto.dto.bean.UserDto;
import cn.wanto.entity.User;
import cn.wanto.entity.Wordbook;
import cn.wanto.util.enume.WordbookType;

/**
 * Aug 30, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class Init {
    static {
        Contexts.init(new ClassPathXmlApplicationContext("spring/context.xml"));
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        initUser();
        initWordbook();
        System.exit(0);
    }

    public static void initUser() {
        String[] ns = { "hancq@qq.com", "zhouzy@qq.com" };
        String[] nns = { "韩成强", "周祝燕" };
        for (int i = 0; i < ns.length; i++) {
            User u = new User();
            u.setHidden(true);
            u.setUsername(ns[i]);
            u.setNickname(nns[i]);
            u.setType(-1);
            u.setPasswd(UserDto.encryptPasswd("123456"));
            Contexts.getBean(IUserService.class).save(null, u);
        }
    }

    private static void initWordbook() {
        initWordbook(WordbookType.SHOP, "shoptype.js");
        initWordbook(WordbookType.REGION, "region.js");
        initWordbook(WordbookType.SCHOOL, "school.js");
    }

    private static void initWordbook(WordbookType wordbookType, String fileName) {
        JackJsonKit kit = Contexts.getBean(JackJsonKit.class);
        @SuppressWarnings({ "unchecked" })
        List<LinkedHashMap<String, Object>> list =
                kit.parse(List.class, new File("src/test/resources/wordbook/" + fileName));
        initWordbook(wordbookType.ordinal(), 0, list);
    }

    private static void initWordbook(int kind, long parentId, List<LinkedHashMap<String, Object>> forest) {
        IWordbookService serv = Contexts.getBean(IWordbookService.class);
        for (LinkedHashMap<String, Object> dto : forest) {
            Wordbook wb = new Wordbook();
            wb.setName(dto.get("name").toString());
            wb.setKind(kind);
            wb.setParentId(parentId);
            wb = serv.save(null, wb);
            @SuppressWarnings("unchecked")
            List<LinkedHashMap<String, Object>> children = (List<LinkedHashMap<String, Object>>) dto.get("children");
            if (children != null) {
                initWordbook(kind, wb.getId().intValue(), children);
            }

        }
    }
}
