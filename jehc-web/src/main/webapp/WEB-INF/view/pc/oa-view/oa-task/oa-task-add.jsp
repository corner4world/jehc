<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>任务表新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建任务表</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">任务标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入任务标题"  name="oa_taskTitle" placeholder="请输入任务标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskBegTime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始分钟</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskBegApTime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskEndTime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束分钟</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskEndApTime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否全天</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="是否全天为数字类型"  name="oa_taskIsAllDay" placeholder="请输入是否全天">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务类型</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="任务类型为数字类型"  name="oa_taskType" placeholder="请输入任务类型">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">事件发生前多少提醒0表示不提醒</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="事件发生前多少提醒0表示不提醒为数字类型"  name="oa_taskRemindType" placeholder="请输入事件发生前多少提醒0表示不提醒">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">紧急程度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="紧急程度为数字类型"  name="oa_taskTightness" placeholder="请输入紧急程度">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="oa_taskContent" placeholder="请输入任务描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addOaTask()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-task/oa-task-add.js"></script> 
</html>
