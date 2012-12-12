/**
 * 	sms 
 *  메인 문자 발송 페이지 
 *  main.jsp
 */
var phoneReg =  /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;	// 핸드폰 정규식
// 폼 정규식 및 메세지 관련
jQuery.phone = {
	smsMode : true,	// sms 모드인지		
	/*
 	 *	엔터값 체크
	 */
	checkEnter : function(buf){	// enter 체크
		var n = 0
		var Is_one_char = "";
		for(var i=0; i<buf.length; i++){
			//encoding
			var hex = escape(buf.substring(i,i + 2));
			Is_one_char = buf.charAt(i);
			
			if(escape(Is_one_char).length > 4)// enter이면
			{
				n += 2;
			}else{// enter 아니면
				n++;
			}
		}
		return n;	
	},
	/*
	 * 문자 메세지란에 값 체크 
	 * 키값을 받아 코드값이 한글일 경우 문자가 byte로 되기 때문에 2글자로 처리 
	 */
	checkLength : function(event){	// 문자 메시지 80자 제한
		var that = $("#message");
		var textThat = $("#textByte");
		var buf = that.val() || that.text();
		var encodedStr = $.phone.checkEnter(buf);
		var l = 0;

		// sms 모드이면
		if($.phone.smsMode){
			if ( encodedStr > 80) {
				if(event.keyCode == 8 || event.keyCode ==46){
					return false;
				}
				
		        for (var i=0; i< buf.length; i++) { 
	                l += (buf.charCodeAt(i) > 128) ? 2 : 1; //  한글 처리 
	                if (l > 80){
	                	// sms 모드일경우 mms 모드로 전환한다.
	                	if($.phone.smsMode){
			                alert( "80자 이상 입력시 MMS로 변환 됩니다..");
			               //$("#sms_sep_icon").attr("src", "./images/lettersend/icon_mms.gif");
		                	$.phone.smsMode = false;
			                 that.val( buf.substring(0,80) );
			                 $("[name='send_type']").first().removeAttr('checked');		                 
			                 $("[name='send_type']").last().attr('checked', 'chekced');			                
	                	}
	                }else{
	                	$.phone.smsMode = true;
	                }
		        }
			}else{
				textThat.text( encodedStr + "/80Bytes" );
			}
		// mms 모드이면	
		}else{
			if ( encodedStr > 2000) {
				if(event.keyCode == 8 || event.keyCode ==46){
					return false;
				}
			}else{
				
		        for (var i=0; i< buf.length; i++) { 
	                l += (buf.charCodeAt(i) > 128) ? 2 : 1; //  한글 처리 
	                if (l > 2000){
	                	$.phone.smsMode = false;
		                alert( "더이상 입력할수 없습니다.");
		                that.val( buf.substring(0,2000) );
		                // return false;
	                }
		        }				
		        
		        if(l < 80){	// 80Byte 이하이면
	                alert( "SMS 모드로 변환 됩니다..");
                	$.phone.smsMode = true;		   
	                 $("[name='send_type']").last().removeAttr('checked');		                 
	                 $("[name='send_type']").first().attr('checked', 'chekced');		                	
                }
		        
				textThat.text( encodedStr + "/2000Bytes" );
			}
		}
		
	},
	
	/*
	 * split 와 같은 , 를 붙여준후 입력값 체크후 시간을 넣어준후 전송
	 */
	send : function(){	// 문자 전송
		/*
		var remainder = parseInt( $("#freeSendCount span").text() );	// 남은 메시지 건수
		if( remainder == 0){	// 남은 갯수가가 없을때
			//alert("남은 메세지 건수가 없습니다.");
			//return false;			
		}
		*/

		var phoneNumbers = [];
		var senderCount = 0; //  보내는 사람 카운트
		var firstSender = "";
		
		// 입력 전화번호 가져오기
		$("#pone_list ul").each(function(){
			var $input = $(this).find("li:odd input");
			if( $input.val() != "" ){
				if( senderCount == 0 ){
					firstSender =$input.val();
				}
				senderCount++;
				phoneNumbers.push($input.val());
			}
		});

		

		// 남은 메세지 건수 와 보내는 메시지 갯수를 비교 처리
		/*
		if(  remainder < parseInt( senderCount ) ){
			alert("남은 메세지 건수를 확인하세요");
			return false;			
		}
		*/
				
		//phoneNumbers = phoneNumbers.substr(0,(phoneNumbers.length-1) );
		// 입력된 전화번가 없을경우  false 처리
		if( phoneNumbers.length <= 0 ){
			alert("입력된 전화번호가 없습니다.");
			return false;
		}
		
		// 입력된 내용이 없을때 false 처리
		if( isDefault == true || $("#message").val().length == 0 ){
			alert("입력된내용이 없습니다.");
			$("#message").focus();
			return false;
		}
		
		var callback_num = $("#my_phone_num").val();
		if( callback_num.length == 0 /* || !phoneReg.test(callback_num) */ ){
			alert("내 번호를 정확히 입력하세요.");
			$("#my_phone_num").focus();
			return false;			
		}

		if( $.phone.spam() != true ){
			return false;
		}

		//$.phone.doNotDuplicate();
		if( $.phone.check() == true  ){
				// 즉시 전송
			$.phone.doNotDuplicate();	// 같은 전화번호 제거 처리
			// 받는 사람 전화번호를 넣어준다.
			$("#call_to_nums").val(phoneNumbers.join(","));
			if( $("#reservationTime span").text() == "" ){
				$("#f_reserve_date").val( $.date.getNow() ); 
				$("#f_reserve_time").val( $.date.getTime() ); 
			}else{
				// 예약 전송시 형식에 맞게 문자를 자른후 날짜 전송값에 넣어줌
				var str = $("#reservationTime span").text();
				var date = str.substring(0,4)+ str.substring(5,7) + str.substring(8,10);
				var time = str.substring(12,14)+ str.substring(15,17);
				$("#f_reserve_date").val( date ); 
				$("#f_reserve_time").val( time );
			}
			
			// ajax 발송 데이터
			var data = {
					call_to_nums : phoneNumbers.join(","),						// 받는 전화번호들(콤마로 연결)
					message : $("#message").val(),									// 메세지
					my_phone_num :  $("#my_phone_num").val(),				// 내 전화번호
					callback : $("#callback").val(),										// 내 발송 수신할 번호
					send_type : $("[name='send_type']:checked").val(),			// 전송 타입
					reserved : $("#reserved_datetime").val().length>0?"true":"false",		// 	예약 여부
					reserved_datetime : $("#reserved_datetime").val()			// 	예약 날짜
					//reserve_date : $("#f_reserve_date").val(),
					//reserve_time : $("#f_reserve_time").val(),
					//send_state : $("#f_send_state").val(),
					//deptcode:  "", //$("#f_deptcode").val(),					
			};
			
			/*
			 *	ajax 로 sms 발송처리
			 */			
			if( confirm("문자메세지를 발송 하시겠습니까?") == true){
				var str = (senderCount == 1)?(firstSender + "에게 문자 발송"):
											(firstSender + "외 " + (senderCount-1) + "명 문자 발송");
				
				// ajax 로 문자 발송 내역 처리
				$.post("./SmsSendAction.sm", data, function(result){
					var sendCount = parseInt($.trim(result));
					if( sendCount > 0 ){
						$("#send_count").text(sendCount);
						$("#send_result_dialog").dialog({
					            modal: true,
					            width: 350,
					            buttons: {
					                "전송내역이동" : function() {
					                    $( this ).dialog( "close" );
					                    window.location.href = "./SmsSendResultAction.sm";
					                },
					                "다시보내기" : function() {
					                    $( this ).dialog( "close" );
					                },					                
					                /*
					                "문자함저장" : function() {
					                    $( this ).dialog( "close" );
					                },
					                */					                
					                "확인" : function() {
					                    $( this ).dialog( "close" );
					                    //  문자내용 및 전화번호 리셋
					                    $("#message, .list_box .inp").val("");
					                },
					                
					            }
					    });
					}else{
						alert($.trim(result));
					}
				});
			}
		}
	},
	/*
	 * 입력된 전화번호 체크
	 * 각 input을 순회하며  정규식 유효성 값 체크
	 * 값이 비어있지 않을때 유효성 검사후 틀린 항목은 해당 인덱스번호를 알려줌
	 */
 	check : function() {	// 입력된 전화번호 체크
		var flag = true;
		var str = "";
		var invaildInput = null;
		$("#pone_list ul").each(function(index){
			var that = $(this).find("li:odd input");		
			var pNum = that.val();
			/*that.css("background", "none");*/
			if( pNum != "" ){
				if( !phoneReg.test(pNum) ){
					// 잘못된 전화번호중에 맨 첫번째만 추출 
					if(invaildInput == null){
						invaildInput = that;
					}
					str += (index + 1) + "번 ";
					that.css({"background-image" : "url(./images/invalid.gif)",
							  "background-position" : "left top",
							  "background-repeat" : "no-repeat"
						});
					flag = false;
				}
			}
		});
		if( str != "" ){
			alert( str + "전화번호를 정확히 입력하세요" );
			// 잘못 입력된 전화번호의 첫번째에 포커스
			if(invaildInput != null){
				invaildInput.focus();
			}
		}
		return flag;
	},
	/*
	 *	금칙어 처리 
	 *	spamList에서 금칙어들을 순환하면서 입력한 텍스트와 매칭시켜 
	 *	매칭값이 존재하면 false 처리
	 */
	spam : function(){
		var str = $("#f_message").text();
		var flag = true; // 금칙어 유무
		$("#spamList a").each(function(){
			var spamStr = $(this).text(); // 각 금칙어들
			if ( str.match( spamStr ) != null){ // 매칭시켜 금칙어가 나오면
				alert( spamStr + "(은)는 금칙어입니다.");
				flag = false; 
			}
		});
		return flag;
	},
	/*
	 *
	 *
	 */
	doNotDuplicate : function(){
		/*
		var arr = new Array();
		$("#pone_list li").each(function(index){
			var that = $(this).children(".rt").find(":input");
			var pNum = that.val();
			//that.css("background", "none");
					arr.push(pNum);
		});
		var len = arr.length;
		var flag = true;
		for(var i=0; i < len -1; i++  ){
			if( arr[i] == "") continue;
			
			for(var j=i+1; j<len; j++){
				if( arr[i] == arr[j] ){
					$("#pone_list li:eq(" + i + ")").val("");
					flag = false;
					//alert(i+1 +"번째와 "+ parseInt(j+1) + "번째 전화번호가 같습니다." );
					//return false;
				}
			} 
		}
		if( flag == false){
			alert("중복전화번호는 자동제거됩니다.");
		}
		*/
		
	},
	/*
	 *	excel_proc.jsp 를 통해 Excel 파일의 데이터를 문자열로 만들어
	 *	insertExcel 함수를 통해 각각의 이름과 전화번호를 넣어준다.
	 *	또한 전화번호에 (-)있으면 없애고 입력 공간이 부족한경우 <li> 동적 생성후 넣어준다.
	 */
	insertExcel : function( str ){
		str = str.substr( 0, str.length - 1 );	// 마지막 문자 (,)를 없앰
		var arr = str.split(",");
		var len = arr.length;

		var phoneName = null;
		var phoneNum = null;
		var html = null;
		var size = $("#pone_list li").size(); // 전화 번호 입력 사이즈
		var count = 0;
		// 해당 input안에 전화번호가 입력 되어있을 경우 비어 있을때까지 카운터 증가 시킴
		
		$("input[name^='recvPhone']").each(function(index){
			if( $(this).val() != ''){
				 count = index + 1;
			}
		});

		for( var i=0; i<len; i+=2){	// 이름과 전화번호 불러오기
			count++;
			phoneName = arr[i].toString();
			phoneNum = arr[i+1].toString().replace(/-/g,"");	// 전화번호 하이픈 없앰

			// Excel 불러오기시 Excel의 서식에 따라 맨 앞의 숫자 0이 빠질수 있기 때문에 체크하여 0을 넣어줌 
			if( phoneNum.charAt(0) != "0" ){
				phoneNum = "0".concat(phoneNum);
			}

			if( count > size ){	// input 입력공간이 부족한 경우
				//count++;
				/*
                <li><span>05</span><input name="recvName1" id="recvName1" type="text" class="inp" /></li>
                <li><input name="recvPhone1" id="recvPhone1" type="text" class="inp" value="" style="width:150px;" /></li>
                <li class="bt"><img src="./images/sms/btn_close2.gif" alt="닫기" /></li>
                */
				html = "<li><span>" + count + "</span><input name='recvName"+ count + "' id='recvName"+ count + "' type=text' class='inp'>"
								+ "<li><input name='recvPhone" + count + "' id='recvPhone" + count + "' type='Text' class='inp'></li>"
								+ "<li class='bt'><img src='./images/sms/btn_close2.gif' alt='닫기'></li>";
			 
				$("#pone_list li:last").after(html);	// 동적 생성	
				size++;			
			}
				$("#recvName"+count).val( phoneName );	// 이름 넣기
				$("#recvPhone"+count).val( phoneNum );	// 전화번호 넣기
		}
	}	
}

