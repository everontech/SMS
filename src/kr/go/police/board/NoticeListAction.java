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
			try{
				page = Integer.valueOf(request.getParameter("page"));
			}catch(NumberFormatException e){
				page =1;
			}
		}
		
		// 페이지 목록수
		int limit = 10;
		if(request.getParameter("limit") != null){
			try{
				limit = Integer.valueOf(request.getParameter("limit"));
			}catch(NumberFormatException e){
				limit = 10;
			}
		}
		
		// 검색 종류
		/*
		String what = "이름";
		if(request.getParameter("what") != null){
			what = request.getParameter("what");	
		}
		*/			
		// 검색어 
		String search = "";
		if(request.getParameter("search") != null){
			search = request.getParameter("search").trim().replace("-", "");
			// 한글 처리
			search = new String(search.getBytes("iso-8859-1"), "EUC-KR"); 
		}			
		
		int start = (page -1 ) * limit +1;							// 시작 번호
		int listSize = dao.getNoticeListCount(search);		// 게시물 갯수
		//	리스트 번호
		int no = listSize - (page - 1) * limit;		
		ArrayList<BoardBean> list =
				(ArrayList<BoardBean>)dao.getNoticeList(start,  start * limit, search);
		// 페이지 네이션 처리
		String params = "limit=" +limit +  "&search=" + search;
		String pagiNation = SMSUtil.makePagiNation(listSize, page, limit, "NoticeListAction.bo", params);  
		
		request.setAttribute("no", no);								// 리스트 번호		
		request.setAttribute("listSize", listSize);					// 총  게시물 갯수
		request.setAttribute("list", list);								// 게시물 리스트
		request.setAttribute("limit", limit);							// 한페이지 목록수	
		request.setAttribute("search", search);						// 검색				
		request.setAttribute("pagiNation", pagiNation);			// 페이지네이션
		forward.setPath("./WEB-INF/board/notice_list.jsp"); 
		return forward;
	}

}
