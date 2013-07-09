<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛首页</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/jquery/jquery.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
</head>
<body style="color:#696969;">
	<#include "/ftl/inc/head.ftl">
	<div class="bbs-main">
		<ul class="nav-module nav4">
			<li class="active">
				<a href="bbs.shop-index.html"><span>店铺们</span></a>
			</li>
			<li>
				<a href="bbs.circle-index.html"><span>圈子</span></a>
			</li>
			<li>
				<a href="javascript:;"><span>活动</span></a>
			</li>
		</ul>
		
		<br/>
		<div style="float:right;width:363px;">
			<select>
				<option value="0">全站</option>
				<option value="1">店铺</option>
				<option value="2">圈子</option>
				<option value="3">活动</option>
			</select>
			<input type="text">
			<a href="bbs.list-all.html" target="_blank" class="all-btn blue-btn">搜索</a>
			<!-- <button class="all-btn blue-btn">搜索</button> -->
		</div>
		
		<#include "/ftl/inc/foot.ftl">
	</div>
	
</body>
</html>