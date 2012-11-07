<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ page import="java.util.*" %>	    
<%@ page import="kr.go.police.board.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	List<BoardBean> list = (List<BoardBean>)request.getAttribute("list");
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
				<table id="board_table" width="100%" border="0" cellpadding="0" cellspacing="0">
					<colgroup>
						<col width="10%" />
						<col width="" />
						<col width="18%" />
						<col width="15%" />
						<col width="8%" />
					</colgroup>
					<thead>
						<tr height="34px;">
							<th>NO.</th>
							<th>제목</th>
							<th>작성자</th>
							<th>등록일</th>
							<th>조회</th>
						</tr>
					</thead>
					<tbody>
					<!-- 공지사항 -->
					<c:forEach var="data"  items="${list}" >
						<tr>
							<td class="num">${data.index}</td>
							<td class="tite">
									${data.hasFile?'<img src="images/notice/icon_disk.gif" />':''}
									${data.newIcon?'<img src="images/notice/icon_n.gif" />':''}							
									<a href="./boardDetailAction.bo?index=${data.index}">${data.notice?'[공지]':''}
									${data.title}</a>
							</td>
							<td class="writer">${data.registerName}</td>
							<td class="day">${data.regDate}</td>
							<td class="hit">${data.viewCount}</td>
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
	// 메뉴 처리
	$("#top_menu4").attr("data-on", "on");
	$("#top_menu4 > img").attr("src", "./images/top/menu04_on.gif");
	$("#board_view_top_menu > img").attr("src", "./images/top/menu_sub07_on.gif");
	$("#board_view_top_menu").attr("data-on", "on");
	$("#top_menu4").trigger("mouseover");
	
    // odd td colume stand out
    $("#board_table tbody tr").each(function(i){
       if(i % 2 == 0){
        $(this).children('td').css('background', '#efe');
       } 
    });
 
    // 테이블 현재 열 강조 효과
    $("#board_table td").hover(
      function () {
        $(this).siblings().andSelf().addClass("hover");
      },
      function () {
        $(this).siblings().andSelf().removeClass("hover");
      }
    );   
    
});	
//-->
</script>
</html>