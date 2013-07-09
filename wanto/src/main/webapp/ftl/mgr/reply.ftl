<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>最新动态</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/scripts/jqpagination/css/pagination.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>

<script type="text/javascript">
  <!--
    var pageIndex = 0;     //页面索引初始值  
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可  
 	
  		
	   $(document).ready(function(){
             $("#Pagination").pagination(${count!'0'}, {  
	            callback: PageCallback,  
	            prev_text: '<<',       //上一页按钮里text  
	            next_text: '>>',       //下一页按钮里text  
	            items_per_page: pageSize,  //显示条数  
	            num_display_entries: 6,    //连续分页主体部分分页条目数  
	            current_page: pageIndex,   //当前页索引  
	            num_edge_entries: 2        //两侧首尾分页条目数  
        	});
	});

  //翻页调用  
        function PageCallback(pageIndex, jq) {             
             $.ajax({   
                type: "POST",  
               dataType: "text",  
               url: '${contextPath}/c/Post-myReplys.htm',      //提交到一般处理程序请求数据  
               data: "pageData.page=" + (pageIndex + 1) + "&pageData.rp=10" ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
                success: function(data) { 
               		data = $.parseJSON(data);
                  $("#Result tr:gt(0)").remove();          //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）  
                   for(var i=0 ;i< data.pageData.rows.length;i++){
                   		var rowData = data.pageData.rows[i];
                   		$("#Result").append('<tr>' +
                   		'<td></td><td><a href="javascript:;"   onclick="javascript:window.open(\'${contextPath}/site/Topic-shopview.htm?id='+rowData.topicId+'\')">'+rowData.topicTitle+'</a></td>' +
                   		'<td><a href="javascript:;"   onclick="javascript:window.open(\'${contextPath}/c/Post-postEdit.htm?id='+rowData.id+'\')">'+rowData.subject+'</a></td>' +
                   		'<td>'+'</td>' +
                   		'<td>'+rowData.replyCount+'</td>' +
                   		'<td>'+rowData.clickCount+'</td>' +
                   		'</tr>');//将返回的数据追加到表格  
                  }
                }  
            });    
        }  
        function deleteReplyPost(id){
		       if (confirm("确认删除该记录么")){
				$.ajax({
					url:'${contextPath}/c/Post-deletePost.htm',
					type:'POST',
					dataType:'json',
					data:'postId='+id,
					success: function(data){
						 	location.reload();
					   	},
				    error: function(xmlHttpRequest, textStatus, errorThrown){
					    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
					    }
				});
			}

        }

  //-->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
		<#if !count ?exists>
			<#assign count = 0>
		</#if>
	<div class="bbs-main">
		
		<#include "/ftl/inc/profile-menu.ftl">
		<@profile_menu idx="news"/>		
		 
		<div class="right man-right">
			<div class="man-right-con">
				<h2 class="bbs-list-h2">${vu.nickname!''} 的最新动态</h2>
			<ul class="nav-module nav2">
				<li class="active"><a href="${contextPath}/c/User-reply.htm"><span>我回复的帖子</span></a></li>
				<li><a href="${contextPath}/c/User-topic.htm"><span>我发起的帖子</span></a></li>
				<#--<li><a href="${contextPath}/c/User-news.htm"><span>消息中心</span></a></li>-->
			</ul>
			<div style="display:block" class="nav-bbs-con nav2-con">
				<table class="list-table" cellspacing="0" cellpadding="0" border="0">
					<tbody id = "Result" >
						<tr>
							<td>
							</td>
							<td>话题所在</td>
							<td>话题</td>
							<td>最新回复时间</td>
							<td>回复数</td>
							<td>浏览数</td>
							<td>&nbsp;</td>
						</tr>
						
					</tbody>
				</table>
				<#if count != 0>
					<div id="Pagination" class="pagination"></div>
				</#if>
			</div>
			</div>
			
		</div>

	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>