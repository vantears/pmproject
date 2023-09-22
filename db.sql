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
INSERT INTO `at_record` VALUES (1,'17:44:00',NULL),(5,'09:27:03',NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `attendance`
--

LOCK TABLES `attendance` WRITE;
/*!40000 ALTER TABLE `attendance` DISABLE KEYS */;
INSERT INTO `attendance` VALUES (1,'2023-09-21','PM00001',2),(2,'2023-09-21','PM00002',1),(3,'2023-09-21','PM00003',1),(4,'2023-09-21','PM00004',1),(5,'2023-09-22','PM00001',2),(6,'2023-09-22','PM00002',1),(7,'2023-09-22','PM00003',1),(8,'2023-09-22','PM00004',1),(9,'2023-09-22','PM00005',1);
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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
INSERT INTO `employee` VALUES ('PM00001','aaa','aaa','010-5888-5888',5,1,1,15,650),('PM00002','박나래','narae@pmcompany.co.kr','010-1235-1235',1,1,2,0,0),('PM00003','김동동','ddong@pjcompany.com','010-5555-5555',1,1,1,15,500),('PM00004','김김김','kim@kim.com','010-4444-4444',1,1,1,15,500),('PM00005','홍대병','hongdae@pjcompany.com','010-8877-8877',1,1,1,15,500);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `leave`
--

LOCK TABLES `leave` WRITE;
/*!40000 ALTER TABLE `leave` DISABLE KEYS */;
INSERT INTO `leave` VALUES (1,'2023-09-15','2023-09-18','연차','PM00001');
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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES (1,'종료 프로젝트1','2022-09-06','2023-03-21','종료'),(2,'종료 프로젝트2','2022-11-12','2023-06-30','종료'),(3,'진행 프로젝트1','2023-04-07',NULL,'진행'),(4,'진행 프로젝트2','2023-08-02',NULL,'진행'),(5,'종료 프로젝트3','2023-09-21','2023-09-21','종료'),(6,'예정 프로젝트4-1','2023-10-16','2023-12-31','예정');
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_info`
--

LOCK TABLES `project_info` WRITE;
/*!40000 ALTER TABLE `project_info` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `salaryhistory`
--

LOCK TABLES `salaryhistory` WRITE;
/*!40000 ALTER TABLE `salaryhistory` DISABLE KEYS */;
INSERT INTO `salaryhistory` VALUES (2,'2023-09-15','PM00001','유급 휴가','9월 유급 휴가',25);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `termination`
--

LOCK TABLES `termination` WRITE;
/*!40000 ALTER TABLE `termination` DISABLE KEYS */;
INSERT INTO `termination` VALUES (1,'2023-09-15','퇴직','PM00002');
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,'2023-09-15','발령','PM00001',5);
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

-- Dump completed on 2023-09-22 10:20:08
