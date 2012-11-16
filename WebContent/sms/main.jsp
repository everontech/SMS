<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.*"  %>			
<%
Boolean result = (Boolean)session.getAttribute("logined");
if(  result == null || result == false ){
	response.setContentType("text/html;charset=euc-kr");
	out.println("<script>");
	out.println("alert('로그인후 이용가능합니다.')");
	out.println("window.location.href='../login/login.jsp'; ");
	out.println("</script>");	
}

%>
<%-- 헤더  --%>
<jsp:include page="../modules/header.jsp" />
<link rel="stylesheet" type="text/css" href="./css/sms.css"/>
<style>
#myMessageBox, #specailCharBox{cursor: pointer;}
</style>
<script type="text/javascript" src="./js/sms_page.js"></script>
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
				<div class="phone">
					<div id="send_box">
						<ul class="choice">
							<li><input type="radio" name="radio" alt="SMS" checked="checked" /><span>SMS</span>
								<input type="radio" name="radio" alt="LSM" /><strong>LSM/MMS</strong>
							</li>
						</ul>
						<p class="disk_icon">
							<img src="./images/lettersend/icon_disk2.gif" />
						</p>
						<p>
							<textarea id="f_message" name="f_message" cols="" rows=""
								class="txt"></textarea>
						</p>
						<ul class="txt_01">
							<li><img src="./images/lettersend/icon_sms.gif" /></li>
							<li id="textByte" class="bytes">0/80Bytes</li>
						</ul>
						<ul class="choice_icon">
							<li><img src="./images/lettersend/icon_disk.gif"
								id="addFile" border="0" /></li>
							<li><img src="./images/lettersend/icon_write.gif"
								id="resetTextBtn" border="0" /></li>
						</ul>
					</div>
				</div>

				<!-- 전화번호 입력 박스 -->
				<div id="pone_list">
					<div class="bg">
						<div class="srh">
							<!-- <input id="ajaxSearch" size="15" type="Text" value="이름 검색"/>&nbsp;<a href="#" onclick="return false;">
						<img src="./images/btn_srh.gif" alt="검"> <img id="addList" src="./images/btn_addre.gif" alt="주소록" /></a>-->
							<a tabindex="996" id="addList" href="#"><img
								src="./images/sms/icon_docu.gif" alt="주소록" />주소록</a> <a
								tabindex="995" id="addExcel" href="#"><img
								src="./images/sms/icon_excel.gif" alt="엑셀등록" />엑셀등록</a> <a
								tabindex="994" id="listPlus" href="#"><img
								src="./images/sms/iconbtn_plus.gif" alt="추가" />항목 추가</a>
							<div id="slist" class="srh_box" style="display: none;"></div>
						</div>
						<div id="testTemp"
							style="position: absolute; left: 42px; top: 20px; display: none;">
						</div>
						<!--
					<div id="receiver">
					<img id="listPlus" src="./images/iconbtn_plus.gif" alt="추가" />항목 추가
					</div>
					-->
						<ul>
							<li>
								<div class="lt">
									<input name="recvName1" id="recvName1" type="text" class="inp" />
									<div class="nt">1</div>
								</div>
								<div class="rt">
									<input name="recvPhone1" id="recvPhone1" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName2" id="recvName2" type="text" class="inp"
										value="" />
									<div class="nt">2</div>
								</div>
								<div class="rt">
									<input name="recvPhone2" id="recvPhone2" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName3" id="recvName3" type="text" class="inp"
										value="" />
									<div class="nt">3</div>
								</div>
								<div class="rt">
									<input name="recvPhone3" id="recvPhone3" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName4" id="recvName4" type="text" class="inp"
										value="" />
									<div class="nt">4</div>
								</div>
								<div class="rt">
									<input name="recvPhone4" id="recvPhone4" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName5" id="recvName5" type="text" class="inp"
										value="" />
									<div class="nt">5</div>
								</div>
								<div class="rt">
									<input name="recvPhone5" id="recvPhone5" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName6" id="recvName6" type="text" class="inp"
										value="" />
									<div class="nt">6</div>
								</div>
								<div class="rt">
									<input name="recvPhone6" id="recvPhone6" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName7" id="recvName7" type="text" class="inp"
										value="" />
									<div class="nt">7</div>
								</div>
								<div class="rt">
									<input name="recvPhone7" id="recvPhone7" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName8" id="recvName8" type="text" class="inp"
										value="" />
									<div class="nt">8</div>
								</div>
								<div class="rt">
									<input name="recvPhone8" id="recvPhone8" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName9" id="recvName9" type="text" class="inp"
										value="" />
									<div class="nt">9</div>
								</div>
								<div class="rt">
									<input name="recvPhone9" id="recvPhone9" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
							<li>
								<div class="lt">
									<input name="recvName10" id="recvName10" type="text"
										class="inp" value="" />
									<div class="nt">10</div>
								</div>
								<div class="rt">
									<input name="recvPhone10" id="recvPhone10" type="text"
										class="inp" value="" />
									<div class="bt">
										<img src="./images/sms/btn_close2.gif" alt="닫기" />
									</div>
								</div>
							</li>
						</ul>
						<!--div class="nsent"-->
						<div class="nsent" id="JsNewNum">
							<!-- <strong>최근보낸번호</strong> : -->
							<a href="#" tabindex="992" onclick="return false;" id="vknPad"><img
								src="./images/sms/keyboard_btn.gif" alt="가상숫자키" /></a> <a href="#"
								tabindex="991" onclick="return false;" id="reset"><img
								src="./images/sms/btn_resetsmall.gif" alt="초기화" /></a>
							<div id="reservationTime">
								<strong>예약일시</strong> :<span></span>
							</div>
						</div>
						<!--임시 예약 필드 yyyymmddhhmi <input type="text" name="reserve" value=""-->
						<div class="to">
							내 번호 :<input type="text" name="f_callback" id="f_callback"
								value="" style="width: 151px; margin-left: 20px;" class="inp" />
						</div>
						<div class="btn">
							<a id="finalSendBtn" href="#" onclick="return false">
								<img src="./images/sms/btn_send.gif" alt="보내기" />
							</a>
						</div>
					</div>
				</div>
				<!-- //전화번호 입력 박스 -->


				<div class="tab_box">
					<h4 class="tab01">
						<img src="./images/lettersend/tab01_off.gif" id="myMessageBox" alt="내문자" border="0" /></a>
						<div class="my01">내문자 영역</div>
					</h4>
					<h4 class="tab02">
						<img src="./images/lettersend/tab02_on.gif"  id="specailCharBox" alt="특수문자" border="0" /></a>
						<div class="my02">특수문자 영역</div>
					</h4>
					<h4 class="tab03">
						<img src="./images/lettersend/tab_line.gif" />
					</h4>
				</div>
			</div>
		</div>
	</div>
	<!-- Numeric Key Pad -->
	<ol id="controls">
		<li id="keyPadClose"><a href="#">Close</a></li>
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>
		<li><a href="#">6</a></li>
		<li><a href="#">7</a></li>
		<li><a href="#">8</a></li>
		<li><a href="#">9</a></li>
		<li><a href="#">0</a></li>
		<li><a href="#" id="backSpace">BackSpace</a></li>
	</ol>
	<!-- //Numeric Key Pad -->
	
<jsp:include page="../modules/footer.jspf" />	
</body>
<script type="text/javascript">
<!--
	$("#top_menu1").attr("data-on", "on");
	$("#top_menu1 > img").attr("src", "./images/top/menu01_on.gif");
	$("#send_top_menu > img").attr("src", "./images/top/menu_sub01_on.gif");
	$("#send_top_menu").attr("data-on", "on");
//-->

	
</script>
</html>