<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/home.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jquery.ui.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/Pager.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/managePage.css'/>"/>

<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.datepicker-zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.inspager.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/Common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/ManageAction.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/baseJS.js'/>"></script>
<script type="text/javascript">
var webRootPath='${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value="/resources/js/zDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDrag.js"/>"></script>
</head>
<body>
<!-- 表单开始 -->
<form id="vform" method="POST" action="">
	<div id="ButtonDiv">
		<table border="0" cellspacing="0" cellpadding="0" id="SearchTable">
			<tr>
				<td>
					<label for="companyName">单位名称:</label>
					<input type="text" name="companyName" id="companyName" value="${companyName }" class="inputtext" title="请输入单位名称" />
				</td>
				<td>
					<label for="name">联系人:</label>
					<input type="text" name="name" id="name" value="${name }" class="inputtext" title="请输入联系人" />
				</td>
				<td>
					<select name="enabled" class="selectclass">
						<option value="" <c:if test="${enabled == '' }">selected="selected"</c:if>>-请选择类型-</option>
						<option value="true" <c:if test="${enabled == 'true' }">selected="selected"</c:if>>启用</option>
						<option value="false" <c:if test="${enabled == 'false' }">selected="selected"</c:if>>禁用</option>
					</select>
				</td>
				<td>
					<input name="" type="image" src="<c:url value='/resources/images/image_07.gif'/>"/>
				</td>
 				</tr>
		</table>
		<div class="buttonTop"><img src="<c:url value='/resources/images/height5px.gif'/>" width="8" height="5" /></div>
		<table border="0" cellspacing="0" cellpadding="0" class="bottonlist">
			<tr>
				<td>
					<img src="<c:url value='/resources/images/mian/menu_left.png'/>" width="3" height="33" />
				</td>
				<td class="ButtonTd">
					<a id="new" href="javascript:void(0);" class="ButtonAll bottonAdd png">增加</a>
				</td>
				<td class="ButtonTd">
					<a id="batchFalse" href="javascript:void(0);" class="ButtonAll bottonCheck png">批量禁用</a>
				</td>
				<td class="ButtonTd">
					<a id="batchTrue" href="javascript:void(0);" class="ButtonAll bottonLock png">批量启用</a>
				</td>
				<td class="ButtonTd TdLast">
					<a id="batchDel" href="javascript:void(0);" class="ButtonAll bottonDel png">批量删除</a>
				</td>
   				<td>
   					<img src="<c:url value='/resources/images/mian/menu_right.png'/>" width="3" height="33" />
   				</td>
			</tr>
		</table>
	</div>
	<div class="ListTable">
		<table width="100%" border="0" cellspacing="1" cellpadding="0" id="ListTable">
			<tr class="title">
				<td style="width:32px;"><input name="SelectCK" type="checkbox" onclick="SelectAll(this)"/></td>
				<td style="width:150px;">单位名称</td>
				<td style="width:80px;">联系人</td>
				<td style="width:100px;">手机号码</td>
				<td style="width:100px;">座机号码</td>
				<td style="min-width:150px;">备注</td>
				<td style="width:80px;">是否启用</td>
				<td style="width:150px;">创建时间</td>
				<td style="width:50px;">操作</td>
			</tr>
			<c:forEach var="row" items="${ResultJson.rows }">
			<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')" ondblclick="checkedInfo(this)">
				<td><input name="SelectID" type="checkbox" value="${row.id }"/></td>
				<td>${row.companyName }</td>
				<td>${row.name }</td>
				<td>${row.phone }</td>
				<td>${row.telephone }</td>
				<td class="tdleft">${row.mark }</td>
				<td>${row.enabled == true ? "启用" : "禁用" }</td>
				<td>${row.createDate }</td>
				<td>
					<a href="javascript:void(0);" onclick="view('${row.id }');">查看</a>
					<%-- &nbsp;
					<a href="javascript:void(0);" onclick="edit('${row.id }');">编辑</a> --%>
				</td>
			</tr>
			</c:forEach>
			<tr onmouseover="$(this).addClass('MouseOn')" onmouseout="$(this).removeClass('MouseOn')">
				<td colspan="10" id="InsPageDiv">&nbsp;</td>
			</tr>
		</table>
	</div>
	<input type="hidden" id="page" name="page" value="${ResultJson.page }"/>
	<input type="hidden" id="size" name="size" value="${ResultJson.size }"/>
