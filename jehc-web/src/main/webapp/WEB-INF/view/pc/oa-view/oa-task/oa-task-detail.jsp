<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>任务表详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>任务表详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">任务ID自动自增列主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_task_id"  placeholder="请输入任务ID自动自增列主键" value="${oaTask.oa_task_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入任务标题"  name="oa_taskTitle" placeholder="请输入任务标题" value="${oaTask.oa_taskTitle }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskBegTime"  placeholder="请选择时间" value="${oaTask.oa_taskBegTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始分钟</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskBegApTime"  placeholder="请选择时间" value="${oaTask.oa_taskBegApTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskEndTime"  placeholder="请选择时间" value="${oaTask.oa_taskEndTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束分钟</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="oa_taskEndApTime"  placeholder="请选择时间" value="${oaTask.oa_taskEndApTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否全天</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="是否全天为数字类型"  name="oa_taskIsAllDay" placeholder="请输入是否全天" value="${oaTask.oa_taskIsAllDay }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务类型</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="任务类型为数字类型"  name="oa_taskType" placeholder="请输入任务类型" value="${oaTask.oa_taskType }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">事件发生前多少提醒0表示不提醒</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="事件发生前多少提醒0表示不提醒为数字类型"  name="oa_taskRemindType" placeholder="请输入事件发生前多少提醒0表示不提醒" value="${oaTask.oa_taskRemindType }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">紧急程度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="紧急程度为数字类型"  name="oa_taskTightness" placeholder="请输入紧急程度" value="${oaTask.oa_taskTightness }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="oa_taskContent" placeholder="请输入任务描述">${oaTask.oa_taskContent }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${oaTask.xt_userinfo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-task/oa-task-detail.js"></script> 
</html>