// ajax 로드 관련
jQuery.html = {
	/*
	 * 특수 문자 tab 생성 함수
	 * 재귀 함수로써  로드 처리후 다음 ajax 처리  
	 * 로드가 완료되면 tabs생성후 이벤트 및 효과 처리를 해줌
	 */		
	character : function(i){
		i = i + 1;
		var url = "./ajax/specialCharicters/character-" + i + ".html";
		var that = "#specialChar" + i;
		$(that).load(url, function(){
			if( i < 5){
				$.html.character(i);
			}else if( i == 5 ){
				$("#emoticonsTab").show();
				$("#emoticonsTab")
				.tabs("#emoticonsTab div.pane", {tabs: 'h2', effect: 'slide', initialIndex: 0});
				$("#emoticonsTab table td").mouseover(function(){
					$(this).css("color", "red");
				}).mouseout(function(){
					$(this).css("color", "#666");
				}).click(function(e){
					var message = $("#message");
					if(isDefault == true){
						 message.empty();
						isDefault = false;
					}
					$.phone.checkLength(e);
					message.text( message.text() + $(this).text() );
				});
			}
		});
	},
	
	/*
	 * 공통 및  나의 메세지 로드 함수 
	 * loadBox div 에 인자값에 따른 메세지로드
	 * callback 처리는 scrollable 설정과 이벤트 설정
	 */
	messages : function( flag, selected ){
		if( selected == "all") selected  ="";
		var data = { flag_group : flag, sms_group : selected }
		$("#loadBox").load('./ajax/messagesHTML.jsp', data, function(){
			$(this).find("#messageLoadBox").scrollable(); 
			$("#loadingImg").css("disaply","none");
			$("#messageBoxes .bx textarea").click(function(){
				isDefault = false;	// 초기화면이 아니라고 설정 
				$("#phoneView textarea").text( $(this).text() );//.attr("readonly","readonly");
				$("#f_message_title").val( $(this).next(".title").text() );
			});
		});
	},
	/*
	 *	숫자 키패드 설정 
	 *	토글로 키패드 display 설정
	 */
	NumericKeyPad : function(){
		if( NumericKeyPadFlag == true ){
			NumericKeyPadFlag = false;
			$("#controls").css("display", "none");
		}else{
			NumericKeyPadFlag = true;
			$("#controls").css("display", "block");			
		}
	}
	
}

