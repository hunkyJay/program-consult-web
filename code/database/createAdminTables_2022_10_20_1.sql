DROP TABLE IF exists `admin`;
CREATE TABLE  IF not exists `admin` (
  `id` int NOT NULL AUTO_INCREMENT,
  `account` varchar(32) NOT NULL,
  `password` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `phone` bigint NOT NULL,
  `email` varchar(40) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT '1970-01-01 10:00:00',
  `update_time` timestamp NOT NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `staff_id_UNIQUE` (`account`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8