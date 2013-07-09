/*
 * cn.wanto.busi.serv.impl.TopicService.java
 * Sep 10, 2012 
 */
package cn.wanto.busi.serv.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.nutz.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.touchin.db.hibernate.IHibernateDao;
import cn.touchin.db.jdbc.PageQuery;
import cn.touchin.db.jdbc.SQLRunner;
import cn.touchin.page.Pagination;
import cn.wanto.busi.db.ITopicDao;
import cn.wanto.busi.serv.ITopicService;
import cn.wanto.busi.tools.ImagePath;
import cn.wanto.concurrent.ThreadPool;
import cn.wanto.dto.Ar;
import cn.wanto.dto.Composition;
import cn.wanto.dto.bean.ProductsDto;
import cn.wanto.dto.bean.ShopIndexViewDto;
import cn.wanto.dto.bean.TagDto;
import cn.wanto.dto.bean.TopicDto;
import cn.wanto.dto.bean.TopicView;
import cn.wanto.dto.bean.WordbookDto;
import cn.wanto.entity.Topic;
import cn.wanto.entity.TopicExtend;
import cn.wanto.entity.TopicVote;
import cn.wanto.event.TopicProductCreateTask;
import cn.wanto.event.TopicProductEditTask;
import cn.wanto.event.TopicShopCreateTask;
import cn.wanto.event.TopicShopEditTask;
import cn.wanto.event.TopicViewTask;
import cn.wanto.event.TopicVoteTask;
import cn.wanto.util.AppConstants;
import cn.wanto.util.enume.WordbookType;

/**
 * Sep 10, 2012
 * 
 * @author <a href="mailto:touchnan@gmail.com">chegnqiang.han</a>
 * 
 */
@Component
@Transactional(readOnly = true)
public class TopicService extends AppService<TopicDto, Topic, Long> implements ITopicService {

    @Autowired
    private ITopicDao topicDao;

    /*
     * (non-Javadoc)
     * 
     * @see cn.touchin.serv.BaseService#dao()
     */
    @Override
    public IHibernateDao<Topic, Long> dao() {
        return topicDao;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#saveTopic(cn.wanto.dto.Ar, cn.wanto.dto.bean.TopicDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TopicDto saveTopic(Ar ar, TopicDto topicDto) {
        topicDto.prepare();// 类型、地区转换
        Date now = new Date();
        if (topicDto.getId() == null || topicDto.getId() <= 0) {
            if (Strings.isBlank(topicDto.getIcon())) {
                throw new RuntimeException("请上传店铺图片");
            }

            ImagePath imagePath = AppConstants.copyTempFile2StorePath(topicDto.getIcon(), "原上传的店铺图片已失效,请重新上传店铺图片");

            ImagePath thumbnail =
                    AppConstants.copyTempFile2StorePath(AppConstants.thumbnailName(topicDto.getIcon()),
                            "原上传的店铺图片已失效,请重新上传店铺图片");

            Topic topic = new Topic();
            topic.setFirstPostId(ar.getLoginId());
            topic.setLastPostId(ar.getLoginId());
            topic.setIcon(imagePath.getRelativeFilename()); // 图片目录
            topic.setThumbnail(thumbnail.getRelativeFilename());// 缩略图
            topic.setPostIp(ar.getIp());
            topic.setTime(now);
            topic.setEtime(now);
            topic.setTitle(topicDto.getTitle());// 主题
            topic.setType(AppConstants.TOPIC_SHOP_MASTER);// 商店
            dao().save(ar, topic);
            topicDto.setIcon(topic.getIcon());
            topicDto.setId(topic.getId());

            // 扩展信息不能异步，图片视图的时候就已经去查询
            TopicExtend te = new TopicExtend();
            te.setTopicId(topicDto.getId());
            te.setAddr(topicDto.getAddr());
            te.setKind(topicDto.getKind());
            te.setPresenter(ar.getLoginId());
            te.setRegion(topicDto.getRegion());
            te.setVip(false);
            te.setMagazineQuota(topicDto.getMagazineQuota());
            te.setText(topicDto.getText());
            te.setVipQuota(topicDto.getVipQuota());
            te.setIdentity(genIdentity());
            dao().save(ar, te);

            ThreadPool.execute(new TopicShopCreateTask(ar, topic, topicDto));
        } else {
            Topic topic = dao().fetch(ar, Topic.class, topicDto.getId());
            topic.setTitle(topicDto.getTitle());
            topic.setEtime(now);

            TopicExtend topicExtend = dao().fetch(ar, TopicExtend.class, topicDto.getId());
            topicExtend.setAddr(topicDto.getAddr());
            topicExtend.setText(topicDto.getText());

            topicExtend.setSaid(topicDto.getSaid());
            topicExtend.setPhone(topicDto.getPhone());
            if (!topicExtend.isVip() && !Strings.isBlank(topicDto.getMagazineQuota())) {
                topicExtend.setMagazineQuota(topicDto.getMagazineQuota());
            }

            topicExtend.prepare();// 类型、地区转换
            long oldKind = topicExtend.getKind2();
            topicExtend.setKind(topicDto.getKind());

            long oldRegion = topicExtend.getRegion4();
            topicExtend.setRegion(topicDto.getRegion());
            dao().save(ar, topic);
            dao().save(ar, topicExtend);

            ThreadPool.execute(new TopicShopEditTask(ar, oldKind, oldRegion, topicDto));
        }
        return topicDto;
    }

