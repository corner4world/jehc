<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>操作日志表详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>操作日志表详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">操作日志ID</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_operate_log_id"  placeholder="请输入操作日志ID" value="${xtOperateLogs.xt_operate_log_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的类名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="2000"  name="xt_operate_logClasseName" placeholder="请输入执行的类名" value="${xtOperateLogs.xt_operate_logClasseName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的方法名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="800"  name="xt_operate_logMethod" placeholder="请输入执行的方法名" value="${xtOperateLogs.xt_operate_logMethod }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">方法参数</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="xt_operate_logMethodPar" placeholder="请输入方法参数">${xtOperateLogs.xt_operate_logMethodPar }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行的结果</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="xt_operate_logResult" placeholder="请输入执行的结果">${xtOperateLogs.xt_operate_logResult }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">访问类型</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_operate_logType" placeholder="请输入访问类型" value="${xtOperateLogs.xt_operate_logType }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">访问地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="xt_operate_logUri" placeholder="请输入访问地址" value="${xtOperateLogs.xt_operate_logUri }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最大内存</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="最大内存为数字类型"  name="xt_operate_logMaxMemory" placeholder="请输入最大内存" value="${xtOperateLogs.xt_operate_logMaxMemory }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">已分配内存</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="已分配内存为数字类型"  name="xt_operate_logTotalMemory" placeholder="请输入已分配内存" value="${xtOperateLogs.xt_operate_logTotalMemory }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">已分配内存中的剩余空间</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="已分配内存中的剩余空间为数字类型"  name="xt_operate_logFreeMemory" placeholder="请输入已分配内存中的剩余空间" value="${xtOperateLogs.xt_operate_logFreeMemory }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最大可用内存</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="最大可用内存为数字类型"  name="xt_operate_logUseMemory" placeholder="请输入最大可用内存" value="${xtOperateLogs.xt_operate_logUseMemory }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">执行总时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_operate_logTotalTime" placeholder="请输入执行总时间" value="${xtOperateLogs.xt_operate_logTotalTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_operate_logBegTime" placeholder="请输入开始时间" value="${xtOperateLogs.xt_operate_logBegTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_operate_logEndTime" placeholder="请输入结束时间" value="${xtOperateLogs.xt_operate_logEndTime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人" value="${xtOperateLogs.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-operate-logs/xt-operate-logs-detail.js"></script> 
</html>
