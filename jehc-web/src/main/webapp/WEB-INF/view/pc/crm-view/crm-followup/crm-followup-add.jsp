<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户跟进日志新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建跟进日志---【${crmCustomer.name }】</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">跟进内容</label>
				</div>
				<div class="col-md-2">
					<textarea rows="8" cols="120" name="content" maxlength="255" placeholder="请输入跟进内容"></textarea>
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">跟进日期</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" maxlength="32" id="customerId" value="${crmFollowup.customerId }" name="customerId" placeholder="请输入客户编号">
					<input class="form_datetime form-control" name="followupTime" style="width:150px" placeholder="请选择跟进日期">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addCrmFollowup()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-followup/crm-followup-add.js"></script> 
</html>
