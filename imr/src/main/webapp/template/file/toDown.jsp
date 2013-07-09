<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/inc/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>文件 </title>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />

<link type="text/css" rel="stylesheet" href="<%=staticWebRoot%>/touchin/css/customer.css" />
<script type="text/javascript" src="<%=staticWebRoot%>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=staticWebRoot%>/touchin/js/os.custom.js"></script>

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
<form id="expForm" action="<%=contextPath%>/mgr/file!down.action" method="post" target="hiddenFrame">
	<input type="hidden" name="showName" value="账号文件.csv" />
	<input type="hidden" name="fileName" value="exp4acc.ftl" />
	<input type="hidden" name="dataKey" value="rows" />
</form>
<iframe id="hiddenFrame" name="hiddenFrame" width="0" height="0" frameborder="0"></iframe>
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
		<s:if test="lists.size>10000">
			<tr>
				<td colspan="4" style="text-align: center; color: red">
					超出一万,共<s:property value="lists.size"/>条数据,请直接导出。
				</td>
			</tr>
		</s:if>
		<s:else>
			<s:iterator value="lists" status="status">
			<tr <s:if test="#status.even">class="altrow"</s:if> >
				<td><s:property value="#status.index+1"/></td>
				<td><s:property value="loginName"/></td>
				<td><s:property value="nickName"/></td>
			</tr>
			</s:iterator>
		</s:else>	
	</tbody>
</table>
</body>
</html>