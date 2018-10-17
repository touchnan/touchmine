-- MySQL dump 10.13  Distrib 5.1.45, for Win32 (ia32)
--
-- Host: localhost    Database: wanto1
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
  `m_post_id` bigint(20) DEFAULT NULL,
  `m_reading` bit(1) DEFAULT NULL,
  `m_reason` varchar(255) DEFAULT NULL,
  `m_shop_id` bigint(20) DEFAULT NULL,
  `m_status` int(11) DEFAULT NULL,
  `m_topic_id` bigint(20) DEFAULT NULL,
  `m_type` int(11) DEFAULT NULL,
  `m_user_id` bigint(20) DEFAULT NULL,
  `m_opinion_state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='消息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_message`
--

LOCK TABLES `t_message` WRITE;
/*!40000 ALTER TABLE `t_message` DISABLE KEYS */;
INSERT INTO `t_message` VALUES (1,'2012-09-21 14:36:03',1,NULL,'\0',NULL,NULL,0,NULL,1,3,NULL),(3,'2012-09-21 16:49:49',1,NULL,'\0',NULL,NULL,0,NULL,1,5,NULL),(4,'2012-09-21 18:42:09',1,NULL,'\0',NULL,NULL,0,NULL,1,6,NULL);
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
  `post_last_reply_time` datetime DEFAULT NULL,
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='发贴';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post`
--

LOCK TABLES `t_post` WRITE;
/*!40000 ALTER TABLE `t_post` DISABLE KEYS */;
INSERT INTO `t_post` VALUES (1,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 15:01:43',1,1,0,0,0,3),(2,0,0,'2012-09-21 15:19:26','2012-09-21 16:30:02',0,'192.168.1.17',0,0,'2012-09-21 15:19:26',1,1,1,0,0,3),(3,0,0,'2012-09-21 15:26:53',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:26:53',1,1,2,0,0,3),(4,0,0,'2012-09-21 15:27:53',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:27:53',1,1,2,0,0,3),(5,0,0,'2012-09-21 15:27:59',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:27:59',1,1,2,0,0,3),(6,0,0,'2012-09-21 15:28:06',NULL,2,'192.168.1.17',0,-99,'2012-09-21 15:28:06',1,1,2,0,0,3),(7,0,0,'2012-09-21 15:28:14',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:28:14',1,1,2,0,0,3),(8,0,0,'2012-09-21 15:30:03',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:30:03',1,1,2,0,0,3),(9,0,0,'2012-09-21 15:30:06',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:30:06',1,1,2,0,0,3),(10,0,0,'2012-09-21 15:30:10',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:30:10',1,1,2,0,0,3),(11,0,0,'2012-09-21 15:30:15',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:30:15',1,1,2,0,0,3),(12,0,0,'2012-09-21 15:30:19',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:30:19',1,1,2,0,0,3),(13,0,0,'2012-09-21 15:33:49',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:33:49',1,1,2,0,0,3),(14,0,0,'2012-09-21 15:33:54',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:33:54',1,1,2,0,0,3),(15,0,0,'2012-09-21 15:34:12',NULL,2,'192.168.1.17',0,0,'2012-09-21 15:34:12',1,1,2,0,0,3),(16,0,0,'2012-09-21 16:11:18','2012-09-21 16:30:19',0,'192.168.1.17',0,0,'2012-09-21 16:11:18',1,1,1,0,0,3),(17,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 16:18:47',2,2,0,0,0,4),(18,0,0,'2012-09-21 16:19:58','2012-09-21 16:20:21',0,'192.168.1.17',0,0,'2012-09-21 16:19:58',2,2,1,0,0,4),(19,0,0,'2012-09-21 16:20:07',NULL,18,'192.168.1.17',0,0,'2012-09-21 16:20:07',2,2,2,0,0,4),(20,0,0,'2012-09-21 16:20:13',NULL,18,'192.168.1.17',0,0,'2012-09-21 16:20:13',2,2,2,0,0,4),(21,0,0,'2012-09-21 16:20:18',NULL,18,'192.168.1.17',0,0,'2012-09-21 16:20:18',2,2,2,0,0,4),(22,0,0,'2012-09-21 16:20:21',NULL,18,'192.168.1.17',0,0,'2012-09-21 16:20:21',2,2,2,0,0,4),(23,0,0,'2012-09-21 16:25:27','2012-09-21 16:29:50',0,'192.168.1.17',0,0,'2012-09-21 16:25:27',1,1,1,0,0,4),(24,0,0,'2012-09-21 16:29:46',NULL,23,'192.168.1.17',0,0,'2012-09-21 16:29:46',1,1,2,0,0,4),(25,0,0,'2012-09-21 16:29:50',NULL,23,'192.168.1.17',0,0,'2012-09-21 16:29:50',1,1,2,0,0,4),(26,0,0,'2012-09-21 16:29:58',NULL,2,'192.168.1.17',0,0,'2012-09-21 16:29:58',1,1,2,0,0,4),(27,0,0,'2012-09-21 16:30:02',NULL,2,'192.168.1.17',0,0,'2012-09-21 16:30:02',1,1,2,0,0,4),(28,0,0,'2012-09-21 16:30:13',NULL,16,'192.168.1.17',0,0,'2012-09-21 16:30:13',1,1,2,0,0,4),(29,0,0,'2012-09-21 16:30:16',NULL,16,'192.168.1.17',0,0,'2012-09-21 16:30:16',1,1,2,0,0,4),(30,0,0,'2012-09-21 16:30:19',NULL,16,'192.168.1.17',0,0,'2012-09-21 16:30:19',1,1,2,0,0,4),(31,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 16:54:11',3,3,0,0,0,5),(32,0,0,'2012-09-21 17:28:52',NULL,0,'192.168.1.17',0,0,'2012-09-21 17:28:52',3,3,1,0,0,5),(33,0,0,'2012-09-21 18:00:37',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:00:37',3,3,1,0,0,5),(34,0,0,'2012-09-21 18:01:13','2012-09-21 18:01:43',0,'192.168.1.17',0,0,'2012-09-21 18:01:13',3,3,1,0,0,5),(35,0,0,'2012-09-21 18:01:38',NULL,34,'192.168.1.17',0,0,'2012-09-21 18:01:38',3,3,2,0,0,5),(36,0,0,'2012-09-21 18:01:43',NULL,34,'192.168.1.17',0,0,'2012-09-21 18:01:43',3,3,2,0,0,5),(37,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',4,4,0,0,0,5),(38,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',5,5,0,0,0,5),(39,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',6,6,0,0,0,5),(40,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',7,7,0,0,0,5),(41,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',8,8,0,0,0,5),(42,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',9,9,0,0,0,5),(43,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:22:26',10,10,0,0,0,5),(44,0,0,'2012-09-21 18:26:07',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:26:07',4,4,2,0,0,5),(45,0,0,'2012-09-21 18:26:12',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:26:12',4,4,2,0,0,5),(46,0,0,'2012-09-21 18:26:16',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:26:16',4,4,2,0,0,5),(47,0,0,'2012-09-21 18:26:33',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:26:33',4,4,2,0,0,5),(48,0,0,'2012-09-21 18:27:07',NULL,0,'192.168.1.17',0,0,'2012-09-21 18:27:07',4,4,2,0,0,5),(49,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:29:15',11,11,0,0,0,5),(50,0,0,'2012-09-21 18:29:33','2012-09-21 18:29:40',0,'192.168.1.17',0,0,'2012-09-21 18:29:33',11,11,1,0,0,5),(51,0,0,'2012-09-21 18:29:40',NULL,50,'192.168.1.17',0,0,'2012-09-21 18:29:40',11,11,2,0,0,5),(52,0,0,'2012-09-21 18:30:03','2012-09-21 18:30:18',0,'192.168.1.17',0,0,'2012-09-21 18:30:03',11,11,1,0,0,5),(53,0,0,'2012-09-21 18:30:08',NULL,52,'192.168.1.17',0,0,'2012-09-21 18:30:08',11,11,2,0,0,5),(54,0,0,'2012-09-21 18:30:13',NULL,52,'192.168.1.17',0,0,'2012-09-21 18:30:13',11,11,2,0,0,5),(55,0,0,'2012-09-21 18:30:18',NULL,52,'192.168.1.17',0,0,'2012-09-21 18:30:18',11,11,2,0,0,5),(56,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:35:26',12,12,0,0,0,5),(57,0,0,'2012-09-21 18:35:38','2012-09-21 18:35:42',0,'192.168.1.17',0,0,'2012-09-21 18:35:38',12,12,1,0,0,5),(58,0,0,'2012-09-21 18:35:42',NULL,57,'192.168.1.17',0,0,'2012-09-21 18:35:42',12,12,2,0,0,5),(59,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',13,13,0,0,0,5),(60,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',14,14,0,0,0,5),(61,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',15,15,0,0,0,5),(62,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',16,16,0,0,0,5),(63,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',17,17,0,0,0,5),(64,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',18,18,0,0,0,5),(65,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:38:50',19,19,0,0,0,5),(66,0,0,NULL,NULL,0,'192.168.1.17',0,0,'2012-09-21 18:43:30',20,20,0,0,0,6);
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
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8 COMMENT='贴子内容';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_post_text`
--

