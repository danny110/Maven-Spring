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
	<table>
		<tr>
			<td class="td"><span style="color: red;">*</span>姓名：</td>
			<td><input id="name" name="name" class="input" value="${Client.name }" disabled="disabled"></td>
			<td class="td">性别：</td>
			<td>
				<input id="MAN" name="sex" value="MAN" type="radio" disabled="disabled" <c:if test="${Client.sex == 'MAN' }">checked="checked"</c:if>/><label for="MAN">男</label>
				<input id="WOMEN" name="sex" value="WOMEN" type="radio" disabled="disabled" <c:if test="${Client.sex == 'WOMEN' }">checked="checked"</c:if>/><label for="WOMEN">女</label>
			</td>
		</tr>
		<tr>
			<td class="td">公司名称：</td>
			<td colspan="3"><input id="companyName" name="companyName" class="input" style="width: 413px;" value="${Client.companyName }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td">手机号码：</td>
			<td><input id="phone" name="phone" class="input" value="${Client.phone }" disabled="disabled"/></td>
			<td class="td">座机号码：</td>
			<td><input id="telephone" name="telephone" class="input" value="${Client.telephone }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td">客户备注：</td>
			<td><textarea id="mark" name="mark" rows="" cols="" class="input" disabled="disabled">${Client.mark }</textarea></td>
			<td class="td">是否启用：</td>
			<td>
				<input id="enabled0" name="enabled" type="radio" value="true" disabled="disabled" <c:if test="${Client.enabled == 'true' }">checked="checked"</c:if>/>
				<label for="enabled0">启用</label>
				<input id="enabled1" name="enabled" type="radio" value="false" disabled="disabled" <c:if test="${Client.enabled == 'false' }">checked="checked"</c:if>/>
				<label for="enabled1">禁用</label>
			</td>
		</tr>
	</table>
</form>
<!-- 表单结束 -->
</body>
</html>