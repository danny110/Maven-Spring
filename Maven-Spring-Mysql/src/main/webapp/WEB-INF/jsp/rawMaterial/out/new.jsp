<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/jquery.ui.css'/>"/>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/newAndView.css'/>"/>
<script type="text/javascript">
var webRootPath='${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.ui.datepicker-zh-CN.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDrag.js"/>"></script>
</head>
<body>
<!-- 表单开始 -->
<form id="vform" method="post">
	<table>
		<tr>
			<td class="td100"><span style="color: red;">*</span>原料：</td>
			<td class="td150">
				<select id="rawMaterialId" name="rawMaterialId" style="width: 150px;">
				<option value=""></option>
				<c:forEach var="raw" items="${rawMaterials }">				
					<option value="${raw.id }" svalue="${raw.specification }"  sunits="${raw.units.toString() }">${raw.name }（规格：${raw.specification }）</option>
				</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td100">规格：</td>
			<td class="td150"><input id="specification" type="text" class="input" disabled="disabled" /></td>
		</tr>
		<tr>
			<td class="td100">数量：</td>
			<td class="td150"><input id="num" name="num" class="input"/></td>
		</tr>
		<tr>
			<td class="td100">单位：</td>
			<td class="td150"><input id="units" name="units" class="input" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td100"><span style="color: red;">*</span>出库日期：</td>
			<td class="td150"><input id="outDate" name="outDate" class="input"/></td>
		</tr>
		<tr>
			<td class="td100">备注：</td>
			<td class="td150"><textarea id="mark" name="mark" rows="" cols=""  style="width: 144px;">暂无备注</textarea></td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input id="btn_add" name="btn_add" type="button" value="提交"/>
				<input id="btn_cancel" name="btn_cancel" type="button" value="取消" />
			</td>
		</tr>
	</table>
</form>
<!-- 表单结束 -->
<script type="text/javascript">
var $rawMaterialId = $("#rawMaterialId");
var $specification = $("#specification");
var $num = $("#num");
var $units = $("#units");
var $outDate = $("#outDate");
var $mark = $("#mark");

$(function() {
	
	$rawMaterialId.change(function() {
		$specification.val($(this).find("option:selected").attr("svalue"));
		$units.val($(this).find("option:selected").attr("sunits"));
	});
	
	$( "#outDate" ).datepicker();
	
	/*提交*/
	$("#btn_add").click(function() {
		if ($rawMaterialId.val() == "") {
			alert("原料不能为空！");
			return;
		}
		if ($num.val() == "") {
			alert("数量不能为空！");
			return;
		}
		if ($outDate.val() == "") {
			alert("出库日期不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/rawMaterial/out/add'/>",
    		data: {
    			rawMaterialId : $rawMaterialId.val(),
    			num : $num.val(),
    			outDate : $outDate.val(),
    			mark : $mark.val()
    		},
    		type: "POST",
    		dataType: "json",
    		success: function(data) {
    			if( data.isSuccess ){
    				 Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);
    			}else {
    				alert(data.errorReason);
					return false;
    			}
    	 	 },error : function() {
		    	alert("操作失败，请联系管理员！");
				return false;
			}
		});
    });
	
	/*取消*/
	$("#btn_cancel").click(function() {
        Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);
    });
});
</script>
</body>
</html>