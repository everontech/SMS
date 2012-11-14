package kr.co.police.address;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.co.police.LoginCheck;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

public class AddressFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	static final long serialVersionUID = 1L;
	
	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		// �α��� ���� Ȯ��
		if(!LoginCheck.checkLogin(request, response)){
			return;
		}
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/Sms.sm")) {
			action = new SmsAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// �� ������
		} else if (command.equals("/MyMessageAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new MyMessageAction();
				try {
					forward = action.execute(request, response);
					response.setContentType("text/html;charset=euc-kr");	
					//response.setHeader(arg0, arg1);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		// ������ �߰�
		} else if (command.equals("/MyMessageAddAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new MyMessageAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}	
		// �� ������ �׷�
		} else if (command.equals("/MyGroupListAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new MyGroupListAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		// �� ������ �׷�
		} else if (command.equals("/GroupAddAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new GroupAddAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}			
		// �׷� ����
		} else if (command.equals("/GroupDelAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new GroupDelAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}		
		// �׷� ����ó��
		} else if (command.equals("/GroupModifyAction.sm")) {
			if(LoginCheck.checkLogin(request, response)){			
				action = new GroupModifyAction();
				try {
					forward = action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}					
		// ���� ����	
		} else if (command.equals("/ReservedListAction.sm")) {
			action = new ReservedListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		// ��ü���� ���� ����	
		} else if (command.equals("/AllSmsAction.sm")) {
			action = new AllSmsAction();
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