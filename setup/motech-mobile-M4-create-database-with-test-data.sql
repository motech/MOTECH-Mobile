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
  `duplicatable` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `last_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `form_code` (`form_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `incoming_message_form_definition` */

insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (254657657567688,0,'RegisterChildU5','DISALLOWED','2010-01-08 14:40:15','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (597658468478768,0,'PregnancyStop','DISALLOWED','2010-01-08 14:19:05','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (678565673457657,0,'EditPatient','TIME_BOUND','2010-01-08 14:41:23','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785688106549491,0,'GeneralOPD','TIME_BOUND','2009-12-18 11:04:20','2010-01-08 15:22:53');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640001,0,'ANC','TIME_BOUND','2010-02-03 09:02:20','2010-02-03 09:02:20');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640002,0,'Abortion','TIME_BOUND','2010-02-03 09:04:02','2010-02-03 09:04:02');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640093,0,'Delivery','TIME_BOUND','2010-02-03 09:04:02','2010-02-03 09:04:02');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640094,0,'PPC','TIME_BOUND','2010-02-03 09:07:04','2010-02-03 09:07:04');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640095,0,'Death','DISALLOWED','2010-02-03 09:07:04','2010-02-03 09:07:4');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640096,0,'Child','TIME_BOUND','2010-02-03 09:10:23','2010-02-03 09:10:23');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640097,0,'ChildOPD','TIME_BOUND','2010-02-03 09:10:25','2010-02-03 09:10:25');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640098,0,'MotherOPD','TIME_BOUND','2010-02-03 09:10:20','2010-02-03 09:10:20');
insert into `incoming_message_form_definition` (`id`,`obj_vesion`,`form_code`,`duplicatable`,`date_created`,`last_modified`) values (785739375640099,0,'RegisterChild','DISALLOWED','2010-02-03 09:10:20','2010-02-03 09:10:20');

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

/*Params for RegisterChildU5*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (3245464634645,0,254657657567688,'chpsId','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (53475686867887,0,254657657567688,'motherRegNum','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (64348767787689,0,254657657567688,'dob','DATE',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7645645756876,0,254657657567688,'childRegNum','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7658545887996,0,254657657567688,'regDate','DATE',10,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (92366865767568,0,254657657567688,'childFirstName','ALPHA',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (12353635757884,0,254657657567688,'childGender','GENDER',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (24575686567567,0,254657657567688,'nhisExp','DATE',10,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (42783631278378,0,254657657567688,'nhis','ALPHANUM',20,true,'2010-01-08 14:54:14','2010-01-08 15:22:53');

/*Params for PregnancyStop*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (4564564645577,0,597658468478768,'chpsId','ALPHANUM',20,true,'2010-01-08 14:46:33','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (876875676546456,0,597658468478768,'patientRegNum','ALPHANUM',20,true,'2010-01-08 14:46:33','2010-01-08 15:22:54');

/*Params for GeneralOPD*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (359809010998245,0,785688106549491,'FacilityId','NUMERIC',10,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (409624894059308,0,785688106549491,'Date','DATE',10,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447236646138029,0,785688106549491,'SerialNo','ALPHANUM',20,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (964559790068935,0,785688106549491,'Sex','GENDER',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (44780981663901,0,785688106549491,'DoB','DATE',10,true,'2009-12-18 11:04:20','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511587,0,785688106549491,'Insured','BOOLEAN',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511588,0,785688106549491,'CaseStatus','CASESTATUS',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511586,0,785688106549491,'Diagnosis','NUMERIC',2,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (447617060511589,0,785688106549491,'secondaryDiagnosis','NUMERIC',2,false,'2009-12-18 11:04:20','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (125568987021450,0,785688106549491,'Referral','BOOLEAN',1,true,'2009-12-18 11:04:20','2010-01-08 15:22:54');

/*Params for EditPatient*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (7765435645455,0,678565673457657,'chpsId','ALPHANUM',10,true,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (15674564545534,0,678565673457657,'patientRegNum','ALPHANUM',20,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (93786634412380,0,678565673457657,'primaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (535767567677889,0,678565673457657,'primaryPhoneType','PHONETYPE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (834459078775,0,678565673457657,'secondaryPhoneType','PHONETYPE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (6378978445645,0,678565673457657,'secondaryPhone','NUMERIC',15,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (20564578745345,0,678565673457657,'nhis','ALPHANUM',20,false,'2010-01-08 15:02:43','2010-01-08 15:22:53');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (72345769456456,0,678565673457657,'nhisExp','DATE',10,false,'2010-01-08 15:02:43','2010-01-08 15:22:54');

/*Params for RegisterChild*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984530,0,785739375640099,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984531,0,785739375640099,'motherMotechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984532,0,785739375640099,'DoB','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984533,0,785739375640099,'sex','GENDER',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984534,0,785739375640099,'firstName','ALPHA',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984535,0,785739375640099,'NHIS#','NUMERIC',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (324546463984536,0,785739375640099,'NHISExpiration','DATE',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for ANC*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984500,0,785739375640001,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984501,0,785739375640001,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984502,0,785739375640001,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984503,0,785739375640001,'visitNo','NUMERIC',4,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984504,0,785739375640001,'TTDose','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984505,0,785739375640001,'IPTDose','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984506,0,785739375640001,'ITN','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (434546463984507,0,785739375640001,'HIVResult','HIVSTATUS',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Abortion*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (464546463987800,0,785739375640002,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (464546463987801,0,785739375640002,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (464546463987802,0,785739375640002,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (464546463987803,0,785739375640002,'abortionType','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (464546463987804,0,785739375640002,'complications','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Delivery*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981200,0,785739375640093,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981201,0,785739375640093,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981202,0,785739375640093,'DoD','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981203,0,785739375640093,'MOD','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981204,0,785739375640093,'OoD','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981205,0,785739375640093,'location','NUMERIC',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981206,0,785739375640093,'deliveredBy','DELIVERER',4,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981207,0,785739375640093,'MaternalDeath','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981208,0,785739375640093,'cause','NUMERIC',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981209,0,785739375640093,'C1BirthOutcome','BIRTHOUTCOME',3,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981210,0,785739375640093,'C1MotechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981211,0,785739375640093,'C1Sex','GENDER',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981212,0,785739375640093,'C1Name','ALPHA',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981213,0,785739375640093,'C1OPV','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981214,0,785739375640093,'C1BCG','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981215,0,785739375640093,'C2BirthOutcome','BIRTHOUTCOME',3,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981216,0,785739375640093,'C2MotechId','ALPHANUM',20,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981217,0,785739375640093,'C2Sex','GENDER',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981218,0,785739375640093,'C2Name','ALPHANUM',10,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981219,0,785739375640093,'C2OPV','BOOLEAN',1,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (473346463981220,0,785739375640093,'C2BCG','BOOLEAN',3,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for PPC*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981100,0,785739375640094,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981101,0,785739375640094,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981102,0,785739375640094,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981103,0,785739375640094,'visitNo','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981104,0,785739375640094,'vitA','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (254546463981105,0,785739375640094,'TTDose','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Death*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (154546463981440,0,785739375640095,'facilityId','NUMERIC',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (154546463981441,0,785739375640095,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (154546463981442,0,785739375640095,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (154546463981443,0,785739375640095,'cause','NUMERIC',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for Child*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981700,0,785739375640096,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981701,0,785739375640096,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981702,0,785739375640096,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981703,0,785739375640096,'BCG','BOOLEAN',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981704,0,785739375640096,'OPVDOSE','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981705,0,785739375640096,'PentaDose','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981706,0,785739375640096,'YellowFever','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981707,0,785739375640096,'CSM','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981708,0,785739375640096,'IPTi','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (064546463981709,0,785739375640096,'VitA','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for ChildOPD*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983210,0,785739375640097,'facilityId','NUMERIC',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983213,0,785739375640097,'serialNo','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983211,0,785739375640097,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983212,0,785739375640097,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983214,0,785739375640097,'newCase','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983215,0,785739375640097,'Diagnosis','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983216,0,785739375640097,'secondaryDiagnosis','NUMERIC',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (879874663983217,0,785739375640097,'referral','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

/*Params for MotherOPD*/
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923330,0,785739375640098,'facilityId','NUMERIC',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923333,0,785739375640098,'serialNo','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923331,0,785739375640098,'motechId','ALPHANUM',20,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923332,0,785739375640098,'date','DATE',10,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923334,0,785739375640098,'newCase','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923335,0,785739375640098,'Diagnosis','NUMERIC',2,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923336,0,785739375640098,'secondaryDiagnosis','NUMERIC',2,false,'2010-02-03 10:29:14','2010-02-03 10:29:14');
insert into `incoming_message_form_parameter_definition` (`id`,`obj_vesion`,`incoming_message_form_definition_id`,`name`,`parameter_type`,`lenght`,`required`,`date_created`,`last_modified`) values (695474663923337,0,785739375640098,'referral','BOOLEAN',1,true,'2010-02-03 10:29:14','2010-02-03 10:29:14');

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
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (11,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 1.  Your estimated due date is <DueDate>.',14,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (12,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 2.  Your estimated due date is <DueDate>.',15,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (13,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 3.  Your estimated due date is <DueDate>.',16,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (14,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 4.  Your estimated due date is <DueDate>.',17,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (15,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 5.  Your estimated due date is <DueDate>.',18,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (16,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 6.  Your estimated due date is <DueDate>.',19,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (17,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 7.  Your estimated due date is <DueDate>.',20,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (18,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 8.  Your estimated due date is <DueDate>.',21,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (19,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 9.  Your estimated due date is <DueDate>.',22,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (20,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 10.  Your estimated due date is <DueDate>.',23,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (21,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 11.  Your estimated due date is <DueDate>.',24,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (22,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 12.  Your estimated due date is <DueDate>.',25,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (23,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 13.  Your estimated due date is <DueDate>.',26,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (24,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 14.  Your estimated due date is <DueDate>.',27,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (25,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 15.  Your estimated due date is <DueDate>.',28,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (26,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 16.  Your estimated due date is <DueDate>.',29,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (27,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 17.  Your estimated due date is <DueDate>.',30,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (28,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 18.  Your estimated due date is <DueDate>.',31,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (29,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 19.  Your estimated due date is <DueDate>.',32,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (30,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 20.  Your estimated due date is <DueDate>.',33,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (31,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 21.  Your estimated due date is <DueDate>.',34,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (32,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 22.  Your estimated due date is <DueDate>.',35,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (33,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 23.  Your estimated due date is <DueDate>.',36,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (34,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 24.  Your estimated due date is <DueDate>.',37,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (35,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 25.  Your estimated due date is <DueDate>.',38,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (36,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 26.  Your estimated due date is <DueDate>.',39,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (37,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 27.  Your estimated due date is <DueDate>.',40,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (38,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 28.  Your estimated due date is <DueDate>.',41,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (39,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 29.  Your estimated due date is <DueDate>.',42,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (40,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 30.  Your estimated due date is <DueDate>.',43,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (41,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 31.  Your estimated due date is <DueDate>.',44,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (42,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 32.  Your estimated due date is <DueDate>.',45,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (43,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 33.  Your estimated due date is <DueDate>.',46,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (44,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 34.  Your estimated due date is <DueDate>.',47,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (45,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 35.  Your estimated due date is <DueDate>.',48,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (46,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 36.  Your estimated due date is <DueDate>.',49,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (47,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 37.  Your estimated due date is <DueDate>.',50,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (48,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 38.  Your estimated due date is <DueDate>.',51,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (49,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 39.  Your estimated due date is <DueDate>.',52,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (50,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 40.  Your estimated due date is <DueDate>.',53,1);
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
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (121,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 1.  Your estimated due date is <DueDate>.@@',14,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (122,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 1.  Your estimated due date is <DueDate>.##',14,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (123,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 2.  Your estimated due date is <DueDate>.',15,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (124,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 2.  Your estimated due date is <DueDate>.',15,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (125,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 3.  Your estimated due date is <DueDate>.@@',16,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (126,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 3.  Your estimated due date is <DueDate>.##',16,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (127,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 4.  Your estimated due date is <DueDate>.@@',17,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (128,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 4.  Your estimated due date is <DueDate>.##',17,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (129,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 5.  Your estimated due date is <DueDate>.@@',18,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (130,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 5.  Your estimated due date is <DueDate>.##',18,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (131,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 6.  Your estimated due date is <DueDate>.@@',19,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (132,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 6.  Your estimated due date is <DueDate>.##',19,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (133,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 7.  Your estimated due date is <DueDate>.@@',20,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (134,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 7.  Your estimated due date is <DueDate>.##',20,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (135,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 8.  Your estimated due date is <DueDate>.@@',21,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (136,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 8.  Your estimated due date is <DueDate>.##',21,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (137,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 9.  Your estimated due date is <DueDate>.@@',22,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (138,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 9.  Your estimated due date is <DueDate>.##',22,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (139,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 10.  Your estimated due date is <DueDate>.@@',23,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (140,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 10.  Your estimated due date is <DueDate>.##',23,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (141,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 11.  Your estimated due date is <DueDate>.@@',24,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (142,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 11.  Your estimated due date is <DueDate>.##',24,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (143,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 12.  Your estimated due date is <DueDate>.@@',25,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (144,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 12.  Your estimated due date is <DueDate>.##',25,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (145,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 13.  Your estimated due date is <DueDate>.@@',26,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (146,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 13.  Your estimated due date is <DueDate>.##',26,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (147,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 14.  Your estimated due date is <DueDate>.@@',27,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (148,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 14.  Your estimated due date is <DueDate>.##',27,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (149,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 15.  Your estimated due date is <DueDate>.',28,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (150,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 15.  Your estimated due date is <DueDate>.',28,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (151,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 16.  Your estimated due date is <DueDate>.@@',29,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (152,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 16.  Your estimated due date is <DueDate>.##',29,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (153,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 17.  Your estimated due date is <DueDate>.@@',30,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (154,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 17.  Your estimated due date is <DueDate>.##',30,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (155,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 18.  Your estimated due date is <DueDate>.@@',31,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (156,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 18.  Your estimated due date is <DueDate>.##',31,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (157,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 19.  Your estimated due date is <DueDate>.@@',32,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (158,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 19.  Your estimated due date is <DueDate>.##',32,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (159,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 20.  Your estimated due date is <DueDate>.@@',33,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (160,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 20.  Your estimated due date is <DueDate>.##',33,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (161,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 21.  Your estimated due date is <DueDate>.@@',34,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (162,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 21.  Your estimated due date is <DueDate>.##',34,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (163,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 22.  Your estimated due date is <DueDate>.@@',35,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (164,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 22.  Your estimated due date is <DueDate>.##',35,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (165,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 23.  Your estimated due date is <DueDate>.@@',36,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (166,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 23.  Your estimated due date is <DueDate>.##',36,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (167,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 24.  Your estimated due date is <DueDate>.@@',37,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (168,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 24.  Your estimated due date is <DueDate>.##',37,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (169,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 25.  Your estimated due date is <DueDate>.@@',38,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (170,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 25.  Your estimated due date is <DueDate>.##',38,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (171,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 26.  Your estimated due date is <DueDate>.@@',39,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (172,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 26.  Your estimated due date is <DueDate>.##',39,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (173,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 27.  Your estimated due date is <DueDate>.@@',40,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (174,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 27.  Your estimated due date is <DueDate>.##',40,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (175,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 28.  Your estimated due date is <DueDate>.@@',41,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (176,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 28.  Your estimated due date is <DueDate>.##',41,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (177,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 29.  Your estimated due date is <DueDate>.@@',42,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (178,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 29.  Your estimated due date is <DueDate>.##',42,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (179,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 30.  Your estimated due date is <DueDate>.@@',43,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (180,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 30.  Your estimated due date is <DueDate>.##',43,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (181,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 31.  Your estimated due date is <DueDate>.@@',44,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (182,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 31.  Your estimated due date is <DueDate>.##',44,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (183,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 32.  Your estimated due date is <DueDate>.@@',45,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (184,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 32.  Your estimated due date is <DueDate>.##',45,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (185,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 33.  Your estimated due date is <DueDate>.@@',46,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (186,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 33.  Your estimated due date is <DueDate>.##',46,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (187,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 34.  Your estimated due date is <DueDate>.@@',47,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (188,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 34.  Your estimated due date is <DueDate>.##',47,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (189,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 35.  Your estimated due date is <DueDate>.@@',48,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (190,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 35.  Your estimated due date is <DueDate>.##',48,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (191,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 36.  Your estimated due date is <DueDate>.@@',49,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (192,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 36.  Your estimated due date is <DueDate>.##',49,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (193,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 37.  Your estimated due date is <DueDate>.@@',50,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (194,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 37.  Your estimated due date is <DueDate>.##',50,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (195,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 38.  Your estimated due date is <DueDate>.@@',51,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (196,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 38.  Your estimated due date is <DueDate>.##',51,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (197,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 39.  Your estimated due date is <DueDate>.@@',52,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (198,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 39.  Your estimated due date is <DueDate>.##',52,3);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (199,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 40.  Your estimated due date is <DueDate>.@@',53,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (200,0,'2009-10-06 00:00:00','TEXT','Hello <PatientFirstName>.  This is your message for week 40.  Your estimated due date is <DueDate>.##',53,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (261,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 1.',54,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (262,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 1.@@',54,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (263,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 1.##',54,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (264,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 2.',55,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (265,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 2.@@',55,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (266,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 2.##',55,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (267,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 3.',56,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (268,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 3.@@',56,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (269,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 3.##',56,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (270,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 4.',57,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (271,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 4.@@',57,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (272,0,'2010-02-02 00:00:00','TEXT','Hello <PatientFirstName>.  This is your child message for week 4.##',57,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (201,0,'2010-02-02 00:00:00','TEXT','When pregnant there are reasons that you must go to the clinic right away.If you have a bad headache,fever or vomiting.If you feel pain in the womb,or a very strong pain on one side of your body.If you are bleeding or more important if the bleeding is heavy+painful.If you have dark yellow urine or blood+a burning feeling when you urinate.If you are very dizzy or have a very fast heart beat.Don\'t be afraid to go to the clinic as the problem may be easy to cure.',58,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (202,0,'2010-02-02 00:00:00','TEXT','When pregnant there are reasons that you must go to the clinic right away.If you have a bad headache,fever or vomiting.If you feel pain in the womb,or a very strong pain on one side of your body.If you are bleeding or more important if the bleeding is heavy+painful.If you have dark yellow urine or blood+a burning feeling when you urinate.If you are very dizzy or have a very fast heart beat.Don\'t be afraid to go to the clinic as the problem may be easy to cure.@@',58,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (203,0,'2010-02-02 00:00:00','TEXT','When pregnant there are reasons that you must go to the clinic right away.If you have a bad headache,fever or vomiting.If you feel pain in the womb,or a very strong pain on one side of your body.If you are bleeding or more important if the bleeding is heavy+painful.If you have dark yellow urine or blood+a burning feeling when you urinate.If you are very dizzy or have a very fast heart beat.Don\'t be afraid to go to the clinic as the problem may be easy to cure.##',58,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (204,0,'2010-02-02 00:00:00','TEXT','Once you know you are pregnant,register with national health insurance to get a full year of free medical care.Health insurance also covers the first 6 weeks of your baby\'s life.It is important to go for antenatal care so you are sure you are healthy for this baby.You will get free vitamins to keep you+baby healthy during pregnancy+you can ask questions about how you are both doing.Antenatal is at your nearest CHPS compound daily so no one knows why you are there.',59,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (205,0,'2010-02-02 00:00:00','TEXT','Once you know you are pregnant,register with national health insurance to get a full year of free medical care.Health insurance also covers the first 6 weeks of your baby\'s life.It is important to go for antenatal care so you are sure you are healthy for this baby.You will get free vitamins to keep you+baby healthy during pregnancy+you can ask questions about how you are both doing.Antenatal is at your nearest CHPS compound daily so no one knows why you are there@@',59,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (206,0,'2010-02-02 00:00:00','TEXT','Once you know you are pregnant,register with national health insurance to get a full year of free medical care.Health insurance also covers the first 6 weeks of your baby\'s life.It is important to go for antenatal care so you are sure you are healthy for this baby.You will get free vitamins to keep you+baby healthy during pregnancy+you can ask questions about how you are both doing.Antenatal is at your nearest CHPS compound daily so no one knows why you are there##',59,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (207,0,'2010-02-02 00:00:00','TEXT','Now that you are pregnant you are not sick but you must be a bit careful.Rough movements+heavy work can cause body pains+damage your body so please ask for help when doing heavy work like fetching firewood or water.When working take plenty of clean water,ginger beer or zonkom to help keep your body cool+try to avoid working in the very hot afternoon sun.If you can,try to find a shady place to do your work.',60,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (208,0,'2010-02-02 00:00:00','TEXT','Now that you are pregnant you are not sick but you must be a bit careful.Rough movements+heavy work can cause body pains+damage your body so please ask for help when doing heavy work like fetching firewood or water.When working take plenty of clean water,ginger beer or zonkom to help keep your body cool+try to avoid working in the very hot afternoon sun.If you can,try to find a shady place to do your work.@@',60,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (209,0,'2010-02-02 00:00:00','TEXT','Now that you are pregnant you are not sick but you must be a bit careful.Rough movements+heavy work can cause body pains+damage your body so please ask for help when doing heavy work like fetching firewood or water.When working take plenty of clean water,ginger beer or zonkom to help keep your body cool+try to avoid working in the very hot afternoon sun.If you can,try to find a shady place to do your work.##',60,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (210,0,'2010-02-02 00:00:00','TEXT','Even though you might not be showing yet your baby\'s heart+brain are growing quickly.As your womb grows you may have some light cramps+waist pain. You need to eat well now for your baby.Please avoid eating ayilo or shrei.It can cause low blood,infection or worms.Try chewing on biscuits or vegetables instead.Don\'t smoke or take alcohol including Guiness as it will damage the baby.Please ask your doctor before using any traditional medicines.',61,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (211,0,'2010-02-02 00:00:00','TEXT','Even though you might not be showing yet your baby\'s heart+brain are growing quickly.As your womb grows you may have some light cramps+waist pain. You need to eat well now for your baby.Please avoid eating ayilo or shrei.It can cause low blood,infection or worms.Try chewing on biscuits or vegetables instead.Don\'t smoke or take alcohol including Guiness as it will damage the baby.Please ask your doctor before using any traditional medicines.@@',61,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (212,0,'2010-02-02 00:00:00','TEXT','Even though you might not be showing yet your baby\'s heart+brain are growing quickly.As your womb grows you may have some light cramps+waist pain. You need to eat well now for your baby.Please avoid eating ayilo or shrei.It can cause low blood,infection or worms.Try chewing on biscuits or vegetables instead.Don\'t smoke or take alcohol including Guiness as it will damage the baby.Please ask your doctor before using any traditional medicines.##',61,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (213,0,'2010-02-02 00:00:00','TEXT','If you are feeling tired+weak now don\'t worry as it is a natural part of pregnancy.Your body is working hard to grow the baby which can make you feel very tired+sometimes your body feels very weak.As you grow bigger you will feel the weight on your body+if you are worrying too much you may not sleep at night.Try to rest when you can.Ask for help with heavy work like fetching water or firewood.Try to take time to relax your body+your mind when you can.',62,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (214,0,'2010-02-02 00:00:00','TEXT','If you are feeling tired+weak now don\'t worry as it is a natural part of pregnancy.Your body is working hard to grow the baby which can make you feel very tired+sometimes your body feels very weak.As you grow bigger you will feel the weight on your body+if you are worrying too much you may not sleep at night.Try to rest when you can.Ask for help with heavy work like fetching water or firewood.Try to take time to relax your body+your mind when you can.@@',62,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (215,0,'2010-02-02 00:00:00','TEXT','If you are feeling tired+weak now don\'t worry as it is a natural part of pregnancy.Your body is working hard to grow the baby which can make you feel very tired+sometimes your body feels very weak.As you grow bigger you will feel the weight on your body+if you are worrying too much you may not sleep at night.Try to rest when you can.Ask for help with heavy work like fetching water or firewood.Try to take time to relax your body+your mind when you can.##',62,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (216,0,'2010-02-02 00:00:00','TEXT','Malaria is very dangerous.It can cause low blood in the mother+miscarriage.Help yourself by sleeping under insecticide treated nets.Soon you will be given medicine called SP at the clinic to protect you.Wear long clothes in light colors in the evening.Try burning neem leaves or orange peels to keep mosquitos away or you can spray the rooms.Avoid hanging many clothes in your room,mosquitos hide in them.Fold your clothes up.Go to the clinic if you feel cold or have headache.',63,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (217,0,'2010-02-02 00:00:00','TEXT','Malaria is very dangerous.It can cause low blood in the mother+miscarriage.Help yourself by sleeping under insecticide treated nets.Soon you will be given medicine called SP at the clinic to protect you.Wear long clothes in light colors in the evening.Try burning neem leaves or orange peels to keep mosquitos away or you can spray the rooms.Avoid hanging many clothes in your room,mosquitos hide in them.Fold your clothes up.Go to the clinic if you feel cold or have headache.@@',63,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (218,0,'2010-02-02 00:00:00','TEXT','Malaria is very dangerous.It can cause low blood in the mother+miscarriage.Help yourself by sleeping under insecticide treated nets.Soon you will be given medicine called SP at the clinic to protect you.Wear long clothes in light colors in the evening.Try burning neem leaves or orange peels to keep mosquitos away or you can spray the rooms.Avoid hanging many clothes in your room,mosquitos hide in them.Fold your clothes up.Go to the clinic if you feel cold or have headache.##',63,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (219,0,'2010-02-02 00:00:00','TEXT','message 7',64,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (220,0,'2010-02-02 00:00:00','TEXT','message 7@@',64,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (221,0,'2010-02-02 00:00:00','TEXT','message 7##',64,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (222,0,'2010-02-02 00:00:00','TEXT','message 8',65,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (223,0,'2010-02-02 00:00:00','TEXT','message 8@@',65,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (224,0,'2010-02-02 00:00:00','TEXT','message 8##',65,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (225,0,'2010-02-02 00:00:00','TEXT','message 9',66,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (226,0,'2010-02-02 00:00:00','TEXT','message 9@@',66,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (227,0,'2010-02-02 00:00:00','TEXT','message 9##',66,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (228,0,'2010-02-02 00:00:00','TEXT','message 10',67,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (229,0,'2010-02-02 00:00:00','TEXT','message 10@@',67,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (230,0,'2010-02-02 00:00:00','TEXT','message 10##',67,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (231,0,'2010-02-02 00:00:00','TEXT','message 11',68,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (232,0,'2010-02-02 00:00:00','TEXT','message 11@@',68,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (233,0,'2010-02-02 00:00:00','TEXT','message 11##',68,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (234,0,'2010-02-02 00:00:00','TEXT','message 12',69,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (235,0,'2010-02-02 00:00:00','TEXT','message 12@@',69,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (236,0,'2010-02-02 00:00:00','TEXT','message 12##',69,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (237,0,'2010-02-02 00:00:00','TEXT','message 13',70,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (238,0,'2010-02-02 00:00:00','TEXT','message 13@@',70,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (239,0,'2010-02-02 00:00:00','TEXT','message 13##',70,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (240,0,'2010-02-02 00:00:00','TEXT','message 14',71,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (241,0,'2010-02-02 00:00:00','TEXT','message 14@@',71,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (242,0,'2010-02-02 00:00:00','TEXT','message 14##',71,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (243,0,'2010-02-02 00:00:00','TEXT','message 15',72,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (244,0,'2010-02-02 00:00:00','TEXT','message 15@@',72,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (245,0,'2010-02-02 00:00:00','TEXT','message 15##',72,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (246,0,'2010-02-02 00:00:00','TEXT','message 16',73,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (247,0,'2010-02-02 00:00:00','TEXT','message 16@@',73,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (248,0,'2010-02-02 00:00:00','TEXT','message 16##',73,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (249,0,'2010-02-02 00:00:00','TEXT','message 17',74,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (250,0,'2010-02-02 00:00:00','TEXT','message 17@@',74,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (251,0,'2010-02-02 00:00:00','TEXT','message 17##',74,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (252,0,'2010-02-02 00:00:00','TEXT','message 18',75,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (253,0,'2010-02-02 00:00:00','TEXT','message 18@@',75,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (254,0,'2010-02-02 00:00:00','TEXT','message 18##',75,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (255,0,'2010-02-02 00:00:00','TEXT','message 19',76,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (256,0,'2010-02-02 00:00:00','TEXT','message 19@@',76,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (257,0,'2010-02-02 00:00:00','TEXT','message 19##',76,3);

insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (258,0,'2010-02-02 00:00:00','TEXT','message 20',77,1);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (259,0,'2010-02-02 00:00:00','TEXT','message 20@@',77,2);
insert into `message_template` (`id`,`obj_vesion`,`date_created`,`message_type`,`template`,`notification_type`,`language`) values (260,0,'2010-02-02 00:00:00','TEXT','message 20##',77,3);

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

insert into `notification_type` (`id`,`name`,`description`) values (14,'Week-by-week','week1 message');
insert into `notification_type` (`id`,`name`,`description`) values (15,'Week-by-week','week 2 message');
insert into `notification_type` (`id`,`name`,`description`) values (16,'Week-by-week','week 3 message');
insert into `notification_type` (`id`,`name`,`description`) values (17,'Week-by-week','week 4 message');
insert into `notification_type` (`id`,`name`,`description`) values (18,'Week-by-week','week 5 message');
insert into `notification_type` (`id`,`name`,`description`) values (19,'Week-by-week','week 6 message');
insert into `notification_type` (`id`,`name`,`description`) values (20,'Week-by-week','week 7 message');
insert into `notification_type` (`id`,`name`,`description`) values (21,'Week-by-week','week 8 message');
insert into `notification_type` (`id`,`name`,`description`) values (22,'Week-by-week','week 9 message');
insert into `notification_type` (`id`,`name`,`description`) values (23,'Week-by-week','week 10 message');
insert into `notification_type` (`id`,`name`,`description`) values (24,'Week-by-week','week 11 message');
insert into `notification_type` (`id`,`name`,`description`) values (25,'Week-by-week','week 12 message');
insert into `notification_type` (`id`,`name`,`description`) values (26,'Week-by-week','week 13 message');
insert into `notification_type` (`id`,`name`,`description`) values (27,'Week-by-week','week 14 message');
insert into `notification_type` (`id`,`name`,`description`) values (28,'Week-by-week','week 15 message');
insert into `notification_type` (`id`,`name`,`description`) values (29,'Week-by-week','week 16 message');
insert into `notification_type` (`id`,`name`,`description`) values (30,'Week-by-week','week 17 message');
insert into `notification_type` (`id`,`name`,`description`) values (31,'Week-by-week','week 18 message');
insert into `notification_type` (`id`,`name`,`description`) values (32,'Week-by-week','week 19 message');
insert into `notification_type` (`id`,`name`,`description`) values (33,'Week-by-week','week 20 message');
insert into `notification_type` (`id`,`name`,`description`) values (34,'Week-by-week','week 21 message');
insert into `notification_type` (`id`,`name`,`description`) values (35,'Week-by-week','week 22 message');
insert into `notification_type` (`id`,`name`,`description`) values (36,'Week-by-week','week 23 message');
insert into `notification_type` (`id`,`name`,`description`) values (37,'Week-by-week','week 24 message');
insert into `notification_type` (`id`,`name`,`description`) values (38,'Week-by-week','week 25 message');
insert into `notification_type` (`id`,`name`,`description`) values (39,'Week-by-week','week 26 message');
insert into `notification_type` (`id`,`name`,`description`) values (40,'Week-by-week','week 27 message');
insert into `notification_type` (`id`,`name`,`description`) values (41,'Week-by-week','week 28 message');
insert into `notification_type` (`id`,`name`,`description`) values (42,'Week-by-week','week 29 message');
insert into `notification_type` (`id`,`name`,`description`) values (43,'Week-by-week','week 30 message');
insert into `notification_type` (`id`,`name`,`description`) values (44,'Week-by-week','week 31 message');
insert into `notification_type` (`id`,`name`,`description`) values (45,'Week-by-week','week 32 message');
insert into `notification_type` (`id`,`name`,`description`) values (46,'Week-by-week','week 33 message');
insert into `notification_type` (`id`,`name`,`description`) values (47,'Week-by-week','week 34 message');
insert into `notification_type` (`id`,`name`,`description`) values (48,'Week-by-week','week 35 message');
insert into `notification_type` (`id`,`name`,`description`) values (49,'Week-by-week','week 36 message');
insert into `notification_type` (`id`,`name`,`description`) values (50,'Week-by-week','week 37 message');
insert into `notification_type` (`id`,`name`,`description`) values (51,'Week-by-week','week 38 message');
insert into `notification_type` (`id`,`name`,`description`) values (52,'Week-by-week','week 39 message');
insert into `notification_type` (`id`,`name`,`description`) values (53,'Week-by-week','week 40 message');
insert into `notification_type` (`id`,`name`,`description`) values (54,'Week-by-week','child week 1 message');
insert into `notification_type` (`id`,`name`,`description`) values (55,'Week-by-week','child week 2 message');
insert into `notification_type` (`id`,`name`,`description`) values (56,'Week-by-week','child week 3 message');
insert into `notification_type` (`id`,`name`,`description`) values (57,'Week-by-week','child week 4 message');

insert into `notification_type` (`id`,`name`,`description`) values (58,'Min-by-min demo ','Demo Message 1');
insert into `notification_type` (`id`,`name`,`description`) values (59,'Min-by-min demo ','Demo Message 2');
insert into `notification_type` (`id`,`name`,`description`) values (60,'Min-by-min demo ','Demo Message 3');
insert into `notification_type` (`id`,`name`,`description`) values (61,'Min-by-min demo ','Demo Message 4');
insert into `notification_type` (`id`,`name`,`description`) values (62,'Min-by-min demo ','Demo Message 5');
insert into `notification_type` (`id`,`name`,`description`) values (63,'Min-by-min demo ','Demo Message 6');
insert into `notification_type` (`id`,`name`,`description`) values (64,'Min-by-min demo ','Demo Message 7');
insert into `notification_type` (`id`,`name`,`description`) values (65,'Min-by-min demo ','Demo Message 8');
insert into `notification_type` (`id`,`name`,`description`) values (66,'Min-by-min demo ','Demo Message 9');
insert into `notification_type` (`id`,`name`,`description`) values (67,'Min-by-min demo ','Demo Message 10');
insert into `notification_type` (`id`,`name`,`description`) values (68,'Min-by-min demo ','Demo Message 11');
insert into `notification_type` (`id`,`name`,`description`) values (69,'Min-by-min demo ','Demo Message 12');
insert into `notification_type` (`id`,`name`,`description`) values (70,'Min-by-min demo ','Demo Message 13');
insert into `notification_type` (`id`,`name`,`description`) values (71,'Min-by-min demo ','Demo Message 14');
insert into `notification_type` (`id`,`name`,`description`) values (72,'Min-by-min demo ','Demo Message 15');
insert into `notification_type` (`id`,`name`,`description`) values (73,'Min-by-min demo ','Demo Message 16');
insert into `notification_type` (`id`,`name`,`description`) values (74,'Min-by-min demo ','Demo Message 17');
insert into `notification_type` (`id`,`name`,`description`) values (75,'Min-by-min demo ','Demo Message 18');
insert into `notification_type` (`id`,`name`,`description`) values (76,'Min-by-min demo ','Demo Message 19');
insert into `notification_type` (`id`,`name`,`description`) values (77,'Min-by-min demo ','Demo Message 20');

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
