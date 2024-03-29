﻿# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: test
# ------------------------------------------------------
# Server version 5.1.56-community

#
# Source for table ss_bill
#

DROP TABLE IF EXISTS `ss_bill`;
CREATE TABLE `ss_bill` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cp_id` int(11) NOT NULL DEFAULT '0',
  `parent_id` int(11) NOT NULL DEFAULT '0',
  `cp_name` varchar(20) NOT NULL DEFAULT '0',
  `users` int(11) NOT NULL DEFAULT '0',
  `cp_users` int(11) NOT NULL DEFAULT '0',
  `time` datetime DEFAULT '2013-01-01 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

#
# Dumping data for table ss_bill
#
LOCK TABLES `ss_bill` WRITE;
/*!40000 ALTER TABLE `ss_bill` DISABLE KEYS */;

INSERT INTO `ss_bill` VALUES (1,9001498,9001,'途睿天成1',1,1,'2013-07-24');
INSERT INTO `ss_bill` VALUES (2,9001497,9001,'途睿天成',1,1,'2013-07-24');
INSERT INTO `ss_bill` VALUES (3,9001497,9001,'途睿天成',1,1,'2013-07-28');
INSERT INTO `ss_bill` VALUES (4,9001498,9001,'途睿天成1',1,1,'2013-07-25');
INSERT INTO `ss_bill` VALUES (5,9001497,9001,'途睿天成',1,1,'2013-07-23');
/*!40000 ALTER TABLE `ss_bill` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
