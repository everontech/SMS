 <%@page import="kr.go.police.account.AccountDAO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	    
<%@ page import="kr.go.police.account.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	AccountDAO dao = new AccountDAO();
	int rencentUser = (Integer)dao.getRecentJoinUserSize();
%>    
<c:set var="recent_user_size" value="<%=rencentUser%>" />
    	<div id="subWrap">
        	<div class="manager_box">
            	<ul>
                	<li><strong>관리자님</strong> 반갑습니다.</li>
                    <li>신규 회원 가입 수 : <span>${recent_user_size}</span> 명</li>                   
                </ul>
                <div class="btn">
            		<a href="./LogoutAction.ac"><img src="./images/boder/btn_logout.gif" alt="로그아웃" border="0"/></a>                               
                </div>
            </div>
     </div>