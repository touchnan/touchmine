<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>推荐店铺</title>
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
		//$('#topic\\.icon').val('');
		//$('#topic\\.iconname').val('');  	
  		//$('#file_upload').uploadify('cancel','*');
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
			
			var kind_data = getSelectData('${contextPath}/site/Enum-shopKindForest.htm');
			var region_data = getSelectData('${contextPath}/site/Enum-shopRegionForest.htm');
			var kindLinkage = new Linkage([document.getElementById("topic.kind1"),
			 	document.getElementById("topic.kind2")],
			kind_data,{choice:true,text:"请选择",value:"0"});
			
			var regionLinkage = new Linkage([document.getElementById("topic.region1"),
			 	document.getElementById("topic.region2"),document.getElementById("topic.region3"),
			 	document.getElementById("topic.region4")], 
			region_data,{choice:true,text:"请选择",value:"0"});		
			
			
				
	  	$("#agree").click(function(){
			agree();
		});
	});
	
	function agree(){
		if($('#agree').attr("checked")){
			$('#createBtn').removeClass("disable-btn");
			$('#createBtn').addClass("all-btn green-btn");
		}else{
			$('#createBtn').removeClass("all-btn green-btn");
			$('#createBtn').addClass("disable-btn");
		}
	}	
	
	function createShop(){
		if (!$("#dataForm").validationEngine('validate')) return;

		if (!$.trim($("#topic\\.icon").val())) {
			showPrompt($('#file_upload'), '请上传图片');
			return;
		} else {
			$('#file_upload').validationEngine('hide');
		}
		
		$.ajax({
    		url: '${contextPath}/c/Topic-shopsave.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#dataForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data,function(msg){
    				showPrompt($('#createBtn'), msg);
    			})) {
    				window.location.href='${contextPath}/site/Topic-shopview.htm?id='+data.obj;
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
			<h2 class="bbs-list-h2">推荐店铺</h2>
			<form id="dataForm">
				<ul class="list bbs-list">
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>店铺名称：
							</span>
							<input placeholder="XXX西湖店" data-validation-engine="validate[required,maxSize[30],custom[noSpecialCaracters]" type="text" name="topic.title" id="topic.title" />
							<div class="list-msg">
								<p class="list-msg-suggest">1到30个字符，不允许输入“（）”等非法字符</p>
							</div>							
						</label>
					</li>

					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>店铺所属类别：
							</span>
							<span>
								<select id="topic.kind1" name="topic.kind1" data-validation-engine="validate[required]" >
									<option value="0">请选择</option>
								</select>
							</span>
						</label>	
						<span>
							<select id="topic.kind2" data-validation-engine="validate[required]" name="topic.kind2" >
								<option value="0">请选择</option>
							</select>
						</span>
					</li>
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>店铺地址：
							</span>
							<span>
								<select id="topic.region1" data-validation-engine="validate[required]" name="topic.region1" >
									<option value="0">请选择</option>
								</select>
							</span>
						</label>	
						<span>
							<select id="topic.region2" data-validation-engine="validate[required]" name="topic.region2" >
								<option value="0">请选择</option>
							</select>
						</span>
						<span>
							<select id="topic.region3" data-validation-engine="validate[required]" name="topic.region3" >
								<option value="0">请选择</option>
							</select>
						</span>
						<span>
							<select id="topic.region4" data-validation-engine="validate[required]" name="topic.region4">
								<option value="0">请选择</option>
							</select>
						</span>											
					</li>
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>店铺详细地址：
							</span>
							<input type="text" id="topic.addr" name="topic.addr" placeholder="拱墅区莫干山路XXX号 A座402"  data-validation-engine="validate[required]">
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
								请具体到门牌号码
							</p>
						</div>						
					</li>
					
					<li>
						<label>
							<span class="list-l">
								店铺简介：
							</span>
							<textarea placeholder="店铺主营各式甜点，特别推荐..." rows="4" id="topic.text" name="topic.text" data-validation-engine="validate[maxSize[150]]"></textarea>
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
								简介内容在150字以内
							</p>
						</div>						
					</li>
					<li>
						<label>
							<span class="list-l">
								<span class="form-required">*</span>店铺门面图：
								<input type="hidden" id="topic.icon" name="topic.icon">
								<input type="hidden" id="topic.iconname" name="topic.iconname">
							</span>
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
							请上传店铺主图，一般是店铺门面或者是店铺logo，要求是长宽等比的图形
							</p>							
							
							<input type="file" name="file_upload" id="file_upload" multiple="true"/>
							<div id="queue"></div>
							<a id="picStartBtn" class="all-btn green-btn" href="javascript:upload()">上传</a> 
					      	<a id="picCnlBtn" class="all-btn green-btn" href="javascript:$('#file_upload').uploadify('cancel')">取消上传</a>
						</div>	
					</li>
					<li>
						<label>
							<span class="list-l">
								我要申请加V：
							</span>
							<input type="text" id="topic.vipQuota" data-validation-engine="validate[maxSize[100]" name="topic.vipQuota" value="">
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
							请填写加V理由
							</p>
						</div>							
					</li>
					<li>
						<label>
							<span class="list-l">
								《昕沙》编号：
							</span>
							<input placeholder="xs201209-14" type="text" data-validation-engine="validate[minSize[11],maxSize[11]" id="topic.magazineQuota" name="topic.magazineQuota" value="">
						</label>
						<div style="display:block;" class="list-msg">
							<p class="list-msg-suggest">
							《昕沙》编号为该店铺在杂志中的出版年份和页码
							<br>例如：xs201209-14  表示出版在2012年9月刊的第14页
							</p>
						</div>							
					</li>					
					<li>
						<div class="list-msg">
							<label>
								<input type="checkbox" name="agree" id="agree" data-validation-engine="validate[required] checkbox">
								<span>
									我同意接受
									<span class="main-color">happy112</span>
									的各项
									<a style="display: inline;" target="_blank" href="${contextPath}/site/About-policy.htm">使用条款</a>
									。
								</span>
							</label>

						</div>
					</li>
					<li>
						<div class="list-msg">
							<a id="createBtn" class="disable-btn" href="javascript:;" onclick="createShop()">推荐店铺</a>
							<a href="javascript:;" class="all-btn green-btn" onclick="window.history.back()">取 消</a>
						</div>
					</li>
				</ul>
			</form>
		</div>
		
		<div class="grid_8 discc-form-tips bbs-form-tips">
			<input type="button" class="all-btn grey-btn back msg-back" onclick="window.history.back()" value="返 回" />	
			<h3 class="tag4">
				<span class="left main-color">店铺创建的基本要求</span>
			</h3>
			<ul class="list vertical-menu2">
				<li>1.请填写实体的店铺信息</li>
				<li>2.请仔细阅读<span class="main-color">happy112</span>
的各项
<a target="_blank" href="${contextPath}/site/About-policy.htm">使用条款</a></li>
				<li>《昕沙》杂志上刊登的店铺可以直接通过一定标号规则来查找</li>
				
			</ul>
		</div>
	</div>
	
	<#include "/ftl/inc/foot.ftl">
</body>
</html>