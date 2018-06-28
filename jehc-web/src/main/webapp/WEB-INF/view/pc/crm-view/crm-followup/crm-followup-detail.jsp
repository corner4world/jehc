<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户跟进日志详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>客户跟进日志详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">客户跟进日志主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="followupId"  placeholder="请输入客户跟进日志主键" value="${crmFollowup.followupId }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">跟进人</label>
				<div class="col-lg-5">
					<input class="form-control" readonly="readonly" type="text" maxlength="32"  name="xt_userinfo_realName" value="${crmFollowup.xt_userinfo_realName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">填写日志时间</label>
				<div class="col-lg-5">
					<input class="form_datetime form-control" readonly="readonly" type="text" name="ctime" value="<fmt:formatDate value='${crmFollowup.ctime}' pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">跟进日期</label>
				<div class="col-lg-5">
					<input class="form-control" type="hidden" maxlength="32" id="customerId" value="${crmFollowup.customerId }" name="customerId" placeholder="请输入客户编号">
					<input class="form_datetime form-control" readonly="readonly" type="text" name="followupTime" value="<fmt:formatDate value='${crmFollowup.followupTime}' pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-5">
					<textarea rows="8" cols="120" class="form-control" readonly="readonly" name="content" placeholder="请输入内容">${crmFollowup.content }</textarea>
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
<script type="text/javascript" src="../view/pc/crm-view/crm-followup/crm-followup-detail.js"></script> 
</html>
