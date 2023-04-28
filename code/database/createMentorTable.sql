CREATE TABLE `program_learning`.`mentor` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `mentorUsername` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `introduction` VARCHAR(1024),
  `email` VARCHAR(32) NOT NULL,
  `img` VARCHAR(1024) DEFAULT 'user1.png',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mentorUsername_UNIQUE` (`mentorUsername`)  
);