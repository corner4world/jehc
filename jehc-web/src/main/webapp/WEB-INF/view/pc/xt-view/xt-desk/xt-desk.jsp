<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%@ include file="/deng/include/inchart.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<style type="text/css">
/* .x-grid-with-row-lines .x-grid-item {
	border-style: dashed;
	border-width: 1px dashed 1px;
	border-color: #cecece;
}
.x-grid-with-col-lines .x-grid-cell {
	border-right: 1px dashed #cecece;
}
.x-grid-item-focused .x-grid-cell-inner:before {
	content: "";
	position: absolute;
	z-index: -1;
	top: 0;
	right: 0;
	bottom: 0;
	left: 0;
	pointer-events: none;
	border: 1px dashed #3d91cf;
}
.x-grid-item-focused {
color: #3d91cf;
}
.x-panel-body-default {
color: #3d91cf;
font-size: 15px;
font-weight: 300;
font-family: helvetica,arial,verdana,sans-serif;
}
.x-grid-item-selected {
color: #3d91cf;
}
.x-grid-item {
color: #000;
font: 300 15px/17px helvetica,arial,verdana,sans-serif;
background-color: white;
}
.x-panel-header-defined{
    background-image:none;
    background-color:#5fa2dd;
}
.x-toolbar-default .x-toolbar-item {
    cursor: pointer;
} */
</style>
</head>  
<script type="text/javascript" src="../view/pc/xt-view/xt-desk/xt-desk.js"></script>  
<body style="background: #ffffff;">  
	<input id="xt_userinfo_id" value="${xtUserinfo.xt_userinfo_id }" type="hidden">
</body>  
</html> 