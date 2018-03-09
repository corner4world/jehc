<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>缓存管理</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-cache/xt-cache-list.js"></script>
	<link href="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/css/bootstrap-table.css" rel="stylesheet">
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table.js"></script>
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bootstrap-table-treegrid/js/bootstrap-table-zh-CN.js"></script>
	<style>
		/* dataTables表头居中 */  
		.table>thead:first-child>tr:first-child>th{  
		        text-align:left;  
		        background-color: #f5f5f5;
		        font-weight:normal;
		} 
		.table>thead:first-child>tr:first-child>th p{  
		        text-align:left;  
		        font-weight:normal;
		}  
		.table>tbody>tr>td {
		    text-align:left;
		}
		.fixed-table-container .bs-checkbox {
		    text-align:left;
		}
		.list-group {
		    margin-bottom: 0px;
		}
	</style>
</head>  
<body>  
	<div class="panel panel-default">
		<fieldset>
			<legend>缓存列表</legend>
		</fieldset>
	</div>
	<div class="btn-group pull-left" style="margin-right: 20px;">
		<button class="btn btn-default" onclick="initTreeTable()">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
	</div>
	<table id="table"></table>
</body>  
</html> 