-- MySQLShell dump 2.0.1  Distrib Ver 9.4.0 for Linux on x86_64 - for MySQL 9.4.0 (MySQL Community Server (GPL)), for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IFPR_BIBLIOTECA    Table: BOOKS
-- ------------------------------------------------------
-- Server version	11.4.7

--
-- Current Database: `IFPR_BIBLIOTECA`
--

USE `IFPR_BIBLIOTECA`;

--
-- Table structure for table `BOOKS`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `BOOKS` (
  `ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `TITULO` varchar(50) DEFAULT NULL,
  `AUTOR` varchar(50) DEFAULT NULL,
  `ANO` varchar(50) DEFAULT NULL,
  `QRCODE` varchar(500) DEFAULT NULL,
  `STATUS` char(1) DEFAULT NULL,
  `ID_CATEGORIA` int(11) DEFAULT NULL,
  `ID_LOCALIZACAO` int(11) DEFAULT NULL,
  `PASTA_CAPA` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
