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
<title>solr更多属性</title>  
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-attribute/solr-index-attribute-list.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-attribute/solr-index-attribute-add.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-attribute/solr-index-attribute-update.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-attribute/solr-index-attribute-detail.js"></script>  
</head>  
<body>  
<input type="hidden" value="${solr_index_id }" id="solr_index_id_">
</body>  
</html> 