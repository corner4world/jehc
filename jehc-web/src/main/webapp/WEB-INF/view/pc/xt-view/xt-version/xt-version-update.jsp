<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台版本编辑页面</title>
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
						编辑平台版本
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-1 control-label">平台版本编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_version_id"  placeholder="请输入平台版本编号" value="${xtVersion.xt_version_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">版本名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="100"  name="xt_version_name" placeholder="请输入版本名称" value="${xtVersion.xt_version_name }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">版本描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2000"  name="xt_version_remark" placeholder="请输入版本描述">${xtVersion.xt_version_remark }</textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">附件</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${xtVersion.xt_attachment_id }">
						<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="updateXtVersion()">保存</button>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-version/xt-version-update.js"></script> 
</html>
