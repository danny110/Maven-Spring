<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/lhgdialog.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/loginPage.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jquery.validity.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/lhgcalendar.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/Pager.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/managePage.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/ico.css'/>"/>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validity.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.inspager.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/Common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/ManageAction.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/baseJS.js'/>"></script>
<script type="text/javascript">
var webRootPath='${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value="/resources/js/zDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDrag.js"/>"></script>

<script type="text/javascript">
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
</head>
<body>
<div id="MianTable">

<!--顶部开始-->
	<%@include file="../../framework/header.jsp"%>
<!--顶部结束-->

<!--中间工作区域开始-->
<div class="WorkMian" id="MianWork">
<!--左边导航开始-->
<div class="menuTd" id="MenuTd">
	<%@include file="../../framework/left.jsp"%>	
</div>
<!--左边导航结束-->

<!--切换左边导航开始-->
<div id="CenterTd" class="splitTd" onClick="DisPlayMenu()"></div>
<!--切换左边导航结束-->

<!--工作区域开始-->
<div id="RightTd">
	<!-- 表单开始 -->
	<form id="vform" method="POST" action="">
		<div id="ButtonDiv" style="height: 36px;">
			<table border="0" cellspacing="0" cellpadding="0" id="SearchTable">
				<tr>
					<td>
						<label for="rawMaterialName">原料名称：</label>
						<input type="text" name="rawMaterialName" id="rawMaterialName" value="${rawMaterialName }" class="inputtext" title="请输入原料名称" />
					</td>
					<td>
						<input name="" type="image" src="<c:url value='/resources/images/image_07.gif'/>"/>
					</td>
  				</tr>
			</table>
			<div class="buttonTop"><img src="<c:url value='/resources/images/height5px.gif'/>" width="8" height="5" /></div>
		</div>
		<div class="ListTable">
			<table width="100%" border="0" cellspacing="1" cellpadding="0" id="ListTable">
				<tr class="title">
					<td style="width:32px;"><input name="SelectCK" type="checkbox" onclick="SelectAll(this)"/></td>
					<td style="width:80px;">原料</td>
					<td style="width:80px;">规格</td>
					<td style="width:80px;">进货数量</td>
					<td style="width:80px;">出库数量</td>
					<td style="width:80px;">库存数量</td>
					<td style="width:50px;">单位</td>
					<td style="width:150px;">操作</td>
				</tr>
				<c:forEach var="row" items="${ResultJson.rows }">
				<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')" ondblclick="checkedInfo(this)">
					<td><input name="SelectID" type="checkbox" value="${row.rawMaterialId }"/></td>
					<td>${row.rawMaterialName }</td>
					<td>${row.specification }</td>
					<td>${row.inNum }</td>
					<td>${row.outNum }</td>
					<td>
					<c:if test="${row.overNum < 0}"><span style="color: red;"></c:if>
					${row.overNum }
					<c:if test="${row.overNum < 0}"></span></c:if>
					</td>
					<td>${row.units }</td>
					<td>
						<a href="<c:url value='/admin/repertory/over/inView-${row.rawMaterialId }'/>" target="inView">进货明细</a>&nbsp;
						<a href="<c:url value='/admin/repertory/over/outView-${row.rawMaterialId }'/>" target="outView">出库明细</a>
					</td>
				</tr>
				</c:forEach>
				<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')">
					<td colspan="9" id="InsPageDiv">&nbsp;</td>
				</tr>
			</table>
		</div>
		<input type="hidden" id="page" name="page" value="${ResultJson.page }"/>
		<input type="hidden" id="size" name="size" value="${ResultJson.size }"/>
	</form>
	<!-- 表单结束 -->
</div>
<!--工作区域结束-->
</div>
<!--中间工作区域结束-->

<!--底部版权区域区域开始-->
	<%@include file="../../framework/footer.jsp"%>
<!--底部版权区域区域结束-->
</div>
<script type="text/javascript">
var pagenum=${ResultJson.size }; //一页多少条
var pageindex=${ResultJson.page }; //当前页
var pagecount=${ResultJson.total }; //总页数
var numcount=${ResultJson.records }; //总个数
$("#ListTable tr:even").addClass("Single");

var PageClick = function (pageclickednumber) {
	// 赋值
	$("#page").val(pageclickednumber);
	$("#size").val(pagenum);
	document.getElementById("vform").action="<c:url value='/admin/repertory/over/list'/>";
	document.getElementById("vform").submit();
};
$("#InsPageDiv").pager({ pagenumber: pageindex, pagecount: pagecount, numcount: numcount, buttonClickCallback: PageClick }); 


//选中
function checkedInfo(node) {
	var $input = $(node).find("input[name=SelectID]");
	if ($input.attr("checked") == "checked") {
		$input.removeAttr("checked");
	} else {
		$input.attr("checked", "checked");
	};
};
</script>
</body>
</html>