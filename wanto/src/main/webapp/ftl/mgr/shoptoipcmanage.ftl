<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>话题管理</title>
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
		               url: '${contextPath}/c/Topic-delshoptopic.htm',     
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
	var tr_str = "<tr><td><input type='checkbox' name ='checkname' value='{0}'></td><td>{1}</td><td>{2}</td><td>{3}</td><td>{4}</td><td>{5}</td><td>{6}</td><td>{7}</td><td>"
	//翻页调用  
	var status = -110;//状态
	function PageCallback(pageIndex, jq) {
		 currentPage = pageIndex; 
	     $.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: '${contextPath}/c/Topic-shoptopics.htm?topicId=${topic.id}&status='+status,      //提交到一般处理程序请求数据  
	        data: "page=" + (pageIndex + 1) + "&rp="+pageSize ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	  data = $.parseJSON(data);
	          $("#Result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）
	          var a_trs = [];
	          var len = data.pageData.rows.length;
	          for(var i=0 ;i< len;i++){
	           		var row = data.pageData.rows[i];
	           		a_trs.push(tr_str.format(row.id, row.subject,row.userNickname, row.time, number_format_commas(row.clickCount), number_format_commas(row.usefulCount), number_format_commas(row.unusefulCount),number_format_commas(row.replyCount),row.etime));
	           		a_trs.push(parseStatusHref(row.totop, row.id));
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
	
	var top_str = "<a href='javascript:;' onclick='gotop(this,{0})'>置顶</a>";
	var cTop_str = "<a href='javascript:;' onclick='canceltop(this,{0})'>取消置顶</a>";
	var down_str = " <a href='javascript:;' onclick='up(this,{0})'>上移</a> <a href='javascript:;' onclick='down(this,{0})'>下移</a>";
	function parseStatusHref(totop, shoptopicId) {
   		if(totop) {
   			return cTop_str.format(shoptopicId)+down_str.format(shoptopicId);
   		} else {
   			return top_str.format(shoptopicId)+down_str.format(shoptopicId);
   		}
	}
	
	function up(thiz,shoptopicId) {
		opert(thiz, '${contextPath}/c/Topic-upshoptopic.htm?topicId=${topic.id}&id='+shoptopicId);
	}
	
	function down(thiz, shoptopicId){
		opert(thiz, '${contextPath}/c/Topic-downshoptopic.htm?topicId=${topic.id}&id='+shoptopicId);
	}
	
	function gotop(thiz, shoptopicId){
		opert(thiz, '${contextPath}/c/Topic-topshoptopic.htm?id='+shoptopicId);
	}	
	
	function canceltop(thiz,shoptopicId) {
		opert(thiz, '${contextPath}/c/Topic-untopshoptopic.htm?id='+shoptopicId);
	}
	
	function opert(obj, url) {
	     $.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: url,      //提交到一般处理程序请求数据  
	        data: null ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	  data = $.parseJSON(data);
	       	  if (preProcessData(data)) {
	       	  	PageCallback(currentPage);
	       	  }
	        },
			beforeSend:function() {
				loading();
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
		<h2 class="bbs-list-h2">${topic.title} 店铺管理：<span class="tagname">话题管理</span></h2>
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
					<span class="left main-color">话题管理</span>
				</h3>
				<div>
					<#if topic.vip>
					<input class="all-btn grey-btn" id="batchDelete" type="button" value="批量删除">
					</#if>
				</div>
				<table cellspacing="0" cellpadding="0" border="1" width="100%">
					<tbody id="Result">
						<tr>
							<th style="width: 20px;">
								<input type="checkbox" id="checkall">全选
							</th>
							<th>
								话题
							</th>
							<th>作者</th>
							<!--
							<th style="width:20%;">描述</th>
							-->
							<th style="width:120px">创建时间</th>
							<th>浏览数</th>
							<th>顶</th>
							<th>踩</th>
							<th>回复数</th>
							<th style="width:110px">操作</th>
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
					<li><a href="${contextPath}/c/Topic-productmanage.htm?id=${topic.id}">图片管理</a></li>
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