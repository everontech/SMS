<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="sms.send.SendDS" %>
<%@ page import="sms.common.*, java.util.*" %> 
<jsp:useBean id="setRegbean" class="sms.common.RegBean" />
<jsp:setProperty name="setRegbean" property="*" />
 <%
request.setCharacterEncoding("euc-kr");
boolean flag = false;
try{
	flag = SendDS.instance().group_delete(request);  
}catch(Exception e){
	out.println("¿¡·¯ = "+ e );
}%>
<%=flag%>