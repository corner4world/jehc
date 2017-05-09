<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>流程实例</title>  
	<script type="text/javascript" src="../view/pc/lc-view/lc-process-instance/lc-process-instance-list.js"></script> 
</head>  
<body>  
<input type="hidden" value="${lc_deployment_his_id }" id= "lc_deployment_his_id">
<input type="hidden" value="${lc_process_id }" id= "lc_process_id">
</body>  
</html> 