<%@ page contentType="text/html;charset=euc-kr" %> 
<% request.setCharacterEncoding("euc-kr"); %> 
<%@ page import="sms.send.SendDS" %>
<%@ page import="sms.common.*, java.util.*" %> 
<jsp:useBean id="setRegbean" class="sms.common.RegBean" />
<jsp:setProperty name="setRegbean" property="*" />
<%
request.setCharacterEncoding("utf-8");
boolean flag = false;
try{
	flag = SendDS.instance().message_insert_ever(setRegbean); 
}catch(Exception e){
	out.println("���� = "+ e );
}%>
<%=flag%>