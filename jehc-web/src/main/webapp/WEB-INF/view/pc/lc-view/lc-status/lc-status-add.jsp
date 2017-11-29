<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程状态新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建流程状态</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">状态名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="lc_status_name" placeholder="请输入状态名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="lc_status_remark" placeholder="请输入备注"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程常量</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="lc_status_constant" placeholder="请输入流程常量">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addLcStatus()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/lc-view/lc-status/lc-status-add.js"></script> 
</html>
