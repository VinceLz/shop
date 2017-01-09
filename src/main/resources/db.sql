/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.5.19 : Database - shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop`;

/*Table structure for table `account` */

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `alogin` varchar(20) DEFAULT NULL,
  `aname` varchar(20) DEFAULT NULL,
  `apass` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Table structure for table `category` */

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `ctype` varchar(20) DEFAULT NULL,
  `chot` tinyint(1) DEFAULT '0',
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `forder` */

DROP TABLE IF EXISTS `forder`;

CREATE TABLE `forder` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `fname` varchar(20) DEFAULT NULL,
  `fphone` varchar(20) DEFAULT NULL,
  `fremark` varchar(20) DEFAULT NULL,
  `femail` varchar(20) DEFAULT NULL,
  `fdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `ftotal` decimal(8,2) DEFAULT NULL,
  `fpost` varchar(20) DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `sid` int(11) DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=2013021802 DEFAULT CHARSET=utf8;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `gid` int(11) NOT NULL AUTO_INCREMENT,
  `gname` varchar(20) DEFAULT NULL,
  `gprice` decimal(8,2) DEFAULT NULL,
  `gpic` varchar(200) DEFAULT NULL,
  `gremark` longtext,
  `gxremark` longtext,
  `gdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `gcommend` tinyint(1) DEFAULT NULL,
  `gopen` tinyint(1) DEFAULT NULL,
  `cid` int(11) DEFAULT NULL,
  PRIMARY KEY (`gid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Table structure for table `logs` */

DROP TABLE IF EXISTS `logs`;

CREATE TABLE `logs` (
  `lid` int(11) NOT NULL AUTO_INCREMENT,
  `lpath` varchar(200) DEFAULT NULL,
  `lparam` varchar(500) DEFAULT NULL,
  `ldate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `uid` int(11) DEFAULT NULL,
  `aid` int(11) DEFAULT NULL,
  PRIMARY KEY (`lid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `sorder` */

DROP TABLE IF EXISTS `sorder`;

CREATE TABLE `sorder` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `sname` varchar(20) DEFAULT NULL,
  `sprice` decimal(8,2) DEFAULT NULL,
  `snumber` int(11) NOT NULL,
  `gid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Table structure for table `status` */

DROP TABLE IF EXISTS `status`;

CREATE TABLE `status` (
  `sid` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `ulogin` varchar(20) DEFAULT NULL,
  `uname` varchar(20) DEFAULT NULL,
  `upass` varchar(20) DEFAULT NULL,
  `usex` varchar(20) DEFAULT NULL,
  `uphone` varchar(20) DEFAULT NULL,
  `upost` varchar(10) DEFAULT NULL,
  `uemail` varchar(20) DEFAULT NULL,
  `uaddress` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
