<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>创建帖子</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>

<script type="text/javascript">
	<!--
	
	function replySubmit(){
		
		$.ajax({
			url:'${contextPath}/c/Post-postReplySubmit.htm',
			type:'POST',
			dataType:'json',
			data:$('#reForm').serializeArray(),
			success: function(data){
		    		window.location.href='${contextPath}/site/Post-postShow.htm?postId='+data.obj;
			   	},
		    error: function(xmlHttpRequest, textStatus, errorThrown){
			    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
			    }
		});
	}
	
	function votePost(flag){
		$.ajax({
			url:'${contextPath}/site/Post-incresPostParaCount.htm',
			type:'POST',
			dataType:'json',
			data:'flag='+flag+'&postId='+$('#postId').val(),
			success:function(data){
					if(flag == 3){
						$('#usefulCount').html(data.obj);
					}else if(flag ==4){
						$('#unusefulCount').html(data.obj);
					}
				},
			error: function(xmlHttpRequest, textStatus, errorThrown){
				    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
				    }
		});
	}
	function showTextEdit(type){
		if(type == 1){
			$('#')
		}else if(type == 2){
			
		}
	}
	-->
</script>

</head>
<body>
<input type="hidden" id="postId" name="postId" value="${postDto.id}" />
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="posts"/>
	
	<#macro show_reply posts>
		<#if posts?exists>
			<#list posts as post>
				<div class="disc-answer-result">
					<div style="overflow:hidden;">
						<div class="disc-answer-floor">
							<p><span class="second">${post_index+1}</span>L：</p>
							<span class="disc-action-time">
								<#if post.time?exists>
									${post.time}
								</#if>	
							</span>
						</div>
						<div class="disc-answer-con">
							<#if post.text?exists>
								<p>${post.text}</p>
							</#if>
						</div>
					</div>
					<div class="disc-user">
						<a class="disc-details" href="#">
							<#if post.postUser?exists>
								${post.postUser}
							</#if>
						</a>
						<a href="#">
							<#if post.userAvatar ?exists>
								<img src="${staticWebRoot}/${user.avatar!''}"  alt="发帖人头像" onerror="http://img3.douban.com/icon/ul48723877-5.jpg" />
							<#else>
								<img src="http://img3.douban.com/icon/ul48723877-5.jpg"  alt="发帖人头像" onerror="http://img3.douban.com/icon/ul48723877-5.jpg" />
							</#if>
						</a>
					</div>
				</div>
			</#list>
		</#if>		
	</#macro>
	
	<div class="bbs-main shop-disc">
		

		<div class="grid_17 disc-topl">
			<h2 class="disc-h2">${postDto.subject}</h2>
			<div class="disc-con">
				<div>
					<div class="disc-con-p">
					${postDto.text}
					</div>
				</div>
			</div>
			<div class="disc-agree">
				<a href="javascript:votePost(3)" class="like" title="赞一个"><span>赞图标</span>顶顶顶</a>
				<a href="javascript:votePost(4)" class="dislike" title="我不赞同"><span>踩图标</span>踩一下</a>
			</div>
			<div class="disc-answer">
				<!-- <h3>回 复</h3> -->
				
				<#if list_post?exists>
						<@show_reply posts=list_post />
				</#if>
				
			</div>
			<ul class="bbs-page">
				<li><span class="prev">&lt;&lt;</span></li>
				<li><a href="#">1</a></li>
				<li><span class="thispage">2</span></li>
				<li><a href="#">3</a></li>
				<li><a href="#">4</a></li>
				<li><a href="#">5</a></li>
				<li><span class="break">...</span></li>
				<li><a href="#">30</a></li>
				<li>
					<span class="next">
						<a href="#">&gt;&gt;</a>
					</span>
				</li>
				

			</ul>
			<div class="disc-myanswer">
			<form id="reForm">
				<input type="hidden" name="postDto.type" value="2"></input>
				<input type="hidden" name="postDto.parentId" value="${postDto.id}"></input>
				<!-- <h3>我来回应</h3> -->
				<textarea  rows="5" cols="88" name="postDto.text"></textarea>
				<a class="all-btn green-btn" href="javascript:replySubmit();">回 复</a>
			</div>
			</form>
			
		</div>
		<div class="grid_7 disc-topr">
			<div class="disc-info">
				<div class="disc-writer">
					<a href="#">
						<img width="80" height="80" src="../img/goodssmallpic_2.jpg_40x40.jpg">
					</a>
					<a class="disc-details" href="#">
						mudenglong
					</a>
					<ul class="tags">
						<li>大一</li>
						<li>湖北</li>
						<li>浙江工商大学</li>
					</ul>
					<p>图片来源:<span class="orange">店长发布</span></p>
					<span>发表于：${postDto.time}</span>
					<div><span class="viewed">${postDto.clickCount}</span>人浏览</div>
					<ul class="evaluate">
						<li><span class="like" id="usefulCount">${postDto.usefulCount}</span>人顶</li>
						<li><span class="dislike" id="unusefulCount">${postDto.unusefulCount}</span>人踩</li>
					</ul>
				</div>
			</div>

			<a class="all-btn grey-btn back" href="bbs.shop.html">回到店铺</a>

			<h3 class="tag4"><span class="left">其他话题</span></h3>
			<table class="list vertical-menu2 shop-table disc-table">
				<tbody>
					<tr>
				    	<td style="width:86%;">话题</td>
				    	<td nowrap="nowrap" style="width:14%;" class="td2">回应</td>
					</tr>
				    <tr>
				        <td>
				            <a href="bbs.discussic-show.html" title="Oh My God">Oh My God 太坑爹太坑爹坑爹</a>
				        </td>
				        <td nowrap="nowrap" class="td2">12345</td>
				    </tr>
				    <tr>
				        <td>
				            <a href="bbs.discussic-show.html" title="天空之城">天空之城</a>
				        </td>
				        <td nowrap="nowrap" class="td2">12345</td>
				    </tr>
				</tbody>
			</table>
		</div>
		
	</div>

	<#include "/ftl/inc/foot.ftl">
</body>
</html>