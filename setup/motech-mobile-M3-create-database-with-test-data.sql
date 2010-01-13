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

/*Table structure for table `gateway_request` */

DROP TABLE IF EXISTS `gateway_request`;

CREATE TABLE `gateway_request` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `gateway_request_details` */

DROP TABLE IF EXISTS `gateway_request_details`;

CREATE TABLE `gateway_request_details` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `message_type` varchar(255) DEFAULT NULL,
  `message` text,
  `number_of_pages` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `gateway_response` */

DROP TABLE IF EXISTS `gateway_response`;

CREATE TABLE `gateway_response` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `incoming_message` */

DROP TABLE IF EXISTS `incoming_message`;

CREATE TABLE `incoming_message` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `incoming_message_session_id` bigint(20) DEFAULT NULL,
  `incoming_message_form_id` bigint(20) DEFAULT NULL,
  `incoming_message_response_id` bigint(20) DEFAULT NULL,
  `message_content` text,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `incoming_message_response_id` (`incoming_message_response_id`),
  KEY `FKA1B318EE97D3332E` (`incoming_message_session_id`),
  KEY `FKA1B318EE51E8D466` (`incoming_message_response_id`),
  KEY `FKA1B318EEA9AD8E06` (`incoming_message_form_id`),
  CONSTRAINT `FKA1B318EEA9AD8E06` FOREIGN KEY (`incoming_message_form_id`) REFERENCES `incoming_message_form` (`id`),
  CONSTRAINT `FKA1B318EE51E8D466` FOREIGN KEY (`incoming_message_response_id`) REFERENCES `incoming_message_response` (`id`),
  CONSTRAINT `FKA1B318EE97D3332E` FOREIGN KEY (`incoming_message_session_id`) REFERENCES `incoming_message_session` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message` */

/*Table structure for table `incoming_message_form` */

DROP TABLE IF EXISTS `incoming_message_form`;

CREATE TABLE `incoming_message_form` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `incoming_message_form_definition_id` bigint(20) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_form_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK60811752DCCF2D1` (`incoming_message_form_definition_id`),
  CONSTRAINT `FK60811752DCCF2D1` FOREIGN KEY (`incoming_message_form_definition_id`) REFERENCES `incoming_message_form_definition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_form` */

/*Table structure for table `incoming_message_form_definition` */

DROP TABLE IF EXISTS `incoming_message_form_definition`;

CREATE TABLE `incoming_message_form_definition` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `form_code` varchar(15) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `form_code` (`form_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_form_definition` */

insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`,`last_modified`) values (254657657567688,0,'RegisterChild','2010-01-08 14:40:15','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`,`last_modified`) values (597658468478768,0,'PregnancyStop','2010-01-08 14:19:05','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`,`last_modified`) values (678565673457657,0,'EditPatient','2010-01-08 14:41:23','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`date_created`,`last_modified`) values (785688106549491,0,'GeneralOPD','2009-12-18 11:04:20','2010-01-08 15:22:53');

/*Table structure for table `incoming_message_form_parameter` */

DROP TABLE IF EXISTS `incoming_message_form_parameter`;

CREATE TABLE `incoming_message_form_parameter` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `incoming_message_from_message_definition_id` bigint(20) DEFAULT NULL,
  `incoming_message_form_id` bigint(20) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `value` varchar(100) DEFAULT NULL,
  `err_code` int(11) DEFAULT NULL,
  `err_text` varchar(200) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_form_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK742C315F96177926` (`incoming_message_from_message_definition_id`),
  KEY `FK742C315FA9AD8E06` (`incoming_message_form_id`),
  CONSTRAINT `FK742C315FA9AD8E06` FOREIGN KEY (`incoming_message_form_id`) REFERENCES `incoming_message_form` (`id`),
  CONSTRAINT `FK742C315F96177926` FOREIGN KEY (`incoming_message_from_message_definition_id`) REFERENCES `incoming_message_form_parameter_definition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_form_parameter` */

/*Table structure for table `incoming_message_form_parameter_definition` */

DROP TABLE IF EXISTS `incoming_message_form_parameter_definition`;

CREATE TABLE `incoming_message_form_parameter_definition` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `incoming_message_form_definition_id` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `parameter_type` varchar(50) DEFAULT NULL,
  `lenght` int(11) DEFAULT NULL,
  `required` bit(1) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `FK584F18332DCCF2D1` (`incoming_message_form_definition_id`),
  CONSTRAINT `FK584F18332DCCF2D1` FOREIGN KEY (`incoming_message_form_definition_id`) REFERENCES `incoming_message_form_definition` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_form_parameter_definition` */

insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (834459078775,0,678565673457657,'secondaryPhoneType','ALPHA',10,'\0','2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (3245464634645,0,254657657567688,'chpsId','ALPHANUM',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (4564564645577,0,597658468478768,'chpsId','ALPHANUM',20,'','2010-01-08 14:46:33','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (6378978445645,0,678565673457657,'secondaryPhone','NUMERIC',15,'\0','2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7645645756876,0,254657657567688,'childRegNum','ALPHANUM',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7658545887996,0,254657657567688,'regDate','DATE',10,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7765435645455,0,678565673457657,'chpsId','ALPHANUM',20,'','2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (12353635757884,0,254657657567688,'childGender','GENDER',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (15674564545534,0,678565673457657,'patientRegNum','ALPHANUM',20,'\0','2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (20564578745345,0,678565673457657,'nhis','ALPHANUM',20,'\0','2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (24575686567567,0,254657657567688,'nhisExp','DATE',10,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (42783631278378,0,254657657567688,'nhis','ALPHANUM',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (44780981663901,0,785688106549491,'DoB','DATE',10,'','2009-12-18 11:04:20','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (53475686867887,0,254657657567688,'motherRegNum','ALPHANUM',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (64348767787689,0,254657657567688,'dob','DATE',20,'','2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (72345769456456,0,678565673457657,'nhisExp','DATE',10,'\0','2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (92366865767568,0,254657657567688,'childFirstName','ALPHA',20,'','2010-01-08 14:54:14','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (93786634412380,0,678565673457657,'primaryPhone','NUMERIC',15,'\0','2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (125568987021450,0,785688106549491,'Referral','BOOLEAN',1,'','2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (359809010998245,0,785688106549491,'FacilityId','NUMERIC',10,'','2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (409624894059308,0,785688106549491,'Date','DATE',10,'','2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447236646138029,0,785688106549491,'SerialNo','ALPHANUM',20,'','2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511586,0,785688106549491,'Diagnosis','NUMERIC',3,'','2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (535767567677889,0,678565673457657,'primaryPhoneType','ALPHA',10,'\0','2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (876875676546456,0,597658468478768,'patientRegNum','ALPHANUM',20,'','2010-01-08 14:46:33','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (964559790068935,0,785688106549491,'Sex','GENDER',1,'','2009-12-18 11:04:20','2010-01-08 15:22:54');

/*Table structure for table `incoming_message_response` */

DROP TABLE IF EXISTS `incoming_message_response`;

CREATE TABLE `incoming_message_response` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `response_content` text,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_response` */

/*Table structure for table `incoming_message_session` */

DROP TABLE IF EXISTS `incoming_message_session`;

CREATE TABLE `incoming_message_session` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `requester_phone` varchar(20) DEFAULT NULL,
  `form_code` varchar(15) DEFAULT NULL,
  `date_started` datetime DEFAULT NULL,
  `date_ended` datetime DEFAULT NULL,
  `last_activity` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `message_session_status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `language` */

DROP TABLE IF EXISTS `language`;

CREATE TABLE `language` (
  `id` bigint(20) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `language` */

insert into `language` (`id`,`code`,`name`,`description`) values (1,'en','English','english language is the official spoken language in Ghana');
insert into `language` (`id`,`code`,`name`,`description`) values (2,'nan','Nankam','Nankam is the most spoken language in North Ghana');
insert into `language` (`id`,`code`,`name`,`description`) values (3,'kas','Kassim','Kassim the most spoken language');

/*Table structure for table `message_request` */

DROP TABLE IF EXISTS `message_request`;

CREATE TABLE `message_request` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `message_template` */

DROP TABLE IF EXISTS `message_template`;

CREATE TABLE `message_template` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `message_template` */

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (1,0,'2009-10-06 00:00:00','TEXT','Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.',2,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (2,0,'2009-10-06 00:00:00','TEXT','Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.',3,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (3,0,'2009-10-06 00:00:00','TEXT','You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.',4,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (4,0,'2009-10-06 00:00:00','TEXT','All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.',5,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (5,0,'2009-10-06 00:00:00','TEXT','Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.',6,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (6,0,'2009-10-06 00:00:00','TEXT','You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.',7,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (7,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.',8,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (8,0,'2009-10-06 00:00:00','TEXT','You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.',9,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (9,0,'2009-10-06 00:00:00','TEXT','You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.',10,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (10,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.',11,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (11,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.',14,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (12,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.',15,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (13,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.',16,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (14,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.',17,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (15,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.',18,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (16,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.',19,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (17,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.',20,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (18,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.',21,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (19,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.',22,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (20,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.',23,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (21,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.',24,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (22,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.',25,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (23,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.',26,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (24,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.',27,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (25,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.',28,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (26,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.',29,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (27,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.',30,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (28,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.',31,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (29,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.',32,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (30,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.',33,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (31,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.',34,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (32,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.',35,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (33,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.',36,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (34,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.',37,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (35,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.',38,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (36,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.',39,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (37,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.',40,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (38,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.',41,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (39,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.',42,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (40,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.',43,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (41,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.',44,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (42,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.',45,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (43,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.',46,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (44,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.',47,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (45,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.',48,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (46,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.',49,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (47,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.',50,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (48,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.',51,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (49,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.',52,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (50,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.',53,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (100,0,'2009-10-06 00:00:00','TEXT','Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.@@',2,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (101,0,'2009-10-06 00:00:00','TEXT','Tetanus is a disorder that leads to severe and painful muscle spasms. It is caused by a bacterium that is commonly found in soil, dust and animal waste.##',2,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (102,0,'2009-10-06 00:00:00','TEXT','Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.@@',3,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (103,0,'2009-10-06 00:00:00','TEXT','Immunization against tetanus is recommended especially in pregnant women. Vaccination of pregnant women is the best preventive measure against neonatal tetanus.##',3,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (104,0,'2009-10-06 00:00:00','TEXT','You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@',4,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (105,0,'2009-10-06 00:00:00','TEXT','You are due for your first tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##',4,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (106,0,'2009-10-06 00:00:00','TEXT','All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.@@',5,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (107,0,'2009-10-06 00:00:00','TEXT','All wounds should be considered risky, so always be cleaned with soap and water.  Some wounds, however small, such as from thorns of roses or lemon, are highly risky and perhaps even more than other large open wounds.##',5,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (108,0,'2009-10-06 00:00:00','TEXT','Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.@@',6,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (109,0,'2009-10-06 00:00:00','TEXT','Tetanus in newborns is severe, with high mortality rate. It usually occurs by contamination of the umbilical cord at delivery or in the first days of life. Furthermore, this disease often aggravated by complications, including infectious, which are the triggers of death.##',6,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (110,0,'2009-10-06 00:00:00','TEXT','You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@',7,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (111,0,'2009-10-06 00:00:00','TEXT','You have missed your first tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##',7,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (112,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.@@',8,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (113,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your first tetanus vaccination.  Please go to your nearest clinic.##',8,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (114,0,'2009-10-06 00:00:00','TEXT','You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.@@',9,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (115,0,'2009-10-06 00:00:00','TEXT','You are due for your second tetanus vaccination in your pregnancy.  Please go to your nearest clinic to receive your vaccination.##',9,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (116,0,'2009-10-06 00:00:00','TEXT','You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.@@',10,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (117,0,'2009-10-06 00:00:00','TEXT','You have missed your second tetanus vaccination.  Please go to your nearest clinic to receive your vaccination.##',10,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (118,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.@@',11,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (119,0,'2009-10-06 00:00:00','TEXT','This is your second reminder for missing your second tetanus vaccination.  Please go to your nearest clinic.##',11,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (121,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.@@',14,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (122,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 1.  Your estimated due date is <DueDate>.##',14,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (123,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.',15,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (124,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 2.  Your estimated due date is <DueDate>.',15,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (125,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.@@',16,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (126,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 3.  Your estimated due date is <DueDate>.##',16,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (127,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.@@',17,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (128,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 4.  Your estimated due date is <DueDate>.##',17,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (129,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.@@',18,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (130,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 5.  Your estimated due date is <DueDate>.##',18,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (131,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.@@',19,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (132,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 6.  Your estimated due date is <DueDate>.##',19,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (133,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.@@',20,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (134,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 7.  Your estimated due date is <DueDate>.##',20,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (135,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.@@',21,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (136,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 8.  Your estimated due date is <DueDate>.##',21,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (137,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.@@',22,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (138,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 9.  Your estimated due date is <DueDate>.##',22,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (139,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.@@',23,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (140,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 10.  Your estimated due date is <DueDate>.##',23,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (141,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.@@',24,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (142,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 11.  Your estimated due date is <DueDate>.##',24,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (143,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.@@',25,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (144,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 12.  Your estimated due date is <DueDate>.##',25,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (145,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.@@',26,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (146,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 13.  Your estimated due date is <DueDate>.##',26,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (147,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.@@',27,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (148,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 14.  Your estimated due date is <DueDate>.##',27,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (149,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.',28,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (150,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 15.  Your estimated due date is <DueDate>.',28,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (151,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.@@',29,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (152,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 16.  Your estimated due date is <DueDate>.##',29,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (153,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.@@',30,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (154,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 17.  Your estimated due date is <DueDate>.##',30,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (155,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.@@',31,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (156,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 18.  Your estimated due date is <DueDate>.##',31,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (157,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.@@',32,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (158,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 19.  Your estimated due date is <DueDate>.##',32,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (159,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.@@',33,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (160,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 20.  Your estimated due date is <DueDate>.##',33,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (161,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.@@',34,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (162,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 21.  Your estimated due date is <DueDate>.##',34,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (163,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.@@',35,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (164,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 22.  Your estimated due date is <DueDate>.##',35,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (165,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.@@',36,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (166,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 23.  Your estimated due date is <DueDate>.##',36,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (167,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.@@',37,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (168,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 24.  Your estimated due date is <DueDate>.##',37,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (169,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.@@',38,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (170,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 25.  Your estimated due date is <DueDate>.##',38,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (171,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.@@',39,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (172,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 26.  Your estimated due date is <DueDate>.##',39,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (173,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.@@',40,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (174,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 27.  Your estimated due date is <DueDate>.##',40,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (175,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.@@',41,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (176,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 28.  Your estimated due date is <DueDate>.##',41,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (177,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.@@',42,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (178,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 29.  Your estimated due date is <DueDate>.##',42,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (179,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.@@',43,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (180,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 30.  Your estimated due date is <DueDate>.##',43,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (181,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.@@',44,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (182,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 31.  Your estimated due date is <DueDate>.##',44,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (183,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.@@',45,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (184,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 32.  Your estimated due date is <DueDate>.##',45,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (185,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.@@',46,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (186,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 33.  Your estimated due date is <DueDate>.##',46,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (187,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.@@',47,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (188,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 34.  Your estimated due date is <DueDate>.##',47,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (189,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.@@',48,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (190,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 35.  Your estimated due date is <DueDate>.##',48,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (191,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.@@',49,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (192,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 36.  Your estimated due date is <DueDate>.##',49,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (193,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.@@',50,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (194,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 37.  Your estimated due date is <DueDate>.##',50,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (195,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.@@',51,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (196,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 38.  Your estimated due date is <DueDate>.##',51,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (197,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.@@',52,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (198,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 39.  Your estimated due date is <DueDate>.##',52,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (199,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.@@',53,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (200,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for day 40.  Your estimated due date is <DueDate>.##',53,3);

/*Table structure for table `notification_type` */

DROP TABLE IF EXISTS `notification_type`;

CREATE TABLE `notification_type` (
  `id` bigint(20) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `notification_type` */

insert into `notification_type` (`id`,`name`,`description`) values (2,'Min-by-min test ','First informational message sent at min 1');
insert into `notification_type` (`id`,`name`,`description`) values (3,'Min-by-min test ','Second informational message sent at min 2');
insert into `notification_type` (`id`,`name`,`description`) values (4,'Min-by-min test ','Prompt for tetanus dose 1  sent at min 3');
insert into `notification_type` (`id`,`name`,`description`) values (5,'Min-by-min test ','Third informational message sent at min 5');
insert into `notification_type` (`id`,`name`,`description`) values (6,'Min-by-min test ','Fourth informational message sent at min 6');
insert into `notification_type` (`id`,`name`,`description`) values (7,'Min-by-min test ','Reminder for missed tetanus 1 sent at min 6 (if missed)');
insert into `notification_type` (`id`,`name`,`description`) values (8,'Min-by-min test ','Second reminder for missed tetanus 1 sent at min 8 (if missed)');
insert into `notification_type` (`id`,`name`,`description`) values (9,'Min-by-min test ','Prompt for tetanus 2');
insert into `notification_type` (`id`,`name`,`description`) values (10,'Min-by-min test ','Reminder for missed tetanus dose 2');
insert into `notification_type` (`id`,`name`,`description`) values (11,'Min-by-min test ','Second reminder for missed tetanus dose 2');
insert into `notification_type` (`id`,`name`,`description`) values (14,'Day-by-day test ','Day 1 message');
insert into `notification_type` (`id`,`name`,`description`) values (15,'Day-by-day test ','Day 2 message');
insert into `notification_type` (`id`,`name`,`description`) values (16,'Day-by-day test ','Day 3 message');
insert into `notification_type` (`id`,`name`,`description`) values (17,'Day-by-day test ','Day 4 message');
insert into `notification_type` (`id`,`name`,`description`) values (18,'Day-by-day test ','Day 5 message');
insert into `notification_type` (`id`,`name`,`description`) values (19,'Day-by-day test ','Day 6 message');
insert into `notification_type` (`id`,`name`,`description`) values (20,'Day-by-day test ','Day 7 message');
insert into `notification_type` (`id`,`name`,`description`) values (21,'Day-by-day test ','Day 8 message');
insert into `notification_type` (`id`,`name`,`description`) values (22,'Day-by-day test ','Day 9 message');
insert into `notification_type` (`id`,`name`,`description`) values (23,'Day-by-day test ','Day 10 message');
insert into `notification_type` (`id`,`name`,`description`) values (24,'Day-by-day test ','Day 11 message');
insert into `notification_type` (`id`,`name`,`description`) values (25,'Day-by-day test ','Day 12 message');
insert into `notification_type` (`id`,`name`,`description`) values (26,'Day-by-day test ','Day 13 message');
insert into `notification_type` (`id`,`name`,`description`) values (27,'Day-by-day test ','Day 14 message');
insert into `notification_type` (`id`,`name`,`description`) values (28,'Day-by-day test ','Day 15 message');
insert into `notification_type` (`id`,`name`,`description`) values (29,'Day-by-day test ','Day 16 message');
insert into `notification_type` (`id`,`name`,`description`) values (30,'Day-by-day test ','Day 17 message');
insert into `notification_type` (`id`,`name`,`description`) values (31,'Day-by-day test ','Day 18 message');
insert into `notification_type` (`id`,`name`,`description`) values (32,'Day-by-day test ','Day 19 message');
insert into `notification_type` (`id`,`name`,`description`) values (33,'Day-by-day test ','Day 20 message');
insert into `notification_type` (`id`,`name`,`description`) values (34,'Day-by-day test ','Day 21 message');
insert into `notification_type` (`id`,`name`,`description`) values (35,'Day-by-day test ','Day 22 message');
insert into `notification_type` (`id`,`name`,`description`) values (36,'Day-by-day test ','Day 23 message');
insert into `notification_type` (`id`,`name`,`description`) values (37,'Day-by-day test ','Day 24 message');
insert into `notification_type` (`id`,`name`,`description`) values (38,'Day-by-day test ','Day 25 message');
insert into `notification_type` (`id`,`name`,`description`) values (39,'Day-by-day test ','Day 26 message');
insert into `notification_type` (`id`,`name`,`description`) values (40,'Day-by-day test ','Day 27 message');
insert into `notification_type` (`id`,`name`,`description`) values (41,'Day-by-day test ','Day 28 message');
insert into `notification_type` (`id`,`name`,`description`) values (42,'Day-by-day test ','Day 29 message');
insert into `notification_type` (`id`,`name`,`description`) values (43,'Day-by-day test ','Day 30 message');
insert into `notification_type` (`id`,`name`,`description`) values (44,'Day-by-day test ','Day 31 message');
insert into `notification_type` (`id`,`name`,`description`) values (45,'Day-by-day test ','Day 32 message');
insert into `notification_type` (`id`,`name`,`description`) values (46,'Day-by-day test ','Day 33 message');
insert into `notification_type` (`id`,`name`,`description`) values (47,'Day-by-day test ','Day 34 message');
insert into `notification_type` (`id`,`name`,`description`) values (48,'Day-by-day test ','Day 35 message');
insert into `notification_type` (`id`,`name`,`description`) values (49,'Day-by-day test ','Day 36 message');
insert into `notification_type` (`id`,`name`,`description`) values (50,'Day-by-day test ','Day 37 message');
insert into `notification_type` (`id`,`name`,`description`) values (51,'Day-by-day test ','Day 38 message');
insert into `notification_type` (`id`,`name`,`description`) values (52,'Day-by-day test ','Day 39 message');
insert into `notification_type` (`id`,`name`,`description`) values (53,'Day-by-day test ','Day 40 message');

/*Table structure for table `personalization_info` */

DROP TABLE IF EXISTS `personalization_info`;

CREATE TABLE `personalization_info` (
  `requestId` bigint(20) NOT NULL,
  `name` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`requestId`,`name`,`value`),
  KEY `FK7233DE9E60D8876` (`requestId`),
  CONSTRAINT `FK7233DE9E60D8876` FOREIGN KEY (`requestId`) REFERENCES `message_request` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `transition` */

DROP TABLE IF EXISTS `transition`;

CREATE TABLE `transition` (
  `id` bigint(20) NOT NULL,
  `obj_vesion` int(11) NOT NULL DEFAULT '0',
  `transactionType` varchar(255) DEFAULT NULL,
  `transactionDate` datetime DEFAULT NULL,
  `transactionDescription` varchar(255) DEFAULT NULL,
  `gateway_response_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK993B6D552E655D7F` (`gateway_response_id`),
  KEY `FK993B6D553E72B537` (`gateway_response_id`),
  CONSTRAINT `FK993B6D553E72B537` FOREIGN KEY (`gateway_response_id`) REFERENCES `transition` (`id`),
  CONSTRAINT `FK993B6D552E655D7F` FOREIGN KEY (`gateway_response_id`) REFERENCES `gateway_response` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2009-11-26 21:11:32
