<%@ page contentType="text/html; charset=euc-kr" pageEncoding="EUC-KR" %>
<%
	String f_id = "wefwe";
 %>
<c:set var="logchk" value="<%=f_id%>" />
<c:if test="${empty logchk}">
<script type="text/javascript">
	//alert("로그인 후 이용하실수 있습니다");
	//parent.document.location.href="../logout.jsp";
</script>
</c:if>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=euc-kr" />
<link rel="stylesheet" type="text/css" href="./css/default.css" />
<link rel="stylesheet" type="text/css" href="./css/formTable.css" />
<link rel="stylesheet" type="text/css" href="./css/plug-in/fileTree/default/easyui.css" />
<link rel="stylesheet" type="text/css" href="./css/main_page.css" />
<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="./js/plug-in/fileTree/jquery.easyui.min.js"></script>
<script src="./js/plug-in/jquery-tools-1.2.4.min.js"></script>
<script type="text/javascript" src="./js/sms-utils.js"></script>
<script type="text/javascript" src="./js/main-page.js"></script>

<!--[if lte IE 6]>
<style>
ol#controls{width: 156px;}
</style>
<![endif]-->
<title>문자보내기</title>
</head>
<body>
<div id="wrapper" >
<%--	상단 메뉴 --%>
<jsp:include page="../module/menu.jsp" flush="false"></jsp:include>
<div id="innerwrapper">
<!--  상단 메뉴  -->
<% 	String s_key = request.getParameter("s_key");
	String s_str = request.getParameter("s_word");
	if(s_str == null) {
		s_str = "";
	} else {
	//	s_str = new String(s_str.getBytes("8859_1"),"euc-kr");
	}
 %>
 <!--  main  -->
<div id="main"  >
<h2 id="Intro">
	<img src="./images/bgLstTitle1.gif" alt="" />
	<img src="./images/titleSend.gif" alt="문자보내기" />
	<img src="./images/bgLstTitle3.gif" style="" alt=""/>
</h2>
<form name="form1" id="form1" method="post" action="send_proc.jsp" >  
	<input type="hidden" name="chk_ps" id="chk_ps" value="" />
	<input type="hidden" name="f_id" id="f_id" value="" />
	<input type="hidden" name="f_to_telnum1" id="f_to_telnum1" value="" />
	<input type="hidden" name="f_reserve_date" id="f_reserve_date"  value="" />
	<input type="hidden" name="f_reserve_time"  id="f_reserve_time" value="" />
	<input type="hidden" name="f_from_telnum" id="f_from_telnum"  value="" />
	<input type="hidden" name="f_send_state" id="f_send_state" value="0" />
	<input type="hidden" name="f_deptcode" id="f_deptcode"  value="" />
<div id="phoneView">
	<div class="phonexViewTextArea">
 	<textarea name="f_message" tabindex="0" style="margin-left:22px;margin-right:20px;" id="f_message" rows="5" cols="12" >여기에 메세지를 입력하세요.  - 업무외 사적용도 사용금지	</textarea>
	</div>
	<div style="text-align: center"><input style="color:#fff" tabindex="999" size="2" value="0" id="textByte" name="txtByte" />Byte</div>
	<div id="phoneViewLogo">충북청 SMS</div>
	<div id="phoneViewBtn"><a tabindex="998" id="resetTextBtn" href="#"><img src="./images/btn_rewr.gif" alt="다시쓰기버튼" /></a><a tabindex="997" id="reserveBtn" href="#"><img src="./images/btn_tsend.gif" alt="예약버튼" /></a></div>
</div>

<!-- 전화번호 입력 박스 -->
 <div id="pone_list">
 <div class="bg">
<div class="srh"><!-- <input id="ajaxSearch" size="15" type="Text" value="이름 검색"/>&nbsp;<a href="#" onclick="return false;">
	<img src="./images/btn_srh.gif" alt="검"> <img id="addList" src="./images/btn_addre.gif" alt="주소록" /></a>-->
	<a tabindex="996" id="addList" href="#"><img src="./images/icon_docu.gif" alt="주소록" />주소록</a>
	<a tabindex="995" id="addExcel" href="#"><img src="./images/icon_excel.gif" alt="엑셀등록" />엑셀등록</a>
	<a tabindex="994" id="listPlus" href="#"><img src="./images/iconbtn_plus.gif" alt="추가" />항목 추가</a>
