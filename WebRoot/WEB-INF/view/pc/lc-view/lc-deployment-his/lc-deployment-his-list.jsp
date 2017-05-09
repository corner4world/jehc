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
<title>流程部署历史记录</title>  
	<script type="text/javascript" src="../view/pc/lc-view/lc-deployment-his/lc-deployment-his-list.js"></script> 
	<script type="text/javascript" src="../view/pc/lc-view/lc-deployment-his/lc-deployment-his-detail.js"></script>  
</head>  
<body>  
	<input type="hidden" value="${lc_Deployment_His.lc_process_id }" id= "lc_process_id">
</body>  
</html> 