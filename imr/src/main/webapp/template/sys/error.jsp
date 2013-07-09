<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@include file="/template/inc/inc.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>new document</title>
<meta name="author" content="" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<body>
<div id="err">
<s:fielderror/>
<s:actionerror/>
<s:actionmessage/>
<s:property value="exception.message"/>
<s:property value="exceptionStack"/>        
<s:property value="exception.fillInStackTrace()"/>   
</div>
</body>
</html>