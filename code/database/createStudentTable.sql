CREATE TABLE `program_learning`.`student` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(32) NOT NULL,
  `studentUsername` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `email` VARCHAR(32) NOT NULL,
  `img` VARCHAR(1024) DEFAULT '1.jpg',
  PRIMARY KEY (`id`),
  UNIQUE KEY `studentUsername_UNIQUE` (`studentUsername`)  
);