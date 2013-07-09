<#include "/template/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
	<title>TO FREEMAKER</title>
	<meta charset="utf-8">
	<meta name="author" content="touchnan">
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	
	<link type="text/css" rel="stylesheet" href="${staticWebRoot}/touchin/css/customer.css" />
	<script type="text/javascript" src="${staticWebRoot}/jquery/jquery.js"></script>
	<script type="text/javascript" src="${staticWebRoot}/touchin/js/os.custom.js"></script>
	
	 <script type="text/javascript">
	 <!--
		$(document).ready(function() {
			
			bindDatalist($("#listTable"));
			
		});	
		 
		function exp2() {
			//$("#opert").val(_opert);
			$("#expForm").submit();
		}
	 //-->
	 </script>	
</head>
<body>
<table id="listTable" class="datalist" summary="list of members in EE Studay">
	<caption>
		<label>账号</label>导出
		<input type="button" class="all-btn blue-btn" onclick="exp2()" value="导出"/>
	</caption>
	<thead>
		<tr>
			<th scope="col">序号</th>
			<th scope="col">账号</th>
			<th scope="col">名称</th>
		</tr>
	</thead>
	<tbody>
	<#if lists?exists>
		<#if (lists?size>10000)>
			<tr>
				<td colspan="4" style="text-align: center; color: red">
					超出一万,共${lists?size}条数据,请直接导出。
				</td>
			</tr>
		<#else>
			<#list lists as row>
				<tr <#if row_index%2==1>class="altrow"</#if> >
					<td>${(row_index+1)?c}</td>
					<td>${row.loginName}</td>
					<td>${row.nickName}</td>
				</tr>				
			</#list>			
		</#if>
	</#if>	
	</tbody>
</table>
<form id="expForm" action="${contextPath}/mgr/file!down.action" method="post" target="hiddenFrame">
	<input type="hidden" name="showName" value="账号文件.csv" />
	<input type="hidden" name="fileName" value="exp4acc.ftl" />
	<input type="hidden" name="dataKey" value="rows" />
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0" height="0" frameborder="0"></iframe>
</body>
</html>