jQuery.date = { // 현재 시간 및 날짜 가져오기
	/*
	 * 현재 시간 가져오기 yyymmdd
	 */
	getNow : function(){
		var d =  new Date();
		var dateStr = d.getFullYear() + getFormatMon( d.getMonth() ) + getFormatDay( d.getDate() );
		/* 
		 * 날짜 시작이 0부터 이기때문에 1을 더해준후
		 * 한자리일경우 포맷에 맞게 0을 붙여줌
		 */
		function getFormatMon(m){
			m = parseInt(m)+ 1;
			if( m < 10 ){
				m = "0" + m;
			}
			return m.toString();
		};
		/* 
		 * 
		 * getFormatMon 마찬가지로  한자리일 경우 0을 붙여줌
		 */	
		function getFormatDay(dd){
			if( dd < 10 ){
				dd = "0" + dd;
			}
			return dd.toString();
		};
		return dateStr;
		
	},
	getTime : function(){
		var d= new Date();
		var timeStr =  getFormatHour( d.getHours() ).toString() + 
						 getFormatMinutes( d.getMinutes() ).toString();

		function getFormatHour(hh){
			if( hh < 10 ){
				hh = "0" + hh;
			}
			return hh;
		};

		function getFormatMinutes(tt){
			if( tt < 10 ){
				tt = "0" + tt;
			}
			return tt;
		};
		return timeStr;
	}
}

