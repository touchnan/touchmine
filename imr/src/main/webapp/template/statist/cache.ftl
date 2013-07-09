<#include "/template/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
	<title>二级缓存</title>
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
	 //-->
	 </script>	
</head>
<body>
<table id="listTable" class="datalist" summary="list of members in EE Studay">
	<caption>
		<label>二级缓存</label>
	</caption>
	<thead>
		<tr>
			<th scope="col">二级命中数</th>
			<th scope="col">二级命中失败数</th>
			<th scope="col">Put数</th>
			<th scope="col">内存中的元素个数</th>
			<th scope="col">磁盘中的元素个数</th>
			<th scope="col">内存大小</th>
		</tr>
	</thead>
	<tbody>
	<#if sessionFactory?exists>
		<#if (sessionFactory.getStatistics()?exists)>
			<#assign  statist=sessionFactory.getStatistics()>
			<tr>
				<th colspan="2">
					合计111
				</th>
				<th colspan="4">
					${statist}
				</th>
			</tr>
			<tr>
				<td>${statist.getSecondLevelCacheHitCount()?c}</td>
				<td>${statist.getSecondLevelCacheMissCount()?c}</td>
				<td>${statist.getSecondLevelCachePutCount()?c}</td>
				<td></td>
				<td></td>
				<td></td>
			</tr>
			<#if (statist.getSecondLevelCacheRegionNames()?exists)>
				<#list statist.getSecondLevelCacheRegionNames() as region>
					<tr>
						<th colspan="6">
							${region}
						</th>
					</tr>
					<#assign subStatist = statist.getSecondLevelCacheStatistics(region)>
					<tr>
						<td colspan="6">${subStatist}</td>
					</tr>
					<tr>
						<td>${subStatist.hitCount}</td>
						<td>${subStatist.missCount}</td>
						<td>${subStatist.putCount}</td>
						<td>${subStatist.elementCountInMemory}</td>
						<td>${subStatist.elementCountOnDisk}</td>
						<td>${subStatist.sizeInMemory}</td>
					</tr>
				</#list>
			</#if>
		</#if>
	</#if>	
	</tbody>
</table>
</body>
</html>