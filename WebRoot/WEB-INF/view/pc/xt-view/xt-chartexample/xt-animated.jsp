<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%@ include file="/deng/include/inchart.jsp"%>
<%
String path = request.getContextPath();
String rootMenu=(String)request.getAttribute("msg");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>  
<head>  
<meta charset="UTF-8">  
<title>${sys_pt_index}</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-chartexample/xt-animated.js"></script>  
</head>  
<body>  
</body>  
</html> 