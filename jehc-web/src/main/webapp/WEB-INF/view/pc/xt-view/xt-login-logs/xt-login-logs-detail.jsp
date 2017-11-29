<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>登录日志详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>登录日志详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">日志编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_login_log_id"  placeholder="请输入日志编号" value="${xtLoginLogs.xt_login_log_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">ip</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_login_logIP" placeholder="请输入登录IP" value="${xtLoginLogs.xt_login_logIP }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">登录时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_login_logtime" placeholder="请输入登录时间" value="${xtLoginLogs.xt_login_logtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">登陆内容日志</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="xt_login_logContent" placeholder="请输入登陆内容日志">${xtLoginLogs.xt_login_logContent }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">浏览器类型</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_login_log_browser_type" placeholder="请输入浏览器类型" value="${xtLoginLogs.xt_login_log_browser_type }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">浏览器名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_login_log_browser_name" placeholder="请输入浏览器名称" value="${xtLoginLogs.xt_login_log_browser_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">浏览器版本</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_login_log_browser_version" placeholder="请输入浏览器版本" value="${xtLoginLogs.xt_login_log_browser_version }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作系统</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_login_log_system" placeholder="请输入操作系统" value="${xtLoginLogs.xt_login_log_system }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-login-logs/xt-login-logs-detail.js"></script> 
</html>
