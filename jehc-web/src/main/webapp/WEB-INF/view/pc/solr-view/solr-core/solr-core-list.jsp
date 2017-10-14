<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%@ include file="/deng/include/inplug.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>全文检索多实例配置</title>  
	<!-- 多实例 -->
	<script type="text/javascript" src="../view/pc/solr-view/solr-core/solr-core-list.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-core/solr-core-add.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-core/solr-core-update.js"></script> 
	<script type="text/javascript" src="../view/pc/solr-view/solr-core/solr-core-detail.js"></script>  
	<!-- 索引 -->
	<script type="text/javascript" src="../view/pc/solr-view/solr-index/solr-index-grid.js"></script>  
	<!-- 实体 -->
	<script type="text/javascript" src="../view/pc/solr-view/solr-entity/solr-entity-add.js"></script>  
	<script type="text/javascript" src="../view/pc/solr-view/solr-entity/solr-entity-update.js"></script>  
	<script type="text/javascript" src="../view/pc/solr-view/solr-core/solr-core-schema-data-config.js"></script> 
	<!-- 导入及映射字段 -->
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-sql-filed/solr-index-sql-filed-grid.js"></script>
	<script type="text/javascript" src="../view/pc/solr-view/solr-index-sql/solr-index-sql-grid.js"></script>  
</head>  
<body>  
	<input type="hidden" value="${solr_url_id }" id="solr_url_id_">
</body>  
</html> 