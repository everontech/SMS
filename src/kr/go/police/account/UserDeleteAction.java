package kr.go.police.account;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	¹®ÀÚÇÔ »èÁ¦ action
 */
public class UserDeleteAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AccountDAO dao = new AccountDAO();
		
		// À¯Àú ÀÎµ¦½º
		String userIndexs = request.getParameter("del_index");
		String[] indexs = userIndexs.split(",");
		
		int count = 0;
		boolean chack = false;
		for(int i=0;i<indexs.length;i++){
			chack=dao.deleteUser(Integer.valueOf(indexs[i]));
			count++;
		}
						
		if(chack){
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('"+count+"¸íÀÇ À¯Àú¸¦ Å»Åð½ÃÄ×½À´Ï´Ù.');");
			out.println("window.location.href='./QuiescenceListAction.ac'");
			out.println("</script>");	
			out.close();
			
			return null;			
		}else{
			response.setContentType("text/html;charset=euc-kr");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('À¯Àú Å»Åð½ÇÆÐ!!')");
			out.println("history.go(-1);");
			out.println("</script>");	
			out.close();
			
			return null;
		}
		
	}

}
