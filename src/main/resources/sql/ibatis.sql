CREATE DATABASE  IF NOT EXISTS `ibatis` DEFAULT CHARACTER SET utf8 ;

USE `ibatis`;

DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

insert  into `category`(`id`,`name`,`status`,`created`,`updated`) values (1,'国内新闻','1','2016-05-22 18:02:35','2016-05-22 18:02:38'),(2,'国际新闻','1','2016-05-22 18:27:59','2016-05-22 18:28:01'),(3,'焦点新闻','1','2016-05-22 18:43:29','2016-05-22 18:43:29'),(4,'娱乐新闻','1','2016-05-22 18:43:29','2016-05-22 18:43:29'),(5,'军事新闻','1','2016-05-22 18:43:29','2016-05-22 18:43:29'),(6,'校园奇闻','1','2016-05-22 18:43:29','2016-05-22 18:43:29'),(7,'视频','1','2016-05-22 18:43:29','2016-05-22 18:43:29');

/*Table structure for table `news` */

DROP TABLE IF EXISTS `news`;

CREATE TABLE `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL,
  `content` longtext,
  `categoryId` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `created` datetime DEFAULT NULL,
  `updated` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=199148 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `redis_demo`;

CREATE TABLE `redis_demo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `dbname` varchar(765) DEFAULT NULL,
  `dbip` varchar(765) DEFAULT NULL,
  `dbpwd` varchar(765) DEFAULT NULL,
  `dbusername` varchar(765) DEFAULT NULL,
  `dbencoding` varchar(765) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8
