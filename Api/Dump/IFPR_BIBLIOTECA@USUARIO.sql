-- MySQLShell dump 2.0.1  Distrib Ver 9.4.0 for Linux on x86_64 - for MySQL 9.4.0 (MySQL Community Server (GPL)), for Linux (x86_64)
--
-- Host: 127.0.0.1    Database: IFPR_BIBLIOTECA    Table: USUARIO
-- ------------------------------------------------------
-- Server version	11.4.7

--
-- Current Database: `IFPR_BIBLIOTECA`
--

USE `IFPR_BIBLIOTECA`;

--
-- Table structure for table `USUARIO`
--

/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE IF NOT EXISTS `USUARIO` (
  `ID_USUARIO` int(11) NOT NULL AUTO_INCREMENT,
  `NOME_USUARIO` varchar(100) NOT NULL,
  `SENHA_USUARIO` varchar(100) NOT NULL,
  `EMAIL_USUARIO` varchar(100) NOT NULL,
  `DATANASCIMENTO_USUARIO` date NOT NULL,
  `IDADE_USUARIO` int(11) NOT NULL,
  `ENDERECO_USUARIO` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`ID_USUARIO`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
