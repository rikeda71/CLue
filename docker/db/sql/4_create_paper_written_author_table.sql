USE research_paper_db;
CREATE TABLE IF NOT EXISTS paper_written_author(
`author_id` BIGINT NOT NULL,
`paper_id` BIGINT NOT NULL,
PRIMARY KEY (`author_id`, `paper_id`),
FOREIGN KEY (`author_id`)
    REFERENCES `authors` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (`paper_id`)
    REFERENCES `papers` (`id`)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;
