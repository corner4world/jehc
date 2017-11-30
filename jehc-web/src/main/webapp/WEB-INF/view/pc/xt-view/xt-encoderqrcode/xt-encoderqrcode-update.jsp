<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台二维码编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑平台二维码</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">二维码编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_encoderqrcode_id"  placeholder="请输入二维码编号" value="${xtEncoderqrcode.xt_encoderqrcode_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="xt_encoderqrcode_title" placeholder="请输入标题" value="${xtEncoderqrcode.xt_encoderqrcode_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">二维码链接地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="1000"  data-bv-notempty data-bv-notempty-message="请输入二维码链接地址"  name="xt_encoderqrcode_url" placeholder="请输入二维码链接地址" value="${xtEncoderqrcode.xt_encoderqrcode_url }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="500"  name="xt_encoderqrcode_content" placeholder="请输入备注">${xtEncoderqrcode.xt_encoderqrcode_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">二维码图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${xtEncoderqrcode.xt_attachment_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtEncoderqrcode()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-update.js"></script> 
</html>
