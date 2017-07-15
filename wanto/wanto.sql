-- MySQL dump 10.13  Distrib 5.1.45, for Win32 (ia32)
--
-- Host: localhost    Database: wanto
-- ------------------------------------------------------
-- Server version	5.1.45-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_group`
--

DROP TABLE IF EXISTS `t_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_group` (
  `group_id` int(11) NOT NULL AUTO_INCREMENT,
  `group_description` varchar(255) DEFAULT NULL,
  `group_name` varchar(40) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统用户组';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_group`
--

LOCK TABLES `t_group` WRITE;
/*!40000 ALTER TABLE `t_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_keeper_said`
--

DROP TABLE IF EXISTS `t_keeper_said`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_keeper_said` (
  `said_id` int(11) NOT NULL AUTO_INCREMENT,
  `keeper_said` varchar(100) DEFAULT NULL,
  `topic_id` bigint(20) NOT NULL DEFAULT '0',
  `keeper_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`said_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店长有话说';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_keeper_said`
--

LOCK TABLES `t_keeper_said` WRITE;
/*!40000 ALTER TABLE `t_keeper_said` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_keeper_said` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_message`
--

DROP TABLE IF EXISTS `t_message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_message` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_createtime` datetime DEFAULT NULL,
  `m_kind` int(11) DEFAULT NULL,
  `m_opinion_state` int(11) DEFAULT NULL,
  `m_post_id` bigint(20) DEFAULT NULL,
  `m_reading` bit(1) DEFAULT NULL,
  `m_reason` varchar(255) DEFAULT NULL,
  `m_shop_id` bigint(20) DEFAULT NULL,
  `m_status` int(11) DEFAULT NULL,
  `m_topic_id` bigint(20) DEFAULT NULL,
  `m_type` int(11) DEFAULT NULL,
  `m_user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES `t_message` WRITE;
/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_post`
--

DROP TABLE IF EXISTS `t_post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_post` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `post_click_count` bigint(20) NOT NULL DEFAULT '0',
  `post_edit_count` bigint(20) NOT NULL DEFAULT '0',
  `post_edit_time` datetime DEFAULT NULL,
  `post_floors` bigint(20) NOT NULL DEFAULT '0',
  `post_last_reply_time` datetime DEFAULT NULL,
  `post_lvl` bigint(20) NOT NULL DEFAULT '0',
  `top` bigint(20) NOT NULL DEFAULT '0',
  `post_ip` varchar(15) DEFAULT NULL,
  `post_reply_count` bigint(20) NOT NULL DEFAULT '0',
  `post_status` int(11) DEFAULT NULL,
  `post_time` datetime DEFAULT NULL,
  `topic_id` bigint(20) NOT NULL DEFAULT '0',
  `topic_topid` bigint(20) NOT NULL DEFAULT '0',
  `post_type` int(11) DEFAULT NULL,
  `post_unuseful_count` bigint(20) NOT NULL DEFAULT '0',
  `post_useful_count` bigint(20) NOT NULL DEFAULT '0',
  `user_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='发贴';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_post_text`
--

DROP TABLE IF EXISTS `t_post_text`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_post_text` (
  `post_id` int(11) NOT NULL AUTO_INCREMENT,
  `bookMark` varchar(100) DEFAULT NULL,
  `post_subject` varchar(100) DEFAULT NULL,
  `post_text` text,
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='贴子内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post_text`
--

LOCK TABLES `t_post_text` WRITE;
/*!40000 ALTER TABLE `t_post_text` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_post_text` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic`
--

DROP TABLE IF EXISTS `t_topic`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_topic` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_boredoms` bigint(20) NOT NULL DEFAULT '0',
  `topic_enjoyments` bigint(20) NOT NULL DEFAULT '0',
  `topic_edit_time` datetime DEFAULT NULL,
  `topic_first_post_id` bigint(20) NOT NULL DEFAULT '0',
  `topic_floors` bigint(20) NOT NULL DEFAULT '0',
  `topic_icon` varchar(100) DEFAULT NULL,
  `topic_last_post_id` bigint(20) NOT NULL DEFAULT '0',
  `topic_last_reply_time` datetime DEFAULT NULL,
  `topic_parent_id` bigint(20) NOT NULL DEFAULT '0',
  `post_ip` varchar(15) DEFAULT NULL,
  `topic_replies` bigint(20) NOT NULL DEFAULT '0',
  `topic_status` int(11) DEFAULT NULL,
  `topic_thumb1` varchar(100) DEFAULT NULL,
  `topic_thumb2` varchar(100) DEFAULT NULL,
  `topic_thumb3` varchar(100) DEFAULT NULL,
  `topic_thumb` varchar(100) DEFAULT NULL,
  `topic_time` datetime DEFAULT NULL,
  `topic_title` varchar(100) DEFAULT NULL,
  `topic_type` int(11) DEFAULT NULL,
  `topic_views` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic`
--

LOCK TABLES `t_topic` WRITE;
/*!40000 ALTER TABLE `t_topic` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_topic` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic_extend`
--

DROP TABLE IF EXISTS `t_topic_extend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_topic_extend` (
  `topic_id` int(11) NOT NULL AUTO_INCREMENT,
  `topic_addr` varchar(150) DEFAULT NULL,
  `topic_identity` bigint(20) NOT NULL DEFAULT '0',
  `topic_kind` varchar(50) DEFAULT NULL,
  `topic_quota` varchar(30) DEFAULT NULL,
  `orderid` int(11) NOT NULL DEFAULT '0',
  `topic_phone` varchar(30) DEFAULT NULL,
  `topic_presenter` bigint(20) NOT NULL DEFAULT '0',
  `topic_price` bigint(20) NOT NULL DEFAULT '0',
  `topic_region` varchar(100) DEFAULT NULL,
  `keeper_said` varchar(150) DEFAULT NULL,
  `topic_text` varchar(150) DEFAULT NULL,
  `is_vip` bit(1) DEFAULT NULL,
  `vip_quota` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`topic_id`),
  UNIQUE KEY `topic_identity` (`topic_identity`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题扩展-店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic_extend`
--

LOCK TABLES `t_topic_extend` WRITE;
/*!40000 ALTER TABLE `t_topic_extend` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_topic_extend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic_vote`
--

DROP TABLE IF EXISTS `t_topic_vote`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_topic_vote` (
  `targetId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `vote` int(11) DEFAULT NULL,
  PRIMARY KEY (`targetId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic_vote`
--

LOCK TABLES `t_topic_vote` WRITE;
/*!40000 ALTER TABLE `t_topic_vote` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_topic_vote` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_topic_watch`
--

DROP TABLE IF EXISTS `t_topic_watch`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_topic_watch` (
  `targetId` bigint(20) NOT NULL,
  `userId` bigint(20) NOT NULL,
  `is_read` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`targetId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题查看明细';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic_watch`
--

LOCK TABLES `t_topic_watch` WRITE;
/*!40000 ALTER TABLE `t_topic_watch` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_topic_watch` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_atuh_url` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(100) DEFAULT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  `is_hidden` bit(1) DEFAULT NULL,
  `user_hometown` varchar(100) DEFAULT NULL,
  `user_hometown_id` bigint(20) NOT NULL DEFAULT '0',
  `user_hometown_private` tinyint(1) DEFAULT '1',
  `user_label` varchar(255) DEFAULT NULL,
  `user_lastvisit` datetime DEFAULT NULL,
  `user_level` int(11) DEFAULT NULL,
  `user_mood` varchar(255) DEFAULT NULL,
  `nickname` varchar(100) DEFAULT NULL,
  `user_password` varchar(32) DEFAULT NULL,
  `user_post_deletes` bigint(20) NOT NULL DEFAULT '0',
  `user_posts` bigint(20) NOT NULL DEFAULT '0',
  `user_regtime` datetime DEFAULT NULL,
  `user_school` varchar(100) DEFAULT NULL,
  `user_school_id` bigint(20) NOT NULL DEFAULT '0',
  `user_school_private` tinyint(1) DEFAULT '1',
  `is_searched` tinyint(1) DEFAULT '1',
  `user_thumb` varchar(100) DEFAULT NULL,
  `c_type` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,NULL,NULL,0,'',NULL,0,0,NULL,NULL,0,NULL,'韩成强','e10adc3949ba59abbe56e057f20f883e',0,0,NULL,NULL,0,0,0,NULL,-1,'hancq@qq.com'),(2,NULL,NULL,0,'',NULL,0,0,NULL,NULL,0,NULL,'周祝燕','e10adc3949ba59abbe56e057f20f883e',0,0,NULL,NULL,0,0,0,NULL,-1,'zhouzy@qq.com');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_wordbook`
--

DROP TABLE IF EXISTS `t_wordbook`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_wordbook` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `is_hidden` tinyint(1) DEFAULT '1',
  `hot` bigint(20) DEFAULT NULL,
  `w_kind` int(11) DEFAULT NULL,
  `w_name` varchar(100) DEFAULT NULL,
  `orderid` int(11) NOT NULL DEFAULT '0',
  `w_parent_id` bigint(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=80 DEFAULT CHARSET=utf8 COMMENT='字数字典、类型、地区等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wordbook`
--

LOCK TABLES `t_wordbook` WRITE;
/*!40000 ALTER TABLE `t_wordbook` DISABLE KEYS */;
INSERT INTO `t_wordbook` VALUES (1,0,0,1,'美食',0,0),(2,0,0,1,'地方菜',0,1),(3,0,0,1,'特色小吃',0,1),(4,0,0,1,'火锅',0,1),(5,0,0,1,'烧烤',0,1),(6,0,0,1,'中式快餐',0,1),(7,0,0,1,'西餐',0,1),(8,0,0,1,'日韩料理',0,1),(9,0,0,1,'西式快餐',0,1),(10,0,0,1,'蛋糕甜点',0,1),(11,0,0,1,'其他',0,1),(12,0,0,1,'购物',0,0),(13,0,0,1,'服饰',0,12),(14,0,0,1,'数码',0,12),(15,0,0,1,'超市',0,12),(16,0,0,1,'眼镜',0,12),(17,0,0,1,'书籍',0,12),(18,0,0,1,'生活相关',0,0),(19,0,0,1,'美发',0,18),(20,0,0,1,'医院',0,18),(21,0,0,1,'药店',0,18),(22,0,0,1,'住宿',0,18),(23,0,0,1,'写真',0,18),(24,0,0,1,'健身',0,18),(25,0,0,1,'美容',0,18),(26,0,0,1,'培训',0,18),(27,0,0,1,'其他',0,18),(28,0,0,1,'休闲娱乐',0,0),(29,0,0,1,'KTV',0,28),(30,0,0,1,'电影院',0,28),(31,0,0,1,'桌游',0,28),(32,0,0,1,'旅游',0,28),(33,0,0,1,'其他',0,28),(34,0,0,2,'浙江省',0,0),(35,0,0,2,'杭州市',0,34),(36,0,0,2,'西湖区',0,35),(37,0,0,2,'西湖北线/黄龙',0,36),(38,0,0,2,'龙井/虎跑',0,36),(39,0,0,2,'高新文教区',0,36),(40,0,0,2,'古墩路沿线',0,36),(41,0,0,2,'高新文教区',0,36),(42,0,0,2,'上城区',0,35),(43,0,0,2,'湖滨',0,42),(44,0,0,2,'南山路',0,42),(45,0,0,2,'吴山广场/河坊街',0,42),(46,0,0,2,'城站火车站',0,42),(47,0,0,2,'下城区',0,35),(48,0,0,2,'武林广场',0,47),(49,0,0,2,'凤起路沿线',0,47),(50,0,0,2,'庆春路沿线',0,47),(51,0,0,2,'朝晖地区',0,47),(52,0,0,2,'拱墅区',0,35),(53,0,0,2,'湖墅南路',0,52),(54,0,0,2,'大关',0,52),(55,0,0,2,'拱宸桥/上塘',0,52),(56,0,0,2,'朝晖地区',0,52),(57,0,0,2,'江干区',0,35),(58,0,0,2,'城东',0,57),(59,0,0,2,'下沙',0,57),(60,0,0,2,'萧山区',0,35),(61,0,0,2,'城厢',0,60),(62,0,0,2,'北干',0,60),(63,0,0,2,'萧山其他',0,60),(64,0,0,2,'滨江区',0,35),(65,0,0,2,'开发区/高教园',0,64),(66,0,0,3,'浙江工商大学',0,0),(67,0,0,3,'杭州电子科技大学',0,0),(68,0,0,3,'浙江理工大学',0,0),(69,0,0,3,'浙江财经学院',0,0),(70,0,0,3,'中国计量学院',0,0),(71,0,0,3,'杭州师范学院',0,0),(72,0,0,3,'浙江传媒学院',0,0),(73,0,0,3,'杭州职业技术学院',0,0),(74,0,0,3,'浙江水利水电专科学校',0,0),(75,0,0,3,'浙江金融职业学院',0,0),(76,0,0,3,'浙江育英职业技术学院',0,0),(77,0,0,3,'浙江经济职业技术学院',0,0),(78,0,0,3,'浙江经贸职业技术学院',0,0),(79,0,0,3,'浙江警官职业学院',0,0);
/*!40000 ALTER TABLE `t_wordbook` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-09-23  4:41:36
