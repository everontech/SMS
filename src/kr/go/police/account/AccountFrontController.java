package kr.go.police.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.go.police.LoginCheck;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class AccountFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	
	static final long serialVersionUID = 1L;

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		// 로그인 액션
		if (command.equals("/LoginAction.ac")) {
			action = new LoginAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("로그인 처리 에러");
			}
		// 회원 가입 액션	
		} else if (command.equals("/JoinAction.ac")) {
			action = new JoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 사용자 승인 액션	
		} else if (command.equals("/ApproveAction.ac")) {
			if(LoginCheck.checkLogin(request, response)){
				action = new LoginAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		// 사용자 삭제 액션	
		} else if (command.equals("/UserDelAction.ac")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new LoginAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		// 사용자 조회 액션	
		} else if (command.equals("/UserListAction.ac")) {
			action = new UserListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// 사용자정보 세부 보기	
		} else if(command.equals("/UserDetailAction.ac")){
			if(LoginCheck.checkLogin(request, response)){					
				action = new UserDetailAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		// 사용자정보 세부 보기	
		} else if(command.equals("/MyInfoAction.ac")){
			if(LoginCheck.checkLogin(request, response)){					
				action = new MyInfoAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}			
		// 신규 가입 유저	
		}else if(command.equals("/RecentJoinUsersAction.ac")){	
			if(LoginCheck.checkLogin(request, response)){					
				//action = new RecentJoinUserAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}	
			}
		}else if(command.equals("/MyInfoModify.ac")){
			action = new MyInfoModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
		//  관리자 사용자 정보 수정	
		}else if(command.equals("/AdminModifyUserAction.ac")){
			action = new AdminModifyUserAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		//  로그 아웃
		}else if(command.equals("/LogoutAction.ac")){
			action = new LogoutAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}

		// 아이디 중복 체크
		if (command.equals("/IdCheckAction.ac")) {
			action = new  IdCheckAction();
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