<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店铺-新增图片</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/uploadify/uploadify.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.mask.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>



<script type="text/javascript">
  <!--
   //var imgsrc = "<div id='{0}' class='uploadify-queue-item'><div class='upload-pic'><img src='{1}'></div><div class='upload-info'><div class='upload-info-title'><input id='i_{0}' name='products.icons' type='hidden' value='{2}'><input id='i_{0}' name='products.names' type='hidden' value='{3}'><strong>标题：</strong><input id='t_{0}' data-validation-engine='validate[required]' name='products.titles' type='text'></div><div class='upload-info-desc'><strong>价格：</strong><input id='m_{0}' class='money' data-validation-engine='validate[required]' name='products.prices' type='text'> 元, 小数后不足两位须手动补零</div><div class='upload-info-desc'><strong>简单描述：</strong><textarea id='n_{0}' data-validation-engine='validate[required]' name='products.texts' ></textarea></div><a title='删除图片' href='javascript:delPic(\"{0}\");'>删除图片</a></div>";
   var imgsrc = "<div id='{0}' class='uploadify-queue-item'><div class='upload-pic'><img src='{1}'></div><div class='upload-info'><div class='upload-info-title'><input id='i_{0}' name='products.icons' type='hidden' value='{2}'><input id='i_{0}' name='products.names' type='hidden' value='{3}'><strong>标题：</strong><input id='t_{0}' data-validation-engine='validate[required]' name='products.titles' type='text'></div><div class='upload-info-desc'><strong>价格：</strong><input id='m_{0}' class='money' name='products.prices' type='text'> 元, 小数后不足两位须手动补零</div><div class='upload-info-desc'><strong>简单描述：</strong><textarea id='n_{0}' name='products.texts' ></textarea></div><a title='删除图片' href='javascript:delPic(\"{0}\");'>删除图片</a></div>";
   
  $(document).ready(function(){
	$('#file_upload').uploadify({
				'debug':false,
				'fileObjName':'upfile',
				'swf'      : '${staticWebRoot}/uploadify/uploadify.swf',
				'uploader' : '${contextPath}/c/upload-producticon.htm',
				'button_image_url':'${staticWebRoot}/uploadify',
                'queueID': 'queue',
                'removeCompleted' : true,  //上传在队列成功后，不消失
                'multi'    : true, //可支持多文件上传
                'fileSizeLimit' : '2048KB', //单个文件上传大小限制
                'auto' : false, //取消加入队列的图片自动上传
				'formData' : {},
				'successTimeout':60,
				'removeTimeout':0,
				'onSelect' : function(file) {
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
						var showId = file.id+'_view';
						$('#' + this.settings.queueID).append(imgsrc.format(showId,'${staticWebRoot}/'+r.obj.thumbnailRelativeFilename, r.obj.relativeFilename, file.name));
						
						$('.money').mask('000,000,000,000,000.00', {reverse: true});
					} else  {
						//$('#picStartBtn').show();	
						$('#file_upload').uploadify('cancel', file.id);//出错，删除queue里的文件
					}
				},
		        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
				}
			});  	
  	
  	});
  	
  	function delPic(id) {
  		$("#"+id).remove();
  	}
  	
  	function createProduct() {
  		//$("#dataForm").validationEngine('detach');
  		validForm($("#dataForm"));
  		if (!$("#dataForm").validationEngine('validate')) return;
  		if ($('#dataForm input').length>1) {
			$.ajax({
	    		url: '${contextPath}/c/Topic-productssave.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#dataForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($('#createBtn'), msg);
	    			})) {
	    				window.location.href='${contextPath}/c/Topic-productmanage.htm?id='+${topicId?c};
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
  	}
  //-->
  </script>

</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
	<form id="dataForm" name="dataForm">
	<input type="hidden" id="topicId" name="topicId" value="${topicId?c}">
	<div class="bbs-main">
		<div class="grid_16 bbs-form">
			<h2 class="bbs-list-h2">新增图片</h2>
			<table align="center">
				    <tr>
				    	<td>
				    		<div>请选择图片:</div>
				    		<br/>
				    		<span >你可以上传JPG、JPEG、GIF、PNG或BMP文件。</span>
				    		<br/>
				    		<input type="file" name="file_upload" id="file_upload" >
								<div id="queue"></div>
								<a id="picStartBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('upload','*')">上传</a> 
						      	<a id="picCnlBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('cancel')">取消上传</a>
				    		<p></p>
					    </td>
					</tr>
	
				    <tr>
				    	<td colspan="2" style="padding-top:20px;">
					    	<a href="javascript:;" id="createBtn" onclick = "createProduct()"  class="all-btn green-btn" >确认保存</a>
					    	<!--
					    	<a class="all-btn green-btn" href="javascript:;" onclick="window.history.back()">返回</a>
					    	-->
					    </td>
					</tr>
		    	
	    	</table>
	
		</div>
		<div class="grid_8 discc-form-tips bbs-form-tips">
			<a class="all-btn grey-btn back msg-back" href="javascript:;" onclick="window.history.back()">返 回</a>
			<h3 class="tag4">
				<span class="left main-color">上传图片须知!</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>上传的图片请勿过大，或者长宽比特别大。</li>
				<li>图片尽量干净，切勿有大幅标语或水印</li>
				<li>保存成功后如果图标不更新，你可以用浏览器多刷新几次。</li>
			</ul>
		</div>
	</div>
	</form>		
	<#include "/ftl/inc/foot.ftl">
</body>
</html>