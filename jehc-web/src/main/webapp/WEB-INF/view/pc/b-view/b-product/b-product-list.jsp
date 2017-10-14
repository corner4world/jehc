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
<title>商品</title>  
	<!-- HTML编辑器--> 
	<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/htmlEditor/htmlEditor.js"></script>
	<script charset="utf-8" src="${syspath}/deng/editor/kindeditor/kindeditor-min.js"></script>
	<script charset="utf-8" src="${syspath}/deng/editor/kindeditor/lang/zh_CN.js"></script>
	<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-list.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-add.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-update.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-detail.js"></script> 
</head>  
<body>  
</body>  
</html> 