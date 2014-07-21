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
			<td class="td100"><span style="color: red;">*</span>进货单位：</td>
			<td class="td150">
				<select id="clientId" name="clientId" style="width: 150px;">
					<option value=""></option>
					<c:forEach var="raw" items="${client }">				
					<option value="${raw.id }">${raw.companyName }</option>
					</c:forEach>
				</select>
			</td>
			<td class="td100"><span style="color: red;">*</span>原料：</td>
			<td class="td150">
				<select id="rawMaterialId" name="rawMaterialId" style="width: 150px;">
				<option value=""></option>
				<c:forEach var="raw" items="${rawMaterials }">
					<option value="${raw.id }" svalue="${raw.specification }" sunits="${raw.units.toString() }">${raw.name }（规格：${raw.specification }）</option>
				</c:forEach>
			</select>
			</td>
			
		</tr>
		<tr>
			<td class="td100"><span style="color: red;">*</span>规格：</td>
			<td class="td150"><input id="specification" type="text" class="input" value="" disabled="disabled"/></td>
			<td class="td100">单位：</td>
			<td class="td150"><input id="units" name="units" class="input" disabled="disabled"></td>
		</tr>
		<tr>
			<td class="td100"><span style="color: red;">*</span>单价(元)：</td>
			<td class="td150"><input id="unitPrice" name="unitPrice" class="input"></td>
			<td class="td100"><span style="color: red;">*</span>数量：</td>
			<td class="td150"><input id="num" name="num" class="input"></td>
		</tr>
		<tr>
			<td class="td100">合计：</td>
			<td class="td150"><input id="sum" name="sum" class="input" disabled="disabled"/></td>
			<td class="td100"><span style="color: red;">*</span>进货日期：</td>
			<td class="td150"><input id="inDate" name="inDate" class="input"/></td>
		</tr>
		<tr>
			<td class="td100">备注：</td>
			<td colspan="3"><textarea id="mark" name="mark" rows="" cols="" style="width: 398px;">暂无备注</textarea></td>
		</tr>
		<tr>
			<td></td>
			<td></td>
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
var $units = $("#units");
var $unitPrice = $("#unitPrice");
var $num = $("#num");
var $clientId = $("#clientId");
var $sum = $("#sum");
var $inDate = $("#inDate");
var $mark = $("#mark");

$(function() {
	$rawMaterialId.change(function() {
		$specification.val($(this).find("option:selected").attr("svalue"));
		$units.val($(this).find("option:selected").attr("sunits"));
	});
	
	$unitPrice.change(function() {
		if ($unitPrice.val() == "" || $num.val() == "") {
			$sum.val(0);
			return;
		}
		$sum.val( parseFloat($unitPrice.val()* $num.val()).toFixed(2));
	});
	
	$num.change(function() {
		if ($unitPrice.val() == "" || $num.val() == "") {
			$sum.val(0);
			return;
		}
		$sum.val( parseFloat($unitPrice.val()* $num.val()).toFixed(2));
	});
	
	$( "#inDate" ).datepicker();
	
	/*提交*/
	$("#btn_add").click(function() {
		if ($clientId.val() == "") {
			alert("进货单位不能为空！");
			return;
		}
		if ($rawMaterialId.val() == "") {
			alert("原料不能为空！");
			return;
		}
		if ($unitPrice.val() == "") {
			alert("单价不能为空！");
			return;
		} else {
			var unitPriceTest = /^[0-9]*[.]*[0-9]*$/;
			if (!unitPriceTest.test($unitPrice.val())) {
				alert("请输入正确的单价！");
				return;
			}
		}
		if ($num.val() == "") {
			alert("数量不能为空！");
			return;
		} else {
			var numTest = /^[0-9]*[.]*[0-9]*$/;
			if (!numTest.test($num.val())) {
				alert("请输入正确的数量！");
				return;
			}
		}
		if ($inDate.val() == "") {
			alert("进货日期不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/rawMaterial/in/add'/>",
    		data: {
    			rawMaterialId : $rawMaterialId.val(),
    			unitPrice : $unitPrice.val(),
    			num : $num.val(),
    			clientId : $clientId.val(),
    			sum : $sum.val(),
    			inDate : $inDate.val(),
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