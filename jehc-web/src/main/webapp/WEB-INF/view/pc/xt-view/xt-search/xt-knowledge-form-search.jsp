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
<title>平台知识内容搜索结果明细</title>
	<script type="text/javascript" src="../view/pc/xt-view/xt-search/xt-knowledge-form-search.js"></script>  
</head>  
<body>  
	<input id="searchid" type="hidden" value="${searchid }">
</body>  
</html> 