package kr.go.police.account;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class MyInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AccountDAO dao = new AccountDAO();

		// 사용자 인덱스 가져오기
		HttpSession session = request.getSession();
		String indexStr= session.getAttribute("index").toString();
		
		int index = Integer.valueOf(indexStr);
		UserBean data = dao.getUserDetail(index);
		// 사용자 정보 담기
		request.setAttribute("userData", data);
		forward.setPath("./account/myInfo.jsp");
		return forward;			
	}
}