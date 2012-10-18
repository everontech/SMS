


//sms 자리수 제한
var maxLen=80; //Defult
var contentId, numId;
var alert80=false;

function setConfig(num, id1, id2){
	maxLen = parseInt(num);
	contentId = id1;
	numId = id2;
}
function cal_pre(){
	var tmpStr;
	tmpStr = document.getElementById(contentId).value; // <<---- error
	cal_byte(tmpStr);
	saveCurrentPos(document.getElementById(contentId));
}

var tcount;
function cal_byte(aquery) {
	var tmpStr;
	var temp=0;
	var onechar;

	tcount = 0;

	tmpStr = new String(aquery);
	temp = tmpStr.length;

	for (k=0;k<temp;k++)
	{
			onechar = tmpStr.charAt(k);
			if (escape(onechar) =='%0D') {
				//onechar이  Enter경우 escape(onechar) ==> IE는 '%0D', '%0A' 2번 입력되고, 파폭의 경우 '%0A' 1변 입력 됨
				continue;
				//tcount++;
			} else if(escape(onechar) == '%0A'){
				tcount += 2;
			} else if (escape(onechar).length > 4) {
				tcount += 2; 
			} else {
				tcount++;
			}
	}


	if(adverMessage.length > 0)
	{
		if(tcount>=60)
		{
			if(document.getElementById("adver") != null)
			{
				document.getElementById("adver").checked = false;
			}
		}
		else
		{
		}
	}

	if (tcount>80) {
		document.getElementById(numId).innerHTML = tcount+"/160 Byte";
	}
	else {
		document.getElementById(numId).innerHTML = tcount+"/80 Byte";
	}
	if(tcount>maxLen) {
			reserve = tcount-maxLen;
			alert(maxLen+"byte 이상은 입력 하실수 없습니다. 쓰신 내용은 "+reserve+"byte가 초과되었습니다. 초과된 부분은 자동으로 삭제됩니다.");
			cutText();
	return;
	}
	if (tcount > 80 && alert80 == false) {
		alert("81Byte 부터 입력하신 내용은 \nSMS 2건으로 나누어 전송되게 됩니다.");
		alert80 = true;
	}

}

function cutText(){
	nets_check( document.getElementById(contentId).value);
}

function nets_check(aquery){

	var tmpStr;
	var temp=0;
	var onechar;
	var tcount;
	tcount = 0;

	tmpStr = new String(aquery);
	temp = tmpStr.length;
	for(k=0;k<temp;k++)
	{
			onechar = tmpStr.charAt(k);

			if (escape(onechar) =='%0D') {
				tcount++; 
			} 
			else if(escape(onechar).length > 4) {
					tcount += 2;
			} else {
					if(escape(onechar)=='%0A') {
							tcount++;
					} else {
							tcount++;
					}
			}

			if(tcount>maxLen) {
					tmpStr = tmpStr.substring(0,k);
					break;
			}

	}
	document.getElementById(contentId).value = tmpStr;
	cal_byte(tmpStr);

}

//select box layer close
function closeSelect(e){
	if ( navigator.appName == "Microsoft Internet Explorer" ){ //ie
		var obj = document.getElementsByTagName("DIV");
		if(e.srcElement.parentNode.parentNode.className!="select_box"){
			for(var i=0; i<obj.length; i++){
				if(obj[i].className == "select_box"){
					obj[i].getElementsByTagName("DL")[0].style.display = "none";
				}
			}
		}
	}else if ( navigator.appName == "Netscape" ){//fire fox
		var obj = document.getElementsByTagName("DIV");
		if(e.target.parentNode.parentNode.className!="select_box"){
			for(var i=0; i<obj.length; i++){
				if(obj[i].className == "select_box"){
					obj[i].getElementsByTagName("DL")[0].style.display = "none";
				}
			}
		}
	}


}

