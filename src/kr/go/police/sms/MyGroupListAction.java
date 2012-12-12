package kr.go.police.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	내 그룹리스트 
 */
public class MyGroupListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SmsDAO dao = new SmsDAO();
		// 내 인덱스
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		// 내 그룹 목록 가져오기
		List<Group> groupList = (List<Group>)dao.getMyGroupList(userIndex);
		
		request.setAttribute("groups", groupList);		
		forward.setPath("./WEB-INF/sms/my_group_list.jsp"); 
		return forward;
	}

}
