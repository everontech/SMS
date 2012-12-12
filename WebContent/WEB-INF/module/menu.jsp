<%@ page contentType="text/html; charset=euc-kr" pageEncoding="EUC-KR"%>
<%@ page import="java.util.*"%>
<link type="text/css" href="./css/sms.css" rel="stylesheet" />
<script type="text/javascript" src="./js/plug-in/jquery.simplemodal.js"></script>
<!-- 다이얼로그  플러그인-->
<style type="text/css">
#subnav1,#subnav2,#subnav3,#subnav4,#subnav5,#subnav6,#subnav7 {
	display: none;
}

#subnav2 {
	margin-left: 80px;
}

#subnav3 {
	margin-left: 155px;
}

#subnav4 {
	margin-left: 315px;
}

#subnav5 {
	margin-left: 390px;
}

#subnav6 {
	margin-left: 370px;
}

#deptname {
	margin-left: 5px;
	font-size: 90%;
	color: #FF8040;
}

#f_id,#freeSendCount span {
	color: #4481d6;
	font-weight: bold;
}

#freeSendCount {
	font-size: 75%;
}

#freeSendCount span {
	font-size: 16px;
	margin-left: 5px;
	margin-right: 5px;
}

.sms_list a {
	color: #4481d6;
}

.sms_list a {
	border-bottom: none;
}

#noticeBox {
	display: none;
	height: 300px;
	width: 575px;
	padding: 10px;
	background: #fff;
	border: 3px solid #B0CDEA;
}

#quickLogout {
	position: relative;
	right: 0;
}

#f_id {
	width: 110px;
}

.dear {
	color: #666;
}

.gonji_date {
	margin-left: 8px;
	font-size: 9px;
	color: #666;
}

.free {
	border: none;
}
</style>
<!-- 
	상단 메뉴
 -->
<div id="header" style="width:1024px;">
	<h1>
		<a href="../sms_send/hp.jsp">
			<!--<img src="../images/SMS.jpg" alt="logo" />-->
			<img src="./images/main-title.gif" alt="충북청SMS" />
		</a>
	</h1>
	<!-- <h2><img src="../images/login_top.gif" alt="SMS" /></h2> -->
	<ul id="nav">
		<li><a href="../sms_send/hp.jsp" class="parent" id="smstransMenu">SMS
				전송</a></li>
		<li><a href="../sms_send/message.jsp" class="parent">메시지관리</a></li>
		<li><a href="../Address/Address_mgr.jsp">주소록</a></li>

		<li><a href="../enrollUser/modifyUser.jsp">사용자정보</a></li>
		<li><a href="../QnA/index_qna.jsp">문의하기</a></li>
		<c:set var="f_class" value="" />
		<c:if test="${f_class=='3' || f_class=='2'}">
			<li id="adminMenu"><a href="../manageUser/ListUser.jsp">관리자모드</a></li>
		</c:if>
		<li><a href="../logout.jsp"><span>로그아웃</span></a></li>
	</ul>
	<ul id="subnav">
		<span id="subnav1">
			<li><a href="../sms_send/hp.jsp"><span>문자보내기</span></a></li>
			<li><a href="../sms_send/send_info.jsp"><span>예약리스트</span></a></li>
			<li><a href="../sms_send/result_info.jsp"><span>전송결과</span></a></li>
		</span>
		<span id="subnav2">
			<li><a href="../sms_send/message.jsp"><span>나의메시지</span></a></li>
			<li><a href="../sms_send/message1.jsp"><span>공통메시지</span></a></li>
		</span>
		<span id="subnav3">

			<li><a href="../Address/Address_mgr.jsp"><span>그룹목록</span></a></li> <!-- <li><a href="../Address/Addr_input.jsp"><span>그룹및개별주소록등록</span></a></li> -->
			<li><a href="../Address/Addr_excel_input.jsp"><span>개별(엑셀)등록</span></a></li>
			<li><a href="../Address/search_user.jsp"><span>주소록검색</span></a></li>

		</span>
		<span id="subnav4">
			<li><a href="../enrollUser/modifyUser.jsp"><span>정보수정</span></a></li>
		</span>
		<span id="subnav5">
			<li><a href="../QnA/index_qna.jsp"><span>문의목록</span></a></li>
			<li><a href="../QnA/index_qna_write.jsp"><span>문의하기</span></a></li>
		</span>
		<span id="subnav6">

			<li><a href="../Statistic/Send_statistic.jsp"><span>통
						계</span></a></li>
			<li><a href="../controlDept/controlDept.jsp"><span>부서관리</span></a></li>
			<c:if test="${f_class=='3'}">
				<!--   지방청 관리자만 -->
				<li><a href="../manageUser/restrict.jsp"><span>그룹별제한설정</span></a></li>
			</c:if>
			<li><a href="../manageUser/spamLetter.jsp"><span>금칙어설정</span></a></li>
			<!--  <li><a href="../manageUser/enrollUser.jsp"><span>회원등록</span></a></li> -->
			<c:if test="${f_class=='3'}">
				<!--   지방청 관리자만 -->
				<li><a href="../manageUser/smsLog.jsp"><span>SMS로그</span></a></li>
			</c:if>
		</span>
		<span id="subnav7">
			<li><a href="../logout.jsp"><span>로그아웃</span></a></li>
		</span>
	</ul>
