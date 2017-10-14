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
<title>JsRun工具</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-code/xt-code-list.js"></script>  
	<style type="">
		.x-form-trigger-wrap-default {
			border-width: 0px;
			border-style: solid;
			border-color: #cecece;
		}
		.x-html-editor-container {
			border: 0px solid;
			border-color: #cecece;
		}
		.edui-default .edui-editor {
			border: 0px solid #d4d4d4;
			background-color: white;
			position: relative;
			overflow: visible;
			-webkit-border-radius: 4px;
			-moz-border-radius: 4px;
			border-radius: 4px;
		}
		.x-btn-inner-default-toolbar-small {
			max-width: 100%;
			/**隐藏**/
			display:block;
		}
	</style>
</head>  
<body>  
</body>  
</html> 