package kr.go.police.account;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import kr.go.police.LoginCheck;
import kr.go.police.action.Action;
import kr.go.police.action.ActionForward;

/**
 *	사용자 계정관련 처리 컨트롤러
 */
public class AccountFrontController extends javax.servlet.http.HttpServlet
		implements javax.servlet.Servlet {
	// key = command , value = Object
	private Map<String, Object> commandMap = new HashMap<String, Object>();
	static final long serialVersionUID = 1L;
	
	/**
	 * init 호출시 config 파일을 읽어와 매핑정보 저장
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		String configFile = config.getInitParameter("configFile");
		Properties prop = new Properties();
		FileInputStream fis = null;
		try{
			// properties path 를 읽어온다.			
			ServletContext context = config.getServletContext();
			String path = (String)context.getInitParameter("propertiesPath");
			System.out.println("properties Path : " + path);				
			fis = new FileInputStream(path + configFile);
			prop.load(fis);
		}catch(IOException ioe){
			System.out.println("config 파일을 읽을수 없습니다.");			
			throw new ServletException();
		}finally{
			if(fis != null){
				try{
					fis.close();
				}catch(Exception e){}
			}
		}
		// command & class 매핑처리
		Iterator<Object> keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()){
			String command = (String)keyIter.next();
			// 해당 클래스이름 얻기
			String className = prop.getProperty(command);
			System.out.println(className  + " ");		
			try{
				// map에 저장처리
				Class<?> actionClass = Class.forName(className);
				Object actionInstance = actionClass.newInstance();
				commandMap.put(command, actionInstance);
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("init complete!");		
		
	}

	protected void doProcess(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// command action 얻기
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = RequestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		//	command 매핑정보로 해당 action 수행
		System.out.println("command : " + command);
		action = (Action)commandMap.get(command);
		// 매핑된 액션이 없을경우 에러페이지로 이동
		if(action == null){
			response.sendRedirect("./error/error_404.jsp");
			return;
		}		
		System.out.println("class  : " + action.getClass().toString());	
		try {
			forward = action.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
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

	
	
/*
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
	*/

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}