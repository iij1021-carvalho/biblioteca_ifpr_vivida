-- MySQLShell dump 2.0.1  Distrib Ver 9.4.0 for Linux on x86_64 - for MySQL 9.4.0 (MySQL Community Server (GPL)), for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IFPR_BIBLIOTECA    Table: CATEGORIA_LIVRO
-- ------------------------------------------------------
-- Server version	11.4.7

--
-- Current Database: `IFPR_BIBLIOTECA`
--

USE `IFPR_BIBLIOTECA`;

--
-- Table structure for table `CATEGORIA_LIVRO`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `CATEGORIA_LIVRO` (
  `ID_CATEGORIA_LIVRO` int(11) NOT NULL AUTO_INCREMENT,
  `DESCRICAO_CATEGORIA` varchar(200) NOT NULL,
  `ATIVO_CATEGORIA` tinyint(1) DEFAULT 0,
  PRIMARY KEY (`ID_CATEGORIA_LIVRO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
