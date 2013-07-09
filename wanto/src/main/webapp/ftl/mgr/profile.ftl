<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>个人信息</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>

<script type="text/javascript">
  <!--
  
   $(document).ready(function(){
  		 var schoolId = ${user.schoolId!"0"}
  		 var hometownId = ${user.hometownId!'0'}
  		 bindSelect('${contextPath}/site/Enum-schools.htm',document.getElementById('schoolSelect'),true,schoolId);
  		 bindSelect('${contextPath}/site/Enum-shopRegion1s.htm',document.getElementById('hometown'),true,hometownId);
	 	$('#regForm').validationEngine("attach");
	 	
	});
		    
  
  
	 function updateInfo() {
		 if(!$('#regForm').validationEngine('validate')){
			return 
		};
	 	if(!($('#schoolprivate').attr("checked") == undefined)){
	 		$('#schoolprivate').val("true")
	 	}else{
	 		$('#schoolprivate').val("false")
	 	}
	 	if(!($('#hometownPrivate').attr("checked") == undefined)){
	 		$('#hometownPrivate').val("true")
	 	}else{
	 		$('#hometownPrivate').val("false")
	 	}
	 	if(!($('#searched').attr("checked") == undefined)){
	 		$('#searched').val("true")
	 	}else{
	 		$('#searched').val("false")
	 	}
	 	
		$.ajax({
    		url: '${contextPath}/c/User-updateProfile.htm',
    		type: 'POST',
    		dataType:'json',
    		//data:"user.nickname="+$('#name').val()+"&user.school="+$("#schoolSelect").find("option:selected").text()+"&user.schoolprivate="+schoolprivate+"&user.hometown="+$("#hometown").find("option:selected").text()+"&user.hometownPrivate="+hometownPrivate+"&user.searched="+searched+"&user.mood="+$('#mood').val(),
    		data:$('#regForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data)) {
    				window.location.href='${contextPath}/c/User-profile.htm';
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
  
</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="none"/>
	<#if !count ?exists>
		<#assign count = 0>
	</#if>
	<div class="bbs-main">
		
		<#include "/ftl/inc/profile-menu.ftl">
		<@profile_menu idx="profile"/>
		
		<div class="left man-right">
			<div class="man-right-con">
				<h2 class="bbs-list-h2">${ar.nickname}的个人信息<span class="right small">${user.regTimeStr!''} 注册</span>
					<span class="right small">ID：${ar.username!''}</span></h2>
				<div class="account-list">
					<form id = "regForm">
						<ul class="list mana-list">
							<li>
								<label>
									<span class="list-l">
										昵称： 
									</span>
									<input class="validate[required,minSize[2],maxSize[10]]" type="text" value="${user.nickname!''}" name="user.nickname" id="name">
								</label>
								<div class="list-msg" style="display:none;">
									<p class="list-msg-error">Error:格式限制.</p>
								</div>
							</li>
							
							<li>
								<div>
									<span class="list-l">
										我的头像:
									</span>
										<img src="${staticWebRoot}/${user.avatar!'${_avatar}'}"  alt="我的头像"  />
											
									<span>
										<a href="${contextPath}/c/User-avatar.htm">更改</a>
									</span>
								</div>
							</li>
							<li>
								<div>
									<span class="list-l">
										我的学校：
									</span>
									<span>
										<select id="schoolSelect" name="user.schoolId" class="validate[required]" value ='${user.schoolId!'0'}'>
											<option value="0">请选择</option>
										</select>
									</span>
									<label>
										<input type="checkbox" id ="schoolprivate" ${user.schoolprivate?string('checked','')} name = "user.schoolprivate">
										<span>
											仅自己可见
										</span>
									</label>
								</div>
							</li>
							<li>
								<label>
									<span class="list-l">
										标签：
									</span>
									<input name="user.label" type="text"  placeholder="活泼 好动 大一" value ='${user.label!''}'>
								</label>
								<div class="list-msg" style="display:block;">
									<p class="list-msg-suggest">
										请用空格空开，单个标签长度不超过10个字
									</p>
								</div>
							</li>
							<li>
								<label>
									<span class="list-l">
										心情：
									</span>
									<input class="validate[maxSize[100]]" type="text" id ="mood" value="${user.mood!''}" name="user.mood">
								</label>
								<div class="list-msg" style="display:none;">
									<p class="list-msg-error">Error:字数限制.</p>
								</div>
							</li>
							
							<li>
								<div>
									<span class="list-l">
										我的家乡：
									</span>
									<span>
										<select id="hometown" name="user.hometownId" class="validate[required]" value ='${user.hometownId!'0'}'>
											<option value="0">请选择</option>
										</select>
									</span>
									<label>
										<input type="checkbox" ${user.hometownPrivate?string('checked','')} name="user.hometownPrivate" id = "hometownPrivate">
										<span>
											仅自己可见
										</span>
									</label>
								</div>
							</li>
							<li>
								<div>
									<span class="list-l">
										密码:
									</span>
									<span style="line-height: 28px;">
										<a href="${contextPath}/c/User-passwd.htm">更改</a>
									</span>
								</div>
							</li>
							<li>
								<div class="list-msg">
									<label>
										<input type="checkbox" ${user.searched?string('checked','false')} name= "user.searched" id="searched">
										<span>
											可以通过个人信息搜索到我
										</span>
									</label>

								</div>
							</li>
							<li>
								<div class="list-msg">
									<!-- <button class="all-btn green-btn">提 交</button> -->
									<a href="javascript:updateInfo();" id="submit" onclick="updateInfo()" class="all-btn green-btn" href="#">更新信息</a>
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
			
		</div>
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>