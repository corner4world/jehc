<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>xt_appkey_appsecret编辑页面</title>
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
						编辑秘钥
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">主键</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_appkey_appsecret_id"  placeholder="请输入主键" value="${xtAppkeyAppsecret.xt_appkey_appsecret_id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_appkey_appsecret_name" placeholder="请输入名称" value="${xtAppkeyAppsecret.xt_appkey_appsecret_name }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">状态</label>
					<div class="col-lg-6">
						<select class="form-control" name="xt_appkey_appsecret_status" placeholder="请选择">
							<option value="">请选择</option>
							<option value="0" <c:if test="${xtAppkeyAppsecret.xt_appkey_appsecret_status eq 0}">selected</c:if> >正常</option>
							<option value="1" <c:if test="${xtAppkeyAppsecret.xt_appkey_appsecret_status eq 1}">selected</c:if>>禁用</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">注备</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="800"  name="xt_appkey_appsecret_remark" placeholder="请输入注备">${xtAppkeyAppsecret.xt_appkey_appsecret_remark }</textarea>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="updateXtAppkeyAppsecret()">保存</button>
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
						<div class="col m--align-right">
							<a href="javascript:resetAll('defaultForm')" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
								<span><i class="fa fa-repeat"></i><span>重置</span></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-appkey-appsecret/xt-appkey-appsecret-update.js"></script> 
</html>
