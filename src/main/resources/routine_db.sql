DROP DATABASE IF EXISTS `routine_db`;
CREATE DATABASE `routine_db` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_uca1400_ai_ci;
USE `routine_db`;
/*M!999999\- enable the sandbox mode */
-- MariaDB dump 10.19-11.7.2-MariaDB, for Win64 (AMD64)
--
-- Host: localhost    Database: routine_db
-- ------------------------------------------------------
-- Server version	12.0.2-MariaDB-ubu2404

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*M!100616 SET @OLD_NOTE_VERBOSITY=@@NOTE_VERBOSITY, NOTE_VERBOSITY=0 */;

--
-- Table structure for table `alert`
--

DROP TABLE IF EXISTS `alert`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert` (
                         `alert_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `ora_fine` datetime(6) NOT NULL,
                         `ora_inizio` datetime(6) NOT NULL,
                         `routine_id` bigint(20) DEFAULT NULL,
                         `citta` varchar(100) NOT NULL,
                         `testo_notifica` varchar(100) NOT NULL,
                         PRIMARY KEY (`alert_id`),
                         KEY `FK2g12bcvoa9syk1nbjyrdjd2bn` (`routine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert`
--

LOCK TABLES `alert` WRITE;
/*!40000 ALTER TABLE `alert` DISABLE KEYS */;
INSERT INTO `alert` VALUES
                        (1,'2019-03-27 10:15:30.000000','2019-03-27 10:15:30.000000',1,'Pescara','Sveglia mattutina'),
                        (2,'2019-03-27 10:15:30.000000','2019-03-27 10:15:30.000000',2,'Pescara','Sveglia mattutina'),
                        (4,'2019-03-27 10:15:30.000000','2019-03-27 10:15:30.000000',1,'Pescara','Sveglia mattutina'),
                        (5,'2019-03-27 10:15:30.000000','2019-03-27 10:15:30.000000',2,'Pescara','Sveglia mattutina'),
                        (7,'2019-03-27 10:15:30.000000','2019-03-27 10:15:30.000000',4,'Silvi','Sveglia mattutina');
/*!40000 ALTER TABLE `alert` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `alert_weather_codes`
--

DROP TABLE IF EXISTS `alert_weather_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `alert_weather_codes` (
                                       `alert_alert_id` bigint(20) NOT NULL,
                                       `weather_code_code` bigint(20) NOT NULL,
                                       PRIMARY KEY (`alert_alert_id`,`weather_code_code`),
                                       KEY `FK2fwopx1dkjmmtackyxa1lpgwp` (`weather_code_code`),
                                       CONSTRAINT `FK2fwopx1dkjmmtackyxa1lpgwp` FOREIGN KEY (`weather_code_code`) REFERENCES `weather_codes` (`code`),
                                       CONSTRAINT `FK79dwtsh3cyn78gjr2lca9ivnu` FOREIGN KEY (`alert_alert_id`) REFERENCES `alert` (`alert_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `alert_weather_codes`
--

LOCK TABLES `alert_weather_codes` WRITE;
/*!40000 ALTER TABLE `alert_weather_codes` DISABLE KEYS */;
INSERT INTO `alert_weather_codes` VALUES
                                      (4,1000),
                                      (5,1000),
                                      (7,1000),
                                      (4,1003),
                                      (5,1003),
                                      (7,1003);
/*!40000 ALTER TABLE `alert_weather_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routine`
--

DROP TABLE IF EXISTS `routine`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `routine` (
                           `routine_id` bigint(20) NOT NULL AUTO_INCREMENT,
                           `nome_routine` varchar(255) NOT NULL,
                           PRIMARY KEY (`routine_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routine`
--

LOCK TABLES `routine` WRITE;
/*!40000 ALTER TABLE `routine` DISABLE KEYS */;
INSERT INTO `routine` VALUES
                          (1,'mattina-lavoro'),
                          (2,'mattina-lavoro'),
                          (4,'mattina-lavoro');
/*!40000 ALTER TABLE `routine` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `weather_codes`
--

DROP TABLE IF EXISTS `weather_codes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8mb4 */;
CREATE TABLE `weather_codes` (
                                 `icon` int(11) DEFAULT NULL,
                                 `code` bigint(20) NOT NULL,
                                 `day` varchar(255) DEFAULT NULL,
                                 `night` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `weather_codes`
--

LOCK TABLES `weather_codes` WRITE;
/*!40000 ALTER TABLE `weather_codes` DISABLE KEYS */;
INSERT INTO `weather_codes` VALUES
                                (113,1000,'Sunny','Clear'),
                                (116,1003,'Partly cloudy','Partly cloudy'),
                                (119,1006,'Cloudy','Cloudy'),
                                (122,1009,'Overcast','Overcast'),
                                (143,1030,'Mist','Mist'),
                                (176,1063,'Patchy rain possible','Patchy rain possible'),
                                (179,1066,'Patchy snow possible','Patchy snow possible'),
                                (182,1069,'Patchy sleet possible','Patchy sleet possible'),
                                (185,1072,'Patchy freezing drizzle possible','Patchy freezing drizzle possible'),
                                (200,1087,'Thundery outbreaks possible','Thundery outbreaks possible'),
                                (227,1114,'Blowing snow','Blowing snow'),
                                (230,1117,'Blizzard','Blizzard'),
                                (248,1135,'Fog','Fog'),
                                (260,1147,'Freezing fog','Freezing fog'),
                                (263,1150,'Patchy light drizzle','Patchy light drizzle'),
                                (266,1153,'Light drizzle','Light drizzle'),
                                (281,1168,'Freezing drizzle','Freezing drizzle'),
                                (284,1171,'Heavy freezing drizzle','Heavy freezing drizzle'),
                                (293,1180,'Patchy light rain','Patchy light rain'),
                                (296,1183,'Light rain','Light rain'),
                                (299,1186,'Moderate rain at times','Moderate rain at times'),
                                (302,1189,'Moderate rain','Moderate rain'),
                                (305,1192,'Heavy rain at times','Heavy rain at times'),
                                (308,1195,'Heavy rain','Heavy rain'),
                                (311,1198,'Light freezing rain','Light freezing rain'),
                                (314,1201,'Moderate or heavy freezing rain','Moderate or heavy freezing rain'),
                                (317,1204,'Light sleet','Light sleet'),
                                (320,1207,'Moderate or heavy sleet','Moderate or heavy sleet'),
                                (323,1210,'Patchy light snow','Patchy light snow'),
                                (326,1213,'Light snow','Light snow'),
                                (329,1216,'Patchy moderate snow','Patchy moderate snow'),
                                (332,1219,'Moderate snow','Moderate snow'),
                                (335,1222,'Patchy heavy snow','Patchy heavy snow'),
                                (338,1225,'Heavy snow','Heavy snow'),
                                (350,1237,'Ice pellets','Ice pellets'),
                                (353,1240,'Light rain shower','Light rain shower'),
                                (356,1243,'Moderate or heavy rain shower','Moderate or heavy rain shower'),
                                (359,1246,'Torrential rain shower','Torrential rain shower'),
                                (362,1249,'Light sleet showers','Light sleet showers'),
                                (365,1252,'Moderate or heavy sleet showers','Moderate or heavy sleet showers'),
                                (368,1255,'Light snow showers','Light snow showers'),
                                (371,1258,'Moderate or heavy snow showers','Moderate or heavy snow showers'),
                                (374,1261,'Light showers of ice pellets','Light showers of ice pellets'),
                                (377,1264,'Moderate or heavy showers of ice pellets','Moderate or heavy showers of ice pellets'),
                                (386,1273,'Patchy light rain with thunder','Patchy light rain with thunder'),
                                (389,1276,'Moderate or heavy rain with thunder','Moderate or heavy rain with thunder'),
                                (392,1279,'Patchy light snow with thunder','Patchy light snow with thunder'),
                                (395,1282,'Moderate or heavy snow with thunder','Moderate or heavy snow with thunder');
/*!40000 ALTER TABLE `weather_codes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'routine_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*M!100616 SET NOTE_VERBOSITY=@OLD_NOTE_VERBOSITY */;

-- Dump completed on 2025-10-07 18:02:29