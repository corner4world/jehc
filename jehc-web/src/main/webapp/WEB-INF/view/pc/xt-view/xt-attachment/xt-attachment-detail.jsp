<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>附件管理详情页面</title>
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
						附件详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">附件编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_attachment_id"  placeholder="请输入附件编号" value="${xtAttachment.xt_attachment_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">附件名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_attachmentName" placeholder="请输入附件名称" value="${xtAttachment.xt_attachmentName }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">附件原名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_attachmentTitle" placeholder="请输入附件原名称" value="${xtAttachment.xt_attachmentTitle }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">件文类型</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="500"  name="xt_attachmentType" placeholder="请输入件文类型" value="${xtAttachment.xt_attachmentType }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">件文大小</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_attachmentSize" placeholder="请输入件文大小" value="${xtAttachment.xt_attachmentSize }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">文件相对路径</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_attachmentPath" placeholder="请输入文件相对路径" value="${xtAttachment.xt_attachmentPath }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
					<div class="col-lg-6">
						<c:if test="${xtAttachment.xt_attachmentIsDelete eq 0 }">
							<label class="control-label">正常</label>
						</c:if>
						<c:if test="${xtAttachment.xt_attachmentIsDelete eq 1 }">
							<label class="control-label">删除</label>
						</c:if>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">顺&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="顺序为数字类型"  name="xt_modules_order" placeholder="请输入顺序" value="${xtAttachment.xt_modules_order }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">平台路径配置中心键（自定义上传绝对路径使用）</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_path_absolutek" placeholder="请输入平台路径配置中心键（自定义上传绝对路径使用）" value="${xtAttachment.xt_path_absolutek }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">平台路径配置中心键（自定义上传相对路径使用）</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_path_relativek" placeholder="请输入平台路径配置中心键（自定义上传相对路径使用）" value="${xtAttachment.xt_path_relativek }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">平台路径配置中心键（自定义上传路径 自定义URL地址）</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_path_urlk" placeholder="请输入平台路径配置中心键（自定义上传路径 自定义URL地址）" value="${xtAttachment.xt_path_urlk }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">上传时间</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="20"  name="xt_attachmentCtime" placeholder="请输入文件上传时间" value="${xtAttachment.xt_attachmentCtime }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-3 control-label">传&nbsp;上&nbsp;者</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入传上者编号" value="${xtAttachment.xt_userinfo_realName }">
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
						</div>
						<div class="col m--align-right">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-attachment/xt-attachment-detail.js"></script> 
</html>
