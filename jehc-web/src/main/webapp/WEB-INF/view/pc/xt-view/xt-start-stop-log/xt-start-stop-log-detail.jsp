<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>服务器启动与关闭日志; InnoDB free: 9216 kB详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>服务器启动与关闭日志详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_start_stop_log_id"  placeholder="请输入主键" value="${xtStartStopLog.xt_start_stop_log_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">服务器启动时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_start_stop_log_starttime" placeholder="请输入服务器启动时间" value="${xtStartStopLog.xt_start_stop_log_starttime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">服务器停止时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_start_stop_log_stoptime" placeholder="请输入服务器停止时间" value="${xtStartStopLog.xt_start_stop_log_stoptime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否出错</label>
				<div class="col-lg-6">
					<label class="control-label">
					<c:if test="${xtStartStopLog.xt_start_stop_log_iserror eq 0 }">正常</c:if>
					<c:if test="${xtStartStopLog.xt_start_stop_log_iserror eq 1 }">错误</c:if>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">加载内容</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="65535"  name="xt_start_stop_log_content" placeholder="请输入加载内容" value="${xtStartStopLog.xt_start_stop_log_content }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-start-stop-log/xt-start-stop-log-detail.js"></script> 
</html>
