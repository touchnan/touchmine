package cn.wanto.busi.www.site;

import java.util.ArrayList;
import java.util.List;

import org.nutz.lang.Mirror;
import org.nutz.log.Logs;
import org.springframework.beans.factory.annotation.Autowired;

import cn.touchin.busi.www.BaseAction;
import cn.touchin.kit.bean.NameValuePair;
import cn.wanto.busi.serv.IWordbookService;
import cn.wanto.dto.TreeDto;
import cn.wanto.util.enume.WordbookType;

/**
 * Aug 11, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public class EnumAction extends BaseAction {
    private static final long serialVersionUID = -8037193920565836516L;

    @Autowired
    IWordbookService wordbookService;

    private int kind;// 类别

    private int parentId;// 父id
    
    private TreeDto tree;
    
    /*
     * (non-Javadoc)
     * 
     * @see com.opensymphony.xwork2.ActionSupport#execute()
     */
    @Override
    public String execute() throws Exception {
        return super.execute();
    }

    public String wordbooktypes() {
        format2Obj(new Enum[] { WordbookType.SCHOOL, WordbookType.REGION, WordbookType.SHOP });
        return RESULT_OBJECT_JSON;
    }

    public String wordbooks() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), kind, parentId));
        return RESULT_OBJECT_JSON;
    }

    public String allwordbooks() {
        List<NameValuePair> l = wordbookService.getNameValuePairs(fetchAppRequest());
        l.add(new NameValuePair("--", "0"));
        setObj(l);
        return RESULT_OBJECT_JSON;
    }
    
    public String optionWordbooks() {
        List<NameValuePair> l = wordbookService.getTopNameValuePairs(fetchAppRequest());
        l.add(new NameValuePair("--", "0"));
        setObj(l);
        return RESULT_OBJECT_JSON;
    }

    public String shopKind1s() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), WordbookType.SHOP.ordinal(), 0));
        return RESULT_OBJECT_JSON;
    }

    public String shopKinds() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), WordbookType.SHOP.ordinal(), parentId));
        return RESULT_OBJECT_JSON;
    }
    
    public String shopKindForest() {
        setObj(wordbookService.getWordbookForest(fetchAppRequest(), WordbookType.SHOP.ordinal()));
        return RESULT_OBJECT_JSON;
    }
    
    public String shopRegion1s() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), WordbookType.REGION.ordinal(), 0));
        return RESULT_OBJECT_JSON;
    }

    public String shopRegions() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), WordbookType.REGION.ordinal(), parentId));
        return RESULT_OBJECT_JSON;
    }

    public String shopRegionForest() {
        setObj(wordbookService.getWordbookForest(fetchAppRequest(), WordbookType.REGION.ordinal()));
        return RESULT_OBJECT_JSON;
    }
    
    public String schools() {
        setObj(wordbookService.getNameValuePairs(fetchAppRequest(), WordbookType.SCHOOL.ordinal(), 0));
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

    /**
     * @return the kind
     */
    public int getKind() {
        return kind;
    }

    /**
     * @param kind
     *            the kind to set
     */
    public void setKind(int kind) {
        this.kind = kind;
    }

    /**
     * @return the parentId
     */
    public int getParentId() {
        return parentId;
    }

    /**
     * @param parentId
     *            the parentId to set
     */
    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    /**
     * @return the tree
     */
    public TreeDto getTree() {
        return tree;
    }

    
}
