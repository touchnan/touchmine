/**
 * 左对齐:右补c字符直到width为止 cs 字符(串) width 长度 c append符
 */
function alignLeft(cs, width, c) {
	if (cs === null)
		return null;
	var len = cs.length;
	if (len >= width)
		return cs;
	else
		return cs + dup(c, width - len);
}

/**
 * 重复num个cs字符(串)
 */
function dup(cs, num) {
	if (!cs || cs.trim() === "" || num <= 0)
		return "";
	var sb = new Array(0);
	for ( var i = num; i--;)
		sb.push(cs);
	return sb.join("");
}

/**
 * 浮点数加法运算
 */
function addFloat(arg1, arg2) {
	var r1, r2, m;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e1) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	return (mulFloat(arg1, m) + mulFloat(arg2, m)) / m;
}

/**
 * 浮点数减法运算
 */
function subFloat(arg1, arg2) {
	var r1, r2, m, n;
	try {
		r1 = arg1.toString().split(".")[1].length;
	} catch (e) {
		r1 = 0;
	}
	try {
		r2 = arg2.toString().split(".")[1].length;
	} catch (e1) {
		r2 = 0;
	}
	m = Math.pow(10, Math.max(r1, r2));
	// 动态控制精度长度
	n = (r1 >= r2) ? r1 : r2;
	return ((mulFloat(arg1 , m) - mulFloat(arg2 , m)) / m).toFixed(n);
}

/**
 * 浮点数乘法运算
 */
function mulFloat(arg1, arg2) {
	var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
	try {
		m += s1.split(".")[1].length;
	} catch (e) {
	}
	try {
		m += s2.split(".")[1].length;
	} catch (e1) {
	}
	return Number(s1.replace(".", "")) * Number(s2.replace(".", ""))
			/ Math.pow(10, m);
}

/**
 * 浮点数除法运算
 */
function divFloat(arg1, arg2) {
	var t1 = 0, t2 = 0, r1, r2;
	try {
		t1 = arg1.toString().split(".")[1].length;
	} catch (e) {
	}
	try {
		t2 = arg2.toString().split(".")[1].length;
	} catch (e1) {
	}
	with (Math) {
		r1 = Number(arg1.toString().replace(".", ""));
		r2 = Number(arg2.toString().replace(".", ""));
		return (r1 / r2) * pow(10, t2 - t1);
	}
}

/**
 * 日期格式化
 */
Date.prototype.format = function(mask) {
	var d = this;
	var zeroize = function(value, length) {
		if (!length)
			length = 2;
		value = String(value);
		for ( var i = 0, zeros = ''; i < (length - value.length); i++) {
			zeros += '0';
		}
		return zeros + value;
	};

	return mask
			.replace(
					/"[^"]*"|'[^']*'|\b(?:d{1,4}|m{1,4}|yy(?:yy)?|([hHMstT])\1?|[lLZ])\b/g,
					function($0) {
						switch ($0) {
						case 'd':
							return d.getDate();
						case 'dd':
							return zeroize(d.getDate());
						case 'ddd':
							return [ 'Sun', 'Mon', 'Tue', 'Wed', 'Thr', 'Fri',
									'Sat' ][d.getDay()];
						case 'dddd':
							return [ 'Sunday', 'Monday', 'Tuesday',
									'Wednesday', 'Thursday', 'Friday',
									'Saturday' ][d.getDay()];
						case 'M':
							return d.getMonth() + 1;
						case 'MM':
							return zeroize(d.getMonth() + 1);
						case 'MMM':
							return [ 'Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
									'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ][d
									.getMonth()];
						case 'MMMM':
							return [ 'January', 'February', 'March', 'April',
									'May', 'June', 'July', 'August',
									'September', 'October', 'November',
									'December' ][d.getMonth()];
						case 'yy':
							return String(d.getFullYear()).substr(2);
						case 'yyyy':
							return d.getFullYear();
						case 'h':
							return d.getHours() % 12 || 12;
						case 'hh':
							return zeroize(d.getHours() % 12 || 12);
						case 'H':
							return d.getHours();
						case 'HH':
							return zeroize(d.getHours());
						case 'm':
							return d.getMinutes();
						case 'mm':
							return zeroize(d.getMinutes());
						case 's':
							return d.getSeconds();
						case 'ss':
							return zeroize(d.getSeconds());
						case 'l':
							return zeroize(d.getMilliseconds(), 3);
						case 'L':
							var m = d.getMilliseconds();
							if (m > 99)
								m = Math.round(m / 10);
							return zeroize(m);
						case 'tt':
							return d.getHours() < 12 ? 'am' : 'pm';
						case 'TT':
							return d.getHours() < 12 ? 'AM' : 'PM';
						case 'Z':
							return d.toUTCString().match(/[A-Z]+$/);
						default:
							return $0.substr(1, $0.length - 2);

						}
					});

};
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

