<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店铺</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/scripts/jqpagination/css/pagination.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.masonry.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>
  <script type="text/javascript">
  
      <!--
  
    var pageIndex = 0;     //页面索引初始值  
    var pageSize = 6;     //每页显示条数初始化，修改显示条数，修改这里即可  
  		
   $(document).ready(function(){
	
	    $(".shop-gsbox").hover(
		  function () {
		    $(this).children(".shop-action").css({display: "block"});
		  },
		  function () {
		    $(this).children(".shop-action").css({display: "none"});
		  }
		);	   
   
         $("#Pagination").pagination(${count}, {  
            callback: PageCallback,  
            prev_text: '<<',       //上一页按钮里text  
            next_text: '>>',       //下一页按钮里text  
            items_per_page: pageSize,  //显示条数  
            num_display_entries: 6,    //连续分页主体部分分页条目数  
            current_page: pageIndex,   //当前页索引  
            num_edge_entries: 2        //两侧首尾分页条目数  
    	});
	});
	
  function init() {
		    $('#container').masonry({
		      itemSelector: '.shop-gs',
		      // columnWidth: 233
		      columnWidth: 217
		    });  
  }

  //翻页调用  
  var tr_str = "<tr><td><a href ='${contextPath}/site/Post-shoptopic.htm?id={0}'>{1}{3}</a></td><td>{2}</td></tr>"
  function PageCallback(pageIndex, jq) {   
       $.ajax({   
	       type: "POST",  
	       dataType: "text",  
	       url: '${contextPath}/site/Post-shopTopics.htm?topicId=${topicView.id?c}',    //提交到一般处理程序请求数据  
	       data: "page=" + (pageIndex + 1) + "&rp=6" ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	   data = $.parseJSON(data);
	           $("#Result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）  
	           for(var i=0 ;i< data.pageData.rows.length;i++){
	           		var rowData = data.pageData.rows[i]; 
	           		$("#Result").append(tr_str.format(rowData.id,rowData.subject,rowData.replyCount, rowData.totop?'[<font color="red">置顶</font>]':''));
	           }
	        }  
	    });                
	
	}  
    -->
  </script>

</head>
<body onload="init()">
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
		
	<#macro show_shop_gs shopgs>
		<div class="shop-gs">
			<div class="shop-gsbox">
				<div class="shop-action" style="display:none;">
					<#if _checkin>
						<a href="javascript:;" class="left like" title="喜欢" onclick="enjoyTopic(${shopgs.id?c})"><span>喜 欢</span></a>
						<a href="javascript:;" class="right dislike" title="不喜欢" onclick="boredTopic(${shopgs.id?c})"><span>不喜欢</span></a>				
					<#else>
						<a href="javascript:;" class="left like" title="喜欢" onclick="$('.reg-link').click()"><span>喜 欢</span></a>
						<a href="javascript:;" class="right dislike" title="不喜欢" onclick="$('.reg-link').click()"><span>不喜欢</span></a>					
					</#if>
				</div>
				<a href="${contextPath}/site/Topic-productview.htm?id=${shopgs.id?c}" target="_blank" class="shop-gsImageLink">
					<#if shopgs.thumbnail?exists>
						<img src="${staticWebRoot}/${shopgs.thumbnail}" alt="no-bake peanut butter bars" class="shop-gsImage">
					</#if>
				</a>
			</div>
	
			<p class="shop-gsDescription">${shopgs.text!''}</p>
			
			<p class="color1">
				<#if (shopgs.price>0)>
					<span>${shopgs.priceYuan}</span>元&nbsp;&nbsp;
				</#if>
				<span class="shop-gsLikesCount" id="enjoy_${shopgs.id?c}">${shopgs.enjoyments}</span>&nbsp;&nbsp;
				<span class="shop-gsImageView">
					<a href="${contextPath}/site/Topic-productview.htm?id=${shopgs.id?c}" target="_blank" class="main-color">查 看</a>
				</span>
			</p>
		</div>		
	</#macro>
	
