package kr.go.police.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AccountDAO dao = new AccountDAO();
		
		// 로그인 정보 가져오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//  로그인 처리 확인
		boolean result = dao.loginUser(id, pwd);
		if(result){	// 정상 로그인 처리면
			forward.setRedirect(true);
			forward.setPath("./sms/index.jsp"); 
			return forward;	
		}else{			// 사용자 정보가 맞지 않으면
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('사용자 정보를 확인하세요');");
			out.println("history.go(-1);");
			out.println("</script>");	
			out.close();
		}
		
		return null;
	}

}
