<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>店铺</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/scripts/jqpagination/css/pagination.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.masonry.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.mask.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>
</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
	
	
	<div class="bbs-main">
		<div class="grid_17 image-show">
			
			
			<h2 id="title_show" class="disc-h2"><span id="title_val">${topicView.title}</span><#if (_checkin && topicView.keeper)><a id="titleEditBtn" class="disc-del" href="#">&#9668编辑</a></#if></h2>
			<#if (_checkin && topicView.keeper)>
			<div class="disc-title" style="display:none;">
				<form id="tpoicTitleForm">
				<input type="hidden" name="topic.id" value="${topicView.id?c}">
				<input type="text" id="topicTitle" name="topic.title" data-validation-engine='validate[required]' oldvalue="${topicView.title}" value="${topicView.title}">
				</form>
				<a class="all-btn grey-btn" href="javascript:;" onclick="modifyTitle()">完成</a>
				<a class="all-btn grey-btn" href="javascript:;" onclick="cancelTitle()">取消</a> 
			</div>
			</#if>
			<div class="disc-con">
				<div class="image-pic">
					<img src="${staticWebRoot}/${topicView.icon}" style="max-width: 636px;">
					<#if (_checkin && topicView.keeper)>
					<a class="disc-edit" href="${contextPath}/c/Topic-productavatar.htm?id=${topicView.id?c}">&#9650编辑图片</a>
					</#if>
				</div>
				<div id="text_show">
					<div class="image-disc">
						<span id="text_val">
						${topicView.text!''}
						</span>
					</div>
					<#if (_checkin && topicView.keeper)>
					<a id="textEditBtn" class="disc-edit" href="javascript:;">&#9668编辑</a>
					</#if>
				</div>
				<#if (_checkin && topicView.keeper)>
					<form id="tpoicTextForm">
					<input type="hidden" name="topic.id" value="${topicView.id?c}">
					<div class="disc-edit-box" style="display:none;">
						<textarea cols="88" id="topicText" name="topic.text" oldvalue="${topicView.text!''}" rows="5">${topicView.text!''}</textarea>
						<a class="all-btn grey-btn" href="javascript:;" onclick="modifyText()">完成</a>
						<a class="all-btn grey-btn" href="javascript:;" onclick="cancelText()">取消</a>
					</div>
					</form>	
				</#if>
				
				<div id="price_show">
					<div>
						<span id="price_val"><#if (topicView.price>0)>${topicView.priceYuan!''}</#if></span><span id="yuan_val" style="display:<#if (topicView.price<=0)>none</#if>">元</span>
					</div>
					<#if (_checkin && topicView.keeper)>
					<a id="priceEditBtn" href="javascript:;">&#9668编辑价格</a>
					</#if>
				</div>
				<#if (_checkin && topicView.keeper)>
					<form id="tpoicPriceForm">
					<input type="hidden" name="topic.id" value="${topicView.id?c}">
					<div id="priceBox" style="display:none;">
						<input type="text" class='money' id="topicPrice" name="topic.pricedecimal" oldvalue="${topicView.priceYuan}" value="${topicView.priceYuan}">
						<a class="all-btn grey-btn" href="javascript:;" onclick="modifyPrice()">完成</a>
						<a class="all-btn grey-btn" href="javascript:;" onclick="cancelPrice()">取消</a>
					</div>
					</form>	
				</#if>				
			</div>
			
			<div class="disc-agree">
				<#if _checkin>
					<#if !topicView.keeper>
						<a href="javascript:;" class="like" onclick="enjoyTopic(${topicView.id?c})"><span></span>顶顶顶</a>
						<a href="javascript:;" class="dislike" onclick="boredTopic(${topicView.id?c})"><span></span>踩一下</a>
					</#if>
				<#else>
					<a href="javascript:;" class="like" onclick="$('.reg-link').click()"><span></span>顶顶顶</a>
					<a href="javascript:;" class="dislike" onclick="$('.reg-link').click()"><span></span>踩一下</a>					
				</#if>				
			</div>
			

			<div id="Result" class="disc-answer">
				<!-- <h3>回 复</h3> --> 
			</div>
			<ul class="bbs-page">
				<div id="Pagination" class="pagination"></div>
			</ul>
			<div class="disc-myanswer">
				<!-- <h3>我来回应</h3> -->
				

			<#if _checkin>
				<div class="disc-myanswer">
					<form id="replyForm">
						<input type="hidden" name="post.topicId" value="${topicView.id?c}"></input>
						<textarea id="replytext" rows="5" cols="88" class="" name="post.text"></textarea>
						<a class="all-btn green-btn" id="replybtn" href="javascript:;" onclick='reply()'>回 复</a>
					</form>
				</div>
			</#if>			
				
			</div>

		</div>

		<div class="grid_7 image-relative">
			<div class="disc-info">
				<div class="disc-writer">
					<a href="#">
						<img width="80" height="80" src="${staticWebRoot}/${topicView.owner.avatar!'${_avatar}'}">
					</a>
					<a href="#" class="disc-details">
						${topicView.owner.nickname!''}
					</a>
					<ul class="tags">
						<#if (topicView.owner?exists && topicView.owner.tags?exists)>
							<#list topicView.owner.tags as tag>
								<li>${tag.name}</li>
							</#list>
						</#if>
						<#if !topicView.owner.hometownPrivate && (topicView.owner.hometownId>0)>
							<li>${topicView.owner.hometown!''}</li>
						</#if>
						<#if !topicView.owner.schoolprivate && (topicView.owner.schoolId>0)>
							<li>${topicView.owner.school!''}</li>
						</#if>				
					</ul>
					<#if topicView.vip>
					<p>图片来源:<span class="orange">店长发布</span></p>
					</#if>
					<span>最后编辑时间：${topicView.etime}</span>
					<div><span class="viewed" id="">${topicView.views}</span>人浏览</div>
					<ul class="evaluate">
						<li><span class="like" id="enjoy_${topicView.id}">${topicView.enjoyments}</span>人顶</li>
						<li><span class="dislike" id="bored_${topicView.id?c}">${topicView.boredoms}</span>人踩</li>
					</ul>
				</div>
			</div>

			<a href="${contextPath}/site/Topic-shopview.htm?id=${topicView.parentId}" class="all-btn grey-btn back">回到店铺</a>
			<#if _checkin && topicView.keeper>
				<a href="${contextPath}/c/Topic-productmanage.htm?id=${topicView.parentId}" class="all-btn green-btn back">图片管理(增、删等)</a>
			</#if>

			<h3 class="tag4"><span class="left">本店铺最热图片</span></h3>
			<div class="list vertical-menu2 shop-hotimages">
				<ul>
					<#if topicView.topicProducts?exists>
						<#list topicView.topicProducts as product>
							<li class="shop-gs">
								<a href="${contextPath}/site/Topic-productview.htm?id=${product.id}" class="shop-gsImageLink">
									<img src="${staticWebRoot}/${product.thumbnail!''}" alt="${product.title!''}" class="shop-gsImage">
								</a>
								<span class="images-sequence">NO.${product_index+1}</span>
							</li>							
						</#list>
					</#if>				

				</ul>
			</div>
			
			<!--
			<a href="#" title="举报">举报不良信息</a>
			-->
		</div>

	</div>
	
	<#include "/ftl/inc/foot.ftl">
	
	
 <script type="text/javascript">
 	 <!--
    var pageIndex = 0;     //页面索引初始值  
    var pageSize = 10;     //每页显示条数初始化，修改显示条数，修改这里即可    
	var init=true;
	//var post_str = "<div class='disc-answer-result'><div style='overflow:hidden;'><div class='disc-answer-floor'><p><span class='second'>测试楼层{0}</span>L：</p><span class='disc-action-time'>{1}</span></div><div class='disc-answer-con'><p>{2}</p></div></div><a class='disc-del' href='{0}'>&#9650删除</a><div class='disc-user'><a class='disc-details' href='#'>{3}</a><a href='#'><img width='50' height='50' src='{4}'></a></div></div>"
	
	var post_str ='<div class="disc-answer-base">'+
		'<div class="disc-answer-floor"><p><span class="first">{0}</span>L：</p><span class="disc-action-time">{1}</span></div>'+
		'<div class="disc-answer-con">{2}</div>'+
		'<div class="disc-del"><a class="disc-reply" href="javascript:;">&#9650回应</a></div>'+
		'<div class="disc-user"><a class="disc-details" href="javascript:;">{4}</a><a href="javascript:;"><img width="50" height="50" src="{5}"></a></div></div>'+
		'<div class="disc-edit-box" style="display:none;"><form id="rForm_{3}"><textarea cols="50" id="text_{3}" name="post.text" rows="6"></textarea><input name="post.parentId" type="hidden" value="{3}"></form><a class="all-btn green-btn reply" href="javascript:;" onclick="reply2(this,{3})">回 复</a><a class="all-btn green-btn cancel">取 消</a></div>';		

	
	
	//翻页调用  
	function PageCallback(pageIndex, jq) {
	     $.ajax({
	        type: "POST",  
	        dataType: "text",  
	        url: '${contextPath}/site/Topic-productposts.htm?id=${topicView.id}',      //提交到一般处理程序请求数据  
	        data: "page=" + (pageIndex + 1) + "&rp="+pageSize ,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
	        success: function(data) { 
	       	  data = $.parseJSON(data);
	         	$("#Result").text('');        //移除Id为Result的表格里的行，从第二行开始（这里根据页面布局不同页变）

					var sub_str = '<div class="disc-answer-reply"><div class="disc-answer-floor"><p>回应</p><span class="disc-action-time">{0}</span></div><div class="disc-answer-con">{1}</div><div class="disc-del"></div><div class="disc-user"><a class="disc-details" href="javascript:;">{2}</a><a href="#"><img width="50" height="50" src="{3}"></a></div></div>';
						
               	    var a_trs = []  
			          for(var i=0 ;i< data.pageData.rows.length;i++){
			           		var row = data.pageData.rows[i];
			           		a_trs.push('<div class="disc-answer-result">');
			           		a_trs.push(post_str.format(row.lvl, row.time, row.text, row.id, row.userNickname,wrapAvatar(row.userAvatar)));
			           		
			           		if (row.replies && row.replies.length>0) {
			           			var sub_s = [];
			           			for (var j=0; j<row.replies.length; j++) {
			           				var s_row = row.replies[j];
			           				sub_s.push(sub_str.format(s_row.time, s_row.text,s_row.userNickname, wrapAvatar(s_row.userAvatar)));
			           			}
			           			a_trs.push(sub_s.join(""));
			           		}			           		
			           		a_trs.push('</div>');
			          }
			          $("#Result").append(a_trs.join(""));

	          buttonShows();
	        },
	        
			beforeSend:function() {
				if (init) {
					init=false;
				} else{
					loading();
				}
			}                
	    });
	} 

  	function reply2(thiz,postId) {
  		<#if _checkin>
			if ($('#text_'+postId).val()==='') {
				showPrompt($(thiz),"回复内容不能为空");
				return;
			}
			$.ajax({
				url:'${contextPath}/c/Post-reply.htm',
				type:'POST',
				dataType:'json',
				data:$('#rForm_'+postId).serializeArray(),
				success: function(data){
					if (preProcessData(data)) {
	    				location.reload();
	    			}
				},
			    error: function(xmlHttpRequest, textStatus, errorThrown){
				    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
				}
			});  			
  		</#if>
  	}

