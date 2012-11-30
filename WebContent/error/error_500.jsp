 <%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>500 에러</title>
<style>
/* @group 404 */
body {background-color: #c8e1f6;  margin: 0 auto; padding: 0; }
div#error { position: absolute; bottom: 0; min-height: 573px; width: 100%; margin: 0; background: url(<%=request.getContextPath()%>/images/error/500error.png) no-repeat;}
* html div#error  { height: 573px; }
div#grass { position: absolute; bottom: 0; width: 55%; height: 140px; background: url(<%=request.getContextPath()%>/images/error/grass.png) repeat-x; left: 800px;  }
/* @end */
</style>
</head>
<body>
<div id="error">
<div id="grass">
</div>
</div>
</body>
</html>