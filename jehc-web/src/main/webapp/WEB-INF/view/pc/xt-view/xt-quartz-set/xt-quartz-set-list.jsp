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
<title>任务调度</title>   
</head>  
<body>  
	<div class="panel-body">
		<div class="btn-group pull-left" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="defaultStartXtQuartzSet()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>启动默认任务
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<br><br>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>任务名称</th>
					<th>任务分组</th>
					<th>任务状态</th>
					<th>运行时间表达式</th>
					<th>任务描述</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body> 
<script type="text/javascript" src="../view/pc/xt-view/xt-quartz-set/xt-quartz-set-list.js"></script>  
</html>