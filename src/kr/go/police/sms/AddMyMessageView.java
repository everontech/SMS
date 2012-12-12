package kr.go.police.sms;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	������ �߰�ȭ��
 */
public class AddMyMessageView implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SmsDAO dao = new SmsDAO();
		// �� �ε���
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		
		// �� �׷� ��� ��������
		List<Group> groupList = (List<Group>)dao.getMyGroupList(userIndex);
		request.setAttribute("groups", groupList);		
		forward.setPath("./WEB-INF/sms/add_message.jsp"); 
		return forward;
	}

}