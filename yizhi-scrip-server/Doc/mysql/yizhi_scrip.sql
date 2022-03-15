-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: yizhi_scrip
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `ap_announcement`
--

DROP TABLE IF EXISTS `ap_announcement`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_announcement`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `title`       varchar(200) DEFAULT NULL COMMENT '标题',
    `description` text COMMENT '描述',
    `created`     datetime     DEFAULT NULL,
    `updated`     datetime     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `created` (`created`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  DEFAULT CHARSET = utf8mb3 COMMENT ='公告表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_announcement`
--

LOCK TABLES `ap_announcement` WRITE;
/*!40000 ALTER TABLE `ap_announcement`
    DISABLE KEYS */;
INSERT INTO `ap_announcement`
VALUES (1, '公告1', '公告测试', '2022-03-09 18:54:53', '2022-03-09 18:54:59');
/*!40000 ALTER TABLE `ap_announcement`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ap_dictionary`
--

DROP TABLE IF EXISTS `ap_dictionary`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_dictionary`
(
    `id`          bigint       NOT NULL DEFAULT '0' COMMENT 'id',
    `parent_id`   bigint       NOT NULL DEFAULT '0' COMMENT '上级id',
    `name`        varchar(100) NOT NULL DEFAULT '' COMMENT '名称',
    `value`       bigint                DEFAULT NULL COMMENT '值',
    `dict_code`   varchar(20)           DEFAULT NULL COMMENT '编码',
    `create_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `is_deleted`  tinyint      NOT NULL DEFAULT '1' COMMENT '删除标记（0:不可用 1:可用）',
    PRIMARY KEY (`id`),
    KEY `idx_dict_code` (`dict_code`),
    KEY `idx_parent_id` (`parent_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb3 COMMENT ='组织架构表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_dictionary`
--

LOCK TABLES `ap_dictionary` WRITE;
/*!40000 ALTER TABLE `ap_dictionary`
    DISABLE KEYS */;
INSERT INTO `ap_dictionary`
VALUES (1, 0, '全部分类', 1, 'ROOT', '2022-03-12 07:24:50', '2022-03-12 07:25:23', 0),
       (10000, 1, '作文分类', 10000, 'ArticleType', '2022-03-12 07:25:12', '2022-03-12 07:25:25', 0),
       (10001, 10000, '记叙文', 10001, '', '2022-03-12 07:25:53', '2022-03-13 05:00:10', 0),
       (10002, 10000, '议论文', 10002, NULL, '2022-03-12 07:28:10', '2022-03-13 05:00:10', 0),
       (10003, 10000, '诗歌', 10003, NULL, '2022-03-12 07:28:42', '2022-03-13 05:00:10', 0),
       (10004, 10000, '散文', 10004, NULL, '2022-03-12 07:28:57', '2022-03-13 05:00:10', 0),
       (20000, 1, '素材分类', 20000, 'MaterialType', '2022-03-12 07:25:19', '2022-03-12 07:25:29', 0),
       (20001, 20000, '爱国', 20001, NULL, '2022-03-12 07:29:23', '2022-03-13 05:00:11', 0),
       (20002, 20000, '敬业', 20002, NULL, '2022-03-12 07:29:33', '2022-03-13 05:00:11', 0),
       (20003, 20000, '诚信', 20003, NULL, '2022-03-12 07:29:41', '2022-03-13 05:00:11', 0),
       (20004, 20000, '友善', 20004, NULL, '2022-03-12 07:29:50', '2022-03-13 05:00:11', 0),
       (20005, 20000, '婉约', 20005, NULL, '2022-03-12 08:16:21', '2022-03-13 05:00:11', 0),
       (20006, 20000, '豪放', 20006, NULL, '2022-03-12 08:17:04', '2022-03-13 05:00:11', 0),
       (30000, 1, '学历分类', 30000, 'EduType', '2022-03-13 05:00:05', '2022-03-13 05:00:11', 0),
       (30001, 30000, '小学', 30001, NULL, '2022-03-13 05:00:34', '2022-03-13 05:00:34', 1),
       (30002, 30000, '七年级', 30002, NULL, '2022-03-13 05:01:14', '2022-03-13 05:01:14', 1),
       (30003, 30000, '八年级', 30003, NULL, '2022-03-13 05:01:25', '2022-03-13 05:02:36', 1),
       (30004, 30000, '九年级', 30004, NULL, '2022-03-13 05:01:36', '2022-03-13 05:02:36', 1),
       (30005, 30000, '高一', 30005, NULL, '2022-03-13 05:01:45', '2022-03-13 05:02:36', 1),
       (30006, 30000, '高二', 30006, NULL, '2022-03-13 05:01:55', '2022-03-13 05:02:36', 1),
       (30007, 30000, '高三', 30007, NULL, '2022-03-13 05:02:04', '2022-03-13 05:02:36', 1),
       (30008, 30000, '本科', 30008, NULL, '2022-03-13 05:02:17', '2022-03-13 05:02:36', 1),
       (40000, 1, '行业分类', 40000, 'IndustryType', '2022-03-13 05:04:37', '2022-03-13 05:04:37', 1),
       (40001, 40000, '学生', 40001, NULL, '2022-03-13 05:04:50', '2022-03-13 05:05:19', 1),
       (40002, 40000, '作文爱好者', 40002, NULL, '2022-03-13 05:05:04', '2022-03-13 05:05:19', 1);
