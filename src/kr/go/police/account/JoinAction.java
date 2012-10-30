package kr.go.police.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	회원 가입 action
 */
public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		ActionForward forward = new ActionForward();
		// 계정 DAO 
		AccountDAO dao = new AccountDAO();
		request.setCharacterEncoding("euc-kr");
		// 사용자 정보 가져오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String grade = request.getParameter("grade");
		String name = request.getParameter("name");
		String deptName = request.getParameter("deptname");
		String psName = request.getParameter("police_name");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		
		// 사용자 정보를 담는다.
		UserBean data = new UserBean();
		data.setId(id);
		data.setPwd(pwd);
		data.setGrade(grade);
		data.setDeptName(deptName);
		data.setPhone1(phone);
		data.setName(name);
		data.setPsName(psName);
		data.setEmail(email);
		
		// 회원 가입처리
		if(dao.joinUser(data)){
			forward.setRedirect(true);
			forward.setPath("./account/joined.jsp"); 
			return forward;
		}else{
			// 가입 실패
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!\n 관리자에게 문의하세요');");
			out.println("history.go(-1);");
			out.println("</script>");	
			out.close();
		}
		
		return null;
	}

}
