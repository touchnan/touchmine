<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>忘记密码</title>
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
	 	$('#regForm').validationEngine("attach");
	});
		
	 function register() {
		if(!$('#regForm').validationEngine('validate')){
			return 
		};
		
		$.ajax({
		    		url: '${contextPath}/site/User-sendPwdUrl.htm',
		    		type: 'POST',
		    		dataType:'json',
		    		data:$('#regForm').serializeArray(),
		    		success: function(data){
			    			if (preProcessData(data, function (msg) {
			    				errorShow('aaaa',1,data['errorVo']['message']);
			    			})) {
			    				window.location.href='${contextPath}/c/User-profile.htm';
			    			}	    			
		    		},
		    		error: function(xmlHttpRequest, textStatus, errorThrown){
		    			alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
		    		}
				});
		
	 }
    //-->
  </script>
</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
<div class="bbs-main">
	<h2 class="bbs-list-h2">忘记密码</h2>
	<div class="grid_16 bbs-form">
		<form id = "regForm" onSubmit="return register();">
			<ul class="list bbs-list">
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>邮箱：
						</span>
						<input class="validate[required,custom[email]]" type="text" name="user.username" id="email"  />  
					</label>
						<div style="display:none;" class="list-msg" id="emailMsg">
							<p class="list-msg-error" id ="emailMsg_error"></p>
						</div>
				</li>
				<li>
					<div class="list-msg">
						<!-- <button class="disable-btn" id="submit" type="submit">提 交</button> -->
						<a href="javascript:register();" id="submit" id="submit"  class="all-btn  green-btn">提 交</a>
						<a class="all-btn green-btn" href="${contextPath}">取 销</a>
						
					</div>
				</li>
			</ul>
		</form>
	</div>
	<div class="grid_8 bbs-form-tips">
		<h3 class="tag4">
			<span class="left main-color">欢迎加入 happy112</span>
		</h3>
		<ul class="list vertical-menu2">
			<li>请在注册前仔细阅读happy112的各项使用守则</li>
			<li>happy112中所有店铺信息都为实体店信息</li>
		</ul>
	</div>
</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>