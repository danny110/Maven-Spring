<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="menuLi_Li" id="MenuTd_1">
<ul>
	<li class="DownLi">
		<a href="javascript:void(0)" class="DownA">库存管理</a>
		<ul>
			<li><a href="<c:url value='/admin/repertory/in/list'/>">入库信息</a></li>
			<li><a href="<c:url value='/admin/repertory/out/list'/>">出库信息</a></li>
			<li><a href="<c:url value='/admin/repertory/over/list'/>">库存统计</a></li>
      	</ul>
	</li>
	<li class="DownLi">
		<a href="javascript:void(0)" class="DownA">基础信息</a>
		<ul>
			<li><a href="<c:url value='/admin/rawMaterial/list'/>">原料信息</a></li>
			<li><a href="<c:url value='/admin/client/list'/>">客户信息</a></li>
      	</ul>
	</li>
	<li class="DownLi">
		<a href="javascript:void(0)" class="DownA">系统管理</a>
		<ul>
			<li><a href="<c:url value='/admin/user/list'/>">用户信息</a></li>
      	</ul>
	</li>
</ul>
</div>
<script type="text/javascript">
/*左侧菜单开始*/
var ShowMenu=getCookie("ShowMenu");
function DisPlayMenu(){
	if(ShowMenu=="1"||ShowMenu==""){
		$("#CenterTd").addClass('splitTdOn');
		$("#MenuTd").toggle();
		ShowMenu="0";
	}else{
		$("#CenterTd").removeClass('splitTdOn');
		$("#MenuTd").toggle();
		ShowMenu="1";
	}
	setCookie("ShowMenu",ShowMenu);
    SetBodySize();
}
if(ShowMenu=="0"){
	$("#CenterTd").addClass('splitTdOn');
	$("#MenuTd").hide();
} 
	/*左侧菜单结束*/
</script>
</body>
</html>