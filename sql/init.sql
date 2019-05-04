-- MySQL dump 10.13  Distrib 8.0.16, for Win64 (x86_64)
--
-- Host: localhost    Database: mydatabase
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
  `class_id` varchar(10) NOT NULL,
  `major_id` varchar(10) DEFAULT NULL,
  `class_name` varchar(50) NOT NULL,
  `class_stu_num` decimal(6,0) DEFAULT NULL,
  `class_moni_id` varchar(12) DEFAULT NULL,
  `class_tea_id` varchar(12) DEFAULT NULL,
  `class_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`class_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('1','1','2016级计算机1班',4,'516030910428','100000000016','计算机专业最好的班级222'),('10','3','2014级信安1班',2,'','100000000020',''),('11','1','2016级计算机3班',1,'516030910452','100000000002',''),('12','4','2016级新闻传播2班',3,'516030910445','100000000008','新闻2班'),('13','2','2016级软件2班',4,'516030910426','100000000004',''),('14','5','2016级影视2班',1,'516030910446','','123131'),('15','7','2016级核1班',1,'516030910461','',''),('16','8','2016级经济1班',0,'','',''),('17','8','2016级经济2班',0,'','','经济2班'),('18','9','2016级管理1班',0,'','',''),('19','10','2016级会计1班',0,'','',''),('2','1','2014级计算机1班',5,'514030910425','100000000017','瓜皮班级'),('20','11','2016级应用数学1班',0,'','',''),('3','2','2016级软件1班',5,'516030910430','100000000003','软件工程最好的班级'),('4','3','2016级信安1班',5,'516030910434','100000000005','渣渣班级'),('5','4','2016级新闻传播1班',4,'516030910438','100000000007',NULL),('6','5','2016级影视1班',4,'516030910440','100000000009','有好多小姐姐'),('7','6','2016级机械自动化1班',2,'516030910457','',''),('8','6','2016级机械自动化2班',2,'','',''),('9','1','2016级计算机2班',4,'516030910427','100000000001','123');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `college`
--

