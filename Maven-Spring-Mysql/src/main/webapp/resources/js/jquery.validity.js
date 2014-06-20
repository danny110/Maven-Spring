/*
 * jQuery.validity beta v0.9.4.6
 * http://code.google.com/p/validity/
 * 
 * Copyright (c) 2009 Wyatt Allen
 * Dual licensed under the MIT and GPL licenses.
 *
 * Date: 2009-6-27 (Saturday, 27 June 2009)
 * Revision: 70
 */
(function($) {
    var defaults =  {
        outputMode:"label",
        scrollTo:false,
        modalErrorsClickable:true
    };
    $.validity = {
        settings:$.extend(defaults, { }),
        patterns: {
            integer:/^\d+$/,
            date:function(val) { return !isNaN(Date.parse(val)); },
            email:/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
            usd:/^\$?(\d{1,3},?(\d{3},?)*\d{3}(\.\d{0,2})?|\d{1,3}(\.\d{0,2})?|\.\d{1,2}?)$/,
            url:/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
            number:function(val) { return !isNaN(parseFloat(val)); },
            zip:/^\d{5}(-\d{4})?$/,
			phone:/(^[0-9]{3,4}\-[0-9]{3,8}$)|(^[0-9]{3,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)|(^0{0,1}15[0-9]{9}$)|(^0{0,1}18[0-9]{9}$)/
            //phone:/^([\(]{1}[0-9]{3}[\)]{1}[\.| |\-]{0,1}|^[0-9]{3}[\.|\-| ]?)?[0-9]{3}(\.|\-| )?[0-9]{4}$/
        },
        messages:{
            require:"未填写。",
            match:"格式错误。",
            integer:"必须是整数格式。",
            date:"必须是日期格式。",
            email:"必须是电子邮箱格式。",
            usd:"必须是美元符号。",
            url:"必须是地址格式。",
            number:"必须是数字格式。",
            zip:"必须是地区邮编格式。",
            phone:"必须是手机和固话号码，不能有分机号。",
            tooBig:"数值太大。",
            tooSmall:"数值太小。",
            tooLong:"数值太长。",
            equal:"输入与要求值不匹配。",
            distinct:"重复输入了。",
            sum:"值相加与要求值不匹配。",
            generic:"输入无效。"/*,
            usercardno:"身份证号码格式输入错误。"*/
        },
        outputs:{},
        setup:function(options) { this.settings = $.extend(this.settings, options); },
        report:null,
        isValidating:function() { return this.report !== null; },
        start:function() { 
            if (this.outputs[this.settings.outputMode] && this.outputs[this.settings.outputMode].start)
                this.outputs[this.settings.outputMode].start();
            this.report = { errors:0, valid:true }; 
        },
        end:function() { 
            if (this.outputs[this.settings.outputMode] && this.outputs[this.settings.outputMode].end)
                this.outputs[this.settings.outputMode].end();
            var results = this.report || { errors:0, valid:true };
            this.report = null; 
            if (!results.valid && 
                this.settings.scrollTo && 
                this.outputs[this.settings.outputMode] && this.outputs[this.settings.outputMode].scrollToFirstError)
                this.outputs[this.settings.outputMode].scrollToFirstError();
            return results;
        },
        clear:function(){
            this.start();
            this.end();
        }
    };
    $.fn.extend({
        validity:function(arg) {
            this.each(
                function() {
                    if (this.tagName.toLowerCase() == "form") {
                        switch (typeof(arg)) {
                            case "string":
                                $(this).bind(
                                    "submit",
                                    function() {
                                        $.validity.start();
                                        $(arg).require();
                                        return $.validity.end().valid;
                                    }
                                );
                                break;
                            case "function":
                                $(this).bind(
                                    "submit",
                                    function() {
                                        $.validity.start();
                                        arg();
                                        return $.validity.end().valid;
                                    }
                                );
                                break;
                        }
                    }
                }
            );
            
            return this;
        },
        require:function(msg) {
            return validate(this, function(obj) { return obj.value.length; }, msg || $.validity.messages.require);
        },
        match:function(rule, msg) {
            if (!msg) {
                msg = $.validity.messages.match;
                if (typeof(rule) == "string" && $.validity.messages[rule])
                    msg = $.validity.messages[rule];
            }
            if (typeof(rule) == "string") 
                rule = $.validity.patterns[rule]; 
            if (typeof(rule) == "function")
                return validate(this, function(obj) { return !obj.value.length || rule(obj.value); }, msg);
            return validate(this, function(obj) { return !obj.value.length || rule.test(obj.value); }, msg);
        },
        range:function(min, max, msg) {
            if (!msg)
                return this
                    .greaterThanOrEqualTo(min)
                    .lessThanOrEqualTo(max);
            return validate(this, function(obj) { var f = parseFloat(obj.value); return f >= min && f <= max; }, msg);
        },
        /*userCardNo:function(msg) {
            return validate(this, function(obj) { return IdentityCodeValid(obj.value); }, msg || $.validity.messages.usercardno);
        },*/
        greaterThan:function(min, msg) {
            return validate(this, function(obj) { return parseFloat(obj.value) > min; }, msg || $.validity.messages.tooSmall);
        },
        greaterThanOrEqualTo:function(min, msg) {
            return validate(this, function(obj) { return parseFloat(obj.value) >= min; }, msg || $.validity.messages.tooSmall);
        },
        lessThan:function(max, msg) {
            return validate(this, function(obj) { return parseFloat(obj.value) < max; }, msg || $.validity.messages.tooBig);
        },
        lessThanOrEqualTo:function(min, msg) {
            return validate(this, function(obj) { return parseFloat(obj.value) <= min; }, msg || $.validity.messages.tooBig);
        },
        maxLength:function(max, msg) {
            return validate(this, function(obj) { return obj.value.length <= max; }, msg || $.validity.messages.tooLong);
        },
        equal:function(arg0, arg1) {
            var $reduction = this.reduction || this;
            if ($reduction.length) {
                var transform = function(val) { return val; };
                var msg = $.validity.messages.equal;
                if (typeof(arg0) == "function") {
                    transform = arg0;
                    if (typeof(arg1) == "string")
                        msg = arg1;
                }
                else if (typeof(arg0) == "string")
                    msg = arg0;
                var map = $.map($reduction, function(obj) { return transform(obj.value); });
                var first = map[0];
                var valid = true;
                for (var i in map)
                    if (map[i] != first)
                        valid = false;
                if (!valid) {
                    raiseAggregateError($reduction, msg); 
                    this.reduction = $([]);
                }
            }
            return this;
        },
        distinct:function(arg0, arg1) {
            var $reduction = this.reduction || this;
            if ($reduction.length) {
                var transform = function(val) { return val; };
                var msg = $.validity.messages.distinct;
                if (typeof(arg0) == "function") {
                    transform = arg0;
                    if (typeof(arg1) == "string")
                        msg = arg1;
                }
                else if (typeof(arg0) == "string")
                    msg = arg0;
                var map = $.map($reduction, function(obj) { return transform(obj.value); });
                var subMap = [];
                var valid = true;
                for (var i1 = 0; i1 < map.length; i1++) {
                    if (map[i1].length) {
                        for (var i2 = 0; i2 < subMap.length; i2++) {
                            if (subMap[i2] == map[i1])
                                valid = false;
                        }
                        subMap.push(map[i1]);
                    }
                }
                if (!valid) {
                    raiseAggregateError($reduction, msg);
                    this.reduction = $([]);
                }
            }
            return this;
        },
        sum:function(sum, msg) {
            var $reduction = this.reduction || this;
            if ($reduction.length && sum != numericSum(this)) {
                raiseAggregateError($reduction, msg || $.validity.messages.sum); 
                this.reduction = $([]);
            }
            return this;
        },
        sumMax:function(max, msg) {
            var $reduction = this.reduction || this;
            if ($reduction.length && max < numericSum(this)) {
                raiseAggregateError($reduction, msg || $.validity.messages.sum); 
                this.reduction = $([]);
            }
            return this;
        },
        assert:function(expression, msg) { 
            var $reduction = this.reduction || this;
            if ($reduction.length && !expression) {
                raiseAggregateError($reduction, msg || $.validity.messages.generic); 
                this.reduction = $([]);
            }
            return this;
        }
    });
    function validate ($obj, regimen, message) {
        var $reduction = $obj.reduction || $obj;
        var elements = [];
        $reduction.each(
            function() {
                if (regimen(this))
                    elements.push(this);
                else
                    raiseError(this, message);
            }
        );
        $obj.reduction = $(elements);
        return $obj;
    }
    function addToReport() {
        if($.validity.isValidating()) {
            $.validity.report.errors++;
            $.validity.report.valid = false;
        }
    }
    function raiseError(obj, msg) {
        with ($.validity) {
            addToReport();
            
            if (outputs[settings.outputMode] && outputs[settings.outputMode].raise)
                outputs[settings.outputMode].raise($(obj), msg);
        }
    }
    function raiseAggregateError($obj, msg) {
        with ($.validity) {
            addToReport();
            
            if (outputs[settings.outputMode] && outputs[settings.outputMode].raiseAggregate)
                outputs[settings.outputMode].raiseAggregate($obj, msg);
        }
    }
    function numericSum(obj) {
        var accumulator = 0;
        obj.each(
            function() {
                var n = parseFloat(this.value);
                if(!isNaN(n))
                    accumulator += n;
            }
        );
        return accumulator;
    }
    function formatError(text, argument, name) {
        if (arguments.length < 2)
            return text;
            
        return text.replace(/{argument}/gi, argument).replace(/{name}/gi, name);
    }
    (function(){
        $.validity.outputs.label = {
            start:function(){ 
                $("label.error").remove(); 
            },
            raise:function($obj, msg){
                var errorId = $obj.attr('id');
                var errorSelector = "#" + errorId;
                var labelSelector = "label.error[for='" + errorId + "']";
                if ($(labelSelector).length)
                    $(labelSelector).text(msg);
                else
                    $("<label/>").attr("for", errorId).addClass("error").text(msg).insertAfter(errorSelector);
            },
            raiseAggregate:function($obj, msg){ 
                if ($obj.length)
                    this.raise($($obj.get($obj.length - 1)), msg);
            },
            scrollToFirstError:function(){ 
                if ($("label.error").length)
                    location.hash = $("label.error:eq(0)").attr('for');
            }
        };
    })();
    (function(){
        var errorClass = "validity-modal-msg";
        var allErrors = "." + errorClass;
        var container = "body";
        var idPrefix = "validity-modal-msg-";
        $.validity.outputs.modal = { 
            start:function(){ 
                $(allErrors).remove(); 
            },
            
            raise:function($obj, msg){                
                var off = $obj.offset();
                var errorStyle = { 
                    left:parseInt(off.left + $obj.width() + 4) + "px", 
                    top:parseInt(off.top - 10) + "px" 
                };
                var errorId = idPrefix + $obj.attr("id");
                var errorSelector = "#" + errorId;
                if ($(errorSelector).length)
                    $(errorSelector).css(errorStyle).text(msg);
                else
                    $("<div/>").attr("id", errorId).addClass(errorClass).css(errorStyle).text(msg).click($.validity.settings.modalErrorsClickable ?
                            function() { $(this).remove(); } : null 
                        ).appendTo(container);
            },
            raiseAggregate:function($obj, msg){ 
                if ($obj.length)
                    this.raise($($obj.get($obj.length - 1)), msg);
            },
            scrollToFirstError:function(){ 
                if ($(allErrors).length)
                    location.hash = $(allErrors + ":eq(0)").attr('id');
            }
        };
    })();
    (function(){
        var container = "#validity-summary-container";
        var summary = "#validity-summary-output";
        var erroneous = "validity-erroneous";
        var errors = ".validity-erroneous";
        var wrapper = "<li/>";
        var buffer = [];
        $.validity.outputs.summary = {
            start:function() { 
                $(errors).removeClass(erroneous);  
                buffer = []; 
            },
            end:function() {
                $(container).hide().find(summary).html('');
                
                if (buffer.length) {
                    for (var i in buffer)
                        $(wrapper).text(buffer[i]).appendTo(summary);
                            
                    $(container).show();
                }
            },
            raise:function($obj, msg) { 
                buffer.push(msg);
                $obj.addClass(erroneous);
            },
            raiseAggregate:function($obj, msg) { 
                this.raise($obj, msg); 
            },
            scrollToFirstError:function(){ 
                location.hash = $(errors + ":eq(0)").attr("id"); 
            }
        };
    })();
})(jQuery);