    private long genIdentity() {
        while (true) {
            StringBuffer buf = new StringBuffer(AppConstants.LEN_SHOP_IDENTITY);
            for (int i = 0; i < AppConstants.LEN_SHOP_IDENTITY; i++) {
                int temp = (AppConstants.rn.nextInt(10240) >>> 10);
                while (i == 0 && temp == 0) {
                    temp = (AppConstants.rn.nextInt(10240) >>> 10);
                }
                buf.append(temp);
            }
            long identity = Long.valueOf(buf.toString());
            if ((Long) db.findBy("SELECT count(topic_identity) FROM t_topic_extend WHERE topic_identity=? ", 1,
                    identity) <= 0) {
                return identity;
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#saveTopicAvatar(cn.wanto.dto.Ar, cn.wanto.dto.bean.TopicDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public TopicDto saveTopicAvatar(Ar ar, TopicDto topicDto) {
        if (Strings.isBlank(topicDto.getIcon())) {
            throw new RuntimeException("请上传店铺图片");
        }
        ImagePath imagePath = AppConstants.copyTempFile2StorePath(topicDto.getIcon(), "原上传的店铺图片已失效,请重新上传店铺图片");
        ImagePath thumbnail =
                AppConstants.copyTempFile2StorePath(AppConstants.thumbnailName(topicDto.getIcon()),
                        "原上传的店铺图片已失效,请重新上传店铺图片");

        // 图片目录、缩略图、最后修改时间
        db.update("UPDATE t_topic SET topic_icon=?, topic_thumb=?, topic_edit_time=? WHERE topic_id=? ",
                imagePath.getRelativeFilename(), thumbnail.getRelativeFilename(), new Date(), topicDto.getId());
        return topicDto;
    }

    private List<TopicDto> findHottestShops() {
        return db
                .find(TopicDto.class,
                        "SELECT topic_id AS id, topic_thumb AS thumbnail, topic_views AS views, topic_enjoyments AS enjoyments, topic_title AS title FROM t_topic WHERE topic_type=? AND topic_status<>? ORDER BY topic_views DESC, topic_time DESC limit ?",
                        new Object[] { AppConstants.TOPIC_SHOP_MASTER, AppConstants.DEL_TAG,
                                AppConstants.TOPIC_SHOPS_MAIN_SHOW_COUNT });
    }

    private List<TopicDto> findNewestShops() {
        return db
                .find(TopicDto.class,
                        "SELECT topic_id AS id, topic_thumb AS thumbnail, topic_views AS views, topic_enjoyments AS enjoyments, topic_title AS title FROM t_topic WHERE topic_type=? AND topic_status<>? ORDER BY topic_time DESC limit ?",
                        new Object[] { AppConstants.TOPIC_SHOP_MASTER, AppConstants.DEL_TAG,
                                AppConstants.TOPIC_SHOPS_MAIN_SHOW_COUNT });
    }

    private List<TopicDto> findBestProducts() {
        return db
                .find(TopicDto.class,
                        "SELECT topic_id AS id, topic_thumb AS thumbnail, topic_views AS views, topic_enjoyments AS enjoyments, topic_title AS title FROM t_topic WHERE topic_type=? AND topic_status<>? ORDER BY topic_enjoyments DESC, topic_time DESC limit ?",
                        new Object[] { AppConstants.TOPIC_SHOP_MASTER_PIC, AppConstants.DEL_TAG,
                                AppConstants.TOPIC_SHOPS_MAIN_SHOW_COUNT });
    }

    private List<WordbookDto> findHottestWordbookDtos(int kind) {
        return db
                .find(WordbookDto.class,
                        "SELECT id, w_name AS name, hot AS hot FROM t_wordbook WHERE w_parent_id>0 AND hot>0 AND w_kind=? ORDER BY hot DESC LIMIT ?",
                        new Object[] { kind, AppConstants.TOPIC_SHOPS_TAGS_HOT_COUNT });
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findShopIndexView()
     */
    @Override
    public ShopIndexViewDto findShopIndexView() {
        ShopIndexViewDto view = new ShopIndexViewDto();
        view.setHottestShops(findHottestShops());
        view.setNewestShops(findNewestShops());
        view.setBestShops(findBestProducts());

        view.setHotTags(findHottestWordbookDtos(WordbookType.SHOP.ordinal()));
        view.setHotAddrs(findHottestWordbookDtos(WordbookType.REGION.ordinal()));
        return view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findTopicByUserId(cn.touchin.page.Pagination, java.lang.Long, int)
     */
    @Override
    public Pagination findShopByUserId(Pagination page, Long userId) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" t.topic_id AS id ,t.topic_title as title, t.topic_time as time,te.topic_addr as addr  ")
                                .from(" t_topic t,t_topic_extend te ")
                                .where(" t.topic_id =te.topic_id and t.topic_type=? and te.topic_presenter = ?  AND t.topic_status<>?  ORDER BY t.topic_time DESC ")
                                .setParameters(AppConstants.TOPIC_SHOP_MASTER, userId, AppConstants.DEL_TAG));

        page.setRows(db.find(TopicDto.class, pq.getPageSql(), pq.getPageParams()));
        page.setTotal(findShopCountByUserId(userId).intValue());
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#searchShopPage(cn.wanto.dto.Ar, cn.touchin.page.Pagination)
     */
    @Override
    public Pagination searchShopPage(Ar ar, Pagination page) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" tp.topic_id AS id, tp.topic_title AS title,tp.topic_thumb AS thumbnail, tp.topic_views AS views, tp.topic_enjoyments AS enjoyments,"
                                        + " tp.topic_boredoms AS boredoms, tpe.topic_presenter AS presenter, u.nickname AS presenterName, "
                                        + " tpe.topic_quota AS  magazineQuota, tpe.is_vip AS vip, tpe.topic_region AS region, tpe.topic_kind AS kind")
                                .from(" t_topic tp, t_topic_extend tpe, t_user u ")
                                .where(" tp.topic_id=tpe.topic_id AND tpe.topic_presenter=u.user_id AND tp.topic_type=? AND tp.topic_status<>?")
                                .orderBy(" tp.topic_time DESC")
                                .setParameters(AppConstants.TOPIC_SHOP_MASTER, AppConstants.DEL_TAG));

