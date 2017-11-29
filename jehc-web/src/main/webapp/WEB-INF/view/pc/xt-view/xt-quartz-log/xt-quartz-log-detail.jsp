<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>调度器日志详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>调度器日志详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_quartz_log_id"  placeholder="请输入主键" value="${xtQuartzLog.xt_quartz_log_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_quartz_log_name" placeholder="请输入名称" value="${xtQuartzLog.xt_quartz_log_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">调度键</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_quartz_log_key" placeholder="请输入调度键" value="${xtQuartzLog.xt_quartz_log_key }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">调度内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="500"  name="xt_quartz_log_content" placeholder="请输入调度内容">${xtQuartzLog.xt_quartz_log_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="xt_quartz_log_ctime" placeholder="请输入开始时间" value="${xtQuartzLog.xt_quartz_log_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="xt_quartz_log_etime" placeholder="请输入结束时间" value="${xtQuartzLog.xt_quartz_log_etime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人编号" value="${xtQuartzLog.xt_userinfo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">运行标识</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_quartz_log_flag" placeholder="请输入运行标识" value="${xtQuartzLog.xt_quartz_log_flag }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-quartz-log/xt-quartz-log-detail.js"></script> 
</html>
