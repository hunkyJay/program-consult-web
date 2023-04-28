CREATE TABLE `program_learning`.`studyPlan` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `planId` INT(11) NOT NULL,
  `studentUsername` VARCHAR(32) NOT NULL,
  `languageType` VARCHAR(32) NOT NULL,
  `selfCondition` VARCHAR(1024) NOT NULL,
  `planContent` VARCHAR(1024) NOT NULL,
  `createTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  `updateTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `planId_UNIQUE` (`planId`)  
);
