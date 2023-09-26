CREATE DATABASE  IF NOT EXISTS `pmproject` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `pmproject`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: pmproject
-- ------------------------------------------------------
-- Server version	8.0.34

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
-- Table structure for table `at_record`
--

DROP TABLE IF EXISTS `at_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `at_record` (
  `ar_ad_num` int NOT NULL,
  `ar_st_time` time DEFAULT NULL,
  `ar_end_time` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `at_record`
--

LOCK TABLES `at_record` WRITE;
/*!40000 ALTER TABLE `at_record` DISABLE KEYS */;
INSERT INTO `at_record` VALUES (1,'08:00:00','17:00:00'),(3,'08:00:00','17:00:00'),(4,'08:00:00','17:00:00'),(5,'08:00:00','17:00:00'),(6,'08:00:00','17:00:00'),(9,'08:00:00','17:00:00'),(10,'08:00:00','17:00:00'),(12,'08:00:00','17:00:00'),(13,'08:00:00','17:00:00'),(14,'08:00:00','17:00:00'),(15,'08:00:00','17:00:00'),(16,'08:00:00','17:00:00'),(17,'08:00:00','17:00:00'),(20,'08:00:00','17:00:00'),(21,'08:00:00','17:00:00'),(22,'08:00:00','17:00:00'),(23,'08:00:00','17:00:00'),(24,'08:00:00','17:00:00'),(26,'08:00:00','17:00:00'),(28,'08:00:00','17:00:00'),(29,'08:00:00','17:00:00'),(30,'08:00:00','17:00:00'),(31,'08:00:00','17:00:00'),(34,'08:00:00','17:00:00'),(35,'08:00:00','17:00:00'),(37,'08:00:00','17:00:00'),(38,'08:00:00','17:00:00'),(39,'08:00:00','17:00:00'),(40,'08:00:00','17:00:00'),(41,'08:00:00','17:00:00'),(42,'08:00:00','17:00:00'),(45,'08:00:00','17:00:00'),(46,'08:00:00','17:00:00'),(47,'08:00:00','17:00:00'),(48,'08:00:00','17:00:00'),(49,'08:00:00','17:00:00');
/*!40000 ALTER TABLE `at_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance`
--

DROP TABLE IF EXISTS `attendance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance` (
  `ad_num` int NOT NULL AUTO_INCREMENT,
  `ad_date` date NOT NULL,
  `ad_ep_id` varchar(20) NOT NULL,
  `ad_at_num` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`ad_num`),
  KEY `FK_attendance_type_TO_attendance_1` (`ad_at_num`),
  KEY `FK_employee_TO_attendance_1` (`ad_ep_id`),
  CONSTRAINT `FK_attendance_type_TO_attendance_1` FOREIGN KEY (`ad_at_num`) REFERENCES `attendance_type` (`at_num`),
  CONSTRAINT `FK_employee_TO_attendance_1` FOREIGN KEY (`ad_ep_id`) REFERENCES `employee` (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'2023-09-05','PM00001',2),(2,'2023-09-05','PM00002',3),(3,'2023-09-05','PM00003',2),(4,'2023-09-05','PM00004',2),(5,'2023-09-05','PM00005',2),(6,'2023-09-05','PM00006',2),(7,'2023-09-05','PM00007',4),(8,'2023-09-05','PM00008',3),(9,'2023-09-05','PM00009',2),(10,'2023-09-05','PM00010',2),(11,'2023-09-05','PM00011',4),(12,'2023-09-05','PM00012',2),(13,'2023-09-05','PM00013',2),(14,'2023-09-05','PM00014',2),(15,'2023-09-05','PM00015',2),(16,'2023-09-05','PM00016',2),(17,'2023-09-05','PM00017',2),(18,'2023-09-05','PM00018',4),(19,'2023-09-05','PM00019',1),(20,'2023-09-05','PM00020',2),(21,'2023-09-05','PM00021',2),(22,'2023-09-05','PM00022',2),(23,'2023-09-05','PM00023',2),(24,'2023-09-05','PM00024',2),(25,'2023-09-05','PM00025',3),(26,'2023-09-06','PM00001',2),(27,'2023-09-06','PM00002',3),(28,'2023-09-06','PM00003',2),(29,'2023-09-06','PM00004',2),(30,'2023-09-06','PM00005',2),(31,'2023-09-06','PM00006',2),(32,'2023-09-06','PM00007',4),(33,'2023-09-06','PM00008',3),(34,'2023-09-06','PM00009',2),(35,'2023-09-06','PM00010',2),(36,'2023-09-06','PM00011',4),(37,'2023-09-06','PM00012',2),(38,'2023-09-06','PM00013',2),(39,'2023-09-06','PM00014',2),(40,'2023-09-06','PM00015',2),(41,'2023-09-06','PM00016',2),(42,'2023-09-06','PM00017',2),(43,'2023-09-06','PM00018',4),(44,'2023-09-06','PM00019',1),(45,'2023-09-06','PM00020',2),(46,'2023-09-06','PM00021',2),(47,'2023-09-06','PM00022',2),(48,'2023-09-06','PM00023',2),(49,'2023-09-06','PM00024',2),(50,'2023-09-06','PM00025',3);
/*!40000 ALTER TABLE `attendance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `attendance_type`
--

