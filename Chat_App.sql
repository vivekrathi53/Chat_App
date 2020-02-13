-- MySQL dump 10.13  Distrib 5.7.28, for Linux (x86_64)
--
-- Host: localhost    Database: Chat_App
-- ------------------------------------------------------
-- Server version	5.7.28-0ubuntu0.18.04.4

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
-- Table structure for table `GauravTable`
--

DROP TABLE IF EXISTS `GauravTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GauravTable` (
  `Sender` varchar(20) NOT NULL,
  `Valid` int(1) NOT NULL,
  `Message` varchar(1000) DEFAULT NULL,
  `Time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GauravTable`
--

LOCK TABLES `GauravTable` WRITE;
/*!40000 ALTER TABLE `GauravTable` DISABLE KEYS */;
INSERT INTO `GauravTable` VALUES ('Vivek',1,NULL,'2019-05-14 16:29:04'),('Vivek',1,NULL,'2019-05-14 16:29:04'),('Vivek',1,NULL,'2019-05-14 16:34:28'),('Vivek',1,NULL,'2019-05-14 16:34:28'),('Vivek',1,NULL,'2019-05-14 16:34:28'),('Vivek',1,NULL,'2019-05-14 16:52:50'),('Vivek',1,NULL,'2019-05-14 16:52:50'),('Vivek',1,NULL,'2019-05-14 16:52:50'),('Vivek',1,NULL,'2019-05-14 16:52:50'),('Vivek',1,NULL,'2019-05-14 17:02:03'),('Vivek',1,NULL,'2019-05-14 17:02:03'),('Vivek',1,NULL,'2019-05-14 17:02:03'),('Vivek',1,NULL,'2019-05-14 17:02:03'),('Vivek',0,'Hey bhaiya','2019-05-14 17:02:26'),('Vivek',0,'No matter what has happened. No matter what you’ve done. No matter what you will do. I will always love you. I swear it','2019-05-14 17:06:11'),('Raj',1,NULL,'2019-05-21 13:16:24'),('Raj',1,NULL,'2019-05-21 13:20:54');
/*!40000 ALTER TABLE `GauravTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `KunalTable`
--

DROP TABLE IF EXISTS `KunalTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `KunalTable` (
  `Sender` varchar(20) DEFAULT NULL,
  `Valid` int(11) DEFAULT NULL,
  `Message` text,
  `Time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `KunalTable`
--

LOCK TABLES `KunalTable` WRITE;
/*!40000 ALTER TABLE `KunalTable` DISABLE KEYS */;
INSERT INTO `KunalTable` VALUES ('Raj',1,NULL,'2019-05-21 13:20:54');
/*!40000 ALTER TABLE `KunalTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LocalGauravChats`
--

DROP TABLE IF EXISTS `LocalGauravChats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LocalGauravChats` (
  `Sender` varchar(15) NOT NULL,
  `Receiver` varchar(15) NOT NULL,
  `Message` text NOT NULL,
  `SentTime` timestamp NULL DEFAULT NULL,
  `ReceivedTime` timestamp NULL DEFAULT NULL,
  `SeenTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LocalGauravChats`
--

LOCK TABLES `LocalGauravChats` WRITE;
/*!40000 ALTER TABLE `LocalGauravChats` DISABLE KEYS */;
INSERT INTO `LocalGauravChats` VALUES ('Vivek','Gaurav','Hello ChatApp','2019-05-11 05:48:52',NULL,NULL),('Raj','Vivek','hello','2019-05-09 01:48:54',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Gaurav','jojoj','2019-05-14 12:58:29',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Gaurav','jojoj','2019-05-14 12:58:29',NULL,NULL),('Gaurav','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Gaurav','Gaurav','jojoj','2019-05-14 12:58:29',NULL,NULL),('Gaurav','Gaurav','jojoj','2019-05-14 12:58:29',NULL,NULL),('Gaurav','Gaurav','jojoj','2019-05-14 12:58:29',NULL,NULL),('Gaurav','Gaurav','Hey babe','2019-05-14 14:23:08',NULL,NULL),('Vivek','Vivek','YUVRAJ','2019-05-14 13:26:32',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Gaurav','chutiya gaurav','2019-05-14 16:34:02',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Gaurav','chutiya gaurav','2019-05-14 16:34:02',NULL,NULL),('Vivek','Gaurav','YUVRAJ','2019-05-14 17:34:35',NULL,NULL),('Vivek','Gaurav','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Gaurav','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Gaurav','chutiya gaurav','2019-05-14 16:34:02',NULL,NULL),('Vivek','Gaurav','YUVRAJ','2019-05-14 17:34:35',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Gaurav','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL);
/*!40000 ALTER TABLE `LocalGauravChats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LocalRajChats`
--

DROP TABLE IF EXISTS `LocalRajChats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LocalRajChats` (
  `Sender` varchar(15) NOT NULL,
  `Receiver` varchar(15) NOT NULL,
  `Message` text NOT NULL,
  `SentTime` timestamp NULL DEFAULT NULL,
  `ReceivedTime` timestamp NULL DEFAULT NULL,
  `SeenTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LocalRajChats`
--

LOCK TABLES `LocalRajChats` WRITE;
/*!40000 ALTER TABLE `LocalRajChats` DISABLE KEYS */;
INSERT INTO `LocalRajChats` VALUES ('Vivek','Raj','Hello ChatApp','2019-05-11 05:48:52',NULL,NULL),('Raj','Vivek','hello','2019-05-09 01:48:54',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Raj','jojoj','2019-05-14 12:58:29',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Raj','jojoj','2019-05-14 12:58:29',NULL,NULL),('Raj','Vivek','hello','2019-05-13 06:49:41',NULL,NULL),('Raj','Raj','jojoj','2019-05-14 12:58:29',NULL,NULL),('Raj','Raj','jojoj','2019-05-14 12:58:29',NULL,NULL),('Raj','Raj','jojoj','2019-05-14 12:58:29',NULL,NULL),('Raj','Raj','Hey babe','2019-05-14 14:23:08',NULL,NULL),('Vivek','Vivek','YUVRAJ','2019-05-14 13:26:32',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Raj','chutiya Raj','2019-05-14 16:34:02',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Raj','chutiya Raj','2019-05-14 16:34:02',NULL,NULL),('Vivek','Raj','YUVRAJ','2019-05-14 17:34:35',NULL,NULL),('Vivek','Raj','Madarchod','2019-05-14 14:42:52',NULL,NULL),('Vivek','Raj','Milf','2019-05-14 16:25:57',NULL,NULL),('Vivek','Raj','chutiya Raj','2019-05-14 16:34:02',NULL,NULL),('Vivek','Raj','YUVRAJ','2019-05-14 17:34:35',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL),('Raj','Vivek','Hey bhaiya','2019-05-14 17:02:26',NULL,NULL);
/*!40000 ALTER TABLE `LocalRajChats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LocalRitikChats`
--

DROP TABLE IF EXISTS `LocalRitikChats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LocalRitikChats` (
  `Sender` varchar(15) NOT NULL,
  `Receiver` varchar(15) NOT NULL,
  `Message` text NOT NULL,
  `SentTime` timestamp NULL DEFAULT NULL,
  `ReceivedTime` timestamp NULL DEFAULT NULL,
  `SeenTime` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LocalRitikChats`
--

LOCK TABLES `LocalRitikChats` WRITE;
/*!40000 ALTER TABLE `LocalRitikChats` DISABLE KEYS */;
INSERT INTO `LocalRitikChats` VALUES ('VivekRathi','Ritik','hi bro!!','2019-12-15 06:53:12','2019-12-15 06:53:50','2019-12-15 06:53:52'),('Ritik','VivekRathi','okay lets begin the chat','2019-12-15 06:54:06','2019-12-15 06:49:39','2019-12-15 06:54:11'),('VivekRathi','Ritik','okay','2019-12-15 06:55:36','2019-12-15 06:55:36','2019-12-15 06:55:36'),('VivekRathi','Ritik','hi bro!!','2019-12-15 06:53:12','2019-12-15 18:30:51','2019-12-15 18:31:14');
/*!40000 ALTER TABLE `LocalRitikChats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `LocalVivekRathiChats`
--

DROP TABLE IF EXISTS `LocalVivekRathiChats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `LocalVivekRathiChats` (
  `Sender` varchar(15) NOT NULL,
  `Receiver` varchar(15) NOT NULL,
  `Message` text NOT NULL,
  `SentTime` timestamp NULL DEFAULT '2018-12-31 18:30:00',
  `ReceivedTime` timestamp NULL DEFAULT '2018-12-31 18:30:00',
  `SeenTime` timestamp NULL DEFAULT '2018-12-31 18:30:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `LocalVivekRathiChats`
--

LOCK TABLES `LocalVivekRathiChats` WRITE;
/*!40000 ALTER TABLE `LocalVivekRathiChats` DISABLE KEYS */;
INSERT INTO `LocalVivekRathiChats` VALUES ('Ritik','VivekRathi','hi new buddy!!','2019-12-15 06:48:46','2019-12-15 06:49:40','2019-12-15 06:49:44'),('VivekRathi','Ritik','hi bro!!','2019-12-15 06:53:12','2019-12-15 06:55:36','2019-12-15 18:31:14'),('VivekRathi','Ritik','okay','2019-12-15 06:55:36','2019-12-15 18:30:49','2019-12-15 18:31:14');
/*!40000 ALTER TABLE `LocalVivekRathiChats` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RajTable`
--

DROP TABLE IF EXISTS `RajTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RajTable` (
  `Sender` varchar(20) NOT NULL,
  `Valid` int(11) NOT NULL,
  `Message` text,
  `Time` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RajTable`
--

LOCK TABLES `RajTable` WRITE;
/*!40000 ALTER TABLE `RajTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `RajTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ReceivedTable`
--

DROP TABLE IF EXISTS `ReceivedTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ReceivedTable` (
  `Sender` varchar(20) NOT NULL,
  `Valid` int(1) DEFAULT NULL,
  `Message` varchar(1000) NOT NULL,
  `Time` timestamp(6) NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ReceivedTable`
--

LOCK TABLES `ReceivedTable` WRITE;
/*!40000 ALTER TABLE `ReceivedTable` DISABLE KEYS */;
INSERT INTO `ReceivedTable` VALUES ('Vivek',0,'Hello','2019-05-11 05:48:52.000000');
/*!40000 ALTER TABLE `ReceivedTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `RitikTable`
--

DROP TABLE IF EXISTS `RitikTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `RitikTable` (
  `Sender` varchar(11) DEFAULT NULL,
  `Valid` int(255) DEFAULT NULL,
  `Message` text,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `RitikTable`
--

LOCK TABLES `RitikTable` WRITE;
/*!40000 ALTER TABLE `RitikTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `RitikTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `UserTable`
--

DROP TABLE IF EXISTS `UserTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `UserTable` (
  `Username` varchar(20) NOT NULL,
  `Password` varchar(20) NOT NULL,
  `LastSeen` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `UserTable`
--

LOCK TABLES `UserTable` WRITE;
/*!40000 ALTER TABLE `UserTable` DISABLE KEYS */;
INSERT INTO `UserTable` VALUES ('Gaurav','12345','2019-12-15 06:39:21'),('Kunal','12345','2019-12-15 06:39:21'),('Raj','12345','2019-12-15 06:39:21'),('Ritik','12345','2019-12-15 06:39:21'),('Ritika','12345','2019-12-15 06:39:21'),('Saurav','12345','2019-12-15 06:39:21'),('Shivam','12345','2019-12-15 06:39:21'),('VivekRathi','password','2019-12-15 06:55:52');
/*!40000 ALTER TABLE `UserTable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VivekRathiTable`
--

DROP TABLE IF EXISTS `VivekRathiTable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VivekRathiTable` (
  `Sender` varchar(11) DEFAULT NULL,
  `Valid` int(255) DEFAULT NULL,
  `Message` text,
  `Time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VivekRathiTable`
--

LOCK TABLES `VivekRathiTable` WRITE;
/*!40000 ALTER TABLE `VivekRathiTable` DISABLE KEYS */;
/*!40000 ALTER TABLE `VivekRathiTable` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-24 13:49:36
