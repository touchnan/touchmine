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
	            if (!preProcessData(data, function(msg){ if (msg) alert(msg); } )) {
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
	
	
	$.fn.topNavButtonAdd = function (btn) {
		var topNavId = "#"+this.attr("id")+"_toppager_left";
		return this.jqGrid('navButtonAdd',topNavId, btn || {});
	}
	
})(jQuery);