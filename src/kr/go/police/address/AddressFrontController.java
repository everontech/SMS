package kr.go.police.address;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.LoginCheck;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	주소록 컨트롤러
 */
public class AddressFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// 로그인 여부 확인
		if(!LoginCheck.checkLogin(request, response)){
			return;
		}
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		// 내 주소록 목록
		if (command.equals("/AddressListAction.ad")) {
			action = new AddressListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 내 주소록 그룹 목록			
		} else if (command.equals("/AddressGroupListAction.ad")) {
			action = new AddressGroupListAction();
			try {
				forward = action.execute(request, response);
				response.setContentType("text/html;charset=euc-kr");	
				//response.setHeader(arg0, arg1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 주소록 그룹추가
		} else if (command.equals("/GroupAddAction.ad")) {
			action = new GroupAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 그룹 삭제
		} else if (command.equals("/GroupDelAction.ad")) {
			action = new GroupDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 그룹 수정처리
		} else if (command.equals("/GroupModifyAction.ad")) {
			action = new GroupModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 주소록 추가
		} else if (command.equals("/AddressAddAction.ad")) {
			action = new AddressAddAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 주소록 수정
		} else if (command.equals("/AddressModifyAction.ad")) {
			action = new AddressModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 주소록 삭제
		} else if (command.equals("/AddressDelAction.ad")) {
			action = new AddressDelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}		
		}
		
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}