/**
 * 数字格式化
 * 
 * @param number
 * @param decimals
 * @param dec_point
 * @param thousands_sep
 * @returns
 */
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

function getTimestamp(connetor) {
	return connetor + "t=" + (new Date()).getTime();
}

var DEFAULT_QTYPE = 'flexi';

function bindDate($target,df) {
	$target.datepicker({
		dateFormat : df || "yy-mm-d",
		autoSize: true,
		// "changeMonth":true,
		changeYear: true,
		//showWeek: true,
		showButtonPanel: true,
	});
}

function bindDate4Month($target) {
	bindDate($target, "yy-mm");
}

function initDate(elem) {
	elem.id = elem.id.replace("\.","_")
	bindDate($(elem),'yy-mm-d');
}

var dateTemplate = {width: 120, align: 'center', sorttype: 'date',
    //formatoptions: { newformat: 'm/d/Y' }, datefmt: 'm/d/Y',
    formatoptions :{ srcformat : 'Y-m-d',newformat : 'Y-m-d' },
    editoptions: {date: true, dataInit: initDate },
    searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge'], dataInit: initDate }
};

function initTime(elem) {
	elem.id = elem.id.replace("\.","_")
	$(elem).datetimepicker({
		showSecond: true,
		timeFormat: 'hh:mm:ss',
		stepHour: 1,
		stepMinute: 1,
		stepSecond: 1
	});
}

var timeTemplate = {width: 120, align: 'center', sorttype: 'date',
	    //formatoptions: { newformat: 'm/d/Y' }, datefmt: 'm/d/Y',
	    //srcformat : 'Y-m-d H:i:s',newformat : 'Y-m-d H:i:s'
	    formatoptions :{ srcformat : 'Y-m-d H:i:s',newformat : 'Y-m-d H:i:s' },
	    editoptions: {date: true, dataInit: initTime },
	    searchoptions: { sopt: ['eq', 'ne', 'lt', 'le', 'gt', 'ge'], dataInit: initTime }
	};

function bindDatalist($target) {
	var tbody = $("tbody", $target);
	// $("tr:odd", tbody).addClass("altrow"); //为奇数行添加样式
	// $("tr:even", tbody).addClass("altrow"); //为偶数行添加样式
	$("tr", tbody).mouseover(function() { // 鼠标移动的高亮显示
		$(this).addClass("mouseOver");
	}).mouseout(function() {
		$(this).removeClass("mouseOver");
	});
}

function renderInfoTab($tab,data){
	$("tbody td", $tab).each(function (i, item){
		$this = $(item);
		var name =$this.attr("name");
		if (name) {
			$this.text('');
			if (data && data[name]) {
				$this.text(data[name]);
			}
		}
	});
}

function renderListTab($tab,rows,cols){
	var _trs = [];
	if (cols && cols.length>0 && rows && rows.length>0) {
		for (var i=rows.length; i--;) {
			var row = rows[i];
			_trs.push("<tr");
			if (i % 2) {
				_trs.push(" class='altrow'")
			}
			_trs.push(">")
			for (var j=0;j<cols.length;j++) {
				_trs.push("<td>");
				_trs.push(row[cols[j]] || "");
				_trs.push("</td>");
			}
			_trs.push("</tr>");
		}
	}
	$("tbody", $tab).html(_trs.join(""));
}

