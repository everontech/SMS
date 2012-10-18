<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="sms.send.MessageDS" %>
<%@ page import="sms.common.*, java.util.*" %> 
<jsp:useBean id="setRegbean" class="sms.common.RegBean" />
<jsp:setProperty name="setRegbean" property="*" />
<%
request.setCharacterEncoding("utf-8");
boolean flag = false;
try{
	flag = MessageDS.instance().getMessage_mod_ever(setRegbean);     
}catch(Exception e){
	out.println("¿¡·¯ = "+ e );
}%>
<%=flag%> 
