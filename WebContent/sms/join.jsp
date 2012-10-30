<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />

<body>

<script type="text/javascript">
$(function(){
	  $("input").tooltip();
});
</script>
<form action="../JoinAction.ac" method="post" >
	<label>아이디</label><input value="" name="id" id="id" type="text" title="아이디를 입력하세요.." /><br/>
	<label>비번</label><input value="" name="pwd" id="pwd" type="password" ><br/>
	<label>계급</label><input value="" name="grade" id="grade" type="text" ><br/>
	<label>이름</label><input value="" name="name" id="name" type="text" /><br/>
	<label>부서</label><input value="" name="deptname" id="deptname" type="text" ><br/>
	<label>경찰서</label><input value="" name="police_name" id="police_name" type="text" /><br/>
	<label>휴대번호</label><input value="" name="phone" id="phone" type="text" ><br/>
	<label>이메일</label><input value="" name="email" id="email" type="text" ><br/>	
	<button type="submit">전송</button>
</body>
</html>