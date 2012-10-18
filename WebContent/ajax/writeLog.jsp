<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import="sms.log.SmsLog" %>
<%
	request.setCharacterEncoding("utf-8");
	SmsLog logInstance = SmsLog.getInstance();
	
	String id = (String)session.getAttribute("f_id");
	String ip = (String)request.getRemoteAddr();
	String msg = (String)request.getParameter("msg");
	String extra = (String)request.getParameter("f_callback");	
//	String to_num = (String)request.getParameter("msg");	
	logInstance.insertlog( id, ip, msg, extra ); // log write
%>