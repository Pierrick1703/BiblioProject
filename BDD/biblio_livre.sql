-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: biblio
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livre` (
  `idLivre` int NOT NULL AUTO_INCREMENT,
  `titre` varchar(250) NOT NULL,
  `auteur` varchar(250) NOT NULL,
  `presentation` varchar(250) NOT NULL,
  `parution` int NOT NULL,
  `colonne` smallint NOT NULL,
  `rangee` smallint NOT NULL,
  `resumer` text NOT NULL,
  `etat` varchar(40) NOT NULL,
  PRIMARY KEY (`idLivre`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre`
--

LOCK TABLES `livre` WRITE;
/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
INSERT INTO `livre` VALUES (1,'La Métamorphose','Gérard RudentBrigitte Vergne-CainFranz Kafka','https://www.hachette.fr/sites/default/files/styles/echelle_232x232/public/images/livres/couv/9782253049807-001-T.jpeg?itok=NYQ2D3ij',1989,1,2,'Voilà une intrigue qui donne tort à ceux qui pensent que “ce n’est pas la petite bête qui va manger la grande” ! Quand l’identité vacille entre le corps et l’esprit, il faut savoir se méfier des apparences…','disponible'),(2,'Le Joueur','Fedor DostoïevskiPierre SipriotC. AndronikofA. de Couriss','https://www.hachette.fr/sites/default/files/styles/echelle_232x232/public/images/livres/couv/9782253011736-001-T.jpeg?itok=WIowsfav',1972,1,3,'Au sein d’une famille russe presque ruinée, un jeune précepteur pris par l’ivresse du jeu voit son destin s’effondrer comme un château de cartes. Un ouvrage “bluffant” !','prété'),(3,'Le Jardin d\'Épicure','Irvin YalomAnne Damour','https://www.hachette.fr/sites/default/files/styles/echelle_232x232/public/images/livres/couv/9782253168645-001-T.jpeg?itok=bEfxV0G-',2015,2,1,'Pourquoi craindre la mort si, lorsqu’elle arrive, nous ne sommes plus ? L’une des plus grosses angoisses de l’humanité analysée en toute transparence.','prété'),(4,'Shining','Stephen King','https://www.hachette.fr/sites/default/files/styles/echelle_232x232/public/images/livres/couv/9782253151623-001-T.jpeg?itok=HLY1k2nU',2007,2,4,'Après avoir lu ce roman d’horreur, vous ne verrez plus vos vacances à la montagne du même oeil... PS : avant de partir, laissez votre boîte à outils à la maison !','disponible'),(8,'Shining','Stephen King','https://www.hachette.fr/sites/default/files/styles/echelle_232x232/public/images/livres/couv/9782253151623-001-T.jpeg?itok=HLY1k2nU',2007,2,4,'Après avoir lu ce roman d’horreur, vous ne verrez plus vos vacances à la montagne du même oeil... PS : avant de partir, laissez votre boîte à outils à la maison !','disponible');
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-08  3:27:48
