<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛首页</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
		
	<div class="bbs-main">
		<div style="float:left;width:363px;">
			<select>
				<option value="1">店铺</option>
			</select>
			<input type="text" value="xs20120734"/>
			<a class="all-btn blue-btn" target="_blank" href="${contextPath}/site/Topic-qshops.htm">搜索</a>
			<!-- <button class="all-btn blue-btn">搜索</button> -->

			<br/>
			<div>
				<h3>最热门店铺，按回帖数先后顺序，提出5家店铺，一个店铺可以有很多帖子，算总数是否可行</h3>
				<a class="all-btn blue-btn" target="_blank" href="${contextPath}/site/Topic-qshops.htm">更多</a>
				<span>回帖子数</span>
				<img src="${staticWebRoot}/img/1.png" />
				<br/>
				目前就考虑放上店铺名称和店铺图片，回帖数
			</div>
			<div>
				<h3>杂志最近一期的店铺重点推荐，提出5家店铺</h3>
				<a class="all-btn blue-btn" target="_blank" href="${contextPath}/site/Topic-qshops.htm">更多</a>
				<img src="${staticWebRoot}/img/1.png" />
				<br/>
				目前就考虑放上店铺名称和店铺图片
			</div>
			<div>
				<h3>最新店铺，按创建店铺先后顺序，提出5家店铺</h3>
				<a class="all-btn blue-btn" target="_blank" href="${contextPath}/site/Topic-qshops.htm">更多</a>
				<img src="${staticWebRoot}/img/1.png" />
				<span>3分钟前</span>
				<br/>
				目前就考虑放上店铺名称和店铺图片，创建店铺时间
			</div>
			<div>
				<h3>店铺所在地址分类</h3>
				<ul>
					<li>西湖区</li>
					<li>江干区</li>
					<li>下沙</li>
				</ul>
				由于我们主要是面对下沙学生，所以，先把几个主城区建立区就可以，下沙的话稍微再细分。
			</div>
			<div>
				<h3>店铺所属类别分类：</h3>
				<ul>
					<li>餐饮美食：全部火锅(129)烧烤(82)西餐(102)海鲜(46)地方菜(368)烤鱼(39)麻辣香锅(3)日韩料理(90)快餐(21)蛋糕(139)其他(145)</li>
					<li>休闲娱乐：电影(22)KTV(84)运动健身(135)游乐电玩(83)展览演出(23)足疗按摩(142)洗浴(47)采摘(11)其他(56)</li>

				</ul>
				分类可以先适当参照百度团购。
			</div>
			
			<div></div>
			<div></div>
		</div>
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>