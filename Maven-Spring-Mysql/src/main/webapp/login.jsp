<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" href="<c:url value='resources/css/lhgdialog.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='resources/css/loginPage.css'/>" rel="stylesheet" />
<script type="text/javascript" src="<c:url value='resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='resources/js/jquery.validity.js'/>"></script>
<script type="text/javascript" src="<c:url value='resources/js/Common.js'/>"></script>
<!--[if IE 6]>
<script type="text/javascript" src="<c:url value='resources/js/Png.js'/>"></script>
<script type="text/javascript">
EvPNG.fix(".png");
</script>
<![endif]-->
<script type="text/javascript">
var webRootPath="${pageContext.request.contextPath}";
function ValidityThisPage(){
	$.validity.start();
	$("#username").require("用户必须填写！");
	$("#password").require("密码必须填写！");
	$.validity.end();
	return ValidityTrue;
}
$(document).ready(function () {
	$("#LoginDiv").css("padding-top",(($(document.body).height()-220)/2)+"px");
	$(window).resize(function(){
	    $("#LoginDiv").css("padding-top",((($(document.body).height()>=$("#LoginDiv").height()?$(document.body).height():$("#LoginDiv").height())-$("#LoginDiv").height())/2)+"px");
    });
});
</script>
</head>
<body>
<div id="LoginDiv">
<div class="top49px">用户登录</div>
<div class="textDiv">
<table border="0" cellspacing="1" cellpadding="1" id="loginTable">
  <tr>
    <td class="tdright">账&nbsp;&nbsp;号</td>
    <td><input name="username" id="username" type="text" /></td>
  </tr>
  <tr>
    <td class="tdright">密&nbsp;&nbsp;码</td>
    <td><input name="password" id="password" type="password" /></td>
  </tr>
  <tr>
    <td class="tdright">&nbsp;</td>
    <td class="aheight"><a href="<c:url value='/admin/index'/>" onclick="return ValidityThisPage();">登&nbsp;录</a></td>
  </tr>
</table>
</div>
<div class="bottom5px"></div>
</div>

<script type="text/javascript"> 
var showMsg=false;
if(!$.support["opacity"]){
	if(document.documentMode==undefined){
		showMsg=true;
	}
}
if(showMsg){
	$.dialog({
		id: "msg",
		title: '&nbsp;&nbsp;&nbsp;&nbsp;浏览器说明',
		content: '<div style="line-height:19px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果您是XP系统是，建议您升级IE8浏览器；如果是Win7系统，建议您升级为IE10。两种情况都可以直接使用Chrome浏览器。<div style="margin-top:3px;text-align:center;"><a href="setup/IE8_WindowsXP.exe" target="_blank" style="color:#F00">IE8下载(XP)</a>&nbsp;&nbsp;<a href="setup/IE10-Windows7.exe" target="_blank" style="color:#F00">IE10下载(Win7)</a>&nbsp;&nbsp;<a href="setup/ChromeSetup.exe" target="_blank" style="color:#F00">Chrome下载(通用)</a></div></div>',
		width: 350,
		height: 100,
		left: '100%',
		top: '100%',
		fixed: false,
		drag: false,
		resize: false,
		max: false,
		min: false,
		skin:'discuz'
	});
}
</script>
</body>
</html>