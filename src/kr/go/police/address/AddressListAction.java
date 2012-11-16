package kr.go.police.address;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.SMSUtil;
import kr.go.police.account.UserBean;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	주소록 목록 action
 */
public class AddressListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		AddressDAO dao = new AddressDAO();
		
		request.setCharacterEncoding("euc-kr");
		// 기본값 설정
		int page = 1;

		if(request.getParameter("page") != null){
			page = Integer.valueOf(request.getParameter("page"));
		}
		
		int limit = 10;
		if(request.getParameter("limit") != null){
			limit = Integer.valueOf(request.getParameter("limit"));
		}
		
		String search = "";
		if(request.getParameter("search") != null){
			search = request.getParameter("search");
		}	
		
		String searchWhat = "아이디";
		if(request.getParameter("what") != null){
			searchWhat = request.getParameter("userClass");	
		}			

		
		// 내 인덱스
		HttpSession session = request.getSession();
		int userIndex = Integer.valueOf(session.getAttribute("index").toString());
		// 그룹 인덱스
		int groupIndex = 0;	//	0은 기본 그룹이다.
		String groupIndexStr = (String)request.getParameter("groupIndex");
		if(groupIndexStr != null){
			groupIndex = Integer.valueOf(groupIndexStr);
		}
		
		int start = (page -1 ) * limit +1;		// 시작 번호
		int listSize = dao.getAddressSize(userIndex, groupIndex);		// 주소록 갯수
		//	리스트 번호
		int no = listSize - (page - 1) * limit;		
		ArrayList<AddressBean> list =
				(ArrayList<AddressBean>)dao.getAddressList(userIndex, groupIndex, start,  start * limit);
		// 페이지 네이션 처리
		String pagiNation = SMSUtil.makePagiNation(listSize, page, limit, "AddressListAction.ad", "groupIndex=" + groupIndex);  
		
		request.setAttribute("no", no);								// 리스트 번호		
		request.setAttribute("listSize", listSize);					// 총  주소록그룹 갯수
		request.setAttribute("list", list);								// 주소록 리스트
		request.setAttribute("groupIndex", groupIndexStr);		
		request.setAttribute("pagiNation", pagiNation);			// 페이지네이션
		forward.setPath("./address/address_list.jsp");
		
		return forward;
	}

}
