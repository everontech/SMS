<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>		
<%@ page import="kr.go.police.sms.*" %>		
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// ������ �׷� �ε���
	String groupIndex = "0";	// ������ �⺻ �׷� �ε�����
 	if(request.getParameter("groupIndex") != null){
		groupIndex = (String)request.getParameter("groupIndex");
 	}
	// requset �� ���� �� ���� ������ �����´�.
	List<Message> list = (List<Message>)request.getAttribute("messages");
	// �׷� ��� 
	List<Message> groupList = (List<Message>)request.getAttribute("groups");
%>	
<c:set var="messages"  value ="<%=list %>" />
<c:set var="groups"  value ="<%=groupList %>" />
<c:set var="groupIndex"  value ="<%=groupIndex%>" />
<%-- ���  --%>
<jsp:include page="../modules/header.jsp" />
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
	// �޼��� �ڽ� ó��
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
    
    $("#group_select").change(function () {
    	var groupIndex = $(this).children("option:selected").val();
    	window.location.href= "./MyMessageAction.sm?groupIndex=" +  groupIndex;
    });
});
</script>
<body>
	<div id="wrapper">
		<%-- ��ܸ޴�  --%>
		<jsp:include page="../modules/topmenu.jsp" />

		<div id="contents">
			<%-- ���̵� �޴�  --%>
			<jsp:include page="../modules/sidebox.jsp" />

			<div id="contentsWrap">
				<h3>
					<img src="../images/lettersend/title.gif" alt="��������" />
				</h3>
				<form style="float: right;">
					<select id="group_select">
						<option value="0">�⺻�׷�</option>
						<c:forEach var="group"  items="${groups}" >
							<option ${groupIndex ==group.index?'selected':''} value="${group.index}">${group.group}</option>
						</c:forEach>							
					</select>
				</form>
				<c:forEach var="msg"  items="${messages}" >
								<div class="column">
							    <div class="portlet">
							        <div class="portlet-header">${msg.title}</div>
							        <div class="portlet-content">${msg.message}</div>
							    </div>					
					 </div>
				</c:forEach>				
			</div>
	</div>
</div>
<jsp:include page="../modules/footer.jspf" />	
</body>

</html>