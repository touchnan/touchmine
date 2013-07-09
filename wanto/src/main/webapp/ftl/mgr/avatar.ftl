<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>个人信息-头像修改</title>
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
  	
 	function msp() {
 		$.ajax({
    		url: '${contextPath}/c/User-avatarSave.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#avatarForm').serializeArray(),
    		success: function(data){
    	
    			if (preProcessData(data, function (msg) {
	    				errorShow('aaaa',1,data['errorVo']['message']);
	    			})) {
	    				window.location.href='${contextPath}/c/User-profile.htm';
	    			}	
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			showNetMsg();
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
		<h2 class="bbs-list-h2">修改头像</h2>
		<table align="center">
			<form id = "avatarForm">
				<input type="hidden" id="user.uploadAvatar" name="user.uploadAvatar">
			    <tr>
			    	<td valign="top">
						<img src="${staticWebRoot}/${user.avatar!'${_avatar}'}"  alt="我的头像" style="float:left;margin:1px 18px 1px 0"/>
			    	</td>
			    	<td>
			    		<div>从电脑中选择你喜欢的照片:</div>
			    		<br/>
			    		<span >你可以上传JPG、JPEG、GIF、PNG或BMP文件。</span>
			    		<br/>
			    		<input type="file" name="file_upload" id="file_upload" >
							<div id="queue"></div>
							<a id="picStartBtn" style="dispaly:none;" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('upload','*')">上传</a> 
					      	<a style="dispaly:none;" id="picCnlBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('cancel')">取消上传</a>
			    		<p></p>
					  
				    </td>
				</tr>

			    <tr>
			    	<td colspan="2" style="padding-top: 20px; display: block;">
			    
				   		<!-- <input type="submit" value="确认修改密码" class="butt"> -->
				    	<a href="javascript:;" onclick = "msp()"  class="all-btn green-btn" >确认修改</a>
				    	<a class="all-btn green-btn" href="${contextPath}/c/User-profile.htm">取 消</a>
				    </td>
				</tr>
	    	</form>
    	</table>
	</div>

	<div class="grid_8 discc-form-tips bbs-form-tips">
		<input type="button" class="all-btn grey-btn back" onclick="window.history.back()" value="返 回" />
		<h3 class="tag4">
			<span class="left main-color">给你自己扮酷!</span>
		</h3>
		<ul class="list vertical-menu2">
			<li>上传的图片请勿过大，或者长宽比特别大。</li>
			<li>建议上传近距离的照片（比如大头照、特写），这样经过编辑后的头像会很清楚。</li>
			<li>保存成功后如果图标不更新，你可以用浏览器多刷新几次。</li>
		</ul>
	</div>
</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>