DROP TABLE IF EXISTS `college`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `college` (
  `college_id` varchar(10) NOT NULL,
  `college_name` varchar(50) NOT NULL,
  `college_stu_num` decimal(6,0) DEFAULT NULL,
  `college_major_num` decimal(6,0) DEFAULT NULL,
  `college_property` varchar(50) DEFAULT NULL,
  `college_tea_id` varchar(12) DEFAULT NULL,
  `college_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`college_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `college`
--

LOCK TABLES `college` WRITE;
/*!40000 ALTER TABLE `college` DISABLE KEYS */;
INSERT INTO `college` VALUES ('1','电子信息与电气工程学院',30,3,'工科','100000000001','这是全校人数最多的学院'),('2','媒体与设计学院',12,2,'文科','100000000009','这是女生比例最高的学院'),('3','机械动力与工程学院',5,2,'工科','','男生太多了'),('4','安泰经济与管理学院',0,3,'文科','','小姐姐好多'),('5','数学科学学院',0,1,'理科','','');
/*!40000 ALTER TABLE `college` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `major`
--

DROP TABLE IF EXISTS `major`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `major` (
  `major_id` varchar(10) NOT NULL,
  `college_id` varchar(10) DEFAULT NULL,
  `major_name` varchar(50) NOT NULL,
  `major_stu_num` decimal(6,0) DEFAULT NULL,
  `major_class_num` decimal(6,0) DEFAULT NULL,
  `major_tea_id` varchar(12) DEFAULT NULL,
  `major_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`major_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `major`
--

LOCK TABLES `major` WRITE;
/*!40000 ALTER TABLE `major` DISABLE KEYS */;
INSERT INTO `major` VALUES ('1','1','计算机科学',14,4,'100000000001','电院排名第一的专业'),('10','4','会计学',0,1,'',''),('11','5','应用数学',0,1,'',''),('2','1','软件工程',9,2,'100000000003','面向就业专业'),('3','1','信息安全',7,2,'100000000005','电院排名第二的专业'),('4','2','新闻与传播系',7,2,NULL,'媒设最热门的专业'),('5','2','电影电视系',5,2,'100000000009','教学质量很高'),('6','3','机械与自动化',4,2,'',''),('7','3','核科学与工程',1,1,'',''),('8','4','经济学',0,2,'',''),('9','4','管理学',0,1,'','');
/*!40000 ALTER TABLE `major` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `student` (
  `stu_id` varchar(12) NOT NULL,
  `class_id` varchar(10) DEFAULT NULL,
  `stu_name` varchar(50) NOT NULL,
  `stu_sex` varchar(2) DEFAULT NULL,
  `stu_age` decimal(3,0) DEFAULT NULL,
  `stu_grade` varchar(50) DEFAULT NULL,
  `stu_degree` varchar(50) DEFAULT NULL,
  `stu_phone` varchar(11) DEFAULT NULL,
  `stu_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`stu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES ('514030910424','2','王润','女',24,'2014级','硕士','12233334444',''),('514030910425','2','卢本伟','男',23,'2014级','硕士','13681864363','挂b'),('514030910426','2','骚猪','男',23,'2014级','硕士','13681864363','pdd'),('514030910427','2','渣渣辉','男',23,'2014级','硕士','18812341235','我系渣渣辉'),('514030910428','2','蔡徐坤','男',23,'2014级','硕士','18812341234','鸡你太美，2014级计算机1班班长,,,,,,11111'),('514030910429','10','高云翔','男',24,'2014级','硕士','13422223333',''),('514030910430','10','牛小涵','女',24,'2014级','硕士','13681864376',''),('516030910422','13','王刚','男',22,'2016级','本科','13681864361',''),('516030910423','13','童永刚','男',22,'2016级','本科','13445546556',''),('516030910424','13','张甜甜','女',22,'2016级','本科','13688990000',''),('516030910425','3','王生','女',22,'2016级','本科','12154749110','报警了'),('516030910426','4','金大王','男',23,'2016级','本科','13681864379',''),('516030910427','9','金小贝','男',21,'2016级','本科','13681864361','是小贝啊'),('516030910428','1','金之贇','男',21,'2016级','本科','13681864361','我是酷乐酱'),('516030910429','1','王小凡','女',20,'2016级','本科','13681864362','2016级计算机1班班长'),('516030910430','3','李天宇','男',21,'2016级','本科','13681864363','2016级软件1班班长'),('516030910431','3','朱佩瑶','女',20,'2016级','本科','13681864364',NULL),('516030910432','3','刘庆','男',22,'2016级','本科','13681864365',NULL),('516030910433','3','高励庆','女',20,'2016级','本科','13681864366',NULL),('516030910434','4','张崴城','男',21,'2016级','本科','13681864367','2016级信安1班班长'),('516030910435','4','谢飞','男',21,'2016级','本科','13681864368',NULL),('516030910436','4','张振瑞','男',21,'2016级','本科','13681864369',NULL),('516030910437','4','胡昶宇','男',21,'2016级','本科','13681864370',NULL),('516030910438','5','陈丽丽','女',21,'2016级','本科','13681864371','2016级新闻传播1班班长'),('516030910439','5','杜雪晴','女',21,'2016级','本科','13681864372',NULL),('516030910440','6','许紫云','女',22,'2016级','本科','13681864373','2016级影视1班班长'),('516030910441','6','李想','女',21,'2016级','本科','13681864374',NULL),('516030910442','6','袁楚楚','女',22,'2016级','本科','13681864375',NULL),('516030910443','6','杨兰兰','女',21,'2016级','本科','13681864376','小姐姐哟！'),('516030910444','5','韩俊萍','女',22,'2016级','本科','13681864361',''),('516030910445','12','蒋雪倩','女',22,'2016级','本科','13681864376',''),('516030910446','14','顾玉蓓','女',22,'2016级','本科','12345123461',''),('516030910447','1','李毅','男',22,'2016级','本科','11122223333',''),('516030910448','1','刘仪伟','男',21,'2016级','本科','15565657777',''),('516030910449','9','范志远','男',21,'2016级','本科','11011001100',''),('516030910450','9','丁欣怡','女',22,'2016级','本科','13344445555','计算机的女生'),('516030910451','9','金梦阳','男',22,'2016级','本科','14454546767',''),('516030910452','11','李金铭','男',22,'2016级','本科','19099999999',''),('516030910453','13','王世杰','男',22,'2016级','本科','14433332222',''),('516030910454','5','王兆旖','男',21,'2016级','本科','15922221111',''),('516030910455','12','倪晨','男',22,'2016级','本科','16777889900',''),('516030910456','12','丁涛军','男',22,'2016级','本科','16677778888',''),('516030910457','7','成明','女',22,'2016级','本科','13681864376',''),('516030910458','7','初音未来','女',21,'2016级','本科','13681864363',''),('516030910459','8','钱志雨','男',22,'2016级','本科','13681864376',''),('516030910460','8','汤杰霖','男',23,'2016级','本科','13681864376',''),('516030910461','15','张晓栋','男',22,'2016级','本科','13681864376','');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `teacher` (
  `tea_id` varchar(12) NOT NULL,
  `major_id` varchar(10) DEFAULT NULL,
  `tea_name` varchar(50) NOT NULL,
  `tea_sex` varchar(2) DEFAULT NULL,
  `tea_age` decimal(3,0) DEFAULT NULL,
  `tea_phone` varchar(11) DEFAULT NULL,
  `tea_title` varchar(50) DEFAULT NULL,
  `tea_remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`tea_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('100000000001','1','张三师','男',47,'13681864361','教授','太强了'),('100000000002','1','李四师','男',50,'12345123452','副教授','人不错'),('100000000003','2','王五师','女',35,'12345123453','讲师','小姐姐老师'),('100000000004','2','金一师','男',38,'12345123454','副教授',NULL),('100000000005','3','高六师','女',30,'12345123455','讲师',NULL),('100000000006','3','李晓旭','男',46,'12345123456','副教授',NULL),('100000000007','4','张哈皮','男',55,'12345123457','教授',NULL),('100000000008','4','韩寒寒','女',45,'12345123458','讲师',NULL),('100000000009','5','李一一','女',36,'12345123459','讲师','可爱'),('100000000010','5','王天','女',53,'12345123460','教授','很有教学经验'),('100000000011','3','郭捷','女',44,'12345123461','教授','1'),('100000000012','2','黄征','男',38,'13681864363','副教授','1213131'),('100000000016','1','范磊','男',48,'13564646464','教授','数据结构老师'),('100000000017','1','李寿初','男',55,'18288889999','副教授',''),('100000000018','1','易萍','女',57,'13444445555','教授','123'),('100000000019','1','王悦天','女',51,'13681860000','讲师',''),('100000000020','3','姚立红','女',44,'17877776655','讲师',''),('100000000021','4','王朋','男',50,'15566662345','教授',''),('100000000022','4','董阳','男',55,'13681864363','副教授',''),('100000000023','6','崔东霞','女',45,'18812341234','副教授',''),('100000000024','6','于丽','女',50,'18812341234','教授',''),('100000000025','7','王军','男',44,'18812341234','讲师','');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `user_id` varchar(12) NOT NULL,
  `user_nickname` varchar(50) NOT NULL,
  `user_password` varchar(50) DEFAULT NULL,
  `user_identity` varchar(10) DEFAULT NULL,
  `user_icon` varchar(500) DEFAULT NULL,
  `user_email` varchar(100) DEFAULT NULL,
  `user_phone` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('100000000001','张老师','123456','教师','/static/uploadUserIcon/user_default_icon.jpg','3140064742@qq.com','12345123451'),('100000000002','李老师','1234567','教师','/static/uploadUserIcon/user_default_icon.jpg','1536524029@qq.com','12345123452'),('100000000003','王老师','1234567','教师','/static/uploadUserIcon/user_default_icon.jpg','929703622@qq.com','12345123453'),('514030910425','lbw','123456','学生','/static/uploadUserIcon/user_default_icon.jpg','92970321@qq.com','12345678901'),('514030910428','蔡徐坤222','123456','学生','/static/uploadUserIcon/user_default_icon.jpg','2419759134@qq.com','18812341234'),('514030910429','渣渣辉111','123456','学生','/static/uploadUserIcon/user_default_icon.jpg','1@qq.com','18812341235'),('516030910428','超级酷乐酱','123456','学生','/static/uploadUserIcon/516030910428_icon_2019_05_01_06_36_51.jpg','929703621@qq.com','13681864361'),('516030910429','王小凡1234','1234567','学生','/static/uploadUserIcon/user_default_icon.jpg','774018186@qq.com','13681864362'),('admin','管理员','admin','管理员','/static/uploadUserIcon/admin_icon_2019_05_03_08_27_52.jpg','13681864361@sjtu.edu.cn','13681864361');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-03  9:37:03
