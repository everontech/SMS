<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.sms.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// requset 로 부터 유저 리스트를 가져온다.
	List<SMSBean> list = (List<SMSBean>)request.getAttribute("sendList");
	// 페이지네이션
	String pagiNation = (String)request.getAttribute("pagiNation");
	// 리스트 갯수
	int listSize = (Integer)request.getAttribute("listSize");	
	//	리스트 번호
	int no = (Integer)request.getAttribute("no");	
%>
<c:set var="list"  value ="<%=list %>" />	
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<style>
.message{
	display: inline-block; width: 200px; text-overflow:ellipsis; white-space: nowrap; overflow: hidden;
}
#sendResultList td{
	cursor: pointer;
}
</style>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />
		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />
     	   <div class="boderWrap">
				<h3>
					<img src="images/notice/title_notice.gif" alt="전송내역" />
				</h3>
				<!--게시판-->
				<div style="float: right;  margin-bottom: 5px;">
					<a href="#" onclick="return false;" id="del_btn">삭제</a>
				</div>	
				<table id="sendResultList"  style="clear: both;" width="100%" border="0" cellpadding="0" cellspacing="0">
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
							<th><input id="select_all"  type="checkbox"   /></th>							
							<th>No.</th>
							<th>받는번호</th>							
							<th>메세지</th>
							<th>전송결과</th>
							<th>전송타입</th>
							<th>발송시간</th>																							
						</tr>
					</thead>
					<tbody>
					
					<!--  발송내역이 없는경우 -->
					<c:if test="${empty list}">
						<tr>
							<td colspan="7"> 발송내역이 없습니다.</td>
						</tr> 
					</c:if>
										
					<!--  발송내역 리스트  -->
					<c:forEach var="data"  items="${list}" >
						<tr>
							<td>					
					   		   <input  type="checkbox" name="del" value="${data.index}"   />
					       </td>							
							<td>					
					   		   <%=no--%>
					       </td>
							<td>					
					   		   ${data.fromPhone}
					       </td>						       
							<td>					
					   		   <a 	title="${data.message}"  class="message" href="#" onclick="return false;" >${data.message}</a>
					       </td>					
							<td>					
					   		 ${data.sendResult}
					       </td>	
							<td>					
					   		   MMS
					       </td>						       
							<td>					
					   		   ${data.regDate}
					       </td>	
					     </tr> 
					</c:forEach>
					</tbody>
				</table>
				<c:if test="${(empty list) == false}">
					${pagiNation}
				</c:if>	
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
    $("#sendResultList tbody tr").each(function(i){
       if(i % 2 == 0){
        $(this).children('td').css('background', '#efe');
       } 
    });
 
    // 테이블 현재 열 강조 효과
    $("#sendResultList td").hover(
      function () {
        $(this).siblings().andSelf().addClass("hover");
      },
      function () {
        $(this).siblings().andSelf().removeClass("hover");
      }
    );   
    
    // 메시지를 볼수 있도록 툴팁처리
    $(".message").tooltip();
    
    // 삭제처리
    $("#del_btn").button().click(function(){
    	var delArr = [];
    	$("input:checked").not("#select_all").each(function(){
    		delArr.push($(this).val());
    	});
    	
    	alert(delArr.join(","));
    });
    
    $("#select_all").change(function(){
    	// 이요소가 체크되어 있다면 다른 체크박스들도 전부 체크를 활성화시킨다.
    	if(this.checked){
    		$("input:checkbox").attr("checked", "checked");
    	}else{
    		$("input:checkbox").removeAttr("checked");
    	}
    });
    
    // 테이블 td를 클릭하면 해당 row의 chekcbox상태 변경
    $("#sendResultList tbody td").click(function(){
    	var $check = $(this).siblings().first().children("input:checkbox");
    	if($check.attr("checked") == "checked"){
    		$check.removeAttr("checked");    		
    	}else{
    		$check.attr("checked", "checked");
    	}
    });    

    $(".gnb_sub1").show();
	$("#top_menu1").attr("data-on", "on");
	$("#top_menu1 > img").attr("src", "./images/top/menu01_on.gif");
	$("#send_result_list_menu > img").attr("src", "./images/top/menu_sub03_on.gif");
	$("#send_result_list_menu").attr("data-on", "on");
});	
//-->
</script>
</html>