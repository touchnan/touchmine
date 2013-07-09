<#if vu?exists>
	<#assign _checkin = true>
	<#assign _nickname = vu.nickname!''>
	<#assign _loginId = vu.loginId!''>
<#else>
	<#assign _checkin = false>
</#if>

<#macro show_pic pic="" alt="">
	<#if pic?exists && pic!=''>
		<img src="${pic}" alt="${alt}">
	</#if>
</#macro>

<#assign _avatar='images/avatar.jpg'>
<#assign _typepost_topic=1>
<#assign _typepost_reply=2>

<#macro main_menu idx="profile">
<div class="bbs-topmenu">
	<div class="bbs-topmenu-main">
		<h1>1+1=2</h1>
		<ul class="menu1">
			<li  class="menu1-li <#if idx=='main'>active</#if>">
				<a href="${contextPath}"><span>首页</span></a>
			</li>
			<li  class="menu1-li <#if idx=='shops'>active</#if>">
				<a href="${contextPath}/site/Topic-shops.htm"><span>店铺</span></a>
			</li>
			<li  class="menu1-li <#if idx=='circles'>active</#if>">
				<a href="javascript:;"><span>圈子(近期开启)</span></a>
			</li>
			<li  class="menu1-li <#if idx=='actives'>active</#if>">
				<a href="javascript:;"><span>活动(近期开启)</span></a>
			</li>
			
			<form id="sForm" method="post" style='margin:0;padding:0' target="_blank" action="${contextPath}/site/Topic-qshops.htm" onsubmit=" $sch = $('#sch_top');$sch.val($.trim($sch.val())); return ''!==$sch.val();">
			<li class="bbs-searchbar">
					<!-- <select>
						<option value="1">店铺</option>
					</select> -->
					
						<input style="border-radius: 14px 14px 14px 14px;" type="text" id="sch_top" name="sch" value="${sch!''}"/>
					<a style="float: right;" href="javascript:;" onclick="javascript:$('#sForm').submit();">
						<img style="width:20px;" src="${staticWebRoot}/images/search.png"></img>
					</a>
			</li>
			</form>
			
			<#if !_checkin>
				<li class="menu1-li menu1-li-right">
					<a class="reg-link" href="javascript:;">
						<span>注册/登录</span>
					</a>
				</li>
			<#else>
				<li  class="menu1-li menu1-li-right">
					<a href="javascript:;">
						<span>您好 ${_nickname}</span>
						<span> 欢迎光临!</span>
					</a>
					<ul class="menu1-con" style="display:none;">
						<#--
						<li>
							<a href="${contextPath}/bg/BgMgr-wordbooks.htm"><span>后台管理</span></a>
						</li>	
						-->				
						<li>
							<a href="${contextPath}/c/User-profile.htm"><span>个人主页</span></a>
						</li>
						<li>
							<a href="${contextPath}/c/User-profile.htm"><span>设置</span></a>
						</li>
						<#--<li>
							<a href="${contextPath}/c/User-news.htm"><span>消息</span></a>
						</li>-->
						<li>
							<a href="javascript:;" onclick="logout()"><span>退出</span></a>
						</li>
					</ul>
				</li>				
			</#if>			
		</ul>
	</div>
</div>
</#macro>

  <script type="text/javascript">
  <!--
	
	var loadingMsg = '<h1><img src="${staticWebRoot}/images/busy.gif" /> 处理中...</h1>';
	
	function wrapAvatar(a) {
		return  '${staticWebRoot}/'+(a ? a : '${_avatar}');
	}  
	
  
	$(document).ready(function(){
		try {
			$(document).ajaxStop($.unblockUI);
		} catch(e){}
	
		var title = $(".bbs-topmenu-main ul li.active").text();
		if (title) {
			document.title =  "1+1=2 论坛"+title;
		}
		
		/**
		$(".menu1 li a").click(function() {
			var flag = ($(".menu1-con",$(this).parent()).css("display") =="block");
			if(flag){
				$(".menu1-con",$(this).parent()).fadeOut("fast");
				$(this).addClass("red");
			}else{
				$(".menu1-con").fadeOut("fast");	
				$(this).removeClass("red");
				$(".menu1-con",$(this).parent()).slideToggle("400");
			}
		});

		$(document.body).bind("click",function(e){
			var $target = $(e.target);
			var a = $target.parent().hasClass("menu1-li");
			var b = $target.parent().parent().hasClass("menu1-li");
			if((!a)&&(!b)){
				$(".menu1-con").fadeOut("fast");
			}
		});
		*/
	});	
	
	function contract_us() {
		window.location.href = '${contextPath}/site/User-contactus.htm?url='+encodeURI(window.location.href);
	}
  //-->
  </script>

