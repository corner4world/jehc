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
<title>商品商户所选图片</title>  
	<script type="text/javascript" src="../view/pc/b-view/b-product-img/b-product-img-list.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product-img/b-product-img-add.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product-img/b-product-img-update.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product-img/b-product-img-detail.js"></script> 
</head>  
<body>  
	<input type="hidden" value="${b_product_id }" id="b_product_id_">
</body>  
</html> 