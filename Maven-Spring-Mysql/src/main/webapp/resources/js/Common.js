 var sweetTitles = {
 x : 10, // @Number: x pixel value of current cursor position
 y : 20, // @Number: y pixel value of current cursor position
 tipElements : ".sliderCenter a,.slider_image a,.ServiceDiv a", // @Array: Allowable elements that can have the toolTip,split with ","
 noTitle : false, //if this value is false,when the elements has no title,it will not be show,if true, show all!
 init : function() {
 var noTitle = this.noTitle;
 $(this.tipElements).each(function(){
 $(this).mouseover(function(e){
 if(noTitle){
 isTitle = true;
 }else{
 isTitle = $.trim(this.title) != '';
 }
 if(isTitle){
 this.myTitle = this.title;
 this.title = "";
 var tooltip = "<div id='tooltip'><p>"+this.myTitle+"</p></div>";
 $('body').append(tooltip);
 $('#tooltip')
 .css({
 "top" :( e.pageY+20)+"px",
 "left" :( e.pageX+10)+"px"
 }).show('fast');
 }
 }).mouseout(function(){
 if(this.myTitle != null){
 this.title = this.myTitle;
 $('#tooltip').remove();
 }
 }).mousemove(function(e){
 $('#tooltip')
 .css({
 "top" :( e.pageY+20)+"px",
 "left" :( e.pageX+10)+"px"
 });
 });
 });
 }
 };
 $(function(){
 //sweetTitles.init();
 });
 
 function slide(navigation_id, pad_out, pad_in, time, multiplier){

	// 创建目标路径
	var list_elements = navigation_id + " li.sliderTag";
	var link_elements = list_elements + " a";

	// 启动定时器用于滑动动画
	var timer = 0;
	// 创建幻灯片动画的所有列表元素
	$(list_elements).each(function(i){
		$(this).css("margin-left","-100px");
		// 更新计时器
		timer = (timer*multiplier + time);
		$(this).animate({ marginLeft: "0px" }, timer);
		$(this).animate({ marginLeft: "10px" }, timer);
		$(this).animate({ marginLeft: "0px" }, timer);
	});

	// 创建的所有链接元素的悬停滑动效果	
	$(link_elements).each(function(i){
		$(this).hover(function(){
			$(this).animate({ paddingLeft: pad_out }, 182);
		},function(){
			$(this).animate({ paddingLeft: pad_in }, 182);
		});
	});

}
var EmbedStr = "";
function SetFlash(obj,url,x,y) { 
	EmbedStr = "<object classid='clsid:d27cdb6e-ae6d-11cf-96b8-444553540000' codebase='http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,0' width='" + x + "' height='" + y + "'>";
	EmbedStr += "<param name='allowScriptAccess' value='always' />";
	EmbedStr += "<param name='movie' value='" + url + "' />";
	EmbedStr += "<param name='quality' value='high' />";
	EmbedStr += "<param name='wmode' value='transparent' />";
	EmbedStr += "<param name='menu' value='false' />";
	EmbedStr += "<embed src='" + url + "' id='flashObject' name='flashObject' quality='high' menu='false' wmode='transparent' bgcolor='#ffffff' width='" + x + "' height='" + y + "' allowScriptAccess='always' type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/go/getflashplayer' />";
	EmbedStr += "</object>";
	obj.html(EmbedStr);
}
var DOMZ = (top == self) ? window : top;
// JavaScript Document
function setCookie(name, value) {
    var today = new Date();
    var expires = new Date();
    expires.setTime(today.getTime() + 1000 * 60 * 60 * 24 * 365);
    try {
        document.cookie = name + "=" + encodeURIComponent(value) + "; expires=" + expires.toGMTString();
    } catch (e) {
        document.cookie = name + "=" + escape(value) + "; expires=" + expires.toGMTString();
    }
}

