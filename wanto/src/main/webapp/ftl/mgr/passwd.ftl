<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息-密码修改</title>

<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">


<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>


<script type="text/javascript">
  <!--
  	$(document).ready(function() {
  		$('#passwdForm').validationEngine("attach");
  		
		$('#passwdForm input').each(function () {
			$(this).bind('keyup',function(event) {
			    if(event.keyCode==13){
			    	msp();
			    }
			}); 
		});   
  	});
  	
 	function msp() {
 		if (!$("#passwdForm").validationEngine('validate')) return;
 		$.ajax({
    		url: '${contextPath}/c/User-msp.htm',
    		type: 'POST',
    		dataType:'json',
    		data:"user.passwd="+$('#oldPasswd').val()+"&user.newPasswd="+$('#newPasswd').val(),
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
		<div class="grid_16 bbs-form">
			<h2 class="bbs-list-h2">修改密码</h2>

			<form id="passwdForm" onsubmit="return msp()">
				<ul class="list bbs-list">
					<li>
						<label>
							<span class="list-l">
								当前密码：
							</span>
							<input type="password" id="oldPasswd" name="user.passwd" data-validation-engine="validate[required,minSize[6],maxSize[20]]" type="password">
							
						</label>
					</li>
					<li>
						<label>
							<span class="list-l">
								新密码：
							</span>
							<input type="password" id="newPasswd" name="user.newPasswd" data-validation-engine="validate[required,minSize[6],maxSize[20]]" type="password">
							
							<div class="list-msg">
								<p class="list-msg-suggest">字母，符号或数字，至少6字符</p>
							</div>
						</label>
					</li>
					<li>
						<label>
							<span class="list-l">
								确认新密码：
							</span>
							<input type="password" id="surePasswd" name="user.surePasswd" data-validation-engine="validate[required,minSize[6],maxSize[20],equals[newPasswd]]">
						</label>
					</li>
					<li>
						<div class="list-msg">
							<a class="all-btn green-btn"  href="javascript:msp();">确认修改</a>
							<a class="all-btn green-btn"  href="${contextPath}/c/User-profile.htm">取 消</a>
						</div>
					</li>
				</ul>
    		</form>
		</div>

		<div class="grid_8 discc-form-tips bbs-form-tips">
			<input type="button" class="all-btn grey-btn back" onclick="window.history.back()" value="返 回" />
			<h3 class="tag4">
				<span class="left main-color">店铺创建的基本要求</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>1.使用标点符号、数字和大小写字母的组合作为密码。</li>
				<li>2.密码中勿包含个人信息（如姓名、生日等）。</li>
				<li>3.不使用和其他网站相同的密码。</li>
				<li>4.定期修改密码。</li>
			</ul>
		</div>

		<!-- <div class="grid_8 bbs-form-tips">
			<h3 class="tag4">
				<span class="left main-color">店铺创建的基本要求</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>1.使用标点符号、数字和大小写字母的组合作为密码。</li>
				<li>2.密码中勿包含个人信息（如姓名、生日等）。</li>
				<li>3.不使用和其他网站相同的密码。</li>
				<li>4.定期修改密码。</li>
			</ul>
		</div> -->
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>