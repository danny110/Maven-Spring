<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value="/resources/css/jquery.ui.css"/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/jqGrid/css/ui.jqgrid.css'/>"/>
<link type="text/css" href="<c:url value='/resources/css/lhgdialog.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/resources/css/loginPage.css'/>" rel="stylesheet" />
<link type="text/css" href="<c:url value='/resources/css/ico.css'/>" rel="stylesheet" />

<script type="text/javascript" src="<c:url value='/resources/jqGrid/js/jquery-1.9.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/jqGrid/js/jquery.jqGrid.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.validity.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/jquery.ui.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/jqGrid/js/i18n/grid.locale-cn.js"/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/Common.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/baseJS.js'/>"></script>
<script type="text/javascript">
var webRoot='${pageContext.request.contextPath}';
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
	<!-- 路径开始 -->
	<div style="height: 30px;line-height: 30px;font-size: 12pt;background-color: #fff">
		<span>当前路径：系统管理 - 库存统计</span>
	</div>
	<!-- 路径结束 -->
	<!-- jqGrid 开始 -->
	<table id="list"></table>
    <div id="pager"></div>
	<!-- jqGrid 结束 -->
</div>
<!--工作区域结束-->
</div>
<!--中间工作区域结束-->

<!--底部版权区域区域开始-->
	<%@include file="../../framework/footer.jsp"%>
<!--底部版权区域区域结束-->
</div>
<script type="text/javascript">
 	/*jqGrid 开始*/
	$(function () {
		$("#list").jqGrid({
			url: "<c:url value = '/admin/repertory/over/data'/>",
			colNames: ["id", "原料", "规格", "单位", "入库", "出库", "库存", "操作"],
			colModel: [
				{name: "rawMaterialId", index: "id", hidden: true, key: true},
				{name: "rawMaterialName", width: 100},
				{name: "specification", width: 100},
				{name: "units", width: 100},
				{name: "inNum", width: 100},
				{name: "outNum", width: 100},
				{name: "overNum", width: 100},
				{name: "id", width: 100, sortable:false, formatter:operateFormatter},
			],
		    sortname: 'rawMaterialId',
		    datatype: 'json',
	        mtype: 'GET',
	        hidegrid: false,
	        rownumbers: true,
	        autowidth: true,
	        rowNum: 10,
	        rowList: [10, 15, 20],
	        sortorder: 'desc',
	        viewrecords: true,
	        pager: '#pager',
	        height: 'auto'
		}).jqGrid('navGrid', '#pager', {edit: false, add: false, del: false, search: false});
		
	});
 
	function operateFormatter(cellvalue, options, rowObject){
	    return "";
	};
	/*jqGrid 结束*/
	
</script>
</body>
</html>