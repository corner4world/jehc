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
<title>会员常用地址信息</title>  
	<script type="text/javascript" src="../view/pc/b-view/b-member-address/b-member-address-list.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-member-address/b-member-address-add.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-member-address/b-member-address-update.js"></script> 
	<script type="text/javascript" src="../view/pc/b-view/b-member-address/b-member-address-detail.js"></script>  
</head>  
<body> 
	<input type="hidden" value="${b_member_id }" id="b_member_id"> 
</body>  
</html> 