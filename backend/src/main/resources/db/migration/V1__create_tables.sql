CREATE TABLE IF NOT EXISTS `papers` (
    `paper_id` BIGINT AUTO_INCREMENT PRIMARY KEY ,
    `year` int(4) NOT NULL,
    `label` varchar(20),
    `task` varchar(50),
    `session` varchar(100),
    `title` varchar(300) NOT NULL UNIQUE,
    `url` varchar(200) NOT NULL,
    `introduction` text,
    `conference` varchar(10),
    `lang` varchar(20)
);

CREATE TABLE IF NOT EXISTS `authors` (
  `author_id` BIGINT AUTO_INCREMENT PRIMARY KEY ,
  `name` varchar(50) UNIQUE
);

CREATE TABLE IF NOT EXISTS `paper_written_author` (
`author_id` BIGINT NOT NULL,
`paper_id` BIGINT NOT NULL,
PRIMARY KEY (`author_id`, `paper_id`),
FOREIGN KEY (`author_id`)
    REFERENCES `authors` (`author_id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`paper_id`)
    REFERENCES `papers` (`paper_id`)
    ON DELETE CASCADE ON UPDATE CASCADE
);
