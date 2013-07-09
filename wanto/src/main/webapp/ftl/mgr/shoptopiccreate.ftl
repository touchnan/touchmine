<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>发起话题</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>


<script type="text/javascript">
	<!--

	$(document).ready(function(){
		 $('#dataForm').validationEngine("attach");
		 initEditor();
	});
	
	function initEditor() {
		//$('#post\\.text').xheditor({tools:'mfull'});
		$('#post\\.text').xheditor({tools:'Preview,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,|,Align,List,Outdent,Indent,|,Link,Img,Emot'});
	}
	
  	function createShopTopic() {
  		if (!$("#dataForm").validationEngine('validate')) return;
  		
		$.ajax({
    		url: '${contextPath}/c/Post-shoptopicSave.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#dataForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data,function(msg){
    				showPrompt($('#createBtn'), msg);
    			})) {
    				window.location.href='${contextPath}/site/Post-shoptopic.htm?id='+data.obj;
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			showPrompt($('#createBtn'), xmlHttpRequest.responseText);
    		},
    		beforeSend:function() {
    			loading(); 
    		}
		});  		
  	}	
//-->
</script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
	
	<div class="bbs-main">
		<h2 class="bbs-list-h2">发起话题</h2>
		<div class="grid_17 discc-form">
			<form id="dataForm">
				<input type="hidden" name="post.topicId" value="${topicId?c}"/>
				<input type="hidden" name="post.type" value="${_typepost_topic}"/>
				<ul class="list bbs-list">
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>标题：
							</span>
							<input type="text" id="subject" name="post.subject" data-validation-engine="validate[required],maxSize[30]">
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
								标题名请勿超过30个字符
							</p>
						</div>
					</li>
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>内容：
							</span>
							
							<textarea  data-validation-engine="validate[required,minSize[5]" class="discc-form-textarea " id="post.text" name="post.text"></textarea>
						</label>
					</li>
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>标签：
							</span>
							<input placeholder="美食 搞笑 吐槽 五花八门" data-validation-engine="validate[required,custom[noSpecialCaracters]" type="text" id="bookMark" name="post.bookMark" />
						</label>
					   
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
								至少一个，最多5个，标签之间用空格隔开，比如：糖醋鲫鱼
							</p>
						</div>
					</li>
					<!--
					<li>
						<div class="list-msg">
							<p>特别说明：</p>
							<ul>
								<li>1.话题发起者禁止删除其他人的回复，可以删除该话题，但删除操作会被系统记录。</li>
								<li>2.商店店主</li>
								<li>&nbsp&nbsp1).禁止删除话题内的其他人的回复，</li>
								<li>&nbsp&nbsp2).可以删除话题，但加v验证的店主无权删除话题</li>
							</ul>
						</div>
					</li>
					-->

					<li>
						<div class="list-msg">
							<!-- <button class="all-btn green-btn">提 交</button> -->
							<a href="javascript:;" onclick="javascript:createShopTopic();" class="all-btn green-btn" id="createBtn" >提 交</a>
						</div>
					</li>
				</ul>
			</form>
		</div>
		<div class="grid_7 discc-form-tips">
			<a class="all-btn grey-btn" href="javascript:;" onclick="window.history.back()">返回</a>
			<h3 class="tag4">
				<span class="left main-color">Tip:发帖相关</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>1.请使用文明用语</li>
				<li>2.发起者可以删除自己话题，并会被系统记录</li>
				<li>由于话题被删除后，其他人的回复也被删除，所以我们不建议删除话题</li>
			</ul>
			
		</div>
	</div>


	<#include "/ftl/inc/foot.ftl">
</body>
</html>