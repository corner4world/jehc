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
<title>数据字典</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-child-list.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-add.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-data-dictionary/xt-data-dictionary-update.js"></script>  
</head>  
<body>  
	<input type="hidden" id="xt_data_dictionary_id" name="xt_data_dictionary_id" value="${xt_data_dictionary_id }">
</body>  
</html> 