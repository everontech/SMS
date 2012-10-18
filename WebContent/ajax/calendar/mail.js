//인픗박스 글씨 사라지게
	function clearText(y){
	if (y.defaultValue==y.value)
	y.value = ""
	}

//faq 테이블
var fcmzt        =   0;
var fctFlagt     =   "";
function faqDisplay(cstrt, cdflagt, faqId)
{  
	cmnum = cstrt.substring(cstrt.length - 2);
	cmstr = cstrt.substring(0, cstrt.length - 2);

	// Browser type : Explore 
	if (cdflagt == "0" & document.getElementById(cmstr+cmnum).style.display=="")
	{  return;     }
	else 
	{ 
		if(document.getElementById(cmstr+cmnum).style.display=="")
		{ 
			document.getElementById(cmstr+cmnum).style.display="none";
			fctFlagt = eval("txt" + cmnum);
			fctFlagt.style.fontWeight = "normal";
			fctFlagt.style.color = "#636363";   
			fcmzt=0;  
		}
		else 
		{
			fctFlagt = eval("txt" + cmnum);
			fctFlagt.style.fontWeight = "normal";
			fctFlagt.style.color = "#f56824";
			document.getElementById(cmstr+cmnum).style.display="";
			fcmzt=cmnum; 
		}
	}
}	



/* open-close script */
var oldObj = "";
function openClose(name)
{
	var objOc = document.getElementById(name);
	
	if( oldObj != objOc )
	{
		if(oldObj != "")
		{
			oldObj.style.display = "none";
		}
		objOc.style.display = "";
		oldObj = objOc;
	}
	else
	{
		if(objOc.style.display == "none")
		{
			objOc.style.display = "";
		}
		else if(objOc.style.display == "")
		{
			objOc.style.display = "none";
		}
		oldObj = "";
	}
}