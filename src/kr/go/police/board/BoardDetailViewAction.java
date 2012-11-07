package kr.go.police.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	게시물 세부 보기 액션
 */
public class BoardDetailViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		//  인덱스로 해당 게시물을 뽑아온다.
		int index = Integer.valueOf((String)request.getParameter("index"));
		BoardBean data = dao. getDetail(index);
		// 해당 게시물의 댓글 목록
		List<BoardBean>replyList =(List<BoardBean>)dao.getReplyList(index);
		
		request.setAttribute("replyList", replyList);
		request.setAttribute("data", data);
		// 게시물 보기 페이지로 이동
		forward.setPath("./board/boardView.jsp"); 
		return forward;
	}

}