function getCookie(Name) {
    var search = Name + "=";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1) end = document.cookie.length;
            try {
                return decodeURIComponent(document.cookie.substring(offset, end)); //用uneccape不能解析中文
            } catch (e) {
                return unescape(document.cookie.substring(offset, end)); //用uneccape不能解析中文
            }
        } else {
            return ('');
        }
    } else {
        return ('');
    }
}
function HtmlEncode(text) {
    try {
        return decodeURIComponent(text);
    } catch (e) {
        return text.replace(/&amp/g, '&').replace(/&quot;/g, '\"').replace(/&lt;/g, '<').replace(/&gt;/g, '>');
    }
}
String.prototype.Trim = function () {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}
function QueryString(sName) {
    var sSource = String(window.document.location);
    var sReturn = "";
    var sQUS = "?";
    var sAMP = "&";
    var sEQ = "=";
    var iPos;
    iPos = sSource.indexOf(sQUS);
    var strQuery = sSource.substr(iPos, sSource.length - iPos);
    var strLCQuery = strQuery.toLowerCase();
    var strLCName = sName.toLowerCase();
    iPos = strLCQuery.indexOf(sQUS + strLCName + sEQ);
    if (iPos == -1) {
        iPos = strLCQuery.indexOf(sAMP + strLCName + sEQ);
        if (iPos == -1)
            return "";
    }
    sReturn = strQuery.substr(iPos + sName.length + 2, strQuery.length - (iPos + sName.length + 2));
    var iPosAMP = sReturn.indexOf(sAMP);
    if (iPosAMP == -1)
        return sReturn;
    else {
        sReturn = sReturn.substr(0, iPosAMP);
    }
    return sReturn;
}
function Flash(file, w, h) {
    document.write("\r\n\
<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=7,0,19,0' width='" + w + "' height='" + h + "'>\r\n\
	<param name='movie' value='" + file + "' />\r\n\
	<param name='wmode' value='Opaque' />\r\n\
	<param name='menu' value='true' />\r\n\
	<param name='quality' value='high' />\r\n\
	<param name='SCALE' value='noborder' /><param name=\"loop\" value=\"true\" />\r\n\
	<embed type='application/x-shockwave-flash' pluginspage='http://www.macromedia.com/shockwave/download/index.cgi?P1_Prod_Version=ShockwaveFlash' loop='true' menu='true' quality='high' wmode='Opaque' src='" + file + "' width='" + w + "' height='" + h + "'  scale='noborder'></embed>\r\n\
</object>");
}
//验证整数

function IsInt(code) {
    var i, j, strTemp;
    var GoInt = true;
    strTemp = "0123456789";

    if (code.length == 0) {
        GoInt = false;
    }

    for (i = 0; i < code.length; i++) {
        j = strTemp.indexOf(code.charAt(i));
        if (j == -1) {
            //说明有字符不是数字
            GoInt = false;
            break;
        }
    }
    return GoInt;
}

//验证邮件地址
function Em(str) { return (/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(str)) }
function Http(str) { return (/^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/.test(str)) }
function QQ(str) { return (/^[1-9]\d{4,11}$/.test(str)) }

//字符串长度，汉字算两个字节 
function CkString(Mstring, MaxintGb, MaxintEn, MinintGb, MinintEn, ifMix, intall) {
    var EnCount = 0;
    var GbCount = 0;
    var StCount = 0;
    var r;
    for (i = 0; i < Mstring.length; i++) {
        r = Mstring.charCodeAt(i, 1);
        if (r > 255) {
            GbCount++;
            StCount = StCount + 2
        } else {
            EnCount++;
            StCount++
        }
    }
    if (ifMix) {
        return intall >= StCount;
    } else {
        if (EnCount != 0 && GbCount != 0) {
            return false;
        } else if (EnCount != 0 && GbCount == 0) {
            return (EnCount < MinintEn || EnCount > MaxintEn) ? false : true;
        } else if (GbCount != 0 && EnCount == 0) {
            return (GbCount < MinintGb || GbCount > MaxintGb) ? false : true;
        } else {
            return true;
        }
    }
}
var flag = false;

