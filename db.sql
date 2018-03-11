
CREATE TABLE `bf_book_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_name` varchar(128) NOT NULL DEFAULT '' COMMENT '书名',
  `picture_url` varchar(255) DEFAULT NULL COMMENT '封面url',
  `category` varchar(64) DEFAULT NULL COMMENT '一级类目名称',
  `author` varchar(64) NOT NULL DEFAULT '' COMMENT '作者',
  `score` double DEFAULT NULL COMMENT '分数',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='书本信息';



INSERT INTO `bf_book_info` (`id`, `book_name`, `picture_url`, `category`, `author`, `score`, `created_time`, `updated_time`)
VALUES
	(1,'人类简史','http://bf-bucket.su.bcebos.com/book_cover%2F%E4%BA%BA%E7%B1%BB%E7%AE%80%E5%8F%B2.jpg','文学','尤瓦尔·赫拉利',9.1,'2018-02-11 21:22:18','2018-02-11 21:22:18'),
	(2,'时间简史','http://bf-bucket.su.bcebos.com/book_cover%2F%E6%97%B6%E9%97%B4%E7%AE%80%E5%8F%B2.jpg','文学','史蒂芬·霍金',8.8,'2018-02-11 21:24:17','2018-02-11 21:24:17'),
	(13,'test1',NULL,NULL,'test',NULL,'2018-02-15 13:44:14','2018-02-15 13:44:14'),
	(14,'三体','http://bf-bucket.su.bcebos.com/book_cover%2F%E4%B8%89%E4%BD%93.png','科幻','刘慈欣',9,'2018-02-15 16:36:17','2018-02-15 16:36:17');

/*!40000 ALTER TABLE `bf_book_info` ENABLE KEYS */;
UNLOCK TABLES;

CREATE TABLE `bf_read_book_progress_record` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `book_event_id` int(11) NOT NULL COMMENT '读书时间id',
  `read_progress` smallint(6) NOT NULL COMMENT '读书的进度,0-100',
  `user_note` varchar(128) DEFAULT NULL COMMENT '用户评价，限制字数',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='读书进度记录';

INSERT INTO `bf_read_book_progress_record` (`id`, `book_event_id`, `read_progress`, `user_note`, `created_time`, `updated_time`)
VALUES
	(1,17,20,'test','2018-03-01 23:30:50','2018-03-01 23:30:50'),
	(2,17,20,'test','2018-03-01 23:30:58','2018-03-01 23:30:58'),
	(3,17,5,'test2','2018-03-01 23:34:07','2018-03-01 23:34:07'),
	(4,17,5,'test3','2018-03-01 23:34:39','2018-03-01 23:34:39'),
	(5,17,5,'testtest','2018-03-01 23:34:58','2018-03-01 23:34:58'),
	(6,17,5,'test','2018-03-01 23:39:57','2018-03-01 23:39:57'),
	(7,1,10,'模拟用户测试','2018-03-04 16:36:43','2018-03-04 16:36:43');

DROP TABLE IF EXISTS `bf_user_question`;

CREATE TABLE `bf_user_question` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `message` varchar(255) NOT NULL DEFAULT '' COMMENT '建议内容',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户建议';


INSERT INTO `bf_user_question` (`id`, `user_id`, `message`, `created_time`)
VALUES
	(1,1,'xxx','2018-02-13 18:09:51'),
	(2,1,'xxx','2018-02-13 18:10:03'),
	(3,1,'xxx','2018-02-13 18:11:11'),
	(4,1,'xxx','2018-02-13 18:11:24');

DROP TABLE IF EXISTS `bf_user_read_book_event`;

CREATE TABLE `bf_user_read_book_event` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `book_id` int(11) NOT NULL COMMENT '书本id',
  `score` double DEFAULT NULL COMMENT '书的评分',
  `read_begin_time` datetime DEFAULT NULL COMMENT '阅读开始时间',
  `read_end_time` datetime DEFAULT NULL COMMENT '阅读结束时间',
  `created_time` datetime NOT NULL COMMENT '记录创建时间',
  `updated_time` datetime NOT NULL COMMENT '记录更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户读书事件';

INSERT INTO `bf_user_read_book_event` (`id`, `user_id`, `book_id`, `score`, `read_begin_time`, `read_end_time`, `created_time`, `updated_time`)
VALUES
	(1,2,2,3,'2018-02-12 17:28:03','2018-02-12 17:28:03','2018-02-12 17:28:03','2018-02-12 17:28:03'),
	(2,3,1,4,'2018-02-12 19:41:39','2018-02-12 19:41:39','2018-02-12 19:41:39','2018-02-12 19:41:39'),
	(3,3,2,4,'2018-02-12 19:41:56','2018-02-12 19:41:56','2018-02-12 19:41:56','2018-02-12 19:41:56'),
	(15,3,13,NULL,'2018-02-15 13:44:08',NULL,'2018-02-15 13:44:14','2018-02-15 13:44:14'),
	(17,3,14,NULL,'2018-02-15 16:36:46',NULL,'2018-02-15 16:37:01','2018-02-15 16:37:01');

DROP TABLE IF EXISTS `bf_weixin_user_info`;

CREATE TABLE `bf_weixin_user_info` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `pen_name` varchar(32) DEFAULT '' COMMENT '用户笔名',
  `open_id` varchar(64) NOT NULL DEFAULT '' COMMENT '微信id',
  `avatar` varchar(255) NOT NULL DEFAULT '' COMMENT '微信头像',
  `longitude` decimal(10,6) DEFAULT NULL COMMENT '经度',
  `latitude` decimal(10,6) DEFAULT NULL COMMENT '纬度',
  `loc_province` varchar(32) DEFAULT NULL COMMENT '省份',
  `loc_city` varchar(32) DEFAULT NULL COMMENT '市',
  `loc_county` varchar(64) DEFAULT NULL COMMENT '区域',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='bf微信用户信息';


INSERT INTO `bf_weixin_user_info` (`id`, `pen_name`, `open_id`, `avatar`, `longitude`, `latitude`, `loc_province`, `loc_city`, `loc_county`, `created_time`, `updated_time`)
VALUES
	(3,'goforit','oXnIA5YjLst2qYj_UA5qyfuXloII','https://wx.qlogo.cn/mmopen/vi_32/NiczfX3T8eg3Q3nYj4jriceickIZhxzdfIh8aibdo6icykoqfAR588rtv1hm1QIC4RWxRl3PhE2nPC2bnljv3x2KOoA/0',NULL,NULL,NULL,NULL,NULL,'2018-02-15 16:18:08','2018-02-15 16:18:08');

DROP TABLE IF EXISTS `system_config`;

CREATE TABLE `system_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(32) NOT NULL DEFAULT '' COMMENT '应用名称',
  `cfg_key` varchar(32) NOT NULL DEFAULT '' COMMENT 'key',
  `cfg_value` varchar(1024) NOT NULL DEFAULT '' COMMENT 'value',
  `des` varchar(128) NOT NULL DEFAULT '' COMMENT '描述',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `updated_time` datetime NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='数据配置信息';


INSERT INTO `system_config` (`id`, `app_name`, `cfg_key`, `cfg_value`, `des`, `created_time`, `updated_time`)
VALUES
	(1,'BF_APP','BF_APP_ID','wx3ef1751cc939f81f','appId','2018-02-12 16:32:57','2018-02-12 16:32:57'),
	(2,'BF_APP','BF_APP_SECRET','73d9e2492b19bc67a3018ad819447916','app secret','2018-02-12 16:33:20','2018-02-12 16:33:20');

