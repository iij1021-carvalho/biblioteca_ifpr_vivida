-- MySQLShell dump 2.0.1  Distrib Ver 9.4.0 for Linux on x86_64 - for MySQL 9.4.0 (MySQL Community Server (GPL)), for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IFPR_BIBLIOTECA    Table: LOCALIZACAO_LIVRO
-- ------------------------------------------------------
-- Server version	11.4.7

--
-- Current Database: `IFPR_BIBLIOTECA`
--

USE `IFPR_BIBLIOTECA`;

--
-- Table structure for table `LOCALIZACAO_LIVRO`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `LOCALIZACAO_LIVRO` (
  `ID_LOCALIZACAO` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO_LOCALIZACAO` varchar(200) NOT NULL,
  `FOTO_LOCALIZACAO` varchar(500) NOT NULL,
  PRIMARY KEY (`ID_LOCALIZACAO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
