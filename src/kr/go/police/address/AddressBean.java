package kr.go.police.address;

/**
 * 주소록 
 */
public class AddressBean {
	private int index;					// 인덱스
	private String people;			// 받는사람이름
	private String phone;			// 전화번호
	private int groupIndex;			// 그룹인덱스
	private String groupName;	// 그룹명

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPeople() {
		return people;
	}

	public void setPeople(String people) {
		this.people = people;
	}

	public int getGroupIndex() {
		return groupIndex;
	}

	public void setGroupIndex(int groupIndex) {
		this.groupIndex = groupIndex;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone.trim();
	}

	/**
	 * 전화번호 첫번째
	 * 
	 * @return
	 */
	public String getPhoneTop() {
		return phone.substring(0, 3);
	}

	/**
	 * 전화번호 중간
	 * 
	 * @return
	 */
	public String getPhoneMiddle() {
		return phone.substring(3, 7);
	}

	/**
	 * 전화번호 마지막
	 * 
	 * @return
	 */
	public String getPhoneBottom() {
		return phone.substring(7).trim();
	}
}
