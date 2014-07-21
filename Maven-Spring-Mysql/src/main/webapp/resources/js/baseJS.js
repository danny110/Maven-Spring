$(document).ready(function(){
	
	/**
	 * 删除验证
	 * */
    /*$("body").on("click","[optype='delete']",function(){
       var $this=$(this);
       if(confirm("您确定要删除这条数据?")){
           var opurl=$this.attr("opurl");
           var ids= $this.attr("ids");
           var callback=$this.attr("callback");
           $.post(opurl,{ids:ids},function(data){
               if (data.isSuccess) {
                   eval(callback);
               } else {
                   if(data.errorReason){
                       alert(data.errorReason);
                   }else{
                       alert("操作出错!");
                   }

               }
           });
       }
   });*/
    
    /**
	 * 禁用/启用验证
	 * */
    /*$("body").on("click","[optype='enabled']",function(){
       var $this=$(this);
       var enabled = $this.attr("enabled");
       var message = "";
       if (enabled == 'false') {message = "您确定要禁用这条数据?";}
       else {message = "您确定要启用这条数据?";}
       if(confirm(message)){
           var opurl=$this.attr("opurl");
           var ids= $this.attr("ids");
           var enabled = $this.attr("enabled");
           var callback=$this.attr("callback");
           $.post(opurl,{ids:ids,enabled:enabled},function(data){
               if (data.isSuccess) {
                   eval(callback);
               } else {
                   if(data.errorReason){
                       alert(data.errorReason);
                   }else{
                       alert("操作出错!");
                   }

               }
           });
       }
   });*/
});

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