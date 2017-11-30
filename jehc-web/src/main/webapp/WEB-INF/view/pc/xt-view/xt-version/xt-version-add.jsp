<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台版本新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建平台版本</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">版本名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_version_name" placeholder="请输入版本名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">版本描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2000"  name="xt_version_remark" placeholder="请输入版本描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtVersion()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-version/xt-version-add.js"></script> 
</html>