</div>
<div id="sideMenu" style="width:300px; margin-left: 50px;">
	<div id="sidebar">
		<h2>로그인 정보</h2>
		<p id="idInfo">
		<div>
			<span id="f_id"><span class="dear"> 님</span></span> <span
				id="quickLogout"><a href="../logout.jsp"><img
					src="./images/logout.gif" alt="로그아웃" /></a></span>
		</div>
		</p>
		<p>행복한 하루 되세요!</p>
		<p>
			<span id="deptname">()</span>
		</p>
		<p id="freeSendCount">
			이달 남은 건수 :<span></span>건
		</p>
		<p id="currentTime"></p>
	</div>
	<div class="leftmenu">
		<dl class="sms_list">
			<dt>
				<div class="send">
					<a href="../sms_send/hp.jsp"><strong>문자보내기</strong></a>
					<!--(<em>6</em>)-->
				</div>
			</dt>
			<dt>
				<div class="save">
					<a href="../sms_send/send_info.jsp"><strong>예약리스트</strong></a>
				</div>
			</dt>
			<dt>
				<div class="save">
					<a href="../sms_send/result_info.jsp"><strong>전송결과</strong></a>
					<!-- (<em>6</em>) -->
				</div>
			</dt>
			<dt>
				<div>
					<a href="../sms_send/message.jsp"><strong>나의메시지</strong></a>
				</div>
			</dt>
			<dt>
				<div>
					<a href="../Address/Address_mgr.jsp"><strong>주소록</strong></a>
				</div>
			</dt>
		</dl>
		<div class="bgbottom"></div>
	</div>
	<div class="motli">
		<div class="mtit">
			<h2>
				공지사항<img src="./images/ico_marr2.gif" />
			</h2>
		</div>
		<ul>

		</ul>
	</div>
</div>
<!--  /사이드 메뉴 -->

<!-- chart  -->
<div id="noticeBox"></div>
<!-- /cahrt -->
<script type="text/javascript">
	var selectedSub = null; // 이전 선택된 메뉴
	var sub = []; // 메뉴 객체 배열
	var adminFlag = false;
	// 관리자모드 유무에 따라 서브 메뉴 위치 조정
	var options = { // 다이얼로그 옵션
		close : true,
		closeHTML : "닫기",
		closeClass : "close",
		onClose : function(dialog) {
			$.modal.close(); // Must be Call
			$("#noticeBox").hide(); // 차트를 닫으면 하단에 차트가 보이기 때문에 숨겨줌
			$("#noticeBox").html(""); // 차트를 비움 
		}
	}
	$(function() {
		if ($("#nav").children("li").is("#adminMenu") === true) {
			$("#subnav7").css("margin-left", "625px");
			adminFlag = true;
		} else {
			$("#subnav7").css("margin-left", "510px");
		}
		// 지방청 관리자가 아닐경우는 li갯수의 변화로 subnav6의 위치 재조정
		if ($("#subnav6 li:last a").is("[href='../manageUser/smsLog.jsp']") != true) {
			$("#subnav6").css("margin-left", "440px");
		}

		/*
		 *	상위 메뉴 li 를  순회하면서
		 *	일반 사용자 메뉴와 관리자 의 메뉴를 구분하고 마우스 오버시 효과 설정
		 *	li 3번째가 주소록되어 있지 않으면  관라자로써 일반사용자와는 다르게
		 *  구분되어지는 메뉴 시점부터 margin-left를 앞으로 70만큼 더 당겨줌 
		 */

		$("#nav li").each(
				function(index) {
					if (index == 5) {
						if (!adminFlag) {
							index = 6;
						}
					}
					if ($("#nav li").eq(2).children("a").text() != "주소록") {
						if (index < 2) {
							sub[index] = "#subnav".concat(index + 1);
						} else {
							sub[index] = "#subnav".concat(index + 2);
							var moveToleft = (parseInt($(sub[index]).css(
									"margin-left")) - 80).toString().concat(
									"px");
							//alert(  moveToleft );
							$(sub[index]).css("margin-left", moveToleft);
						}
					} else {
						sub[index] = "#subnav".concat(index + 1);
					}
					$(this).mouseover(function() {
						$(selectedSub).hide();
						$(sub[index]).show();
						selectedSub = sub[index];
					});
				});

		$(".motli a").click(function() {
			var arg = $(this).attr("alt");
			$.get("../sms_send/ajax/main_board_view.jsp", {
				num : arg
			}, function(html) {
				$("#noticeBox").html(html);
				$("#noticeBox").modal(options);
			});
			return false;
		});
		/*	
		 * 관라자일 경우 subnav3의 li 갯수는 하나 이기때문에
		 * 주소록과 서브 메뉴를 주소를 관리자용으로 바꿔줌
		 */
		if ($("#subnav3 li").size() == 1) {
			$("ul#nav li:eq(2) a").attr("href", "../Address/Addr_adm.jsp");
			$(".sms_list dt:last div a")
					.attr("href", "../Address/Addr_adm.jsp");
		}

		/*
		$(".date").each(function(){ // 공지사항 year는 잘라버림
			$(this).text( "[".concat( $(this).text().slice(5)).concat("]") );
		});*/
	});
</script>