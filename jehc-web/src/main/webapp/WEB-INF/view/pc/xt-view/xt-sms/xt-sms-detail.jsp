<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短信配置表详情页面</title>
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
						短信配置表详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-1 control-label">ID</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_sms_id"  placeholder="请输入ID" value="${xtSms.xt_sms_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">用&nbsp;户&nbsp;名</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  name="xt_smsName" placeholder="请输入用户名" value="${xtSms.xt_smsName }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">短信接口密码</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  name="xt_smsPWD" placeholder="请输入短信接口密码" value="${xtSms.xt_smsPWD }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">URL地址</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="200"  name="xt_smsURL" placeholder="请输入URL地址" value="${xtSms.xt_smsURL }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">公&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="100"  name="xt_smsCompany" placeholder="请输入公司" value="${xtSms.xt_smsCompany }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_smsCompanTel" placeholder="请输入电话" value="${xtSms.xt_smsCompanTel }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">短信平台</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_smsValue" placeholder="请输入短信平台" value="${xtSms.xt_smsValue }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">公司地址</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="200"  name="xt_smsCompanyAddress" placeholder="请输入公司地址">${xtSms.xt_smsCompanyAddress }</textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">联&nbsp;系&nbsp;人</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_smsCompanyContacts" placeholder="请输入联系人" value="${xtSms.xt_smsCompanyContacts }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">短信协议类型</label>
					<div class="col-lg-6">
						<select class="form-control" data-bv-numeric data-bv-numeric-message="请选择类型" name="xt_smsType">
							<option value="">请选择</option>
							<option value="0" <c:if test="${xtSms.xt_smsType eq 0 }">selected</c:if>>http</option>
							<option value="1" <c:if test="${xtSms.xt_smsType eq 1 }">selected</c:if>>其他</option>
						</select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
					<div class="col-lg-6">
						<select class="form-control" data-bv-numeric data-bv-numeric-message="请选择状态" name="xt_smsState">
							<option value="">请选择</option>
							<option value="0" <c:if test="${xtSms.xt_smsState eq 0 }">selected</c:if>>正常</option>
							<option value="1" <c:if test="${xtSms.xt_smsState eq 1 }">selected</c:if>>启用</option>
						</select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">创建时间</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_smsCtime" placeholder="请输入创建时间" value="${xtSms.xt_smsCtime }">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--solid">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
						<div class="col m--align-right">
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-sms/xt-sms-detail.js"></script> 
</html>
