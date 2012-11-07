<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>		
<%@ page import="kr.go.police.sms.*" %>		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// requset 로 부터 내 문자 내역을 가져온다.
	List<Message> list = (List<Message>)request.getAttribute("messages");
%>	
<c:set var="messages"  value ="<%=list %>" />
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
  <style>
    .column { width: 170px; float: left; padding-bottom: 100px; }
    .portlet { margin: 0 1em 1em 0; }
    .portlet-header { margin: 0.3em; padding-bottom: 4px; padding-left: 0.2em; }
    .portlet-header .ui-icon { float: right; }
    .portlet-content { padding: 0.4em; }
    .ui-sortable-placeholder { border: 1px dotted black; visibility: visible !important; height: 50px !important; }
    .ui-sortable-placeholder * { visibility: hidden; }
</style>
<script type="text/javascript" src="../js/sms_page.js"></script>
<script>
$(function() {
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
});
</script>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />

		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />

			<div id="contentsWrap">
				<h3>
					<img src="../images/lettersend/title.gif" alt="내문자함" />
				</h3>
				
					<c:forEach var="msg"  items="${messages}" >
									<div class="column">
								    <div class="portlet">
								        <div class="portlet-header">${msg.title}</div>
								        <div class="portlet-content">${msg.message}</div>
								    </div>					
						 </div>
					</c:forEach>				
				
				<div class="column">
				 
				    <div class="portlet">
				        <div class="portlet-header">Feeds</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				    <div class="portlet">
				        <div class="portlet-header">News</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				</div>
				 
				<div class="column">
				    <div class="portlet">
				        <div class="portlet-header">Shopping</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				 
				</div>
				 
				<div class="column">
				 
				    <div class="portlet">
				        <div class="portlet-header">Links</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				 
				    <div class="portlet">
				        <div class="portlet-header">Images</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				 
				</div>		
				
				
				<div class="column">
				 
				    <div class="portlet">
				        <div class="portlet-header">Links</div>
				        <div class="portlet-content"><a href="#">개와 손가방 2개에 나눠 담아 차를 몰고 청와대 관저로 가져갔다. 특검은 시형씨의 6억원 운송 과정에 김 전 행정관, 설씨, 청와대 경호처 직원 정모씨 등이 개입한 것으로 보고 있다.</a></div>
				    </div>
				 
				    <div class="portlet">
				        <div class="portlet-header">Images</div>
				        <div class="portlet-content">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</div>
				    </div>
				 
				</div>						
				
			</div>
		<div id="footer">푸터영역</div>
	</div>

</body>


</html>