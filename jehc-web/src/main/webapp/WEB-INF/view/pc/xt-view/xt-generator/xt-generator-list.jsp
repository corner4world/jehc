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
<title>代码生成信息</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-generator/xt-generator-list.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-generator/xt-generator-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-generator/xt-generator-one-to-many-add.js"></script>  
</head>  
<body>  
<input type="hidden" id="databasetype" value="${dbInfo.databasetype }">
</body>  
</html> 