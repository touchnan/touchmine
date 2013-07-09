<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主题</title>
<link type="text/css" href="${staticWebRoot}/style/validationEngine.jquery.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/scripts/jqpagination/css/pagination.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine-zh_CN.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.validationEngine.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqpagination/js/jquery.pagination.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/xheditor/xheditor-1.1.14-zh-cn.min.js"></script>

<script type="text/javascript">
	<!--
	
	var pageIndex = 0;     //页面索引初始值  
    var pageSize = 2;     //每页显示条数初始化，修改显示条数，修改这里即可  
  		
   $(document).ready(function(){
         $("#Pagination").pagination(${count}, {  
            callback: PageCallback,  
            prev_text: '<<',       //上一页按钮里text  
            next_text: '>>',       //下一页按钮里text  
            items_per_page: pageSize,  //显示条数  
            num_display_entries: 10,    //连续分页主体部分分页条目数  
            current_page: pageIndex,   //当前页索引  
            num_edge_entries: 2        //两侧首尾分页条目数  
    	});
        	
    	initEditor();
    	
	});


  //翻页调用  
  function PageCallback(pageIndex, jq) {   
               $.ajax({   
               type: "POST",  
               dataType: "text",  
               url: '${contextPath}/site/Post-shoptopicReplis.htm?id=${post.id?c}',    //提交到一般处理程序请求数据  
               data: "page=" + (pageIndex + 1) + "&rp="+pageSize,          //提交两个参数：pageIndex(页面索引)，pageSize(显示条数)                  
                	success: function(data) { 
                 	$("#Result").text('');   
               	  	data = $.parseJSON(data);
               	  
               	  var post_str ='<div class="disc-answer-base">'+
					'<div class="disc-answer-floor"><p><span class="first">{0}</span>L：</p><span class="disc-action-time">{1}</span></div>'+
					'<div class="disc-answer-con">{2}</div>'+
					'<div class="disc-del"><a class="disc-reply" href="javascript:;">&#9650回应</a></div>'+
					'<div class="disc-user"><a class="disc-details" href="javascript:;">{4}</a><a href="javascript:;"><img width="50" height="50" src="{5}"></a></div></div>'+
					'<div class="disc-edit-box" style="display:none;"><form id="rForm_{3}"><textarea cols="50" id="text_{3}" name="post.text" rows="6"></textarea><input name="post.parentId" type="hidden" value="{3}"></form><a class="all-btn green-btn reply" href="javascript:;" onclick="reply2(this,{3})">回 复</a><a class="all-btn green-btn cancel">取 消</a></div>';
					
					
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
 
               	 }
            });                

        }  
		
		function initEditor() {
			//$('#posttext').xheditor({tools:'mfull'});
			$('#posttext, #replytext').xheditor({tools:'Preview,Blocktag,Fontface,FontSize,Bold,Italic,Underline,Strikethrough,FontColor,BackColor,|,Align,List,Outdent,Indent,|,Link,Img,Emot'});
			
		}	
	
	  	function buttonShows() {
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
  		
  		
  		
	-->
</script>

</head>
<body>
<input type="hidden" id="postId" name="postId" value="${post.id?c}" />
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="shops"/>
	
	<#macro show_reply posts>
		<#if posts?exists>
			<#list posts as post>
				<tr>
			        <td>
			            <a href="${contextPath}/site/Post-shoptopic.htm?id=${post.id?c}" title="${post.subject!''}">${post.subject!''}<#if post.totop><font color=red>[置顶]</font></#if></a>
			        </td>
			        <td nowrap="nowrap" class="td2">${post.replyCount}</td>
			    </tr>
			</#list>
		</#if>
	</#macro>

	<div class="bbs-main shop-disc">
		<div class="grid_17 disc-topl">
			<h2 id="subject_show" class="disc-h2"><span id="subject_val">${post.subject!''}</span>
				<#if _checkin && (_loginId==post.userId)>
				<a class="disc-del" href="javascript:;" id="titleEditBtn">&#9668编辑</a>
				</#if>
			</h2>
			<#if _checkin && (_loginId==post.userId)>
				<div class="disc-title" style="display:none;">
					<form id="postSubjectForm" >
						<input type="hidden" name="post.id" value="${post.id?c}"/>
						<input type="text" value="${post.subject!''}" oldvalue="${post.subject!''}" id="postsubject" name="post.subject" data-validation-engine="validate[required],maxSize[30]">
						<a class="all-btn grey-btn" href="javascript:;" onclick="modifySubject()">完成</a>
						<a class="all-btn grey-btn" href="javascript:;" onclick="cancelSubject()">取消</a> 
					</form>
				</div>
			</#if>
			<div class="disc-con">
				<div id="text_show">
					<div class="disc-con-p" id="text_val">
						${post.text!''}
					</div>
					<#if _checkin && (_loginId==post.userId)>
					<a class="disc-edit" href="javascript:;" id="textEditBtn">&#9650编辑</a>
					</#if>
				</div>
						
				<div class="disc-edit-box"  id="text_input" style="display:none;">
					<form id="postTextForm">
						<input type="hidden" name="post.id" value="${post.id?c}"/>
						<textarea style="height:150px" cols="88" rows='5' id="posttext" name="post.text" rows="5">${post.text!''}</textarea>
						<a class="all-btn grey-btn" href="javascript:;" onclick="modifyText()">完成</a>
						<a class="all-btn grey-btn" href="javascript:;" onclick="cancelText()">取消</a>
					</form>
				</div>
			</div>
		
			<div class="disc-agree">
				<#if _checkin>
					<a href="javascript:;" onclick="enjoyTopic()" class="like" title="赞一个"><span>赞图标</span>顶顶顶</a>
					<a href="javascript:;" onclick="boredTopic()" class="dislike" title="我不赞同"><span>踩图标</span>踩一下</a>
				<#else>
					<a href="javascript:;" onclick="$('.reg-link').click()" class="like" title="赞一个"><span>赞图标</span>顶顶顶</a>
					<a href="javascript:;" onclick="$('.reg-link').click()" class="dislike" title="我不赞同"><span>踩图标</span>踩一下</a>				
				</#if>				
			</div>		
		
			<div class="disc-answer" id="Result" >
				<!-- <h3>回 复</h3> -->
			</div>
			<div id="Pagination" class="pagination"></div>
			
			<#if _checkin>
				<div class="disc-myanswer">
					<form id="replyForm">
						<input type="hidden" name="post.parentId" value="${post.id?c}"></input>
						<textarea id="replytext" rows="5" cols="88" class="" name="post.text"></textarea>
						<a class="all-btn green-btn" id="replybtn" href="javascript:;" onclick='reply()'>回 复</a>
					</form>
				</div>
			</#if>
		</div>
		<div class="grid_7 disc-topr">
			<div class="disc-info">
				<div class="disc-writer">
					<a href="#">
						<img width="80" height="80" src="${staticWebRoot}/${post.owner.avatar!'${_avatar}'}">
					</a>
					<a href="#" class="disc-details">
						${post.owner.nickname!''}
					</a>
					<ul class="tags">
						<#if (post.owner?exists && post.owner.tags?exists)>
							<#list post.owner.tags as tag>
								<li>${tag.name}</li>
							</#list>
						</#if>
						<#if !post.owner.hometownPrivate && (post.owner.hometownId>0)>
							<li>${post.owner.hometown!''}</li>
						</#if>
						<#if !post.owner.schoolprivate && (post.owner.schoolId>0)>
							<li>${post.owner.school!''}</li>
						</#if>
					</ul>
					
					<#if (post.vipShop && post.shopPresenter>0 && post.shopPresenter==post.userId)>
					<p>来源:<span class="orange">店长发布</span></p>
					</#if>
					
					<span>发表于：${post.time}</span>
					<div><span class="viewed">${post.clickCount}</span>人浏览</div>
					<ul class="evaluate">
						<li><span class="like" id="usefulCount">${post.usefulCount}</span>人顶</li>
						<li><span class="dislike" id="unusefulCount">${post.unusefulCount}</span>人踩</li>
					</ul>
				</div>
			</div>
	
			<a class="all-btn grey-btn back" href="${contextPath}/site/Topic-shopview.htm?id=${post.topicId?c}">回到店铺</a>
			<#if _checkin>
				<a href="${contextPath}/c/Post-shoptopiccreate.htm?topicId=${post.topicId?c}" class="all-btn green-btn">我要发起话题</a>
			<#else>
				<a href="javascript:;" onclick="$('.reg-link').click()" class="all-btn green-btn">我要发起话题</a>
			</#if>
	
			<h3 class="tag4"><span class="left"><#if !list_post?exists || (list_post?size<=0)>暂无</#if>其他话题</span></h3>
			<#if post.replies?exists && (post.replies?size>0)>
			<!-- 其它热门话题-->
			<table class="list vertical-menu2 shop-table disc-table">
				<tbody>
					<tr>
				    	<td style="width:86%;">话题</td>
				    	<td nowrap="nowrap" style="width:14%;" class="td2">回应</td>
					</tr>
					<@show_reply posts=post.replies />
				</tbody>
			</table>
			</#if>
		</div>
			
	</div>

	<#include "/ftl/inc/foot.ftl">
	
 <script type="text/javascript">
  <!--
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
  	
		function deletePost(id){
			if (confirm("确认删除该记录么")){
				$.ajax({
					url:'${contextPath}/c/Post-deletePost.htm',
					type:'POST',
					dataType:'json',
					data:'postId='+id,
					success: function(data){
						 	location.reload();
					   	},
				    error: function(xmlHttpRequest, textStatus, errorThrown){
					    	alert("网络异常:\r\n{0}".format(xmlHttpRequest.responseText));
					    }
				});
			}
		}  	
  	
  		function enjoyTopic() {
			voteTopc('${contextPath}/c/Post-enjoyTopic.htm?id=${post.id?c}','#usefulCount');
  		}
		
		function boredTopic() {
			voteTopc('${contextPath}/c/Post-boredTopic.htm?id=${post.id?c}','#unusefulCount');
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
  	
  	<#if _checkin && (_loginId==post.userId)>
  		$("#postSubjectForm").validationEngine();
  		$("#postTextForm").validationEngine();
  		
  		function modifySubject() {
			if (!$("#postSubjectForm").validationEngine('validate')) return;
			$this = $(this);
			$.ajax({
	    		url: '${contextPath}/c/Post-shoptopicbaseinfo.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#postSubjectForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($this, msg);
	    			})) {
						var obj = $('#postsubject');
						obj.attr("oldvalue",obj.val());
						$('#subject_val').text(obj.val());
						
						$(".disc-title").hide();
						$('#subject_show').show();     				
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
  		
		function cancelSubject() {
			var obj = $('#postsubject');
			obj.val(obj.attr("oldvalue"));
			$(".disc-title").hide();
			$('#subject_show').show();
		} 	
		
		
		function modifyText() {
			if (!$("#postTextForm").validationEngine('validate')) return;
			$this = $(this);
			$.ajax({
	    		url: '${contextPath}/c/Post-shoptopicbaseinfo.htm',
	    		type: 'POST',
	    		dataType:'json',
	    		data:$('#postTextForm').serializeArray(),
	    		success: function(data){
	    			if (preProcessData(data,function(msg){
	    				showPrompt($this, msg);
	    			})) {
						var obj = $('#posttext');
						$('#text_val').html(obj.val());
							    			
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
			var obj = $('#posttext');
			obj.val($('#text_val').html());		
			$(".disc-edit-box").hide();
			$('#text_show').show();		
		}			
  	</#if>
  	
  //-->
  </script>		
</body>
</html>