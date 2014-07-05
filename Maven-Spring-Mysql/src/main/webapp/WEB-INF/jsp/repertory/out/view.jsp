<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link type="text/css" rel="stylesheet" href="<c:url value='/resources/css/newAndView.css'/>"/>
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
			<td class="td100"><span style="color: red;">*</span>原料：</td>
			<td class="td150"><input id="rawMaterialId" type="text" class="input" value="${RawrawMaterial.name }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td100">规格：</td>
			<td class="td150"><input id="specification" type="text" class="input" value="${RawrawMaterial.specification }" disabled="disabled" /></td>
		</tr>
		<tr>
			<td class="td100">数量：</td>
			<td class="td150"><input id="num" name="num" class="input" value="${RepertoryOut.num }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td100">单位：</td>
			<td class="td150"><input id="units" name="units" class="input" value="${RawrawMaterial.units.toString() }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td100"><span style="color: red;">*</span>出库日期：</td>
			<td class="td150"><input id="outDate" name="outDate" class="input" value="${RepertoryOut.outDate }" disabled="disabled"/></td>
		</tr>
		<tr>
			<td class="td100">备注：</td>
			<td class="td150"><textarea id="mark" name="mark" rows="" cols="" class="input" disabled="disabled">${RepertoryOut.mark }</textarea></td>
		</tr>
	</table>
</form>
<!-- 表单结束 -->
</body>
</html>