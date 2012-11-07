package kr.go.police.sms;

/**
 * 문자 Dto
 */
/*
DROP TABLE IF EXISTS `sms2`.`send_sms_info`;
CREATE TABLE  `sms2`.`send_sms_info` (
  `f_index` int(10) unsigned NOT NULL auto_increment COMMENT '인덱스',
  `f_id` varchar(20) character set euckr default NULL COMMENT '유저아이디',
  `f_deptcode` varchar(5) character set euckr NOT NULL COMMENT '부서코드',
  `f_to_phone` varchar(11) character set euckr NOT NULL COMMENT '받는전화번호',
  `f_from_phone` varchar(11) character set euckr NOT NULL COMMENT '보내는전화번호',
  `f_message` varchar(255) character set euckr default NULL COMMENT '메세지',
  `f_inwon` int(10) default NULL,
  `f_send_state` varchar(1) character set euckr NOT NULL default '',
  `f_send_result` varchar(8) character set euckr default NULL,
  `f_reserve_date` varchar(8) character set euckr default NULL,
  `f_reserve_time` varchar(4) character set euckr default NULL COMMENT '예약시간',
  `f_callback` varchar(11) character set euckr NOT NULL COMMENT '콜백전화번호',
  `f_deptname` varchar(45) default NULL COMMENT '부서명',
  `f_mms` char(1) NOT NULL default 'n' COMMENT 'MMS 여부',
  PRIMARY KEY  USING BTREE (`f_index`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
*/

public class Message {
	private String id; // 유저 아이디
	private int index; // 메세지 인덱스
	private int userIndex; // 유저 인덱스
	private String title; // 제목
	private String message; // 내용
	private String group; // 그룹
	private String groupIndex; // 그룹 인덱스

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getUserIndex() {
		return userIndex;
	}

	public void setUserIndex(int userIndex) {
		this.userIndex = userIndex;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public String getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(String groupIndex) {
		this.groupIndex = groupIndex;
	}

}
