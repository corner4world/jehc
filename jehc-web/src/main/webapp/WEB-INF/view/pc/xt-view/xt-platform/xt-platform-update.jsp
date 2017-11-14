<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台信息发布编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑平台信息发布</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_platform_id"  placeholder="请输入主键" value="${xtPlatform.xt_platform_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_platform_title" placeholder="请输入标题" value="${xtPlatform.xt_platform_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态0正常1关闭</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="10"  name="xt_platform_status" placeholder="请输入状态0正常1关闭" value="${xtPlatform.xt_platform_status }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人" value="${xtPlatform.xt_userinfo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">注备</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="500"  name="xt_platform_remark" placeholder="请输入注备" value="${xtPlatform.xt_platform_remark }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_platform_ctime"  placeholder="请选择时间" value="${xtPlatform.xt_platform_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_platform_mtime"  placeholder="请选择时间" value="${xtPlatform.xt_platform_mtime }">
				</div>
			</div>
			<div class="form-group" style="display:none;">
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xtPlatformFeedback_removed_flag" id="xtPlatformFeedback_removed_flag" >
					<input class="form-control" type="hidden" value="${fn:length(xtPlatform.xtPlatformFeedback) }" name="xtPlatformFeedbackFormNumber" id="xtPlatformFeedbackFormNumber" >
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
			<c:forEach var="xtPlatformFeedback" items="${xtPlatform.xtPlatformFeedback }" varStatus="xtPlatformFeedbackStatus">
				<div id="form_xtPlatformFeedback_${xtPlatformFeedbackStatus.index}">
			<fieldset>
			<legend><h5>序号${xtPlatformFeedbackStatus.index+1}</h5></legend>
			<div class="form-group"><div class="col-lg-3"><a class="btn btn-danger" href="javascript:delXtPlatformFeedbackItems(this,${xtPlatformFeedbackStatus.index})" >删除该条信息</a></div></div>
					<div class="form-group">
						<label class="col-lg-3 control-label">主键编号</label>
						<div class="col-lg-6">
							<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入主键编号"  id="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_id" name="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_id" placeholder="请输入主键编号" value="${xtPlatformFeedback.xt_platform_feedback_id }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">创建时间</label>
						<div class="col-lg-6">
							<input class="form_datetime form-control" id="xtPlatformFeedback_${xtPlatformFeedbackStatus.index}_xt_platform_feedback_ctime" name="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_ctime"  placeholder="请选择时间" value="${xtPlatformFeedback.xt_platform_feedback_ctime }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">评论人编号</label>
						<div class="col-lg-6">
							<input class="form-control" type="text" maxlength="32"  id="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_userinfo_id" name="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_userinfo_id" placeholder="请输入评论人编号" value="${xtPlatformFeedback.xt_userinfo_id }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">评论内容</label>
						<div class="col-lg-6">
							<textarea class="form-control" maxlength="500"  id="xtPlatformFeedback_${xtPlatformFeedbackStatus.index}_xt_platform_feedback_remark" name="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_remark" placeholder="请输入评论内容">${xtPlatformFeedback.xt_platform_feedback_remark }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">状态0正常1隐藏</label>
						<div class="col-lg-6">
							<input class="form-control" type="text" maxlength="10"  id="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_status" name="xtPlatformFeedback[${xtPlatformFeedbackStatus.index}].xt_platform_feedback_status" placeholder="请输入状态0正常1隐藏" value="${xtPlatformFeedback.xt_platform_feedback_status }">
						</div>
					</div>
				</fieldset>
				</div>
				</c:forEach>
			</div>
			<!-- 一对多子表结束（平台反馈意见） -->
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtPlatform()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-platform/xt-platform-update.js"></script> 
</html>
