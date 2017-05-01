DROP DATABASE IF EXISTS `campushelpingplatform`;
CREATE DATABASE IF NOT EXISTS `campushelpingplatform`;

DROP TABLE IF EXISTS `campushelpingplatform`.`role`;
CREATE TABLE `campushelpingplatform`.`role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `rolevalue` int(11) NOT NULL,
  `rolecode` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL DEFAULT '-1',
  `createip` varchar(128) NOT NULL DEFAULT '-1',
  `createuserid` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `campushelpingplatform`.`role`
(`uid`,`name`,`rolevalue`,`rolecode`,`createuserid`)
VALUES
('role_default','默认普通用户','100','1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1','admin_default');

DROP TABLE IF EXISTS `campushelpingplatform`.`creditvaluelog`;
CREATE TABLE `campushelpingplatform`.`creditvaluelog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `userid` varchar(128) NOT NULL,
  `changevalue` int(11) NOT NULL,
  `finalvalue` int(11) NOT NULL,
  `createtime` int(11) NOT NULL,
  `reason` varchar(1024) NOT NULL,
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`academy`;
CREATE TABLE `campushelpingplatform`.`academy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`course`;
CREATE TABLE `campushelpingplatform`.`course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `academyid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`grade`;
CREATE TABLE `campushelpingplatform`.`grade` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `admissiontime` int(11) NOT NULL,
  `graduationtime` int(11) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`term`;
CREATE TABLE `campushelpingplatform`.`term` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `starttime` int(11) NOT NULL,
  `endtime` int(11) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`user`;
CREATE TABLE `campushelpingplatform`.`user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `num` VARCHAR(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `nickname` varchar(30) DEFAULT NULL,
  `pwd` varchar(256) NOT NULL,
  `resourceid` varchar(1024) DEFAULT NULL,
  `academyid` varchar(128) NOT NULL,
  `courseid` varchar(128) NOT NULL,
  `gradeid` varchar(128) NOT NULL,
  `locationid` varchar(128) NOT NULL,
  `roleid` varchar(128) NOT NULL,
  `qualtypeid` varchar(128) DEFAULT NULL,
  `creditvalue` int(11) NOT NULL DEFAULT '100',
  `createip` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`privilege`;
CREATE TABLE `campushelpingplatform`.`privilege` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `privilegevalue` int(11) NOT NULL,
  `privilegecode` varchar(128) NOT NULL,
  `createip` varchar(128) NOT NULL DEFAULT '-1',
  `createtime` int(11) NOT NULL DEFAULT '-1',
  `createuserid` varchar(128) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
INSERT INTO `campushelpingplatform`.`privilege`
(`uid`,`name`,`privilegevalue`,`privilegecode`,`createuserid`)
VALUES
('priv_default','默认管理员','100','1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1','admin_default');

DROP TABLE IF EXISTS `campushelpingplatform`.`admin`;
CREATE TABLE `campushelpingplatform`.`admin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `email` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `pwd` varchar(128) NOT NULL,
  `privilegeid` varchar(128) DEFAULT NULL,
  `createip` varchar(128) NOT NULL DEFAULT '-1',
  `createtime` int(11) NOT NULL DEFAULT '-1',
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO `campushelpingplatform`.`admin`
(`uid`,`name`,`pwd`,`privilegeid`)
VALUES
('admin_default','administrator','P@ssw0rd','priv_admin_default');

DROP TABLE IF EXISTS `campushelpingplatform`.`adminloginlog`;
CREATE TABLE `campushelpingplatform`.`adminloginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `adminid` varchar(128) NOT NULL,
  `ip` varchar(128) NOT NULL,
  `logintime` int(11) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`userloginlog`;
CREATE TABLE `campushelpingplatform`.`userloginlog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `userid` varchar(128) NOT NULL,
  `ip` varchar(128) NOT NULL,
  `logintime` int(11) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`resource`;
CREATE TABLE `campushelpingplatform`.`resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `type` tinyint(4) NOT NULL,
  `value` varchar(1024) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`complaintrequest`;
CREATE TABLE `campushelpingplatform`.`complaintrequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `userid` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `problemid` varchar(128) NOT NULL,
  `resourceid` varchar(1024) DEFAULT NULL,
  `status` tinyint(4) NOT NULL,
  `replydescription` varchar(1024) DEFAULt NULL,
  `replyresourceid` varchar(1024) DEFAULT NULL,
  `replycreateuserid` varchar(128) DEFAULT NULL,
  `replycreatetime` int(11) DEFAULT NULL,
  `replycreateip` varchar(128) DEFAULT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`qualificationtype`;
CREATE TABLE `campushelpingplatform`.`qualificationtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `problemtypeid` varchar(1024) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`problemtype`;
CREATE TABLE `campushelpingplatform`.`problemtype` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`qualificationrequest`;
CREATE TABLE `campushelpingplatform`.`qualificationrequest` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `userid` varchar(128) NOT NULL,
  `typeid` varchar(128) NOT NULL,
  `description` varchar(1024) NOT NULL,
  `resourceid` varchar(1024) DEFAULT NULL,
  `requesttime` int(11) NOT NULL,
  `requestip` varchar(128) NOT NULL,
  `checkingstatus` tinyint(4) NOT NULL,
  `checkingtype` tinyint(4) DEFAULT NULL,
  `checkingtime` int(11) DEFAULT NULL,
  `checkingip` varchar(128) DEFAULT NULL,
  `checkinguserid` varchar(128) DEFAULT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`location`;
CREATE TABLE `location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` varchar(128) NOT NULL,
  `name` varchar(30) NOT NULL,
  `createuserid` varchar(128) NOT NULL,
  `createtime` int(11) NOT NULL,
  `createip` varchar(128) NOT NULL,
  `longitude` float(10,7) NOT NULL,
  `latitude` float(10,7) NOT NULL,
  `isvalid` tinyint(4) NOT NULL DEFAULT '1',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `uid_UNIQUE` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`problem`;
CREATE TABLE `campushelpingplatform`.`problem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(128) NOT NULL AUTO_INCREMENT,
  `createuserid` VARCHAR(128) NOT NULL,
  `locationid` VARCHAR(128) NOT NULL,
  `problemtypeid` VARCHAR(128) NOT NULL,
  `title` VARCHAR(64) NOT NULL,
  `preferday` VARCHAR(32) DEFAULT NULL,
  `preferstart` INT(11) DEFAULT NULL,
  `preferend` INT(11) DEFAULT NULL,
  `description` VARCHAR(1024) NOT NULL,
  `resourceid` VARCHAR(1024) DEFAULT NULL,
  `createtime` INT NOT NULL,
  `createip` VARCHAR(128) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `solvedid` VARCHAR(128) DEFAULT NULL,
  `isvalid` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `uid_UNIQUE` (`uid` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `campushelpingplatform`.`solve`;
CREATE TABLE `campushelpingplatform`.`solve` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `uid` VARCHAR(128) NOT NULL,
  `problemid` VARCHAR(128) NOT NULL,
  `createuserid` VARCHAR(128) NOT NULL,
  `assistantid` VARCHAR(1024) DEFAULT NULL,
  `description` VARCHAR(1024) DEFAULT NULL,
  `createtime` INT NOT NULL,
  `createip` VARCHAR(128) NOT NULL,
  `isvalid` TINYINT NOT NULL DEFAULT 1,
  PRIMARY KEY (`uid`),
  UNIQUE INDEX `id_UNIQUE` (`id` ASC),
  UNIQUE INDEX `uid_UNIQUE` (`uid` ASC)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `campushelpingplatform`.`user` 
ADD INDEX `fk_user_academy_idx` (`academyid` ASC),
ADD INDEX `fk_user_course_idx` (`courseid` ASC),
ADD INDEX `fk_user_grade_idx` (`gradeid` ASC),
ADD INDEX `fk_user_location_idx` (`locationid` ASC),
ADD INDEX `fk_user_role_idx` (`roleid` ASC),
ADD INDEX `fk_user_qualtype_idx` (`qualtypeid` ASC);
ALTER TABLE `campushelpingplatform`.`user` 
ADD CONSTRAINT `fk_user_academy`
  FOREIGN KEY (`academyid`)
  REFERENCES `campushelpingplatform`.`academy` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_course`
  FOREIGN KEY (`courseid`)
  REFERENCES `campushelpingplatform`.`course` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_grade`
  FOREIGN KEY (`gradeid`)
  REFERENCES `campushelpingplatform`.`grade` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_location`
  FOREIGN KEY (`locationid`)
  REFERENCES `campushelpingplatform`.`location` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_role`
  FOREIGN KEY (`roleid`)
  REFERENCES `campushelpingplatform`.`role` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_user_qualtype`
  FOREIGN KEY (`qualtypeid`)
  REFERENCES `campushelpingplatform`.`qualificationtype` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`admin` 
ADD INDEX `fk_admin_privilege_idx` (`privilegeid` ASC);
ALTER TABLE `campushelpingplatform`.`admin` 
ADD CONSTRAINT `fk_admin_privilege`
  FOREIGN KEY (`privilegeid`)
  REFERENCES `campushelpingplatform`.`privilege` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`role` 
ADD INDEX `fk_role_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`role` 
ADD CONSTRAINT `fk_role_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`privilege` 
ADD INDEX `fk_privilege_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`privilege` 
ADD CONSTRAINT `fk_privilege_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`course` 
ADD INDEX `fk_course_academy_idx` (`academyid` ASC),
ADD INDEX `fk_course_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`course` 
ADD CONSTRAINT `fk_course_academy`
  FOREIGN KEY (`academyid`)
  REFERENCES `campushelpingplatform`.`academy` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_course_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`academy` 
ADD INDEX `fk_academy_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`academy` 
ADD CONSTRAINT `fk_academy_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`grade` 
ADD INDEX `fk_grade_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`grade` 
ADD CONSTRAINT `fk_grade_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`location` 
ADD INDEX `fk_location_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`location` 
ADD CONSTRAINT `fk_location_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`complaintrequest` 
ADD INDEX `fk_complaintrequest_createuser_idx` (`replycreateuserid` ASC),
ADD INDEX `fk_complaintrequest_user_idx` (`userid` ASC);
ADD INDEX `fk_complaintrequest_problem_idx` (`problemid` ASC);
ALTER TABLE `campushelpingplatform`.`complaintrequest` 
ADD CONSTRAINT `fk_complaintrequest_createuser`
  FOREIGN KEY (`replycreateuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_complaintrequest_user`
  FOREIGN KEY (`userid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_complaintrequest_problem`
  FOREIGN KEY (`problemid`)
  REFERENCES `campushelpingplatform`.`problem` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`creditvaluelog` 
ADD INDEX `fk_creditvaluelog_user_idx` (`userid` ASC);
ALTER TABLE `campushelpingplatform`.`creditvaluelog` 
ADD CONSTRAINT `fk_creditvaluelog_user`
  FOREIGN KEY (`userid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`adminloginlog` 
ADD INDEX `fk_adminloginlog_admin_idx` (`adminid` ASC);
ALTER TABLE `campushelpingplatform`.`adminloginlog` 
ADD CONSTRAINT `fk_adminloginlog_admin`
  FOREIGN KEY (`adminid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
  
ALTER TABLE `campushelpingplatform`.`userloginlog` 
ADD INDEX `fk_userloginlog_user_idx` (`userid` ASC);
ALTER TABLE `campushelpingplatform`.`userloginlog` 
ADD CONSTRAINT `fk_userloginlog_user`
  FOREIGN KEY (`userid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`problem` 
ADD INDEX `fk_problem_createuser_idx` (`createuserid` ASC),
ADD INDEX `fk_problem_location_idx` (`locationid` ASC),
ADD INDEX `fk_problem_problemtype_idx` (`problemtypeid` ASC),
ADD INDEX `fk_problem_solved_idx` (`solvedid` ASC);
ALTER TABLE `campushelpingplatform`.`problem` 
ADD CONSTRAINT `fk_problem_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_problem_location`
  FOREIGN KEY (`locationid`)
  REFERENCES `campushelpingplatform`.`location` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_problem_problemtype`
  FOREIGN KEY (`problemtypeid`)
  REFERENCES `campushelpingplatform`.`problemtype` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_problem_solved`
  FOREIGN KEY (`solvedid`)
  REFERENCES `campushelpingplatform`.`solve` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`problemtype` 
ADD INDEX `fk_problemtype_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`problemtype`
ADD CONSTRAINT `fk_problemtype_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`qualificationrequest` 
ADD INDEX `fk_qualificationrequest_user_idx` (`userid` ASC),
ADD INDEX `fk_qualificationrequest_type_idx` (`typeid` ASC),
ADD INDEX `fk_qualificationrequest_checkinguser_idx` (`checkinguserid` ASC);
ALTER TABLE `campushelpingplatform`.`qualificationrequest` 
ADD CONSTRAINT `fk_qualificationrequest_user`
  FOREIGN KEY (`userid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_qualificationrequest_type`
  FOREIGN KEY (`typeid`)
  REFERENCES `campushelpingplatform`.`qualificationtype` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_qualificationrequest_checkinguser`
  FOREIGN KEY (`checkinguserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`qualificationtype` 
ADD INDEX `fk_qualificationtype_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`qualificationtype` 
ADD CONSTRAINT `fk_qualificationtype_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`solve` 
ADD INDEX `fk_solve_problem_idx` (`problemid` ASC),
ADD INDEX `fk_solve_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`solve` 
ADD CONSTRAINT `fk_solve_problem`
  FOREIGN KEY (`problemid`)
  REFERENCES `campushelpingplatform`.`problem` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION,
ADD CONSTRAINT `fk_solve_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`user` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

ALTER TABLE `campushelpingplatform`.`term` 
ADD INDEX `fk_term_createuser_idx` (`createuserid` ASC);
ALTER TABLE `campushelpingplatform`.`term` 
ADD CONSTRAINT `fk_term_createuser`
  FOREIGN KEY (`createuserid`)
  REFERENCES `campushelpingplatform`.`admin` (`uid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;
