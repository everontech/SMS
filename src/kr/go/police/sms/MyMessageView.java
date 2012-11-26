package kr.go.police.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	내 문자함 보기
 */
public class MyMessageView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SmsDAO dao = new SmsDAO();
		// 내 인덱스
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		
		// 선택한 인덱스
		int index = 0;	
		String indexStr = (String)request.getParameter("index");
		if(indexStr != null){
			index = Integer.valueOf(indexStr);
		}
		// 내 문자함 메세지 가져오기
		Message data = (Message)dao.getMyMessage(index);
		// 내 그룹 목록 가져오기
		List<Group> groupList = (List<Group>)dao.getMyGroupList(userIndex);
		request.setAttribute("groups", groupList);		
		request.setAttribute("message", data);
		forward.setPath("./sms/message_view.jsp"); 
		return forward;
		
	}

}
