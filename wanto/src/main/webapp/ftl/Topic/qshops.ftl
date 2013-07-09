<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>搜索店铺</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>

<script type="text/javascript">
  <!--
    var pageIndex = 0;     //页面索引初始值  
    var pageSize = 20;     //每页显示条数初始化，修改显示条数，修改这里即可  
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
   		$('#rp').val(pageSize);
	});

	
	var init=true;
	var tr_str = "<tr><td><input type='checkbox' name ='checkname' value='{0}'></td><td>{1}</td><td><a href='${contextPath}/site/Topic-productview.htm?id={0}'><img height='80' width='80' src='{2}'></a></td><td>{3}</td><td>{4}</td><td>{5}</td><td>{6}</td><td>{7}</td><td>{8}</td><td>"
	var down_str = "<a href='javascript:;' onclick='down(this,{0})'>下架</a>";
	var show_str = "<a href='javascript:;' onclick='show(this,{0})'>显示</a>";
	//翻页调用  
	var status = -110;//状态
	function PageCallback(pageIndex, jq) {
		 currentPage = pageIndex;
		 $('#page').val(pageIndex + 1);
		 $('#rp').val(pageSize);
		 
		 
		 
		 
		 
	     $.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: '${contextPath}/site/Topic-sshops.htm',      //提交到一般处理程序请求数据  
	        data: $('#searchForm').serializeArray(),          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	  data = $.parseJSON(data);
	          $("#Result div").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）
	          var a_trs = []  
	          for(var i=0 ;i< data.pageData.rows.length;i++){
	           		var row = data.pageData.rows[i];
					a_trs.push("<div class=\"list-result list-grid1\">");
					a_trs.push("<div class=\"list-result-l\">");
					a_trs.push("	<div class=\"list-range list-cat-shop\">");
					a_trs.push("		类别：店铺");
					a_trs.push("	</div>");
					a_trs.push("	<div class=\"list-range\">");
					a_trs.push("		<img  style=\"width: 60px;\" src=\"${staticWebRoot}/"+row.thumbnail+"\" />");
					a_trs.push("	</div>");
					if (row.vip) {
						a_trs.push("	<div class=\"list-range verify-img\">");
						a_trs.push("		V验证");
						a_trs.push("	</div>");
					}
					a_trs.push("	<div class=\"list-range\">");
					a_trs.push("		<div class=\"list-grid1\">");
					a_trs.push("			<h4><a target=\"_blank\" href=\"${contextPath}/site/Topic-shopview.htm?id="+row.id+"\">"+row.title+"</a></h4>");
					a_trs.push("			<div class=\"list-recommend\">");
					a_trs.push("				<h6>推荐人：</h6>");
					a_trs.push("				<a href=\"#\">"+row.presenterName+"</a>");
					a_trs.push("			</div>");
					a_trs.push("		</div>");
					a_trs.push("		<div>");
					a_trs.push("			<div class=\"list-shop-location\">");
					a_trs.push("				<h6>位置：</h6>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schRid="+row.region1+"\">"+row.region1name+"</a>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schRid="+row.region2+"\">"+row.region2name+"</a>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schRid="+row.region3+"\">"+row.region3name+"</a>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schRid="+row.region4+"\">"+row.region4name+"</a>");
					a_trs.push("			</div>");
					a_trs.push("			<div class=\"list-tag-box\">");
					a_trs.push("				<h6>标签：</h6>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schKid="+row.kind1+"\">"+row.kind1name+"</a>");
					a_trs.push("				<a class=\"list-tag\" href=\"${contextPath}/site/Topic-qshops.htm?schKid="+row.kind2+"\">"+row.kind2name+"</a>");
					a_trs.push("			</div>");
					a_trs.push("		</div>");
					a_trs.push("	</div>");
					a_trs.push("</div>");
					a_trs.push("<div class=\"list-result-r\">");
					a_trs.push("	<ul>");
					a_trs.push("		<li class=\"list-grey\">");
					a_trs.push("			<span>"+row.views+"</span>");
					a_trs.push("			<span class=\"list-little\">浏 览</span>");
					a_trs.push("		</li>");
					a_trs.push("		<li class=\"list-darkorange\">");
					a_trs.push("			<span>"+row.enjoyments+"</span>");
					a_trs.push("			<span class=\"list-little\">喜 欢</span>");
					a_trs.push("		</li>");
					a_trs.push("		<li>");
					a_trs.push("			");
					a_trs.push("		</li>");
					a_trs.push("		<li class=\"list-darkgreen\">");
					a_trs.push("			<span>"+row.boredoms+"</span>");
					a_trs.push("			<span class=\"list-little\">不喜欢</span>");
					a_trs.push("		</li>");
					a_trs.push("	</ul>");
					a_trs.push("</div>");
					a_trs.push("</div>");	           		
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
	
  //-->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>

	<div class="bbs-main">
		<div class="list-sidebar">
			<div>
				<!-- <h2>search</h2> -->
				<h3 class="tag4">
					<span>搜 索</span>
				</h3>
				<div class="vertical-menu2">
					<form id="searchForm" method="post" action="${contextPath}/site/Topic-qshops.htm" onsubmit=" $sch = $('#sch_form');$sch.val($.trim($sch.val())); return ''!==$sch.val();">
						<input id="sch_form" name="sch" type="text" value="${sch!''}">
						<input type="hidden" id="page" name="page" value='0'>
						<input type="hidden" id="rp" name="rp" value='20'>
						<input type="hidden" id="schRid" name="schRid" value='${schRid?c}'>
						<input type="hidden" id="schKid" name="schKid" value='${schKid?c}'>
						<!--
						<p>Search: <select class="postform">
							<option value="0">全 站</option>
							<option selected="selected" >商店</option>
							<option >圈子</option>
							<option >活动</option>
						</select>
						</p>
						-->
						<input type="submit" value="搜 索">
						<!-- <button class="all-btn blue-btn">搜索</button> -->

					</form>
				</div>
			</div>

			<div class="list-page">
				<!-- <h2>search</h2> -->
				<h3 class="tag4">
					<span>快捷翻页</span>
				</h3>
				<div id="Pagination" class="pagination vertical-menu2 list-page-box"></div>
			</div>
			<div class="list-page">
				<!-- <h2>search</h2> -->
				<h3 class="tag4">
					<span>定位</span>
				</h3>
				<div class="vertical-menu2 list-page-box">
					<a href="#top_p">回顶部</a>
					<a href="#bottom_p">至底部</a>
				</div>
			</div>
		</div>

		<div class="left list-box">
			<h2 class="list-search-h2" id="top_p">搜索：<span>${sch!''} ${schKname!''} ${schRname!''} </span></h2>		
			
			<div id="Result" class="list-con">
				
			</div>
			<div id="bottom_p"></div>
		</div>
	</div>
	<#include "/ftl/inc/foot.ftl">
</body>
</html>