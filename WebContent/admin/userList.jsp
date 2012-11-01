<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// requset 로 부터 유저 리스트를 가져온다.
	List<UserBean> list = (List<UserBean>)request.getAttribute("userList");
%>	
<c:set var="list"  value ="<%=list %>" />
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/sms.css"/>
<script type="text/javascript" src="./js/sms_page.js"></script>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />

		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />

			<div id="contentsWrap">
				<h3>
					<img src="./images/lettersend/title.gif" alt="문자보내기" />
				</h3>
				<div>
					<!--  유저 리스트 부분 -->
					<table>
					<c:forEach var="user"  items="${list}" >
						<tr>
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.name}</a>
					       </td>
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.psName}</a>
					       </td>					
							<td>					
					   		   <a href="./UserDetailActionac?index=${user.index}" >${user.deptName}</a>
					       </td>	
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.id}</a>
					       </td>						       					              
					     </tr> 
					</c:forEach>
					</table>
				</div>
			</div>
		</div>
		<div id="footer">푸터영역</div>
	</div>
</body>


</html>