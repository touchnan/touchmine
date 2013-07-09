<#macro profile_menu idx="profile">
	<div class="left man-left">
		<div class="models">
			<h3 class="tag1 main-color">
				<span>用 户</span>
			</h3>
			<ul class="list vertical-menu3">
				<li <#if idx=='news'>class="active"</#if> >
					<a href="${contextPath}/c/User-reply.htm" title="最新动态">
						最新动态
					</a>
				</li>
				<li <#if idx=='profile'>class="active"</#if> >
					<a href="${contextPath}/c/User-profile.htm" title="个人信息">个人信息</a>
				</li>
			</ul>
			<h3 class="tag1 main-color">
				<span>管 理</span>
				</h3>
			<ul class="list vertical-menu3">
				<li <#if idx=='mshop'>class="active"</#if> >
					<a href="${contextPath}/c/Topic-shopmanage.htm" title="店 铺">
						店 铺
					</a>
				</li>
				<!--
				<li <#if idx=='circle'>class="active"</#if> >
					<a href="#" title="圈子">圈 子</a>
				</li>
				-->
			</ul>
		</div>
	</div>
</#macro>

<script type="text/javascript">
  <!--	
	$(document).ready(function(){
		document.title = $("div.man-left li.active").text();
	});
  //-->
</script>	


