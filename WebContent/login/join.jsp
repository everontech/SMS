<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<jsp:include page="../modules/header.jsp" />

<style>
.check_yes img {
	display: none;
}

input {
	margin-left: 10px;
}
</style>
<script type="text/javascript">
<!--
$(document).ready(function() {
	// 모든 input에 툴팁 효과 주기 
	$("input").tooltip();
	
 	 // 아이디 입력포커스를 나갈경우 아이디 중복 체크
 	var duple_check_id = false; 
    $("#id").focusout(function(){
    	if($("#id").val().length <= 0)
    		return;
    	
        $.ajax({
          url: '../IdCheckAction.ac',
          data: { id : $(this).val() },
          success: function(result){
              if($.trim(result) == 'true'){
                  alert("사용가능한 아이디 입니다.");
                  duple_check_id = true;
              }else{
            	  alert("이미 사용중인 아이디 입니다.");
                  duple_check_id = false;
              }
          },
        });  
    });	 
 	 

	// 전송전 로그인필드 검증 처리
	$("#joinBtn").click(function() {
		
		// 이름 입력 검증
		if ($("#name").val().length <= 0) {
			alert("이름을 정확히 입력하세요");
			$("#name").trigger("focus");
			var $imgs = $("#name").parent('td').siblings().eq(1);
			$imgs.children(":last").show();
			$imgs.children(":first").hide();		
			return;
		}else{
			var $imgs = $("#name").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();		
		}	
		
		// 아이디 정규식
		var regIdExp = /^[a-z0-9_]{4,20}$/;		
		//입력을안했다면
		if ($("#id").val().length <= 0) {
			alert("아이디를 입력하세요");
			$("#id").trigger("focus");
			var $imgs = $("#id").parent('td').siblings().eq(1);
			$imgs.children(":last").show();
			$imgs.children(":first").hide();				
			return;
		}

		//  아이디 입력 형식 체크
		if (regIdExp.test($("#id").val()) === false) {
			alert("정확한 아이디를 입력하세요");
			$("#id").trigger("focus");
			var $imgs = $("#id").parent('td').siblings().eq(1);
			$imgs.children(":last").show();
			$imgs.children(":first").hide();				
			return;
		}else{
			var $imgs = $("#id").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
		}
		
		
		// 경찰서명 입력검증
		if ($("#psname").val().length <= 0) {
			alert("경찰서명을 입력하세요");
			$("#psname").trigger("focus");
			var $imgs = $("#psname").parent('td').siblings().eq(1);
			$imgs.children(":last").show();
			$imgs.children(":first").hide();	
			return;
		}else{
			var $imgs = $("#psname").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();	
		}
		
		// 부서 입력 검증
		if ($("#deptName").val().length <= 0) {
			alert("부서명을 정확히 입력하세요");
			$("#deptName").trigger("focus");
			var $imgs = $("#deptName").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
			return;
		}else{
			var $imgs = $("#deptName").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
		}	
		
		// 계급 입력 검증
		if ($("#grade").val().length <= 0) {
			alert("계급명을 정확히 입력하세요");
			$("#grade").trigger("focus");
			var $imgs = $("#grade").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
			return;
		}else{
			var $imgs = $("#grade").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
		}			
		
		// 계급 입력 검증
		var regEmailExp = /^([0-9a-zA-Z_-]+)@([0-9a-zA-Z_-]+)(\.[0-9a-zA-Z_-]+){1,2}$/;
		if ($("#email").val().length <= 0 || regEmailExp.test($("#email").val()) === false) {
			alert("이메일을 정확히 입력하세요");
			$("#email").trigger("focus");
			var $imgs = $("#email").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
			return;
		}else{
			var $imgs = $("#email").parent('td').siblings().eq(1);
			$imgs.children(":first").show();
			$imgs.children(":last").hide();				
		}			
		
		//  비밀번호 검증
		if ($("#pwd").val().length <= 0) {
			alert("비밀번호를 입력하세요");
			$("#pwd").trigger("focus");
			return;
		} else if (checkPassword($("#pwd").val()) === false) {
			alert("비밀번호를 정확히 입력하세요");
			$("#pwd").trigger("focus");
			return;
		}

		// 비밀번호 확인처리
		if( $("#rePwd").val() != $("#rePwd").val()) {
			alert("비밀번호가 동일하지 않습니다.");
			$("#rePwd").trigger("focus");
			return;
		}
		
		if(duple_check_id == false){
			alert('중복된 아이디 입니다.');
			return;
		}
		
		$("form").submit();
	});
	
	//$.myUtil.debug($("#id").val(), 'awef', 'awefawef', 'awefawef');

	// 비밀번호창에 엔터키를 누르면 로그인버튼 트리거
	/*
	$("#user_pwd").keydown(function(event){
	   if(event.keyCode == ENTER_KEY){
	       $("#login_btn").click();
	   }
	});
	 */

	// 아이디 필드에 포커스
	//$("#id").focus();
});
//-->
</script>
<body>
	<div id="join_wrapper">
		<!-- join -->
		<h1 class="logo">
			<img src="../images/top/logo.gif" alt="강원청SMS" border="0" /></a>
		</h1>
		<div class="join_box">
			<h3>
				<img src="../images/join/txt_join.gif" alt="회원가입" />
			</h3>
			<form action="../JoinAction.ac" method="post">
				<fieldset>
					<legend>회원가입 정보입력</legend>
					<table width="547" border="0" cellspacing="0" cellpadding="0">
						<tr>
							<th width="146">이름</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="name" name="name"
								title="정확한 이름을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">아이디</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="id" name="id"
								title="영문숫자로만 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">경찰서</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="psname" name="psname" title="정확한 경찰서명을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">부서</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="deptName" name="deptName" title="정확한 부서명을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">휴대번호</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td>
								<input type="text"  class="phone" id="phone1" name="phone1" /> - <input type="text"
								class="phone" id="phone2" name="phone2"  /> - <input class="phone" type="text" id="phone3" name="phone3"  />
							</td>
						</tr>
						<tr>
							<th width="146">계급</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="grade" name="grade" title="계급을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">이메일</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="text" id="email" name="email" title="이메일을 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">비밀번호</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="password" id="pwd" name="pwd"
								title="최소 6글자 이상 입력하세요" /></td>
						</tr>
						<tr>
							<th width="146">비밀번호 확인</th>
							<td class="check_yes"><img
								src="../images/join/check_yes.gif" /> <img
								src="../images/join/check_no.gif" /></td>
							<td><input type="password" id="rePwd" name="rePwd"
								title="동일한 비밀번호를 입력하세요" /></td>
						</tr>
					</table>
				</fieldset>
			</form>
			<p class="join_info">
				<a href="#" id="joinBtn"><img src="../images/join/btn_join.gif"
					alt="회원가입" /></a><a href=""><img
					src="../images/join/btn_cancel.gif" alt="취소" /></a>
			</p>
		</div>
	</div>
	<div id="footer_wrap">
		<div class="footer">푸터영역</div>
	</div>

</body>


</html>