//특수문자 애니메이션 레이어 js모듈
var layerObj;
function classChange(id, obj){

	layerObj = document.getElementById(id);

	if(obj.className == "on"){ //close -> open
		obj.className = "";
		aniLayer('open');
	}else{//open -> close
		obj.className = "on";
		aniLayer('close');
	}
}
function aniLayer(type){
	if(type=="open"){
		if(parseInt(layerObj.style.left) < 191){
			layerObj.style.left = (parseInt(layerObj.style.left) +24) +"px";
			setTimeout("aniLayer('open');", 100);
		}
	}else if(type=="close"){
		if(parseInt(layerObj.style.left) <= 191 && parseInt(layerObj.style.left) > 71){
			layerObj.style.left = (parseInt(layerObj.style.left) -24) +"px";
			setTimeout("aniLayer('close');", 100);
		}
	}
}
var checkFirstClick = false; // 처음 클릭되는 시점 체크

function saveCurrentPos(objTextArea) {
    if (objTextArea.createTextRange) {
	    objTextArea.currentPos = document.selection.createRange().duplicate();
	}

	if(!checkFirstClick) {
		if(objTextArea.value == defaultMessage) {
		    objTextArea.value = "";
		}

		checkFirstClick=true;
	}
}
function insertText (objId, text) {
    var objText = document.getElementById(objId);

	if (objText.createTextRange && objText.currentPos) {
         var currentPos = objText.currentPos;

         currentPos.text =
           currentPos.text.charAt(currentPos.text.length - 1) == ' ' ?
             text + ' ' : text;
    } else {
		if(objText.value == defaultMessage) {
		    objText.value  = text;
			
		} else {
			objText.value  = objText.value + text;
		}
	}
	objText.focus();
}
//layerOverAction
function thumImgAction(obj, type){
	var viewLayer = obj.parentNode.getElementsByTagName("DIV")[0];
	if(type){
		viewLayer.style.display = "block";
	}else{
		viewLayer.style.display = "none";
	}
}

function layerAction(id){
	var obj = document.getElementById(id);
	if(obj.style.display =="none")  obj.style.display = "block";
	else if(obj.style.display =="block")  obj.style.display = "none";
}
function emoticonTab(obj, tabId, layerId){
	var idx;
	var tmpCount=0;
	var tabObj = document.getElementById(tabId).getElementsByTagName("A");
	var layerObj = document.getElementById(layerId).childNodes;
	for(var i=0; i<tabObj.length;i++){
		if(tabObj[i] == obj){
			idx=i;
			obj.className = "on";
		}else{
			tabObj[i].className = "off";
		}
	}

	for(var i=0; i<layerObj.length;i++){
		if(layerObj[i].tagName == "DIV"){
			if(tmpCount == idx){
				layerObj[i].style.display="block";
			}else{
				layerObj[i].style.display="none";
			}
		tmpCount++;
		}
	}
}
function emoticonTab2(obj, tabId, layerId, phoneId){
	var idx;
	var tmpCount=0;
	var tabObj = document.getElementById(tabId).getElementsByTagName("A");
	var layerObj = document.getElementById(layerId).childNodes;
	var phoneObj = document.getElementById(phoneId).childNodes;
	for(var i=0; i<tabObj.length;i++){
		if(tabObj[i] == obj){
			idx=i;
			obj.className = "on";
		}else{
			tabObj[i].className = "";
		}
	}

	for(var i=0; i<layerObj.length;i++){
		if(layerObj[i].tagName == "DIV"){
			if(tmpCount == idx){
				layerObj[i].style.display="block";
			}else{
				layerObj[i].style.display="none";
			}
		tmpCount++;
		}
	}
	tmpCount = 0;
	for(var i=0; i<phoneObj.length;i++){
		if(phoneObj[i].tagName == "DIV"){
			if(tmpCount == idx){
				phoneObj[i].style.display="block";
			}else{
				phoneObj[i].style.display="none";
			}
		tmpCount++;
		}
	}
}
function setEmoticonAction(id){
	var obj = document.getElementById(id).getElementsByTagName("A");

	for(var i=0; i<obj.length;i++){
		obj[i].onclick = function(){
			insertText('JsSmsArea', this.innerHTML);
			cal_pre();
		}
	}

}


