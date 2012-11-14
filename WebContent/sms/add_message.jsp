<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>		
<%@ page import="kr.go.police.sms.*" %>		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int userIndex = Integer.valueOf(session.getAttribute("index").toString());
	// 내 그룹 목록 가져오기
	SmsDAO dao = new SmsDAO();	
	List<Group> groupList = dao.getMyGroupList(userIndex);		
%>	
<c:set var="groups"  value ="<%=groupList %>" />
<%-- 헤더  --%>
<jsp:include page="../include/header.jsp" />
  <style>
    .column { width: 170px; float: left; padding-bottom: 100px; }
    .portlet { margin: 0 1em 1em 0; cursor: pointer; }
    .portlet-header { margin: 0.3em; padding-bottom: 4px; padding-left: 0.2em; }
    .portlet-header .ui-icon { float: right; }
    .portlet-content { padding: 0.4em; }
    .ui-sortable-placeholder { border: 1px dotted black; visibility: visible !important; height: 50px !important; }
    .ui-sortable-placeholder * { visibility: hidden; }
</style>
<script>
$(function() {
	// 메세지 박스 처리
    $( ".column" ).sortable({
        connectWith: ".column"
    });
    $( ".portlet" ).addClass( "ui-widget ui-widget-content ui-helper-clearfix ui-corner-all" )
        .find( ".portlet-header" )
            .addClass( "ui-widget-header ui-corner-all" )
            .prepend( "<span class='ui-icon ui-icon-minusthick'></span>")
            .end()
        .find( ".portlet-content" );

    $( ".portlet-header .ui-icon" ).click(function() {
        $( this ).toggleClass( "ui-icon-minusthick" ).toggleClass( "ui-icon-plusthick" );
        $( this ).parents( ".portlet:first" ).find( ".portlet-content" ).toggle();
    });

    $( ".column" ).disableSelection();
    
    // 문자함 추가
    $("#add_btn").click(function(){
    	// 그룹 인덱스값 
    	$("#groupIndex").val($("#group_select option:selected").val());
    	// 그룹명
    	$("#groupName").val($("#group_select option:selected").text());    	
    	alert($("#groupName").val());
    	var title = $("#title").val();
    	var message = $("#message").val();    	
    	if(title.length <= 0){
    		alert("제목을 입력하세요");
    		return;
    	}
    	
    	if(message.length <= 0){
    		alert("내용을 입력하세요");
    		return;
    	}    	
    	
    	$("#add_message_form").submit();
    });
});
</script>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../include/topmenu.jsp" />

		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../include/sidebox.jsp" />
			<div id="contentsWrap">
				<h3>
					<img src="../images/lettersend/title.gif" alt="내문자함" />
				</h3>
				<form style="float: right;">
					<select id="group_select">
						<option value="0">기본그룹</option>
						<c:forEach var="group"  items="${groups}" >
							<option value="${group.index}">${group.group}</option>
						</c:forEach>							
					</select>
				</form>
				<div>
					<!--  문자함 추가 폼 -->
					<form id="add_message_form" method="post" action="../MyMessageAddAction.sm">
							<input value=""	type="hidden" id="groupIndex" name="groupIndex" />	
							<input value=""	type="hidden" id="groupName" name="groupName" />							
							<input value="" title="제목을 입력하세요"	type="text"	 id="title" name="title" /><br/>
							<textarea rows="5" cols="50" id="message" name="message"></textarea>
							<a href="#" id="add_btn">추가</a>
					</form>
				</div>
			</div>
		<div id="footer">푸터영역</div>
	</div>
</body>

</html>