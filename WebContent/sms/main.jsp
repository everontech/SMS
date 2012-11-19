<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ page import="java.util.*" %>	
<%@ page import="kr.go.police.*"  %>			
<%

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
        	<h3><img src="./images/lettersend/title.gif" alt="문자보내기" /></h3>
            <div class="phone">
						<ul class="choice">
							<li><input type="radio" name="radio" alt="SMS" checked="checked" /><span>SMS</span>
								<input type="radio" name="radio" alt="LSM" /><strong>LSM/MMS</strong>
							</li>
						</ul>
						<p class="disk_icon">
							<img src="././images/lettersend/icon_disk2.gif" />
						</p>
						<p>
							<textarea id="message" name="message" cols="" rows="" class="txt"></textarea>
						</p>
						<ul class="txt_01">
							<li><img  id="sms_sep_icon" src="./images/lettersend/icon_sms.gif" /></li>
							<li id="textByte" class="bytes">0/80Bytes</li>
						</ul>
						<ul class="choice_icon">
							<li><img src="././images/lettersend/icon_disk.gif"
								id="addFile" border="0" /></li>
							<li><img src="././images/lettersend/icon_write.gif"
								id="resetTextBtn" border="0" /></li>
						</ul>
				</div>
          <!-- 전화번호 입력 박스 -->
				<div id="pone_list">
					<div class="bg">
						<div class="srh">
							<!-- <input id="ajaxSearch" size="15" type="Text" value="이름 검색"/>&nbsp;<a href="#" onclick="return false;">
						<img src="./images/btn_srh.gif" alt="검"> <img id="addList" src="./images/btn_addre.gif" alt="주소록" /></a>-->
							<div id="slist" class="srh_box" style="display: none;"></div>
						</div>
						<div id="testTemp" style="position: absolute; left: 42px; top: 20px; display: none;"></div>
						<!--
					<div id="receiver">
					<img id="listPlus" src="./images/iconbtn_plus.gif" alt="추가" />항목 추가
					</div>
					-->	
                        <div class="list_box">
                            <ul style="margin-top: 5px;">
                                <li><span>01</span><input name="recvName1" id="recvName1"  maxlength="5" type="text" class="inp" /></li>
                                <li><input name="recvPhone1" id="recvPhone1" type="text" class="inp" maxlength="11" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>02</span><input name="recvName2" id="recvName2"  maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone2" id="recvPhone2" type="text" class="inp" maxlength="11" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>03</span><input name="recvName3" id="recvName3"  maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone3" id="recvPhone3" type="text" class="inp" maxlength="11" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>04</span><input name="recvName4" id="recvName4" maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone4" id="recvPhone4" type="text" class="inp" maxlength="11" value="" style="width:150px;"/></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>05</span><input name="recvName5" id="recvName5" maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone5" id="recvPhone5" type="text" class="inp" maxlength="11" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>06</span><input name="recvName6" id="recvName6" maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone6" id="recvPhone6" type="text" class="inp" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>07</span><input name="recvName7" id="recvName7" maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone7" id="recvPhone7" type="text" class="inp" maxlength="11" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>08</span><input name="recvName8" id="recvName8" type="text" class="inp" /></li>
                                <li><input name="recvPhone9" id="recvPhone9" type="text" class="inp" value="" style="width:150px;" /></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>09</span><input name="recvName9" id="recvName9"  maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone9" id="recvPhone9" type="text" class="inp" maxlength="11" value="" style="width:150px;"/></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                            <ul>
                                <li><span>10</span><input name="recvName10" id="recvName10"  maxlength="5"  type="text" class="inp" /></li>
                                <li><input name="recvPhone10" id="recvPhone10" type="text"  maxlength="11" class="inp" value="" style="width:150px;"/></li>
                                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                            </ul>
                        </div>
						<!--div class="nsent"-->
						<div class="nsent" id="JsNewNum" style="margin-top: 10px;">
							<span id="toolbar" class="ui-widget-header ui-corner-all" >
						    	<a tabindex="996" id="addList" href="#">주소록</a>
                                <a tabindex="995" id="addExcel" href="#">엑셀</a> 							
								<a href="#" tabindex="992" onClick="return false;" id="vknPad">숫자패드</a> 
                            	<a href="#" tabindex="991" onClick="return false;" id="reset">초기화</a>
                      	   		<a tabindex="994" id="listPlus" href="#" onClick="return false;">입력추가</a>  
							</span>
						</div>
						<!--임시 예약 필드 yyyymmddhhmi <input type="text" name="reserve" value=""-->
						<div id="reservationTime">
						<!-- 	<strong>예약일시</strong> :<span></span> -->
						</div>
                        <div class="to" style="font-size: 16px; font-weight: bold;text-align: center;">
							내 번호 :<input type="text" name="my_phone_num" id="my_phone_num"
								value="" style="width: 181px; margin-left: 5px;" class="inp" />
						</div>
						<div class="btn">
							<a id="finalSendBtn" href="#" onClick="return false">
								<img src="./images/sms/btn_send.gif" alt="보내기" />
							</a>
						</div>
					</div>
				</div>
				<!-- //전화번호 입력 박스 -->

				<div class="tab_box">
					<h4 class="tab01">
						<img src="././images/lettersend/tab01_off.gif" id="myMessageBox" alt="내문자" border="0" /></a>
						<div class="my01">내문자 영역</div>
					</h4>
					<h4 class="tab02">
						<img src="././images/lettersend/tab02_on.gif"  id="specailCharBox" alt="특수문자" border="0" /></a>
	                    <div class="my02">
						<jsp:include page="../modules/specialBox.jspf" />
                    </div>
                </h4>
                <h4 class="tab03"><img src="./images/lettersend/tab_line.gif" /></h4>
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
	// 버튼 ui 처리
	//$(".srh a").button();
	$( "#toolbar" ).buttonset();

	// 메뉴 효과 처리
	$("#top_menu1").attr("data-on", "on");
	$("#top_menu1 > img").attr("src", "././images/top/menu01_on.gif");
	$("#send_top_menu > img").attr("src", "././images/top/menu_sub01_on.gif");
	$("#send_top_menu").attr("data-on", "on");

//-->
</script>
</html>
