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
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />
		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />
     	   <div class="boderWrap">
				<h3>
					<img src="images/notice/title_notice.gif" alt="공지사항" />
				</h3>
				<!--게시판-->
				<table id="usersList" width="100%" border="0" cellpadding="0" cellspacing="0">
				<!-- 
					<colgroup>
						<col width="10%" />
						<col width="" />
						<col width="18%" />
						<col width="15%" />
						<col width="8%" />
					</colgroup>
				-->	
					<thead>
						<tr height="34px;">
							<th>이름</th>
							<th>아이디</th>							
							<th>경찰서</th>
							<th>부서</th>
							<th>전화번호</th>
							<th>계급</th>							
						</tr>
					</thead>
					<tbody>
					<!--  회원 리스트 -->
					<c:forEach var="user"  items="${list}" >
						<tr>
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.name}</a>
					       </td>
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.id}</a>
					       </td>						       
							<td>					
					   		   <a href="./UserDetailAction.ac?index=${user.index}" >${user.psName}</a>
					       </td>					
							<td>					
					   		   <a href="./UserDetailActionac?index=${user.index}" >${user.deptName}</a>
					       </td>	
							<td>					
					   		   <a href="./UserDetailActionac?index=${user.index}" >${user.phone1}</a>
					       </td>	
							<td>					
					   		   <a href="./UserDetailActionac?index=${user.index}" >${user.grade}</a>
					       </td>						       					       
					     </tr> 
					</c:forEach>
					</tbody>
				</table>
				<!-- 
				<div class="page">
					<a href="#"><img src="images/notice/page_prev_btn.gif" /></a><a
						href="#"><span>1</span></a><a href="#">2</a><a href="#"><img
						src="images/notice/page_next_btn.gif" /></a>
				</div>
				-->
			</div>
		</div>
		</div>
		<div id="footer">푸터영역</div>
	</div>
</body>
<script type="text/javascript">
<!--
$(function(){
	
    // odd td colume stand out
    $("#usersList tbody tr").each(function(i){
       if(i % 2 == 0){
        $(this).children('td').css('background', '#efe');
       } 
    });
 
    // 테이블 현재 열 강조 효과
    $("#usersList td").hover(
      function () {
        $(this).siblings().andSelf().addClass("hover");
      },
      function () {
        $(this).siblings().andSelf().removeClass("hover");
      }
    );   
    
	/*
	$("#top_menu4").attr("data-on", "on");
	$("#top_menu4 > img").attr("src", "./images/top/menu04_on.gif");
	$("#board_view_top_menu > img").attr("src", "./images/top/menu_sub07_on.gif");
	$("#board_view_top_menu").attr("data-on", "on");
	$("#top_menu4").trigger("mouseover");
	*/
});	
//-->
</script>
</html>