</form>
<!-- 表单结束 -->
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
	document.getElementById("vform").action="<c:url value='/admin/sys/client/list'/>";
	document.getElementById("vform").submit();
};
$("#InsPageDiv").pager({ pagenumber: pageindex, pagecount: pagecount, numcount: numcount, buttonClickCallback: PageClick });

$(function () {
// 批量禁用
$("#batchFalse").click(function() {
	var ids = getSelectIds();
	if (ids == "") {
		alert("请选择需要禁用的数据行！");
		return;
	}
	if (confirm("确定要禁用吗？")) {
		$.ajax({
			url: "<c:url value='/admin/sys/client/enabled'/>",
    		data: {
    			ids : ids,
    			enabled : false
    		},
    		type: "POST",
    		dataType: "json",
    		success: function(data) {
    			if( data.isSuccess ){
    				location.reload();
    			}else {
    				alert(data.errorReason);
					return false;
    			}
    	 	 },error : function() {
		    	alert("操作失败，请联系管理员！");
				return false;
			}
		});
	};
});

// 批量启用
$("#batchTrue").click(function() {
	var ids = getSelectIds();
	if (ids == "") {
		alert("请选择需要启用的数据行！");
		return;
	}
	if (confirm("确定要启用吗？")) {
		$.ajax({
			url: "<c:url value='/admin/sys/client/enabled'/>",
    		data: {
    			ids : ids,
    			enabled : true
    		},
    		type: "POST",
    		dataType: "json",
    		success: function(data) {
    			if( data.isSuccess ){
    				location.reload();
    			}else {
    				alert(data.errorReason);
					return false;
    			}
    	 	 },error : function() {
		    	alert("操作失败，请联系管理员！");
				return false;
			}
		});
	};
});

// 批量删除
$("#batchDel").click(function() {
	var ids = getSelectIds();
	if (ids == "") {
		alert("请选择需要删除的数据行！");
		return;
	}
	if (confirm("确定要删除吗？")) {
		$.ajax({
			url: "<c:url value='/admin/sys/client/del'/>",
    		data: {
    			ids : ids
    		},
    		type: "POST",
    		dataType: "json",
    		success: function(data) {
    			if( data.isSuccess ){
    				location.reload();
    			}else {
    				alert(data.errorReason);
					return false;
    			}
    	 	 },error : function() {
		    	alert("操作失败，请联系管理员！");
				return false;
			}
		});
	};
});
});

//获取被选中 ID
function getSelectIds() {
var ids = "";
$("input[name=SelectID]:checked").each(function() {
	if (ids != "") ids += ",";
	ids += $(this).val();	
});
return ids;
};

//选中
function checkedInfo(node) {
	var $input = $(node).find("input[name=SelectID]");
	if ($input.attr("checked") == "checked") {
		$input.removeAttr("checked");
	} else {
		$input.attr("checked", "checked");
	};
};

//新增客户
$("#new").click(function() {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 250;
	diag.URL = '<c:url value="/admin/sys/client/new"/>';
	diag.Title = "新增";
	diag.CancelEvent = function () {
		diag.close();
		location.reload();
	};
	diag.show();
});

//浏览客户
function view(id) {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 250;
	diag.URL = '<c:url value="/admin/sys/client/view-' + id + '"/>';
	diag.Title = "浏览";
	diag.CancelEvent = function () {
		diag.close();
	};
	diag.show();
};

//编辑客户
function edit(id) {
	var diag = new Dialog();
	diag.Width = 700;
	diag.Height = 250;
	diag.URL = '<c:url value="/admin/sys/client/edit-' + id + '"/>';
	diag.Title = "编辑";
	diag.CancelEvent = function () {
		diag.close();
		location.reload();
	};
	diag.show();
};
</script>
</body>
</html>