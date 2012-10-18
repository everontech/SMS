<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>

<html>
<head>
<title>충북청 SMS</title>
<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
<script type="text/javascript" src="./js/jquery-1.8.2.min.js"></script>
<link rel="stylesheet" type="text/css" href="../css/basic_text.css">
<script language="javascript">
var newwin;

function popOpen(webpage){
			
			if (newwin == null || newwin.closed){
				newwin = window.open(webpage,"newwin", "width=400, height=250, toolbar=no, location=no, menubar=no, scrollbars=no, resizeble=no,top=400, left=400" );
				
			} else {
				newwin.close()
				newwin = window.open(webpage,"newwin", "width=400, height=250, toolbar=no, location=no, menubar=no, scrollbars=no, resizeble=no,top=400, left=400" );
			}
		
	}
	
function popOpen2(webpage){
	if (newwin == null || newwin.closed){
				newwin = window.open(webpage,"newwin", "width=720, height=600, toolbar=no, location=no, menubar=no, scrollbars=no, resizeble=no,top=50, left=50" );
				
	} else {
		newwin.close()
		newwin = window.open(webpage,"newwin", "width=720, height=600, toolbar=no, location=no, menubar=no, scrollbars=no, resizeble=no,top=50, left=50" );
	}
}
function ok(){
	frm = document.login;
	if (frm.f_id.value == "") {
		alert("아이디를 입력하세요!");
		frm.f_id.focus();
	} else if (frm.f_password.value == ""){
		alert("아이디를 입력하세요!");
		frm.f_password.focus();
	} else {
		frm.action = "chkLogin.jsp";
		frm.method = "post";
		frm.submit();
	}
}

//\========================================================================
//====================================  쿠키 설정 =============================
<!--
function setCookie (name, value, expires) {
  document.cookie = name + "=" + escape (value) +
    "; path=/; expires=" + expires.toGMTString();
}

function getCookie(Name) {
  var search = Name + "="
  if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
    offset = document.cookie.indexOf(search)
    if (offset != -1) { // 쿠키가 존재하면
      offset += search.length
      // set index of beginning of value
      end = document.cookie.indexOf(";", offset)
      // 쿠키 값의 마지막 위치 인덱스 번호 설정
      if (end == -1)
        end = document.cookie.length
      return unescape(document.cookie.substring(offset, end))
    }
  }
  return "";
}
function saveid() {
	form = document.login;
  var expdate = new Date();
  // 기본적으로 30일동안 기억하게 함. 일수를 조절하려면 * 30에서 숫자를 조절하면 됨
  if (form.checksaveid.checked)
    expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 30); // 30일
  else
    expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
  setCookie("saveid", form.f_id.value, expdate);
}
function getid(form) {
  form.checksaveid.checked = ((form.f_id.value = getCookie("saveid")) != "");
}
//-->

//===============================================================================
//===============================================================================
	
$(function(){
	alert("wef");
});
</script>

</head>
<body onload="document.login.f_id.focus();getid(document.login)" >

