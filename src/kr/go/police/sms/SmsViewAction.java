package kr.go.police.sms;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	문자 발송 화면 처리
 */
public class SmsViewAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		/*
		SmsDAO dao = new SmsDAO();
		dao.getPslist();
		*/
		// 문자발송 메인 화면
		forward.setPath("./WEB-INF/sms/main.jsp");
		return forward;
	}

}
