<%@page import="kr.go.police.address.AddressBean"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.address.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	//	리스트 번호
	int no = (Integer)request.getAttribute("no");		
%>	
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<body>
	<div id="dialog-modify-form" title="주소록 변경">
		<form method="post" id="modifyFrm" action="./AddressModifyAction.ad" style="margin-top: 20px">
		    <fieldset>
				<input type="hidden" id="groupIndex" value="${groupIndex}"  name="groupIndex"  />		    
				<input type="hidden" id="index"  value=""  name="index"  />
				<label>이름 : </label>	
		        <input type="text" size="30"  class="search" name="peopleName"  id="peopleName" title="이름을 입력하세요" class="text ui-widget-content ui-corner-all" /><br/>
				<label>전화번호 : </label>		        						    
		        <input type="text" size="30"  class="search" name="phoneNum" id="phoneNum" title="전화번호를 입력하세요"  class="text ui-widget-content ui-corner-all" />
		    </fieldset>
	    </form>
	</div>
	<div id="dialog-add-form" title="주소록 추가">
		<form method="post" id="addFrm" action="./AddressAddAction.ad" style="margin-top: 20px">
		    <fieldset>
				<input type="hidden" value="${groupIndex}"  name="groupIndex"  />			    
				<label>이름 : </label>	
		        <input type="text" size="30"  class="search" name="peopleName"  id="peopleName" title="이름을 입력하세요" class="text ui-widget-content ui-corner-all" /><br/>
				<label>전화번호 : </label>		        						    
		        <input type="text" size="30"  class="search" name="phoneNum" id="phoneNum" title="전화번호를 입력하세요"  class="text ui-widget-content ui-corner-all" />
		    </fieldset>
	    </form>
	</div>	
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />
		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />
     	   <div class="boderWrap">
				<h3>
					<img src="images/lettersend/title_address.gif" alt="내 주소록 " />
				</h3>
				<!--  주소록 삭제 -->
				<form method="post"  id="delForm" action="./AddressDelAction.ad">
					<input value="" id="index" name="index" type="hidden" />
					<input type="hidden" value="${groupIndex}"  name="groupIndex"  />							
				</form>	
				<%--	검색 처리 --%>
				<form style="clear: both; width: 100%; padding:3px; vertical-align: middle;" id="search_frm" action="./AddressListAction.ad" method="get"  >
					<input value="" name="page" type="hidden" />
					<input type="hidden" value="${groupIndex}"  name="groupIndex"  />
					<select id="limit" name="limit" style="float: left; display: inline-block;">
						<option ${limit == "10"?"selected":""} value="10">10개</option>
						<option ${limit == "20"?"selected":""} value="20">20개</option>
						<option ${limit == "30"?"selected":""} value="30">30개</option>
						<option ${limit == "40"?"selected":""} value="40">40개</option>
						<option ${limit == "50"?"selected":""} value="50">50개</option>
					</select>	
					<div style="float: right; display: inline-block;">
						<input title="검색할 이름 혹은 전화번호를 입력하세요" style="margin-bottom: 3px;" value="${search}"  class="search" type="text" name="search" id="search" size="20" />
						<a  href="#"  onclick="return false;" id="search_btn"><img style="margin-bottom:5px;margin-right:5px; right;vertical-align: middle;"  src="./images/base/category_btn.gif" /></a>
					</div>
				</form>							
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<colgroup>
						<col width="15%" />
						<col width="30%" />
						<col width="40%" />						
						<col width="15%" />						
					</colgroup>
					<thead>
						<tr height="34px;">
							<th>No.</th>
							<th>이름</th>				
							<th>전화번호</th>	
							<th>삭제</th>																	
						</tr>
					</thead>
					<tbody>
					<!--  주소록이 없는경우 -->
					<c:if test="${empty list}">
						<tr>
							<td colspan="4"> 주소록이 없습니다.</td>
						</tr> 
					</c:if>
					<!--  주소록 리스트 -->
					<c:forEach var="data"  items="${list}" >
						<tr>
							<td>					
					   		   <%=no--%>
					       </td>
							<td>					
					   		   <a class="group_td"  data-index="${data.index}"  href="#" onclick="javascript:return false;" >${data.people}</a>
					       </td>
							<td>					
					   		   <a class="group_td phone hyphen"  data-index="${data.index}"  href="#" onclick="javascript:return false;" >${data.phone}</a>
					       </td>						       	
							<td>					
					   		   <a class="group_del" data-index="${data.index}"  href="#" onclick="javascript:return false;" ><img src="./images/sms/bt_categoryAll_close.gif" alt="삭제" /></a>
					       </td>					       		
					     </tr> 
					</c:forEach>
					</tbody>
				</table>
				<%--	삭제 폼 --%>
				<div id="buttons" style="float: right;  margin-top: 5px;">
						<a  href="#"  id="add_btn">추가</a>
						<a  href="./AddressGroupListAction.ad"  >그룹목록</a>
				</div>		
				<div style="clear: both;"></div>							
				<c:if test="${(empty list) == false}">
					${pagiNation}
				</c:if>				
			</div>
		</div>
	</div>
