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
<title>全文检索拷贝索引字段配置</title>  
	<script type="text/javascript" src="../view/pc/solr-view/solr-filed-copy/solr-filed-copy-list.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-filed-copy/solr-filed-copy-add.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-filed-copy/solr-filed-copy-update.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-filed-copy/solr-filed-copy-detail.js"></script>  
</head>  
<body>  
	<input id="solr_core_id"  value="${solr_core_id }" type="hidden">
</body>  
</html> 