        List<TopicDto> list = db.find(TopicDto.class, pq.getPageSql(), pq.getPageParams());
        // 需要标签说明
        if (list != null && !list.isEmpty()) {
            for (TopicDto topic : list) {
                topic.prepare();
                topic.setKind1name(findWordbookNameById(topic.getKind1()));
                topic.setKind2name(findWordbookNameById(topic.getKind2()));

                topic.setRegion1name(findWordbookNameById(topic.getRegion1()));
                topic.setRegion2name(findWordbookNameById(topic.getRegion2()));
                topic.setRegion3name(findWordbookNameById(topic.getRegion3()));
                topic.setRegion4name(findWordbookNameById(topic.getRegion4()));
            }
        }
        page.setRows(list);
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#searchShopCount(cn.wanto.dto.Ar, cn.touchin.page.Pagination)
     */
    @Override
    public Integer searchShopCount(Ar ar, Pagination page) {
        PageQuery pq =
                sqlParser.createPageQuery(new SQLRunner(page).select(" tp.topic_id AS id ")
                        .from(" t_topic tp,t_topic_extend tpe ")
                        .where(" tp.topic_id =tpe.topic_id and tp.topic_type=? AND tp.topic_status<>?")
                        .orderBy(" tp.topic_time DESC ")
                        .setParameters(AppConstants.TOPIC_SHOP_MASTER, AppConstants.DEL_TAG));
        return ((Long) db.findBy(pq.getCountSql(), 1, pq.getCountParams())).intValue();
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findTopicCountByUserId(java.lang.Long, java.lang.Integer)
     */
    @Override
    public Long findShopCountByUserId(Long userId) {
        List<TopicDto> count =
                db.find(TopicDto.class,
                        " SELECT count(t.topic_id) AS id FROM t_topic t,t_topic_extend te WHERE t.topic_id =te.topic_id and t.topic_type=? and te.topic_presenter = ? AND t.topic_status<>? ORDER BY t.topic_time DESC",
                        new Object[] { AppConstants.TOPIC_SHOP_MASTER, userId, AppConstants.DEL_TAG });
        if (count == null || count.size() == 0) {
            return 0L;
        }
        return Long.valueOf(count.get(0).getId().toString());
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#topicView(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public TopicView topicView(Ar ar, Long topicId) {
        String sql =
                "SELECT tp.topic_id AS id, tp.topic_title AS title,tp.topic_icon AS icon, tp.topic_views AS views, tp.topic_enjoyments AS enjoyments, "
                        + " tp.topic_boredoms AS boredoms, tpe.topic_presenter AS presenter, u.nickname AS presenterName, tpe.keeper_said AS said,"
                        + " tpe.topic_quota AS  magazineQuota, tpe.is_vip AS vip,tpe.topic_kind AS kind, tpe.topic_region AS region, tpe.topic_addr AS addr, tpe.topic_text AS text,tpe.topic_phone AS phone "
                        + " FROM t_topic tp, t_topic_extend tpe, t_user u "
                        + " WHERE tp.topic_id=tpe.topic_id AND tpe.topic_presenter=u.user_id AND tp.topic_type=? AND tp.topic_status<>? AND tp.topic_id=? ";
        TopicView topic =
                db.findFirst(TopicView.class, sql, new Object[] { AppConstants.TOPIC_SHOP_MASTER, AppConstants.DEL_TAG,
                        topicId });

        // 产品列表
        String productsql =
                "SELECT tp.topic_id AS id,tp.topic_thumb AS thumbnail, tp.topic_title AS title,tpe.topic_text AS text, tpe.topic_price AS price,tp.topic_enjoyments AS enjoyments"
                        + " FROM t_topic tp, t_topic_extend tpe WHERE tp.topic_id=tpe.topic_id AND tp.topic_type=? AND topic_parent_id=? AND tp.topic_status=? ORDER BY tpe.orderid DESC, tp.topic_edit_time DESC";
        List<TopicView> topicProducts =
                db.find(TopicView.class, productsql, AppConstants.TOPIC_SHOP_MASTER_PIC, topicId,
                        AppConstants.SHELFCARRIAGE_TAG);
        topic.setTopicProducts(topicProducts);

        if (ar != null && ar.getLoginId() != null && topic != null) {// 是否店长
            topic.setKeeper(ar.getLoginId() == topic.getPresenter());
        }

        // 店铺标签
        List<TagDto> tags = new ArrayList<TagDto>(6);
        topic.prepare();
        if (topic.getKind1() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getKind1(), TagDto.schKid));

        }
        if (topic.getKind2() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getKind2(), TagDto.schKid));
        }
        if (topic.getRegion1() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getRegion1(), TagDto.schRid));
        }
        if (topic.getRegion2() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getRegion2(), TagDto.schRid));
        }
        if (topic.getRegion3() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getRegion3(), TagDto.schRid));
        }
        if (topic.getRegion4() > 0) {
            tags.add(findTagsOnWordbookDtoById(topic.getRegion4(), TagDto.schRid));
        }
        topic.setTags(tags);

        if (!topic.isKeeper()) {
            // 如果不是店长，看过数自动加一
            topic.setViews(topic.getViews() + 1);
            ThreadPool.execute(new TopicViewTask(topicId));
        }
        return topic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#productView(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public TopicView productView(Ar ar, Long productId) {
        String sql =
                "SELECT tp.topic_id AS id, tp.topic_parent_id AS parentId, tp.topic_title AS title,tp.topic_icon AS icon, tp.topic_edit_time AS time, tp.topic_views AS views, tp.topic_enjoyments AS enjoyments, "
                        + " tp.topic_boredoms AS boredoms, tpe.topic_presenter AS presenter,"
                        + " tpe.is_vip AS vip, tpe.topic_text AS text, tpe.topic_price AS price,tp.topic_edit_time AS etime "
                        + " FROM t_topic tp, t_topic_extend tpe  "
                        + " WHERE tp.topic_id=tpe.topic_id  AND tp.topic_type=? AND tp.topic_status<>? AND tp.topic_id=? ";
        TopicView topic =
                db.findFirst(TopicView.class, sql, new Object[] { AppConstants.TOPIC_SHOP_MASTER_PIC,
                        AppConstants.DEL_TAG, productId });

        // 本店其它最热产品
        if (topic.getParentId() > 0) {
            String productsql =
                    "SELECT tp.topic_id AS id,tp.topic_thumb AS thumbnail, tp.topic_title AS title,tpe.topic_text AS text "
                            + " FROM t_topic tp, t_topic_extend tpe WHERE tp.topic_id=tpe.topic_id AND tp.topic_id<>? AND tp.topic_type=? AND topic_parent_id=? AND tp.topic_status=? ORDER BY tp.topic_enjoyments DESC, tp.topic_edit_time DESC limit ?";
            List<TopicView> topicProducts =
                    db.find(TopicView.class, productsql, topic.getId(), AppConstants.TOPIC_SHOP_MASTER_PIC,
                            topic.getParentId(), AppConstants.SHELFCARRIAGE_TAG,
                            AppConstants.TOPIC_SHOPS_PRODUCT_SHOW_HOT_COUNT);
            topic.setTopicProducts(topicProducts);
        }

        if (topic.getPresenter() > 0) {
            topic.setOwner(fetchUser(topic.getPresenter()));
        }

        if (ar != null && ar.getLoginId() != null && topic != null) {// 是否店长
            topic.setKeeper(ar.getLoginId() == topic.getPresenter());
        }

        if (!topic.isKeeper()) {
            // 如果不是店长，看过数自动加一
            topic.setViews(topic.getViews() + 1);
            ThreadPool.execute(new TopicViewTask(productId));
        }
        return topic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findShopProductsPage(cn.wanto.dto.Ar, java.lang.Long,
     * cn.touchin.page.Pagination)
     */
    @Override
    public Pagination findShopProductsPage(Ar ar, Long shopId, Pagination page) {
        PageQuery pq =
                sqlParser
                        .createPageQuery(new SQLRunner(page)
                                .select(" tp.topic_id AS id,tp.topic_thumb AS thumbnail, tp.topic_time AS time, tp.topic_views AS views,tp.topic_title AS title, tp.topic_status AS status,"
                                        + " tpe.topic_text AS text, tpe.topic_price AS price,tp.topic_enjoyments AS enjoyments,tp.topic_boredoms AS boredoms, tp.topic_edit_time AS etime")
                                .from(" t_topic tp, t_topic_extend tpe ")
                                .where(" tp.topic_id=tpe.topic_id AND tp.topic_type=? AND topic_parent_id=? AND tp.topic_status<>? ")
                                .orderBy(" tp.topic_edit_time DESC ")
                                .setParameters(AppConstants.TOPIC_SHOP_MASTER_PIC, shopId, AppConstants.DEL_TAG));

        page.setRows(db.find(TopicDto.class, pq.getPageSql(), pq.getPageParams()));
        // page.setTotal(Integer.valueOf(db.findBy(pq.getCountSql(),1, pq.getCountParams()).toString()));
        return page;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#findShopProductCount(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public Long findShopProductCount(Ar ar, Long shopId, int status) {
        switch (status) {
        case AppConstants.ALL_VALIDATE_TAG:
            return (Long) db.findBy(
                    "SELECT count(topic_id) AS num FROM t_topic WHERE topic_type=? AND topic_parent_id=?", 1,
                    AppConstants.TOPIC_SHOP_MASTER_PIC, shopId);
        default:
            return (Long) db
                    .findBy("SELECT count(topic_id) AS num FROM t_topic WHERE topic_type=? AND topic_parent_id=? AND topic_status=?",
                            1, AppConstants.TOPIC_SHOP_MASTER_PIC, shopId, status);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#fetchTopic4Edit(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public TopicDto fetchTopic4Edit(Ar ar, Long topicId) {
        String sql =
                "SELECT tp.topic_id AS id, tp.topic_title AS title, tp.topic_icon AS icon,tp.topic_time AS time, tp.topic_views AS views, tp.topic_enjoyments AS enjoyments, "
                        + " tp.topic_boredoms AS boredoms, tpe.topic_presenter AS presenter, tpe.topic_identity AS identity, tpe.keeper_said AS said,"
                        + " tpe.topic_quota AS  magazineQuota, tpe.is_vip AS vip, tpe.topic_kind AS kind, tpe.topic_region AS region, tpe.keeper_said AS said, tpe.topic_addr AS addr, tpe.topic_text AS text, tpe.topic_phone AS phone, u.nickname AS presenterName"
                        + " FROM t_topic tp, t_topic_extend tpe, t_user u"
                        + " WHERE tp.topic_id=tpe.topic_id AND tpe.topic_presenter=u.user_id AND u.user_id=? AND tp.topic_type=? AND tp.topic_status<>? AND tp.topic_id=? ";
        TopicDto topic =
                db.findFirst(TopicDto.class, sql, new Object[] { ar.getLoginId(), AppConstants.TOPIC_SHOP_MASTER,
                        AppConstants.DEL_TAG, topicId });
        if (topic != null) {
            topic.prepare();
        }
        return topic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#fetchTopic4Avatar(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public TopicDto fetchTopic4Avatar(Ar ar, Long topicId) {
        String sql =
                "SELECT tp.topic_id AS id, tp.topic_icon AS icon, tp.topic_thumb AS thumbnail FROM t_topic tp WHERE tp.topic_id=? ";
        TopicDto topic = db.findFirst(TopicDto.class, sql, topicId);
        return topic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#fetchTopicSimpleInfo4ProductManage(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    public TopicDto fetchTopicSimpleInfo4Manage(Ar ar, Long topicId) {
        String sql =
                "SELECT tp.topic_id AS id, tp.topic_title AS title, tp.topic_time AS time, tp.topic_views AS views, tp.topic_enjoyments AS enjoyments, "
                        + " tp.topic_boredoms AS boredoms, tpe.topic_presenter AS presenter, tpe.topic_identity AS identity, tpe.keeper_said AS said,"
                        + " tpe.topic_quota AS  magazineQuota, tpe.is_vip AS vip"
                        + " FROM t_topic tp, t_topic_extend tpe, t_user u"
                        + " WHERE tp.topic_id=tpe.topic_id AND tpe.topic_presenter=u.user_id AND u.user_id=? AND tp.topic_type=? AND tp.topic_status<>? AND tp.topic_id=? ";
        TopicDto topic =
                db.findFirst(TopicDto.class, sql, new Object[] { ar.getLoginId(), AppConstants.TOPIC_SHOP_MASTER,
                        AppConstants.DEL_TAG, topicId });
        return topic;
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#enjoyTopc(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void enjoyTopc(Ar ar, Long topicId) {
        voteTopc(ar, topicId, AppConstants.VOTE_ENJOY);
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#boredTopic(cn.wanto.dto.Ar, java.lang.Long)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void boredTopic(Ar ar, Long topicId) {
        voteTopc(ar, topicId, AppConstants.VOTE_BORED);
    }

    /**
     * 投票
     * 
     * @param ar
     * @param topicId
     * @param voteType
     */
    private void voteTopc(Ar ar, Long topicId, int voteType) {
        if (ar != null && ar.getLoginId() != null && topicId != null) {
            Composition id = new Composition(topicId, ar.getLoginId());
            TopicVote vote = dao().getHibernateTemplate().get(TopicVote.class, id);
            if (vote == null) {
                vote = new TopicVote();
                vote.setId(id);
                vote.setVote(voteType);
                dao().save(ar, vote);
                ThreadPool.execute(new TopicVoteTask(vote));
            } else {
                throw new RuntimeException("您已经投过票了!");
            }
        } else {
            throw new RuntimeException("请选择具体对象投票!");
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#updateProductBaseinfo(cn.wanto.dto.Ar, cn.wanto.dto.bean.TopicDto)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateProductBaseinfo(Ar ar, TopicDto topicDto) {
        if (!Strings.isBlank(topicDto.getTitle())) {
            Topic topic = dao().fetch(ar, Topic.class, topicDto.getId());
            topic.setTitle(topicDto.getTitle());
            topic.setEtime(new Date());
            dao().save(ar, topic);
            ThreadPool.execute(new TopicProductEditTask(ar, topicDto));
        } else if (topicDto.getText() != null) {
            //可为空
            Topic topic = dao().fetch(ar, Topic.class, topicDto.getId());
            topic.setEtime(new Date());
            TopicExtend topicExtend = dao().fetch(ar, TopicExtend.class, topicDto.getId());
            topicExtend.setText(topicDto.getText());
            dao().save(ar, topic);
            dao().save(ar, topicExtend);
            ThreadPool.execute(new TopicProductEditTask(ar, topicDto));
        } else if (topicDto.getPricedecimal() != null) {
            //可为空
            Topic topic = dao().fetch(ar, Topic.class, topicDto.getId());
            topic.setEtime(new Date());

            topicDto.setPrice(AppConstants.parseYuanMoney(topicDto.getPricedecimal()));
            TopicExtend topicExtend = dao().fetch(ar, TopicExtend.class, topicDto.getId());
            topicExtend.setPrice(topicDto.getPrice());
            dao().save(ar, topic);
            dao().save(ar, topicExtend);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#saveProducts(cn.wanto.dto.Ar, java.util.List)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveProducts(Ar ar, Long shopId, ProductsDto products) {
        if (products.isNotBlank()) {
            if (!(products.getIcons().size() == products.getTexts().size()
                    && products.getIcons().size() == products.getTitles().size()
                    && products.getIcons().size() == products.getNames().size() && products.getPrices().size() == products
                    .getPrices().size())) {
                throw new RuntimeException("图片信息数不匹配，请重新上传");
            }
            Date now = new Date();
            for (int i = 0; i < products.getIcons().size(); i++) {
                String icon = products.getIcons().get(i);
                String title = products.getTitles().get(i);
                String text = products.getTexts().get(i);
                String picName = products.getNames().get(i);
                long price = AppConstants.parseYuanMoney(products.getPrices().get(i));

                if (Strings.isBlank(icon)) {
                    throw new RuntimeException("请上传图片");
                }

                ImagePath imagePath =
                        AppConstants.copyTempFile2StorePath(icon, String.format("原上传的图片已失效,请重新上传图片[%s]", picName));
                ImagePath thumbnail =
                        AppConstants.copyTempFile2StorePath(AppConstants.thumbnailName(icon),
                                String.format("原上传的图片已失效,请重新上传图片[%s]", picName));

                Topic topic = new Topic();
                topic.setFirstPostId(ar.getLoginId());
                topic.setLastPostId(ar.getLoginId());
                topic.setIcon(imagePath.getRelativeFilename()); // 图片目录
                topic.setThumbnail(thumbnail.getRelativeFilename());// 缩略图
                topic.setParentId(shopId);
                topic.setPostIp(ar.getIp());
                topic.setTime(now);
                topic.setEtime(now);
                topic.setTitle(title);// 主题
                topic.setType(AppConstants.TOPIC_SHOP_MASTER_PIC);// 产品

                // tp.setUserId(ar.getLoginId());
                dao().save(ar, topic);

                // 扩展信息不能异步，图片视图的时候就已经去查询
                TopicExtend te = new TopicExtend();
                te.setTopicId(topic.getId());
                te.setPresenter(ar.getLoginId());
                te.setText(text);
                te.setPrice(price);
                dao().save(ar, te);
                ThreadPool.execute(new TopicProductCreateTask(ar, topic, title, text));
            }
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#updateStatus(cn.wanto.dto.Ar, java.lang.Long, int)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateStatus(Ar ar, Long topicId, int status) {
        if (ar.isAdmin()) {
            return db.update("UPDATE t_topic SET topic_status=? WHERE topic_id=?", status, topicId);
        } else {
            return db
                    .update("UPDATE t_topic tp, t_topic_extend tpe SET tp.topic_status=? WHERE tp.topic_id=? AND tp.topic_id=tpe.topic_id AND tpe.topic_presenter=?",
                            status, topicId, ar.getLoginId());
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see cn.wanto.busi.serv.ITopicService#updateStatus(cn.wanto.dto.Ar, java.lang.Long[], int)
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public int updateStatus(Ar ar, Long[] topicIds, int status) {
        if (ar.isAdmin()) {
            return dao().currSession().createSQLQuery("UPDATE t_topic SET topic_status=? WHERE topic_id in (:ids)")
                    .setParameter(0, status).setParameterList("ids", topicIds).executeUpdate();
        } else {
            return dao()
                    .currSession()
                    .createSQLQuery(
                            "UPDATE t_topic tp, t_topic_extend tpe SET tp.topic_status=? WHERE tp.topic_id=tpe.topic_id AND tpe.topic_presenter=? AND tp.topic_id in (:ids) ")
                    .setParameter(0, status).setParameter(1, ar.getLoginId()).setParameterList("ids", topicIds)
                    .executeUpdate();
        }
    }

}
