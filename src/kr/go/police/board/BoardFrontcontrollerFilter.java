package kr.go.police.board;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.go.police.LoginCheck;
import kr.go.police.action.Action;

/**
 * Servlet Filter implementation class SmsFrontcontrollerFilter
 */
@WebFilter(description = "게시판관련필터", urlPatterns = { "*.bo" })
public class BoardFrontcontrollerFilter implements Filter {

    /**
     * Default constructor. 
     */
    public BoardFrontcontrollerFilter() {
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		System.out.println("do filter~");
		// httpservlet 으로 변환
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		// url 얻기
		/*
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		*/
		request.setCharacterEncoding("EUC-KR");

		// 로그인 여부 확인
		if(!LoginCheck.checkLogin(request, response)){
			return;
		}
		
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
