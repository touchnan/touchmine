<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/inc/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理系统</title>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />

<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/jqui/css/jquery.ui.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/jqeasyui/css/panel.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/jqeasyui/css/accordion.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/touchin/css/customer.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/touchin/css/frame.css" />


<script type="text/javascript" src="<%=staticWebRoot %>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqui/jquery.ui.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqlayout/jquery.layout.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqeasyui/plugins/jquery.panel.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqeasyui/plugins/jquery.accordion.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/touchin/js/os.custom.js"></script>

 <script type="text/javascript">
 <!--
	$(document).ready(function() {
		$('body').layout({ applyDefaultStyles: true,maskContents:true});
	    $('body > .ui-layout-center').layout({
	    	maskContents:true,
	    	north:{resizable: false},
	    	south:{resizable: false},
	    	west:{resizable: false},
	    	center:{
	    		triggerEventsOnLoad: true,
	    		onresize:function(pane, $Pane, paneState) {
	    			$("table",$('#cc').document).each(function(i,v) {
	    				//alert($(v).html())
	    					//var gridId = $(this).attr('id');
	    			      //$('#' + gridId).setGridWidth(paneState.innerWidth - 2);
	    			});
	    		}
	    	}
	    });		
		
		$('#mm').accordion({});
		//$('#mm').accordion('select','统计分析');
		
		$('#dialog').dialog({
			autoOpen: false,
			width: 400,
			modal:true,
			resizable:false,
			draggable:true,
			buttons: {
				"保存": msp, 
				"关闭": function() { 
					$(this).dialog("close"); 
				} 
			},
			beforeClose : function() {
				clearPasswd();
			}
		});
		
		$('#dialog input').each(function () {
			$(this).bind('keyup',function(event) {
			    if(event.keyCode==13){  
			    	msp();  
			    }  
			});  
		});		
	});	
 
 	function open1(url) {
 		$('#cc').attr('src',url+getTimestamp( ((url.indexOf("?")==-1) ? "?" : "&") ) );
 	}
 	
 	function logout() {
		$.ajax({
    		url: '<%=contextPath%>/c/main!logout.action'+getTimestamp('?'),
    		type: 'GET',
    		dataType:'json',
    		data:null,
    		success: function(data){
    			window.location.href='<%=contextPath%>';
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
        		alert("网络异常!")
    		}
		}); 		
 	}
 	
 	function passwd() {
 		hideMsg();
 		$('#dialog').dialog('open');
 	}
 	
 	function clearPasswd() {
 		$("#oldPasswd").val('');
 		$("#newPasswd").val('');
 		$("#surePasswd").val('');
 	}
 	
 	function msp() {
 		hideMsg();
 		$("#oldPasswd").val($.trim($("#oldPasswd").val()));
 		$("#newPasswd").val($.trim($("#newPasswd").val()));
		if ($("#oldPasswd").val()=="") {
			showMsg("旧密码不能为空！");
			return false;
		}
		if ($("#newPasswd").val()=="") {
			showMsg("新密码不能为空！");
			return false;
		}
		if ($("#newPasswd").val() != $("#surePasswd").val()) {
			showMsg("新密码输入不一致！");
			return false;			
		}
 		$.ajax({
    		url: '<%=contextPath%>/mgr/user!msp.action'+getTimestamp('?'),
    		type: 'POST',
    		dataType:'json',
    		data:$('#passwdForm').serializeArray(),
    		success: function(data){
    			if (preProcessAjaxData(data)) {
    				showMsg("密码修改成功!");
    				clearPasswd();
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			showNetMsg();
    		}
		}); 		
 		
 		
 		//var msg = $('#passwdMsg');
 		//msg.hide();
 		//$('#passwdMsg').show();
 		//$('#dialog').dialog("close"); 
 		
 	}
 //-->
 </script>
 
</head>
<body>
<div class="ui-layout-center">
	<iframe id="cc" class="ui-layout-center" src="about:blank" frameborder="0" scrolling="auto" style="width:100%;"></iframe>
	<div id="touchin-header" class="ui-layout-north" >
		<table cellpadding="0" cellspacing="0" style="width:100%;">
			<tr>
				<td rowspan="2" style="width:60px;"></td>
				<td style="height:52px;">
					<div style="color:#fff;font-size:22px;font-weight:bold;">
						<a href="#" style="color:#fff;font-size:22px;font-weight:bold;text-decoration:none">他去米管理系统</a>
					</div>
					<div style="color:#fff">
						<a href="#" style="color:#fff;text-decoration:none">&nbsp;</a>
					</div>
				</td>
				<td style="padding-right:5px;text-align:right;vertical-align:bottom;">
					<div id="touchin-topmenu">
						<span style="color:#fff;text-decoration:none">欢迎您,<span style="font-size:22px;font-weight:bold;color:black;"><s:property value="#session['vu@touchin'].nickName"/></span></span>
						<a href="<%=contextPath%>">主页</a>
						<a href="#" onclick="passwd()">密码</a>
						<a href="#" onclick="logout()">退出</a>
					</div>
				</td>
			</tr>
		</table>	
	</div>
	
	<div class="ui-layout-west">
		<div id="mm" class="easyui-accordion" border="false">
		<s:if test="#session['vu@touchin'].typeMgr">
			<div title="STATIST">
				<ul class="pitem">
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/c/statist!cache.action')">缓存</a></li>
				</ul>			
			</div>
			<div title="CRUD">
				<ul class="pitem">
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/mgr/user!users.action')">账号</a></li>
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/mgr/user!cacheUsers.action')">账号缓存</a></li>
				</ul>				
			</div>		
			<div title="TABLE&DOWNLOAD">
				<ul class="pitem">
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/mgr/file!toFtl.action')">freemarker循环</a></li>
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/mgr/file!toDown.action')">struts2循环</a></li>
				</ul>
			</div>
		</s:if>
		<s:else>
			<div title="文件">
				<ul class="pitem">
					<li><a href="javascript:void(0)" onclick="open1('<%=contextPath%>/mgr/charge!statCharge.action')">CRUD DataGrid</a></li>
				</ul>
			</div>			
		</s:else>
		</div>	
	</div>
	
	<div id="touchin-footer" class="ui-layout-south">
			<div>Copyright © 2012-2013 chengqiang.han</div>
	</div>
</div>

<div id="dialog" title="修改密码" >
	<form id="passwdForm">
		<div class="list-msg">
			<p class="list-msg-error">Error:There seems ain later.</p>
		</div>
		<ul class="list def-list">
			<li>
				<label>
					<span class="list-l">旧密码：</span>
					<input type="password" id="oldPasswd" name="user.passwd"/>
				</label>
			</li>		
			<li>
				<label>
					<span class="list-l">新密码：</span>
					<input type="password" id="newPasswd" name="user.newPasswd"/>
				</label>
			</li>
			<li>
				<label>
					<span class="list-l">确认密码：</span>
					<input type="password" id="surePasswd" name="user.surePasswd"/>
				</label>
			</li>	
		</ul>
	</form>
</div>

</body>
</html>