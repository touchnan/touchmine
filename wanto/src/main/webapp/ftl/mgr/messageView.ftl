<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>最新动态</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">



<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>

<script type="text/javascript">
  <!--
  	function del(id){
  		 var dataVal = new Array();  
		 var val ={};
  		 val.name ="message.ids";
  		 val.value =id;
  		 dataVal.push(val); 
		 $.ajax({   
	           type: "POST",  
	           dataType: "text",  
	           url: '${contextPath}/c/Message-batchDelete.htm',     
	           data: dataVal,                  
	           success : function(data) {
	           		window.location.href='${contextPath}/c/User-news.htm';
	           }
	  });
  	}
   -->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
	<#if !count ?exists>
		<#assign count = 0>
	</#if>
	<div class="bbs-main">
	<!-- <span class="right small orange">系统消息</span> -->
	<div class="grid_16 bbs-form">
		<h2 class="bbs-list-h2">您的回复被删除<span class="right small">${message.createTime!''}</span></h2>
		<div class="msg-con">
			您关于 <a class="main-color" href="bbs.discussic-show.html">${message.postTitle!'测试'}</a>话题的回复被删除了
		</div>
		<div class="msg-related">
			<ul>
				<li><p class="msg-auth">来自 
					<#if message.type == 1>
						系统消息
						<#elseif message.type == 2>
						店铺
						<#elseif message.type == 3>
						活动
						<#elseif message.type == 4>
						圈子
					</#if>
				</p></li>
				<li><a class="all-btn green-btn msg-btn" onclick="del(${message.id})">删 除</a></li>
			</ul>
			
			
			
		</div>

		
	</div>
	<div class="grid_8 discc-form-tips bbs-form-tips">
		<a class="all-btn grey-btn back msg-back" href='${contextPath}/c/User-news.htm'>返回 消息中心</a>
		<h3 class="tag4">
			<span class="left main-color">欢迎加入 happy112</span>
		</h3>
		<ul class="list vertical-menu2">
			<li>&#10022请填写有效的店铺信息</li>
			<li>blablabla</li>
			<li>房间的了双方就死定了</li>
			<li>看看法律上的</li>
			<li>发的说法是的</li>
			<li>发的说法是的</li>
		</ul>
	</div>
</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>