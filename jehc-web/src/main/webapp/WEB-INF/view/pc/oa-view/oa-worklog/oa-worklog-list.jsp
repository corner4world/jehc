<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>工作日志</title>
</head>
<body>
	<div class="panel panel-default">
		<div class="panel-heading">查询条件</div>
		<div class="panel-body form-group" style="margin-bottom: 0px;">
			<form method="POST" id="searchForm">
				<div class="form-group col-lg-2 col-md-3 col-sm-4 col-xs-6">
					<label>标题</label> 
					<input type="text" class="input-sm form-control" name="oa_worklogTitle" placeholder="输入标题">
				</div>
				<div class="form-group col-lg-2 col-md-4 col-sm-4 col-xs-12">
					<label>创建时间</label>
					<div class="input-daterange input-group" id="date">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="oa_worklogCreateTime_st" />
						<span class="input-group-addon">至</span> 
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="oa_worklogCreateTime_et" />
					</div>
				</div>
			</form>
			<div class="col-md-offset-0 col-md-6">
				<button class="btn btn-sm btn-default" onclick="search('datatables')">
					<i class="glyphicon glyphicon-search"></i>&nbsp;检索
				</button>
				<button class="btn btn-sm btn-default" onclick="resetAll();">重置</button>
			</div>
		</div>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="toOaWorklogAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-default" onclick="toOaWorklogUpdate()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="delOaWorklog()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<table id="datatables"
			class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><input type="checkbox" class="checkall" /></th>
					<th>序号</th>
					<th>日志标题</th>
					<th>日志内容</th>
					<th>创建人</th>
					<th>创建时间</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-worklog/oa-worklog-list.js"></script> 
</html>
