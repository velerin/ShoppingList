


DROP SCHEMA IF EXISTS `web-shopping-list`;

CREATE SCHEMA `web-shopping-list`;

USE `web-shopping-list`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(128) DEFAULT NULL,
  `amount` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),

  UNIQUE KEY `TITLE_UNIQUE` (`product_name`)

) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf16;

DROP TABLE IF EXISTS `list`;

CREATE TABLE `list` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE KEY `TITLE_UNIQUE` (`title`),

  KEY `FK_PRODUCT_idx` (`product_id`),

  CONSTRAINT `FK_PRODUCT`
  FOREIGN KEY (`product_id`)
  REFERENCES `product` (`id`)

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf16;


DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `list_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_LIST_idx` (`list_id`),

  CONSTRAINT `FK_LIST`
  FOREIGN KEY (`list_id`)
  REFERENCES `list` (`id`)
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf16;

SET FOREIGN_KEY_CHECKS = 1;