var isDefault = true;
var sec = 500;
var NumericKeyPadFlag = false;	// 키패드 flag
var currentPhoneInput = null;	// 현재 input  객체값
$(document).ready(function(){

	/*
	 *	숫자만 입력 허용-- 전화번호 입력란만 설정
	 */
	$("#pone_list ul li input:odd").inputNumber();

	/*
	 *	초기 메시지란에는 메세지 notice정보가 입력 되어있기 때문에 
	 *  사용자가 처음 포커스를 줄때 기본 메시지창에 입력된 값은 삭제해줌
	 */
	$("#message").one('click',function(){ // 디폴트 메세지 삭제
		if( isDefault == true ){ // 기본 문자 인지 확인
			$(this).empty();
		}
	});
	
	$("#resetTextBtn").click(function(){ // 다시 쓰기 버튼
		$("#message").val("");
		$("#textByte").text("0/80Bytes");
		return false;
	});

	$("#message").keydown(function(e) { // 메세지 키 입력시 마다 키값 체크
		isDefault = false;
		$.phone.checkLength(e);
	});	

	$("#reserveBtn").click(function(){ //예약 버튼 클릭시 예약창 modal
		$('#dateTimePicker').modal(options);
	});

	/*
	 *	전화번호 입력란이 10개이기 때문에 사용자가 추가 버튼을 누를시
	 *  전화번호 입력란 갯수를 체크에 마지막에 동적 태그를 삽입후 포커스를 줌
	 *  생성된 태그는 동적 이벤트 할당
	 */
	$("#listPlus").click(function(){
		var index = $("#pone_list ul").size();

		var html = "<ul><li><span>" + (++index) + "</span><input name='recvName"+ index + "' id='recvName"+ index + "' type=text' class='inp'>"
					+ " <li><input name='recvPhone" + index + "' id='recvPhone" + index + "' type='Text' class='inp' style=\"width:150px;\"></li>"
					+ " <li class='bt'><img src='./images/sms/btn_close2.gif' alt='닫기'></li></ul>";
			 
	   $("#pone_list ul:last").after(html);
		var $lastInput = $("#recvPhone" + index);
		$lastInput.focus();				
		currentPhoneInput = $lastInput;

	});


	/*  
	 *  전화번호 입력공간이 초기에는 10개이지만 추후 추가 될수 있기 때문에
	 *  동적으로 생성된 html에도 event를 걸어줘야함
	 *
	 */
	$(".bt").live("click", function(){
		$(this).parents("ul")
			.children("li")
			.children("input").css("background-image", "none").val("");
	});
	
	// 현재 포커스를 받은 input 객체 저장
	$("#pone_list ul li input:odd").live("focusin", function(){
		currentPhoneInput = $(this);
	});
	
	$("#special td").click(function(e){
		$.phone.checkLength(e);		
		var $message = $("#message");
		$message.val($message.val() + $(this).text());
	});

	/*  
	 *  하단 메세지리스트 박스에서 select 태그의 change 이벤트가 발생할경우
	 *  바뀐 값에 따라 ajax로 메세지를 가져옴
	 */
	$("#messageBoxes select").change(function(){
		var val = $(this).val();
		if( $(this).is("#myGroup") == true ){ // 선택된 그룹이 나의 메시지 그룹일 경우
			flag_group = "my";
		}else{
			flag_group = "public";
		}
			
		if( val != ""){
			//$("#messageLoadBox").empty();
			$.html.messages( flag_group, val );
		}
		//alert( $(this).val() );
		/*
		if( $("#messageBoxes select:first option").val() == "1" ){
			messagesHTML("public", ""); // 공통 메시지를 보여줌
		}else{
			//var selected = $("#myGroup option:selected").val();
			alert( $("#myGroup option").val() );
			messagesHTML("my", selected );
		}
		
		*/
		//myMessages
		//messagesHTML();
	});
	
	var addressWindow = null;
	/**
	 * 주소록 선택시
	 * 주소록 윈도우 창을 띄워준다.
	 */
	$("#address_book_btn").click(function(){		// 주소록 버튼
		var url = "./AddressBookWindow.ad";
		var title = "addressWindow";
		var status = "toolbar=no, scrollbars=no, status=no, menubar=no, width=900, height=750, top=100, left=200";
		if( addressWindow != null ) { 	// 새창이 부모창 위에 보여주기 위함
			addressWindow.close();
		}
		addressWindow = window.open(url, title, status);
		addressWindow.focus();			

	});
	
	$("#send_btn").click(function(){	// 보내기 버튼
		$.phone.send();
	});

	$("#reset").click(function(){ // 초기화
		$("#pone_list ul")
			.children("li")
				.children("input").css("background-image", "none").val("");
	});
	
	$("#vknPad").click(function(){ // Virtual Numeric Keypad
	//	alert("Virtual Numeric Keypad 는 현재 지원 되지 않습니다.(추후 지원 예정)");
		if( currentPhoneInput == null){
			currentPhoneInput = $("#recvPhone1");
		}
		$.html.NumericKeyPad();
	});

	/*
	 *	Numeric KeyPad Event function
	 *	가상 키패드 클릭시 해당 숫자값을 현재 저장된 input 객체값에 넣어준다.
	 *	BackSpace 클릭시 키보드와 같은 효과로 한글자를 잘라낸다
	 *	단  핸드폰 숫자자리보다 같거나 작은경우에만 처리한다.
	 */
	$("ol#controls li a").click(function(){
		var k = $(this).text();
		var v = currentPhoneInput.val();
		var len = v.length;
		if(k == "Close"){
			$.html.NumericKeyPad();
			return false;
		}else if(k == "BackSpace"){
			currentPhoneInput
			.val( v.substr(0, (len-1) ) ).focus();				
		}else{
			if(len < 12 ){	//  핸드폰 자릿수가 넘지 않을때만
				currentPhoneInput
				.val( v.concat( $(this).text() ) ).focus();
			}
		}
		return false;
	});	

	/*
	 * 엑셀 추가 다이얼로그
	 * 확장자가 xls 이나 xlsx 경우만 true
	 */
	var excelWindow = null;
	$("#addExcel").click(function(){
		var url = "./excel.jsp";
		var title = "excel";
		var status = "toolbar=no, scrollbars=no, status=no, menubar=no, width=525, height=60, top=100, left=200";
		if( excelWindow != null ) { 	// 새창이 부모창 위에 보여주기 위함
			excelWindow.close();
		}
		excelWindow = window.open(url, title, status);
		excelWindow.focus();	
	});
	
	
	/**
	 * sms or mms 체크박스 처리
	 */
    $(".choice li input").click(function(){
    	var $this = $(this);
    	$this.siblings();
    	// 첫번째 클릭 즉 sms 선택이면
    	// 메시지가 있으면 지워준다.
    	if($this.index() == 0){
        	$.phone.smsMode = true;
        	$("#f_message").val("");	
        	$("#textByte").text("0/80Bytes" );        	
    	}else{	// mms 이면
        	$.phone.smsMode = false;
        	$("#f_message").val("");	
        	$("#textByte").text("0/2000Bytes" );
    	}
    });
    
    /**
     * 내 문자 혹은 특수 문자 영역 선택처리
     */
    $("#myMessageBox").click(function(){
    	/*
    	$(this).attr("src", "./images/lettersend/tab01_on.gif");
    	$("#specailCharBox").attr("src", "./images/lettersend/tab02_off.gif");    	
    	$(".my02").hide();    	
    	$(".my01").show();
    	*/
    });
    
    // sms mode or mms mode 전환
    $("[name='send_type']").click(function(event){
    	$.phone.smsMode =$("[name='send_type']:first").is(this);
    });
    
	// 전화번호 하이픈 넣기
	$(".hyphen").addHyphen();
    
    $("#specailCharBox").click(function(){
    	$(this).attr("src", "./images/lettersend/tab02_on.gif");
    	$("#myMessageBox").attr("src", "./images/lettersend/tab01_off.gif");    	    	
    	$(".my01").hide();    	
    	$(".my02").show();
    });
    
    // 예약 날짜
    $("#reserved_btn").click(function(){
    	$("#reserved_datetime").datetimepicker( "show" );
    });
    
    // datetime picker 로컬화처리 및 옵션 설정
    // datetime가 show할때 툴바 버튼을 겹쳐지므로 툴바 버튼을 숨겨주고
    // 다시 close할때 복구한다.
	$("#reserved_datetime").datetimepicker({
		hideIfNoPrevNextType : true,
		dateFormat : "yy-mm-dd",
		monthNames: [ "1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월" ],
		dayNamesType : ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"  ],
		dayNamesMin : ["일", "월", "화", "수", "목", "금", "토"  ],		
		prevText: "이전",
		nextText: "다음",
		closeText: "확인",		
		currentText : "현재",
		hourText : "시",
		minuteText : "분",
		timeText : "시간",
		beforeShow : function(){
			$("#toolbar").css("visibility", "hidden");
			$("#reserved_datetime").val("");
			//$("#reserved_datetime, #reserved_label").show();
		},
		onClose : function(dateText, inst ){
			$("#toolbar").css("visibility", "visible");
			if(dateText.length <=0){
				//$("#reserved_datetime,  #reserved_label").hide();
				
			}
		},
		onSelect : function(){
		//	$("#reserved_datetime").val();
		}
	});
	/*
	 * 특수문자 tab
	 * 우측 특수문자들 모음 기본 인덱스는 첫번째로 함
	 */
	$.html.character(0);
	/*
	setTimeout(function(){ // 스크립트 분할 로드
		$.html.messages("public", "all");	// 지연 스크립트-1 ( 공통 메시지  )
		setTimeout(function(){
			$('#dateTimePicker').load("./ajax/calendar/calendar.html", function(){// 지연 스크립트-2
				Defaults();
				setTimeout(function(){ $('.miniload').hide() }, 50);
			});
		},50);
	},50);
	*/
	
});