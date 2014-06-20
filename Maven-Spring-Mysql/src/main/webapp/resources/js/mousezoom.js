(function($){
    $.fn.Zoom = function(){$(this).mouseover(function (e) {
        var s = $("<div id = 'imgdiv'><img src = " + this.src + " /></div>");
        $("body").append(s);
        $("#imgdiv").css({"top": (e.pageY + 20) + "px","left": (e.pageX + 10) + "px"}).show("slow");
	}).mouseout(function () {
        $("#imgdiv").remove();
    }).mousemove(function (e) {
        $("#imgdiv").css({"top": (e.pageY + 20) + "px","left": (e.pageX + 10) + "px"});
	});
};
})(jQuery);
