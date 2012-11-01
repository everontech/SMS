<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.account.*" %>	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	// requset 로 부터 유저 데이터를 가져온다.
	UserBean user = (UserBean)request.getAttribute("userData");
%>	
<c:set var="user"  value ="<%=user %>" />
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<style>
.check_yes img {
	display: none;
}

input {
	margin-left: 10px;
}
</style>
<script>
$(function() {
	// 승인 미승인 ui 설정후 승인이나 미승인을 누르면 확인 다이얼로그를 띄워준다.
    $( "#radio" ).buttonset().children("[type=radio]").click(function(event){
    	//  승인 버튼을 눌렀을경우
    	if( $(this).attr("id") == "approve"){
    		$( "#approveConfirm" ).dialog( "open" );
    	}else{
    		$( "#refuseConfirm" ).dialog( "open" );    		
    	}
	
    });
    
	
	// 승인 처리 확인 다이얼로그
    $( "#approveConfirm" ).dialog({
        resizable: false,
        height:160,
        modal: true,
        autoOpen : false,
        buttons: {
            "확인": function() {
            	$("#refuse").removeAttr('checked');
            		//.andSelf().attr('checked', 'checked');
            	// 라디오 버튼 갱신
            	$( "#radio" ).buttonset("refresh");                   	
                $( this ).dialog( "close" );
            },
            "취소": function() {
            	$("#approve").siblings().removeAttr('checked');
            	// 라디오 버튼 갱신
            	$( "#radio" ).buttonset("refresh");                	
                $( this ).dialog( "close" );
            }
        }
    });
	
    $( "#refuseConfirm" ).dialog({
        resizable: false,
        height:160,
        modal: true,
        autoOpen : false,
        buttons: {
            "확인": function() {
            	$("#approve").removeAttr('checked');
            	// 라디오 버튼 갱신
            	$( "#radio" ).buttonset("refresh");                   	
                $( this ).dialog( "close" );
            },
            "취소": function() {
            	$("#refuse").siblings().removeAttr('checked');
            	// 라디오 버튼 갱신
            	$( "#radio" ).buttonset("refresh");             	
                $( this ).dialog( "close" );
            }
        }
    });	
    
});
</script>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/topmenu.jsp" />

		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/sidebox.jsp" />

			<div id="contentsWrap">
				<h3>
					<img src="./images/lettersend/title.gif" alt="문자보내기" />
				</h3>
			<form action="./JoinAction.ac" method="post">
				<fieldset>
					<legend>회원 정보</legend>
					<table width="547" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="146">이름</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="name" name="name"  value="${user.name}"
								title="정확한 이름을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">아이디</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="id" name="id"  value="${user.id}" disabled="disabled"
								title="영문숫자로만 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">경찰서</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" value="${user.psName}" id="psname" name="psname" title="정확한 경찰서명을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">부서</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="deptName" value="${user.deptName}" name="deptName" title="정확한 부서명을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">휴대번호</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td>
								<input type="text"  value="${user.phone1}"  id="phone1" name="phone1" /> - <input type="text"
								id="phone2" name="phone2" value="${user.phone1}"   /> - <input value="${user.phone1}"  type="text" id="phone3" name="phone3"  />
							</td>
						</tr>
						<tr>
							<th width="146">계급</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="grade" name="grade" title="계급을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">이메일</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="email" name="email" value="${user.email}" title="이메일을 입력하세요" /></td>
						</tr>
						
						<tr>
							<th width="146">승인여부</th>
							<td class="check_yes"><img
								src="./images/join/check_yes.gif" /> <img
								src="./images/join/check_no.gif" /></td>
							<td><input type="text" id="email" name="email" value="${user.email}" title="이메일을 입력하세요" /></td>
						</tr>						
	
						<tr>
							<th width="300">승인여부</th>	
								<td id="radio" colspan="2">							
					        		 <input ${user.approve ? 'checked="checked"':'' }  type="radio" id="approve" name="approve" /><label for="approve">승인</label>
					       			 <input ${user.approve ? '' : 'checked="checked"' } type="radio" id="refuse" name="refuse"   /><label for="refuse"">미승인</label>
					 			</td>						
						</tr>	
										
					</table>
				</fieldset>
			</form>
			<p class="join_info">
				<a href="#" id="joinBtn"><img src="./images/join/btn_join.gif"
					alt="회원가입" /></a><a href=""><img
					src="./images/join/btn_cancel.gif" alt="취소" /></a>
			</p>
		</div>
					</table>
				</div>
			</div>
		</div>
		<div id="footer">푸터영역</div>
	</div>
	
<!--  다이얼 로그 -->	
<div id="approveConfirm" title="승인처리 확인">
    <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span >사용자를 승인처리 하시겠습니까?</p>
</div>
	
<div id="refuseConfirm" title="미승인처리 확인">
    <p><span class="ui-icon ui-icon-alert" style="float: left; margin: 0 7px 20px 0;"></span >사용자를 미승인처리 하시겠습니까?</p>
</div>	
	
</body>


</html>