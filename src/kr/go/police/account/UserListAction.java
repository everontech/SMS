package kr.go.police.account;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class UserListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AccountDAO dao = new AccountDAO();
		
		request.setCharacterEncoding("euc-kr");
		// 기본값 설정
		int page = 1;
		
		if(request.getParameter("page") != null){
			page = Integer.valueOf(request.getParameter("page"));
		}
		
		int limit = 10;
		if(request.getParameter("limit") != null){
			limit = Integer.valueOf(request.getParameter("limit"));
		}
		
		String search = "";
		if(request.getParameter("search") != null){
			search = request.getParameter("search");
		}	
		
		int userClass = 1;
		if(request.getParameter("userClass") != null){
			userClass = Integer.valueOf(request.getParameter("userClass"));	
		}			
		
		String psName = "";
		if(request.getParameter("psName") != null){
			search = request.getParameter("psName");
		}	
		
		ArrayList<UserBean> list = (ArrayList<UserBean>)dao.getUserList(page, limit, search, userClass, psName);
		request.setAttribute("userList", list);
		forward.setPath("./admin/userList.jsp");
		
		return forward;			
	}

}
