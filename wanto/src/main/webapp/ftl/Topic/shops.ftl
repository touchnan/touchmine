<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店铺</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>

 <script type="text/javascript">
  <!--
	$(document).ready(function(){
	});	
	
  //-->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
		
	<#macro show_shops shops isshop>
		<#if shops?exists>
			<#list shops as shop>
				<li class="shop-gs">
					<div class="shop-gsbox">
						<#if isshop>
							<a class="shop-gsImageLink" href="${contextPath}/site/Topic-shopview.htm?id=${shop.id?c}">
						<#else>
							<a class="shop-gsImageLink" href="${contextPath}/site/Topic-productview.htm?id=${shop.id?c}">
						</#if>
							<#if shop.thumbnail?exists>
								<img class="shop-gsImage" alt="no-bake peanut butter bars" src="${staticWebRoot}/${shop.thumbnail}">
							</#if>
						</a>
					</div>
					<p class="color1 shopin-desc">
						<span class="shop-gsPrice">
						${shop.views}
						&nbsp;&nbsp;</span>
						<span class="shop-gsLikesCount">
						${shop.enjoyments}
						&nbsp;&nbsp;</span>
					</p>
					<p class="color1 shopin-desc">
						<span class="shop-name">
						${shop.title!''}
						&nbsp;&nbsp;</span>
						
					</p>
				</li>				
			</#list>
		</#if>		
	</#macro>
	
	<div class="bbs-main shopin">
		<h1 class="bbs-main-h1 shopin-tit">店铺们</h1>
		<div style="background:#fff;overflow:hidden;">
		
		<div class="grid_18">
			<h2 class="tag4 list-h2">
				<span class="left">最热店铺</span>
			</h2>
			<div class="list vertical-menu2">
				<ul>
					<#if indexView.hottestShops?exists>
						<@show_shops shops=indexView.hottestShops isshop=true/>
					</#if>
				</ul>
			</div>

			<h2 class="tag4 list-h2">
				<span class="left">好评产品</span>
			</h2>
			<div class="list vertical-menu2">
				<ul>
					<#if indexView.bestShops?exists>
						<@show_shops shops=indexView.bestShops isshop=false/>
					</#if>
				</ul>
			</div>

			<h2 class="tag4 list-h2">
				<span class="left">最新店铺</span>
			</h2>
			<div class="list vertical-menu2">
				<ul>
					<#if indexView.newestShops?exists>
						<@show_shops shops=indexView.newestShops isshop=true/>
					</#if>
				</ul>
			</div>

		</div>
		<div class="grid_6">
			<#if _checkin>
				<a class="all-btn green-btn" href="${contextPath}/c/Topic-shopcreate.htm">我要推荐店铺</a>
			<#else>
				<a class="all-btn green-btn" href="javascript:;"  onclick="$('.reg-link').click()">我要推荐店铺</a>
			</#if>
			<h3 class="tag4"><span class="left">热门标签</span></h3>
			<div class="list vertical-menu2 hot-tags shopin-h3-box">
				<ul>
					<#if indexView.hotTags?exists>
						<#list indexView.hotTags as tag>
							<li>
								<a href="${contextPath}/site/Topic-qshops.htm?schKid=${tag.id?c}" title="${tag.name!''}">
									${tag.name!''}
									<span class="tag-num">${tag.hot}</span>
								</a>
							</li>							
						</#list>
					</#if>
				</ul>
			</div>
			<h3 class="tag4"><span class="left">店铺所在商圈</span></h3>
			<div class="list vertical-menu2 shopin-h3-box field-tags">
				<ul>
					<#if indexView.hotAddrs?exists>
						<#list indexView.hotAddrs as tag>
							<li>
								<a href="${contextPath}/site/Topic-qshops.htm?schRid=${tag.id?c}" title="${tag.name!''}">
									${tag.name!''}
									<span class="tag-num">${tag.hot}</span>
								</a>
							</li>							
						</#list>
					</#if>
				</ul>
			</div>
		</div>
		</div>
		
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>