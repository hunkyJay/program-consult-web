CREATE TABLE `program_learning`.`appraise` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `appraiseId` INT(11) NOT NULL,
  `appointmentId` INT(11) NOT NULL,
  `mentorUsername` VARCHAR(32) NOT NULL,
  `content` VARCHAR(1024) NOT NULL,
  `createTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  `updateTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `appraiseId_UNIQUE` (`appraiseId`)
);