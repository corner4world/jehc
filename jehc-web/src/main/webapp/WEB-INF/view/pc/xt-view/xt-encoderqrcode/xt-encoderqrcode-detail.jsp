<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台二维码详情页面</title>
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
						平台二维码详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="xt_encoderqrcode_title" placeholder="请输入标题" value="${xtEncoderqrcode.xt_encoderqrcode_title }">
					</div>
				</div>
				<div class="form-group" style="display:none;">
					<label class="col-lg-1 control-label">二维码编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_encoderqrcode_id"  placeholder="请输入二维码编号" value="${xtEncoderqrcode.xt_encoderqrcode_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="500"  name="xt_encoderqrcode_content" placeholder="请输入备注">${xtEncoderqrcode.xt_encoderqrcode_content }</textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">二维码图片</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${xtEncoderqrcode.xt_attachment_id }">
						<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">创建时间</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="xt_encoderqrcode_ctime"  data-bv-notempty data-bv-notempty-message="请输入创建时间"  placeholder="请选择时间" value="${xtEncoderqrcode.xt_encoderqrcode_ctime }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">修改时间</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="xt_encoderqrcode_mtime"  placeholder="请选择时间" value="${xtEncoderqrcode.xt_encoderqrcode_mtime }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">二维码链接地址</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="1000"  data-bv-notempty data-bv-notempty-message="请输入二维码链接地址"  name="xt_encoderqrcode_url" placeholder="请输入二维码链接地址" value="${xtEncoderqrcode.xt_encoderqrcode_url }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">创&nbsp;建&nbsp;人</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入创建人"  name="xt_userinfo_id" placeholder="请输入创建人" value="${xtEncoderqrcode.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-detail.js"></script> 
</html>
