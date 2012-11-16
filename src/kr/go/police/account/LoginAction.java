package kr.go.police.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;
import kr.go.police.aria.Aria;

public class LoginAction implements Action {
	private AccountDAO dao = new AccountDAO();
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		// 로그인 정보 가져오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//  로그인 처리 확인
		boolean result = dao.loginUser(id, pwd);
		if(result){	// 정상 로그인 처리면
			// 사용자 승인여부 확인
			if(dao.checkApprove(id) == false){
				response.setContentType("text/html;charset=euc-kr");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('관리자 승인후 이용 가능합니다.');");
				out.println("history.go(-1);");
				out.println("</script>");	
				out.close();
				return null;
			}
			
			// 사용자 정보 세션 설정
			initUserInfoSession(request,  id);
			forward.setRedirect(true);
			forward.setPath("./SmsSendViewAction.sm"); 
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

	/**
	 * 	세션에 사용자 정보 담기
	 * @param request
	 * @param id
	 * 	사용자 아이디
	 */
	private void initUserInfoSession(HttpServletRequest request, String id) {
		// 아이디를값을 이용하여 사용자정보를 가져온다.
		UserBean data = dao.getUserInfo(id);
		Aria aria = Aria.getInstance();	
		// 세션에 사용자 정보를 담는다.
		HttpSession session = request.getSession();
		session.setAttribute("name",  data.getName());
		session.setAttribute("id", data.getId());
		session.setAttribute("class", data.getUserClass());
		session.setAttribute("index", data.getIndex());
		session.setAttribute("phone",  data.getPhone1());		
		session.setAttribute("sendLimit", data.getMonthSendLimit() - data.getMonthSend());
		session.setAttribute("logined", true);		
	}

}
