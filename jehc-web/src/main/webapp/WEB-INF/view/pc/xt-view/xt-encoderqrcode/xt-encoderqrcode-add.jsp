<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台二维码新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建平台二维码</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="xt_encoderqrcode_title" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">二维码链接地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="1000"  data-bv-notempty data-bv-notempty-message="请输入二维码链接地址"  name="xt_encoderqrcode_url" placeholder="请输入二维码链接地址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="500"  name="xt_encoderqrcode_content" placeholder="请输入备注"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtEncoderqrcode()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-encoderqrcode/xt-encoderqrcode-add.js"></script> 
</html>
