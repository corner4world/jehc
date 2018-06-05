<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台信息发布新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建平台信息发布</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_platform_title" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="10"  name="xt_platform_status" placeholder="请输入状态0正常1关闭">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">注备</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="500"  name="xt_platform_remark" placeholder="请输入注备">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_platform_ctime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_platform_mtime"  placeholder="请选择时间">
				</div>
			</div>
			<div class="form-group" style="display:none;">
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xtPlatformFeedback_removed_flag" id="xtPlatformFeedback_removed_flag" >
					<input class="form-control" type="hidden" value="0" name="xtPlatformFeedbackFormNumber" id="xtPlatformFeedbackFormNumber" >
				</div>
			</div>
			<!-- 一对多子表开始（平台反馈意见） -->
			<div class="page-header">
				<h4>平台反馈意见</h4>
			</div>
			<div class="form-group">
				<div class="col-lg-4">
					<a class="btn btn-mini btn-primary glyphicon glyphicon-plus" href="javascript:addXtPlatformFeedbackItems()" role="button">新一行</a>
				</div>
			</div>
			<div class="form_xtPlatformFeedback">
			</div>
			<!-- 一对多子表结束（平台反馈意见） -->
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtPlatform()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-platform/xt-platform-add.js"></script> 
</html>