<#if (_checkin && topicView.keeper)>

		function modifyTitle() {
			if (!$("#tpoicTitleForm").validationEngine('validate')) return;
			$this = $(this);
			$.ajax({
	    		url: '${contextPath}/c/Topic-producteditbaseinfo.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#tpoicTitleForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($this, msg);
	    			})) {
	    				var obj = $('#topicTitle');
	    				obj.attr("oldvalue",obj.val());
	    				$('#title_val').text(obj.val());
	    				
						$(".disc-title").hide();
						$('#title_show').show();    				
	    			}
	    		},
	    		error: function(xmlHttpRequest, textStatus, errorThrown){
	    			showPrompt($('#createBtn'), xmlHttpRequest.responseText);
	    		},
	    		beforeSend:function() {
	    			loading(); 
	    		}
			});  			

		}
		
		function cancelTitle() {
			var obj = $('#topicTitle');
			obj.val(obj.attr("oldvalue"));
			$(".disc-title").hide();
			$('#title_show').show();
		}
		
		function modifyText() {
			if (!$("#tpoicTextForm").validationEngine('validate')) return;
			$this = $(this);
			$.ajax({
	    		url: '${contextPath}/c/Topic-producteditbaseinfo.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#tpoicTextForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($this, msg);
	    			})) {
	    				var obj = $('#topicText');
	    				obj.attr("oldvalue",obj.val());
	    				$('#text_val').text(obj.val());
	    					    			
						$(".disc-edit-box").hide();
						$('#text_show').show();   				
	    			}
	    		},
	    		error: function(xmlHttpRequest, textStatus, errorThrown){
	    			showPrompt($('#createBtn'), xmlHttpRequest.responseText);
	    		},
	    		beforeSend:function() {
	    			loading(); 
	    		}
			});
						

		}
		function cancelText() {
			var obj = $('#topicText');
			obj.val(obj.attr("oldvalue"));		
			$(".disc-edit-box").hide();
			$('#text_show').show();		
		}


		function modifyPrice() {
			if (!$("#tpoicPriceForm").validationEngine('validate')) return;
			$this = $(this);
			$.ajax({
	    		url: '${contextPath}/c/Topic-producteditbaseinfo.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#tpoicPriceForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($this, msg);
	    			})) {
	    				var obj = $('#topicPrice');
	    				obj.attr("oldvalue",obj.val());
	    				$('#price_val').text(obj.val());
	    				
						$("#priceBox").hide();
						$('#price_show').show();
						var price=1; 
						try {
							price = parseInt(obj.val().replace(',','').replace('.',''));
						} catch(e) {
						}
						if (obj.val()!='' && price>0 ) {
							$('#price_val').show();
							$('#yuan_val').show();
						} else {
							$('#price_val').hide();
							$('#yuan_val').hide();
						}
	    			}
	    		},
	    		error: function(xmlHttpRequest, textStatus, errorThrown){
	    			showPrompt($('#createBtn'), xmlHttpRequest.responseText);
	    		},
	    		beforeSend:function() {
	    			loading(); 
	    		}
			});  			

		}
		
		function cancelPrice() {
			var obj = $('#topicPrice');
			obj.val(obj.attr("oldvalue"));
			$("#priceBox").hide();
			$('#price_show').show();
		}
