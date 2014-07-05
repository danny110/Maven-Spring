<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jquery.ui.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/managePage.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/view.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/Pager.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jquery.validity.css'/>"/>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.datepicker-zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validity.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.inspager.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/Common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/ManageAction.js'/>"></script>
</head>
<body>
<form id="vform" method="POST" action="">
<div id="ButtonDiv">
	<table border="0" cellspacing="0" cellpadding="0" id="SearchTable" class="search">
		<tr>
			<td>
				<label for="loginCode">经手人：</label>
				<input type="text" name="loginCode" id="loginCode" value="${loginCode }" class="inputtext" style="width: 120px;" title="请输入经手人" />
			</td>
			<td>
				<label for="beginTime">开始时间：</label>
				<input type="text" name="beginTime" id="beginTime" value="${beginTime }" class="inputtext" style="width: 120px;" title="请输入开始时间" />
			</td>
			<td>
				<label for="endTime">结束时间：</label>
				<input type="text" name="endTime" id="endTime" value="${endTime }" class="inputtext" style="width: 120px;" title="请输入结束时间" />
			</td>
			<td>
				<select name="enabled" class="selectclass">
					<option value="" <c:if test="${enabled == '' }">selected="selected"</c:if>>-请选择类型-</option>
					<option value="true" <c:if test="${enabled == 'true' }">selected="selected"</c:if>>模糊查询</option>
					<option value="false" <c:if test="${enabled == 'false' }">selected="selected"</c:if>>直接查询</option>
				</select>
			</td>
			<td>
				<input name="" type="image" src="<c:url value='/resources/images/image_07.gif'/>"/>
			</td>
		</tr>
	</table>
	<div class="buttonTop"><img src="<c:url value='/resources/images/height5px.gif'/>" width="8" height="5" /></div>
	<div class="info">原料：${rawMaterial.name }（规格：${rawMaterial.specification }）&nbsp;&nbsp;单位：${rawMaterial.units.toString() }&nbsp;&nbsp;出库数量合计：${sum.nums }</div>
</div>
<div class="ListTable">
	<table width="100%" border="0" cellspacing="1" cellpadding="0" id="ListTable">
		<tr class="title">
			<td style="width:32px;"><input name="SelectCK" type="checkbox" onclick="SelectAll(this)"/></td>
			<td style="width:80px;">出库数量</td>
			<td style="width:80px;">经手人</td>
			<td style="width:100px;">出库日期</td>
			<td style="min-width: 150px;">备注</td>
		</tr>
		<c:forEach var="row" items="${ResultJson.rows }">
		<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')" ondblclick="checkedInfo(this)">
			<td><input name="SelectID" type="checkbox" value="${row.id }"/></td>
			<td>${row.num }</td>
			<td>${row.loginCode }</td>
			<td>${row.outDate }</td>
			<td>${row.mark }</td>
		</tr>
		</c:forEach>
		<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')">
			<td colspan="12" id="InsPageDiv">&nbsp;</td>
		</tr>
	</table>
</div>
<input type="hidden" id="page" name="page" value="${ResultJson.page }"/>
<input type="hidden" id="size" name="size" value="${ResultJson.size }"/>
</form>
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
	document.getElementById("vform").action="<c:url value='/admin/repertory/over/outView-${rawMaterialId }'/>";
	document.getElementById("vform").submit();
};
$("#InsPageDiv").pager({ pagenumber: pageindex, pagecount: pagecount, numcount: numcount, buttonClickCallback: PageClick }); 

$(function() {
	$( "#beginTime" ).datepicker({
		onSelect:function(dateText,inst){
			$("#endTime").datepicker("option","minDate",dateText);
			}
	});
	$( "#endTime" ).datepicker({
		onSelect:function(dateText,inst){
			$("#beginTime").datepicker("option","maxDate",dateText);
			}
	});
});
</script>
</body>
</html>