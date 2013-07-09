<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店铺</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/linkage.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>

  <script type="text/javascript">
  <!--
	$(function(){
		
			var kind_data = getSelectData('${contextPath}/site/Enum-shopKindForest.htm');
			var region_data = getSelectData('${contextPath}/site/Enum-shopRegionForest.htm');
			var kindLinkage = new Linkage([document.getElementById("topic.kind1"),
			 	document.getElementById("topic.kind2")],
			kind_data,{choice:true,text:"请选择",value:"0"});
			kindLinkage.setValue([${topic.kind1},${topic.kind2}]);
			
			var regionLinkage = new Linkage([document.getElementById("topic.region1"),
			 	document.getElementById("topic.region2"),document.getElementById("topic.region3"),
			 	document.getElementById("topic.region4")], 
			region_data,{choice:true,text:"请选择",value:"0"});	
			regionLinkage.setValue([${topic.region1},${topic.region2},${topic.region3},${topic.region4}]);
			
			validForm($("#dataForm"));	
	});	
	
	function editShop(){
		if (!$("#dataForm").validationEngine('validate')) return;
		
		$.ajax({
    		url: '${contextPath}/c/Topic-shopsave.htm',
    		type: 'POST',
    		dataType:'json',
    		data:$('#dataForm').serializeArray(),
    		success: function(data){
    			if (preProcessData(data,function(msg){
    				showPrompt($('#editBtn'), msg);
    			})) {
    				window.location.href='${contextPath}/site/Topic-shopview.htm?id='+data.obj;
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			//errorShow(xmlHttpRequest.responseText);
    			showPrompt($('#editBtn'), xmlHttpRequest.responseText);
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
		<h2 class="bbs-list-h2">${topic.title} 店铺管理：<span class="tagname">基本信息</span></h2>
		<div class="mana-info"style="display:block;overflow:hidden;">
			<ul>
				<#if topic.vip>
				<li style="color:#F25C05;">已通过店长验证</li>
				</#if>
				<li>ID：${topic.identity?c}</li>
				<li>推荐时间：${topic.time}</li>
				<li>
					<p class="evaluate"><span class="viewed">${topic.views}</span>人浏览过</p>
					<p class="evaluate"><span class="like">${topic.enjoyments}</span>人喜欢</p>
					<p class="evaluate"><span class="dislike">${topic.boredoms}</span>人不喜欢</p>
				</li>
			</ul>
		</div>
		

		<div class="shop-mana">
			<div class="grid_16 shop-mana-left">
				<h3 class="tag4">
					<span class="left main-color">基本信息</span>
				</h3>
				<div class="list">
					<form id="dataForm">
					<input type="hidden" id="topic.id" name="topic.id" value="${topic.id}">
					<ul class="list mana-list">
						<li>
							<label>
								<span class="list-l">
									店铺名称：
								</span>
								<input value="${topic.title!''}" data-validation-engine="validate[required,maxSize[20],minSize[5],custom[noSpecialCaracters]" type="text" name="topic.title" id="topic.title" />
								<div class="list-msg">
									<p class="list-msg-suggest">5到20个字符，不允许输入“（）”等非法字符</p>
								</div>								
							</label>
						</li>
						<li>
							<label>
								<span class="list-l">
									店铺所属类别：
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
									店铺地址：
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
								<input type="text" id="topic.addr" value="${topic.addr!''}" name="topic.addr" placeholder="拱墅区莫干山路XXX号 A座402"  data-validation-engine="validate[required]">
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
								<textarea rows="4" id="topic.text" name="topic.text" data-validation-engine="validate[maxSize[150]]">${topic.text!''}</textarea>
							</label>
							<div style="display:block;" class="list-msg">
								<p class="list-msg-suggest">
									简介内容在150字以内
								</p>
							</div>
						</li>
						<li>
							<div>
								<span class="list-l">
									店铺主图：
								</span>
								<img alt="店铺门面图" src="${staticWebRoot}/${topic.icon!''}">
								<span>
									<a href="${contextPath}/c/Topic-shopavatar.htm?id=${topic.id}">更改</a>
								</span>
							</div>
						</li>
						<#if !topic.vip>
						<li>
							<label>
								<span class="list-l">
									您是店长？申请加V：
								</span>
								<input type="text" id="topic.vipQuota" name="topic.vipQuota" value="${topic.vipQuota!''}">
							</label>
						</li>
						<li>
							<label>
								<span class="list-l">
									《昕沙》编号：
								</span>
								<input placeholder="xs201209-14" type="text" data-validation-engine="validate[minSize[11],maxSize[11]" id="topic.magazineQuota" name="topic.magazineQuota" value="${topic.magazineQuota!''}">
							</label>
							<div style="display:block;" class="list-msg">
								<p class="list-msg-suggest">
								《昕沙》编号为该店铺在杂志中的出版年份和页码
								<br>例如：xs201209-14  表示出版在2012年9月刊的第14页
								</p>
							</div>							
						</li>						
						</#if>
					</ul>
					
					<#if topic.vip>
					<h3 class="tag4" style="border-bottom: 1px solid #DDD;">
						<span class="orange">店长特权：</span>
					</h3>
					<ul class="list mana-list">
						<li>	
						
							<label>
								<span class="list-l">
									《昕沙》编号：
								</span>
								<input type="text" readonly=true disabled=true value="${topic.magazineQuota!''}">
							</label>
							<div style="display:block;" class="list-msg">
								<p class="list-msg-suggest">
								《昕沙》编号为该店铺在杂志中的出版年份和页码
								<br>例如：xs201209-14  表示出版在2012年9月刊的第14页
								</p>
							</div>
						</li>
						<li>
							<label>
								<span class="list-l">
									店铺电话：
								</span>
								<input type="text" id="topic.phone" name="topic.phone" value="${topic.phone!''}">
							</label>
						</li>
						<li>
							<label>
								<span class="list-l">
									店长说：
								</span>
								<input type="text" id="topic.said" name="topic.said" value="${topic.said!''}">
							</label>
						</li>
					</ul>
					</#if>
					<div class="list mana-list">
						<li>
							<div class="list-msg">
								<a href="javascript:;" id="editBtn" onclick="editShop()" class="all-btn green-btn">更新信息</a>
								<a href="javascript:;" onclick="window.history.back()" class="all-btn green-btn">取 消</a>
							</div>
						</li>					
					</div>
				</form>

				</div>
				
			</div>
			<div class="grid_8 shop-mana-right">
				<a class="all-btn grey-btn back" href="${contextPath}/c/Topic-shopmanage.htm">返回 所有店铺管理</a>

				<h3 class="tag4">
					<span class="left main-color">店铺管理传送门</span>
				</h3>
				<ul class="list vertical-menu2">
					<li><a href="${contextPath}/site/Topic-shopview.htm?id=${topic.id}">查看店铺效果</a></li>
					<li><a href="${contextPath}/c/Topic-productmanage.htm?id=${topic.id}">图片管理</a></li>
					<li><a href="${contextPath}/c/Topic-shoptoipcmanage.htm?id=${topic.id}">话题管理</a></li>
				</ul>

				<h3 class="tag4">
					<span class="left main-color">Tip:店铺内容修改相关</span>
				</h3>
				<ul class="list vertical-menu2">
					<li>1.店铺加v后就可以发布“店长说”</li>
					<li>2.经常更新店铺图片，可以保持图片内容的新鲜度</li>
				</ul>
				

			</div>
			
		</div>

	</div>
	
	<#include "/ftl/inc/foot.ftl">
</body>
</html>