</#if>
	  
   $(document).ready(function(){
		<#if (_checkin && topicView.keeper)>
			// 点击 编辑 
			$("#titleEditBtn").click(function(){
				$(".disc-title").show();
				$(this).parent().hide();
				return false;
			})
			
			$("#textEditBtn").click(function(){
				$(".disc-edit-box").show();
				$(this).parent().hide();
				return false;
			})
			
			$("#priceEditBtn").click(function(){
				$("#priceBox").show();
				$(this).parent().hide();
				return false;
			})			
			
			$('.money').mask('000,000,000,000,000.00', {reverse: true});
			
			validForm($("#tpoicTitleForm"));
			validForm($("#tpoicTextForm"));
		</#if>  
		
         $("#Pagination").pagination(${count!'0'}, {  
            callback: PageCallback,  
            prev_text: '<<',       //上一页按钮里text  
            next_text: '>>',       //下一页按钮里text  
            items_per_page: pageSize,  //显示条数  
            num_display_entries: 6,    //连续分页主体部分分页条目数  
            current_page: pageIndex,   //当前页索引  
            num_edge_entries: 2        //两侧首尾分页条目数  
    	});
    	
    	initEditor();
	});     
        
	function initEditor() {
		//$('#replytext').xheditor({tools:'mfull'});
		$('#replytext').xheditor({tools:'Preview,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,|,Align,List,Outdent,Indent,|,Link,Img,Emot'});
		
	}        
        
  	<#if _checkin>
		function reply(){
			if ($('#replytext').val()==='') {
				showPrompt($('#replybtn'),"回复内容不能为空");
				return;
			}
			$.ajax({
				url:'${contextPath}/c/Post-reply.htm',
				type:'POST',
				dataType:'json',
				data:$('#replyForm').serializeArray(),
				success: function(data){
					if (preProcessData(data)) {
	    				location.reload();
	    			}
				},
			    error: function(xmlHttpRequest, textStatus, errorThrown){
				    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
				}
			});
		}   	
  	
  		function enjoyTopic(topicId) {
			voteTopc('${contextPath}/c/Topic-enjoyTopic.htm?id='+topicId,'#enjoy_'+topicId);
  		}
		
		function boredTopic(topicId) {
			voteTopc('${contextPath}/c/Topic-boredTopic.htm?id='+topicId,'#bored_'+topicId);
		} 		
		
		function voteTopc(url,voteId) {
			$.ajax({
	    		url: url,
	    		type: 'GET',
	    		dataType:'json',
	    		data:null,
	    		success: function(data){
	    			if (preProcessData(data, function (msg) {
	    				//FIXME 此处需要修改
	    				errorView("你已经投过票了!");
	    				//$.show({ message: "你已经投过票了!"});
	    			})) {
	    			    var voteShow =  $(voteId);
	    			    var count = parseInt(voteShow.text().replace(",",""))+1;
	    				voteShow.text(number_format_commas(count));
	    			}
	    		},
	    		error: function(xmlHttpRequest, textStatus, errorThrown){
	    			errorView(xmlHttpRequest.responseText);
	    		},
	    		beforeSend:function() {
	    			loading(); 
	    		}    		
			});		
		}
 		
  	</#if>
  	
  	function buttonShows(){
  		$(".disc-answer-base, .disc-answer-reply").hover(
		  function () {
		    $(this).find(".disc-del>a").fadeIn("fast");

		    // css("display","block");
		  },
		  function () {
		    $(this).find(".disc-del>a").fadeOut("fast");
		    // $(this).find(".disc-del>a").css("display","none");
		  }
		);

		$("#titleEditBtn, #textEditBtn").click(function(){
			$(this).parent().next().show();
			$(this).parent().hide();
			return false;
		})
		//点击 回复 淡入textarea框
		$(".disc-reply").click(function(){
			$(this).parent().parent().next().fadeIn("fast");	
			return false;
		})
		//点击 取消编辑textarea框
		$(".disc-edit-box >.cancel").click(function(){
			$(this).siblings("textarea").val("");
			$(this).parent().fadeOut("fast");				
			return false;
		})
		//点击 确认编辑textarea框
		$(".disc-edit-box >.reply").click(function(){
			var textarea = $(this).siblings("textarea").val();
			$(this).parent().fadeOut("fast");				
			return false;
		})

		//删除自己的回复
		$(".disc-delete").click(function(){
			$(this).parent().siblings().remove(".disc-user").siblings(".disc-answer-con").html("该回复已删除... ");
			// $(this).parent().siblings(".disc-answer-con").html("该回复已删除... ");
			return false;
		})
  	}
  	
  //-->
  </script>	
</body>

</html>