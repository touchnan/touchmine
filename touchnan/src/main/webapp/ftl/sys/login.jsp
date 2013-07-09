<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/inc/inc.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6 lt8"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7 lt8"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8 lt8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理应用系统</title>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />

<link rel="stylesheet" type="text/css" href="<%=staticWebRoot %>/touchin/css/login.css" />

<script type="text/javascript" src="<%=staticWebRoot %>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/touchin/js/os.custom.js"></script>

 <script type="text/javascript">
 <!--
	$(document).ready(function() {
	});	
 
 	function login() {
 		<s:if test="notHtml5Compatible">
 			showMsg("");
 			if ($.trim($("#loginName").val())=="") {
 				showMsg("用户名不能为空！");
 				return false;
 			}
 			if ($.trim($("#passwd").val())=="") {
 				showMsg("密码不能为空！");
 				return false;
 			}
 		</s:if>
 		<s:else>
 			$('.list-msg').hide();
 		</s:else>
		$.ajax({
    		url: '<%=contextPath%>/c/main!login.action'+getTimestamp('?'),
    		type: 'GET',
    		dataType:'json',
    		data:$("#lform").serializeArray(),
    		success: function(data){
    			if (preProcessAjaxData(data)) {
    				window.location.href='<%=contextPath%>';
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
 
</head>
<body>
     <div class="container">
         <header>
             <h1><span>他去米</span>管理系统</h1>
         </header>
         <section>				
             <div id="container_section" >
                 <div id="wrapper">
                     <div id="login" class="animate form">
                         <form  method="post" id="lform" onsubmit="return login()" autocomplete="on"> 
                             <h1>登录</h1>
							 <div class="list-msg">
							 	<p class="list-msg-error"></p>
							 	<br>
							 </div>                       
                             <p>
                                 <label for="loginName" data-icon="u" > 用户名 </label>
                                 <input id="loginName" name="loginName" required="required" type="text" placeholder="请输入用户名" value="<s:property value='loginName'/>" />
                             </p>
                             <p>
                                 <label for="passwd" data-icon="p"> 密码 </label>
                                 <input id="passwd" name="passwd" required="required" type="password" placeholder="请输入密码" /> 
                             </p>
                             <!-- 
                             <p> 
                                 <label for="imgcode" data-icon=""> 验证码  
                                   <img id="checkCode" src="<%=contextPath%>/imageValidate" align="absmiddle"></img>
                                   <a href="#toregister" class="to_register">看不清？&nbsp;换张图片。</a>
                                 </label>
                                 <input id="imgcode" name="imgcode" required="required" type="text" placeholder="eg. X8df!90EO" /> 
                             </p> 
                              -->
                             <p class="login button"> 
                                 <input type="submit" value="Login" /> 
							 </p>
                         </form>
                     </div>
                 </div>
             </div>  
         </section>
     </div>
</body>
</html>