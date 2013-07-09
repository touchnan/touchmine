<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>注册</title>
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
     var imgsrc = "<div id='{0}' class='uploadify-queue-item'><div class='upload-pic'><img src='{1}'></div><a title='删除图片' href='javascript:delpic(\"{2}\");'>删除图片</a></div>";
     
     function delpic(fileId) {
		$('#user\\.uploadAvatar').val('');
		$('#file_upload').uploadify('cancel', fileId);
  	}
     
	 $(document).ready(function(){
	 
	 	bindSelect('${contextPath}/site/Enum-schools.htm',document.getElementById('schoolSelect'),true);
	  
	 	$('#regForm').validationEngine("attach");
	 	$("#email").blur(function(){
			  var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
	      	  var email_val = $("#email").val();
	      	  if(search_str.test(email_val)){
		     	$.ajax({
		    		url: '${contextPath}/site/User-emailExist.htm',
		    		type: 'GET',
		    		dataType:'json',
		    		data:"fieldValue="+email_val,
		    		success: function(data){
						if(data['validateReturn'][1] == false){
							$("#emailMsg").css("display","block");
							$("#emailMsg_error").text(data['validateReturn'][2]);
						}else{
							$("#emailMsg_error").text('');
							$("#emailMsg").css("display","none");
						}		    			
		    		},
		    		error: function(xmlHttpRequest, textStatus, errorThrown){
		    			alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
		    		}
				});
		     }
		});
		
		$("#checkService").click(function(){
			checkService();
		});
		
		checkService();
		
		$('#file_upload').uploadify({
			'debug':false,
			'queueSizeLimit':1,
			//'uploadLimit':1,
			'fileObjName':'upfile',
			'swf'      : '${staticWebRoot}/uploadify/uploadify.swf',
			'uploader' : '${contextPath}/c/upload-avatar.htm',
			'button_image_url':'${staticWebRoot}/uploadify',
            'queueID': 'queue',
            'removeCompleted' : true,  //上传在队列成功后，不消失
            'multi'    : false, //可支持多文件上传
            'fileSizeLimit' : '2048KB', //单个文件上传大小限制
            'auto' : false, //取消加入队列的图片自动上传
			'formData' : {},
			'successTimeout':60,
			'removeTimeout':0,
			'buttonText' : '选择文件',
			'fileTypeDesc' : 'Image Files',
    		'fileTypeExts' : '*.gif; *.jpg; *.png',
			'onSelect' : function(file) {
				$('#file_upload').validationEngine('hide');
				$("#picStartBtn,#picCnlBtn").fadeIn("fast");
	        },
			'onUploadStart' : function(file) {
	        },
	        'onUploadComplete':function(file){
	        },
			'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
		    },
			'onUploadSuccess' : function(file, data, response) {
				var r = $.parseJSON(data);
				if (r.obj && r.obj.success) {
					$('#' + file.id).find('.data').html(' 上传完毕');
					$('#user\\.uploadAvatar').val(r.obj.relativeFilename);
											
					var showId = file.id+'_view';
					
					$('#'+this.settings.queueID).append(imgsrc.format(showId, '${staticWebRoot}/'+r.obj.relativeFilename, showId));
					
				} else  {
					//$('#picStartBtn').show();	
					$('#file_upload').uploadify('cancel', file.id);//出错，删除queue里的文件
					$('#user\\.uploadAvatar').val('');
				}
			},
			'onCancel':function(file) {
			},
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
			}
		}); 			
		
	});
	function checkService(){
		if(($('#checkService').attr("checked") == undefined)){
			$('#submit').removeClass("all-btn green-btn");
			$('#submit').addClass("disable-btn");
				//控制提交按钮
			}else{
				//控制提交按钮
				$('#submit').removeClass("disable-btn");
				$('#submit').addClass("all-btn  green-btn");
			}
	}

	 function register() {
		if(!$('#regForm').validationEngine('validate')){
			return 
		};
	 	if(($('#checkService').attr("checked") == undefined)){
			alert('请查看服务条款和隐私权政策并同意!');
			return
		} 
		
		$.ajax({
		    		url: '${contextPath}/site/User-registered.htm',
		    		type: 'POST',
		    		dataType:'json',
		    		//data:"user.username="+$('#email').val()+"&user.passwd="+$('#password').val()+"&user.nickname="+$('#name').val()+"&user.school="+$("#schoolSelect").find("option:selected").text(),
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
	<@main_menu idx="none"/>
<div class="bbs-main">
	
	<div class="grid_16 bbs-form">
		<h2 class="bbs-list-h2">注 册</h2>
		<form id = "regForm" onSubmit="return register();">
			<input type="hidden" id="user.uploadAvatar" name="user.uploadAvatar">
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
					<label>
						<span class="list-l">
							<span class="form-required">*</span>昵称：
						</span>
						<input class="validate[required,maxSize[30]]" type="text" name="user.nickname" id="name" /> 
					</label>
				   
					<div style="display:none;" class="list-msg">
						<p class="list-msg-error">Error:There seems to have been an issue, please try again later.</p>
					</div>
				</li>
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>登录密码：
						</span>
						<input type="password" class="validate[required,minSize[8],maxSize[20]]" type="text" name="user.passwd" id="password" >
					</label>
				</li>
				<li>
					<label>
						<span class="list-l">
							<span class="form-required">*</span>确认密码：
						</span>
						<input type="password" class="validate[required,equals[password]]" type="text" name="confirmPwd" id="confirmPwd" >
					</label>
				</li>
				<li>
					<label>
						<span class="list-l">
								上传头像：
						</span>

					</label>
					<div style="display:block;" class="list-msg">
						<input type="file" name="file_upload" id="file_upload" >
						<div id="queue"></div>
						<a style="display:none;" id="picStartBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('upload','*')">上传</a> 
				      	<a style="display:none;" id="picCnlBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('cancel')">取消上传</a>
					</div>
				</li>
				<li>
					<label>
						<span class="list-l">
							学校：
						</span>
						<span>
								<select name="user.schoolId" id="schoolSelect" class="validate[required]">
									<option value="0">请选择</option>
								</select>
						</span>
					</label>	
				</li>
				<li>
					<label>
						<span class="list-l">
							标签：
						</span>
						<input name="user.label" type="text"  placeholder="活泼 好动 大一">
					</label>
					<div class="list-msg" style="display:block;">
						<p class="list-msg-suggest">
							请用空格空开，单个标签长度不超过10个字
						</p>
					</div>
				</li>
				<li>
					<div class="list-msg">
						<label>
							<input type="checkbox" value="yes" id="checkService">
							<span>
								我同意接受<span class="main-color">happy112</span>的各项<a href="${contextPath}/site/About-policy.htm" target="_blank">使用条款</a>。
							</span>
						</label>
					</div>
				</li>   
				<li>
					<div class="list-msg">
						<!-- <button class="disable-btn" id="submit" type="submit">提 交</button> -->
						<a href="javascript:register();" id="submit" id="submit"  class="disable-btn">提 交</a>
						<a class="all-btn green-btn" href="${contextPath}">取 销</a>
						
					</div>
				</li>
			</ul>
		</form>
	</div>
	<div class="grid_8 discc-form-tips bbs-form-tips">
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