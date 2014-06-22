<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/lhgdialog.css'/>"/>
<link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/loginPage.css'/>"/>

<script type="text/javascript">
var  webRoot='${pageContext.request.contextPath}';
</script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.js'/>"></script>
<script type="text/javascript" src="<c:url value='/resources/js/jquery.lhgdialog.js'/>"></script>
<script type="text/javascript" src="<c:url value="/resources/js/Validform_v5.3.2.js"/>"></script>
<script type="text/javascript">
$(document).ready(function () {
	$("#LoginDiv").css("padding-top",(($(document.body).height()-220)/2)+"px");
	$(window).resize(function(){
	    $("#LoginDiv").css("padding-top",((($(document.body).height()>=$("#LoginDiv").height()?$(document.body).height():$("#LoginDiv").height())-$("#LoginDiv").height())/2)+"px");
    });
});
</script>
</head>
<body>
<form id="vform" method="post">
	<div id="LoginDiv">
	<div class="top49px">用户登录</div>
	<div class="textDiv">
	<table border="0" cellspacing="1" cellpadding="1" id="loginTable">
		<tr>
		    <td class="tdright">账&nbsp;&nbsp;号</td>
		    <td><input name="loginCode" id="loginCode" type="text" /></td>
		</tr>
		<tr>
		    <td class="tdright">密&nbsp;&nbsp;码</td>
		    <td><input name="password" id="password" type="password" /></td>
		</tr>
		<tr>
		    <td class="tdright">&nbsp;</td>
		    <td class="aheight"><a id="login">登&nbsp;录</a></td>
		</tr>
	</table>
	</div>
		<div class="bottom5px"></div>
	</div>
</form>
<script type="text/javascript"> 
$(function() {
	/*表单提交*/
	var vform;
    vform = $("#vform").Validform({ tiptype: 4, showAllError: true});
    vform.addRule([
        {
            ele: "#loginCode",
            datatype: "*"
        }, 
        {
            ele: "#password",
            datatype: "*"
        }
    ]);
    
	/*提交*/
	$("#login").click(function() {
		vform.config({
            ajaxpost: {
                url: '<c:url value="/admin/login"/>',
                success: function (data) {
                    if( data.isSuccess ) {
                        location.href = "<c:url value='/admin/home'/>";
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
});
</script>
</body>
</html>