<p align="center">
<form name="login" action="#" method="post">
<!-- ImageReady Slices (login.PSD) -->
  <table id="Table_01" width="801" height="601" border="0" cellpadding="0" cellspacing="0">
    <tr> 
      <td colspan="11"> <img src="images/login_top.gif" width="800" height="247" alt=""></td>
    </tr>
    <tr> 
      <td width="218" rowspan="11" background="images/login_left.gif">&nbsp; </td>
      <td colspan="9"> <img src="images/login_03.gif" width="379" height="17" alt=""></td>
      <td width="204" rowspan="11" background="images/login_right.gif">&nbsp; </td>
    </tr>
    <tr> 
      <td width="37" rowspan="3"> <img src="images/login_05.gif" width="37" height="90" alt=""></td>
      <td width="48"> <img src="images/img_id.gif" width="48" height="26" alt=""></td>
      <td colspan="2" height="26" style="padding-left:6px;padding-top:1px;" bgcolor="#f2f2f2"> 
        <input type="text" name="f_id" size="18" style="height:23px;" tabindex="1"></td>
      <td colspan="3" rowspan="2"> <a href="javascript:ok();"><img src="images/btn_login.gif" width="101" height="55" alt="로그인하기" tabindex="4"></a></td>
      <td colspan="2" rowspan="3"> <img src="images/login_09.gif" width="53" height="90" alt=""></td>
    </tr>
    <tr> 
      <td> <img src="images/img_pass.gif" width="48" height="29" alt=""></td>
      <td colspan="2" height="29" style="padding-left:6px;padding-top:1px;" bgcolor="#f2f2f2"> 
        <input type="password" name="f_password" size="18" style="height:23px;" tabindex="2" onkeypress="javascript:if(event.keyCode == 13) {ok()}"></td>
    </tr>
    <tr> 
      <td colspan="2" height="35" bgcolor="#f2f2f2" style="padding-left:50px;padding-top:12px;"> 
        <input type="checkbox" name=checksaveid onClick="saveid()" tabindex="3"  ></td>
        
      <td width="117"> <img src="images/img_id_save.gif" width="117" height="35" alt=""></td>
      <td colspan="3"> <img src="images/login_14.gif" width="101" height="35" alt=""></td>
    </tr>
    <tr> 
      <td colspan="9"> <img src="images/login_15.gif" width="379" height="12" alt=""></td>
    </tr>
    <tr> 
      <td colspan="9"> <img src="images/login_16.gif" width="379" height="9" alt=""></td>
    </tr>
    <tr> 
      <td colspan="5" rowspan="2" style="font-size:11px; font-weight:bold;font-familly:DOTUM; color:#666666" ><div align="center"><font face="돋움">회원가입을 안하셨나요?</font></div></td>
      <td width="51"> <a href="javascript:popOpen2('../enrollUser/entryUser.jsp')"><img src="images/btn_member_entry.gif" width="51" height="14" alt=""></a></td>
      <td colspan="3" rowspan="2"> <img src="images/login_19.gif" width="79" height="17" alt=""></td>
    </tr>
    <tr> 
      <td> <img src="images/login_20.gif" width="51" height="3" alt=""></td>
    </tr>
    <tr> 
      <td colspan="5" rowspan="2" style="font-size:11px; font-weight:bold; font-familly:DOTUM; color:#666666"><div align="center"><font face="돋움">아이디와 
          비밀번호를 잊으셨나요?</font></div></td>
      <td colspan="4" rowspan="2" > <a href="javascript:popOpen('search_id.jsp');"><img src="images/btn_id_search.gif" alt=""></a><a href="javascript:popOpen('search_pass.jsp');"><img src="images/btn_pass_search.gif" alt=""></a> 
      </td>
    </tr>
    <tr> 
    </tr>
    <tr> 
      <td height="187" colspan="9" background="images/login_bottom.gif">
      <p align="center">문의처 
          : 충북지방경찰청 정보통신2담당실 <BR>
          일반 043)240-2243 / 경찰 54 - 2243 </p>
        <p align="center">&nbsp;</p>
        </td>
    </tr>
    <tr> 
      <td>&nbsp;</td>
      <td colspan="9">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr> 
      <td> <img src="images/spacer.gif" width="218" height="1" alt=""></td>
      <td> <img src="images/spacer.gif" width="37" height="1" alt=""></td>
      <td> <img src="images/spacer.gif" width="48" height="1" alt=""></td>
      <td width="23"> <img src="images/spacer.gif" width="23" height="1" alt=""></td>
      <td> <img src="images/spacer.gif" width="117" height="1" alt=""></td>
      <td width="24"> <img src="images/spacer.gif" width="24" height="1" alt=""></td>
      <td> <img src="images/spacer.gif" width="51" height="1" alt=""></td>
      <td width="26"> <img src="images/spacer.gif" width="26" height="1" alt=""></td>
      <td width="21"> <img src="images/spacer.gif" width="21" height="1" alt=""></td>
      <td width="32"> <img src="images/spacer.gif" width="32" height="1" alt=""></td>
      <td> <img src="images/spacer.gif" width="203" height="1" alt=""></td>
    </tr>
  </table>
</form>
<!-- End ImageReady Slices -->
</body>
</html>
