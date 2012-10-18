<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="sms.common.*, java.util.*" %>
<jsp:useBean id="setRegbean" class="sms.common.RegBean" />
<jsp:setProperty name="setRegbean" property="*" />
<%@ page import="sms.send.Send_infoDS" %>
<%
	request.setCharacterEncoding("euc-kr");
	String f_id = (String)session.getAttribute("f_id");
	String f_deptname = (String)session.getAttribute("f_deptname");
	String f_deptcode = (String)session.getAttribute("f_deptcode");
	String f_class = (String)session.getAttribute("f_class");
	String f_name = (String)session.getAttribute("f_name");
	String f_phone1 = (String)session.getAttribute("f_phone1"); 

	boolean flag = false;
	
	try{
		
		flag = Send_infoDS.instance().Send_info_multi_delete(request); 
		
	}catch(Exception e){
		out.println("¿¡·¯ = "+ e );
	}//finally{ out.println( flag )};
%>
<%=flag%>