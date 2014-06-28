<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
body{font-size: 13pt; margin: 15px auto; padding: 0;}
table{margin: 0 auto;}
td{line-height: 30px;}
.td{float: right;}
.input{width: 150px;}
</style>
<script type="text/javascript">
var webRootPath='${pageContext.request.contextPath}';
</script>

<script type="text/javascript" src="<c:url value='/resources/jqGrid/js/jquery-1.9.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDrag.js"/>"></script>
</head>
<body>
<!-- 表单开始 -->
<form id="vform" method="post">
	<table>
		<tr>
			<td class="td"><span style="color: red;">*</span>原料：</td>
			<td>
				<select id="rawMaterialId" name="rawMaterialId" class="input">
				<option value=""></option>
				<c:forEach var="raw" items="${rawrawMaterial }">
					<option value="${raw.id }" svalue="${raw.specification }" sunits="${raw.units }">${raw.name }(${raw.specification })</option>
				</c:forEach>
			</select>
			</td>
			<td class="td"><span style="color: red;">*</span>规格：</td>
			<td><input id="specification" type="text" class="input" value="" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>单价(元)：</td>
			<td><input id="unitPrice" name="unitPrice" class="input"></td>
			<td class="td">单位：</td>
			<td><input id="units" name="units" class="input" disabled="disabled"></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>数量：</td>
			<td><input id="num" name="num" class="input"></td>
			<td class="td">合计：</td>
			<td><input id="sum" name="sum" class="input" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>客户：</td>
			<td>
				<select id="clientId" name="clientId" class="input">
				<option value=""></option>
				<c:forEach var="raw" items="${client }">				
					<option value="${raw.id }">${raw.name }</option>
				</c:forEach>
			</select>
			</td>
			<td class="td">备注：</td>
			<td><textarea id="mark" name="mark" rows="" cols="" class="input">暂无备注</textarea></td>
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
		$sum.val($unitPrice.val()* $num.val());
	});
	
	$num.change(function() {
		if ($unitPrice.val() == "" || $num.val() == "") {
			$sum.val(0);
			return;
		}
		$sum.val($unitPrice.val() * $num.val());
	});
    
	/*提交*/
	$("#btn_add").click(function() {
		if ($rawMaterialId.val() == "") {
			alert("原料不能为空！");
			return;
		}
		if ($unitPrice.val() == "") {
			alert("单价不能为空！");
			return;
		}
		if ($num.val() == "") {
			alert("数量不能为空！");
			return;
		}
		if ($clientId.val() == "") {
			alert("客户不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/repertory/in/add'/>",
    		data: {
    			rawMaterialId : $rawMaterialId.val(),
    			unitPrice : $unitPrice.val(),
    			num : $num.val(),
    			clientId : $clientId.val(),
    			sum : $sum.val(),
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