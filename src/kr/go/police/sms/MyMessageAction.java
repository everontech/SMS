package kr.go.police.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class MyMessageAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SmsDAO dao = new SmsDAO();
		// 내 인덱스
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		
		// 그룹 인덱스
		// 내 기본 그룹 가져오기		
		int groupIndex = dao.getMyBaseGroup(userIndex);
		String groupIndexStr = (String)request.getParameter("groupIndex");
		if(groupIndexStr != null){
			groupIndex = Integer.valueOf(groupIndexStr);
		}
		
		
		// 내 문자내역을 가져오기
		List<Message> messageList = (List<Message>)dao.getMyMessages(userIndex, groupIndex);
		// 내 그룹 목록 가져오기
		List<Group> groupList = (List<Group>)dao.getMyGroupList(userIndex);
		
		request.setAttribute("messages", messageList);
		request.setAttribute("groupIndex", groupIndexStr);		
		request.setAttribute("groups", groupList);		
		forward.setPath("./WEB-INF/sms/my_message.jsp"); 
		return forward;
	}

}
