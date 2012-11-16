package kr.go.police.board;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.go.police.SMSUtil;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;
import kr.go.police.address.AddressBean;

/**
 *	공지사항 action
 */
public class NoticeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("euc-kr");		
		BoardDAO dao = new BoardDAO();
		// 기본값 설정
		int page = 1;
		if(request.getParameter("page") != null){
			page = Integer.valueOf(request.getParameter("page"));
		}
		// 한페이지 목록수
		int limit = 10;
		if(request.getParameter("limit") != null){
			limit = Integer.valueOf(request.getParameter("limit"));
		}
		// 검색어
		String search = "";
		if(request.getParameter("search") != null){
			search = request.getParameter("search");
		}	
		// 검색 종류
		String searchWhat = "아이디";
		if(request.getParameter("what") != null){
			searchWhat = request.getParameter("what");	
		}			
		
		int start = (page -1 ) * limit +1;					// 시작 번호
		int listSize = dao.getNoticeListCount();		// 게시물 갯수
		//	리스트 번호
		int no = listSize - (page - 1) * limit;		
		ArrayList<BoardBean> list =
				(ArrayList<BoardBean>)dao.getNoticeList(start,  start * limit, "");
		// 페이지 네이션 처리
		String pagiNation = SMSUtil.makePagiNation(listSize, page, limit, "NoticeListAction.bo", null);  
		
		request.setAttribute("no", no);								// 리스트 번호		
		request.setAttribute("listSize", listSize);					// 총  게시물 갯수
		request.setAttribute("list", list);								// 게시물 리스트
		request.setAttribute("pagiNation", pagiNation);			// 페이지네이션
		forward.setPath("./board/notice_list.jsp"); 
		return forward;
	}

}
