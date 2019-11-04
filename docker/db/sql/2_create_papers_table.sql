CREATE TABLE IF NOT EXISTS `papers`(
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `year` int(4) NOT NULL,
    `label` varchar(20),
    `task` varchar(50),
    `session` varchar(100),
    `title` varchar(300) NOT NULL UNIQUE,
    `url` varchar(200) NOT NULL,
    `introduction` text,
    `conference` varchar(10),
    `lang` varchar(20),
     PRIMARY KEY (`id`)
) ENGINE=InnoDB;