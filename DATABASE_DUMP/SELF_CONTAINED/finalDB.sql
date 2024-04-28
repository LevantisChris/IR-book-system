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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_analyzers`
--

LOCK TABLES `frequency_of_analyzers` WRITE;
/*!40000 ALTER TABLE `frequency_of_analyzers` DISABLE KEYS */;
INSERT INTO `frequency_of_analyzers` VALUES (1,'ANALYZER_ENGLISH',17),(2,'ANALYZER_STANDARD',6),(3,'ANALYZER_WHITESPACE',3),(4,'ANALYZER_SIMPLE',6),(5,'ANALYZER_STOP',4),(6,'ANALYZER_KEYWORD',6),(7,'ANALYZER_SIMPLE_WHITE_SPACE',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_qparsers`
--

LOCK TABLES `frequency_of_qparsers` WRITE;
/*!40000 ALTER TABLE `frequency_of_qparsers` DISABLE KEYS */;
INSERT INTO `frequency_of_qparsers` VALUES (1,'QPARSER_STANDARD',10),(2,'QPARSER_MULTIFIELD',1),(3,'QPARSER_COMPLEX_PHRASE',2),(4,'QPARSER_SIMPLE',3);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_salgos`
--

LOCK TABLES `frequency_of_salgos` WRITE;
/*!40000 ALTER TABLE `frequency_of_salgos` DISABLE KEYS */;
INSERT INTO `frequency_of_salgos` VALUES (1,'SIMIALGO_TFIDF',30),(2,'SIMIALGO_BM25',12);
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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `frequency_of_squerys`
--

LOCK TABLES `frequency_of_squerys` WRITE;
/*!40000 ALTER TABLE `frequency_of_squerys` DISABLE KEYS */;
INSERT INTO `frequency_of_squerys` VALUES (1,'SQUERY_TERM',3),(2,'SQUERY_WILDCARD',22),(3,'SQUERY_PREFIX',1),(4,'SQUERY_BOOLEAN',0);
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
) ENGINE=InnoDB AUTO_INCREMENT=687 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search_info`
--

