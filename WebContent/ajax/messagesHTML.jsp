<%@ page contentType="text/html; charset=utf-8" %>
<%@ page import="java.util.*" %>
<%@ page import="sms.common.Utility" %>
<%@ page import="sms.common.RegBean" %>
<jsp:useBean id="b_sel1" scope="page" class="sms.send.SendDS" />
<jsp:useBean id="b_sel2" scope="page" class="sms.send.MessageDS" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <!-- JTSL -->  
<% 
	request.setCharacterEncoding("utf-8"); 
	String f_id = (String)session.getAttribute("f_id");
	String f_deptname = (String)session.getAttribute("f_deptname");
	String f_deptcode = (String)session.getAttribute("f_deptcode");
	String f_class = (String)session.getAttribute("f_class");
	String f_name = (String)session.getAttribute("f_name");
	String groupName = (String)request.getParameter("sms_group");
	String flag_group = (String)request.getParameter("flag_group");

	if( groupName == "" || groupName == null ){ // 검색글자가 없을 경우
		groupName ="전체";
	}
	String pscode = f_deptcode.substring(0,3);
	String chk ="sub";	
	Vector list  = new Vector();
	if( flag_group.equals("public") ){ // 공통 메세지일 경우
		try{
			list = b_sel1.group_search(chk, request);     
		}catch(Exception e){
			out.println("error ="+ e +"<Br>");
		}	

	}else if( flag_group.equals("my") ){ // 나의 메세지일 경우
		try{
			list = b_sel2.group_search2(chk, f_id, request);     
			out.println(b_sel2.Error_show()); 
		}catch(Exception e){
			out.println("error ="+ e +"<Br>");
		}	
	}
	int cnt = list.size();
	int i=0;int j=0;
%>
<a class="prev browse"><img src="../images/left.gif" alt="이전버튼"/></a>		
<div id="messageLoadBox" class="scrollable" align="left">
<div class="items">
	<div>
		<c:set var="cnt" value="<%=cnt%>" />
		<c:forEach var="j" begin="1" end="${cnt}">
		<% RegBean be = (RegBean)list.get(j++); %>  
		<div class="bx">
			<textarea readonly='true' class="phon" rows="5"><%=be.getF_message_text()%></textarea>
			<p class="title"><%= be.getF_message_title()%></p>
			<input type="hidden" value="<%=be.getF_idx()%>" />
		</div>
		<% if( j % 5 == 0){ %>
			</div><div>
		<% } %>
		</c:forEach>
	</div>
</div>
</div>
<a class="next browse"><img src="../images/right.gif" alt="다음버튼"/></a>