<div id="slist" class="srh_box" style="display:none;">
</div>
</div>
<div id="testTemp" style="position:absolute;left:42px;top:20px;display:none;">
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
		<div class="nt">1</div></div>
		<div class="rt">
		<input name="recvPhone1" id="recvPhone1"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName2" id="recvName2" type="text" class="inp" value="" />
		<div class="nt">2</div></div>
		<div class="rt">
		<input name="recvPhone2" id="recvPhone2" type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName3" id="recvName3" type="text" class="inp" value="" />
		<div class="nt">3</div></div>
		<div class="rt">
		<input name="recvPhone3" id="recvPhone3"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName4" id="recvName4" type="text" class="inp" value="" />
		<div class="nt">4</div></div>
		<div class="rt">
		<input name="recvPhone4" id="recvPhone4" type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName5" id="recvName5" type="text" class="inp" value="" />
		<div class="nt">5</div></div>
		<div class="rt">
		<input name="recvPhone5" id="recvPhone5"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName6" id="recvName6" type="text" class="inp" value="" />
		<div class="nt">6</div></div>
		<div class="rt">
		<input name="recvPhone6" id="recvPhone6" type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName7" id="recvName7" type="text" class="inp" value="" />
		<div class="nt">7</div></div>
		<div class="rt">
		<input name="recvPhone7" id="recvPhone7"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName8" id="recvName8" type="text" class="inp" value="" />
		<div class="nt">8</div></div>
		<div class="rt">
		<input name="recvPhone8" id="recvPhone8"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName9" id="recvName9" type="text" class="inp" value="" />
		<div class="nt">9</div></div>
		<div class="rt">
		<input name="recvPhone9" id="recvPhone9" type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>
	<li>
		<div class="lt">
		<input name="recvName10" id="recvName10" type="text" class="inp" value="" />
		<div class="nt">10</div></div>
		<div class="rt">
		<input name="recvPhone10" id="recvPhone10"  type="text" class="inp" value="" />
		<div class="bt">
		<img src="./images/btn_close2.gif" alt="닫기" /></div></div>
	</li>	
 </ul>
<!--div class="nsent"-->
<div class="nsent" id="JsNewNum" >
<!-- <strong>최근보낸번호</strong> : -->
	<a href="#" tabindex="992" onclick="return false;" id="vknPad"><img src="./images/keyboard_btn.gif" alt="가상숫자키" /></a>
	<a href="#" tabindex="991" onclick="return false;" id="reset"><img src="./images/btnClear.gif" alt="초기화" /></a>
<div id="reservationTime">
	<strong>예약일시</strong> :<span></span>
</div>  
</div>
 <!--임시 예약 필드 yyyymmddhhmi <input type="text" name="reserve" value=""-->
<div class="to">내  번호 :<input type="text" name="f_callback" id="f_callback"  value="" style="width:151px;margin-left: 20px;" class="inp" /></div>
<div class="btn"><a id="finalSendBtn" href="#" onclick="return false"><img src="./images/btn_send.gif" alt="보내기" /></a></div>
</div>
</div>
<!-- //전화번호 입력 박스 -->

<!--emoticons tab -->
<div id="emoticonsTab">
	<h2 class="current">특수문자-1</h2>
	<div class="pane" id="specialChar1" style="display:block"></div>
	<h2>특수문자-2</h2>
	<div class="pane" id="specialChar2"></div>
	<h2>특수문자-3</h2>
	<div class="pane" id="specialChar3"></div>
	<h2>특수문자-4</h2>
	<div class="pane" id="specialChar4"></div>			
	<h2>이모티콘</h2>
	<div class="pane" id="specialChar5"></div>
</div>
<!--//emoticons tab -->

<!--  메세지 리스트 박스 -->
</form>
<div style="clear: both;"></div>

<!-- //메세지 리스트 박스 -->

<!--  //main -->
</div>
</div><!-- wrapper -->
</div><!-- innerwrapper -->

<!-- 달력 -->
<div id="dateTimePicker"></div>

<div id="spamList">

</div>

<div class="miniload"> 
	<div class="minile"><img src="./images/img_sloading.gif" alt="로딩중" /></div>	
	<div class="minire"><strong>페이지를</strong>로딩중입니다</div> 
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
</body>


</html>