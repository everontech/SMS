<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ page import="java.util.*" %>	    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
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
	                    <thead>
	                        <tr height="34px;">
	                            <th colspan="2">글쓰기</th>
	                      </tr>
	                    </thead>
	                    <tbody>
	                    	  <tr>
	                              <td width="100" ><strong>작성자</strong></td>
	                              <td class="tite"><input type="text" class="none"/></td>
	                          </tr>
	                          <tr>
	                              <td><strong>비밀번호</strong></td>
	                              <td class="tite"><input type="password" class="none"/></td>
	                          </tr>
	                          <tr>
	                              <td><strong>제목</strong></td>
	                              <td class="tite"><input type="text" class="none" style="width:385px"/></td>
	                    	  </tr>
	                          <tr>
	                              <td><strong>첨부파일</strong></td>
	                              <td class="tite"><a href=""><img src="images/notice/file_btn.gif" alt="파일추가"  style="margin:5px 0 0 10px;" /></a><br />
	                              <input type="text"  class="none" style="width:293px; vertical-align:middle;"><input type="image" src="images/notice/btn_found.gif" style="padding-left:5px; border:0; vertical-align:middle; margin-bottom:1px;" ></td>  
	                          </tr>
	                   		  <tr  class="end">
	                      		  <td><strong>내용</strong></td>
	                              <td colspan="2"><textarea></textarea></td>
	                    	  </tr>
	                  </tbody>                
	          	</table>
	         	<div class="btn">
	         		<a href="#"><img src="images/notice/register_btn.gif" alt="등록"/></a>
	         		<a href="#"><img src="images/notice/cancel_btn.gif"  alt="취소"/></a>
	         		<a href="#"><img src="images/notice/list_btn.gif" alt="목록"/></a>
	         	</div>
	   		</div>
		</div>
	</div>
	<jsp:include page="../include/footer.jspf" />	
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