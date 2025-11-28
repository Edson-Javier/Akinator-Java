/*M!999999\- enable the sandbox mode */ 
-- MariaDB dump 10.19  Distrib 10.11.11-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: Red_Neuronal
-- ------------------------------------------------------
-- Server version	10.11.11-MariaDB-0+deb12u1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `Personajes_entrenamiento`
--

DROP TABLE IF EXISTS `Personajes_entrenamiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `Personajes_entrenamiento` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `Es_sayayin` tinyint(1) DEFAULT NULL,
  `Es_namekiano` tinyint(1) DEFAULT NULL,
  `Es_androide` tinyint(1) DEFAULT NULL,
  `Es_humano` tinyint(1) DEFAULT NULL,
  `Es_dios` tinyint(1) DEFAULT NULL,
  `vuela` tinyint(1) DEFAULT NULL,
  `usa_ki` tinyint(1) DEFAULT NULL,
  `tiene_cola` tinyint(1) DEFAULT NULL,
  `Es_verde` tinyint(1) DEFAULT NULL,
  `es_amigo_de_goku` tinyint(1) DEFAULT NULL,
  `es_enemigo_de_goku` tinyint(1) DEFAULT NULL,
  `ha_revivido` tinyint(1) DEFAULT NULL,
  `tiene_transformaciones` tinyint(1) DEFAULT NULL,
  `es_mujer` tinyint(1) DEFAULT NULL,
  `pertenece_al_ejercito_de_freezer` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Personajes_entrenamiento`
--

LOCK TABLES `Personajes_entrenamiento` WRITE;
/*!40000 ALTER TABLE `Personajes_entrenamiento` DISABLE KEYS */;
INSERT INTO `Personajes_entrenamiento` VALUES
(1,'Android 18',0,0,1,0,0,1,1,0,0,1,1,0,0,1,0),
(2,'Trunks',1,0,0,0,0,1,1,0,0,1,0,1,1,0,0),
(3,'Goku',1,0,0,0,0,1,1,1,0,0,0,1,1,0,0),
(4,'Vegeta',1,0,0,0,0,1,1,1,0,1,0,1,1,0,0),
(5,'Piccolo',0,1,0,0,0,1,1,0,1,1,0,1,1,0,0),
(6,'Krillin',0,0,0,1,0,1,1,0,0,1,0,1,0,0,0),
(7,'Freezer',0,0,0,0,0,1,1,0,0,0,1,1,1,0,1),
(8,'Kale',1,0,0,0,0,1,1,0,1,1,1,0,1,1,0),
(9,'Cell',0,0,1,0,0,1,1,0,1,0,1,1,1,0,0),
(10,'Gohan',1,0,0,0,0,1,1,0,0,1,0,1,1,0,0),
(11,'Majin Buu',0,0,0,0,0,1,1,0,0,1,1,1,1,0,0);
/*!40000 ALTER TABLE `Personajes_entrenamiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'Red_Neuronal'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-11  9:35:07
