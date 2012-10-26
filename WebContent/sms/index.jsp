<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%-- 헤더  --%>
<%@ include file="../modules/header.jsp" %>
<body>
<div id="wrapper">
	<%-- 상단메뉴  --%>
	<%@ include file="../modules/topmenu.jsp" %>

    <div id="contents">
	<%-- 사이드 메뉴  --%>
	<%@ include file="../modules/sidebox.jsp" %>
        
        
        <div id="contentsWrap">
        	<h3><img src="images/lettersend/title.gif" alt="문자보내기" /></h3>
            <div class="phone">
           	    <ul class="choice">
                    <li>
                        <input type="radio" name="radio" alt="SMS" /><span>SMS</span>
                        <input type="radio" name="radio" alt="LSM" /><strong>LSM/MMS</strong>
                    </li>
                </ul>
                <p class="disk_icon"><img src="images/lettersend/icon_disk2.gif" /></p>
                <p><textarea name="" cols="" rows="" class="txt"></textarea></p>
                <ul class="txt_01">
                    <li><img src="images/lettersend/icon_sms.gif" /></li>
                    <li class="bytes">0/80Bytes</li>
                </ul>
                <ul class="choice_icon">
               	    <li><img src="images/lettersend/icon_disk.gif"  border="0"/></li>
                    <li><img src="images/lettersend/icon_write.gif" border="0" /></li>
                </ul>
            </div>
            <div class="tab_box">
                <h4 class="tab01"><a href=""><img src="images/lettersend/tab01_off.gif" alt="내문자" border="0" /></a>
                    <div class="my01"> 내문자 영역 </div>
                </h4>
                <h4 class="tab02"><a href=""><img src="images/lettersend/tab02_on.gif" alt="특수문자" border="0" /></a>
                    <div class="my02"> 특수문자 영역 </div>
                </h4>
                <h4 class="tab03"><img src="images/lettersend/tab_line.gif" /></h4>
            </div>
        </div>
    </div>
  <div id="footer">푸터영역</div>
</div>

</body>


</html>