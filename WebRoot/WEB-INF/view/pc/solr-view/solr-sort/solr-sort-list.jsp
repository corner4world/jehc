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
<title>solr排序</title>  
	<script type="text/javascript" src="../view/pc/solr-view/solr-sort/solr-sort-list.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-sort/solr-sort-add.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-sort/solr-sort-update.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-sort/solr-sort-detail.js"></script>  
</head>  
<body>  
<input type="hidden" value="${solr_index_id }" id="solr_index_id_">
</body>  
</html> 