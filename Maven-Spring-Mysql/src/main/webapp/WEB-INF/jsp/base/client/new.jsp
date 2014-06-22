<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<style type="text/css">
body{font-size: 13pt; margin: 15px auto; padding: 0;}
div{float: left;margin: 0 0 8px 0;}
span{float: left;}
label{width: 100px;}
.divTR{width: 100%;}
.label{float: right;}
.input{width: 150px;}
.margin_50{margin: 0 0 0 50px;}
</style>
<script type="text/javascript">
var  webRoot='${pageContext.request.contextPath}';
</script>

<script type="text/javascript" src="<c:url value='/resources/jqGrid/js/jquery-1.9.0.min.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDialog.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/zDrag.js"/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Validform_v5.3.2.js"/>"></script>
</head>
<body>
<!-- 表单开始 -->
<form id="vform" method="post">
	<div class="divTR">
		<div class="margin_50">
			<label for="name" class="label"><span style="color: red;">*</span>姓名：</label>
		</div>
		<div>
			<input id="name" name="name" class="input">
		</div>
		<div class="margin_50">
		<label for="sex" class="label">性别：</label>
		</div>
		<div>
			<input id="MAN" name="sex" value="MAN" type="radio" checked="checked"/><label for="MAN">男</label>
			<input id="WOMEN" name="sex" value="WOMEN" type="radio"/><label for="WOMEN">女</label>
		</div>
	</div>
	<div class="divTR">
		<div class="margin_50">
			<label for="companyName" class="label">公司名称：</label>
		</div>
		<div>
		<input id="companyName" name="companyName" class="input" style="width: 455px;">
	</div>
	</div>
	<div class="divTR">
		<div class="margin_50">
			<label for="phone" class="label">手机号码：</label>
		</div>
		<div>
			<input id="phone" name="phone" class="input">
		</div>
		<div class="margin_50">
			<label for="telephone" class="label">座机号码：</label>
		</div>
		<div>
			<input id="telephone" name="telephone" class="input">
		</div>
	</div>
	<div class="divTR">
		<div class="margin_50">
			<label for="mark" class="label">客户备注：</label>
		</div>
		<div>
			<textarea id="mark" name="mark" rows="" cols="" class="input">暂无备注</textarea>
		</div>
		<div class="margin_50">
			<label class="label">是否启用：</label>
		</div>
		<div>
			<input id="enabled0" name="enabled" type="radio" value="true" checked="checked" />
			<label for="enabled0">启用</label>
			<input id="enabled1" name="enabled" type="radio" value="false" />
			<label for="enabled1">禁用</label>
		</div>
	</div>
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
	
	/*表单提交*/
	var vform;
    vform = $("#vform").Validform({ tiptype: 4, showAllError: true});
    vform.addRule([
        {
            ele: "#name",
            datatype: "*"
        }
    ]);
    
	/*提交*/
	$("#btn_add").click(function() {
		vform.config({
            ajaxpost: {
                url: '<c:url value="/admin/client/add"/>',
                success: function (data) {
                    if( data.isSuccess ) {
                        Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);
                    } else {
                        alert(data.errorReason);
                    }
                },
                error: function () {
                    createTips("操作出现异常，请联系管理员处理！");
                }
            }
        });

        if (vform.check()) {
            vform.ajaxPost();
        }
    });
	
	/*取消*/
	$("#btn_cancel").click(function() {
        Dialog.getInstance('0').cancelButton.onclick.apply(Dialog.getInstance('0').cancelButton,[]);
    });
});
</script>
</body>
</html>