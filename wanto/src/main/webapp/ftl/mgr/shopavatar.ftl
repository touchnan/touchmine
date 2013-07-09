<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>修改图片</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/uploadify/uploadify.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/linkage.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>

  <script type="text/javascript">
  <!--
  
  	var imgsrc = "<div id='{0}' class='uploadify-queue-item'><div class='upload-pic'><img src='{1}'></div><a title='删除图片' href='javascript:delpic(\"{2}\");'>删除图片</a></div>";
  	
  	function delpic(fileId) {
		$('#topic\\.icon').val('');
		$('#topic\\.iconname').val('');
  		$('#file_upload').uploadify('cancel', fileId);
  	}
  	
  	function upload() {
  		//上传清除所有的信息再上传
  		$('#file_upload').uploadify('upload','*');
  	}
  	
	$(function(){
		validForm($("#dataForm"));
		$('#file_upload').uploadify({
				'debug':false,
				'queueSizeLimit':1,
				//'uploadLimit':1,
				'fileObjName':'upfile',
				'swf'      : '${staticWebRoot}/uploadify/uploadify.swf',
				'uploader' : '${contextPath}/c/upload-topicicon.htm',
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
					//$('#picStartBtn').hide();
					//$('#picCnlBtn').show();
		        },
		        'onUploadComplete':function(file){
		        	//$('#picCnlBtn').hide();
		        	//$('#picStartBtn').hide();
		        },
				'onUploadProgress' : function(file, bytesUploaded, bytesTotal, totalBytesUploaded, totalBytesTotal) {
			    },
				'onUploadSuccess' : function(file, data, response) {
					var r = $.parseJSON(data);
					if (r.obj && r.obj.success) {
						$('#' + file.id).find('.data').html(' 上传完毕');
						$('#topic\\.icon').val(r.obj.relativeFilename);
						$('#topic\\.iconname').val(file.name);
												
						var showId = file.id+'_view';
						
						$('#'+this.settings.queueID).append(imgsrc.format(showId, '${staticWebRoot}/'+r.obj.relativeFilename, showId));
						
					} else  {
						//$('#picStartBtn').show();	
						$('#file_upload').uploadify('cancel', file.id);//出错，删除queue里的文件
						$('#topic\\.icon').val('');
						$('#topic\\.iconname').val('');						
					}
				},
				'onCancel':function(file) {
				},
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				}
			});
		
	});
	
	function modifyshop(){
		if (!$.trim($("#topic\\.icon").val())) {
			showPrompt($('#file_upload'), '请上传图片');
			return;
		} else {
			$('#file_upload').validationEngine('hide');
		}
		
		$.ajax({
    		url: '${contextPath}/c/Topic-avatarsave.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#dataForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data,function(msg){
    				showPrompt($('#createBtn'), msg);
    			})) {
    				window.location.href='${contextPath}/c/Topic-shopedit.htm?id=${topic.id?c}';
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			//errorShow(xmlHttpRequest.responseText);
    			showPrompt($('#createBtn'), xmlHttpRequest.responseText);
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
	<@main_menu idx="shops"/>
	
	<div class="bbs-main">
		<div class="grid_16 bbs-form">
			<h2 class="bbs-list-h2">修改店铺主图</h2>
			<table align="center">
				<form id = "dataForm">
					<input type="hidden" id="topic.icon" name="topic.icon">
					<input type="hidden" id="topic.iconname" name="topic.iconname">
					<input type="hidden" id="topic.id" name="topic.id" value='${topic.id?c}'>				
				    <tr>
				    	<td valign="top">
				    		<#if topic.icon ?exists>
								<img src="${staticWebRoot}/${topic.icon!''}"  alt="店铺图片" style="float:left;margin:1px 18px 1px 0"/>
							</#if>
				    	</td>
				    	<td>
				    		<div>从电脑中选择你喜欢的照片:</div>
				    		<br/>
				    		<span >你可以上传JPG、JPEG、GIF、PNG或BMP文件。</span>
				    		<br/>
							
							<input type="file" name="file_upload" id="file_upload" multiple="true"/>
							<div id="queue"></div>
							<a style="display:none" id="picStartBtn" class="all-btn green-btn" href="javascript:upload()">上传</a> 
					      	<a style="display:none" id="picCnlBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('cancel')">取消上传</a>
				    		<p></p>
					    </td>
					</tr>
	
				    <tr>
				    	<td colspan="2" style="padding-top:20px;">
					    	<a id="createBtn" href="javascript:;" onclick = "modifyshop()"  class="all-btn green-btn" >确认修改</a>
					    	<a class="all-btn green-btn" href="javascript:;" onclick="window.history.back()">取 消</a>
					    </td>
					</tr>
		    	</form>
	    	</table>
		</div>
		
		<div class="grid_8 discc-form-tips bbs-form-tips">
			<input type="button" class="all-btn grey-btn back msg-back" onclick="window.history.back()" value="返 回" />
			<h3 class="tag4">
				<span class="left main-color">上传店铺图片须知!</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>请上传店铺主图，一般是店铺门面或者是店铺logo，要求是长宽等比的图形</li>
			</ul>
		</div>
	</div>
			
	<#include "/ftl/inc/foot.ftl">
</body>
</html>