function showMsg(msg) {
	$box = $('.list-msg');
	if ($box[0]){
	   ( typeof($box.dialog)=== 'function' ? $box.dialog('open').show() :  $box.show() );
		$('.list-msg-error').text(msg || '');
	} else {
		$("<div class='list-msg' title='信息提示'><p class='list-msg-error'>"+(msg || '') +"</p></div>")
			.dialog({autoOpen:true,modal:true,buttons:{"关闭": function(){$(this).dialog("close");}},overlay:{backgroundColor: '#FFF',opacity: 0.5}});
	}	
}

function hideMsg(msg) {
	$box = $('.list-msg');
	if ($box[0]) {
		( $box.dialog ? $box.dialog('close').hide() :  $box.hide() );
		$('.list-msg-error').text(msg || '');
	} 
}

function showNetMsg() {
	showMsg("网络异常，请过几分再试.");
}

function preProcessAjaxData(data, invoker) {
	//"errorVo":{"code":101,"message":"","name":"","stack":""}
	//{"errorVo":null,"result":{"url":"c\/main.action"}}
	if (data && (data.errorVo || data.result)) {
		
		if (data.errorVo) {
			(invoker || showMsg) (data.errorVo.message || data.errorVo.name || data.errorVo.stack || data.errorVo.code);
		}
		if(data.result && data.result.url) {
			window.top.location.href=data.result.url;
		}
		return undefined;
	}
	return true;
}

