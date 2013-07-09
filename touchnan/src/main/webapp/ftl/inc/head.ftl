<#assign _KEY_VU='vu@touchin'>
<#if session.getAttribute?exists>
	<#assign _checkin = session.getAttribute(_KEY_VU)?exists>
	<#assign _vu = session.getAttribute(_KEY_VU)!''>
<#else>
	<#assign _checkin = false>
</#if>
<div class="bbs-topmenu">
	<div class="bbs-topmenu-main">
		<h1>MuDL</h1>
		<ul class="menu1">
			<#if !_checkin>
			<li  class="menu1-li">
				<a href="javascript:login()">
					<span>注册/登录</span>
				</a>
			</li>
			</#if>
			<li  class="menu1-li">
				<a href="javascript:;">
					<span>菜单 </span>
				</a>
				<ul class="menu1-con" style="display:none;">
				<#if !_checkin>
					<li>
						<a href="bbs.register.html" target = "_blank">
							<span>注册/登录</span>
						</a>
					</li>
				<#else>
					<li>
						<a href="bbs.account.html" target = "_blank">
							<span>个人主页</span>
						</a>
					</li>
					<li>
						<a href="#"><span>设置</span></a>
					</li>
					<li>
						<a href="#"><span>消息</span></a>
					</li>				
				</#if>
					<li>
						<a href="bbs.shop-index.html"><span>店铺们</span></a>
					</li>
					<li>
						<a href="bbs.circle-index.html"><span>圈子</span></a>
					</li>
					<li>
						<a href="javascript:;"><span>活动</span></a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div>

  <script type="text/javascript">
  <!--
	$(document).ready(function(){
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
	});	
  //-->
  </script>

<#if !_checkin>
  <script type="text/javascript">
  <!--
	 function login(){
	 	$('#ldiv').show();
	 }
	 
	 function auth() {
 		<@s.if test="notHtml5Compatible">
 			showMsg("");
 			if ($.trim($("#loginName").val())=="") {
 				showMsg("用户名不能为空！");
 				return false;
 			}
 			if ($.trim($("#passwd").val())=="") {
 				showMsg("密码不能为空！");
 				return false;
 			}
 		</@s.if>
 		<@s.else>
 			$('.list-msg').hide();
 		</@s.else>
		$.ajax({
    		url: '${contextPath}/auth!login.htm',
    		type: 'GET',
    		dataType:'json',
    		data:$("#lform").serializeArray(),
    		success: function(data){
    			if (preProcessData(data)) {
    				//$('.bbs-topmenu .menu1'){
    				//}
    				window.location.href='${contextPath}';
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			showNetMsg();
    		}
		});
	 	return false;
	 }
  //-->
  </script>
  
<div id="ldiv" method="post" style="top:300px;display:none;border:1px solid #e0e0e0;">
<form id="lform" onsubmit="return auth()">
	 <div class="list-msg">
	 	<p class="list-msg-error">aaaa</p>
	 	<br>
	 </div>  
		登录：
	<br/>
	<label data-icon="u" style="overflow: hidden;width: 350px;">
		<span>
			邮 箱：
		</span>
		<input id="loginName" name="loginName" required="required" type="text" placeholder="邮 箱" value="" />
	</label>
	<br/>
	<label style="overflow: hidden;width: 350px;">
		<span>
			密 码：
		</span>
		<input id="passwd" name="passwd" required="required" type="password" placeholder="密码" />
	</label>
	<input type="submit">
</form>
</div>
</#if>