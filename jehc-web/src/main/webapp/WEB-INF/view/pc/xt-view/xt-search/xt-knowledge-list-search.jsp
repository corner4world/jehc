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
<title>平台知识内容搜索结果</title>
	<script type="text/javascript" src="../view/pc/xt-view/xt-search/xt-knowledge-list-search.js"></script>  
	<style>
		/* .x-panel-header-default-framed {
		border: 0px solid #fff;
		}
		.x-column-header {
		border-right: 1px solid #fff;
		color: #404040;
		font: 400 13px/19px 'Open Sans', 'Helvetica Neue', helvetica, arial, verdana, sans-serif;
		outline: 0;
		background-color: #fff;
		}
		.x-grid-body {
		    background: white;
		    border-width: 1px;
		    border-style: solid;
		}
		.x-grid-body {
		    border-top-color: #f5f5f5;
		}
		.x-column-header {
		    border-right: 1px solid #cfcfcf;
		    color: #666;
		    font: 300 13px/15px helvetica, arial, verdana, sans-serif;
		    outline: 0;
		    background-color: #fff;
		    border-bottom: 1px solid #fff;
		}
		.x-panel-header-defined {
		    background-image: none;
		    background-color: #32c5d2;
		} */
	</style>
</head>  
<body>  
	<input id="keywords" type="hidden" value="${keywords }">
</body>  
</html> 