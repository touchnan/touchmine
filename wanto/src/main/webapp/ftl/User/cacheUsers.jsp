<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/inc/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />


<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/touchin/css/customer.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/jqui/css/jquery.ui.css" />
<link type="text/css" rel="stylesheet" href="<%=staticWebRoot %>/jqgrid/css/jqgrid.ui.css" />


<script type="text/javascript" src="<%=staticWebRoot %>/jquery/jquery.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqui/i18n/jquery.ui.datepicker.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqui/jquery.ui.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqgrid/i18n/grid.locale.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/jqgrid/jqgrid.js"></script>
<script type="text/javascript" src="<%=staticWebRoot %>/touchin/js/os.custom.js"></script>

 <script type="text/javascript">
 <!--
 
 $(document).ready(function () {
	 	
	 	var userRoles = jqSelect('<%=contextPath%>/mgr/enum!userRoles.action'),
	 		userTypes = jqSelect('<%=contextPath%>/mgr/enum!userTypes.action');
	 	
		$("#grid").bindGrid({
			_oname:"user",
		   	url:"<%=contextPath%>/mgr/user!listCacheUsers.action",
		   	editurl:"<%=contextPath%>/mgr/user!saveUser.action",
		   	pager: '#pager',
		   	sortname: 'id',
		    sortorder: "desc",
		    caption:"账号管理",
		   	colModel:[
				{name:'id',label:'id',index:'S_id',hidedlg:true,hidden:true,editable: true},
		   		{name:'nickname',label:'姓名', index:'S_nickname',align:'center',
					hidedlg:true,
					editable:true,
					formoptions:{elmprefix:'(*)'},
					editrules: {required: true }
		   		},
		   		{name:'username',label:'账号',index:'S_uesrname',align:'center',
		   			hidedlg:true,
		   			editable: true,
		   			//formatter: 'integer',
		   			//editoptions: { size: 20, readonly: 'readonly', maxlength: 200},
		   			formoptions:{elmprefix:'(*)'},
		   			editrules: { required: true }
		   		},
		   		{name:'newPasswd',label:'密码',index:'S_newPasswd',hidedlg:true,hidden:true,editable:true,formoptions:{elmprefix:'&nbsp;&nbsp;&nbsp;&nbsp;'},editrules:{edithidden:true}},
		   		{name:'role',label:'角色',index:'I_role',width:60, editable:true,
		   			edittype:'select',
		   			formatter:'select',
		   			editoptions:{value:userRoles},
		   			formoptions:{elmprefix:'&nbsp;&nbsp;&nbsp;&nbsp;'},
		   			search:true,
		   			stype:'select',
		   			searchoptions:{value:userRoles}
		   		},
		   		{name:'type',label:'类型',index:'I_type',width:60, editable:true,
		   			edittype:'select',
		   			formatter:'select',
		   			editoptions:{value:userTypes},
		   			formoptions:{elmprefix:'&nbsp;&nbsp;&nbsp;&nbsp;'},
		   			search:true,
		   			stype:'select',
		   			searchoptions:{value:userTypes}		   			
		   		}
		   	],
			//ondblClickRow: function(id){
				//afterComplete:function(erverResponse, postdata, formid)
				//$(this).jqGrid('editGridRow',id,{ afterComplete:function(){ alert("aaaa") } });
				//$(this).trigger('jqGridAddEditClickSubmit',id);
			//},
			loadComplete:function() {
				
			},
		}).bindGridNav('#pager');
	});


 	
 //-->
 </script>
 
</head>
<body>
<div class="touchin-box">
  <table id="grid">
  </table>
  <div id="pager"></div>
</div>
</body>
</html>