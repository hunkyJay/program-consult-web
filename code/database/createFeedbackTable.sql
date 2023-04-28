CREATE TABLE `program_learning`.`feedback` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `feedbackId` INT(11) NOT NULL,
  `feedbackUsername` VARCHAR(32) NOT NULL,
  `planId` INT(11) NOT NULL,
  `content` VARCHAR(1024) NOT NULL,
  `isCheck` INT(1) NOT NULL DEFAULT 0,
  `createTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  `updateTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `feedbackId_UNIQUE` (`feedbackId`) 
);