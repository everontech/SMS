package kr.go.police.account;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class IdCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		AccountDAO dao = new AccountDAO();
		
		// 로그인 정보 가져오기
		String userId = request.getParameter("id");

		//  로그인 처리 확인
		boolean result = dao.checkDupleUserId(userId);
		if(result){	// 정상 로그인 처리면
			PrintWriter out = response.getWriter();
			out.println("true");
			out.close();
		}else{			// 사용자 정보가 맞지 않으면
			PrintWriter out = response.getWriter();
			out.println("false");
			out.close();
		}
		
		return null;
	}

}
