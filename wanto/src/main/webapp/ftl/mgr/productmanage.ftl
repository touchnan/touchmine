<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>最新动态</title>
<link type="text/css" href="${staticWebRoot}/scripts/jqpagination/css/pagination.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>

<script type="text/javascript">
  <!--
    var pageIndex = 0;     //页面索引初始值  
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可  
  	var currentPage = 0;
  	
  	function initPage(count) {
         $("#Pagination").pagination(count, {  
            callback: PageCallback,  
            prev_text: '<<',       //上一页按钮里text  
            next_text: '>>',       //下一页按钮里text  
            items_per_page: pageSize,  //显示条数  
            num_display_entries: 6,    //连续分页主体部分分页条目数  
            current_page: pageIndex,   //当前页索引  
            num_edge_entries: 2        //两侧首尾分页条目数  
    	});  	
  	}
  	
   $(document).ready(function(){
   		initPage(${count!'0'});
    	
		$("#checkall").click(
			function(){
				if(this.checked){
					$("input[name='checkname']").each(function(){this.checked=true;});
				}else{
					$("input[name='checkname']").each(function(){this.checked=false;});
				}
			}
		);
		
		$("#batchDelete").click(
			function(){
				 var ids = [];  
				 var delCount =0;
				  $('input[name="checkname"]:checked').each(function(){
				  		ids.push($(this).val()); 
				  }); 
				  if(ids.length >0){
				  	$.ajax({   
		               type: "POST",  
		               dataType: "json",  
		               url: '${contextPath}/c/Topic-delproducts.htm',     
		               data: [{'name':'id', 'value':ids.join(',')}],                  
		               success : function(data) {
			    			if (preProcessData(data)) {
			               		PageCallback(currentPage);			    			
			    			}
		               },
						beforeSend:function() {
							init=true;
							loading();
						}
	            	}); 
				  }
			}
		);
		
		$("#batchdown").click(
			function(){
				 var ids = [];  
				 var delCount =0;
				  $('input[name="checkname"]:checked').each(function(){
				  		ids.push($(this).val()); 
				  }); 
				  if(ids.length >0){
				  	$.ajax({   
		               type: "POST",  
		               dataType: "json",  
		               url: '${contextPath}/c/Topic-undercarriages.htm',     
		               data: [{'name':'id', 'value':ids.join(',')}],                  
		               success : function(data) {
			    			if (preProcessData(data)) {
			               		PageCallback(currentPage);			    			
			    			}
		               },
						beforeSend:function() {
							init=true;
							loading();
						}
	            	}); 
				  }
			}
		);		
		
	});

	
	var init=true;
	var tr_str = "<tr><td><input type='checkbox' name ='checkname' value='{0}'></td><td>{1}</td><td><a href='${contextPath}/site/Topic-productview.htm?id={0}'><img height='80' width='80' src='{2}'></a></td><td>{3}</td><td>{4}</td><td>{5}</td><td>{6}</td><td>{7}</td><td>{8}</td><td>"
	var down_str = "<a href='javascript:;' onclick='down(this,{0})'>下架</a>";
	var show_str = "<a href='javascript:;' onclick='show(this,{0})'>显示</a>";
	//翻页调用  
	var status = -110;//状态
	function PageCallback(pageIndex, jq) {
		 currentPage = pageIndex; 
	     $.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: '${contextPath}/c/Topic-products.htm?topicId=${topic.id}&status='+status,      //提交到一般处理程序请求数据  
	        data: "page=" + (pageIndex + 1) + "&rp="+pageSize ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	  data = $.parseJSON(data);
	          $("#Result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）
	          var a_trs = []  
	          for(var i=0 ;i< data.pageData.rows.length;i++){
	           		var row = data.pageData.rows[i];
	           		a_trs.push(tr_str.format(row.id, parseStatus(row.status), '${staticWebRoot}/'+row.thumbnail ,row.title, row.priceYuan, number_format_commas(row.views), number_format_commas(row.enjoyments), number_format_commas(row.boredoms),row.etime));
	           		a_trs.push(parseStatusHref(row.status, row.id));
	           		a_trs.push('</td></tr>');
	          }
	          $("#Result").append(a_trs.join(""));
	        },
			beforeSend:function() {
				if (init) {
					init=false;
				} else{
					loading();
				}
			}                
	    });
	}
	
	function parseStatus(status) {	
		return status==0?'显示':'下架';
	}
	
	function parseStatusHref(status, picId) {	
   		if(status==0) {
   			return down_str.format(picId);
   		} else {
   			return show_str.format(picId);
   		}
	}
	
	function show(thiz,picId) {
		opert(thiz, '${contextPath}/c/Topic-shelfcarriage.htm?id='+picId, 0,picId);
	}
	
	function down(thiz, picId){
		opert(thiz, '${contextPath}/c/Topic-undercarriage.htm?id='+picId, -1,picId);
	}
	
	function opert(obj, url, status, picId) {
		 $status = $('td',$(obj).parent().parent()).eq(1);
		 if ($status.text() !== parseStatus(status)) {
		     $.ajax({
		        type: "POST",  
		        dataType: "text",  
		        url: url,      //提交到一般处理程序请求数据  
		        data: null ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
		        success: function(data) { 
		       	  data = $.parseJSON(data);
		       	  if (preProcessData(data)) {
		       	  	$status.text(parseStatus(status));
		       	  	$(obj).html(parseStatusHref(status,picId));
		       	  }
		        },
				beforeSend:function() {
					loading();
				}                
		    });
	    }
	}
	
	
	function changeStatus(thiz) {
		status = thiz.value;
		$.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: '${contextPath}/c/Topic-productcount.htm?id=${topic.id}&status='+status,
	        data: null ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
		       	  data = $.parseJSON(data);
		       	  if (preProcessData(data)) {
		       	  	initPage(data.obj);
		       	  }
	        },
			beforeSend:function() {
			}                
		});
	}
  //-->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
	
	<div class="bbs-main">
		<h2 class="bbs-list-h2">${topic.title} 店铺管理：<span class="tagname">图片管理</span></h2>
		<div class="mana-info">
			<ul>
				<#if topic.vip>
				<li style="color:#F25C05;">已通过店长验证</li>
				</#if>
				<li>ID：${topic.identity?c}</li>
				<li>推荐时间：${topic.time}</li>
				<li>
					<p class="evaluate"><span class="viewed">${topic.views}</span>人浏览过</p>
					<p class="evaluate"><span class="like">${topic.enjoyments}</span>人喜欢</p>
					<p class="evaluate"><span class="dislike">${topic.boredoms}</span>人不喜欢</p>
				</li>
			</ul>			
		</div>

		<div class="shop-mana">
			<div class="grid_16 shop-mana-left">
				<h3 class="tag4">
					<span class="left main-color">图片管理</span>
				</h3>
				<div>
					<input class="all-btn grey-btn" id="batchDelete" type="button" value="批量删除">
					<input id="batchdown" type="button" class="all-btn grey-btn" value="批量下架">
					<a class="all-btn grey-btn" href="${contextPath}/c/Topic-productcreate.htm?topicId=${topic.id}">增加图片</a>
					
				</div>
				<table cellspacing="0" cellpadding="0" border="1" width="100%">
					<tbody id="Result">
						<tr>
							<th style="width: 20px;">
								<input type="checkbox" id="checkall">全选
							</th>
							<th>
								<select onchange="changeStatus(this)">
									<option value="-110">状态</option>
									<option value="0">显示</option>
									<option value="3">下架</option>
								</select>
							</th>
							<th>
								图片
							</th>
							<th>名称</th>
							<!--
							<th style="width:20%;">描述</th>
							-->
							<th>价格</th>
							<th>浏览数</th>
							<th>喜欢数</th>
							<th>不喜欢数</th>
							<th style="width:120px">最后编辑时间</th>
							<th style="width:50px">操作</th>
						</tr>
					</tbody>
				</table>
				
				<div id="Pagination" class="pagination"></div>
			</div>
			<div class="grid_8 shop-mana-right">
				<a class="all-btn grey-btn back" href="${contextPath}/c/Topic-shopmanage.htm">返回 所有店铺管理</a>

				<h3 class="tag4">
					<span class="left main-color">店铺管理传送门</span>
				</h3>
				<ul class="list vertical-menu2">
					<li><a href="${contextPath}/site/Topic-shopview.htm?id=${topic.id}">查看店铺效果</a></li>
					<li><a href="${contextPath}/c/Topic-shopedit.htm?id=${topic.id}">基本信息管理</a></li>
					<li><a href="${contextPath}/c/Topic-shoptoipcmanage.htm?id=${topic.id}">话题管理</a></li>
				</ul>

				<h3 class="tag4">
					<span class="left main-color">Tip:店铺内容修改技巧</span>
				</h3>
				<ul class="list vertical-menu2">
					<li>在信息真实的基础上，加上华丽的修饰语句</li>
					<li>申请店长加V，可以有很多的优点</li>
					<li>看看法律上的</li>
					<li>发的说法是的</li>
					<li>发的说法是的</li>
				</ul>
			</div>
		</div>
	</div>	
	<#include "/ftl/inc/foot.ftl">
</body>
</html>