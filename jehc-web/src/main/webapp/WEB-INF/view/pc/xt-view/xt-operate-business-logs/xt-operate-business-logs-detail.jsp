<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台业务操作日志表详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>平台业务操作日志表详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">平台业务操作日志ID</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_operate_business_logs_id"  placeholder="请输入平台业务操作日志ID" value="${xtOperateBusinessLogs.xt_operate_business_logs_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">模块</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="2000"  name="xt_operate_business_logsModules" placeholder="请输入模块" value="${xtOperateBusinessLogs.xt_operate_business_logsModules }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行方法</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="800"  name="xt_operate_business_logsMethod" placeholder="请输入执行方法" value="${xtOperateBusinessLogs.xt_operate_business_logsMethod }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">方法参数</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="xt_operate_business_logsMethodPar" placeholder="请输入方法参数">${xtOperateBusinessLogs.xt_operate_business_logsMethodPar }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的结果</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="xt_operate_business_logsResult" placeholder="请输入执行的结果">${xtOperateBusinessLogs.xt_operate_business_logsResult }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_operate_business_logsTime" placeholder="请输入操作时间" value="${xtOperateBusinessLogs.xt_operate_business_logsTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人" value="${xtOperateBusinessLogs.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-operate-business-logs/xt-operate-business-logs-detail.js"></script> 
</html>
