package kr.go.police.board;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *	게시판 bean
 */
public class BoardBean {
	private int num;						//	번호
	private String registerName;		//	등록자
	private String title;					//	제목
	private String content;				//	내용
	private String filename;			//	첨부파일
	private int viewCount;				//	뷰 카운트
	private boolean notice;			//	공지여부
	private int index;						//  게시판 인덱스번호
	private int parentIndex;			// 댓글일경우 부모 인덱스
	private String regDate;				// 등록 날짜
	private String modiDate;			// 수정 날짜
	private int regUserIndex;			// 등록자 인덱스	
	private String pwd;					// 비밀번호
	private boolean newIcon = false;			// 신규 등록여부
	private boolean hasFile;			// 파일여부
	
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getRegisterName() {
		return registerName;
	}

	public void setRegisterName(String registerName) {
		this.registerName = registerName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getViewCount() {
		return viewCount;
	}

	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}

	public boolean isNotice() {
		return notice;
	}

	public void setNotice(boolean isNotice) {
		this.notice = isNotice;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getParentIndex() {
		return parentIndex;
	}

	public void setParentIndex(int parentIndex) {
		this.parentIndex = parentIndex;
	}

	public String getRegDate() {
		return regDate.substring(0, 10);
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
		setNewIcon();
	}

	public String getModiDate() {
		return modiDate;
	}

	public void setModiDate(String modiDate) {
		this.modiDate = modiDate;
	}

	public int getRegUserIndex() {
		return regUserIndex;
	}

	public void setRegUserIndex(int regUserIndex) {
		this.regUserIndex = regUserIndex;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	/**
	 * 신규 글 등록인지 판별
	 * 7일이내 등록이면 신규로 처리
	 * @return
	 */
	public boolean setNewIcon() {
		if(this.regDate == null){
			return false;
		}
		// 등록날짜 설정
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, Integer.valueOf(regDate.substring(0, 4)));
		cal.set(Calendar.MONTH, Integer.valueOf(regDate.substring(5, 7)) -1);
		cal.set(Calendar.DAY_OF_MONTH, Integer.valueOf(regDate.substring(8, 10)));
		// 현재 날짜에서 7일을 빼준다.
		Calendar currentCal = Calendar.getInstance();
		currentCal.add(Calendar.DAY_OF_MONTH, -7);
		// 빼준 날짜보다 등록 날짜가 큰지 비교한다.
		if(cal.getTimeInMillis() - currentCal.getTimeInMillis() > 0){
			 newIcon = true;			
			 return true;
		}
		return false;
	}

	public boolean isHasFile() {
		return filename != null && filename.length() > 0;
	}

	public boolean isNewIcon() {
		return newIcon;
	}

	public void setNewIcon(boolean newIcon) {
		this.newIcon = newIcon;
	}

}
