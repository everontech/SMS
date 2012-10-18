<%@ page contentType="text/html;charset=euc-kr" %> 
<%@ page import="sms.common.*, java.util.*" %>
<%@ page import="sms.send.SendDS, sms.restrict.RestrictSms" %>
<jsp:useBean id="setRegbean" class="sms.common.RegBean" />
<jsp:setProperty name="setRegbean" property="*" />
<%
	request.setCharacterEncoding("utf-8");
	String restrict_id = (String)request.getParameter("f_id");
	int restrict_count = Integer.parseInt( (String)request.getParameter("f_count") );
	
	boolean flag = false;
	try{
		flag = SendDS.instance().insertSend(setRegbean); 
	}catch(Exception e){
		out.println("에러 = "+ e );
	}

	int sended_count = 0;	// 차감된 메시지 갯수
	try{
		sended_count = RestrictSms.getInstance().deduct(restrict_id, restrict_count); 
	}catch(Exception e){
		out.println("에러 = "+ e );
	}	

	out.println( flag );
%>

