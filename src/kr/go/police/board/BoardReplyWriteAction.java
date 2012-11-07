package kr.go.police.board;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	게시물 세부 보기 액션
 */
public class BoardReplyWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		BoardDAO dao = new BoardDAO();
		//  인덱스로 해당 게시물을 뽑아온다.
		String index = (String)request.getParameter("parentIndex");
		if(index == null){
			// 등록 실패
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록 실패');");
			out.println("history.back(-1);");
			out.println("</script>");	
			out.close();
			return null;
		}
		int parentIndex = Integer.valueOf(index);
		String content = (String)request.getParameter("reply_content");
		
		HttpSession session = request.getSession();
		// 댓글 내용
		BoardBean data = new BoardBean();
		data.setParentIndex(parentIndex);
		data.setRegisterName((String)session.getAttribute("id"));
		data.setContent(content);
		if(dao.replyBoard(data, parentIndex)){
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("history.back(-1);");
			out.println("</script>");	
			out.close();
		}else{
			// 등록 실패
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록 실패');");
			out.println("history.back(-1);");
			out.println("</script>");	
			out.close();
		}
		
		return null;
	}

}
