package kr.go.police.sms;

/**
 * 문자함 그룹 DTO
 */
public class Group {
	private int index;				// 인덱스
	private String group;		// 그룹명
	private int count; 			// 인원수

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	
}
