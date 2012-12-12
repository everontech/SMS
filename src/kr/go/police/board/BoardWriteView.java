package kr.go.police.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.CommandToken;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	문의 하기 화면
 */
public class BoardWriteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		// token 설정
		String token = CommandToken.set(request);
		request.setAttribute("token", token);
		//	문의하기 화면
		forward.setPath("./WEB-INF/board/board_write.jsp");
		return forward;
	}

}
