-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: localhost    Database: yizhi_scrip
-- ------------------------------------------------------
-- Server version	8.0.27

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `yizhi_scrip`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `yizhi_scrip` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `yizhi_scrip`;

--
-- Table structure for table `ap_user`
--

DROP TABLE IF EXISTS `ap_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_user` (
                           `id` bigint NOT NULL AUTO_INCREMENT,
                           `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
                           `password` varchar(32) DEFAULT NULL COMMENT '密码，需要加密',
                           `created` datetime DEFAULT NULL,
                           `updated` datetime DEFAULT NULL,
                           PRIMARY KEY (`id`),
                           KEY `mobile` (`mobile`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_user`
--

LOCK TABLES `ap_user` WRITE;
/*!40000 ALTER TABLE `ap_user` DISABLE KEYS */;
INSERT INTO `ap_user` VALUES (1,'18018594399','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(2,'18018594400','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(3,'18018594401','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(4,'18018594402','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(5,'18018594403','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(6,'18018594404','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(7,'18018594405','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(8,'18018594406','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(9,'18018594407','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27'),(10,'18018594408','e10adc3949ba59abbe56e057f20f883e','2022-02-28 17:39:27','2022-02-28 17:39:27');
/*!40000 ALTER TABLE `ap_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ap_user_info`
--

DROP TABLE IF EXISTS `ap_user_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ap_user_info` (
                                `id` bigint NOT NULL AUTO_INCREMENT,
                                `user_id` bigint NOT NULL COMMENT '用户id',
                                `nick_name` varchar(50) DEFAULT NULL COMMENT '昵称',
                                `logo` varchar(100) DEFAULT NULL COMMENT '用户头像',
                                `tags` varchar(50) DEFAULT NULL COMMENT '用户标签：多个用逗号分隔',
                                `sex` int DEFAULT '3' COMMENT '性别，1-男，2-女，3-未知',
                                `age` int DEFAULT NULL COMMENT '用户年龄',
                                `edu` varchar(20) DEFAULT NULL COMMENT '学历',
                                `city` varchar(20) DEFAULT NULL COMMENT '居住城市',
                                `birthday` varchar(20) DEFAULT NULL COMMENT '生日',
                                `cover_pic` varchar(100) DEFAULT NULL COMMENT '封面图片',
                                `industry` varchar(20) DEFAULT NULL COMMENT '行业',
                                `created` datetime DEFAULT NULL,
                                `updated` datetime DEFAULT NULL,
                                `status` int DEFAULT NULL COMMENT '用户状态',
                                PRIMARY KEY (`id`),
                                UNIQUE KEY `user_id` (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3 COMMENT='用户信息表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ap_user_info`
--

LOCK TABLES `ap_user_info` WRITE;
/*!40000 ALTER TABLE `ap_user_info` DISABLE KEYS */;
INSERT INTO `ap_user_info` VALUES (1,1,'yizhi_user_01','https://z3.ax1x.com/2021/05/22/gqLnWq.png','高二,议论文,爱国',1,18,'高二','上海市-浦东新区','2004-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(2,2,'yizhi_user_02','https://z3.ax1x.com/2021/05/22/gqLnWq.png','高一,诗歌,敬业',1,17,'高一','上海市-浦东新区','2005-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(3,3,'yizhi_user_03','https://z3.ax1x.com/2021/05/22/gqLnWq.png','九年级,散文,诚信',1,16,'九年级','上海市-浦东新区','2006-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(4,4,'yizhi_user_04','https://z3.ax1x.com/2021/05/22/gqLnWq.png','八年级,记叙文,友善',1,15,'八年级','上海市-浦东新区','2007-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(5,5,'yizhi_user_05','https://z3.ax1x.com/2021/05/22/gqLnWq.png','七年级,议论文,爱国',1,14,'七年级','上海市-浦东新区','2008-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(6,6,'yizhi_user_06','https://z3.ax1x.com/2021/05/22/gqLnWq.png','七年级,散文,敬业',2,15,'七年级','上海市-浦东新区','2007-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(7,7,'yizhi_user_07','https://z3.ax1x.com/2021/05/22/gqLnWq.png','八年级,诗歌,诚信',2,16,'八年级','上海市-浦东新区','2006-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(8,8,'yizhi_user_08','https://z3.ax1x.com/2021/05/22/gqLnWq.png','九年级,记叙文,友善',2,17,'九年级','上海市-浦东新区','2005-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(9,9,'yizhi_user_09','https://z3.ax1x.com/2021/05/22/gqLnWq.png','高一,议论文,爱国',2,18,'高一','上海市-浦东新区','2004-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1),(10,10,'yizhi_user_10','https://z3.ax1x.com/2021/05/22/gqLnWq.png','高二,议论文,爱国',2,19,'高二','上海市-浦东新区','2003-01-11','https://z3.ax1x.com/2021/05/26/2SOHRU.jpg','学生','2022-02-28 17:39:27','2022-02-28 17:39:27',1);
/*!40000 ALTER TABLE `ap_user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-02-28  9:39:54
