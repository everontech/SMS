/*
 * t_restrict_send 테이블 생성
 */
CREATE TABLE `sms`.`t_restrict_send` (
  `f_id` VARCHAR(20) NOT NULL,
  `f_restrictCount` INTEGER UNSIGNED NOT NULL DEFAULT 100,
  `f_totalSendCount` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `f_monthSendCount` INTEGER UNSIGNED NOT NULL DEFAULT 0,
  `f_exceptUser` BOOLEAN NOT NULL DEFAULT 0,
  `f_exceptCount` INTEGER UNSIGNED,
  PRIMARY KEY (`f_id`)
)
ENGINE = InnoDB;
ALTER TABLE `sms`.`t_restrict_send` ADD COLUMN `f_lastSendMonth` DATE AFTER `f_exceptCount`;






/*
 * t_log 테이블 생성
 */

DROP TABLE IF EXISTS `sms`.`t_log`;
CREATE TABLE  `sms`.`t_log` (
  `f_idx` int(10) unsigned NOT NULL auto_increment,
  `f_id` varchar(45) NOT NULL,
  `f_date` datetime NOT NULL,
  `f_ip` varchar(45) NOT NULL,
  `f_mac` varchar(45) default NULL,
  `f_message` varchar(45) NOT NULL,
  PRIMARY KEY  (`f_idx`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=euckr;

/*
 * t_group_restrict 테이블 생성
 */
CREATE TABLE `sms`.`t_group_restrict` (
  `f_class` SMALLINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `f_limit` VARCHAR(45) NOT NULL,
  `f_prev_limit` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`f_level`)
)
ENGINE = InnoDB;
