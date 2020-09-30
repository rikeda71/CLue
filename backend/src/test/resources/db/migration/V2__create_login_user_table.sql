CREATE TABLE IF NOT EXISTS `users` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY ,
    `name` varchar(100) NOT NULL,
    `email` varchar(100) NOT NULL UNIQUE,
    `image_url` varchar(1000) NOT NULL,
    `password` varchar(200),
    `user_type` varchar(100) NOT NULL
);
