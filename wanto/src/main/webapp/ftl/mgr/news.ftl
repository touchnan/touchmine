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
    var currentPage = 0;
  		
	   $(document).ready(function(){
            
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
					 var dataVal = new Array();  
					 var delCount =0;
					  $('input[name="checkname"]:checked').each(function(){  
					  		if($(this).hasClass('mynew')){
					  			delCount++;
					  		}
					  		var val ={};
					  		val.name ="message.ids";
					  		val.value =$(this).val();
					  		dataVal.push(val);
					  });  
					  if(dataVal.length >0){
					  	  $.ajax({   
			               type: "POST",  
			               dataType: "text",  
			               url: '${contextPath}/c/Message-batchDelete.htm',     
			               data: dataVal,                  
			               success : function(data) {
			               		$("#checkall").checked=false;
			               		$("#messageNum").text(parseInt($("#messageNum").text())-delCount)
			               		PageCallback(currentPage);
			               }
		            	}); 
					  }
				}
			);
		
		
		$("#batchMark").click(
				function(){
					 var dataVal = new Array();  
					 var delCount =0;
					 var removeCss = new Array();  
					  $('input[name="checkname"]:checked').each(function(){  
					  		if($(this).hasClass('mynew')){
					  			delCount++;
					  			$(this).removeClass('mynew');
					  		}
					  		var val ={};
					  		val.name ="message.ids";
					  		val.value =$(this).val();
					  		dataVal.push(val);
					  		removeCss.push($(this).val());
					  }); 
					 
					
					  if(dataVal.length>0){
					  	  $.ajax({   
			               type: "POST",  
			               dataType: "text",  
			               url: '${contextPath}/c/Message-batchMark.htm',     
			               data: dataVal,                  
			               success : function(data) {
			               		$("#checkall").checked=false;
			               		$("#messageNum").text(parseInt($("#messageNum").text())-delCount)
			               		 for(var i = 0 ; i<removeCss.length;i++){
								  	$('#newID'+removeCss[i]).removeClass('td5');
								  	$('#newID'+removeCss[i]).text('');
								 }
			               		//PageCallback(currentPage);
			               }
		            	}); 
					  }
				}
			);
		
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
       		 currentPage = pageIndex;         
             $.ajax({   
                type: "POST",  
               dataType: "text",  
               url: '${contextPath}/c/Message-myMessages.htm',      //提交到一般处理程序请求数据  
               data: "pageData.page=" + (pageIndex + 1) + "&pageData.rp=10" ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
                success: function(data) { 
                
               		data = $.parseJSON(data);
                	//alert(data.pageData.rows.length)
                  $("#Result tr:gt(0)").remove();        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）  
                  
                   for(var i=0 ;i< data.pageData.rows.length;i++){
                   		var rowData = data.pageData.rows[i];
                   		var rowID = rowData.id;
                   		var td_0 ='';
                   		var td_1 ='';
                   		var td_2 ='';
                   		var td_3 ='';
                   		var td_4 ='';
                   		var td_5 ='';
                   		if(rowData.type ==1){
                   			td_1='<td>系统消息</td>';
                   		}else if(rowData.type ==2){
                   			td_1='<td>店铺</td>';
                   		}else if(rowData.type ==3){
                   			td_1='<td>活动</td>';
                   		}else if(rowData.type ==4){
                   			td_1='<td>圈子</td>';
                   		}
                   		if(rowData.reading ==  undefined|| rowData.reading == false){
                   			td_0 ='<td><input type="checkbox" name ="checkname" id =checkname'+rowID+' class="mynew" value='+rowID+'></td>';
                   			td_2 ='<td class="td5" id=newID'+rowID+'>new</td>';
                   		}else{
                   			td_0 ='<td><input type="checkbox" name ="checkname" id =checkname'+rowID+' value='+rowID+'></td>';
                   			td_2 ='<td id=newID'+rowID+'></td>';
                   		}
                   		if(rowData.shopTitle == undefined){
                   			td_3 ='<td class="td3"></td>';
                   		}else{
                   			td_3 ='<td class="td3"><a href="${contextPath}/site/Topic-shopview.htm?id='+rowData.shopId+'">'+rowData.shopTitle+'</a></td>';
                   		}
                   		
						
						if(rowData.kind ==1){
                   			td_4='<td>欢迎加入</td>';
                   		}else if(rowData.kind ==2){
                   			td_4='<td><a href="${contextPath}/c/Message-messageView.htm?message.id='+rowID+'">您关于'+rowData.postTitle+'话题的回复被删除了</a></td>';
                   		}else if(rowData.kind ==3){
                   			td_4='<td></td>';
                   		}	
						td_5 ='<td>'+rowData.createTime+'</td>'
							
                   		$("#Result").append('<tr>'+td_0+td_1+td_2+td_3+td_4+td_5+'<td >&nbsp;</td></tr>');
                  }
                }  
            });    
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
		
		<div class="left man-right">
			<div class="man-right-con">
				<h2 class="bbs-list-h2">${vu.nickname!''} 的最新动态</h2>
			<ul class="nav-module nav2">
				<li><a href="${contextPath}/c/User-reply.htm"><span>我回复的帖子</span></a></li>
				<li><a href="${contextPath}/c/User-topic.htm"><span>我发起的帖子</span></a></li>
				<#--<li class="active"><a href="${contextPath}/c/User-news.htm"><span>消息中心</span><span class="tag-num" id ="messageNum" value ="${num!''}">${num!''}</span></a></li> -->
			</ul>
			<div class="nav-bbs-con nav2-con">
			
				<div  class="btn">
					<input class="all-btn grey-btn" type="button" id="batchDelete" value="批量删除">
					<input  class="all-btn grey-btn" type="button" id="batchMark" value="批量标记为已读">
				</div>
				<table class="list-table" cellspacing="0" cellpadding="0" border="0">
					<tbody id="Result">
						<tr>
							<td>
								<input type="checkbox" id ="checkall">
							</td>
							<td>
								
							</td>
							<td></td>
							<td>关于</td>
							<td>内容</td>
							<td>时间</td>

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