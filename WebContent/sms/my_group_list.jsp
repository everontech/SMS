<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.sms.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// requset 로 부터 그룹리스트를 가져온다.
	List<Group> groupList = (List<Group>)request.getAttribute("groups");
%>	
<c:set var="list"  value ="<%=groupList %>" />
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<body>
	<div id="dialog-modify-form" title="그룹명 변경">
		<form method="post" id="modifyFrm" action="./GroupModifyAction.sm" style="margin-top: 20px">
		    <fieldset>
				<input type="hidden" id="groupIndex" value=""  name="groupIndex"  />		    
		        <input type="text" size="30" name="groupName" id="groupName" class="text ui-widget-content ui-corner-all" />
		    </fieldset>
	    </form>
	</div>
	<div id="dialog-add-form" title="그룹 추가">
		<form method="post" id="addFrm" action="./GroupAddAction.sm" style="margin-top: 20px">
		    <fieldset>
		        <input type="text" size="30" name="groupName" id="groupName" class="text ui-widget-content ui-corner-all" />
		    </fieldset>
	    </form>
	</div>	
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/admin_topmenu.jsp" />
		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/admin_sidebox.jsp" />
     	   <div class="boderWrap">
				<h3>
					<img src="images/notice/title_notice.gif" alt="내그룹" />
				</h3>
				<form id="delForm" action="./GroupDelAction.sm">
					<input value="" id="groupIndex" name="groupIndex" type="hidden" />
				</form>				
				<!--게시판-->
				<div style="float: right;"><a href="#"  id="add_btn">추가</a></div>
				<table style="float: left;" id="groupList" width="100%" border="0" cellpadding="0" cellspacing="0">
					<colgroup>
						<col width="15%" />
						<col width="70%" />
						<col width="15%" />						
					</colgroup>
					<thead>
						<tr height="34px;">
							<th>No.</th>
							<th>그룹명</th>				
							<th>삭제</th>										
						</tr>
					</thead>
					<tbody>
					<!--  내그룹 리스트 -->
					<c:forEach var="user"  items="${list}" >
						<tr>
							<td>					
					   		   ${user.index}
					       </td>
							<td>					
					   		   <a class="group_td"  data-index="${user.index}"  href="#" onclick="javascript:return false;" >${user.group}</a>
					       </td>	
							<td>					
					   		   <a class="group_del" data-index="${user.index}"  href="#" onclick="javascript:return false;" ><img src="./images/sms/bt_categoryAll_close.gif" alt="삭제" /></a>
					       </td>					       		
					     </tr> 
					</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
<jsp:include page="../modules/footer.jspf" />
</body>
<script type="text/javascript">
<!--
$(function(){
    // odd td colume stand out
    $("#groupList tbody tr").each(function(i){
       if(i % 2 == 0){
        $(this).children('td').css('background', '#efe');
       } 
    });
 
    // 테이블 현재 열 강조 효과
    $("#groupList td").hover(
      function () {
        $(this).siblings().andSelf().addClass("hover");
      },
      function () {
        $(this).siblings().andSelf().removeClass("hover");
      }
    );   
    
    // 수정 다이얼로그 설정
	$( "#dialog-modify-form" ).dialog({
            autoOpen: false,
            modal: true,
            width : 250,
            buttons: {
                "수정": function() {
                	var groupVal = $("#modifyFrm #groupName").val();
                	if(groupVal.length <= 0){
                		alert("그룹명을 입력하세요");
                		return;
                	}                	
                	$("#modifyFrm").submit();           	
                },
                "취소": function() {
                    $( this ).dialog( "close" );
                }
            }
    }); 
    
    //  추가 다이얼로그 설정
	$( "#dialog-add-form" ).dialog({
            autoOpen: false,
            modal: true,
            width : 250,
            buttons: {
            	// 그룹 추가 처리
                "추가": function() {
                	var groupVal = $("#addFrm #groupName").val();
                	if(groupVal.length <= 0){
                		alert("그룹명을 입력하세요");
                		return;
                	}     
                	$("#addFrm").submit();                        	
                },
                "취소": function() {
                    $( this ).dialog( "close" );
                }
            }
    });     
    
    // 그룹추가버튼
    $("#add_btn").click(function(){
    	$( "#dialog-add-form" ).dialog( "open" );
    });
    
    //  그룹명 변경 처리
    $(".group_td").click(function(){
    	$( "#dialog-modify-form" ).dialog( "open" );
    	var groupName = $(this).text();
    	$("#modifyFrm #groupName").val(groupName);
		var index = $(this).attr("data-index");    	
    	$("#modifyFrm #groupIndex").val(index);    	
    });
    
    // 그룹 삭제 처리
	$(".group_del").click(function(){
		// 그룹명 얻기
		var group = $(this).parent("td").siblings().first().next().children().text();
		if(confirm(group + "그룹을 삭제 하시겠습니까?")){
			var index = $(this).attr("data-index");
			// 삭제 폼 전송
			$("#delForm > input:first").val(index);
			$("#delForm").submit();
		}
	});  
	
	//modify_btn
    

	$("#top_menu2").attr("data-on", "on");
	$("#top_menu2 > img").attr("src", "./images/top/menu02_on.gif");
	//$("#my_group_menu > img").attr("src", "./images/top/menu_sub07_on.gif");
	$("#my_group_menu").attr("data-on", "on");
	$("#top_menu2").trigger("mouseover");

});	
//-->
</script>
</html>