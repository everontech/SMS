<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ page import="java.util.*" %>	    
<%@ page import="kr.go.police.board.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	List<BoardBean> list = (List<BoardBean>)request.getAttribute("list");
	// 페이지네이션
	String pagiNation = (String)request.getAttribute("pagiNation");
	// 리스트 갯수
	int listSize = (Integer)request.getAttribute("listSize");	
	//	리스트 번호
	int no = (Integer)request.getAttribute("no");		
%>
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
					<img src="images/notice/title_notice.gif" alt="문의보기" />
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
					<!--  공지사항이 없는경우 -->
					<c:if test="${empty list}">
						<tr>
							<td colspan="5"> 문의사항이 없습니다.</td>
						</tr> 
					</c:if>
										
					<!-- 문의목록 -->
					<c:forEach var="data"  items="${list}" >
						<tr>
							<td class="num"><%=no--%></td>
							<td class="tite">
									${data.newIcon?'<img src="images/notice/icon_n.gif" />':''}							
									<a href="./BoardDetailAction.bo?index=${data.index}">${data.notice?'[공지]':''}
									${data.title}</a>
							</td>
							<td class="writer">${data.registerName}</td>
							<td class="day">${data.regDate}</td>
							<td class="hit">${data.viewCount}</td>
						</tr>
					</c:forEach>		
					</tbody>
				</table>
				<c:if test="${(empty list) == false}">
					${pagiNation}
				</c:if>
			</div>
		</div>
	</div>
<jsp:include page="../modules/footer.jspf" />	
</body>
<script type="text/javascript">
<!--
$(function(){

	
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
    
    
	// 메뉴 처리
	$("#top_menu4").attr("data-on", "on");
	$("#top_menu4 > img").attr("src", "./images/top/menu04_on.gif");
	$("#board_view_menu > img").attr("src", "./images/top/menu_sub07_on.gif");
	$("#board_view_menu").attr("data-on", "on");
	$("#top_menu4").trigger("mouseover");
	$(".gnb_sub4").show();
	
});	
//-->
</script>
</html>