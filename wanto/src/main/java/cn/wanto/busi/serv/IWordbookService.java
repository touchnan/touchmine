package cn.wanto.busi.serv;

import java.util.List;

import cn.touchin.dto.AppRequest;
import cn.touchin.kit.bean.NameValuePair;
import cn.touchin.serv.IService;
import cn.wanto.dto.TreeDto;
import cn.wanto.dto.bean.WordbookDto;
import cn.wanto.entity.Wordbook;

/**
 * Aug 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
public interface IWordbookService extends IService<WordbookDto, Wordbook, Long> {

    /**
     * @param ar
     * @param type
     * @param parentId
     * @return
     */
    List<NameValuePair> getNameValuePairs(AppRequest ar, int type, long parentId);

    /**
     * @param ar
     * @return
     */
    List<NameValuePair> getTopNameValuePairs(AppRequest ar);

    /**
     * @param ar
     * @return
     */
    List<NameValuePair> getNameValuePairs(AppRequest ar);

    
    List<TreeDto> getWordbookForest(AppRequest ar, int type);

}
