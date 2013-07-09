账号,名称
<#if rows?exists>
	<#list rows as row>
${row.loginName},${row.nickName}
	</#list>
</#if>	