/*
SQLyog Ultimate v8.32 
MySQL - 5.1.46-community : Database - vpdb
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`vpdb` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `vpdb`;

/*Table structure for table `sys_menuactiontbl` */

DROP TABLE IF EXISTS `sys_menuactiontbl`;

CREATE TABLE `sys_menuactiontbl` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '标示',
  `actionName` varchar(50) NOT NULL COMMENT '动作名称',
  `actionType` varchar(10) NOT NULL COMMENT '0:菜单;1:按钮',
  `doAction` varchar(100) NOT NULL DEFAULT '#' COMMENT '动作',
  `opMan` int(10) NOT NULL DEFAULT '0' COMMENT '添加人',
  `opTime` datetime DEFAULT NULL COMMENT '操作时间',
  `isDel` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除. 0:未;1:已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

/*Data for the table `sys_menuactiontbl` */

insert  into `sys_menuactiontbl`(`id`,`actionName`,`actionType`,`doAction`,`opMan`,`opTime`,`isDel`) values (1,'菜单1','0','菜单1的动作',0,NULL,0),(2,'菜单2','0','菜单2的动作',0,NULL,0),(3,'菜单3','0','菜单3的动作',0,NULL,0),(4,'菜单4','0','菜单4的动作',0,NULL,0),(5,'菜单5','0','菜单5的动作',0,NULL,0),(6,'菜单6','0','菜单6的动作',0,NULL,0),(7,'菜单7','0','菜单7的动作',0,NULL,0),(8,'系统管理','1','###',0,NULL,0),(9,'基础管理','1','###',0,'2014-09-25 23:13:48',0),(10,'基础管理1','1','###',0,'2014-09-25 23:15:13',0),(11,'基础管理Thu Sep 25 23:31:02 CST 2014','1','###',0,'2014-09-25 23:31:02',0),(12,'菜单Sat Sep 27 11:00:19 CST 2014','1','#########',0,'2014-09-27 11:00:20',0),(13,'菜单Sat Sep 27 11:08:18 CST 2014','1','#########',0,'2014-09-27 11:08:20',0),(14,'菜单Sat Sep 27 11:16:50 CST 2014','1','#########',0,'2014-09-27 11:16:51',0),(15,'菜单Sat Sep 27 20:54:34 CST 2014','1','#########',0,'2014-09-27 20:54:37',0),(16,'菜单Sat Sep 27 20:56:38 CST 2014','1','#########',0,'2014-09-27 20:56:39',0),(17,'菜单Sat Sep 27 20:58:04 CST 2014','1','#########',0,'2014-09-27 20:58:05',0),(18,'菜单Sat Sep 27 21:08:01 CST 2014','1','#########',0,'2014-09-27 21:08:02',0),(21,'菜单Sat Sep 27 21:15:54 CST 2014','1','#########',0,'2014-09-27 21:15:56',0),(22,'菜单Sun Oct 12 20:58:13 CST 2014','1','#########',0,'2014-10-12 20:58:14',0),(24,'菜单Tue Oct 14 09:34:38 CST 2014','1','#########',0,'2014-10-14 09:34:38',0),(25,'给养管理','1','jiyang/to.do',0,'2014-10-15 09:11:19',0),(27,'菜单Mon Dec 29 14:14:58 CST 2014','1','#########',0,'2014-12-29 14:15:00',0);

/*Table structure for table `t_emp` */

DROP TABLE IF EXISTS `t_emp`;

CREATE TABLE `t_emp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `password` varchar(32) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `t_emp` */

insert  into `t_emp`(`id`,`name`,`password`,`salary`) values (1,'dbw','123',NULL),(2,'abc','123',NULL),(4,'邓宾望','123',NULL),(5,'???2','11111',NULL),(6,'???2','ddddd',NULL),(7,'???2','1111111111',NULL),(8,'???2','ddddddddddddd',NULL),(9,'点点点','sdfsdf',NULL),(10,'???2','bbbbbbb',NULL),(11,'???2','ddd',NULL),(12,'邓宾望2','o',NULL),(13,'邓宾望2','ll',NULL),(14,'邓宾望2','333',NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
