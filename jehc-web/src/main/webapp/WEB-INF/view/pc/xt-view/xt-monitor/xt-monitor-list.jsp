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
<title>监控管理(JVM)</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-monitor/xt-monitor-list.js"></script>  
</head>  
<body>  
	<div class="panel panel-default">
		<fieldset>
			<legend>虚拟机监测列表</legend>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="delXtMonitor()">
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
					<th>用户名</th>
					<th>账户名称</th>
					<th>本地主机名</th>
					<th>JVM可使用总内存</th>
					<th>JVM可使用剩余内存</th>
					<th>JVM可使用处理器个数</th>
					<th>取读时间</th>
				</tr>
			</thead>
		</table>
	</div>
</body>  
</html> 