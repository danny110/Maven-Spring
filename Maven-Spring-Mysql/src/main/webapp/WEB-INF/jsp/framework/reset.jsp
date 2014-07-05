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
			<td class="td100"><span style="color: red;">*</span>当前密码：</td>
			<td class="td150"><input id="oldPWD" name="oldPWD" type="password" class="input"></td>
		</tr>
		<tr>
			<td class="td100"><span style="color: red;">*</span>新密码：</td>
			<td class="td150"><input id="newPWD" name="newPWD" type="password" class="input"></td>
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
var $oldPWD = $("#oldPWD");
var $newPWD = $("#newPWD");

$(function() {
	/*提交*/
	$("#btn_add").click(function() {
		if ($oldPWD.val() == "") {
			alert("当前密码不能为空！");
			return;
		}
		if ($newPWD.val() == "") {
			alert("新密码不能为空！");
			return;
		} else {
			if ($newPWD.val().trim().length < 6) {
				alert("新密码长度不能小于6位！");
				return;
			}
			if ($oldPWD.val() == $newPWD.val()) {
				alert("新密码不能当前密码相同！");
				return;
			}
		}
		
		$.ajax({
			url: "<c:url value='/admin/user/reset'/>",
    		data: {
    			oldPWD : $oldPWD.val(),
    			newPWD : $newPWD.val()
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