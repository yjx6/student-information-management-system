-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: practical
-- ------------------------------------------------------
-- Server version	8.0.33

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admin` (
  `id` int NOT NULL,
  `username` varchar(15) NOT NULL,
  `password` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1002,'杨健新','123456'),(1003,'李鑫','123456'),(1103,'何廷君','88888888');
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `class` (
  `classteacher` varchar(10) NOT NULL,
  `classname` varchar(10) NOT NULL,
  `grade` varchar(10) NOT NULL,
  `number` int NOT NULL,
  `college` varchar(15) NOT NULL,
  PRIMARY KEY (`classteacher`),
  CONSTRAINT `classteacher` FOREIGN KEY (`classteacher`) REFERENCES `teacher` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `class`
--

LOCK TABLES `class` WRITE;
/*!40000 ALTER TABLE `class` DISABLE KEYS */;
INSERT INTO `class` VALUES ('小张','计科22102','大二',99,'计算机科学与工程学院'),('小杨','计科22101','大二',76,'计算机科学与工程学院'),('小陈','计科22103','大二',80,'计算机科学与工程学院');
/*!40000 ALTER TABLE `class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `course` (
  `cno` varchar(10) NOT NULL,
  `cname` varchar(10) NOT NULL,
  `teacher` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  `place` varchar(10) NOT NULL,
  PRIMARY KEY (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES ('22101','数据库原理','张雪峰','周一56节','新商科'),('22102','数据结构','王梅','周三34节','景行楼'),('22103','面向对象程序设计','阿玮','周四12节','知行楼'),('22104','数字电路','娜娜','周五12节','敦行楼'),('22105','离散数学','李光','周二34节','雅行楼'),('22106','控制工程','金利','周三34节','景行楼'),('22107','马克思主义原理','李五','周四78节','雅行楼'),('22108','C语言程序设计','王六','周一34节','新商科'),('22109','高等数学','宋浩','周二12节','知行楼');
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studentinfo`
--

DROP TABLE IF EXISTS `studentinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `studentinfo` (
  `id` varchar(15) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sex` varchar(2) NOT NULL,
  `age` int NOT NULL,
  `class` varchar(15) NOT NULL,
  `address` varchar(30) NOT NULL,
  `facial` varchar(10) NOT NULL,
  `time` varchar(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studentinfo`
--

LOCK TABLES `studentinfo` WRITE;
/*!40000 ALTER TABLE `studentinfo` DISABLE KEYS */;
INSERT INTO `studentinfo` VALUES ('198198','三个男孩','男',20,'计科22103','河北邯郸','团员','2022.09'),('2205006002','李四','男',19,'计科22101','宁夏银川','党员','2022.09'),('2205006003','王五','女',19,'计科22101','河北邯郸','群众','2022.09'),('2205006004','赵六','男',18,'计科22103','山东青岛','群众','2022.09'),('2205006005','宋七','男',20,'计科22102','山西晋城','党员','2022.09'),('2205006006','黑八','男',19,'计科22102','河北石家庄','群众','2022.09'),('2205006007','小九','女',19,'计科22101','河北唐山','团员','2022.09'),('2205006008','王一','女',22,'计科22103','山东淄博','入党积极分子','2022.09'),('2205006010','陈六','女',20,'计科22103','陕西宝鸡','群众','2022.10'),('2205006011','鲁班','男',20,'计科22101','浙江杭州','群众','2022.09'),('2205006012','韩信','男',21,'计科22101','浙江台州','群众','2022.09'),('2205006013','小乔瞧','女',20,'计科22102','河北石家庄','入党积极分子','2022.10'),('2205006014','李白','女',19,'计科22101','宁夏银川','团员','2022.10'),('2205006015','庄周','女',21,'计科22012','山东青岛','团员','2022.10'),('2205006016','西施洗','女',22,'计科22103','山西晋城','群众','2022.10'),('2205006017','孙尚香','女',22,'计科22103','陕西西安','团员','2022.10'),('2205006215','有人','男',19,'计科22103','河北邯郸','党员','2022.10'),('2205006216','唐僧','男',20,'计科22102','山东青岛','群众','2022.10'),('2205006217','安琪拉','女',20,'计科22102','广东深圳','群众','2022.09'),('2205006218','宫本武藏','男',19,'计科22103','云南大理','团员','2022.09'),('2205006219','后羿','男',20,'计科22103','江西南昌','团员','2022.09'),('2205006220','艾琳','女',20,'计科22103','四川成都','团员','2022.09'),('2206005221','长城炮','男',20,'计科22103','河北邯郸','团员','2022.09'),('2206005222','大力仑','男',20,'计科22103','河北邯郸','团员','2022.09'),('2206005223','赵云','男',20,'计科22102','河北邯郸','团员','2022.09'),('2206005224','云中','男',19,'计科22101','河北石家庄','团员','2022.09');
/*!40000 ALTER TABLE `studentinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `teacher` (
  `t_id` varchar(15) NOT NULL,
  `name` varchar(10) NOT NULL,
  `sex` varchar(10) NOT NULL,
  `academic` varchar(10) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `facial` varchar(10) DEFAULT NULL,
  `class` varchar(10) DEFAULT NULL,
  `password` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`t_id`),
  KEY `teacher_idx` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES ('2001','小杨','男','硕士研究生','18330068959','党员','计科22101','123456'),('2002','小张','男','博士研究生','13579542542','党员','计科22102','123456'),('2003','小陈','女','硕士研究生','11254528974','党员','计科22103','123456'),('2004','小李','女','硕士研究生','18369215214','党员','电自22101','123456'),('2005','小何','男','博士研究生','13025623845','党员','土木22102','123456'),('2006','小六','男','硕士研究生','13256875420','党员','财会22101','123456'),('2007','小程','女','博士研究生','17123589654','党员','金融22104','123456'),('2008','小周','男','硕士研究生','17235489632','党员','网工22105','123456'),('2009','小赵','女','博士研究生','17745820325','党员','英语22101','123456'),('2010','小王','女','硕士研究生','15624569853','党员','航艺22101','123456');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-11-04 20:55:59
