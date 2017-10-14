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
<title>商品默认图片列表</title>  
	<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/ux/DataView/data-view.css" type="text/css" />
	<script type="text/javascript" src="../view/pc/b-view/b-product-img-default/b-product-img-default-datagrid.js"></script> 
</head>  
<body>  
	<input type="hidden" value="${b_product_id }" id="b_product_id_">
</body>  
</html> 