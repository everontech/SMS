<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ page import="java.util.*" %>	    
<%@ page import="kr.go.police.board.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%
	BoardBean data = (BoardBean)request.getAttribute("data");
	List<BoardBean> replyList = (List<BoardBean>)request.getAttribute("replyList");
	
%>  
<c:set var="data"  value ="<%=data%>" />	
<c:set var="replyList"  value ="<%=replyList%>" />	
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
        	<h3><img src="images/notice/title_notice.gif" alt="공지사항" /></h3>
	            <!--게시판-->
	            	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	                   <colgroup>
	                        <col width="10%"/>
	                        <col width="" />
	                        <col width="10%"/>
	                   </colgroup>
	                    <thead>
	                        <tr height="34px;">
	                            <th colspan="3">${data.title}</th>
	                      </tr>
	                    </thead>
	                    <tbody>
	                    	  <tr>
	                              <td><strong>작성자</strong> |</td>
	                              <td class="tite">${data.registerName}</td>
	                              <td>| 조회 : ${data.viewCount}</td>
	                          </tr>
	                          <tr>
	                               <td colspan="3" class="view" height="300">
	                               	 ${data.content}
	                               </td>
	                          </tr>
	                      <tr class="re">
	                               <td><%=session.getAttribute("id")%> |</td>
	                        <td><form id="frm" action="./boardReplyAction.bo?parentIndex=${data.index}" method="post"><textarea id="reply_content" name="reply_content" style="width:580px; height:50px; margin:0 0; padding: 0 0;"></textarea></form></td>
	                               <td><a href="#" id="reply_btn"><img src="images/notice/btn_re.gif" alt="댓글등록" /></a></td>
	                          </tr>
							<!-- 댓글목록 -->
							<c:forEach var="data"  items="${replyList}" >
		                          <tr>
		                                <td><img src="images/notice/icon_re.gif" /><strong> ${data.registerName}</strong></td>
		                                <td class="tite"  >${data.content}</td>
		                                <td>| ${data.regDate}</td>
		                          </tr>
							</c:forEach>		                          
	                  </tbody>                
	          </table>
	          <div class="btn"><a href="./boardListAction.bo"><img src="images/notice/list_btn.gif" alt="목록"/></a></div>
			</div>
		</div>
		</div>
		<div id="footer">푸터영역</div>
	</div>
</body>
<script type="text/javascript">
<!--
$(function(){
	$("#top_menu4").attr("data-on", "on");
	$("#top_menu4 > img").attr("src", "./images/top/menu04_on.gif");
	$("#board_view_top_menu > img").attr("src", "./images/top/menu_sub07_on.gif");
	$("#board_view_top_menu").attr("data-on", "on");
	$("#top_menu4").trigger("mouseover");
});	

$(function(){
	// 댓글 입력 검증 처리 및 전송
	$("#reply_btn").click(function(){
		if(confirm("댓글을 등록하시겠습니까?")){
			var replyContent = $("#reply_content").val();
			if(replyContent.length <= 0){
				alert("댓글 내용이 없습니다.");
				return;
			}
			
			$("form").submit();
		}
	});
});	
//-->
</script>
</html>