LOCK TABLES `t_post_text` WRITE;
/*!40000 ALTER TABLE `t_post_text` DISABLE KEYS */;
INSERT INTO `t_post_text` VALUES (1,NULL,'外婆家 湖墅店','一家杭城知名老店，少吃加福禄寿的费省得浪费付款灵山大佛加速定理非均上付款灵山大佛加速定理非均栗色考虑缩短军风来树动加福禄寿的飞机栗色反射定律福建省电缆附件收到了，军fks丁莱夫是！！！'),(2,'吐槽 很一般 福建省的科林费斯','我觉得很一般，没有大家说的那么好','我觉得很一般，没有大家说的那么好，根本对不起这个价格房价多少来房间收到了费费省得浪费军上登录非道费灵山大佛加速定理福建省的费栗色的分角色读方法\r\n\r\n龙卷风费灵山大佛加速定理费记录锁定考虑缩短加福禄寿的\r\n\r\n军考虑缩短加福禄寿的到粉色的粉色的费少到佛挡杀佛水电\r\n\r\n费辅导书粉色的是多少倒萨倒萨倒萨倒萨倒萨倒萨大声大声道到沙发上的非\r\n粉色的粉色的费上到费水电费\r\n我是第三行\r\n我是第四行了！！！\r\n\r\n空过一行了\r\n    我空了4格'),(3,NULL,NULL,'我是楼主'),(4,NULL,NULL,'粉色的粉色的'),(5,NULL,NULL,'粉色的粉色的费'),(6,NULL,NULL,'粉色的粉色的'),(7,NULL,NULL,'这个是什么'),(8,NULL,NULL,'倒萨佛挡杀佛到上'),(9,NULL,NULL,'费上到佛挡杀佛'),(10,NULL,NULL,'粉色的粉色的费水电费'),(11,NULL,NULL,'粉色的粉色的费水电费'),(12,NULL,NULL,'粉色的粉色的费水电费'),(13,NULL,NULL,'粉色的粉色的费'),(14,NULL,NULL,'11'),(15,NULL,NULL,'12'),(16,'辅导书粉色的 123 435435','话题22222','粉色的粉色的费粉色的粉色的\r\n粉色的粉色的费粉色的粉色的\r\n到费水电费广泛地费水电费'),(17,NULL,'花中城 滨文店','我觉得加福禄寿的军费省得浪费咖啡色的来分手典礼军焚枯食淡房间收到了军费卡斯迪朗房间收到了省得浪费三闾大夫记录记录'),(18,'粉色的粉色的 粉色的粉色的费','花中城房间收到了房价多少了','粉色的粉色的费少到'),(19,NULL,NULL,'1楼'),(20,NULL,NULL,'2楼'),(21,NULL,NULL,'3'),(22,NULL,NULL,'4'),(23,'到发送到 粉色的粉色的费 佛挡杀佛水电费 粉色的粉色的费 粉色的粉色的','我是11111我来说点啥','粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n粉色的粉色的费少到test\r\n'),(24,NULL,NULL,'123123123213'),(25,NULL,NULL,'3213123'),(26,NULL,NULL,'1232133132'),(27,NULL,NULL,'3213213213'),(28,NULL,NULL,'额外企鹅王企鹅'),(29,NULL,NULL,'额外企鹅王企鹅'),(30,NULL,NULL,'额外企鹅王企鹅'),(31,NULL,'银乐迪 湖滨西湖店付款的数来','粉色的粉色的费粉色的粉色的费粉色的粉色的粉色的粉色的费非似懂非懂是'),(32,'23454 6786797897','银乐迪 湖滨西湖店付款的数来','粉色的粉色的费粉色的粉色的费粉色的粉色的粉色的粉色的费非似懂非懂是'),(33,'31232 435435 546546546','银乐迪 22222','人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n'),(34,'31232 435435 546546546','银乐迪 333333333333333','2143143243543564657\r\n2143143243543564657\r\n2143143243543564657\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人2143143243543564657\r\n214314324354356465721431432435435646572143143243543564657214314324354356465721431432435435646572143143243543564657214314324354356465721431432435435646572143143243543564657都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费人都在唱歌来房间收到了灵山大佛加速定理粉色的粉色的费\r\n'),(35,NULL,NULL,'3143234234234'),(36,NULL,NULL,'4234234234234234'),(37,NULL,'红烧猪肝','非常好吃非常好吃。发送到，发链接收到了福建省电缆附件收到了非均'),(38,NULL,'茭白','一般般'),(39,NULL,'寿司卷非均卢卡斯的','坑爹了'),(40,NULL,'春卷发送到','粉色的粉色的费少到'),(41,NULL,'石锅饭','粉色的粉色的费水电费'),(42,NULL,'炒辣椒','有点辣，太辣了'),(43,NULL,'醉鱼','杭州特色小吃'),(44,NULL,NULL,'相当难吃啊'),(45,NULL,NULL,'粉色的粉色的费水电费'),(46,NULL,NULL,'粉色的粉色的费少到'),(47,NULL,NULL,'粉色的粉色的费'),(48,NULL,NULL,'粉色的粉色的费'),(49,NULL,'KFC324354','粉色的粉色的费粉色的粉色的费少到'),(50,'粉色的粉色的 粉色的粉色的 粉色的粉色的费','kfc 粉色的粉色的费水电费','粉色的粉色的费少到'),(51,NULL,NULL,'粉色的粉色的费水电费上'),(52,'粉色的粉色的 粉色的粉色的 粉色的粉色的费','kfc22222222222222 粉色的粉色的费水电费','2222222222222222222222222\r\n粉色的粉色的费水电费\r\n粉色的粉色的费\r\n粉色的粉色的费少到粉色的粉色的费水电费'),(53,NULL,NULL,'粉色的粉色的粉色的粉色的'),(54,NULL,NULL,'粉色的粉色的'),(55,NULL,NULL,'粉色的粉色的费'),(56,NULL,'杭州 宋城','粉色的粉色的费少颠三倒四倒萨倒萨倒萨倒萨倒萨倒萨大声大声道\r\n粉色的粉色的费少到'),(57,'发送到 辅导书粉色的 粉色的粉色的费','粉色的粉色的费少','辅导书粉色的粉色的费'),(58,NULL,NULL,'粉色的粉色的'),(59,NULL,'上毒蛇钉刺','解锁点击福禄寿的242342342342342'),(60,NULL,'粉色的粉色的费','粉色的粉色的费少'),(61,NULL,'人得分送到府上','粉色的粉色的费少到'),(62,NULL,'粉色的粉色的费少到','3213123213'),(63,NULL,'321313213213','32132131231'),(64,NULL,'费水电费为费','粉色的粉色的费少'),(65,NULL,'粉色的粉色的费少','粉色的粉色的费少到'),(66,NULL,'绕弯儿维尔瓦儿粉色的粉色的','佛挡杀佛');
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
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='主题';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic`
--

LOCK TABLES `t_topic` WRITE;
/*!40000 ALTER TABLE `t_topic` DISABLE KEYS */;
INSERT INTO `t_topic` VALUES (1,0,1,NULL,3,'wanto/201209/1348210630781-528.jpg',3,'2012-09-21 16:30:19',0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348210630781-528_thumb.jpg','2012-09-21 15:01:43','外婆家 湖墅店',1,11),(2,0,1,NULL,4,'wanto/201209/1348215501625-341.jpg',4,'2012-09-21 16:20:21',0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348215501625-341_thumb.jpg','2012-09-21 16:18:47','花中城 滨文店',1,0),(3,0,0,'2012-09-21 17:59:42',5,'wanto/201209/1348221579281-443.jpg',5,'2012-09-21 18:01:43',0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348221579281-443_thumb.jpg','2012-09-21 16:54:11','银乐迪 湖滨西湖店付款的数来',1,3),(4,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222617453-778.jpg',5,'2012-09-21 18:27:07',3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222617453-778_thumb.jpg','2012-09-21 18:22:26','红烧猪肝',11,0),(5,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222617812-110.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222617812-110_thumb.jpg','2012-09-21 18:22:26','茭白',11,0),(6,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222618109-450.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222618109-450_thumb.jpg','2012-09-21 18:22:26','寿司卷非均卢卡斯的',11,0),(7,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222618375-670.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222618375-670_thumb.jpg','2012-09-21 18:22:26','春卷发送到',11,0),(8,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222618656-901.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222618656-901_thumb.jpg','2012-09-21 18:22:26','石锅饭',11,0),(9,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222618921-490.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222618921-490_thumb.jpg','2012-09-21 18:22:26','炒辣椒',11,0),(10,0,0,'2012-09-21 18:22:26',5,'wanto/201209/1348222619312-120.jpg',5,NULL,3,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348222619312-120_thumb.jpg','2012-09-21 18:22:26','醉鱼',11,0),(11,0,0,NULL,5,'wanto/201209/1348223341953-23.jpg',5,'2012-09-21 18:30:18',0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223341953-23_thumb.jpg','2012-09-21 18:29:15','KFC324354',1,0),(12,0,0,NULL,5,'wanto/201209/1348223686109-759.jpg',5,'2012-09-21 18:35:42',0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223686109-759_thumb.jpg','2012-09-21 18:35:26','杭州 宋城',1,0),(13,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223815562-378.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223815562-378_thumb.jpg','2012-09-21 18:38:50','上毒蛇钉刺',11,0),(14,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223815921-212.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223815921-212_thumb.jpg','2012-09-21 18:38:50','粉色的粉色的费',11,0),(15,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223816156-552.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223816156-552_thumb.jpg','2012-09-21 18:38:50','人得分送到府上',11,0),(16,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223816453-122.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223816453-122_thumb.jpg','2012-09-21 18:38:50','粉色的粉色的费少到',11,0),(17,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223816703-803.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223816703-803_thumb.jpg','2012-09-21 18:38:50','321313213213',11,0),(18,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223816984-670.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223816984-670_thumb.jpg','2012-09-21 18:38:50','费水电费为费',11,0),(19,0,0,'2012-09-21 18:38:50',5,'wanto/201209/1348223817515-771.jpg',5,NULL,11,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348223817515-771_thumb.jpg','2012-09-21 18:38:50','粉色的粉色的费少',11,0),(20,0,0,NULL,6,'wanto/201209/1348224200546-201.jpg',6,NULL,0,'192.168.1.17',0,0,NULL,NULL,NULL,'wanto/201209/1348224200546-201_thumb.jpg','2012-09-21 18:43:30','绕弯儿维尔瓦儿粉色的粉色的',1,0);
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
  PRIMARY KEY (`topic_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COMMENT='主题扩展-店铺';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_topic_extend`
--

LOCK TABLES `t_topic_extend` WRITE;
/*!40000 ALTER TABLE `t_topic_extend` DISABLE KEYS */;
INSERT INTO `t_topic_extend` VALUES (1,'拱墅区119号 ，合同药店旁的小弄堂里的二楼308号',0,'#1#7#',NULL,0,NULL,3,0,'#21#22#40#41#',NULL,'一家杭城知名老店，少吃加福禄寿的费省得浪费付款灵山大佛加速定理非均上付款灵山大佛加速定理非均栗色考虑缩短军风来树动加福禄寿的飞机栗色反射定律福建省电缆附件收到了，军fks丁莱夫是！！！','\0','我的昕沙标号是xs201202-24'),(2,'滨文店粉色的粉色的费费水电费费水电费',0,'#1#7#',NULL,0,NULL,4,0,'#21#22#30#33#',NULL,'我觉得加福禄寿的军费省得浪费咖啡色的来分手典礼军焚枯食淡房间收到了军费卡斯迪朗房间收到了省得浪费三闾大夫记录记录','\0',''),(3,'西湖边均非收到了收到了加快速度了费省得浪费105号',0,'#12#13#',NULL,0,NULL,5,0,'#21#22#23#24#',NULL,'粉色的粉色的费粉色的粉色的费粉色的粉色的粉色的粉色的费非似懂非懂是','\0',''),(4,NULL,0,NULL,NULL,0,NULL,5,282800,NULL,NULL,'非常好吃非常好吃。发送到，发链接收到了福建省电缆附件收到了非均','\0',NULL),(5,NULL,0,NULL,NULL,0,NULL,5,121200,NULL,NULL,'一般般','\0',NULL),(6,NULL,0,NULL,NULL,0,NULL,5,303000,NULL,NULL,'坑爹了','\0',NULL),(7,NULL,0,NULL,NULL,0,NULL,5,2005,NULL,NULL,'粉色的粉色的费少到','\0',NULL),(8,NULL,0,NULL,NULL,0,NULL,5,1025,NULL,NULL,'粉色的粉色的费水电费','\0',NULL),(9,NULL,0,NULL,NULL,0,NULL,5,1500,NULL,NULL,'有点辣，太辣了','\0',NULL),(10,NULL,0,NULL,NULL,0,NULL,5,2800,NULL,NULL,'杭州特色小吃','\0',NULL),(11,'KFC324354辅导书粉色的粉色的粉色的',0,'#17#19#',NULL,0,NULL,5,0,'#21#22#48#50#',NULL,'粉色的粉色的费粉色的粉色的费少到','\0',''),(12,'粉色的粉色的费少粉色的费水电费',0,'#12#15#',NULL,0,NULL,5,0,'#21#22#48#51#',NULL,'粉色的粉色的费少颠三倒四倒萨倒萨倒萨倒萨倒萨倒萨大声大声道\r\n粉色的粉色的费少到','\0',''),(13,NULL,0,NULL,NULL,0,NULL,5,12334444444,NULL,NULL,'解锁点击福禄寿的242342342342342','\0',NULL),(14,NULL,0,NULL,NULL,0,NULL,5,23143254354365465,NULL,NULL,'粉色的粉色的费少','\0',NULL),(15,NULL,0,NULL,NULL,0,NULL,5,343400,NULL,NULL,'粉色的粉色的费少到','\0',NULL),(16,NULL,0,NULL,NULL,0,NULL,5,213213,NULL,NULL,'3213123213','\0',NULL),(17,NULL,0,NULL,NULL,0,NULL,5,57800,NULL,NULL,'32132131231','\0',NULL),(18,NULL,0,NULL,NULL,0,NULL,5,564646,NULL,NULL,'粉色的粉色的费少','\0',NULL),(19,NULL,0,NULL,NULL,0,NULL,5,2132423423423423,NULL,NULL,'粉色的粉色的费少到','\0',NULL),(20,'粉色的粉色的费水电费',0,'#1#3#',NULL,0,NULL,6,0,'#21#22#45#46#',NULL,'佛挡杀佛','\0','');
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
INSERT INTO `t_topic_vote` VALUES (1,3,1),(2,4,1);
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
  `c_type` int(11) DEFAULT NULL,
  `username` varchar(100) DEFAULT NULL,
  `user_thumb` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='系统用户';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,NULL,0,'',NULL,0,0,NULL,NULL,0,NULL,'韩成强','e10adc3949ba59abbe56e057f20f883e',0,0,NULL,NULL,0,0,0,-1,'hancq@qq.com',NULL),(2,NULL,0,'',NULL,0,0,NULL,NULL,0,NULL,'周祝燕','e10adc3949ba59abbe56e057f20f883e',0,0,NULL,NULL,0,0,0,-1,'zhouzy@qq.com',NULL),(3,'wanto/201209/1348209318140-578.jpg',0,'\0',NULL,21,0,'反射定律技法所 了反射定律 减肥裤收到了收到了 飞速度快浪费 jklsdflsd 金牛座',NULL,0,'我的心情贼爽贼爽的粉色的粉色的费粉色的粉色的费','白天不懂夜','183bf9f40f51b671e629e3d347feb778',0,0,'2012-09-21 14:36:03',NULL,56,0,0,0,'123@123.com',NULL),(4,'wanto/201209/1348215227031-383.jpg',0,'\0',NULL,0,0,'霸气 秃头 100元',NULL,0,'最近赶脚有点累','8个1','1bbd886460827015e5d605ed44252251',0,0,'2012-09-21 16:14:12',NULL,0,0,0,0,'11111111@11.COM',NULL),(5,'wanto/201209/1348217151265-924.jpg',0,'\0',NULL,0,0,'粉色的粉色的 分段设定粉色的 粉色的粉色的',NULL,0,NULL,'野田家宴','bae5e3208a3c700e3db642b6631e95b9',0,0,'2012-09-21 16:49:49',NULL,55,0,0,0,'22222222@22.com',NULL),(6,'wanto/201209/1348224090687-105.jpg',0,'\0',NULL,0,0,'为二位 热污染物二然为二位 然为二位的热污染物二',NULL,0,NULL,'狼来了','d27d320c27c3033b7883347d8beca317',0,0,'2012-09-21 18:42:09',NULL,0,0,0,0,'33333333@33.com',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8 COMMENT='字数字典、类型、地区等';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_wordbook`