//* select box *//
function styleSelectBox(){
	this.objId = "";
	this.divId = "";
	this.tmpSelectValue = "";
	this.hiddenObjId = "";
	this.firstName = "";

	this.aryItem = new Array();
	this.aryItem[0] = new Array();
	this.aryItem[1] = new Array();

	this.itemCount = 0;

	this.init = function(){
		this.divId = arguments[0];
       	this.objId = arguments[1];
		this.tmpSelectValue = arguments[2];
		this.hiddenObjId = arguments[3];
		this.firstName = arguments[4];
	};
	this.draw = function(){
		var innerVal = '<div class="select_box" >';
		if(parseInt(this.itemCount)>0){
		 	innerVal += '<div class="alnk"><a href="###" onclick="'+this.objId+'.layerAction(this);">'+this.firstName+'</a></div>';
			innerVal += '<dl style="display:none">' ;
	        innerVal += '<input type="hidden" name="'+this.hiddenObjId+'" id="'+this.hiddenObjId+'" value="'+this.tmpSelectValue+'" />';

	         for(var i=0; i< parseInt(this.itemCount); i++){
	             innerVal += '<dd onclick="'+this.objId+'.selectAction(this);" onmouseover="'+this.objId+'.mouseAction(this);" value="'+this.aryItem[1][i]+'">'+this.aryItem[0][i]+'</dd>';
	         }
	     }
	     innerVal += '</dl></div>';
	     document.getElementById(this.divId).innerHTML = innerVal;

	};
	this.addItems = function(){
		this.aryItem[0][this.itemCount] = arguments[0];
		this.aryItem[1][this.itemCount] = arguments[1];
		this.itemCount ++;
	};
	this.layerAction = function(){
		var obj = document.getElementById(this.divId);


		if(obj.getElementsByTagName("DL")[0].style.display == "none"){
			obj.getElementsByTagName("DL")[0].style.display = "block";
		}else{
			obj.getElementsByTagName("DL")[0].style.display = "none";
		}
	};
	this.selectAction = function(){
		var titleObj = document.getElementById(this.divId).getElementsByTagName("A")[0];
		var selectTitle = arguments[0].innerHTML;

		titleObj.innerHTML = selectTitle;
		arguments[0].parentNode.getElementsByTagName("INPUT")[0].setAttribute("value", arguments[0].getAttribute("value"));
		for(var i=0; i < arguments[0].parentNode.getElementsByTagName("DD").length; i++){
			if(arguments[0].parentNode.getElementsByTagName("DD")[i].className=="on"){
				arguments[0].parentNode.getElementsByTagName("DD")[i].className = "";
				break;
			}
		}
		arguments[0].className = "on";
		this.layerAction();
	};
	this.setSelected = function(){
		var TitleObj = document.getElementById(this.divId).getElementsByTagName("A")[0];
		var LayerObj = document.getElementById(this.divId).getElementsByTagName("DL")[0];
		var setValue = LayerObj.getElementsByTagName("INPUT")[0].getAttribute("value");

		if(setValue){
			for(var i=0; i<LayerObj.getElementsByTagName("DD").length; i++){
				if(LayerObj.getElementsByTagName("DD")[i].getAttribute("value") == setValue){
					LayerObj.getElementsByTagName("DD")[i].className="on";
					TitleObj.innerHTML = LayerObj.getElementsByTagName("DD")[i].innerHTML;
					break;
				}
			}
		}
	};
	this.mouseAction = function(){
		var parentObj = document.getElementById(this.divId).getElementsByTagName("DD");
		for(var i=0; i < parentObj.length; i++){
			if(parentObj[i].className == "on"){
				parentObj[i].className="";
				break;
			}
		}
		arguments[0].className="on";
	};

}

// 0728 main 추가
function IconLayer(obj, type){
				
	if(type == "out"){
		obj.setAttribute("src", obj.getAttribute("src").replace("over","off"));
		obj.parentNode.parentNode.getElementsByTagName("DIV")[0].style.display ="none";
	}else if(type == "over"){
		obj.setAttribute("src", obj.getAttribute("src").replace("off","over"));
		obj.parentNode.parentNode.getElementsByTagName("DIV")[0].style.display ="block";
	}
	
}
