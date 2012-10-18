package kr.go.police.action;

/**
 * 페이지 포워딩, action인터페이스 처리
 * @author juwan
 *
 */
public class ActionForward {
	/**
	 * 리다이렉트 여부
	 */
	private boolean isRedirect = false;
	/**
	 * 리다이렉트 path
	 */
	private String path = null;

	/**
	 * 
	 * @return
	 * 	리다이렉트 여부
	 */
	public boolean isRedirect() {
		return isRedirect;
	}

	
	public String getPath() {
		return path;
	}

	public void setRedirect(boolean b) {
		isRedirect = b;
	}

	public void setPath(String string) {
		path = string;
	}
}