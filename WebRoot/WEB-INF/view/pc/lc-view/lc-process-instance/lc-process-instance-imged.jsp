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
<title>流程实例监控图</title>  
<script type="text/javascript" src="../view/pc/lc-view/lc-process-instance/lc-process-instance-img.js"></script> 
</head>  
<body>  
<img src="../lcProcessController/viewImage?lc_apply_id=${lc_apply_id}" style="position: absolute;top: 0px;left: 0px;" title="流程已结束"/>
</body>  
</html> 