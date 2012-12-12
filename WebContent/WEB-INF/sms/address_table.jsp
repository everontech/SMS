<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.address.*"  %>		
<%@ page import="kr.go.police.sms.Group"  %>			
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	int count =0;
	// 주소록 리스트
	List<AddressBean> addressList = (List<AddressBean>)request.getAttribute("list");
%>	
<c:set var="list"  value ="<%=addressList %>" />
<c:set var="size"  value ="<%=addressList.size() %>" />
<?xml version="1.0" encoding="EUC-KR" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>주소록 목록</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr" />
<!-- <link rel="stylesheet" type="text/css" href="../css/address_window_default.css" /> -->
<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
<style>
body{overflow:auto;color:#333;font-size:16px;scrollbar-face-color:#fff;scrollbar-shadow-color:#BEBEBE;scrollbar-3dlight-color:#BEBEBE;scrollbar-arrow-color:#666;scrollbar-darkshadow-color:#FFF;scrollbar-base-color:#F2EDCE;scrollbar-track-color:#F7F7F7;}
a:LINK,a:VISITED{color:#333;text-decoration:none;}
a:HOVER{color:#00b5f5;}
#main{overflow:hidden;}
.ads_tbl{width:100%;text-align:center;}
.ads_tbl thead th{padding: 3px 0px; background-color:#f7f7f7;border-right:1px solid #ccc;border-bottom:1px solid #ccc;color:#333;font-size:12px;}
.ads_tbl tbody tr{border-top:1px solid red;}
.ads_tbl tbody td{font-size:12px;padding:2px 0;}
.odd{background-color:#f7f7f7;border:none;}
</style>
<script> 
jQuery.js = {
	selectAll : function(check){
		var flag = check;
		$("input:checkbox").each(function(){
			this.checked = flag;
		});
	},
	spectrum : function(){  
	   return 'rgb(' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ',' + (Math.floor(Math.random() * 256)) + ')';  
	 }
};

$(function() {
	 
	 $.getScript('./js/plug-in/jquery.tablesorter.min.js', function() {
		$("#list").tablesorter({
		       headers: { 
		      	 0: { // 첫번째 th는 전체선택 버튼이기때문에 정렬이때문에 disable
		           sorter: false
		      	}}
			}); // 테이블 정렬				  
	});			 
	  
	$("#allBtn").click(function(){	// 전체 선택 및 해제
		var all = "./images/sms/btn_tall.gif";
		var clear = "./images/sms/btn_choice_off.gif";
		var src = $(this).children("img").attr("src");
		if( src == clear ){
			$(this).children("img").attr("src", all);
			$.js.selectAll(false);
		}else{
			$(this).children("img").attr("src", clear);
			$.js.selectAll(true);
		}
	});

	$("table tbody td").mouseover(function(){
		$(this).siblings().andSelf().css("color", $.js.spectrum());
	}).mouseout(function(){
		$(this).siblings().andSelf().css("color", "#333");
	});
});
</script>
</head>
<body>
<div>
<table id="list" class="ads_tbl" cellspacing="0" cellpadding="0" >
	<colgroup>
		<col width="10%" />
		<col width="40%" />
		<col width="50%" />
		<!-- 
		<col width="20%" />
		<col width="30%" />
		
		-->
	</colgroup>
	<thead>
		<tr>
			<th><a id="allBtn" href="#" onclick="return false;">
				<img src="./images/sms/btn_tall.gif" alt="전체 선택" /></a></th>
			<th><a href="#">이름</a><img id="sortBtn" src="./images/sms/btn_opope.gif" alt="sort" /></th>
			<th><a href="#">휴대번호</a><img src="./images/sms/btn_opope.gif" alt="sort" /></th>
			<!-- 
			<th><a href="#">경비전화</a><img src="./images/sms/btn_opope.gif" alt="sort" /></th>
			<th style="border-right: none; border-bottom: 1px solid #ccc;"><a href="#">직책 </a><img id="sortBtn" src="./images/sms/btn_opope.gif" alt="sort" /></th>
			-->			
		</tr>
	</thead>
	<tbody>
	
	<c:if test="${empty list}">
		<tr>
			<td style="padding: 20px;font-weight: bold;" colspan="5">검색된  주소록이 없습니다.</td>
		</tr>
	</c:if>
						
	<c:forEach var="data"  items="${list}" >
		<tr>
			<td>					
	   		   <input type="checkbox" value="" />
	       </td>
			<td>${data.people}</td>
			<td>${data.phone}</td>
			<td></td>
			<td></td>				       		
	     </tr> 
	</c:forEach>
	</tbody>
</table>
</div>
</body>
</html>
