<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%-- 헤더  --%>
<jsp:include page="../include/header.jsp" />
<script type="text/javascript">
<!--
$(function(){
	//	비밀번호에서 엔터키를 입력하면 로그인 버튼 트리거처리
    $("form #pwd").keydown(function(event){
       if(event.keyCode == 13){
    	 //  alert("로그인");
    	   $("body").trigger("focusout");
            $("#loginBtn").trigger("click");
       }
     });	
	
	//  입력 검증 처리	
	$("form").submit(function(){
		
		if ($("form #id").val().length <= 0) {
			alert("아이디를 입력하세요");
			$("form #id").trigger("focus");
			return false;
		}
		
		if ($("form #pwd").val().length <= 0) {
			alert("비밀번호를 입력하세요");
			$("form #pwd").trigger("focus");
			return false;
		}
		
		return true;
	});
});
//-->
</script>

<body>
	<div id="login_wrapper">
		<!-- login -->
		<h1 class="logo">
			<img src="../images/top/logo.gif" alt="충북청SMS" border="0" /></a>
		</h1>
		<div class="login_box">
			<h3>
				<img src="../images/login/txt_login.gif" />
			</h3>
			<form action="../LoginAction.ac" method="post">
				<fieldset>
					<legend>로그인정보입력</legend>
					<p class="id">
						<label for="id"><img src="../images/login/txt_id.gif"
							alt="아이디" /></label> <input type="text" id="id" name="id" value="ddononi" />
					</p>
					<p class="pw">
						<label for="pwd"><img src="../images/login/txt_ow.gif"
							alt="비밀번호" /></label> <input type="password" id="pwd" name="pwd" value="zhtmahtm01" />
					</p>
					<p class="btnLogin">
						<input type="image" src="../images/login/btn_login.gif" alt="로그인" />
					</p>
					<p class="id_send">
						<input type="checkbox" />아이디 저장
					</p>
				</fieldset>
			</form>
			<p class="memberinfo">
				<a href=""><img src="../images/login/btn_find.gif" alt="아이디비번찾기" /></a><a
					href="./join.jsp" id="loginBtn"><img src="../images/login/btn_join.gif"
					alt="회원가입" /></a>
			</p>
		</div>
	</div>
	<jsp:include page="../include/footer.jspf" />	
</body>
</html>
