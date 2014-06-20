<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" href="<c:url value='/resources/css/lhgdialog.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/resources/css/loginPage.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/resources/css/ico.css'/>" rel="stylesheet" />

<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validity.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/Common.js'/>"></script>

<script type="text/javascript">
var webRootPath="${pageContext.request.contextPath}";
var LeftLiTdHeight=0;
//计算页面一些元素的高度和宽度
function SetBodySize(){
	$("#MianTable").css("width", $("body").width() < 1001?"1001px":"100%");
	$("#MianTable").css("height", $("body").width() < 500?"500px":"100%");
	$("#MianWork,#RightTd").css("height",($("#MianTable").height()-$("#MianTop").height()-$("#MianRoot").height()-$("#MianMid").height())+"px");
	$("#RightTd").css("width",($("#MianTable").width()-$("#CenterTd").width()-($("#MenuTd").css("display")=="none"?0:$("#MenuTd").width()))+"px");
	$("#IfromTd").css("height",($("#RightTd").height()-$("#RightTdMenu").height()-1)+"px");
	LeftLiTdHeight=$("#RightTd").height()-$(".menuList").height()*$(".menuList").length-4;
	$(".menuLi_Li").css("height",LeftLiTdHeight+"px");
}
$(document).ready(function () {
	SetBodySize();
    $(window).resize(function(){
	    SetBodySize();
    });
});
</script>
<!--[if IE 6]>
<script type="text/javascript" src="Js/Png.js"></script>
<script type="text/javascript">
EvPNG.fix(".png");
</script>
<![endif]-->
</head>
<body>
<div id="MianTable">

<!--顶部开始-->
<div class="topTd" id="MianTop">
	<div class="topTitle">管理系统框架</div>
	    <ul class="topIco"><li class="textLi userpng png"><a href="UserInfo.html">Administrator，您好</a></li><li class="splitLi2px"></li>
	    <li class="textLi managepng png"><a href="UserInfo.html">设置</a></li><li class="splitLi2px"></li>
	    <li class="textLi helppng png"><a href="Help.html">帮助</a></li><li class="splitLi2px"></li> <li class="textLi quitpng png"><a href="Login.html">退出</a></li>
	    </ul>
	</div>
<div class="midTd" id="MianMid"><img src="<c:url value='../resources/images/height5px.gif'/>" width="8" height="5" /></div>
<!--顶部结束-->

<!--中间工作区域开始-->
<div class="WorkMian" id="MianWork">
<!--左边导航开始-->
<div class="menuTd" id="MenuTd">
<!--导航菜单二开始-->
<div class="menuLi_Li" id="MenuTd_1">
<ul>
	<li class="DownLi">
		<a href="javascript:void(0)" class="DownA">库存管理</a>
		<ul>
			<li><a href="javascript:void(0)" onClick="AddMenu('登&nbsp;&nbsp;录','Login.html')">库存统计</a></li>
      	</ul>
	</li>
	<li class="DownLi">
		<a href="javascript:void(0)" class="DownA">系统管理</a>
		<ul>
			<li><a href="<c:url value='/admin/rawMaterial/list'/>">原料管理</a></li>
			<li><a href="<c:url value='/admin/specification/list'/>">规格管理</a></li>
			<li><a href="<c:url value='/admin/client/list'/>">客户管理</a></li>
      	</ul>
	</li>
</ul>
</div>
	
</div>
<!--左边导航结束-->

<!--切换左边导航开始-->
<div id="CenterTd" class="splitTd" onClick="DisPlayMenu()"></div>
<!--切换左边导航结束-->

<!--工作区域开始-->
<div id="RightTd">
	<!-- 路径开始 -->
	<div style="height: 30px;line-height: 30px;font-size: 12pt;background-color: #fff">
		<span>当前路径：首页</span>
	</div>
	<!-- 路径结束 -->
</div>
<!--工作区域结束-->
</div>
<!--中间工作区域结束-->

<!--底部版权区域区域开始-->
<div id="MianRoot">版权所有</div>
<!--底部版权区域区域结束-->
</div>
<script type="text/javascript">
/*左侧菜单开始*/
var ShowMenu=getCookie("ShowMenu");
function DisPlayMenu(){
	if(ShowMenu=="1"||ShowMenu==""){
		$("#CenterTd").addClass('splitTdOn');
		$("#MenuTd").toggle();
		ShowMenu="0";
	}else{
		$("#CenterTd").removeClass('splitTdOn');
		$("#MenuTd").toggle();
		ShowMenu="1";
	}
	setCookie("ShowMenu",ShowMenu);
    SetBodySize();
}
if(ShowMenu=="0"){
	$("#CenterTd").addClass('splitTdOn');
	$("#MenuTd").hide();
} 
/*左侧菜单结束*/
</script>
</body>
</html>