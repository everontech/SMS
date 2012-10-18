package kr.go.police.board;

/**
 *	게시판 bean
 */
public class BoardBean {
	private int num;					//	번호
	private String registerName;		//	등록자
	private String title;					//	제목
	private String content;				//	내용
	private String filename;			//	첨부파일
	private int viewCount;				//	뷰 카운트
	private boolean isNotice;			//	공지여부
	private int index;					//  게시판 인덱스번호
	private int parentIndex;			// 댓글일경우 부모 인덱스
	private String regDate;				// 등록 날짜
	private String modiDate;			// 수정 날짜
	private int regUserIndex;			// 등록자 인덱스	
	
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
		return isNotice;
	}

	public void setNotice(boolean isNotice) {
		this.isNotice = isNotice;
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
		return regDate;
	}

	public void setRegDate(String regDate) {
		this.regDate = regDate;
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
	
	
	
}
