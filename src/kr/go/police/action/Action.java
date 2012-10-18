package kr.go.police.action;

import javax.servlet.http.*;

/**
 * actionForward 메소츠를 처리를 위한 인터페이스
 * 
 * @author juwan
 * 
 */
public interface Action {
	
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception;
	
}

