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
-- Table structure for table `indexing_collection`
--

DROP TABLE IF EXISTS `indexing_collection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `indexing_collection` (
  `ID_INDEXING_START_FILES` int NOT NULL AUTO_INCREMENT,
  `TYPE_ANALYZER` varchar(50) NOT NULL,
  `DATE_INDEXED` date NOT NULL,
  `NUMBER_OF_FILES` int NOT NULL,
  `TOTAL_SIZE` int NOT NULL,
  `TOTAL_TIME` time NOT NULL,
  PRIMARY KEY (`ID_INDEXING_START_FILES`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indexing_collection`
--

LOCK TABLES `indexing_collection` WRITE;
/*!40000 ALTER TABLE `indexing_collection` DISABLE KEYS */;
INSERT INTO `indexing_collection` VALUES (10,'Standard Analyzer','2024-04-27',32,8074301,'00:00:02'),(11,'Simple Analyzer','2024-04-27',32,8074301,'00:00:02'),(12,'Whitespace Analyzer','2024-04-27',32,8074301,'00:00:02'),(13,'Standard Analyzer','2024-04-27',12,3270156,'00:00:00'),(14,'Simple Analyzer','2024-04-27',12,3270156,'00:00:01'),(15,'Standard Analyzer','2024-04-27',12,3270156,'00:00:01'),(16,'Whitespace Analyzer','2024-04-27',12,3270156,'00:00:01'),(17,'Standard Analyzer','2024-04-27',32,8074301,'00:00:01'),(18,'Simple Analyzer','2024-04-27',32,8074301,'00:00:01'),(19,'Whitespace Analyzer','2024-04-27',32,8074301,'00:00:01'),(20,'Simple Analyzer','2024-04-27',32,8074301,'00:00:01'),(21,'Standard Analyzer','2024-04-27',32,8074301,'00:00:02');
/*!40000 ALTER TABLE `indexing_collection` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-28 19:26:25