/*!40000 ALTER TABLE `ap_dictionary`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ap_user`
--

DROP TABLE IF EXISTS `ap_user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_user`
(
    `id`       bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `mobile`   varchar(11) DEFAULT NULL COMMENT '手机号',
    `password` varchar(32) DEFAULT NULL COMMENT '密码，需要加密',
    `created`  datetime    DEFAULT NULL COMMENT '创建日期',
    `updated`  datetime    DEFAULT NULL COMMENT '更新日期',
    PRIMARY KEY (`id`),
    KEY `mobile` (`mobile`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 29
  DEFAULT CHARSET = utf8mb3 COMMENT ='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_user`
--

LOCK TABLES `ap_user` WRITE;
/*!40000 ALTER TABLE `ap_user`
    DISABLE KEYS */;
INSERT INTO `ap_user`
VALUES (1, '18018594399', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-03-09 22:32:49'),
       (2, '18018594400', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (3, '18018594401', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (4, '18018594402', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (5, '18018594403', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (6, '18018594404', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (7, '18018594405', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (8, '18018594406', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (9, '18018594407', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27'),
       (10, '18018594408', 'e10adc3949ba59abbe56e057f20f883e', '2022-02-28 17:39:27', '2022-02-28 17:39:27');
/*!40000 ALTER TABLE `ap_user`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ap_user_info`
--

DROP TABLE IF EXISTS `ap_user_info`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_user_info`
(
    `id`        bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
    `user_id`   bigint NOT NULL COMMENT '用户id',
    `nick_name` varchar(50)  DEFAULT NULL COMMENT '昵称',
    `logo`      varchar(100) DEFAULT NULL COMMENT '用户头像',
    `tags`      varchar(50)  DEFAULT NULL COMMENT '用户标签：多个用逗号分隔',
    `sex`       int          DEFAULT '3' COMMENT '性别，1-男，2-女，3-未知',
    `age`       int          DEFAULT NULL COMMENT '用户年龄',
    `edu`       varchar(20)  DEFAULT NULL COMMENT '学历',
    `city`      varchar(20)  DEFAULT NULL COMMENT '居住城市',
    `birthday`  varchar(20)  DEFAULT NULL COMMENT '生日',
    `cover_pic` varchar(100) DEFAULT NULL COMMENT '封面图片',
    `industry`  varchar(20)  DEFAULT NULL COMMENT '行业',
    `created`   datetime     DEFAULT NULL COMMENT '创建日期',
    `updated`   datetime     DEFAULT NULL COMMENT '更新日期',
    `status`    int          DEFAULT NULL COMMENT '用户状态',
    PRIMARY KEY (`id`),
    UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb3 COMMENT ='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_user_info`
--

LOCK TABLES `ap_user_info` WRITE;
/*!40000 ALTER TABLE `ap_user_info`
    DISABLE KEYS */;
INSERT INTO `ap_user_info`
VALUES (1, 1, 'yizhi_user_01', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '七年级,议论文,诚信', 1, 14, '七年级', '上海市-浦东新区',
        '2008-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-03-04 15:25:42', '2022-03-09 20:39:40',
        1),
       (2, 2, 'yizhi_user_02', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '高一,诗歌,敬业', 1, 17, '高一', '上海市-浦东新区',
        '2005-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (3, 3, 'yizhi_user_03', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '九年级,散文,诚信', 1, 16, '九年级', '上海市-浦东新区',
        '2006-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (4, 4, 'yizhi_user_04', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '八年级,记叙文,友善', 1, 15, '八年级', '上海市-浦东新区',
        '2007-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (5, 5, 'yizhi_user_05', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '七年级,议论文,爱国', 1, 14, '七年级', '上海市-浦东新区',
        '2008-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (6, 6, 'yizhi_user_06', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '七年级,散文,敬业', 2, 15, '七年级', '上海市-浦东新区',
        '2007-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (7, 7, 'yizhi_user_07', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '八年级,诗歌,诚信', 2, 16, '八年级', '上海市-浦东新区',
        '2006-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (8, 8, 'yizhi_user_08', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '九年级,记叙文,友善', 2, 17, '九年级', '上海市-浦东新区',
        '2005-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (9, 9, 'yizhi_user_09', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '高一,议论文,爱国', 2, 18, '高一', '上海市-浦东新区',
        '2004-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1),
       (10, 10, 'yizhi_user_10', 'https://z3.ax1x.com/2021/05/22/gqLnWq.png', '高二,议论文,爱国', 2, 19, '高二', '上海市-浦东新区',
        '2003-01-11', 'https://z3.ax1x.com/2021/05/26/2SOHRU.jpg', '学生', '2022-02-28 17:39:27', '2022-02-28 17:39:27',
        1);
/*!40000 ALTER TABLE `ap_user_info`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2022-03-15  5:10:45
