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
			<td><input id="rawMaterialId" type="text" class="input" value="${RawrawMaterial.name }" disabled="disabled"/></td>
			<td class="td"><span style="color: red;">*</span>规格：</td>
			<td><input id="specification" type="text" class="input" value="${RawrawMaterial.specification }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>单价(元)：</td>
			<td><input id="unitPrice" name="unitPrice" value="${RepertoryIn.unitPrice }" class="input" disabled="disabled"/></td>
			<td class="td">单位：</td>
			<td><input id="units" name="units" class="input" value="${RawrawMaterial.units }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>数量：</td>
			<td><input id="num" name="num" class="input" value="${RepertoryIn.num }" disabled="disabled"/></td>
			<td class="td">合计：</td>
			<td><input id="sum" name="sum" class="input" value="${RepertoryIn.sum }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td"><span style="color: red;">*</span>客户：</td>
			<td><input id="clientId" name="clientId" class="input" value="${Client.name }" disabled="disabled"/></td>
			<td class="td">备注：</td>
			<td><textarea id="mark" name="mark" rows="" cols="" class="input" disabled="disabled">${RepertoryIn.mark }</textarea></td>
		</tr>
	</table>
</form>
<!-- 表单结束 -->
</body>
</html>