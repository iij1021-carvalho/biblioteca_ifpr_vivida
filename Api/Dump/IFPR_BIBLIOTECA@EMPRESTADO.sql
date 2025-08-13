-- MySQLShell dump 2.0.1  Distrib Ver 9.4.0 for Linux on x86_64 - for MySQL 9.4.0 (MySQL Community Server (GPL)), for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IFPR_BIBLIOTECA    Table: EMPRESTADO
-- ------------------------------------------------------
-- Server version	11.4.7

--
-- Current Database: `IFPR_BIBLIOTECA`
--

USE `IFPR_BIBLIOTECA`;

--
-- Table structure for table `EMPRESTADO`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `EMPRESTADO` (
  `ID_EMPRESTADO` int(11) NOT NULL AUTO_INCREMENT,
  `ID_LIVRO` int(11) DEFAULT 0,
  `ID_USUARIO` int(11) DEFAULT 0,
  `DATA_EMPRESTADO` date NOT NULL,
  `DATA_DEVOLUCAO_EMPRESTADO` date NOT NULL,
  `STATUS_EMPRESTIMO` enum('EMPRESTADO','DEVOLVIDO') DEFAULT 'EMPRESTADO',
  PRIMARY KEY (`ID_EMPRESTADO`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
