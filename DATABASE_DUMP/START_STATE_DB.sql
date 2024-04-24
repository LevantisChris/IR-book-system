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
-- Table structure for table `frequency_of_analyzers`
--

DROP TABLE IF EXISTS `frequency_of_analyzers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frequency_of_analyzers` (
  `INDEX_FA` int NOT NULL AUTO_INCREMENT,
  `NAME_FA` varchar(100) NOT NULL,
  `COUNTER_FA` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX_FA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_analyzers`
--

LOCK TABLES `frequency_of_analyzers` WRITE;
/*!40000 ALTER TABLE `frequency_of_analyzers` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequency_of_analyzers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency_of_qparsers`
--

DROP TABLE IF EXISTS `frequency_of_qparsers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frequency_of_qparsers` (
  `INDEX_FQP` int NOT NULL AUTO_INCREMENT,
  `NAME_FQP` varchar(100) NOT NULL,
  `COUNTER_FQP` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX_FQP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_qparsers`
--

LOCK TABLES `frequency_of_qparsers` WRITE;
/*!40000 ALTER TABLE `frequency_of_qparsers` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequency_of_qparsers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency_of_salgos`
--

DROP TABLE IF EXISTS `frequency_of_salgos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frequency_of_salgos` (
  `INDEX_FSA` int NOT NULL AUTO_INCREMENT,
  `NAME_FSA` varchar(100) NOT NULL,
  `COUNTER_FSA` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX_FSA`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_salgos`
--

LOCK TABLES `frequency_of_salgos` WRITE;
/*!40000 ALTER TABLE `frequency_of_salgos` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequency_of_salgos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `frequency_of_squerys`
--

DROP TABLE IF EXISTS `frequency_of_squerys`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `frequency_of_squerys` (
  `INDEX_FSQ` int NOT NULL AUTO_INCREMENT,
  `NAME_FSQ` varchar(100) NOT NULL,
  `COUNTER_FSQ` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`INDEX_FSQ`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_squerys`
--

LOCK TABLES `frequency_of_squerys` WRITE;
/*!40000 ALTER TABLE `frequency_of_squerys` DISABLE KEYS */;
/*!40000 ALTER TABLE `frequency_of_squerys` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indexing_collection`
--

LOCK TABLES `indexing_collection` WRITE;
/*!40000 ALTER TABLE `indexing_collection` DISABLE KEYS */;
/*!40000 ALTER TABLE `indexing_collection` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings` DISABLE KEYS */;
/*!40000 ALTER TABLE `ratings` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search_info`
--

DROP TABLE IF EXISTS `search_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `search_info` (
  `INDEX_SEARCH_I` int NOT NULL AUTO_INCREMENT,
  `INDEX_SEARCH_H` int NOT NULL,
  `NAME_DOC_SEARCH_I` varchar(100) NOT NULL,
  `SCORE_SEARCH_I` float NOT NULL,
  PRIMARY KEY (`INDEX_SEARCH_I`),
  KEY `INDEX_SEARCH_H` (`INDEX_SEARCH_H`),
  CONSTRAINT `search_info_ibfk_1` FOREIGN KEY (`INDEX_SEARCH_H`) REFERENCES `searching_history` (`INDEX_SEARCH_H`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_info`
--

LOCK TABLES `search_info` WRITE;
/*!40000 ALTER TABLE `search_info` DISABLE KEYS */;
INSERT INTO `search_info` VALUES (1,1,'Operating_Systems_From_0_to_1.txt',0.0882955),(2,1,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144),(3,2,'Operating_Systems_From_0_to_1.txt',0.0882955),(4,2,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144),(5,3,'Operating_Systems_From_0_to_1.txt',0.0882955),(6,3,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144),(7,4,'Operating_Systems_From_0_to_1.txt',0.0882955),(8,4,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144),(9,5,'Operating_Systems_From_0_to_1.txt',0.0882955),(10,5,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144),(11,6,'Operating_Systems_From_0_to_1.txt',0.0882955),(12,6,'At_the_Worlds_End-Irene_Trimble.txt',0.0288144);
/*!40000 ALTER TABLE `search_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `searching_history`
--

DROP TABLE IF EXISTS `searching_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `searching_history` (
  `INDEX_SEARCH_H` int NOT NULL AUTO_INCREMENT,
  `USER_QUERY_H` varchar(500) NOT NULL,
  `ANALYZER_SEARCH_H` varchar(100) NOT NULL,
  `QPARSER_SEARCH_H` varchar(100) NOT NULL,
  `SALGO_SEARCH_H` varchar(100) NOT NULL,
  `SQUERY_SEARCH_H` varchar(100) NOT NULL,
  `TIME_SEARCH_H` datetime(6) NOT NULL,
  PRIMARY KEY (`INDEX_SEARCH_H`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searching_history`
--

LOCK TABLES `searching_history` WRITE;
/*!40000 ALTER TABLE `searching_history` DISABLE KEYS */;
INSERT INTO `searching_history` VALUES (1,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.531695'),(2,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.004999'),(3,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.015992'),(4,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.010010'),(5,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.009993'),(6,'hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.003022');
/*!40000 ALTER TABLE `searching_history` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-24 22:34:52
