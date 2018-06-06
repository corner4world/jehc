<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>工作日志</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
		    <legend>查询条件</legend>
		    <form method="POST" id="searchForm" class="form-inline">
				<div class="form-group">
					<label>标题</label> 
					<input type="text" class="form-control" name="oa_worklogTitle" placeholder="输入标题">
				</div>
				<div class="form-group">
					<label>创建时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="oa_worklogCreateTime_st" />
						<span class="input-group-addon">至</span> 
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="oa_worklogCreateTime_et" />
					</div>
				</div>
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-primary" onclick="toOaWorklogAdd()" title="新增">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-warning" onclick="toOaWorklogUpdate()" title="修改">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-danger " onclick="delOaWorklog()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true" title="删除"></span>删除
			</button>
			<button class="btn btn-success" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true" title="刷新"></span>刷新
			</button>
		</div>
		<table id="datatables" class="table table-striped table-bordered table-hover table-checkable order-column">
			<thead>
				<tr>
					<th>
						<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label>
					</th>
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
