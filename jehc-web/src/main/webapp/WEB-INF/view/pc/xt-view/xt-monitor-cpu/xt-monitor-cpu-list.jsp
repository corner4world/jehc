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
<title>监控管理(CPU)</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-monitor-cpu/xt-monitor-cpu-list.js"></script>  
</head>  
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	中央处理器检测列表
            </div>
            <div class="actions">
                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('datatables');">
                     <i class="glyphicon glyphicon-search"></i>
                 </a>
                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll();;">
                     <i class="icon-trash"></i>
                 </a>
            </div>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="delXtMonitorCpu()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
    </div>
    
	<table id="datatables" class="table table-bordered table-striped table-hover"style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>生产商</th>
				<th>用户使用率</th>
				<th>系统使用率</th>
				<th>当前等待率</th>
				<th>当前错误率</th>
				<th>当前空闲率</th>
				<th>总使用率</th>
				<th>取读时间</th>
			</tr>
		</thead>
	</table>
</body>
</html> 