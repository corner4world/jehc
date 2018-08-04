<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台业务操作日志表详情页面</title>
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
					<label class="col-lg-3 control-label">平台业务操作日志ID</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_operate_b_logs_id"  placeholder="请输入平台业务操作日志ID" value="${xtOperateBusinessLogs.xt_operate_b_logs_id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">模块</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="2000"  name="xt_operate_b_logsModules" placeholder="请输入模块" value="${xtOperateBusinessLogs.xt_operate_b_logsModules }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">执行方法</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="800"  name="xt_operate_b_logsMethod" placeholder="请输入执行方法" value="${xtOperateBusinessLogs.xt_operate_b_logsMethod }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">方法参数</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  name="xt_operate_b_logsMethodPar" placeholder="请输入方法参数">${xtOperateBusinessLogs.xt_operate_b_logsMethodPar }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">执行的结果</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  name="xt_operate_b_logsResult" placeholder="请输入执行的结果">${xtOperateBusinessLogs.xt_operate_b_logsResult }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">操作时间</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_operate_b_logsTime" placeholder="请输入操作时间" value="${xtOperateBusinessLogs.xt_operate_b_logsTime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">操作人</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人" value="${xtOperateBusinessLogs.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-operate-business-logs/xt-operate-business-logs-detail.js"></script> 
</html>
