<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="topTd" id="MianTop">
	<div class="topTitle">管理系统</div>
	    <ul class="topIco">
	    	<li class="textLi userpng png"><a href="javascript:void(0);"><%=request.getSession().getAttribute("_LOGINCODE") %>，您好</a></li>
	    	<li class="splitLi2px"></li>
	   		<li class="textLi helppng png"><a href="javascript:void(0);">帮助</a></li>
	   		<li class="splitLi2px"></li> <li class="textLi quitpng png"><a href="<c:url value='/admin/logout'/>">退出</a></li>
	    </ul>
	</div>
<div class="midTd" id="MianMid"><img src="<c:url value='/resources/images/height5px.gif'/>" width="8" height="5" /></div>
</body>
</html>