function DrawImage(ImgD, Hw, Hh) {
    var image = new Image();
    var iwidth = Hw;  //定义允许图片宽度
    var iheight = Hh;  //定义允许图片高度
    image.src = ImgD.src;
    if (image.width > 0 && image.height > 0) {
        flag = false;
        if (image.width / image.height >= iwidth / iheight) {
            if (image.width > iwidth) {
                ImgD.width = iwidth;
                ImgD.height = (image.height * iwidth) / image.width;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        } else {
            if (image.height > iheight) {
                ImgD.height = iheight;
                ImgD.width = (image.width * iheight) / image.height;
            } else {
                ImgD.width = image.width;
                ImgD.height = image.height;
            }
        }
    }
}

//---------------------------------------------------  
// 判断闰年  
//---------------------------------------------------  
Date.prototype.isLeapYear = function () {
    return (0 == this.getYear() % 4 && ((this.getYear() % 100 != 0) || (this.getYear() % 400 == 0)));
}

//---------------------------------------------------  
// 日期格式化  
// 格式 YYYY/yyyy/YY/yy 表示年份  
// MM/M 月份  
// W/w 星期  
// dd/DD/d/D 日期  
// hh/HH/h/H 时间  
// mm/m 分钟  
// ss/SS/s/S 秒  
//---------------------------------------------------
Date.prototype.Format = function (formatStr) {
    var str = formatStr;
    var Week = ['日', '一', '二', '三', '四', '五', '六'];
    var monthInt=this.getMonth()+1;
    str = str.replace(/yyyy|YYYY/, this.getFullYear());
    str = str.replace(/yy|YY/, (this.getYear() % 100) > 9 ? (this.getYear() % 100).toString() : '0' + (this.getYear() % 100));

    str = str.replace(/MM/, monthInt > 9 ? monthInt.toString() : '0' + monthInt.toString());
    str = str.replace(/M/g, monthInt.toString());

    str = str.replace(/w|W/g, Week[this.getDay()]);

    str = str.replace(/dd|DD/, this.getDate() > 9 ? this.getDate().toString() : '0' + this.getDate());
    str = str.replace(/d|D/g, this.getDate());

    str = str.replace(/hh|HH/, this.getHours() > 9 ? this.getHours().toString() : '0' + this.getHours());
    str = str.replace(/h|H/g, this.getHours());
    str = str.replace(/mm/, this.getMinutes() > 9 ? this.getMinutes().toString() : '0' + this.getMinutes());
    str = str.replace(/m/g, this.getMinutes());

    str = str.replace(/ss|SS/, this.getSeconds() > 9 ? this.getSeconds().toString() : '0' + this.getSeconds());
    str = str.replace(/s|S/g, this.getSeconds());

    return str;
}
$(document).ready(function () {
    //alert($("script:eq(5)").attr("src"));
    $("script").each(function () {
        if ($(this).attr("src")) {
            if ($(this).attr("src").indexOf("lhgdialog") > -1) {
                //window.alert = function (msg, fun) { $.dialog.tips(msg, 2, "existmenu.gif", fun || null, "discuz"); }
                window.alert = function (msg, fun) { $.dialog.Tips(msg, fun || null, "discuz"); }
                return false;
            }
        }
    });
});
/*验证方式*/
var ValidityTrue = false;
$.validity.outputs.custom = {
    start: function () {
        ValidityTrue = false;
        $.validity.report = { errors: 0, valid: true };
        $("input:text").css({ border: "1px solid #7e9db9" }).removeClass("fail"); //.val("haha");
    },

    end: function () { if ($.validity.report.errors == 0) ValidityTrue = true; },

    raise: function (obj, msg) {
        DOMZ.alert(msg, function () { $(obj).css({ borderColor: "#f00" }).addClass("fail"); $(obj).focus() });
    },

    // Just raise the error on the last input.
    raiseAggregate: function (obj, msg) {
        this.raise($($(obj).get($(obj).length - 1)), msg);
    },

    scrollToFirstError: function () {
        location.hash = $('.fail:eq(0)').attr('id');
    }
}
$.validity.setup({ outputMode: "custom" });
//校验身份证
function IdentityCodeValid(code) {
    var city = { 11: "北京", 12: "天津", 13: "河北", 14: "山西", 15: "内蒙古", 21: "辽宁", 22: "吉林", 23: "黑龙江 ", 31: "上海", 32: "江苏", 33: "浙江", 34: "安徽", 35: "福建", 36: "江西", 37: "山东", 41: "河南", 42: "湖北 ", 43: "湖南", 44: "广东", 45: "广西", 46: "海南", 50: "重庆", 51: "四川", 52: "贵州", 53: "云南", 54: "西藏 ", 61: "陕西", 62: "甘肃", 63: "青海", 64: "宁夏", 65: "新疆", 71: "台湾", 81: "香港", 82: "澳门", 91: "国外 " };
    //var tip = "";
    var pass = true;
    if (!code || !/^\d{6}(18|19|20)?\d{2}(0[1-9]|1[12])(0[1-9]|[12]\d|3[01])\d{3}(\d|X)$/i.test(code)) {
        //tip = "身份证号格式错误";
        pass = false;
    } else if (!city[code.substr(0, 2)]) {
        //tip = "地址编码错误";
        pass = false;
    } else {
        //18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            //校验位
            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if (parity[sum % 11] != code[17]) {
                //tip = "校验位错误";
                pass = false;
            }
        }
    }
    //if (!pass) alert(tip);
    return pass;
}
//校验身份证
function IsImgType(objvalue) {
    var allowType = "|jpg|gif|bmp|png|jpeg|";
    if (objvalue.indexOf(".") >= 0) {
        var FileExt = objvalue.substring(objvalue.lastIndexOf(".") + 1).toLowerCase();
        if (allowType.indexOf("|" + FileExt + "|") > -1) {
            return true;
        } else {
            return false;
        }
    } else {
        return false;
    }
}
/*如果是Ajax异步分页
var PageClick = null;
$(document).ready(function () {
    if ($("#InsPageDiv").length > 0) {
        $("script").each(function () {
            if ($(this).attr("src")) {
                if ($(this).attr("src").indexOf("inspager") > -1) {
                    PageClick = function (pageclickednumber) {
                        ShowSearchSelectLi(pageclickednumber, 0);
                        $("#InsPageDiv").pager({ pagenumber: pageclickednumber, pagecount: pageCount, numcount: numCount, buttonClickCallback: PageClick });
                    };
                    AreaBig(OrgAreaLi, 0);
                    return false;
                }
            }
        });
    }
});
*/
/*
如果A标签超级链接提交，用return ValidityThisPage()
    function ValidityThisPage() {
        $.validity.start();
        $("#<%=comName.ClientID%>").require("企业名称必须填写！");
        $("#<%=trueName.ClientID%>").require("联系人姓名必须填写！");
        $("#<%=truePhone.ClientID%>").require("联系号码必须填写！").match("phone", "联系号码格式错误！");
        $("#<%=trueMail.ClientID%>").require("联系邮箱必须填写！").match("email", "邮箱格式错误！");
        $("#<%=comAdr.ClientID%>").require("联系地址必须填写！");
        $("#<%=trueNo.ClientID%>").require("企业工商号必须填写！");
        $("#<%=trueImg.ClientID%>").require("必须上传文件！").match(IsImgType, "图片上传格式错误。");
        $("#<%=comDesc.ClientID%>").require("企业介绍必须填写！");
        $.validity.end();
        return ValidityTrue;
    }
    如果表单提交则采取如下方式
    $(function () {
        $("#aspnetForm").validity(function () {
            $("#<%=trueName.ClientID%>").require("真实姓名必须填写！");
            $("#<%=truePhone.ClientID%>").require("号码必须填写！").match("phone", "号码格式错误！");
            //return ValidityTrue;
        });
    });
*/