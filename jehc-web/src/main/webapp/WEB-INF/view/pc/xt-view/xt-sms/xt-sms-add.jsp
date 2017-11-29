<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短信配置表新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建短信配置表</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">用户名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_smsName" placeholder="请输入用户名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">短信接口密码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_smsPWD" placeholder="请输入短信接口密码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">URL地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="xt_smsURL" placeholder="请输入URL地址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">公司</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_smsCompany" placeholder="请输入公司">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_smsCompanTel" placeholder="请输入电话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">短信平台</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_smsValue" placeholder="请输入短信平台">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">公司地址</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="xt_smsCompanyAddress" placeholder="请输入公司地址"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_smsCompanyContacts" placeholder="请输入联系人">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">短信协议类型</label>
				<div class="col-lg-6">
					<select class="form-control" data-bv-numeric data-bv-numeric-message="请选择状态" name="xt_smsType">
						<option value="">请选择</option>
						<option value="0">http</option>
						<option value="1">其他</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" data-bv-numeric data-bv-numeric-message="请选择状态" name="xt_smsState">
						<option value="">请选择</option>
						<option value="0">正常</option>
						<option value="1">启用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtSms()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-sms/xt-sms-add.js"></script> 
</html>
