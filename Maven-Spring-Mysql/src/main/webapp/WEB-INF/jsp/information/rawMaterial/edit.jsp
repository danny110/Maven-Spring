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
	<input id="id" name="id" type="hidden" value="${RawMaterial.id }"/>
	<table>
		<tr>
			<td class="td"><span style="color: red;">*</span>原料名称：</td>
			<td><input id="name" name="name" type="text" class="input" value="${RawMaterial.name }"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>规格：</td>
			<td><input id="specification" name="specification" type="text" class="input" value="${RawMaterial.specification }"/></td>
		</tr>
		<tr>
			<td class="td">单位：</td>
			<td>
				<select id="units" name="units">
					<option value="KG" <c:if test="${RawMaterial.units == 'KG'}">selected="selected"</c:if>>Kg</option>
					<option value="TAO" <c:if test="${RawMaterial.units == 'TAO'}">selected="selected"</c:if>>套</option>
					<option value="ZHI" <c:if test="${RawMaterial.units == 'ZHI'}">selected="selected"</c:if>>只</option>
					<option value="GE" <c:if test="${RawMaterial.units == 'GE'}">selected="selected"</c:if>>个</option>
					<option value="JIAN" <c:if test="${RawMaterial.units == 'JIAN'}">selected="selected"</c:if>>件</option>
					<option value="UNKNOWN" <c:if test="${RawMaterial.units == 'UNKNOWN'}">selected="selected"</c:if>>缺省</option>
				</select>
			</td>
		</tr>
		<tr>
			<td class="td">备注：</td>
			<td><textarea id="mark" name="mark" rows="" cols="" class="input">${RawMaterial.mark }</textarea></td>
		</tr>
		<tr>
			<td class="td">是否启用：</td>
			<td>
				<input id="enabled0" name="enabled" type="radio" value="true" <c:if test="${RawMaterial.enabled == 'true'}">checked="checked"</c:if>/>
				<label for="enabled0">启用</label>
				<input id="enabled1" name="enabled" type="radio" value="false" <c:if test="${RawMaterial.enabled == 'false'}">checked="checked"</c:if>/>
				<label for="enabled1">禁用</label>
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<input id="btn_add" name="btn_add" type="button" value="提交"/>
				<input id="btn_cancel" name="btn_cancel" type="button" value="取消"/>
			</td>
		</tr>
	</table>
</form>
<!-- 表单结束 -->
<script type="text/javascript">
$(function() {
	var $id = $("#id");
	var $name = $("#name");
	var $specification = $("#specification");
	var $units = $("select[name=units]");
	var $mark = $("#mark");
	    
	/*提交*/
	$("#btn_add").click(function() {
		if ($name.val() == "") {
			alert("原料名称不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/rawMaterial/update'/>",
    		data: {
    			id : $id.val(),
    			name : $name.val(),
    			specification : $specification.val(),
    			units : $units.val(),
    			mark : $mark.val(),
    			enabled : $("input[name=enabled]:checked").val()
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