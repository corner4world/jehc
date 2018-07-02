<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>任务表</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
			<div class="caption">
				查询区域
			</div>
		</div>
		<div class="portlet-body form">
			<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>任务标题</label>
					<input type="text" class="form-control" name="oa_taskTitle" placeholder="请输入任务标题">
				</div>
				<div class="form-group">
					<label>是否全天</label>
					<select class="form-control" name="oa_taskIsAllDay" placeholder="请选择">
						<option value="">请选择</option>
						<option value="1">是</option>
						<option value="2">否</option>
					</select>
				</div>
				<div class="form-group">
					<label>级别</label>
					<select class="form-control" name="oa_taskTightness" placeholder="请选择">
						<option value="">请选择</option>
						<option value="1">一般</option>
						<option value="2">重要</option>
						<option value="3">紧急</option>
					</select>
				</div>
				<div class="form-group">
					<label>任务类型</label>
					<select class="form-control" name="oa_taskType" placeholder="请选择">
						<option value="">请选择</option>
						<option value="1">个人</option>
						<option value="2">公开</option>
					</select>
				</div>&nbsp;
				<a class="btn btn-outline-primary waves-light waves-effect" href="javascript:search('datatables');">
					<i class="fa fa-search m-r-5"></i>&nbsp;检索
				</a>&nbsp;
				<a class="btn btn-outline-danger waves-light waves-effect" href="javascript:resetAll();">
					<i class="fa fa-remove m-r-5"></i>&nbsp;重置
				</a>
			</form>
		</div>
	</div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
		<button class="btn btn-outline-success waves-light waves-effect" onclick="toOaTaskAdd()">
			<i class="fa fa-plus-circle"></i>新增
		</button>
		<button class="btn btn-outline-info waves-light waves-effect" onclick="toOaTaskUpdate()">
			<i class="fa fa-pencil"></i>修改
		</button>
		<button class="btn btn-outline-danger waves-light waves-effect" onclick="delOaTask()">
			<i class="fa fa-trash-o"></i>删除
		</button>
		<button class="btn btn-outline-warning waves-light waves-effect" onclick="search('datatables')">
			<i class="fa fa-spin fa-refresh"></i>刷新
		</button>
	</div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>任务标题</th>
				<th>是否全天</th>
				<th>任务类型</th>
				<th>级别</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-task/oa-task-list.js"></script> 
</html>