<#if !_checkin>
  <script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
  <script type="text/javascript">
  <!--
  
  	 $(document).ready(function(){
		$(".reg-link").click(function(){
			$("#inner-reg").show();
			return false;
		}) 
		$("#inner-close").click(function(){
			$("#inner-reg").hide();
		})  	
		
		$("#loginName").blur(function(){
			 checkName();
		});
		$("#passwd").blur(function(){
			 checkPassword();
		});
		 
		 
		$('#lform input').each(function () {
			$(this).bind('keyup',function(event) {
			    if(event.keyCode==13){
			    	auth();
			    }
			}); 
		}); 		 
  	 });
  	 
	 function checkName(){
	   	var email_p = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i;
	 	$("#loginName").val($.trim($("#loginName").val()));
		var loginName = $("#loginName").val();
	 	if (loginName=="") {
			showMsg("#errorMsg","Email不能为空！");
			return false;
		}
		if (!email_p.test(loginName)){
           showMsg("#errorMsg","邮箱格式不正确！");
           return false;
       }	
       hideMsg("#errorMsg");
       return true;
	 }
	 
	 function checkPassword(){
	 	if ($.trim($("#passwd").val())=="") {
			showMsg("#errorMsg","密码不能为空！");
			return false;
		}
	 	if ($.trim($("#passwd").val()).length <6) {
			showMsg("#errorMsg","密码不能少于6位！");
			return false;
		}
		hideMsg("#errorMsg");
		 return true;
	 }
	 
	 function auth() {
	    hideMsg("#errorMsg");
		if((!checkName()) || (!checkPassword())){
			return;
		}
		$.ajax({
    		url: '${contextPath}/auth-login.htm',
    		type: 'GET',
    		dataType:'json',
    		data:$("#lform").serializeArray(),
    		success: function(data){
    			if (preProcessData(data, function (msg) {
    				
    				showMsg("#errorMsg",data['errorVo']['message']);
    			})) {
    				//window.location.href='${contextPath}/c/User-profile.htm';
    				window.location.href=window.location.href;
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			errorView(xmlHttpRequest.responseText);
    		},
    		beforeSend:function() {
    			loading(); 
    		}    		
		});
	 	return false;
	 }
  //-->
  </script>
  
  
<div id="inner-reg" class="inner-reg-box" style="display:none;">
<h2>登 录<span><a href="${contextPath}/site/User-registers.htm">我要注册！</a></span></h2>
<form id="lform" onsubmit="return auth()">
	<ul class="innerlogin-list">
		<li>
			<label>
				<span class="list-l">
					Email：
				</span>
				<input style="width:60%;" id="loginName" name="loginName" type="text" placeholder="XXX@xx.com" value="" />
			</label>
		</li>
		<li>
			<label>
				<span class="list-l">
					密 码：
				</span>
				<input id="passwd" name="passwd" style="width:60%;" type="password" placeholder="123456" />
			</label>
		</li>
		<li>
			<div class="list-msg">
				<a href="javascript:;" class="all-btn green-btn" onclick="auth()">登 录</a>
				<label style="margin-left: 32px;">
					<input type="checkbox">记住我
            	</label>
			</div>
		</li>
		<li>
			<div id ="errorMsg" class="list-msg" style="display:none;">
				<p class="list-msg-error">用户名密码不匹配，请重新输入</p>
			</div>
		</li>
		<li>
			<a href="${contextPath}/site/User-forgetPwd.htm">忘记密码 &#10132 </a>
		</li>
	</ul>
</form>
<a id="inner-close" class="inner-close-btn" title="关闭" href="javascript:;">关闭</a>
</div>
<#else>
 <script type="text/javascript">
  <!--
	 function logout() {
		$.ajax({
    		url: '${contextPath}/auth-logout.htm',
    		type: 'GET',
    		dataType:'json',
    		data:null,
    		success: function(data){
    			if (preProcessData(data)) {
    				window.location.href='${contextPath}';
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
    		}
		});
	 	return false;
	 }
  //-->
  </script>
</#if>