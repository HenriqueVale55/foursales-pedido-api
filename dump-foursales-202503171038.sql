-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: foursales
-- ------------------------------------------------------
-- Server version	9.2.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `id_pedido` varchar(255) DEFAULT NULL,
  `id_usuario` varchar(255) DEFAULT NULL,
  `id_produto` varchar(255) DEFAULT NULL,
  `data_pedido` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `quantidade_produto` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9estgt6frorcbiblkbqruek29` (`id_produto`),
  KEY `FK9ggns8wgi350asplcie001cv4` (`id_usuario`),
  CONSTRAINT `FK9estgt6frorcbiblkbqruek29` FOREIGN KEY (`id_produto`) REFERENCES `produto` (`id`),
  CONSTRAINT `FK9ggns8wgi350asplcie001cv4` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (5,'e4c30247-11b9-4c28-a8b6-2bf67d4a7185','067e7f01-750a-428e-a628-8e593908a017','75f0dafc-cfb8-4dd6-9a9c-66e7b6693a8d','2025-03-17 03:09:02.389000','PAGO',1),(6,'e4c30247-11b9-4c28-a8b6-2bf67d4a7185','067e7f01-750a-428e-a628-8e593908a017','8581f119-c8a6-4591-b6e5-fa063edbcf74','2025-03-17 03:09:02.492000','PAGO',1),(7,'e4c30247-11b9-4c28-a8b6-2bf67d4a7185','067e7f01-750a-428e-a628-8e593908a017','d2136332-c02a-42a3-9989-2fbf579fd5f0','2025-03-17 03:09:02.528000','PAGO',1),(8,'ff1bbe1b-38f6-4f2b-9fa5-cc79426989be','55a0e55a-dd92-473c-842e-7dbba7c7a8a0','75f0dafc-cfb8-4dd6-9a9c-66e7b6693a8d','2025-03-17 03:10:47.106000','PAGO',1),(9,'ff1bbe1b-38f6-4f2b-9fa5-cc79426989be','55a0e55a-dd92-473c-842e-7dbba7c7a8a0','8581f119-c8a6-4591-b6e5-fa063edbcf74','2025-03-17 03:10:47.139000','PAGO',1),(10,'83acc34c-89b6-4ca2-b28d-925b428f12a4','55a0e55a-dd92-473c-842e-7dbba7c7a8a0','d2136332-c02a-42a3-9989-2fbf579fd5f0','2025-03-17 03:32:34.175000','PENDENTE',1),(11,'593108e9-7b01-424e-b61f-d739a74d2dee','fdc1d95c-53b9-4233-b7db-054506a79321','75f0dafc-cfb8-4dd6-9a9c-66e7b6693a8d','2025-03-17 13:11:34.059000','PAGO',1),(12,'dea09db8-bbf8-4498-979f-3ac61fdf6480','067e7f01-750a-428e-a628-8e593908a017','d2136332-c02a-42a3-9989-2fbf579fd5f0','2025-03-17 13:21:16.317000','PAGO',5),(13,'0531b7e4-1d44-4cad-b1cf-94e20968e291','067e7f01-750a-428e-a628-8e593908a017','d2136332-c02a-42a3-9989-2fbf579fd5f0','2025-03-17 13:21:22.391000','CANCELADO',2);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `produto`
--

DROP TABLE IF EXISTS `produto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `produto` (
  `id` varchar(255) NOT NULL,
  `nome` varchar(255) DEFAULT NULL,
  `descricao` varchar(255) DEFAULT NULL,
  `preco` float NOT NULL,
  `categoria` varchar(255) DEFAULT NULL,
  `quantidade_estoque` int NOT NULL,
  `data_criacao` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `produto`
--

LOCK TABLES `produto` WRITE;
/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` VALUES ('75f0dafc-cfb8-4dd6-9a9c-66e7b6693a8d','Guaraná','Guaraná 2L',9.5,'Bebidas',5,'2025-03-13 00:00:00.000000'),('8581f119-c8a6-4591-b6e5-fa063edbcf74','Cebolitos','Salgadinho Cebolitos',5.5,'Salgadinhos',8,'2025-03-13 00:00:00.000000'),('d2136332-c02a-42a3-9989-2fbf579fd5f0','Coca Cola','Coca Cola 2 Litros',10.5,'Bebidas',0,'2025-03-13 00:00:00.000000');
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `id` varchar(255) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` tinyint DEFAULT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `usuario_chk_1` CHECK ((`role` between 0 and 1))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('067e7f01-750a-428e-a628-8e593908a017','henrique','$2a$10$1ReNjv7xPtPN4xJMmnyHO.8YSHuh1CWXlEihMResK6Nc9a2vyf.7C',0),('55a0e55a-dd92-473c-842e-7dbba7c7a8a0','user','$2a$10$mhA3thiFkjX.FM7OabzgJugomaEh2H073kzm2bHhKXK4QUuMQ69Jq',1),('fdc1d95c-53b9-4233-b7db-054506a79321','admin','$2a$10$uczFiRqBLFb5zNHimvcOi.ktOmUgWFkRGqtxSx5udFnGaVTel1P4i',0);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'foursales'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-17 10:38:56
