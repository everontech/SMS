package kr.go.police.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	관리자 공지사항 등록 화면
 */
public class AdminNoticeWriteView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		//	공지사항 등록 화면
		forward.setPath("./admin/notice_write.jsp");
		return forward;
	}

}
