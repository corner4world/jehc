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
<!-- 1.获取到规则流程图
<img style="position: absolute;top: 0px;left: 0px;" src="../lcProcessController/drawLcProcessInstanceImg?processInstanceId=${processInstanceId }">
 -->
<!-- 2.根据当前活动的坐标，动态绘制DIV 
<div style="position: absolute;border:1px solid red;top:<s:property value='#acs.y'/>px;left: <s:property value='#acs.x'/>px;width: <s:property value='#acs.width'/>px;height:<s:property value='#acs.height'/>px;   "></div>
-->

<img src="${img}" style="position: absolute;top: 0px;left: 0px;"/>
<div style="position: absolute;border:3px solid red;top:${y-1}px;left:${x-1}px;width:${width-3}px;height:${height-3}px;cursor:pointer;" onclick="msgDetail(${processInstanceId })">
</body>  
</html> 