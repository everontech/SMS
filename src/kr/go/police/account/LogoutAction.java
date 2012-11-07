package kr.go.police.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	로그아웃 액션
 */
public class LogoutAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
			//  세션을 지워준후 다시 로그인페이지로 이동			
			HttpSession session = request.getSession();
			session.invalidate();
		
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그아웃 되었습니다.');");
			out.println("window.location.href='./login/login.jsp'");
			out.println("</script>");	
			out.close();

		return null;
	}

}
