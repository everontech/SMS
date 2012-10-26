package kr.go.police.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class JoinAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AccountDAO dao = new AccountDAO();
		dao.joinUser(null);
		
		forward.setPath("./account/joined.jsp"); 
		return forward;
	}

}
