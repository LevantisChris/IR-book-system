-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: ir_system
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `ratings`
--

DROP TABLE IF EXISTS `ratings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings` (
  `INDEX_CT` int NOT NULL AUTO_INCREMENT,
  `FILE_NAME_CT` varchar(100) NOT NULL,
  `RATING` double NOT NULL,
  PRIMARY KEY (`INDEX_CT`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
INSERT INTO `ratings` VALUES (4,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',5),(5,'Murder_in_the_Fog-Dominic_Butler.txt',1),(6,'Introduction to Data Compression (4th Edition).txt',1),(7,'Hotel-Arthur_Hailey.txt',1),(8,'Guide_to_Network_Programming.txt',4),(9,'A_Ghost-Robert_Harris.txt',5),(10,'taoofseneca_vol1-1.txt',1),(11,'The_Accidental_Tourist-Anne_Tyler.txt',3),(12,'A_Ghost-Robert_Harris.txt',5),(13,'The_Accidental_Tourist-Anne_Tyler.txt',4),(14,'The Bishop\'s purse.txt',1),(15,'taoofseneca_vol1-1.txt',5),(16,'The doctor.txt',1),(17,'Code.txt',5),(18,'Murder_in_the_Fog-Dominic_Butler.txt',3),(19,'taoofseneca_vol3.txt',5),(20,'Sailing ships.txt',4),(21,'taoofseneca_vol2.txt',2),(22,'A_Ghost-Robert_Harris.txt',3);
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-28 19:26:27
