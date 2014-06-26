<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
body{font-size: 13pt; margin: 15px auto; padding: 0;}
div{float: left;width: 40%; margin: 0 0 8px 0;}
span{float: left;}
.label{float: right;}
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
	<div>
		<label for="loginCode" class="label"><span style="color: red;">*</span>帐号：</label>
	</div>
	<div>
		<input type="text" id="loginCode" name="loginCode"/>
	</div>
	<div>
		<label for="password" class="label"><span style="color: red;">*</span>密码：</label>
	</div>
	<div>
		<input id="password" name="password" type="password" class="input"/>
	</div>
	<div>
		<label for="mark" class="label">备注：</label>
	</div>
	<div>
		<textarea id="mark" name="mark" rows="" cols="" class="input">暂无备注</textarea>
	</div>
	<div>
		<label class="label">是否启用：</label>
	</div>
	<div>
		<input id="enabled0" name="enabled" type="radio" value="true" checked="checked" />
		<label for="enabled0">启用</label>
		<input id="enabled1" name="enabled" type="radio" value="false" />
		<label for="enabled1">禁用</label>
	</div>
	<div>
		<input id="btn_add" name="btn_add" type="button" value="提交" style="float: right;"/>
	</div>
	<div>
		<input id="btn_cancel" name="btn_cancel" type="button" value="取消" />
	</div>
</form>
<!-- 表单结束 -->
<script type="text/javascript">
$(function() {
    var $loginCode = $("#loginCode");
    var $password = $("#password");
    var $mark = $("#mark");
    var $enabled = $("input[name=enabled]");
    
	/*提交*/
	$("#btn_add").click(function() {
		if ($loginCode.val() == "") {
			alert("帐号不能为空！");
			return;
		}
		if ($password.val() == "") {
			alert("密码不能为空！");
			return;
		}
		
		$.ajax({
			url: "<c:url value='/admin/user/add'/>",
    		data: {
    			loginCode : $loginCode.val(),
    			password : $password.val(),
    			mark : $mark.val(),
    			enabled : $enabled.val()
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