<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>任务调度配置信息表详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>任务调度配置信息表详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="id"  placeholder="请输入主键" value="${xtQuartz.id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="jobId" placeholder="请输入任务编号" value="${xtQuartz.jobId }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="jobName" placeholder="请输入任务名称" value="${xtQuartz.jobName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务分组</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="jobGroup" placeholder="请输入任务分组" value="${xtQuartz.jobGroup }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="jobStatus" style="width:150px;">
						<option value="PAUSED" <c:if test="${xtQuartz.jobStatus == 'PAUSED'}">selected</c:if> >暂停</option>
						<option value="NORMAL" <c:if test="${xtQuartz.jobStatus == 'NORMAL'}">selected</c:if> >启用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务运行时间表达式</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="cronExpression" placeholder="请输入任务运行时间表达式" value="${xtQuartz.cronExpression }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">任务描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="600"  name="desc_" placeholder="请输入任务描述">${xtQuartz.desc_ }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的类方法</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="150"  name="targetMethod" placeholder="请输入执行的类方法" value="${xtQuartz.targetMethod }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的类</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="500"  name="targetClass" placeholder="请输入执行的类" value="${xtQuartz.targetClass }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-quartz/xt-quartz-detail.js"></script> 
</html>
