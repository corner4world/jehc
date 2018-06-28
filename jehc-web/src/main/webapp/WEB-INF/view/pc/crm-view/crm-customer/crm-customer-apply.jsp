<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户基础资料详情页面</title>
</head>
<body>
	<div class="panel-body">
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="customerId"  placeholder="请输入Id主键" value="${crmCustomer.customerId }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
				<div class="col-lg-6">
					<input class="form-control" disabled="disabled" type="text" maxlength="255"  name="name" placeholder="请输入客户名称" value="${crmCustomer.name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
				<div class="col-lg-6">
					<input class="form-control" disabled="disabled" type="text" maxlength="64"  name="shortName" placeholder="请输入客户简称" value="${crmCustomer.shortName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">维&nbsp;护&nbsp;人：${crmCustomer.xt_userinfo_realName }</label>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-customer/crm-customer-apply.js"></script> 
</html>
