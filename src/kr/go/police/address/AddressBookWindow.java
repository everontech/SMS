package kr.go.police.address;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;
import kr.go.police.sms.Group;

/**
 *	주소록 선택 윈도우
 */
public class AddressBookWindow implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AddressDAO dao = new AddressDAO();
		request.setCharacterEncoding("euc-kr");
		// 사용자 인덱스
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		// 사용자 전체 주소록 그룹 가져오기
		ArrayList<Group> list =
				(ArrayList<Group>)dao.getGroupList(userIndex);
		request.setAttribute("groups", list);							// 주소록그룹 리스트
		forward.setPath("./WEB-INF/sms/address_book.jsp");

		return forward;
	}

}
