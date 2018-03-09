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
<title>监控管理(内存)</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-monitor-mem/xt-monitor-mem-list.js"></script>  
</head>  
<body>  
	<div class="panel panel-default">
		<fieldset>
			<legend>服务器内存监测列表</legend>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="delXtMonitorMem()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>总内存</th>
					<th>当前内存使用量</th>
					<th>当前内存剩余量</th>
					<th>交换区总量</th>
					<th>当前交换区使用量</th>
					<th>当前交换区剩余量</th>
					<th>取读时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>  
</html> 