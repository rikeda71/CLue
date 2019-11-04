CREATE TABLE IF NOT EXISTS `authors` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` varchar(50),
  PRIMARY KEY (`id`,`name`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB;