--

LOCK TABLES `t_wordbook` WRITE;
/*!40000 ALTER TABLE `t_wordbook` DISABLE KEYS */;
INSERT INTO `t_wordbook` VALUES (1,0,0,1,'美食',0,0),(2,0,0,1,'火锅',0,1),(3,0,1,1,'烧烤',0,1),(4,0,0,1,'麻辣烫',0,1),(5,0,0,1,'西餐',0,1),(6,0,0,1,'海鲜',0,1),(7,0,2,1,'家常菜',0,1),(8,0,0,1,'日韩料理',0,1),(9,0,0,1,'快餐',0,1),(10,0,0,1,'蛋糕甜点',0,1),(11,0,0,1,'其他',0,1),(12,0,0,1,'娱乐',0,0),(13,0,1,1,'KTV',0,12),(14,0,0,1,'跑酷',0,12),(15,0,1,1,'爬山',0,12),(16,0,0,1,'环湖',0,12),(17,0,0,1,'其他',0,0),(18,0,0,1,'双人会',0,17),(19,0,1,1,'同乡会',0,17),(20,0,0,1,'同人会',0,17),(21,0,0,2,'浙江省',0,0),(22,0,0,2,'杭州市',0,21),(23,0,0,2,'西湖区',0,22),(24,0,1,2,'西湖北线/黄龙',0,23),(25,0,0,2,'龙井/虎跑',0,23),(26,0,0,2,'高新文教区',0,23),(27,0,0,2,'古墩路沿线',0,23),(28,0,0,2,'高新文教区',0,23),(29,0,0,2,'高新文教区',0,23),(30,0,0,2,'上城区',0,22),(31,0,0,2,'湖滨',0,30),(32,0,0,2,'南山路',0,30),(33,0,1,2,'吴山广场/河坊街',0,30),(34,0,0,2,'城站火车站',0,30),(35,0,0,2,'下城区',0,22),(36,0,0,2,'武林广场',0,35),(37,0,0,2,'凤起路沿线',0,35),(38,0,0,2,'庆春路沿线',0,35),(39,0,0,2,'朝晖地区',0,35),(40,0,0,2,'拱墅区',0,22),(41,0,1,2,'湖墅南路',0,40),(42,0,0,2,'大关',0,40),(43,0,0,2,'拱宸桥/上塘',0,40),(44,0,0,2,'朝晖地区',0,40),(45,0,0,2,'江干区',0,22),(46,0,1,2,'城东',0,45),(47,0,0,2,'下沙',0,45),(48,0,0,2,'萧山区',0,22),(49,0,0,2,'城厢',0,48),(50,0,1,2,'北干',0,48),(51,0,1,2,'萧山其他',0,48),(52,0,0,2,'滨江区',0,22),(53,0,0,2,'开发区/高教园',0,52),(54,0,0,3,'浙江大学',0,0),(55,0,0,3,'浙江理工',0,0),(56,0,0,3,'浙江工商大学',0,0),(57,0,0,3,'浙江工业大学',0,0),(58,0,0,3,'浙江传媒学院',0,0);
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

-- Dump completed on 2012-09-22  9:11:42
