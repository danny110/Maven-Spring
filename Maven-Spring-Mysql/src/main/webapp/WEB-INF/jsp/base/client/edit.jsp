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
tr{line-height: 30px;}
.td{width: 100px; text-align: right;}
div{float: left;margin: 0 0 8px 0;}
label{width: 100px;}
.divTR{width: 100%;}
.label{float: right;}
.input{width: 150px;}
.margin_50{margin: 0 0 0 50px;}
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
	<input id="id" name="id" type="hidden" value="${Client.id }"/>
	<table>
		<tr>
			<td class="td"><span style="color: red;">*</span>姓名：</td>
			<td><input id="name" name="name" class="input" value="${Client.name }"></td>
			<td class="td">性别：</td>
			<td>
				<input id="MAN" name="sex" value="MAN" type="radio" checked="checked" <c:if test="${Client.sex == 'MAN' }">checked="checked"</c:if>/><label for="MAN">男</label>
				<input id="WOMEN" name="sex" value="WOMEN" type="radio" <c:if test="${Client.sex == 'WOMEN' }">checked="checked"</c:if>/><label for="WOMEN">女</label>
			</td>
		</tr>
		<tr>
			<td class="td">公司名称：</td>
			<td colspan="3"><input id="companyName" name="companyName" class="input" style="width: 413px;" value="${Client.companyName }"/></td>
		</tr>
		<tr>
			<td class="td">手机号码：</td>
			<td><input id="phone" name="phone" class="input" value="${Client.phone }"/></td>
			<td class="td">座机号码：</td>
			<td><input id="telephone" name="telephone" class="input" value="${Client.telephone }"/></td>
		</tr>
		<tr>
			<td class="td">客户备注：</td>
			<td><textarea id="mark" name="mark" rows="" cols="" class="input">${Client.mark }</textarea></td>
			<td class="td">是否启用：</td>
			<td>
				<input id="enabled0" name="enabled" type="radio" value="true" <c:if test="${Client.enabled == 'true' }">checked="checked"</c:if>/>
				<label for="enabled0">启用</label>
				<input id="enabled1" name="enabled" type="radio" value="false" <c:if test="${Client.enabled == 'false' }">checked="checked"</c:if>/>
				<label for="enabled1">禁用</label>
			</td>
		</tr>
	</table>
	<div class="divTR">
		<div style="width: 50%;">
			<input id="btn_add" name="btn_add" type="button" value="提交" style="float: right;"/>
		</div>
		<div class="margin_50">
			<input id="btn_cancel" name="btn_cancel" type="button" value="取消" />
		</div>
	</div>
</form>
<!-- 表单结束 -->
<script type="text/javascript">
$(function() {
	var $id = $("#id");
    var $name = $("#name");
    var $companyName = $("#companyName");
    var $phone = $("#phone");
    var $telephone = $("#telephone");
    var $mark = $("#mark");
    
	/*提交*/
	$("#btn_add").click(function() {
		if ($name.val() == "") {
			alert("姓名不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/client/update'/>",
    		data: {
    			id : $id.val(),
    			name : $name.val(),
    			sex : $("input[name=sex]:checked").val(),
    			companyName : $companyName.val(),
    			phone : $phone.val(),
    			telephone :$telephone.val(),
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