function jqSelect(url){
	var options = '';
	try {
		var data = $.parseJSON($.ajax({
		    url: url,
		    async: false,
		    success: function(data,result) {
		    	//if (!result) alert('Failure to ajax retrieve.');
		    }
		}).responseText);
		if (preProcessAjaxData(data)) {
			var vs = data.obj;
			if (vs) {
				var r = [];
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

function jqGridSubmitComplete(jqXHR, postdata) {
	if (jqXHR) {
		var status = jqXHR.status;
		if ( status >= 200 && status < 300 || status === 304 ) {
			if ( status === 304 ) {
				// If not modified
				return [true,"304 : not modified",];
			} else {
				try {
					// If we have data
					var data = $.parseJSON(jqXHR.responseText);
					if (data && (data.errorVo || data.result)) {
						var eMsg = "数据错误";
						if (data.errorVo) {
							var eMsg = data.errorVo.message || data.errorVo.name || data.errorVo.stack || data.errorVo.code;
							return [false, eMsg, -1];
						}
						if(data.result && data.result.url) {
							window.top.location.href=data.result.url;
						}
						return [false, data, -1];
					}
					return [true,"304 : not modified",data.id];					
				} catch(e) {
					return [false,e,-1];
				}
			}
		} else {
			return [false,jqXHR.statusText+"["+status+"]错误",-1];
		}
	}
	return [false,"jqXHR参数无效",-1];	
}

/**
 * jqGrid
 */
(function($) {
	function gridParam() {
	    var O_NAME = '_oname',
	        jsonRoot = "pageData.rows";
	    var ep = {};//jqgrid自定义的参数
	    $.each(arguments, function(i, p) {
	        if (p[O_NAME]) {
	        	//直接编辑的时候，增加对象别名,grid数据要增加一层解析
	            jsonRoot = function(obj) {
	            	if (obj.pageData && obj.pageData.rows) {
		                $.each(obj.pageData.rows, function(i, row) {
		                    // MANIPULATON HERE
		                    row[p[O_NAME]] = row;
		                });
		                return obj.pageData.rows;
	            	}
	            	return [];
	            };
	            $.each(p.colModel, function(i, col) {
	                // MANIPULATON HERE
	                col.name = p[O_NAME] + "." + col.name;
	            });
	        }
	        $.extend(ep, p);
	    });
	    return $.extend({
	        jsonReader: {
	            root: jsonRoot,
	            page: "pageData.page",
	            total: "pageData.totalPages",
	            records: "pageData.total",
	            repeatitems: false
	        },
	        prmNames: {
	            rows: "rp",
	            sort: "sortname",
	            order: "sortorder"
	        },
	        altRows: false,
	        datatype: "json",
	        rowNum: 15,
	        rowList: [10, 15, 20, 30, 50, 100],
	        pager: '#pager',
	        //sortname: 'id',
	        //sortorder: "desc",
	        viewrecords: true,
	        mtype: "POST",//请求方式
	        //multiselectWidth:20,
	        hidegrid: true,//最小化
	        hiddengrid: false,//隐藏
	        rownumbers: true,
	        toppager: true,
	        autowidth: true,
	        shrinkToFit:true,
	        sortable:true,
	        griedview:true,
	        forceFit:false,
	        toolbar: [false, "top"],//“top”,”bottom”, “both”
	        height: "auto",
	        caption: "列表",
	        beforeProcessing: function(data, status, xhr) {
	            if (!preProcessAjaxData(data, function(msg){ if (msg) alert(msg); } )) {
	            	data.pageData = {"rows": []}
	            }
	        },
	        afterComplete:function(){
	        },
	        beforeRequest: function() {
	            //$(this).jqGrid('setGridParam','postData', postData);
	            return true;
	        }
	    }, ep);
	}
	
	//初始化grid
	$.fn.bindGrid = function (p) {
		return this.jqGrid(gridParam({
			multiselect:true,
			multiboxonly:true
		},p));
	};
	
	//初始化innerGrid
	$.fn.bindInnerGrid = function (p) {
		var lastSel;//最后选择的行
		return this.jqGrid(gridParam({
			ondblClickRow: function(id){
				/**
				if(id && id!==lastSel){ 
					$(this).jqGrid('restoreRow',lastSel); 
					lastSel=id; 
				}
				$(this).jqGrid('editRow',id,{
					keys:true,
					aftersavefunc : function() {$(this).trigger("reloadGrid");}
				});
				*/
				/*rowid, keys, oneditfunc, succesfunc, url, extraparam, aftersavefunc,errorfunc, afterrestorefunc
				$(this).jqGrid('editRow',id,{
					"keys" : true,
					"oneditfunc" : function(v){console.log(v);console.log("oneditfunc:")},
					"successfunc" : function(v){console.log(v);console.log("successfunc:")},
					"url" : null,
					"editurl":null,
				    "extraparam" : {},
					"aftersavefunc" : function() {$(this).trigger("reloadGrid");},
					"errorfunc": null,
					"afterrestorefunc" : null,
					"restoreAfterError" : true,
					"mtype" : "POST"
				});
				*/
			}
		}, p));
	};	
	
	//初始化导航
	$.fn.bindGridNav = function (pager) {

		var $this = this;
		
		$this.jqGrid('navGrid',pager,
				    $.extend({edit:true,add:true,del:true,view:true, cloneToTop:true}, arguments[1] || {}),//parameters
				    $.extend({recreateForm:false,modal:true,savekey:[true,13],navkeys:[true,38,40],reloadAfterSubmit:true,closeOnEscape:true,checkOnSubmit:true,checkOnUpdate:false, afterSubmit:jqGridSubmitComplete,bottominfo:"*:必填,回车键:保存,Esc:取消,上下键:上/下一条"}, arguments[2] || {}),//prmEdit
				    $.extend({recreateForm:false,modal:true,savekey:[true,13],navkeys:[true,38,40],reloadAfterSubmit:true,closeOnEscape:true,checkOnSubmit:true,checkOnUpdate:false, afterSubmit:jqGridSubmitComplete,bottominfo:"*:必填,回车键:保存,Esc:取消,"}, arguments[3] || {}),//prmAdd
				    $.extend({checkOnSubmit:true,checkOnUpdate:true,reloadAfterSubmit:true,afterSubmit:jqGridSubmitComplete}, arguments[4]|| {}),//prmDel
				    $.extend({multipleSearch:true, multipleGroup:true,closeOnEscape:true,showQuery:false}, arguments[5]),////prmSearch
				    $.extend({}, arguments[6])//prmView
				);
		if (arguments[1]===undefined || arguments[1].colhidable === undefined || arguments[1].colhidable) {
			var colbtn = { 
					caption: "", 
					buttonicon: "ui-icon-calculator",
					title: "选择列",
				  	onClickButton: function() {
				  		$(this).jqGrid('columnChooser');
				  	}
				};				
			$this.jqGrid('navButtonAdd', pager, colbtn);
			if (arguments[1]===undefined || arguments[1].cloneToTop === undefined || arguments[1].cloneToTop) {
				$this.topNavButtonAdd(colbtn)
			}
		}
		
		if (arguments[1]===undefined || arguments[1].sizable === undefined || arguments[1].sizable) {
			var resizeBtn = { 
					caption: "", 
					buttonicon: "ui-icon-arrow-4-diag",
					title: "缩放功能",
					onClickButton: function() {
						$(this).jqGrid('gridResize',
							{minWidth:350, maxWidth:'100%', minHeight:80, maxHeight:'100%'}
					    );
					}
				};				
			$this.jqGrid('navButtonAdd', pager, resizeBtn);
			if (arguments[1]===undefined || arguments[1].cloneToTop === undefined || arguments[1].cloneToTop) {
				$this.topNavButtonAdd(resizeBtn)
			}
		}			
		return $this;
		/*
		console.log("gridid:"+$grid.attr("id"));
		var topPagerDiv = $("#"+$grid.attr("id")+"_toppager")[0];
		$($("table table", topPagerDiv)[1]).remove();
		$("#"+$grid.attr("id")+"_toppager_right").remove();
		var bottomPagerDiv = $("div"+pager)[0];
		$($("table table", bottomPagerDiv)[0]).remove();
		*/
	};
	
	$.fn.bindInnerGridNav = function (pager) {
		/**
		return this.bindGridNav(pager,$.extend({
			editfunc:function(){
				$(this).jqGrid('editRow',$(this).jqGrid("getGridParam","selrow"),
						{
						 keys:true,
						 oneditfunc:function() {
						 },
						 successfunc:function() {
							 console.log(arguments)
							 alert('2222');
						 }
						}
				)
			},
			addfunc:function() {				
				$(this).jqGrid('addRow',{});
			}
		},arguments[1]),arguments[2],arguments[3],arguments[4],arguments[5],arguments[6])
		*/
		
		//return this.jqGrid('navGrid',pager,{add:true,edit:false}).jqGrid('inlineNav',pager,{editParams:{keys:true}});
		
	};	
	
	$.fn.topNavButtonAdd = function (btn) {
		var topNavId = "#"+this.attr("id")+"_toppager_left";
		return this.jqGrid('navButtonAdd',topNavId, btn || {});
	}
	
	//append参数
	$.fn.appendGridRequestParams = function (p) {
		if (p) {
			var postData = $.extend($(this).jqGrid('getGridParam','postData'),p);
			$(this).jqGrid('setGridParam','postData', postData);
		}
	};
	
	$.cc = {
		openDialog : function (options) {
			var divId = "dialog" + new Date().getTime();
			this._id = divId;
			var settings = {
					_id: divId,
					_params:{},
					_dialog:{},
					url:"",
					autoOpen: false,
					modal: false,
					width: 800,
					minHeight:300,
					buttons: {
						"保存" : function () {
							$.cc.save();
							$(this).dialog("close");
						},
						"关闭":function(){
							$(this).dialog("close");
						}
					},
					hide: "highlight",
					show: "highlight",//highlight,explode,slide
					title: "窗口",
					
					close: function () {//销毁
						$this = $("#"+settings._id)
						if ($this) {
							$this.remove();
						}
						$(this).dialog("destory");
					},
					
					/**
					open: function () {
						if (settings.url) {
							$("#" + settings._id).html('<iframe src="' + settings.url + '" frameborder="0" height="100%" width="100%" id="'+settings._id+'_ifn" scrolling="auto"></iframe>');
						}
					}
					**/
			}
			if (options) {
				$.extend(settings, options);
			}
			this._dialog = $('<div id="' + settings._id + '"></div>')
			.html('<iframe src="' + settings.url + '" frameborder="0" height="100%" width="100%" id="'+settings._id+'_ifn" scrolling="auto"></iframe>')
			.dialog(settings).dialog("open");
			settings._dialog = this._dialog;
			return this;
		},
		submitForm : function() {
			
		},
		ajaxForm : function() {
			
		}
	}
	
})(jQuery);