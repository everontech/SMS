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
			// 관리자이면 관리자 모드로 이동
			if(id.equals("admin")){
				forward.setPath("./UserListAction.ac"); 	
			}else{		// 일반 사용자
				forward.setPath("./SmsSendViewAction.sm");
			}
			
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
		// 세션에 사용자 정보를 담는다.
		HttpSession session = request.getSession();
		session.setAttribute("name",  data.getName());
		session.setAttribute("id", data.getId());
		String ip = getClientIP(request);		
		session.setAttribute("clientIp", ip);		
		// 관리자 인지 확인
		if(data.getId().equals("admin")){
			session.setAttribute("admin", true);
		}
		session.setAttribute("class", data.getUserClass());
		session.setAttribute("index", data.getIndex());
		session.setAttribute("phone",  data.getPhone1());		
		session.setAttribute("sendLimit", data.getMonthSendLimit() - data.getMonthSend());
		session.setAttribute("logined", true);		
		// 리스너에 로그기록을 하기 위해 구분
		session.setAttribute("loginListener",  "loginListener");				
	}

	private String getClientIP(HttpServletRequest request) {
		// 서버 아이피가 나오지 안도록 처리
		String ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		if(ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown")) {
		        ip = request.getHeader("REMOTE_ADDR");
		}
		if(ip == null || ip.length() == 0 || ip.toLowerCase().equals("unknown")) {
		        ip = request.getRemoteAddr();
		}
		return ip;
	}

}
