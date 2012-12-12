package kr.go.police.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	내 예약리스트 액션
 */
public class z_ReservedListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SmsDAO dao = new SmsDAO();
		HttpSession session = request.getSession();
		// 내 인덱스
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		//  페이지
		String pageStr = (String)request.getParameter("page");
		int page = 1;
		if(pageStr != null){
			page = Integer.valueOf(pageStr);
		}
		
		//  한페이지수
		String limitStr = (String)request.getParameter("limit");
		int limit = 10;
		if(limitStr != null){
			limit = Integer.valueOf(limitStr);
		}
		
		// 내 문자내역을 가져오기
		List<SMSBean> list = (List<SMSBean>)dao.getReservedList(userIndex, page, limit);
		request.setAttribute("list", list);
		forward.setPath("./WEB-INF/sms/reservedList.jsp"); 
		return forward;
	}
}
