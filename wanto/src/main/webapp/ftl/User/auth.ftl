<#include "/index.ftl">
	
<script type="text/javascript">
  	<!--
  	
  	 $(document).ready(function(){
  	 	<#if !_checkin>
    		$('.reg-link').click();
    	</#if>
     });
     
	 function auth() {
	    hideMsg("#errorMsg");
		if((!checkName()) || (!checkPassword())){
			return;
		}
		$.ajax({
    		url: '${contextPath}/auth-login.htm',
    		type: 'GET',
    		dataType:'json',
    		data:$("#lform").serializeArray(),
    		success: function(data){
    			if (preProcessData(data, function (msg) {
    				showMsg("#errorMsg",data['errorVo']['message']);
    			})) {
    				window.location.href="${contextPath}${request.getParameter('url')}";
    			}
    		},
    		error: function(xmlHttpRequest, textStatus, errorThrown){
    			errorView(xmlHttpRequest.responseText);
    		},
    		beforeSend:function() {
    			loading(); 
    		}    		
		});
	 	return false;
	 }		    	
    //-->
 </script>	
