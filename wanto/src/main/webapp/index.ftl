<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>论坛首页</title>
<link type="text/css" href="${staticWebRoot}/style/public.css" rel="stylesheet">
<link type="text/css" href="${staticWebRoot}/style/bbs.css" rel="stylesheet">
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jquery.blockUI.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
</head>
<body>
	<#include "/ftl/inc/head.ftl">
	<@main_menu idx="main"/>
	
	<div class="bbs-index-main" style="height:550px;">
		<div class="bbs-index-pic1">
			happy
		</div>
		<div class="bbs-index-pic2">
			112
		</div>
		<form id="sIdxForm" method="post" style='margin:0;padding:0' target="_blank" action="${contextPath}/site/Topic-qshops.htm" onsubmit=" $sch = $('#sch_index');$sch.val($.trim($sch.val())); return ''!==$sch.val();">
		<div class="bbs-searchbar">
			<input type="text" id="sch_index" name="sch" value="">
			<a href="javascript:;" onclick="javascript:$('#sIdxForm').submit();" style="float: right;">
				<img src="${staticWebRoot}/images/search.png" style="width:20px;">
			</a>
		</div>
		</form>
		<div class="bbs-index-pic3">
			快乐就像1+1=2这样简单..
		</div>
	
	</div>

	<#include "/ftl/inc/foot.ftl">
</body>
</html>