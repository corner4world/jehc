<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>异常日志表详情页面</title>
</head>
<body>
	<div class="m-portlet">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<span class="m-portlet__head-icon m--hide">
					<i class="la la-gear"></i>
					</span>
					<h3 class="m-portlet__head-text">
						日志详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">异常ID</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_error_log_id"  placeholder="请输入异常ID" value="${xtErrorLogs.xt_error_log_id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">异常日志内容</label>
					<div class="col-lg-6">
						<textarea class="form-control" rows="10" maxlength="65535"  name="xt_error_logContent" placeholder="请输入异常日志内容">${xtErrorLogs.xt_error_logContent }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">异常日志级别</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="异常日志级别为数字类型"  name="xt_error_logType" placeholder="请输入异常日志级别" value="${xtErrorLogs.xt_error_logType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">日志出错时间</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_error_logTime" placeholder="请输入日志出错时间" value="${xtErrorLogs.xt_error_logTime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">操作人</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人ID外键" value="${xtErrorLogs.xt_userinfo_realName }">
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
						</div>
						<div class="col m--align-right">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-error-logs/xt-error-logs-detail.js"></script> 
</html>