<div class="bbs-main bbs-shop">
		
		<div class="grid_17 shop-info">
			<h2 >${topicView.title!''}</h2>
			<#if topicView.vip>
				<span class="verificate-pic">是否加V</span>
				<p class="magazine"><span>《昕沙》</span>${topicView.magazineQuota!''}</p>
				<p class="orange">电话：${topicView.phone!''}</p>
			</#if>
			
			<h4>创建人：<a title="店长" href="#${topicView.presenter}">${topicView.presenterName!''}</a></h4>
			<ul>
				<#if topicView.tags?exists>
				<li>
					<p class="tags">
						店铺标签：
						<#list topicView.tags as tag>
							<#if (tag.qname?exists && tag.id>0)>
								<a href="${contextPath}/site/Topic-qshops.htm?${tag.qname}=${tag.id?c}" title="${tag.name!''}">${tag.name!''}</a>
							</#if>
						</#list>
					</p>
				</li>
				</#if>
				<li>
					<p>详细位置：${topicView.addr!''}</p>
				</li>
				<li>
					<p>简介：${topicView.text!''}</p>
				</li>
			</ul>
			
			
			<p class="evaluate"><span class="like" id="enjoy_${topicView.id?c}">${topicView.enjoyments}</span>人喜欢</p>
			<p class="evaluate"><span class="dislike" id="bored_${topicView.id?c}">${topicView.boredoms}</span>人不喜欢</p>
			<p class="evaluate"><span class="viewed">${topicView.views}</span>人浏览过</p>
			<div class="right evaluate-pic">
				<#if _checkin>
					<a href="javascript:;" title="喜欢" onclick="enjoyTopic(${topicView.id?c})" class="like-pic">喜欢图标</a>
					<a href="javascript:;" title="不喜欢"  onclick="boredTopic(${topicView.id?c})" class="dislike-pic">不喜欢图标</a>
				<#else>
					<a href="javascript:;" title="喜欢"  onclick="$('.reg-link').click()" class="like-pic">喜欢图标</a>
					<a href="javascript:;" title="不喜欢"  onclick="$('.reg-link').click()" class="dislike-pic">不喜欢图标</a>					
				</#if>
			</div>
			
			<div class="boss"><#if topicView.keeper>这是我的店铺： <a href="${contextPath}/c/Topic-shopedit.htm?id=${topicView.id?c}">进入管理店铺》》</a></#if></div>

		</div>
		<div class="grid_7 shop-mainpic">
			<img style="width:230px;" src="${staticWebRoot}/${topicView.icon!''}" />
		</div>
		
		<#if topicView.vip>
		<div class="shop-bulletin clear">
			
			<h3>店长说：</h3>
			<p>${topicView.said!'欢迎光临，店长在此'}</p>
			
		</div>
		</#if>
		
		<div class="shop-center clear">
			
			
			<#if topicView.topicProducts?exists && (topicView.topicProducts?size>0)>
			<div class="grid_17 shop-gs-show" id="container" style="padding-right: 0;">
				<#list topicView.topicProducts as product>
					<@show_shop_gs product />
				</#list>
			</div>
			<#else>
				<div class="grid_17 shop-gs-show shop-gs-none"><h4>暂无店铺图片...
				<#if _checkin && topicView.keeper>
					<a href="${contextPath}/c/Topic-productcreate.htm?topicId=${topicView.id?c}">现在就去上传图片>></a>
				</#if>
				</h4></div>
			</#if>
			



			<div class="grid_7 shop-discussic">

				<div class="topic">
					<#if _checkin>
						<a href="${contextPath}/c/Post-shoptopiccreate.htm?topicId=${topicView.id?c}" class="all-btn green-btn">我要发起话题</a>
						
						<#if topicView.keeper>
							<a href="${contextPath}/c/Topic-productcreate.htm?topicId=${topicView.id?c}" class="all-btn green-btn">我要上传图片</a>
						</#if>						
					<#else>
						<a href="javascript:;"  onclick="$('.reg-link').click()" class="all-btn green-btn">我要发起话题</a>
					</#if>
				</div>

				<h3>大家说：</h3>
				<table class="shop-table">
					<tbody id="Result">
						<tr>
					    	<td style="width:80%;">话题</td>
					    	<td nowrap="nowrap" class="td2" style="width:20%;">回应</td>
						</tr>
					</tbody>
				</table>
                <div id="Pagination" class="pagination"></div>
			</div>
		</div>
	</div>
	
	<#include "/ftl/inc/foot.ftl">
	
  <script type="text/javascript">
  <!--
  	<#if _checkin>
  		function enjoyTopic(topicId) {
			voteTopc('${contextPath}/c/Topic-enjoyTopic.htm?id='+topicId,'#enjoy_'+topicId);
  		}
		
		function boredTopic(topicId) {
			voteTopc('${contextPath}/c/Topic-boredTopic.htm?id='+topicId,'#bored_'+topicId);
		} 		
		
		function voteTopc(url,voteId) {
			$.ajax({
	    		url: url,
	    		type: 'GET',
	    		dataType:'json',
	    		data:null,
	    		success: function(data){
	    			if (preProcessData(data, function (msg) {
	    				//FIXME 此处需要修改
	    				errorView("你已经投过票了!");
	    				//$.show({ message: "你已经投过票了!"});
	    			})) {
	    			    var voteShow =  $(voteId);
	    			    var count = parseInt(voteShow.text().replace(",",""))+1;
	    				voteShow.text(number_format_commas(count));
	    			}
	    		},
	    		error: function(xmlHttpRequest, textStatus, errorThrown){
	    			errorView(xmlHttpRequest.responseText);
	    		},
	    		beforeSend:function() {
	    			loading(); 
	    		}    		
			});		
		}
  	</#if>
  //-->
  </script>	
</body>

</html>