CREATE TABLE `program_learning`.`appointment` (  
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `appointmentId` INT(11) NOT NULL,
  `studentUsername` VARCHAR(32) NOT NULL,
  `mentorUsername` VARCHAR(32) NOT NULL,
  `appointmentTime` TIMESTAMP NOT NULL,
  `meetingUrl` VARCHAR(100),
  `status` INT(1) NOT NULL DEFAULT 0,
  `createTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  `updateTime` TIMESTAMP NOT NULL DEFAULT '1970-01-01 10:00:00',
  PRIMARY KEY (`id`),
  UNIQUE KEY `appointmentId_UNIQUE` (`appointmentId`) 
);