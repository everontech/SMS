<%@ page contentType="text/html; charset=euc-kr" %>
<%@ page import="java.util.*" %>
<%@ page import="sms.qna.util.Utility" %>
<%@ page import="sms.qna.bean.Board_Entity" %>
<%@ page import="sms.qna.util.ServerInfo" %>

<jsp:useBean id="b_data_sub" scope="page" class="sms.qna.bean.Board_Entity"/>
<jsp:setProperty name="b_data_sub" property="*"/>

<jsp:useBean id="b_query" scope="page" class="sms.qna.bean.Board_Query_G"/>

<%
	String f_id = (String)session.getAttribute("f_id");
	String f_class =(String)session.getAttribute("f_class");
%>

<% 
//out.println("data = "+ b_data.toInfoString());
boolean han_yn = ServerInfo.GET_HAN;
try{
	b_query.getSin_Data(b_data_sub);
    //out.println("Msg = "+ b_query.show_msg()); 
	out.println(b_query.Error_show()); 
}catch(Exception e){
	out.println("error ="+ e +"<Br>");
}	 	

%>
<style>
.notice caption {background: none;}
.notice{ border: none;}
#noticeBtn{ text-align: right; cursor: pointer; }
#noticeTitle{margin-bottom: -15px;}
</style>
<div id="noticeTitle"><img src="../images/gongji_title1.gif" alt="공지사항" /></div>
<table class="join_tbl notice" cellpadding="0" cellspacing="0">
<!-- <caption><img src="../images/gongji_title1.gif" alt="공지사항" /></caption>-->
<colgroup>
	<col width="20%">
	<col width="50%">
	<col width="15%">
	<col width="15%">
</colgroup>
<tr>
	<th colspan="3"><%=b_data_sub.getTitle(false)%></th>
	<td>조회수: <%=b_data_sub.getHit_cnt()%></td>
</tr>
<tr>
	<th>이름</th>
	<td><%=b_data_sub.getReg_name(han_yn)%></td>
	<th>등록일</th>
	<td><%= Utility.date_format1(b_data_sub.getReg_date(), ".")%></td>
</tr>
<tr>
	<th>내용</th>
	<td colspan="3">
	<textarea class="textarea01_v" name="content"  readonly="readonly" style="border:0;font-size:9pt;" ><%=b_data_sub.getContent(han_yn)%></textarea>
	</td>
</tr>
<tr>
	<th>파일</th>
	<td colspan="3">
	<%		if(b_data_sub.getFile_name1(false).trim().length() > 0 ) { %>
	<a href='../system/download.jsp?file_name1=<%=b_data_sub.getFile_name1(false)%>'>
	<%=b_data_sub.getFile_name1(false) %>
	<img src="../../images/fileIcons/<%=Utility.file_ImgSel(b_data_sub.getFile_name1(false).trim())%>" height="16" alt="" border="0"/></a>
	<%		}else { out.println(" 파일이 없습니다.");  }  %>
	</td>
</tr>
</table>
<div id="noticeBtn"><img src="../images/btn_close.gif" alt="닫기버튼" class="close" /></div>		
