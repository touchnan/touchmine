<#include "/ftl/inc/inc.ftl">
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<title>后台管理</title>

<link type="text/css" rel="stylesheet" href="${staticWebRoot}/scripts/jqui/css/jquery.ui.css" />
<link type="text/css" rel="stylesheet" href="${staticWebRoot}/scripts/jqgrid/css/jqgrid.ui.css" />

<script type="text/javascript" src="${staticWebRoot}/scripts/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqgrid/i18n/grid.locale.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/jqgrid/jqgrid.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bbs.js"></script>
<script type="text/javascript" src="${staticWebRoot}/scripts/bg.js"></script>

 <script type="text/javascript">
 <!--
 
 $(document).ready(function () {
	 	
		$("#grid").bindGrid({
			_oname:"wordbook",
		   	url:"${contextPath}/bg/BgMgr-listMessages.htm",
		   	pager: '#pager',
		   	sortname: 'id',
		    sortorder: "desc",
		    caption:"数据字典",		   	
		   	colModel:[
				{name:'id',label:'id',index:'L_id',hidedlg:true,hidden:true,editable: true},
		   		{name:'name',label:'名称', index:'S_name',align:'center',
					hidedlg:true,
					editable:true,
					formoptions:{elmprefix:'(*)'},
					editrules: {required: true }
		   		}	   		
		   	]
		}).bindGridNav('#pager',{},{
			 beforeInitData: function(form) {
				 //$('#tr_wordbook\\.id', form).hide();
	         },
		},{
			 beforeInitData: function (form) {
           	 	//$('#tr_wordbook\\.id', form).show();
            }
		});
	});


 	
 //-->
 </script>
 
</head>
<body>
<div>
  <table id="grid">
  </table>
  <div id="pager"></div>
</div>
</body>
</html>