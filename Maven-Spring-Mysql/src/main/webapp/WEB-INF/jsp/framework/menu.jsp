<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>
<div class="menuTd" id="MenuTd">
	<div class="menuLi_Li" id="MenuTd_1">
	<ul>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">原料管理</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/rawMaterial/in/list'/>')">原料进货</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/rawMaterial/out/list'/>')">原料出库</a></li>
	      	</ul>
		</li>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">成品管理</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/product/order/list'/>')">成品订单</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/product/process/list'/>')">成品加工</a></li>
	      	</ul>
		</li>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">库存管理</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/repertory/rawMaterial/list'/>')">原料库存</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/repertory/product//list'/>')">成品库存</a></li>
	      	</ul>
		</li>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">报表中心</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/report/rawMaterialIn/list'/>')">原料进货</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/report/rawMaterialOut/list'/>')">原料出货</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/report/productOrder/list'/>')">成品订单</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/report/productProcess/list'/>')">成品加工</a></li>
	      	</ul>
		</li>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">资料</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/information/rawMaterial/list'/>')">原料信息</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/information/product/list'/>')">成品定制</a></li>
	      	</ul>
		</li>
		<li class="DownLi">
			<a href="javascript:void(0);" class="DownA">系统管理</a>
			<ul>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/sys/client/list'/>')">来往单位</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/sys/user/list'/>')">职工管理</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/sys/role/list'/>')">系统角色</a></li>
				<li><a href="javascript:void(0);" onclick="showPage('<c:url value='/admin/sys/roleAuth/list'/>')">角色授权</a></li>
	      	</ul>
		</li>
	</ul>
	</div>
</div>
<div id="CenterTd" class="splitTd" onClick="DisPlayMenu()"></div>
<script type="text/javascript">
var ShowMenu=getCookie("ShowMenu");
$(document).ready(function () {
	if(ShowMenu=="0"){
		$("#CenterTd").addClass('splitTdOn');
	}
	
	$(".DownLi a").click(function () {
		$(this).parent().find("ul").toggle();
	});
	
	$(".splitTd").click(function () {
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
	});
});

if(ShowMenu=="0"){
	$("#CenterTd").addClass('splitTdOn');
	$("#MenuTd").hide();
}

// iframe 显示页面
function showPage(url) {
	$("#workArea").attr("src", url);
}
</script>
</body>
</html>