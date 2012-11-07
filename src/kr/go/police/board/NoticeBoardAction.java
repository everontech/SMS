package kr.go.police.board;

import java.util.List;

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
		List<BoardBean> list = (List<BoardBean>)dao. getNoticeList(1, 10);		
		request.setAttribute("list", list);
		forward.setPath("./board/notice.jsp"); 
		return forward;
	}

}
