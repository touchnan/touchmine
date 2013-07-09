/**
 * 格式化
 * '<li class="style_{0}" >{1}</li>'.format(name,value)
 */
String.prototype.format = function() {
    var args = arguments;
    return this.replace(/{(\d{1})}/g, function() {
        return args[arguments[1]];
    });
};

function number_format(number, decimals, dec_point, thousands_sep) {
    number = (number + '').replace(/[^0-9+\-Ee.]/g, '');
    var n = !isFinite(+number) ? 0 : +number,
        prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
        sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
        dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
        s = '',
        toFixedFix = function(n, prec) {
            var k = Math.pow(10, prec);
            return '' + Math.round(n * k) / k;
        };
    // Fix for IE parseFloat(0.55).toFixed(0) = 0;
    s = (prec ? toFixedFix(n, prec) : '' + Math.round(n)).split('.');
    if (s[0].length > 3) {
        s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
    }
    if ((s[1] || '').length < prec) {
        s[1] = s[1] || '';
        s[1] += new Array(prec - s[1].length + 1).join('0');
    }
    return s.join(dec);
}

function number_format_commas(nStr){
	nStr += '';
	x = nStr.split('.');
	x1 = x[0];
	x2 = x.length > 1 ? '.' + x[1] : '';
	var rgx = /(\d+)(\d{3})/;
	while (rgx.test(x1)) {
		x1 = x1.replace(rgx, '$1' + ',' + '$2');
	}
	return x1 + x2;
}

function loading(){
	try {
		$.blockUI({ message: loadingMsg});
	} catch(e){}
}

function preProcessData(data, invoker) {
	//"errorVo":{"code":101,"message":"","name":"","stack":""}
	//{"errorVo":null,"result":{"url":"site\/page.htm"}}
	if (data && (data.errorVo || data.result)) {
		if (data.errorVo) {
			(invoker || errorView) (data.errorVo.message || data.errorVo.name || data.errorVo.stack || data.errorVo.code);
		}
		if(data.result && data.result.url) {
			window.top.location.href=data.result.url;
		}
		return undefined;
	}
	return true;
}

function showMsg(boxId, msg) {
	$box = $(boxId);
    $box.show();
	$('.list-msg-error', $box).text(msg || '');
}

function hideMsg(boxId) {
	$(boxId).hide();
}

function errorView(msg)  {
	//errorShow('','',msg);
	setTimeout(function() {
		    $.blockUI({ 
		        message: msg, 
		        fadeIn: 700, 
		        fadeOut: 700, 
		        timeout: 2000, 
		        showOverlay: false, 
		        centerY: false, 
		        css: { 
		            width: '240px', 
		            top: '10px', 
		            left: '', 
		            right: '10px', 
		            border: 'none', 
		            padding: '5px', 
		            backgroundColor: '#000', 
		            '-webkit-border-radius': '10px', 
		            '-moz-border-radius': '10px', 
		            opacity: .6, 
		            color: '#fff' 
		        } 
		    });
		}, 100);
}

function errorView2(msg) {
	errorShow('','',msg);
}

function errorShow(errorDom,time,msg){
	time = time || 3;
	msg = msg || "服务器崩溃了，让他休息一下吧！";

	if ($(errorDom).length > 0 ) {
		
     } else {
     	var errorBox = '<div id="error-box"><div class="error-icon"></div></div>';
     	var errorDom = $(errorBox).append("<p>"+msg+"</p>");
     	$("body").append(errorDom);
     	
     	setTimeout( function(){
    		$(errorDom).fadeOut(300, function(){ 
    			$(this).remove(); 
    		});
    	}, (time*1000 ));     	
     }
}

function showPrompt($objId, msg) {
	$objId.validationEngine('showPrompt', msg || '', 'load', 'bottomRight', true);
}

function validForm($form) {
	$form.validationEngine({
		promptPosition: "bottomRight",
		validationEventTriggers:"keyup blur"
	});
}

function jqSelect(url, choice){
	var options = '';
	try {
		var data = $.parseJSON($.ajax({
		    url: url,
		    async: false,
		    success: function(data,result) {
		    	//if (!result) alert('Failure to ajax retrieve.');
		    }
		}).responseText);
		if (preProcessData(data)) {
			var vs = data.obj;
			if (vs) {
				var r = choice ? ["0:请选择"] : [];
				for (var i=vs.length; i--;) {
					r.push("{0}:{1}".format(vs[i].value,vs[i].name));
				}
				return r.join(";");
			}
		}
	} catch(e) {
		alert(e);
	}
	return options;
}

function getSelectData(url) {
	try {
		var data = $.parseJSON($.ajax({
		    url: url,
		    async: false,
		    success: function(data,result) {
		    	//if (!result) alert('Failure to ajax retrieve.');
		    }
		}).responseText);
		if (preProcessData(data)) {
			var vs = data.obj;
			if (vs) {
				return vs;
			}
		}
	} catch(e) {
		alert(e);
	}
	return null;
}

function bindSelect(url,obj, choice,checkValue){
	try {
		var data = $.parseJSON($.ajax({
		    url: url,
		    async: false,
		    success: function(data,result) {
		    	//if (!result) alert('Failure to ajax retrieve.');
		    }
		}).responseText);
		if (preProcessData(data)) {
			var vs = data.obj;
			if (vs) {
				bindData2Select(obj, vs, choice,checkValue);
			}
		}
	} catch(e) {
		alert(e);
	}
}

function bindData2Select(obj, aParis ,choice,checkValue) {
	var optionsObj = obj.options;
	optionsObj.length=0;
	if (choice) {
		optionsObj.add(new Option("请选择",0));
	}
	if (aParis) {
		for (var i=aParis.length; i--;) {
			if(checkValue!= undefined && checkValue == aParis[i].value){
				optionsObj.add(new Option(aParis[i].name,aParis[i].value,true,true));
			}else{
				optionsObj.add(new Option(aParis[i].name,aParis[i].value));
			}
		}
	}
}

$(document).ready(function (){
	$(".menu1 li a").click(function() {
		var flag = ($(".menu1-con",$(this).parent()).css("display") =="block");
		if(flag){
			$(".menu1-con",$(this).parent()).fadeOut("fast");
			$(this).addClass("red");
			
		}else{
			$(".menu1-con").fadeOut("fast");	
			$(this).removeClass("red");
			$(".menu1-con",$(this).parent()).slideToggle("400");
		}
	});

	$(document.body).bind("click",function(e){
		var $target = $(e.target);
		var a = $target.parent().hasClass("menu1-li");
		var b = $target.parent().parent().hasClass("menu1-li");
		
		if((!a)&&(!b)){
			$(".menu1-con").fadeOut("fast");
		}
	});

	// 注册弹出框
	$(".reg-link").click(function(){
		$("#inner-reg").show();
		return false;
	}) 
	$("#inner-close").click(function(){
		$("#inner-reg").hide();
	});

	//
	$('[placeholder]').focus(function() {
        var input = $(this);
        if (input.val() == input.attr('placeholder')) {
            input.val('');
            input.removeClass('placeholder');
        }
    }).blur(function() {
        var input = $(this);
        if (input.val() == '' || input.val() == input.attr('placeholder')) {
            input.addClass('placeholder');
            input.val(input.attr('placeholder'));
        }
    }).blur();

    $('[placeholder]').parents('form').submit(function() {
      $(this).find('[placeholder]').each(function() {
        var input = $(this);
        if (input.val() == input.attr('placeholder')) {
          input.val('');
        }
      })
	});

});