<jsp:include page="../modules/footer.jspf" />
</body>
<script type="text/javascript">
<!--
$(function(){
    // odd td colume stand out
    $("table:last tbody tr").each(function(i){
       if(i % 2 == 0){
       	 $(this).children('td').css('background', '#efe');
       } 
    });
 
    // 테이블 현재 열 강조 효과
    $("table:last td").hover(
      function () {
        $(this).siblings().andSelf().addClass("hover");
      },
      function () {
        $(this).siblings().andSelf().removeClass("hover");
      }
    );   
    //	전화번호 하이픈 처리
    //$(".phone").addHyphen();
    // 버튼 ui
    $("#buttons a").button();
	//	입력창 에서 엔터 버튼 입력시 폼전송
    $("#search").tooltip().keydown(function(event){
	       if(event.keyCode == 13){
	    	   $("#search_frm").submit();
	       }
    });	 
	
	// 검색 버튼    
    $("#search_btn").click(function(){
    	$("#search_frm").submit();
    });  
	
    // 페이지 목록수
    $("#limit").change(function(){
    	$("#search_frm").submit();
    });  
    
    // 수정 다이얼로그 설정
	$( "#dialog-modify-form" ).dialog({
            autoOpen: false,
            modal: true,
            width : 250,
            buttons: {
                "수정": function() {
                	var groupVal = $("#modifyFrm #peopleName").val();
                	if(groupVal.length <= 0){
                		alert("이름을 입력하세요");
                		return;
                	} 
                	
                	var groupVal = $("#modifyFrm #phoneNum").val();
                	if(groupVal.length <= 0){
                		alert("전화번호를 입력하세요");
                		return;
                	}                 	
                	$("#modifyFrm").submit();           	
                },
                "취소": function() {
                    $( this ).dialog( "close" );
                }
            }
    }); 
    
    //  추가 다이얼로그 설정
	$( "#dialog-add-form" ).dialog({
            autoOpen: false,
            modal: true,
            width : 250,
            buttons: {
            	// 주소록 추가 처리
                "추가": function() {
                	var groupVal = $("#addFrm #peopleName").val();
                	if(groupVal.length <= 0){
                		alert("이름을 입력하세요");
                		return;
                	} 
                	
                	var phoneVal = $("#addFrm #phoneNum").val();
                	if(phoneVal.length <= 0){
                		alert("전화번호를 입력하세요");
                		return;
                	}                  	
                	
                	// 휴대폰번호 입력시 올바른 휴대폰 번호인지 체크   
                	var rgEx = /[01](0|1|6|7|8|9)(\d{4}|\d{3})\d{4}$/g;  
                	var chkFlg = rgEx.test(phoneVal);    
                	if(!chkFlg){ 
                	    alert("올바른 휴대폰번호가 아닙니다.");
                	    $("#addFrm #phoneNum").focus();  
                	    return; 
                	 }
                	 
                	$("#addFrm").submit();                        	
                },
                "취소": function() {
                    $( this ).dialog( "close" );
                }
            }
    });     
    
    // 	주소록 추가 
    $("#add_btn").click(function(){
    	$( "#dialog-add-form" ).dialog( "open" );
    });
    
    //  그룹명 변경 처리
    $(".group_td").click(function(){
    	$( "#dialog-modify-form" ).dialog( "open" );
    	// 이름 가져오기
    	var peopleName = $.trim($(this).parent('td').siblings().first().next().text());
    	$("#modifyFrm #peopleName").val(peopleName);
    	// 전화번호 가져오기
    	var phoneNum = $.trim($(this).parent('td').siblings().first().next().next().text());
    	$("#modifyFrm #phoneNum").val(phoneNum);    	
    	// 해당 인덱스
		var index = $(this).attr("data-index");    	
    	$("#modifyFrm #index").val(index);    	
    });
    
    // 그룹 삭제 처리
	$(".group_del").click(function(){
		// 그룹명 얻기
		var group = $(this).parent("td").siblings().first().next().children().text();
		if(confirm("(" + group + ") 주소록을 삭제 하시겠습니까?")){
			var index = $(this).attr("data-index");
			// 삭제 폼 전송
			$("#delForm > input:first").val(index);
			$("#delForm").submit();
		}
	});  
	// 전화번호 하이픈 넣기
	$(".hyphen").addHyphen();
    
	// 메뉴 처리
	$("#top_menu3").attr("data-on", "on");
	$("#top_menu3 > img").attr("src", "./images/top/menu03_on.gif");
	$("#address_book_menu > img").attr("src", "./images/top/menu_sub05_on.gif");
	$("#address_book_menu").attr("data-on", "on");
	$("#top_menu3").trigger("mouseover");
	$(".gnb_sub3").show();
});	
//-->
</script>
</html>