DROP TABLE IF EXISTS `attendance_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendance_type` (
  `at_num` int NOT NULL AUTO_INCREMENT,
  `at_type` varchar(15) NOT NULL,
  PRIMARY KEY (`at_num`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance_type`
--

LOCK TABLES `attendance_type` WRITE;
/*!40000 ALTER TABLE `attendance_type` DISABLE KEYS */;
INSERT INTO `attendance_type` VALUES (1,'결근'),(2,'출근'),(3,'재택'),(4,'출장');
/*!40000 ALTER TABLE `attendance_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `dm_num` int NOT NULL AUTO_INCREMENT,
  `dm_name` varchar(255) NOT NULL,
  PRIMARY KEY (`dm_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'대기'),(2,'기획부서'),(3,'마케팅부서'),(4,'시스템부서'),(5,'금융부서');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `ep_id` varchar(20) NOT NULL,
  `ep_name` varchar(20) NOT NULL,
  `ep_email` varchar(50) NOT NULL,
  `ep_phone_num` varchar(20) NOT NULL,
  `ep_dm_num` int NOT NULL DEFAULT '1',
  `ep_po_num` int NOT NULL DEFAULT '1',
  `ep_st_num` int NOT NULL DEFAULT '1',
  `ep_leave` int NOT NULL DEFAULT '15',
  `ep_salary` int NOT NULL,
  PRIMARY KEY (`ep_id`),
  KEY `FK_department_TO_employee_1` (`ep_dm_num`),
  KEY `FK_position_TO_employee_1` (`ep_po_num`),
  KEY `FK_status_TO_employee_1` (`ep_st_num`),
  CONSTRAINT `FK_department_TO_employee_1` FOREIGN KEY (`ep_dm_num`) REFERENCES `department` (`dm_num`),
  CONSTRAINT `FK_position_TO_employee_1` FOREIGN KEY (`ep_po_num`) REFERENCES `position` (`po_num`),
  CONSTRAINT `FK_status_TO_employee_1` FOREIGN KEY (`ep_st_num`) REFERENCES `status` (`st_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES ('PM00001','김첨지','kcj@pmcompany.kr','010-1111-1111',2,6,1,10,500),('PM00002','홍길동','hkd@pmcompany.kr','010-2222-2222',3,6,1,8,500),('PM00003','임창정','lcj@pmcompany.kr','010-3333-3333',4,6,1,12,500),('PM00004','이순신','lss@pmcompany.kr','010-4444-4444',5,6,1,6,500),('PM00005','김중배','kjb@pmcompany.kr','010-1234-1111',2,5,1,2,450),('PM00006','박나라','pnl@pmcompany.kr','010-5678-2222',3,5,1,11,450),('PM00007','정도전','jdj@pmcompany.kr','010-9123-3333',4,5,1,7,450),('PM00008','민기옥','mko@pmcompany.kr','010-4567-4444',5,5,1,14,450),('PM00009','전해리','jhl@pmcompany.kr','010-4321-1111',2,4,1,7,400),('PM00010','유해진','lhj@pmcompany.kr','010-8765-2222',3,4,1,6,400),('PM00011','고길동','ggd@pmcompany.kr','010-3219-3333',4,4,1,12,400),('PM00012','신선아','ssa@pmcompany.kr','010-7654-4444',5,4,1,9,400),('PM00013','구하라','ghr@pmcompany.kr','010-1212-1111',2,3,1,3,350),('PM00014','이병헌','lbh@pmcompany.kr','010-2323-2222',3,3,1,9,350),('PM00015','박서준','psj@pmcompany.kr','010-3434-3333',4,3,1,8,350),('PM00016','이형기','lhk@pmcompany.kr','010-4545-4444',5,3,1,7,350),('PM00017','추신수','css@pmcompany.kr','010-5555-1111',2,2,1,3,300),('PM00018','이만기','lmk@pmcompany.kr','010-6666-2222',3,2,1,4,300),('PM00019','마소희','msh@pmcompany.kr','010-7777-3333',4,2,1,6,300),('PM00020','김진수','kjs@pmcompany.kr','010-8888-4444',5,2,1,10,300),('PM00021','오만수','oms@pmcompany.kr','010-5656-1111',2,1,1,8,250),('PM00022','구본혁','kbh@pmcompany.kr','010-6767-2222',3,1,1,7,250),('PM00023','강기혁','kkh@pmcompany.kr','010-7878-3333',4,1,1,7,250),('PM00024','정주영','jjy@pmcompany.kr','010-8989-4444',5,1,1,6,250),('PM00025','최대기','cdk@pmcompany.kr','010-9999-9999',1,1,1,6,250),('PM00026','김휴직','khj@pmcompany.kr','010-0000-0000',4,5,1,8,0),('PM00027','이퇴직','ltj@pmcompany.kr','010-0101-0101',5,4,2,0,0),('PM00028','나애진','naj@pmcompany.kr','010-0202-0202',2,1,2,0,0);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `leave`
--

DROP TABLE IF EXISTS `leave`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `leave` (
  `le_num` int NOT NULL AUTO_INCREMENT,
  `le_start_date` date NOT NULL,
  `le_end_date` date NOT NULL,
  `le_type` varchar(30) NOT NULL,
  `le_ep_id` varchar(20) NOT NULL,
  PRIMARY KEY (`le_num`),
  KEY `FK_employee_TO_Leave_1` (`le_ep_id`),
  CONSTRAINT `FK_employee_TO_Leave_1` FOREIGN KEY (`le_ep_id`) REFERENCES `employee` (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave`
--

LOCK TABLES `leave` WRITE;
/*!40000 ALTER TABLE `leave` DISABLE KEYS */;
INSERT INTO `leave` VALUES (1,'2022-12-24','2022-12-24','연차','PM00012'),(2,'2023-03-02','2022-03-05','경조사','PM00005'),(3,'2023-08-16','2023-08-16','연차','PM00017'),(4,'2023-01-16','2023-08-16','육아휴직','PM00026');
/*!40000 ALTER TABLE `leave` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pay`
--

DROP TABLE IF EXISTS `pay`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pay` (
  `pa_type` varchar(20) NOT NULL,
  PRIMARY KEY (`pa_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pay`
--

LOCK TABLES `pay` WRITE;
/*!40000 ALTER TABLE `pay` DISABLE KEYS */;
INSERT INTO `pay` VALUES ('성과금'),('월급'),('유급 휴가'),('퇴직금');
/*!40000 ALTER TABLE `pay` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `position`
--

DROP TABLE IF EXISTS `position`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `position` (
  `po_num` int NOT NULL AUTO_INCREMENT,
  `po_name` varchar(255) NOT NULL,
  PRIMARY KEY (`po_num`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `position`
--

LOCK TABLES `position` WRITE;
/*!40000 ALTER TABLE `position` DISABLE KEYS */;
INSERT INTO `position` VALUES (1,'사원'),(2,'주임'),(3,'대리'),(4,'과장'),(5,'차장'),(6,'부장');
/*!40000 ALTER TABLE `position` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `pj_num` int NOT NULL AUTO_INCREMENT,
  `pj_name` varchar(255) NOT NULL,
  `pj_start_date` date DEFAULT NULL,
  `pj_end_date` date DEFAULT NULL,
  `pj_state` varchar(15) NOT NULL,
  PRIMARY KEY (`pj_num`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'종료 프로젝트1','2022-09-06','2023-03-21','종료'),(2,'종료 프로젝트2','2022-11-12','2023-06-30','종료'),(3,'진행 프로젝트1','2023-04-07',NULL,'진행'),(4,'진행 프로젝트2','2023-08-02',NULL,'진행'),(5,'예정 프로젝트1',NULL,NULL,'예정');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_info`
--

DROP TABLE IF EXISTS `project_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project_info` (
  `pi_num` int NOT NULL AUTO_INCREMENT,
  `pi_ep_id` varchar(20) NOT NULL,
  `pi_pj_num` int NOT NULL,
  `pi_role` varchar(20) NOT NULL,
  PRIMARY KEY (`pi_num`),
  KEY `FK_employee_TO_project_info_1` (`pi_ep_id`),
  KEY `FK_project_TO_project_info_1` (`pi_pj_num`),
  CONSTRAINT `FK_employee_TO_project_info_1` FOREIGN KEY (`pi_ep_id`) REFERENCES `employee` (`ep_id`),
  CONSTRAINT `FK_project_TO_project_info_1` FOREIGN KEY (`pi_pj_num`) REFERENCES `project` (`pj_num`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_info`
--

LOCK TABLES `project_info` WRITE;
/*!40000 ALTER TABLE `project_info` DISABLE KEYS */;
INSERT INTO `project_info` VALUES (1,'PM00001',1,'팀장'),(2,'PM00026',1,'부팀장'),(3,'PM00010',1,'팀원'),(4,'PM00015',1,'팀원'),(5,'PM00018',1,'팀원'),(6,'PM00028',1,'팀원'),(7,'PM00002',2,'팀장'),(8,'PM00007',2,'부팀장'),(9,'PM00027',2,'팀원'),(10,'PM00014',2,'팀원'),(11,'PM00017',2,'팀원'),(12,'PM00021',2,'팀원'),(13,'PM00003',3,'팀장'),(14,'PM00006',3,'부팀장'),(15,'PM00012',3,'팀원'),(16,'PM00016',3,'팀원'),(17,'PM00019',3,'팀원'),(18,'PM00022',3,'팀원'),(19,'PM00004',4,'팀장'),(20,'PM00008',4,'부팀장'),(21,'PM00009',4,'팀원'),(22,'PM00013',4,'팀원'),(23,'PM00020',4,'팀원'),(24,'PM00027',4,'팀원'),(25,'PM00001',5,'팀장'),(26,'PM00005',5,'부팀장'),(27,'PM00011',5,'팀원'),(28,'PM00014',5,'팀원'),(29,'PM00017',5,'팀원'),(30,'PM00023',5,'팀원');
/*!40000 ALTER TABLE `project_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `salaryhistory`
--

DROP TABLE IF EXISTS `salaryhistory`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `salaryhistory` (
  `sh_num` int NOT NULL AUTO_INCREMENT,
  `sh_payday` date NOT NULL,
  `sh_ep_id` varchar(20) NOT NULL,
  `sh_pa_type` varchar(20) NOT NULL,
  `sh_type_detail` varchar(20) NOT NULL,
  `sh_salary` int NOT NULL,
  PRIMARY KEY (`sh_num`),
  KEY `FK_employee_TO_salaryHistory_1` (`sh_ep_id`),
  KEY `FK_pay_TO_salaryHistory_1` (`sh_pa_type`),
  CONSTRAINT `FK_employee_TO_salaryHistory_1` FOREIGN KEY (`sh_ep_id`) REFERENCES `employee` (`ep_id`),
  CONSTRAINT `FK_pay_TO_salaryHistory_1` FOREIGN KEY (`sh_pa_type`) REFERENCES `pay` (`pa_type`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaryhistory`
--

LOCK TABLES `salaryhistory` WRITE;
/*!40000 ALTER TABLE `salaryhistory` DISABLE KEYS */;
INSERT INTO `salaryhistory` VALUES (1,'2023-04-01','PM00026','월급','3월 월급',450),(2,'2023-07-01','PM00027','월급','6월 월급',400),(3,'2023-05-01','PM00028','월급','4월 월급',250),(4,'2023-05-01','PM00026','유급 휴가','유급 휴가',450),(5,'2023-07-04','PM00027','퇴직금','퇴직금',400),(6,'2023-04-01','PM00028','성과금','프로젝트 성과금',100),(7,'2023-08-01','PM00001','월급','7월 월급',500),(8,'2023-08-01','PM00002','월급','7월 월급',500),(9,'2023-08-01','PM00003','월급','7월 월급',500),(10,'2023-08-01','PM00004','월급','7월 월급',500),(11,'2023-08-01','PM00005','월급','7월 월급',450),(12,'2023-08-01','PM00006','월급','7월 월급',450),(13,'2023-08-01','PM00007','월급','7월 월급',450),(14,'2023-08-01','PM00008','월급','7월 월급',450),(15,'2023-08-01','PM00009','월급','7월 월급',400),(16,'2023-08-01','PM00010','월급','7월 월급',400),(17,'2023-08-01','PM00011','월급','7월 월급',400),(18,'2023-08-01','PM00012','월급','7월 월급',400),(19,'2023-08-01','PM00013','월급','7월 월급',350),(20,'2023-08-01','PM00014','월급','7월 월급',350),(21,'2023-08-01','PM00015','월급','7월 월급',350),(22,'2023-08-01','PM00016','월급','7월 월급',350),(23,'2023-08-01','PM00017','월급','7월 월급',300),(24,'2023-08-01','PM00018','월급','7월 월급',300),(25,'2023-08-01','PM00019','월급','7월 월급',300),(26,'2023-08-01','PM00020','월급','7월 월급',300),(27,'2023-08-01','PM00021','월급','7월 월급',250),(28,'2023-08-01','PM00022','월급','7월 월급',250),(29,'2023-08-01','PM00023','월급','7월 월급',250),(30,'2023-08-01','PM00024','월급','7월 월급',250);
/*!40000 ALTER TABLE `salaryhistory` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `st_num` int NOT NULL AUTO_INCREMENT,
  `st_type` varchar(20) NOT NULL,
  PRIMARY KEY (`st_num`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,'재직'),(2,'퇴직');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `termination`
--

DROP TABLE IF EXISTS `termination`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `termination` (
  `tm_num` int NOT NULL AUTO_INCREMENT,
  `tm_date` date NOT NULL,
  `tm_reason` varchar(30) NOT NULL,
  `tm_ep_id` varchar(20) NOT NULL,
  PRIMARY KEY (`tm_num`),
  KEY `FK_employee_TO_Termination_1` (`tm_ep_id`),
  CONSTRAINT `FK_employee_TO_Termination_1` FOREIGN KEY (`tm_ep_id`) REFERENCES `employee` (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termination`
--

LOCK TABLES `termination` WRITE;
/*!40000 ALTER TABLE `termination` DISABLE KEYS */;
INSERT INTO `termination` VALUES (1,'2023-07-03','퇴직','PM00027'),(2,'2023-05-29','퇴직','PM00028');
/*!40000 ALTER TABLE `termination` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `tr_num` int NOT NULL AUTO_INCREMENT,
  `tr_date` date NOT NULL,
  `tr_reason` varchar(30) NOT NULL,
  `tr_ep_id` varchar(20) NOT NULL,
  `tr_dm_num` int NOT NULL,
  PRIMARY KEY (`tr_num`),
  KEY `FK_employee_TO_transfer_1` (`tr_ep_id`),
  KEY `FK_department_TO_transfer_1` (`tr_dm_num`),
  CONSTRAINT `FK_department_TO_transfer_1` FOREIGN KEY (`tr_dm_num`) REFERENCES `department` (`dm_num`),
  CONSTRAINT `FK_employee_TO_transfer_1` FOREIGN KEY (`tr_ep_id`) REFERENCES `employee` (`ep_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,'2023-02-27','공석 보충','PM00012',5),(2,'2023-05-30','공석 보충','PM00023',2);
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-26 17:42:25
