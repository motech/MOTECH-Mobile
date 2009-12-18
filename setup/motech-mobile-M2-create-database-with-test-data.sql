-- MySQL dump 10.13  Distrib 5.1.39, for Win32 (ia32)
--
-- Host: localhost    Database: motechmobiledb
-- ------------------------------------------------------
-- Server version	5.1.39-community

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

CREATE DATABASE IF NOT EXISTS `motechmobiledb`;

GRANT ALL ON motechmobiledb.* TO 'motechmobile'@'localhost' IDENTIFIED BY 'mmobilepass';

USE `motechmobiledb`;

--
-- Table structure for table `gateway_request`
--

DROP TABLE IF EXISTS `gateway_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_request` (
  `id` bigint(20) NOT NULL,
  `request_details_id` bigint(20) DEFAULT NULL,
  `recipients_number` varchar(255) DEFAULT NULL,
  `date_sent` datetime DEFAULT NULL,
  `date_from` datetime DEFAULT NULL,
  `date_to` datetime DEFAULT NULL,
  `try_number` int(11) DEFAULT NULL,
  `message` text,
  `request_id` varchar(255) DEFAULT NULL,
  `message_status` varchar(255) DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK81205B94A5644C7B` (`request_details_id`),
  CONSTRAINT `FK81205B94A5644C7B` FOREIGN KEY (`request_details_id`) REFERENCES `gateway_request_details` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gateway_request_details`
--

DROP TABLE IF EXISTS `gateway_request_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_request_details` (
  `id` bigint(20) NOT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  `message` text,
  `number_of_pages` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `gateway_response`
--

DROP TABLE IF EXISTS `gateway_response`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gateway_response` (
  `id` bigint(20) NOT NULL,
  `gateway_message_id` varchar(255) DEFAULT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `recipient_number` varchar(255) DEFAULT NULL,
  `message_status` varchar(255) DEFAULT NULL,
  `response_text` text,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKA612CBDCDD47F282` (`message_id`),
  CONSTRAINT `FKA612CBDCDD47F282` FOREIGN KEY (`message_id`) REFERENCES `gateway_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `language`
--

DROP TABLE IF EXISTS `language`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `language` (
  `id` bigint(20) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message_request`
--

DROP TABLE IF EXISTS `message_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_request` (
  `id` bigint(20) NOT NULL,
  `request_id` varchar(255) DEFAULT NULL,
  `notification_type_id` bigint(20) DEFAULT NULL,
  `language_id` bigint(20) DEFAULT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  `schedule` date DEFAULT NULL,
  `p113_nData` varchar(255) DEFAULT NULL,
  `recipient_number` varchar(255) DEFAULT NULL,
  `date_from` datetime DEFAULT NULL,
  `date_to` datetime DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `date_processed` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `max_try_number` int(11) DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FKF9A8A257C247835` (`notification_type_id`),
  KEY `FKF9A8A25716C79BE` (`language_id`),
  CONSTRAINT `FKF9A8A25716C79BE` FOREIGN KEY (`language_id`) REFERENCES `language` (`id`),
  CONSTRAINT `FKF9A8A257C247835` FOREIGN KEY (`notification_type_id`) REFERENCES `notification_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `message_template`
--

DROP TABLE IF EXISTS `message_template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_template` (
  `id` bigint(20) NOT NULL,
  `date_created` datetime DEFAULT NULL,
  `message_type` varchar(255) DEFAULT NULL,
  `template` text,
  `notification_type` bigint(20) DEFAULT NULL,
  `language` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK41715B2D5781D57` (`notification_type`),
  KEY `FK41715B2E7550DB4` (`language`),
  CONSTRAINT `FK41715B2E7550DB4` FOREIGN KEY (`language`) REFERENCES `language` (`id`),
  CONSTRAINT `FK41715B2D5781D57` FOREIGN KEY (`notification_type`) REFERENCES `notification_type` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notification_type`
--

DROP TABLE IF EXISTS `notification_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `notification_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `personalization_info`
--

DROP TABLE IF EXISTS `personalization_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `personalization_info` (
  `requestId` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`requestId`,`name`,`value`),
  KEY `FK7233DE9E60D8876` (`requestId`),
  CONSTRAINT `FK7233DE9E60D8876` FOREIGN KEY (`requestId`) REFERENCES `message_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transition`
--

DROP TABLE IF EXISTS `transition`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transition` (
  `id` bigint(20) NOT NULL,
  `transactionType` varchar(255) DEFAULT NULL,
  `transactionDate` datetime DEFAULT NULL,
  `transactionDescription` varchar(255) DEFAULT NULL,
  `gateway_response_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK993B6D552E655D7F` (`gateway_response_id`),
  KEY `FK993B6D553E72B537` (`gateway_response_id`),
  CONSTRAINT `FK993B6D553E72B537` FOREIGN KEY (`gateway_response_id`) REFERENCES `transition` (`id`),
  CONSTRAINT `FK993B6D552E655D7F` FOREIGN KEY (`gateway_response_id`) REFERENCES `gateway_response` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

insert into language (id, code,name,description) values (1,'en','English', 'english language is the official spoken language in Ghana');
insert into language (id, code,name,description) values (2,'nan','Nankam', 'Nankam is the most spoken language in North Ghana');
insert into language (id, code,name,description) values (3,'kas','Kassim', 'Kassim the most spoken language');

insert into notification_type(id, name, description) values (2, 'Min-by-min test ','First informational message sent at min 1');
insert into notification_type(id, name, description) values (3, 'Min-by-min test ','Second informational message sent at min 2');
insert into notification_type(id, name, description) values (4, 'Min-by-min test ','Prompt for tetanus dose 1  sent at min 3');
insert into notification_type(id, name, description) values (5, 'Min-by-min test ','Third informational message sent at min 5');
insert into notification_type(id, name, description) values (6, 'Min-by-min test ','Fourth informational message sent at min 6');
insert into notification_type(id, name, description) values (7, 'Min-by-min test ','Reminder for missed tetanus 1 sent at min 6 (if missed)');
insert into notification_type(id, name, description) values (8, 'Min-by-min test ','Second reminder for missed tetanus 1 sent at min 8 (if missed)');
insert into notification_type(id, name, description) values (9, 'Min-by-min test ','Prompt for tetanus 2');
insert into notification_type(id, name, description) values (10, 'Min-by-min test ','Reminder for missed tetanus dose 2');
insert into notification_type(id, name, description) values (11, 'Min-by-min test ','Second reminder for missed tetanus dose 2');


insert into notification_type(id, name, description) values (14, 'Day-by-day test ','Day 1 message');
insert into notification_type(id, name, description) values (15, 'Day-by-day test ','Day 2 message');
insert into notification_type(id, name, description) values (16, 'Day-by-day test ','Day 3 message');
insert into notification_type(id, name, description) values (17, 'Day-by-day test ','Day 4 message');
insert into notification_type(id, name, description) values (18, 'Day-by-day test ','Day 5 message');
insert into notification_type(id, name, description) values (19, 'Day-by-day test ','Day 6 message');
insert into notification_type(id, name, description) values (20, 'Day-by-day test ','Day 7 message');
insert into notification_type(id, name, description) values (21, 'Day-by-day test ','Day 8 message');
insert into notification_type(id, name, description) values (22, 'Day-by-day test ','Day 9 message');
insert into notification_type(id, name, description) values (23, 'Day-by-day test ','Day 10 message');
insert into notification_type(id, name, description) values (24, 'Day-by-day test ','Day 11 message');
insert into notification_type(id, name, description) values (25, 'Day-by-day test ','Day 12 message');
insert into notification_type(id, name, description) values (26, 'Day-by-day test ','Day 13 message');
insert into notification_type(id, name, description) values (27, 'Day-by-day test ','Day 14 message');
insert into notification_type(id, name, description) values (28, 'Day-by-day test ','Day 15 message');
insert into notification_type(id, name, description) values (29, 'Day-by-day test ','Day 16 message');
insert into notification_type(id, name, description) values (30, 'Day-by-day test ','Day 17 message');
insert into notification_type(id, name, description) values (31, 'Day-by-day test ','Day 18 message');
insert into notification_type(id, name, description) values (32, 'Day-by-day test ','Day 19 message');
insert into notification_type(id, name, description) values (33, 'Day-by-day test ','Day 20 message');
insert into notification_type(id, name, description) values (34, 'Day-by-day test ','Day 21 message');
insert into notification_type(id, name, description) values (35, 'Day-by-day test ','Day 22 message');
insert into notification_type(id, name, description) values (36, 'Day-by-day test ','Day 23 message');
insert into notification_type(id, name, description) values (37, 'Day-by-day test ','Day 24 message');
insert into notification_type(id, name, description) values (38, 'Day-by-day test ','Day 25 message');
insert into notification_type(id, name, description) values (39, 'Day-by-day test ','Day 26 message');
insert into notification_type(id, name, description) values (40, 'Day-by-day test ','Day 27 message');
insert into notification_type(id, name, description) values (41, 'Day-by-day test ','Day 28 message');
insert into notification_type(id, name, description) values (42, 'Day-by-day test ','Day 29 message');
insert into notification_type(id, name, description) values (43, 'Day-by-day test ','Day 30 message');
insert into notification_type(id, name, description) values (44, 'Day-by-day test ','Day 31 message');
insert into notification_type(id, name, description) values (45, 'Day-by-day test ','Day 32 message');
insert into notification_type(id, name, description) values (46, 'Day-by-day test ','Day 33 message');
insert into notification_type(id, name, description) values (47, 'Day-by-day test ','Day 34 message');
insert into notification_type(id, name, description) values (48, 'Day-by-day test ','Day 35 message');
insert into notification_type(id, name, description) values (49, 'Day-by-day test ','Day 36 message');
insert into notification_type(id, name, description) values (50, 'Day-by-day test ','Day 37 message');
insert into notification_type(id, name, description) values (51, 'Day-by-day test ','Day 38 message');
insert into notification_type(id, name, description) values (52, 'Day-by-day test ','Day 39 message');
insert into notification_type(id, name, description) values (53, 'Day-by-day test ','Day 40 message');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (1,2,'TEXT',1,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (100,2,'TEXT',2,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (101,2,'TEXT',3,'Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (2,3,'TEXT',1,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (102,3,'TEXT',2,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (103,3,'TEXT',3,'Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (3,4,'TEXT',1,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (104,4,'TEXT',2,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (105,4,'TEXT',3,'You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (4,5,'TEXT',1,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (106,5,'TEXT',2,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (107,5,'TEXT',3,'All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (5,6,'TEXT',1,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (108,6,'TEXT',2,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (109,6,'TEXT',3,'Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (6,7,'TEXT',1,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (110,7,'TEXT',2,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (111,7,'TEXT',3,'You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (7,8,'TEXT',1,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (112,8,'TEXT',2,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (113,8,'TEXT',3,'This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (8,9,'TEXT',1,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (114,9,'TEXT',2,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (115,9,'TEXT',3,'You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (9,10,'TEXT',1,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (116,10,'TEXT',2,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (117,10,'TEXT',3,'You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (10,11,'TEXT',1,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (118,11,'TEXT',2,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (119,11,'TEXT',3,'This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (11,14,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (121,14,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (122,14,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (12,15,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (123,15,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (124,15,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (13,16,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (125,16,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (126,16,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (14,17,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (127,17,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (128,17,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (15,18,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (129,18,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (130,18,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (16,19,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (131,19,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (132,19,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (17,20,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (133,20,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (134,20,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (18,21,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (135,21,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (136,21,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (19,22,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (137,22,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (138,22,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (20,23,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (139,23,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (140,23,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (21,24,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (141,24,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (142,24,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (22,25,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (143,25,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (144,25,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (23,26,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (145,26,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (146,26,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (24,27,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (147,27,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (148,27,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (25,28,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (149,28,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (150,28,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (26,29,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (151,29,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (152,29,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (27,30,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (153,30,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (154,30,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (28,31,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (155,31,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (156,31,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (29,32,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (157,32,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (158,32,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (30,33,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (159,33,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (160,33,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (31,34,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (161,34,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (162,34,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (32,35,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (163,35,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (164,35,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (33,36,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (165,36,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (166,36,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (34,37,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (167,37,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (168,37,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (35,38,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (169,38,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (170,38,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (36,39,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (171,39,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (172,39,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (37,40,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (173,40,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (174,40,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (38,41,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (175,41,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (176,41,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (39,42,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (177,42,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (178,42,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (40,43,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (179,43,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (180,43,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (41,44,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (181,44,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (182,44,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (42,45,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (183,45,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (184,45,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (43,46,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (185,46,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (186,46,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (44,47,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (187,47,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (188,47,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (45,48,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (189,48,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (190,48,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (46,49,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (191,49,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (192,49,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (47,50,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (193,50,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (194,50,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (48,51,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (195,51,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (196,51,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (49,52,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (197,52,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (198,52,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.##','2009-10-06');

insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (50,53,'TEXT',1,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (199,53,'TEXT',2,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.@@','2009-10-06');
insert into message_template(id, notification_type, message_type, language, template ,date_created ) values (200,53,'TEXT',3,'Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.##','2009-10-06');


/*Data for the table `incoming_message_form_definition` */

insert into `incoming_message_form_definition` (`id`,`form_code`,`date_created`,`last_modified`) values (785688106549491,'GeneralOPD',NULL,'2009-12-18 11:04:20');


/*Data for the table `incoming_message_form_parameter_definition` */

insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (726,NULL,'paramdefinition name',NULL,34,'','2009-12-18 11:04:19','2009-12-18 11:04:19');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (44780981663901,785688106549491,'DoB','DATE',10,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (125568987021450,785688106549491,'Referral','BOOLEAN',1,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (359809010998245,785688106549491,'FacilityID','NUMERIC',10,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (409624894059308,785688106549491,'Date','DATE',10,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447236646138029,785688106549491,'SerialNo','ALPHANUM',20,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511586,785688106549491,'Diagnosis','NUMERIC',3,'','2009-12-18 11:04:20','2009-12-18 11:04:20');
insert into `incoming_message_form_parameter_definition` (`id`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (964559790068935,785688106549491,'Sex','GENDER',1,'','2009-12-18 11:04:20','2009-12-18 11:04:20');

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-11-26 21:11:32
