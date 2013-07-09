package cn.wanto.busi.serv.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.dto.AppRequest;
import cn.touchin.kit.bean.NameValuePair;
import cn.touchin.serv.BaseService;
import cn.wanto.busi.db.IWordbookDao;
import cn.wanto.busi.serv.IWordbookService;
import cn.wanto.dto.TreeDto;
import cn.wanto.dto.bean.WordbookDto;
import cn.wanto.entity.Wordbook;

/**
 * Aug 12, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class WordbookService extends BaseService<WordbookDto, Wordbook, Long> implements IWordbookService {
    @Autowired
    private IWordbookDao wordbookDao;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#dao()
     */
    @Override
    public IHibernateDao<Wordbook, Long> dao() {
        return wordbookDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IWordbookService#getNameValuePairs(cn.touchin.dto.AppRequest, int, int)
     */
    @Override
    public List<NameValuePair> getNameValuePairs(AppRequest ar, int type, long parentId) {
        @SuppressWarnings("unchecked")
        List<Wordbook> wbs =
                dao().currSession().createCriteria(getEntityClass())
                        .add(Restrictions.not(Restrictions.eq("hidden", true))).add(Restrictions.eq("kind", type))
                        .add(Restrictions.eq("parentId", parentId)).addOrder(Order.desc("orderid")).setCacheable(true)
                        .list();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(wbs.size());
        for (Wordbook peer : wbs) {
            pairs.add(new NameValuePair(peer.getName(), peer.getId()));
        }
        return pairs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IWordbookService#getTopNameValuePairs(cn.touchin.dto.AppRequest)
     */
    @Override
    public List<NameValuePair> getTopNameValuePairs(AppRequest fetchAppRequest) {
        @SuppressWarnings("unchecked")
        List<Wordbook> wbs =
                dao().currSession().createCriteria(getEntityClass())
                        .add(Restrictions.not(Restrictions.eq("hidden", true))).add(Restrictions.eq("parentId", 0))
                        .addOrder(Order.desc("orderid")).setCacheable(true).list();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(wbs.size());
        for (Wordbook peer : wbs) {
            pairs.add(new NameValuePair(peer.getName(), peer.getId()));
        }
        return pairs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IWordbookService#getNameValuePairs(cn.touchin.dto.AppRequest)
     */
    @Override
    public List<NameValuePair> getNameValuePairs(AppRequest ar) {
        @SuppressWarnings("unchecked")
        List<Wordbook> wbs =
                dao().currSession().createCriteria(getEntityClass())
                        .add(Restrictions.not(Restrictions.eq("hidden", true))).addOrder(Order.desc("orderid"))
                        .setCacheable(true).list();
        List<NameValuePair> pairs = new ArrayList<NameValuePair>(wbs.size());
        for (Wordbook peer : wbs) {
            pairs.add(new NameValuePair(peer.getName(), peer.getId()));
        }
        return pairs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.IWordbookService#getWordbookForest(cn.touchin.dto.AppRequest, int)
     */
    @Override
    public List<TreeDto> getWordbookForest(AppRequest ar, int type) {
        @SuppressWarnings("unchecked")
        List<Wordbook> wbs =
                dao().currSession().createCriteria(getEntityClass())
                        .add(Restrictions.not(Restrictions.eq("hidden", true))).add(Restrictions.eq("kind", type))
                        .addOrder(Order.desc("orderid")).setCacheable(true).list();
        return assemble(wbs);
    }

    public List<TreeDto> assemble(List<Wordbook> wbs) {
        List<TreeDto> root = new ArrayList<TreeDto>(wbs.size());
        if (wbs != null && !wbs.isEmpty()) {
            root = findChildren(0L, wbs);
        }
        return root;
    }

    /**
     * @param id
     * @param wbs
     * @return
     */
    private List<TreeDto> findChildren(Long id, List<Wordbook> wbs) {
        if (id != null) {
            List<TreeDto> root = new ArrayList<TreeDto>(wbs.size());
            if (wbs != null && !wbs.isEmpty()) {
                List<Long> ids = new ArrayList<Long>();
                for (Wordbook wb : wbs) {
                    if (wb.getParentId() == id) {
                        ids.add(wb.getId());
                        TreeDto node = new TreeDto(wb);
//                        wbs.remove(wb);
                        node.setChildren(findChildren(wb.getId(), wbs));
                        root.add(node);
                    }
                }
            }
            return root;
        }
        return null;
    }
}