LOCK TABLES `search_info` WRITE;
/*!40000 ALTER TABLE `search_info` DISABLE KEYS */;
INSERT INTO `search_info` VALUES (449,156,'Operating_Systems_From_0_to_1.txt',0.129764),(450,156,'The_Curse_of_the_Black_Pearl-Irene_Trimble.txt',0.055334),(451,156,'A_Ghost-Robert_Harris.txt',0.0488499),(452,156,'Murder_in_the_Fog-Dominic_Butler.txt',0.0267605),(453,156,'Guide_to_Network_Programming.txt',0.0207327),(454,156,'The_Accidental_Tourist-Anne_Tyler.txt',0.0197631),(455,156,'At_the_Worlds_End-Irene_Trimble.txt',0.0181727),(456,156,'Hotel-Arthur_Hailey.txt',0.00988417),(457,159,'At_the_Worlds_End-Irene_Trimble.txt',1),(458,159,'A_Ghost-Robert_Harris.txt',1),(459,159,'Guide_to_Network_Programming.txt',1),(460,159,'Hotel-Arthur_Hailey.txt',1),(461,159,'Murder_in_the_Fog-Dominic_Butler.txt',1),(462,159,'Operating_Systems_From_0_to_1.txt',1),(463,159,'The_Accidental_Tourist-Anne_Tyler.txt',1),(464,159,'The_Curse_of_the_Black_Pearl-Irene_Trimble.txt',1),(465,160,'Operating_Systems_From_0_to_1.txt',0.129764),(466,160,'The_Curse_of_the_Black_Pearl-Irene_Trimble.txt',0.055334),(467,160,'A_Ghost-Robert_Harris.txt',0.0488499),(468,160,'Murder_in_the_Fog-Dominic_Butler.txt',0.0267605),(469,160,'Guide_to_Network_Programming.txt',0.0207327),(470,160,'The_Accidental_Tourist-Anne_Tyler.txt',0.0197631),(471,160,'At_the_Worlds_End-Irene_Trimble.txt',0.0181727),(472,160,'Hotel-Arthur_Hailey.txt',0.00988417),(473,161,'A_Ghost-Robert_Harris.txt',0.105898),(474,161,'Hotel-Arthur_Hailey.txt',0.0912592),(475,161,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(476,161,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(477,161,'The Bishop\'s purse.txt',0.0413378),(478,161,'Code.txt',0.029203),(479,161,'The doctor.txt',0.0144844),(480,161,'taoofseneca_vol1-1.txt',0.0139931),(481,161,'taoofseneca_vol3.txt',0.00730756),(482,162,'A_Ghost-Robert_Harris.txt',0.105898),(483,162,'Hotel-Arthur_Hailey.txt',0.0912592),(484,162,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(485,162,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(486,162,'The Bishop\'s purse.txt',0.0413378),(487,162,'Code.txt',0.029203),(488,162,'The doctor.txt',0.0144844),(489,162,'taoofseneca_vol1-1.txt',0.0139931),(490,162,'taoofseneca_vol3.txt',0.00730756),(491,163,'A_Ghost-Robert_Harris.txt',0.105898),(492,163,'Hotel-Arthur_Hailey.txt',0.0912592),(493,163,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(494,163,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(495,163,'The Bishop\'s purse.txt',0.0413378),(496,163,'Code.txt',0.029203),(497,163,'The doctor.txt',0.0144844),(498,163,'taoofseneca_vol1-1.txt',0.0139931),(499,163,'taoofseneca_vol3.txt',0.00730756),(500,165,'A_Ghost-Robert_Harris.txt',0.105898),(501,165,'Hotel-Arthur_Hailey.txt',0.0912592),(502,165,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(503,165,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(504,165,'The Bishop\'s purse.txt',0.0413378),(505,165,'Code.txt',0.029203),(506,165,'The doctor.txt',0.0144844),(507,165,'taoofseneca_vol1-1.txt',0.0139931),(508,165,'taoofseneca_vol3.txt',0.00730756),(509,166,'A_Ghost-Robert_Harris.txt',0.105898),(510,166,'Hotel-Arthur_Hailey.txt',0.0912592),(511,166,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(512,166,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(513,166,'The Bishop\'s purse.txt',0.0413378),(514,166,'Code.txt',0.029203),(515,166,'The doctor.txt',0.0144844),(516,166,'taoofseneca_vol1-1.txt',0.0139931),(517,166,'taoofseneca_vol3.txt',0.00730756),(518,167,'A_Ghost-Robert_Harris.txt',0.105898),(519,167,'Hotel-Arthur_Hailey.txt',0.0912592),(520,167,'Murder_in_the_Fog-Dominic_Butler.txt',0.0685266),(521,167,'The_Accidental_Tourist-Anne_Tyler.txt',0.0669481),(522,167,'The Bishop\'s purse.txt',0.0413378),(523,167,'Code.txt',0.029203),(524,167,'The doctor.txt',0.0144844),(525,167,'taoofseneca_vol1-1.txt',0.0139931),(526,167,'taoofseneca_vol3.txt',0.00730756),(527,168,'Hotel-Arthur_Hailey.txt',1.22567),(528,168,'A_Ghost-Robert_Harris.txt',1.22565),(529,168,'Murder_in_the_Fog-Dominic_Butler.txt',1.20431),(530,168,'The_Accidental_Tourist-Anne_Tyler.txt',1.20105),(531,168,'The Bishop\'s purse.txt',1.16554),(532,168,'Code.txt',0.981884),(533,168,'The doctor.txt',0.808355),(534,168,'taoofseneca_vol1-1.txt',0.78327),(535,168,'taoofseneca_vol3.txt',0.390654),(536,169,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',1),(537,169,'At_the_Worlds_End-Irene_Trimble.txt',1),(538,169,'A_Ghost-Robert_Harris.txt',1),(539,169,'A_Study_in_Scarlet-Conan_Doyle.txt',1),(540,169,'Barchester_Towers-Anthony_Trollope.txt',1),(541,169,'Code.txt',1),(542,169,'Guide_to_Network_Programming.txt',1),(543,169,'Hotel-Arthur_Hailey.txt',1),(544,169,'Hunted_down-Charles_Dickens.txt',1),(545,169,'Introduction to Data Compression (4th Edition).txt',1),(546,170,'Hotel-Arthur_Hailey.txt',1.22567),(547,170,'A_Ghost-Robert_Harris.txt',1.22565),(548,170,'Murder_in_the_Fog-Dominic_Butler.txt',1.20431),(549,170,'The_Accidental_Tourist-Anne_Tyler.txt',1.20105),(550,170,'The Bishop\'s purse.txt',1.16554),(551,170,'Code.txt',0.981884),(552,170,'The doctor.txt',0.808355),(553,170,'taoofseneca_vol1-1.txt',0.78327),(554,170,'taoofseneca_vol3.txt',0.390654),(555,171,'A_Ghost-Robert_Harris.txt',1),(556,171,'Code.txt',1),(557,171,'Hotel-Arthur_Hailey.txt',1),(558,171,'Murder_in_the_Fog-Dominic_Butler.txt',1),(559,171,'taoofseneca_vol1-1.txt',1),(560,171,'taoofseneca_vol3.txt',1),(561,171,'The Bishop\'s purse.txt',1),(562,171,'The doctor.txt',1),(563,171,'The_Accidental_Tourist-Anne_Tyler.txt',1),(564,172,'A_Ghost-Robert_Harris.txt',1),(565,172,'Code.txt',1),(566,172,'Hotel-Arthur_Hailey.txt',1),(567,172,'Murder_in_the_Fog-Dominic_Butler.txt',1),(568,172,'taoofseneca_vol1-1.txt',1),(569,172,'taoofseneca_vol3.txt',1),(570,172,'The Bishop\'s purse.txt',1),(571,172,'The doctor.txt',1),(572,172,'The_Accidental_Tourist-Anne_Tyler.txt',1),(573,173,'A_Ghost-Robert_Harris.txt',1),(574,173,'Code.txt',1),(575,173,'Hotel-Arthur_Hailey.txt',1),(576,173,'Murder_in_the_Fog-Dominic_Butler.txt',1),(577,173,'taoofseneca_vol1-1.txt',1),(578,173,'taoofseneca_vol3.txt',1),(579,173,'The Bishop\'s purse.txt',1),(580,173,'The doctor.txt',1),(581,173,'The_Accidental_Tourist-Anne_Tyler.txt',1),(582,174,'A_Ghost-Robert_Harris.txt',1),(583,174,'Code.txt',1),(584,174,'Hotel-Arthur_Hailey.txt',1),(585,174,'Murder_in_the_Fog-Dominic_Butler.txt',1),(586,174,'taoofseneca_vol1-1.txt',1),(587,174,'taoofseneca_vol3.txt',1),(588,174,'The Bishop\'s purse.txt',1),(589,174,'The doctor.txt',1),(590,174,'The_Accidental_Tourist-Anne_Tyler.txt',1),(591,181,'A_Ghost-Robert_Harris.txt',1),(592,181,'Code.txt',1),(593,181,'Hotel-Arthur_Hailey.txt',1),(594,181,'Murder_in_the_Fog-Dominic_Butler.txt',1),(595,181,'taoofseneca_vol1-1.txt',1),(596,181,'taoofseneca_vol3.txt',1),(597,181,'The Bishop\'s purse.txt',1),(598,181,'The doctor.txt',1),(599,181,'The_Accidental_Tourist-Anne_Tyler.txt',1),(600,182,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',1),(601,182,'At_the_Worlds_End-Irene_Trimble.txt',1),(602,182,'A_Ghost-Robert_Harris.txt',1),(603,182,'A_Study_in_Scarlet-Conan_Doyle.txt',1),(604,182,'Barchester_Towers-Anthony_Trollope.txt',1),(605,182,'Code.txt',1),(606,182,'Guide_to_Network_Programming.txt',1),(607,182,'Hotel-Arthur_Hailey.txt',1),(608,182,'Hunted_down-Charles_Dickens.txt',1),(609,182,'Introduction to Data Compression (4th Edition).txt',1),(610,183,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',1),(611,183,'A_Study_in_Scarlet-Conan_Doyle.txt',1),(612,183,'Barchester_Towers-Anthony_Trollope.txt',1),(613,183,'Code.txt',1),(614,183,'Guide_to_Network_Programming.txt',1),(615,183,'Hotel-Arthur_Hailey.txt',1),(616,183,'Introduction to Data Compression (4th Edition).txt',1),(617,183,'Louie\'s married life.txt',1),(618,183,'Murder_in_the_Fog-Dominic_Butler.txt',1),(619,183,'Nerve enough.txt',1),(620,184,'A_Ghost-Robert_Harris.txt',1),(621,184,'Code.txt',1),(622,184,'Hotel-Arthur_Hailey.txt',1),(623,184,'Murder_in_the_Fog-Dominic_Butler.txt',1),(624,184,'taoofseneca_vol1-1.txt',1),(625,184,'taoofseneca_vol3.txt',1),(626,184,'The Bishop\'s purse.txt',1),(627,184,'The doctor.txt',1),(628,184,'The_Accidental_Tourist-Anne_Tyler.txt',1),(629,185,'A_Ghost-Robert_Harris.txt',1),(630,185,'Code.txt',1),(631,185,'Hotel-Arthur_Hailey.txt',1),(632,185,'Murder_in_the_Fog-Dominic_Butler.txt',1),(633,185,'taoofseneca_vol1-1.txt',1),(634,185,'taoofseneca_vol3.txt',1),(635,185,'The Bishop\'s purse.txt',1),(636,185,'The doctor.txt',1),(637,185,'The_Accidental_Tourist-Anne_Tyler.txt',1),(638,192,'taoofseneca_vol1-1.txt',2.15945),(639,192,'The doctor.txt',1.96977),(640,192,'Code.txt',1.85693),(641,192,'Murder_in_the_Fog-Dominic_Butler.txt',1.83932),(642,192,'Hotel-Arthur_Hailey.txt',1.80207),(643,192,'taoofseneca_vol3.txt',1.74053),(644,192,'The Bishop\'s purse.txt',1.65405),(645,192,'Sailing ships.txt',1.53656),(646,192,'taoofseneca_vol2.txt',1.45087),(647,192,'A_Ghost-Robert_Harris.txt',1.22565),(648,193,'Operating_Systems_From_0_to_1.txt',0.0934707),(649,193,'The_Curse_of_the_Black_Pearl-Irene_Trimble.txt',0.0578471),(650,193,'A_Ghost-Robert_Harris.txt',0.0510684),(651,193,'Murder_in_the_Fog-Dominic_Butler.txt',0.0279759),(652,193,'The Bishop\'s purse.txt',0.0242364),(653,193,'Guide_to_Network_Programming.txt',0.0216743),(654,193,'The_Accidental_Tourist-Anne_Tyler.txt',0.0206606),(655,193,'At_the_Worlds_End-Irene_Trimble.txt',0.0197723),(656,193,'Hotel-Arthur_Hailey.txt',0.0103331),(657,194,'Operating_Systems_From_0_to_1.txt',0.0992738),(658,194,'Introduction to Data Compression (4th Edition).txt',0.0984855),(659,194,'Guide_to_Network_Programming.txt',0.0515123),(660,194,'Nerve enough.txt',0.0471562),(661,194,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',0.0419093),(662,194,'The doctor.txt',0.0380781),(663,194,'Sailing ships.txt',0.0373387),(664,194,'Code.txt',0.0336667),(665,194,'taoofseneca_vol3.txt',0.0297851),(666,194,'Louie\'s married life.txt',0.0168468),(667,195,'Operating_Systems_From_0_to_1.txt',0.0992738),(668,195,'Introduction to Data Compression (4th Edition).txt',0.0984855),(669,195,'Guide_to_Network_Programming.txt',0.0515123),(670,195,'Nerve enough.txt',0.0471562),(671,195,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',0.0419093),(672,195,'The doctor.txt',0.0380781),(673,195,'Sailing ships.txt',0.0373387),(674,195,'Code.txt',0.0336667),(675,195,'taoofseneca_vol3.txt',0.0297851),(676,195,'Louie\'s married life.txt',0.0168468),(677,197,'Operating_Systems_From_0_to_1.txt',0.0992738),(678,197,'Introduction to Data Compression (4th Edition).txt',0.0984855),(679,197,'Guide_to_Network_Programming.txt',0.0515123),(680,197,'Nerve enough.txt',0.0471562),(681,197,'1. Eastern Philosophy Author J.S.R.L. Narayana Moorty.txt',0.0419093),(682,197,'The doctor.txt',0.0380781),(683,197,'Sailing ships.txt',0.0373387),(684,197,'Code.txt',0.0336667),(685,197,'taoofseneca_vol3.txt',0.0297851),(686,197,'Louie\'s married life.txt',0.0168468);
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
  `QPARSER_SEARCH_H` varchar(100) DEFAULT NULL,
  `SALGO_SEARCH_H` varchar(100) NOT NULL,
  `SQUERY_SEARCH_H` varchar(100) DEFAULT NULL,
  `TIME_SEARCH_H` datetime(6) NOT NULL,
  PRIMARY KEY (`INDEX_SEARCH_H`)
) ENGINE=InnoDB AUTO_INCREMENT=198 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `searching_history`
--

LOCK TABLES `searching_history` WRITE;
/*!40000 ALTER TABLE `searching_history` DISABLE KEYS */;
INSERT INTO `searching_history` VALUES (156,'Hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.544687'),(157,'Hello','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.008995'),(158,'Hello','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.031973'),(159,'hello','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.014991'),(160,'hello','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_TERM','1970-01-01 00:00:00.008994'),(161,'car','ANALYZER_STANDARD','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.318410'),(162,'car','ANALYZER_WHITESPACE','QPARSER_COMPLEX_PHRASE','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.006995'),(163,'car','ANALYZER_SIMPLE','QPARSER_SIMPLE','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.007020'),(164,'car','ANALYZER_KEYWORD','QPARSER_SIMPLE','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.003996'),(165,'car','ANALYZER_STOP','QPARSER_SIMPLE','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.051820'),(166,'car','ANALYZER_STOP','QPARSER_COMPLEX_PHRASE','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.003977'),(167,'car','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.005975'),(168,'car','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_BM25',NULL,'1970-01-01 00:00:00.004000'),(169,'car','ANALYZER_ENGLISH',NULL,'SIMIALGO_BM25','SQUERY_PREFIX','1970-01-01 00:00:00.022986'),(170,'car','ANALYZER_ENGLISH',NULL,'SIMIALGO_BM25','SQUERY_TERM','1970-01-01 00:00:00.002994'),(171,'car','ANALYZER_ENGLISH',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.005896'),(172,'car','ANALYZER_WHITESPACE',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.004023'),(173,'car','ANALYZER_SIMPLE',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.003000'),(174,'car','ANALYZER_STOP',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.003013'),(175,'car and','ANALYZER_STANDARD',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.002978'),(176,'car and','ANALYZER_SIMPLE',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.002997'),(177,'car and','ANALYZER_KEYWORD',NULL,'SIMIALGO_BM25','SQUERY_WILDCARD','1970-01-01 00:00:00.002976'),(178,'car and','ANALYZER_KEYWORD','QPARSER_MULTIFIELD','SIMIALGO_BM25',NULL,'1970-01-01 00:00:00.010992'),(179,'car and','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.017810'),(180,'Hello','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.002996'),(181,'car','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.003001'),(182,'car*','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.007997'),(183,'obs*','ANALYZER_ENGLISH',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.003997'),(184,'car','ANALYZER_STANDARD',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.004995'),(185,'car','ANALYZER_SIMPLE',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.003986'),(186,'car shippment','ANALYZER_KEYWORD',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.002996'),(187,'car shipment','ANALYZER_KEYWORD',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.003001'),(188,'car ship result','ANALYZER_KEYWORD',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.002997'),(189,'car ship result','ANALYZER_STOP',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.004017'),(190,'car ship result','ANALYZER_SIMPLE',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.003000'),(191,'car ship result','ANALYZER_STANDARD',NULL,'SIMIALGO_TFIDF','SQUERY_WILDCARD','1970-01-01 00:00:00.004995'),(192,'car ship result','ANALYZER_STANDARD','QPARSER_STANDARD','SIMIALGO_BM25',NULL,'1970-01-01 00:00:00.006996'),(193,'Hello','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.109938'),(194,'C#','ANALYZER_ENGLISH','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.004995'),(195,'C#','ANALYZER_STANDARD','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.003991'),(196,'C#','ANALYZER_WHITESPACE','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.006995'),(197,'C#','ANALYZER_SIMPLE','QPARSER_STANDARD','SIMIALGO_TFIDF',NULL,'1970-01-01 00:00:00.003561');
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

-- Dump completed on 2024-04-28 19:27:06
