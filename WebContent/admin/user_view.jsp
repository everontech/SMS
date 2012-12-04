<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<%@ page import="kr.go.police.account.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<style>
.check_yes img {
	display: none;
}
</style>
<script>
	$(function() {
		// 승인 미승인 ui 설정후 승인이나 미승인을 누르면 확인 다이얼로그를 띄워준다.
		$("#radio").children("[type=radio]").click(function(event) {
			//  승인 버튼을 눌렀을경우
			if ($(this).attr("id") == "approve") {
				$("#approveConfirm").dialog("open");
			} else {
				$("#refuseConfirm").dialog("open");
			}

		});

		// 승인 처리 확인 다이얼로그
		$("#approveConfirm").dialog({
			resizable : false,
			height : 160,
			modal : true,
			autoOpen : false,
			buttons : {
				"확인" : function() {
					$("#refuse").removeAttr('checked');
					$(this).dialog("close");
				},
				"취소" : function() {
					$("#approve").siblings().removeAttr('checked');
					$(this).dialog("close");
				}
			}
		});

		$("#refuseConfirm").dialog({
			resizable : false,
			height : 160,
			modal : true,
			autoOpen : false,
			buttons : {
				"확인" : function() {
					$("#approve").removeAttr('checked');
					$(this).dialog("close");
				},
				"취소" : function() {
					$("#refuse").siblings().removeAttr('checked');
					$(this).dialog("close");
				}
			}
		});

		// 수정 확인 버튼
		$("#modifyBtn").click(function() {
			if (confirm("사용자정보를 수정 하시겠습니까?")) {
				$("form").submit();
			}
		});

	});
</script>
<body>
	<div id="wrapper">
		<%-- 상단메뉴  --%>
		<jsp:include page="../modules/admin_topmenu.jsp" />

		<div id="contents">
			<%-- 사이드 메뉴  --%>
			<jsp:include page="../modules/admin_sidebox.jsp" />
			<div class="boderWrap">
				<h3>
					<img src="./images/boder/tit_member.gif" alt="회원정보변경" />
				</h3>
				<form action="./AdminModifyUserAction.ac" method="post">
					<input value="${index}" id="index"
						name="index" type="hidden" />
					<table width="100%" border="0" cellpadding="0" cellspacing="0">
						<tbody>
							<tr style="border-top: #258abf 2px solid;">
								<td style="background: #f4f4f4;"><strong>이름</strong></td>
								<td class="tite"><input type="text" id="name" name="name" value="${userData.name}"
									title="정확한 이름을 입력하세요" class="none" />
								</td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>아이디</strong></td>
								<td class="tite"><input type="text" id="id" name="id"
									value="${userData.id}" disabled="disabled" class="none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>경찰서</strong></td>
								<td class="tite"><input type="text" value="${userData.psName}"
									id="psname" name="psname" title="정확한 경찰서명을 입력하세요" class="none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>부서</strong></td>
								<td class="tite"><input type="text" id="deptName"
									value="${userData.deptName}" name="deptName" title="정확한 부서명을 입력하세요"
									class="none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>휴대번호</strong></td>
								<td class="tite"><input value="${userData.phoneTop1}" id="phone1"
									name="phone1" type="text"
									style="width: 60px; margin-left: 10px;" class="phone none" /> - <input
									value="${userData.phoneMiddle1}" id="phone2" name="phone2" type="text"
									style="width: 60px;" class="phone none" /> - <input
									value="${userData.phoneBottom1}" id="phone3" name="phone3" type="text"
									style="width: 60px;" class="phone none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>계급</strong></td>
								<td class="tite"><input id="grade" name="grade" value="${userData.grade}"
									title="계급을 입력하세요" type="text" class="none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>이메일</strong></td>
								<td class="tite"><input id="email" name="email"
									value="${userData.email}" title="이메일을 입력하세요" type="text"
									class="none" /></td>
							</tr>
							<tr>
								<td style="background: #f4f4f4;"><strong>등급</strong></td>
								<td class="tite">
									<select style="height: 20px; margin-left: 10px; width: 133px;" id="userClass" name="userClass">
										<option ${userData.userClass == 1  ? ' selected="selected" ' : '' }
										value="1">일반사용자</option>
										<option ${userData.userClass == 2  ? ' selected="selected" ' : '' }
										value="2">과서무사용자</option>
										<option ${userData.userClass == 3  ? ' selected="selected" ' : '' }
										value="3">청사용자</option>
										<option ${userData.userClass == 0  ? ' selected="selected" ' : '' }
										value="0">관리자</option>
									</select>
								</td>
							</tr>
							<tr class="end">
								<td style="background: #f4f4f4;"><strong>승인여부</strong></td>
								<td class="tite"  id="radio" ><input style="margin-left: 10px"
									${userData.approve ? 'checked="checked"':'' } value="y"
									type="radio" id="approve" name="approve" /><label
									for="approve">승인</label> <input class="none" 
									${userData.approve ? '' : 'checked="checked"' } value="n"
									type="radio" id="refuse" name="approve" /><label
									for="refuse"">미승인</label></td>
								</td>
							</tr>
						</tbody>
					</table>
				</form>
				<div class="btn">
					<a href="#" id="modifyBtn"><img
						src="./images/notice/register_btn.gif" alt="등록" /></a>
						<a href="javascript:history.go(-1);"><img src="./images/notice/cancel_btn.gif" alt="취소" /></a>
				</div>
			</div>
		</div>
	</div>
	<!--  다이얼 로그 -->
	<div id="approveConfirm" title="승인처리 확인">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>사용자를 승인처리 하시겠습니까?
		</p>
	</div>
	<div id="refuseConfirm" title="미승인처리 확인">
		<p>
			<span class="ui-icon ui-icon-alert"
				style="float: left; margin: 0 7px 20px 0;"></span>사용자를 미승인처리 하시겠습니까?
		</p>
	</div>
	<jsp:include page="../modules/footer.jspf" />	
</body>
</html>