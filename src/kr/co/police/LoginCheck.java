package kr.co.police;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginCheck {
	
	public static boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException{
		HttpSession session  = request.getSession();
		Boolean result = (Boolean)session.getAttribute("logined");
		if(  result == null || result == false ){
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('로그인후 이용가능합니다.')");
			out.println("window.location.href='./login/login.jsp'; ");
			out.println("</script>");	
			out.close();
			return false;
		}
		
		return true;
	}
}
