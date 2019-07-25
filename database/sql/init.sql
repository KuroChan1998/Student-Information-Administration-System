CREATE DATABASE  IF NOT EXISTS `mydatabase2` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mydatabase2`;
-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: mydatabase2
-- ------------------------------------------------------
-- Server version	8.0.13

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `class` (
  `class_id` varchar(32) NOT NULL,
  `class_major` int(11) NOT NULL,
  `class_name` varchar(50) NOT NULL,
  `class_grade` int(11) NOT NULL,
  `class_stu_num` char(12) DEFAULT NULL,
  `class_tea_num` char(12) DEFAULT NULL,
  `class_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`class_id`),
  UNIQUE KEY `class_name_UNIQUE` (`class_name`),
  KEY `c_m_idx` (`class_major`),
  KEY `c_g_idx` (`class_grade`),
  CONSTRAINT `c_g` FOREIGN KEY (`class_grade`) REFERENCES `grade` (`grade_id`),
  CONSTRAINT `c_m` FOREIGN KEY (`class_major`) REFERENCES `major` (`major_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('0824d28fa0a711e991dd54ab3aa53988',4,'2016级电影电视1班',6,'','',''),('22223114ab6111e9976254ab3aa53988',9,'2016级基础数学1班',6,'','',''),('2d69671dab6111e9976254ab3aa53988',8,'2014级应用数学1班',4,'','',''),('4039c6fe91ca11e99f1554ab3aa53988',2,'2016级信安1班',6,NULL,NULL,'信安'),('437757baa0a611e991dd54ab3aa53988',1,'2014级计算机1班',4,'514000000000','100000000003',''),('48b48061ab6111e9976254ab3aa53988',10,'2012级建筑1班',2,'','',''),('4da07558a0a711e991dd54ab3aa53988',4,'2014级电影电视1班',4,'','',''),('569b0b4cab6111e9976254ab3aa53988',11,'2012级船舶与海洋1班',2,'','',''),('6382641aa0a711e991dd54ab3aa53988',6,'2016级机械自动化1班',6,'','',''),('66e8283a91ca11e99f1554ab3aa53988',3,'2016级软件1班',6,NULL,NULL,NULL),('6fe9c465a0a711e991dd54ab3aa53988',6,'2014级机械自动化1班',4,'','',''),('7bb58cc1a0a711e991dd54ab3aa53988',7,'2016级核能源1班',6,'','',''),('85e4be3ea0a711e991dd54ab3aa53988',7,'2014核能源1班',4,'','',''),('9ee003b5a0a611e991dd54ab3aa53988',1,'2012级计算机1班',2,'512000000002','',''),('bccf84aaa0a611e991dd54ab3aa53988',2,'2014级信安1班',4,'','',''),('c86fae77a0a611e991dd54ab3aa53988',3,'2014级软件1班',4,'','',''),('d6384ce7a0a611e991dd54ab3aa53988',5,'2016级新闻传播1班',6,'','',''),('e00d47d58ce811e98a0b54ab3aa53988',1,'2016级计算机1班',6,'516030910429','100000000001',NULL),('e7bea2f7a0a611e991dd54ab3aa53988',5,'2014级新闻传播1班',4,'','','');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `college` (
  `college_id` int(11) NOT NULL AUTO_INCREMENT,
  `college_name` varchar(50) NOT NULL,
  `college_property` enum('工科','理科','文科') DEFAULT NULL,
  `college_tea_num` char(12) DEFAULT NULL,
  `college_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`college_id`),
  UNIQUE KEY `college_name_UNIQUE` (`college_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES (1,'电子信息与电气工程学院','工科','100000000001',NULL),(2,'媒体与设计学院','文科','100000000023',''),(3,'机械与动力工程学院','工科','100000000035',''),(4,'海洋船舶与建筑工程学院','工科','100000000050',''),(5,'数学科学学院','理科','100000000060','');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `grade`
--

DROP TABLE IF EXISTS `grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `grade` (
  `grade_id` int(11) NOT NULL AUTO_INCREMENT,
  `grade_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `grade_stu_num` char(12) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `grade_tea_num` char(12) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  `grade_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`grade_id`),
  UNIQUE KEY `grade_name_UNIQUE` (`grade_name`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `grade`
--

LOCK TABLES `grade` WRITE;
/*!40000 ALTER TABLE `grade` DISABLE KEYS */;
INSERT INTO `grade` VALUES (1,'2011级',NULL,NULL,NULL),(2,'2012级',NULL,NULL,NULL),(3,'2013级',NULL,NULL,NULL),(4,'2014级',NULL,NULL,NULL),(5,'2015级',NULL,NULL,NULL),(6,'2016级','516030910429',NULL,NULL),(7,'2017级',NULL,NULL,NULL),(8,'2018级',NULL,NULL,NULL),(9,'2019级',NULL,NULL,NULL),(10,'2020级',NULL,NULL,NULL);
/*!40000 ALTER TABLE `grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `major` (
  `major_id` int(11) NOT NULL AUTO_INCREMENT,
  `major_college` int(11) NOT NULL,
  `major_name` varchar(50) NOT NULL,
  `major_tea_num` char(12) DEFAULT NULL,
  `major_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`major_id`),
  UNIQUE KEY `major_name_UNIQUE` (`major_name`),
  KEY `m_c_idx` (`major_college`),
  CONSTRAINT `m_c` FOREIGN KEY (`major_college`) REFERENCES `college` (`college_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES (1,1,'计算机科学','100000000001',''),(2,1,'信息安全','100000000002',''),(3,1,'软件工程','100000000016',''),(4,2,'电影与电视系','100000000023','媒设专业哦~'),(5,2,'新闻传播系','100000000024',''),(6,3,'机械自动化','100000000035',''),(7,3,'核动力与能源','100000000040',''),(8,5,'应用数学','100000000055',''),(9,5,'基础数学','100000000060',''),(10,4,'建筑环境与设备工程','',''),(11,4,'船舶与海洋工程','100000000050','');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `stu_id` varchar(32) NOT NULL,
  `stu_class` varchar(32) NOT NULL,
  `stu_num` char(12) NOT NULL,
  `stu_name` varchar(50) NOT NULL,
  `stu_sex` enum('男','女') NOT NULL,
  `stu_birthday` date DEFAULT NULL,
  `stu_grade` int(11) NOT NULL,
  `stu_degree` enum('本科','硕士','博士') NOT NULL,
  `stu_phone` char(11) DEFAULT NULL,
  `stu_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`stu_id`),
  UNIQUE KEY `stu_num_UNIQUE` (`stu_num`),
  KEY `s_c_idx` (`stu_class`),
  KEY `s_g_idx` (`stu_grade`),
  CONSTRAINT `s_c` FOREIGN KEY (`stu_class`) REFERENCES `class` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `s_g` FOREIGN KEY (`stu_grade`) REFERENCES `grade` (`grade_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('00b2bafea0b011e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000030','王五','男','2000-01-21',6,'本科','',''),('050bed99ab6e11e9976254ab3aa53988','2d69671dab6111e9976254ab3aa53988','514100000004','张一元','男','2000-03-09',4,'硕士','',''),('06c36f7ba0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000043','王淑华','女','1998-01-21',4,'硕士','',''),('07bf6df0a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000002','张以','男','2000-01-08',4,'硕士','',''),('08cf3c8cab7011e9976254ab3aa53988','569b0b4cab6111e9976254ab3aa53988','512100000009','陈九九','男','2000-01-01',2,'博士','',''),('0aad7904a0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000042','刘鹗','女','2000-02-01',6,'本科','',''),('0b2273c3a13711e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000070','施浩','男','2000-05-25',6,'本科','',''),('0b5fcb8ea0b611e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000059','何级','男','1997-01-15',4,'硕士','',''),('0bb9a07ea0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000031','王五为','男','2000-01-25',6,'本科','',''),('0be21034a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000072','张世豪','男','1998-01-15',4,'硕士','',''),('0eb820baa0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000002','金晶','女','1997-01-03',2,'博士','',''),('0f586643a13811e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000070','蔡红','女','1998-05-22',4,'硕士','',''),('1064d885a0b511e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000057','李强奇','男','2000-04-12',6,'本科','',''),('1227ad67a0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000033','李少','男','1997-03-01',4,'硕士','',''),('1ce6f1b4a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000073','张诗涵','女','1997-01-23',4,'硕士','',''),('1d658b2ea0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000020','沈一','男','2000-01-07',6,'本科','',''),('1db693eeab7011e9976254ab3aa53988','569b0b4cab6111e9976254ab3aa53988','512100000010','丁一宇','男','2000-01-01',2,'博士','',''),('1ee47bd5a0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000044','王思思','女','1998-03-06',4,'硕士','',''),('21afd808ab6e11e9976254ab3aa53988','2d69671dab6111e9976254ab3aa53988','514100000005','周晓','男','2000-06-15',4,'硕士','',''),('22eaa51fa0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000032','王二','男','2000-01-28',6,'本科','',''),('2321f084a0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000043','刘三','男','2000-03-02',6,'本科','',''),('234f39f7a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000061','周有','男','2000-01-25',6,'本科','',''),('236d50daa0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000021','严一','男','1998-01-01',4,'硕士','',''),('23d15fcba13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000061','张琪','男','1998-02-24',4,'硕士','',''),('24365021a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000003','张无','男','2000-04-06',4,'硕士','',''),('248ee755a0b511e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000058','胡宇','男','2000-01-20',6,'本科','',''),('27c55e54a0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000035','王昊','男','1998-01-30',4,'硕士','',''),('287ef423a13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000071','刘琦琦','男','2000-01-27',6,'本科','',''),('2aa7d90ea0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000001','李四','男','1999-01-28',6,'本科','',''),('2b9bb03ea0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000003','蔡蔡','女','1997-02-13',2,'博士','',''),('2f01dd3ca13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000074','刘诗涵','女','1997-01-23',4,'硕士','',''),('3282ab43a0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000045','李静','女','1998-03-19',4,'硕士','',''),('37604388a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000022','严而','男','1998-01-22',4,'硕士','',''),('3778715fa0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000044','李思思','男','2000-03-15',6,'本科','',''),('3d519884a0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000033','李三','男','2000-01-19',6,'本科','',''),('3d939faea0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000021','张海','男','2000-02-10',6,'本科','',''),('3e441616a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000062','庄彩虹','男','1998-01-01',4,'硕士','',''),('3e4f2a39a0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000036','李海洋','男','1998-02-19',4,'硕士','',''),('4161fa02a0b511e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000059','万方','男','2000-04-13',6,'本科','',''),('42c8a60ea13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000072','吴欢','男','2000-01-06',6,'本科','',''),('433fd404a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000005','王艳','女','1999-11-07',4,'硕士','',''),('4450cedca13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000075','王浩浩','男','1997-02-14',4,'硕士','',''),('46c50b54a0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000002','王毅','男','2000-02-24',6,'本科','',''),('47476c8ea0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000004','丁新','男','1997-01-09',2,'博士','',''),('4962109ba0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000023','李三','男','1998-03-20',4,'硕士','',''),('4ad4b079a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000062','张好','男','2000-02-01',6,'本科','',''),('4bf2ff80a0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000046','刘浏','男','1997-02-14',4,'硕士','',''),('4c3f4116a09911e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516030910428','王大发','男','1998-01-09',6,'本科','13681864363',''),('4c62680fa0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000045','李武','男','2000-03-05',6,'本科','',''),('53031cdda0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000034','王世豪','男','2000-03-25',6,'本科','',''),('55995a1ca13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000063','刘珊','女','1997-01-16',4,'硕士','',''),('55b391c8a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000076','张晓雷','男','1998-01-21',4,'硕士','',''),('563c3402a0b511e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000060','王国良','男','2000-05-25',6,'本科','',''),('582ed5efa0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000037','丁奇','男','1998-04-17',4,'硕士','',''),('5a2ce6aeab6e11e9976254ab3aa53988','22223114ab6111e9976254ab3aa53988','518100000001','陈以然','男','2000-01-01',6,'本科','',''),('5a95eba3a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000022','李飒','男','2000-03-02',6,'本科','',''),('5b89c850ab6f11e9976254ab3aa53988','48b48061ab6111e9976254ab3aa53988','512100000001','章子怡','男','2000-01-01',2,'博士','',''),('5e375fc1a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000024','王室','男','1998-04-01',4,'硕士','',''),('5f28a59ca0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000046','王力','男','2000-04-06',6,'本科','',''),('5fec088da0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000047','沈七七','男','1998-01-16',4,'硕士','',''),('6196aaa5a0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000005','金高','男','1997-04-10',2,'博士','',''),('619fd3aaa13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000073','陈珊珊','女','2000-03-23',6,'本科','',''),('62ab07c2a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000063','庄斯','男','2000-02-23',6,'本科','',''),('63322000a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000007','金艳','女','1998-01-14',4,'硕士','',''),('65f8192ca0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000003','丁一','男','1999-12-29',6,'本科','',''),('6735cb18a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000054','刘诗诗','女','1997-01-21',4,'硕士','',''),('69064e0ba0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000035','陈武','男','2000-03-16',6,'本科','',''),('6fd44a35a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000077','徐洪亮','男','1998-07-17',4,'硕士','',''),('7010ac10ab6e11e9976254ab3aa53988','22223114ab6111e9976254ab3aa53988','516100000002','王思思','男','2000-01-01',6,'本科','',''),('707ee136a0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000051','吴以','男','1998-01-22',4,'硕士','',''),('71d26245a0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000038','张亮','男','1998-03-20',4,'硕士','',''),('72bc4f56a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000023','高科技','男','2000-01-01',6,'本科','',''),('738b6144a13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000074','陈思','男','2000-03-16',6,'本科','',''),('74dc2321ab6f11e9976254ab3aa53988','48b48061ab6111e9976254ab3aa53988','512100000002','李冰','男','2000-01-01',2,'博士','',''),('783b66d4a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000025','王午','男','1998-05-01',4,'硕士','',''),('787f34bea0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000048','胡有','男','1998-01-02',4,'硕士','',''),('78d2e2bba13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000064','武含','男','2000-04-01',6,'本科','',''),('795f0146a0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000006','陈柳','女','1997-04-01',2,'博士','',''),('7cc7d07fa0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000036','丁六','男','2000-01-06',6,'本科','',''),('7cd2789aa0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000047','司马一','男','2000-02-06',6,'本科','',''),('7cd5115aa0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000004','丁二','男','1999-11-22',6,'本科','',''),('80baf085a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000008','陈依依','男','1998-02-26',4,'硕士','',''),('836be9e6ab6f11e9976254ab3aa53988','48b48061ab6111e9976254ab3aa53988','512100000003','李冰冰','男','2000-01-01',2,'博士','',''),('83a73289a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000065','吴山','男','1998-02-27',4,'硕士','',''),('83cbdb13a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000078','李亮','男','1997-04-26',4,'硕士','',''),('8492a2fca13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000075','刘海','男','2000-03-23',6,'本科','',''),('8636dbeda0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000039','黄金','女','1998-07-16',4,'硕士','',''),('8793f910ab6e11e9976254ab3aa53988','22223114ab6111e9976254ab3aa53988','516100000003','李佳琪','男','2000-01-01',6,'本科','',''),('8c0c3316a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000024','高山','男','2000-01-01',6,'本科','',''),('8c155b12a0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000049','胡二','男','1998-01-22',4,'硕士','',''),('8d9a8beca0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000052','胡已','男','1998-03-12',4,'硕士','',''),('90b47003a0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000048','司马为','男','2000-01-01',6,'本科','',''),('90e16c82a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000065','李莉','女','2000-06-22',6,'本科','',''),('9133976ca0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000007','蔡依林','女','1997-05-08',2,'博士','',''),('938a2d5fa0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000026','陆小刘','男','1998-01-03',4,'硕士','',''),('94589f33a0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000037','金小七','男','2000-03-09',6,'本科','',''),('9802a55dab6f11e9976254ab3aa53988','48b48061ab6111e9976254ab3aa53988','512100000004','周一其','男','2000-01-01',2,'博士','',''),('985fe1faa13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000076','张乐乐','男','2000-05-25',6,'本科','',''),('9a15592ba0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000040','张张','男','1998-08-13',4,'硕士','',''),('9b4c56a1ab6e11e9976254ab3aa53988','22223114ab6111e9976254ab3aa53988','516100000004','严艺其','男','2000-01-01',6,'本科','',''),('9bb68f76a13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000079','陈梁','男','1998-01-22',4,'硕士','',''),('9c3ede86a7ce11e98b0654ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516030910000','蔡徐坤111','男','2000-01-01',6,'本科','',''),('9c876fa9a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000066','李琪琪','男','1998-06-25',4,'硕士','',''),('9dc1c62da0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000005','黄豆','女','1999-05-01',6,'本科','',''),('a055fdb0a0b411e991dd54ab3aa53988','4da07558a0a711e991dd54ab3aa53988','514000000050','沈烈','男','1998-05-21',4,'硕士','',''),('a25d06f4a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000066','张晓波','男','2000-01-01',6,'本科','',''),('a2db77a5a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000009','陈刚','男','2000-03-16',4,'硕士','',''),('a3ba378fa0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000053','胡珊珊','女','1998-01-15',4,'硕士','',''),('a5d6728fa0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000049','欧阳久','男','2000-04-19',6,'本科','',''),('a69aadc5a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000025','王皓','男','2000-01-07',6,'本科','',''),('a8557e33a0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000038','李发','男','2000-05-12',6,'本科','',''),('ab799fd4a13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000077','李科','男','2000-03-20',6,'本科','',''),('ac9ce2d3a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000027','陆小七','女','1998-01-09',4,'硕士','',''),('adb91374a0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000008','王凯','男','1997-04-10',2,'博士','',''),('ae35fe83ab6e11e9976254ab3aa53988','22223114ab6111e9976254ab3aa53988','516100000005','金山','男','2000-01-01',6,'本科','',''),('aeedb854ab6f11e9976254ab3aa53988','48b48061ab6111e9976254ab3aa53988','512100000005','范小青','男','2000-01-01',2,'博士','',''),('b3eee166ab6d11e9976254ab3aa53988','2d69671dab6111e9976254ab3aa53988','514100000001','丁丁','男','2000-01-11',4,'硕士','',''),('b56ef9dea13911e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000080','刘杰林','男','1998-07-23',4,'硕士','',''),('b6c85668a0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000055','李武武','男','1998-04-17',4,'硕士','',''),('b72c19b2a0b411e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000051','王刘','男','2000-01-12',6,'本科','',''),('b7abd2eda0ac11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000006','王刚刚','男','2000-03-22',6,'本科','',''),('b7bae959a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000067','金晶晶','女','1997-03-13',4,'硕士','',''),('b88c7835a0b211e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000041','郭靖金','男','1999-01-29',4,'硕士','',''),('b9a25900a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000027','陈奇','男','2000-05-05',6,'本科','',''),('bbbd9001a0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000039','王久','男','2000-01-07',6,'本科','',''),('bcd76984a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000067','施与','男','2000-03-15',6,'本科','',''),('bd93115ba13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000078','陈昊','男','2000-02-21',6,'本科','',''),('bdec0ac391ca11e99f1554ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516030910429','张镇睿','男','1998-12-23',6,'本科','13681864362','asd'),('be19bd3ca0b311e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000050','王洋','男','2000-01-01',6,'本科','',''),('bf07847da0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000010','张珊','女','1998-01-30',4,'硕士','',''),('c6d8c69ba0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000028','李佳琪','女','1998-02-12',4,'硕士','',''),('c7691ce8a0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000009','王久','男','1997-05-01',2,'博士','',''),('c86d19f6a0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000056','张维','男','1997-03-14',4,'硕士','',''),('cc5a952aa0b111e991dd54ab3aa53988','66e8283a91ca11e99f1554ab3aa53988','516000000040','黄伟','男','2000-01-14',6,'本科','',''),('cdcc8b58a0b411e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000052','王工','男','2000-01-01',6,'本科','',''),('ce43007da0a911e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516030910430','恶趣味群无','男','2000-01-01',6,'本科','',''),('cf02d688a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000028','李顺','男','2000-01-04',6,'本科','',''),('d03c1333a13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000079','蔡久久','男','2000-01-26',6,'本科','',''),('d3ffa526ab6f11e9976254ab3aa53988','569b0b4cab6111e9976254ab3aa53988','512100000006','王宝强','男','2000-01-01',2,'博士','',''),('d4385f02a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000068','陈黎','男','1998-01-16',4,'硕士','',''),('d47263aaab6d11e9976254ab3aa53988','2d69671dab6111e9976254ab3aa53988','514100000002','王一','男','2000-01-01',4,'硕士','',''),('d4c48063a0ad11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000011','沈斌斌','男','2000-01-01',4,'硕士','',''),('d4d24797a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000068','刘琦','男','2001-01-25',6,'本科','',''),('d9832ee2a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000029','郭久','男','1998-01-10',4,'硕士','',''),('dcc161eaa0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000057','王小强','男','1998-02-19',4,'硕士','',''),('e1282fd0a0ae11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000011','李浩','男','1997-06-04',2,'博士','',''),('e1e29389a0b411e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000053','王三','男','2000-01-12',6,'本科','',''),('e311f880a0b311e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000042','李思是','男','1998-01-19',4,'硕士','',''),('e3fde797a13811e991dd54ab3aa53988','7bb58cc1a0a711e991dd54ab3aa53988','516000000080','胡丽丽','女','2000-07-14',6,'本科','',''),('e4743e7fa0b111e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000031','黄石','男','1998-01-06',4,'硕士','',''),('e56307eeab6f11e9976254ab3aa53988','569b0b4cab6111e9976254ab3aa53988','512100000007','刘华','男','2000-01-01',2,'博士','',''),('e8478ce0a0af11e991dd54ab3aa53988','4039c6fe91ca11e99f1554ab3aa53988','516000000029','张珊','女','2000-04-06',6,'本科','',''),('e92a7a14ab6d11e9976254ab3aa53988','2d69671dab6111e9976254ab3aa53988','514100000003','丁丁一','男','2000-01-01',4,'硕士','',''),('ebc481e8a0ac11e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000001','丁丁','男','2000-01-05',4,'硕士','',''),('edf64e07a0ab11e991dd54ab3aa53988','e00d47d58ce811e98a0b54ab3aa53988','516000000000','张三','男','2000-01-04',6,'本科','',''),('ee5661d5a0ad11e991dd54ab3aa53988','9ee003b5a0a611e991dd54ab3aa53988','512000000001','张张','男','1997-01-01',2,'博士','',''),('ee68c7c0a0b511e991dd54ab3aa53988','e7bea2f7a0a611e991dd54ab3aa53988','514000000058','李芳芳','女','1997-02-20',4,'硕士','',''),('ee915c32a0b011e991dd54ab3aa53988','bccf84aaa0a611e991dd54ab3aa53988','514000000030','张珊珊','女','1997-02-21',4,'硕士','',''),('ef5fb686a13611e991dd54ab3aa53988','6382641aa0a711e991dd54ab3aa53988','516000000069','周杰','男','2000-03-29',6,'本科','',''),('f25099d7a13711e991dd54ab3aa53988','6fe9c465a0a711e991dd54ab3aa53988','514000000069','徐工','男','1998-05-29',4,'硕士','',''),('f2c81f5da0b211e991dd54ab3aa53988','0824d28fa0a711e991dd54ab3aa53988','516000000041','刘一','男','2000-01-13',6,'本科','',''),('f68cfcdca13811e991dd54ab3aa53988','85e4be3ea0a711e991dd54ab3aa53988','514000000071','刘毅','男','1997-01-16',4,'硕士','',''),('f76a8883ab6f11e9976254ab3aa53988','569b0b4cab6111e9976254ab3aa53988','512100000008','张宇','男','2000-01-01',2,'博士','',''),('fa0c6485a0b411e991dd54ab3aa53988','d6384ce7a0a611e991dd54ab3aa53988','516000000054','丁三三','男','2000-02-17',6,'本科','',''),('fc0dddb7a0b111e991dd54ab3aa53988','c86fae77a0a611e991dd54ab3aa53988','514000000032','王世虎','男','1998-01-27',4,'硕士','',''),('ff5a762ba0a911e991dd54ab3aa53988','437757baa0a611e991dd54ab3aa53988','514000000000','qwq','男','2000-01-01',4,'硕士','','');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `tea_id` varchar(32) NOT NULL,
  `tea_major` int(11) NOT NULL,
  `tea_num` char(12) NOT NULL,
  `tea_name` varchar(50) NOT NULL,
  `tea_sex` enum('男','女') NOT NULL,
  `tea_birthday` date DEFAULT NULL,
  `tea_title` int(11) NOT NULL,
  `tea_phone` varchar(11) DEFAULT NULL,
  `tea_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tea_id`),
  UNIQUE KEY `tea_num_UNIQUE` (`tea_num`),
  KEY `t_m_idx` (`tea_major`),
  KEY `t_t_idx` (`tea_title`),
  CONSTRAINT `t_m` FOREIGN KEY (`tea_major`) REFERENCES `major` (`major_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `t_t` FOREIGN KEY (`tea_title`) REFERENCES `title` (`title_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('055f59a6ab7411e9976254ab3aa53988',9,'100000000060','姜轩','男','1980-01-01',1,'',''),('071086e6ab7311e9976254ab3aa53988',10,'100000000046','李琦','男','1980-01-01',2,'',''),('08718354ab7211e9976254ab3aa53988',5,'100000000031','王佳琪','女','1980-01-01',1,'',''),('10ea9150ab7111e9976254ab3aa53988',2,'100000000014','王琪瑞','男','1980-01-01',3,'',''),('15de90eaab7211e9976254ab3aa53988',5,'100000000032','赵明华','男','1980-01-01',2,'',''),('16de87ddab7411e9976254ab3aa53988',9,'100000000061','秦逸','男','1980-01-01',2,'',''),('1da1f4e1ab7311e9976254ab3aa53988',10,'100000000047','朱奕','男','1980-01-01',3,'',''),('28a84c5dab7411e9976254ab3aa53988',9,'100000000062','张家加','男','1980-01-01',3,'',''),('2a735154ab7111e9976254ab3aa53988',3,'100000000016','刘萱萱','女','1980-01-01',1,'',''),('2a870c0cab7211e9976254ab3aa53988',5,'100000000033','赵海','男','1980-01-01',3,'',''),('2cb3d632ab7311e9976254ab3aa53988',10,'100000000048','沈军','男','1980-01-01',4,'',''),('35a930f7a05911e991dd54ab3aa53988',1,'100000000001','郭靖','男','1980-01-01',1,'',''),('3812d3f2ab7111e9976254ab3aa53988',3,'100000000017','卢晓琪','女','1980-01-01',2,'',''),('3c4862d9ab7311e9976254ab3aa53988',10,'100000000049','沈佳俊','男','1980-01-01',5,'',''),('3d844094ab7411e9976254ab3aa53988',9,'100000000063','李怡然','女','1980-01-01',4,'',''),('3fca74fbab7211e9976254ab3aa53988',5,'100000000034','胡耀义','男','1980-01-01',2,'',''),('48ad1b10ab7111e9976254ab3aa53988',3,'100000000018','朱凯文','男','1980-01-01',3,'',''),('4b7398bbab7311e9976254ab3aa53988',11,'100000000050','沈力','男','1980-01-01',1,'',''),('4cb2a754a09a11e991dd54ab3aa53988',2,'100000000002','郭捷','女','1960-11-01',1,'13681864376',''),('52cfb91dab7411e9976254ab3aa53988',9,'100000000064','刘倩元','女','1980-01-01',1,'',''),('53574c43ab7211e9976254ab3aa53988',6,'100000000035','胡已于','男','1980-01-01',1,'',''),('57959572ab7111e9976254ab3aa53988',3,'100000000020','张瑞','男','1980-01-01',4,'',''),('5a6575d4ab7011e9976254ab3aa53988',1,'100000000003','丁宇扬','男','1980-01-01',1,'',''),('5ef54410ab7211e9976254ab3aa53988',6,'100000000036','倪家豪','男','1980-01-01',2,'',''),('634a2eaaab7311e9976254ab3aa53988',11,'100000000051','车力','男','1980-01-01',2,'',''),('6ae5a866ab7111e9976254ab3aa53988',3,'100000000022','张怡然','女','1980-01-01',5,'',''),('6c45cb94ab7211e9976254ab3aa53988',6,'100000000037','倪浩','男','1980-01-01',3,'',''),('776eb003ab7311e9976254ab3aa53988',11,'100000000052','张雨欣','男','1980-01-01',3,'',''),('7af3e359ab7211e9976254ab3aa53988',6,'100000000038','黄一凡','男','1980-01-01',4,'',''),('8421bf21ab7111e9976254ab3aa53988',4,'100000000023','杨乐天','男','1980-01-01',1,'',''),('85c7a0b7ab7011e9976254ab3aa53988',1,'100000000004','方才','男','1980-01-01',2,'',''),('8988a819ab7211e9976254ab3aa53988',6,'100000000039','王凡','男','1980-01-01',5,'',''),('8b6ec37fab7311e9976254ab3aa53988',11,'100000000053','卢一方','男','1980-01-01',4,'',''),('97dd1961ab7311e9976254ab3aa53988',11,'100000000054','陆一飞','男','1980-01-01',5,'',''),('99915d6eab7111e9976254ab3aa53988',4,'100000000027','陈乐','男','1980-01-01',2,'',''),('a1459854ab7211e9976254ab3aa53988',7,'100000000040','江涛','男','1980-01-01',1,'',''),('a794a788ab7011e9976254ab3aa53988',1,'100000000005','王宇轩','男','1980-01-01',3,'',''),('aa57e23fab7111e9976254ab3aa53988',5,'100000000024','陈阳','男','1980-01-01',3,'',''),('ab5b256eab7311e9976254ab3aa53988',8,'100000000055','岳依琳','女','1980-01-01',1,'',''),('b34d0919ab7211e9976254ab3aa53988',7,'100000000041','姜懿航','女','1980-01-01',2,'',''),('ba2bf854ab7311e9976254ab3aa53988',8,'100000000056','姚姚','男','1980-01-01',2,'',''),('bf5c945dab7011e9976254ab3aa53988',1,'100000000006','王俊华','男','1980-01-01',4,'',''),('c260f6e1ab7111e9976254ab3aa53988',4,'100000000025','李琪','女','1980-01-01',4,'',''),('c31367c8ab7211e9976254ab3aa53988',7,'100000000042','王志臣','男','1980-01-01',1,'',''),('cbd4816eab7311e9976254ab3aa53988',8,'100000000057','洪兰','女','1980-01-01',1,'',''),('d28f69c3ab7211e9976254ab3aa53988',7,'100000000043','刁玉鑫','女','1980-01-01',4,'',''),('d6d901bbab7111e9976254ab3aa53988',4,'100000000026','沈初','男','1980-01-01',5,'',''),('ddad38aaab7311e9976254ab3aa53988',8,'100000000058','赵晶','男','1980-01-01',3,'',''),('dde6dbe1ab7011e9976254ab3aa53988',2,'100000000011','王浩林','男','1980-01-01',1,'',''),('e3fb9803ab7211e9976254ab3aa53988',7,'100000000044','甘雨','男','1980-01-01',4,'',''),('ee2208eeab7311e9976254ab3aa53988',8,'100000000059','赵晗','男','1980-01-01',1,'',''),('ee7db926ab7111e9976254ab3aa53988',4,'100000000028','赵启元','男','1980-01-01',1,'',''),('efed8b72ab7011e9976254ab3aa53988',2,'100000000012','李佳琪','女','1980-01-01',2,'',''),('f9f790fbab7211e9976254ab3aa53988',10,'100000000045','李欣','男','1980-01-01',1,'',''),('ff5afdccab7011e9976254ab3aa53988',2,'100000000013','范冰冰','女','1980-01-01',3,'','');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `title`
--

DROP TABLE IF EXISTS `title`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `title` (
  `title_id` int(11) NOT NULL AUTO_INCREMENT,
  `title_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `title_remark` varchar(500) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`title_id`),
  UNIQUE KEY `title_name_UNIQUE` (`title_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `title`
--

LOCK TABLES `title` WRITE;
/*!40000 ALTER TABLE `title` DISABLE KEYS */;
INSERT INTO `title` VALUES (1,'教授',NULL),(2,'副教授',NULL),(3,'研究员',NULL),(4,'副研究员',NULL),(5,'高级工程师',NULL);
/*!40000 ALTER TABLE `title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` varchar(32) NOT NULL,
  `user_name` varchar(12) NOT NULL,
  `user_nickname` varchar(50) NOT NULL,
  `user_password` varchar(50) NOT NULL,
  `user_identity` enum('学生','教师','管理员') NOT NULL,
  `user_icon` varchar(500) DEFAULT NULL,
  `user_email` varchar(100) NOT NULL,
  `user_phone` char(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_name_UNIQUE` (`user_name`),
  UNIQUE KEY `user_email_UNIQUE` (`user_email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('1adb593211b14216bcf9d009dd51cad2','516030910429','酷乐酱','Bn5Dr6xcBNeLzhRkwp+6SA==','学生','/static/custom/img/uploadUserIcon/user_default_icon.jpg','13681864361@sjtu.edu.cn','13681864361'),('82d583c0b3cc4d6a87fd5460c87c0f05','000000000000','管理员1','Z5OtYgV5ia7CSsBmWful8w==','管理员','/static/custom/img/uploadUserIcon/user_default_icon.jpg','929703621@qq.com','13681864361'),('eddb5442a19d4d1186fda4bf45c65c99','100000000001','余勇老师','unv/emQ2LFaEzNvxHNznvA==','教师','/static/custom/img/uploadUserIcon/user_default_icon.jpg','3140064742@qq.com','12345678901');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'mydatabase2'
--

--
-- Dumping routines for database 'mydatabase2'
--
/*!50003 DROP PROCEDURE IF EXISTS `count_stu_percent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`%` PROCEDURE `count_stu_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
BEGIN
	case type_
		when 'allCollege' then
			select count(*) as total,c2.college_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college group by c2.college_id;
		when 'majorUnderCollege' then
			select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college and c2.college_name=college_name_ group by m1.major_id;
		when 'allMajor' then
			select count(*) as total,m1.major_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id group by m1.major_id;
		when 'classUnderMajor' then
			select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id and m1.major_name=major_name_ group by c1.class_id;
		when 'allClass' then
			select count(*) as total,c1.class_name as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id group by c1.class_id;
		when 'grade' then
			select count(*) as total,g1.grade_name as commonName from student s1 inner join grade g1 on s1.stu_grade=g1.grade_id group by g1.grade_id;
		when 'allCollegeByStuDegree' then
			select count(*) as total,s1.stu_degree as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id inner join college c2 on c2.college_id=m1.major_college and c2.college_name=college_name_ group by s1.stu_degree;
		when 'majorUnderCollegeByStuDegree' then
			select count(*) as total,s1.stu_degree as commonName from student s1 inner join class c1 on s1.stu_class=c1.class_id inner join major m1 on c1.class_major=m1.major_id and m1.major_name=major_name_ group by s1.stu_degree;
        when 'wholeSchoolByStuDegree' then
			select count(*) as total,s1.stu_degree as commonName from student s1 group by s1.stu_degree;
        else
			select 0 as total,'' as commonName;
    end case;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `count_tea_percent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `count_tea_percent`(IN type_ varchar(50), IN college_name_ varchar(50), IN major_name_ varchar(50))
begin
	case type_
		when 'allCollegeByTeaTitle' then
			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id inner join major m1 on t1.tea_major=m1.major_id inner join college c1 on m1.major_college=c1.college_id and c1.college_name=college_name_ group by t1.tea_title;
		when 'wholeSchoolByTeaTitle' then
			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id group by t1.tea_title;
		when 'allMajorByTeaTitle' then
			select count(*) as total,t2.title_name as commonName from teacher t1 inner join title t2 on t1.tea_title=t2.title_id inner join major m1 on t1.tea_major=m1.major_id and m1.major_name=major_name_ group by t1.tea_title;
        else
			select 0 as total,'' as commonName;
    end case;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-07-25 17:18:09
