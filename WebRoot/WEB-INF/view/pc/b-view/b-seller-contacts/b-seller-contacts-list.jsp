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
<title>商户联系人信息</title>  
	<script type="text/javascript" src="../view/pc/b-view/b-seller-contacts/b-seller-contacts-list.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-seller-contacts/b-seller-contacts-add.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-seller-contacts/b-seller-contacts-update.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-seller-contacts/b-seller-contacts-detail.js"></script>  
</head>  
<body> 
	<input type="hidden" value="${b_seller_id }" id="b_seller_id"> 
</body>  
</html> 