package kr.go.police.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	공지사항 action
 */
public class NoticeBoardAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		dao.getNoticeListCount();
		dao. getBoardList(1, 10);
		dao. getBoardList(1, 10);		
		forward.setPath("./board/notice.jsp"); 
		return forward;
	}

}
