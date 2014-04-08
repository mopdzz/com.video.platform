# MySQL-Front 5.1  (Build 2.7)

/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE=''STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION'' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES=''ON'' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS */;
/*!40014 SET FOREIGN_KEY_CHECKS=0 */;


# Host: localhost    Database: test
# ------------------------------------------------------
# Server version 5.1.56-community

#
# Source for table ss_user
#

DROP TABLE IF EXISTS `ss_user`;
CREATE TABLE `ss_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login_name` varchar(64) NOT NULL DEFAULT '''',
  `plain_password` varchar(20) NOT NULL DEFAULT '''',
  `cp_id` int(11) DEFAULT ''0'',
  `name` varchar(64) NOT NULL DEFAULT '''',
  `password` varchar(255) NOT NULL DEFAULT '''',
  `salt` varchar(64) NOT NULL,
  `roles` varchar(255) NOT NULL,
  `reduce` int(11) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `register_date` timestamp NOT NULL DEFAULT ''0000-00-00 00:00:00'',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login_name` (`login_name`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

#
# Dumping data for table ss_user
#
LOCK TABLES `ss_user` WRITE;
/*!40000 ALTER TABLE `ss_user` DISABLE KEYS */;

INSERT INTO `ss_user` VALUES (1,''admin'',''admin'',0,''超级管理员'',''ae3d6af129efc4cca80b5d8fdc7887d9dba7df84'',''6fdc72fdf9ec00e9'',''admin'',0,-1,''2013-07-27 10:46:08'');
INSERT INTO `ss_user` VALUES (5,''test'',''test'',9001,''途睿'',''85c0a3dd634d2530cb0a7d4c3770bd4c2da20e02'',''09c8c89116cacb6d'',''user'',0,0,''2013-07-27 10:50:19'');
INSERT INTO `ss_user` VALUES (10,''t1'',''t1'',9001497,''途睿天成'',''dced91172ab162eaa529bd1b6514126d32f819da'',''a7df3e4a5efc760c'',''user'',0,9001,''2013-07-27 10:50:19'');
INSERT INTO `ss_user` VALUES (11,''t2'',''t2'',9001498,''途睿天成1'',''9b34c7dcc6b1e12627f0340f9d16a067d0fbb28b'',''3442f4b60e4158f8'',''user'',0,9001,''2013-07-27 10:50:19'');
/*!40000 ALTER TABLE `ss_user` ENABLE KEYS */;
UNLOCK TABLES;

/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
