<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>管理店铺</title>
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
               url: '${contextPath}/c/Topic-myShops.htm',      //提交到一般处理程序请求数据  
               data: "pageData.page=" + (pageIndex + 1) + "&pageData.rp=10" ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
                success: function(data) { 
               		data = $.parseJSON(data);
                	//alert(data.pageData.rows.length)
                   $("#Result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）  
                   for(var i=0 ;i< data.pageData.rows.length;i++){
                   		var rowData = data.pageData.rows[i];
                   		$("#Result").append('<tr><td>'+rowData.title+'</td><td>'+rowData.addr+'</td><td>'+rowData.time+'</td><td><a href="${contextPath}/c/Topic-shopedit.htm?id='+rowData.id+'">管理</a></td><td>删除</td></tr>');
                  }
                }  
            });    
        }  
  //-->
  </script>
  
</head>
<body>
<#if !count ?exists>
	<#assign count = 0>
</#if>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
	
	<div class="bbs-main">
		
		<#include "/ftl/inc/profile-menu.ftl">
		<@profile_menu idx="mshop"/>
		
		<div class="left man-right">
			<div class="man-right-con">
				<h2 class="bbs-list-h2">${vu.nickname!''} 推荐的店铺</h2>

				<h3 class="tag4">
					<#if count == 0>对不起，目前您没有推荐过任意一家店铺 </#if><a class="orange" href="${contextPath}/c/Topic-shopcreate.htm">现在就去创建店铺 >></a>
				</h3>
				
				<#if (count gt 0)>
				<div>
					<table class="list-table" cellspacing="0" cellpadding="0" border="0">
						
						<tbody id="Result">
							<tr>
								<td>店铺名</td>
								<td>店铺详细位置</td>
								<td>创建店铺时间</td>
								<td>管理</td>
								<td>删除</td>
							</tr>
						</tbody>
					</table>
					<#if count != 0>
						<div id="Pagination" class="pagination"></div>
						</#if>
				</div>
				</#if>
			</div>
		</div>
			 
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>