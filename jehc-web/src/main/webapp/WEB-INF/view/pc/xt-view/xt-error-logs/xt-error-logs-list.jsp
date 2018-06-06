<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>异常日志表</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	异常日志列表
            </div>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="delXtErrorLogs()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
    </div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>异常日志内容</th>
				<th>异常日志级别</th>
				<th>异常日志出错时间</th>
				<th>操作人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-error-logs/xt-error-logs-list.js"></script> 
</html>
