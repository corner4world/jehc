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
<title>省市区县</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-district/xt-district-list.js"></script> 
	<script type="text/javascript" src="../view/pc/xt-view/xt-district/xt-district-add.js"></script> 
	<script type="text/javascript" src="../view/pc/xt-view/xt-district/xt-district-update.js"></script>  
</head>  
<body>  
	<input type="hidden" value="${xt_provinceID }" name="xt_provinceID" id="xt_provinceID">
	<input type="hidden" value="${xt_cityID }" name="xt_cityID" id="xt_cityID">
</body>  
</html> 