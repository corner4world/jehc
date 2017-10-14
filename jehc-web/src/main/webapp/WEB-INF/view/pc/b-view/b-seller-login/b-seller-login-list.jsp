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
<title>商户登陆账号</title>  
	<script type="text/javascript" src="../view/pc/b-view/b-seller-login/b-seller-login-update.js"></script> 
</head>  
<body> 
	<input type="hidden" value="${b_seller_id }" id="b_seller_id"> 
</body>  
</html> 