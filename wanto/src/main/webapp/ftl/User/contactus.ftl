<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>联系我们</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/uploadify/uploadify.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>


<script type="text/javascript">
  <!--
     
	 $(document).ready(function(){
		 	//validForm ($('#contactForm'));
		 	
	});

	 function contact_us() {
		if(!$('#contactForm').validationEngine('validate')){
			return 
		};
		$.ajax({
    		url: '${contextPath}/site/User-sendContactus.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#contactForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data)) {
    				alert("已经发送MEAIL给管理员！")
    				<#if  request.getParameter('url')?exists >
    					window.location.href="${request.getParameter('url')}";
    				<#else>
    					window.location.href="${contextPath}";
    				</#if>    				
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
    		},
    		beforeSend:function() {
    			loading(); 
    		}    		
		});
	 }
	 
	function init() {
		$('#contactForm').validationEngine("attach");
		$('#contactForm input').each(function () {
			$(this).bind('keyup',function(event) {
			    if(event.keyCode==13){
			    	contact_us();
			    }
			}); 
		}); 		
	}	 
    //-->
  </script>
</head>
<body onload='init()'>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
<div class="bbs-main">
	<!-- <span class="right small orange">系统消息</span> -->
	<div class="grid_16 bbs-form">
		<h2 class="bbs-list-h2">联系我们</h2>
		您可以通过下面的方式联系我们，我们将在最短的时间内以<span class="main-color">www_happy112_com@gmail</span>的邮箱名给您回复
		<form  id="contactForm">
			<ul class="list bbs-list contact">
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>标题：
						</span>
						<input type="text" data-validation-engine="validate[required,maxSize[30]" name="user.title">
					</label>
				</li>
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>内容：
						</span>
						<textarea placeholder="填写您想对我们说的话..." name="user.content" data-validation-engine="validate[required,minSize[5]]" class="discc-form-textarea"></textarea>
					</label>
				</li>
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>您的邮箱：
						</span>
						<input placeholder="注意切换至英文输入法" data-validation-engine="validate[required,custom[email]]" type="text" name="user.username" id="email">
					</label>
				   
					<div style="display:block;" class="list-msg">
						<p class="list-msg-suggest">
							方便我们在第一时间把情况反馈给您
						</p>
					</div>
				</li>
				<li>
					<div class="list-msg">
						<!-- <button class="all-btn blue-btn">提 交</button> -->
						<a href="javascript:contact_us();" id="submit" id="submit"  class="all-btn green-btn">提 交</a>
						<a href="#" class="all-btn green-btn">取 消</a>
					</div>
				</li>
			</ul>
		</form>

		
	</div>
	<div class="grid_8 discc-form-tips bbs-form-tips">
		<a class="all-btn grey-btn back msg-back" href="${contextPath}">返 回 首 页 </a>
		<h3 class="tag4">
			<span class="left main-color">happy112网相关内容</span>
		</h3>
		<ul class="list vertical-menu2">
			<li><a href="${contextPath}/site/About-policy.htm">happy112的使用协议</a></li>
			<li><a href="${contextPath}/site/About-about.htm">关于happy112</a></li>
		</ul>
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>