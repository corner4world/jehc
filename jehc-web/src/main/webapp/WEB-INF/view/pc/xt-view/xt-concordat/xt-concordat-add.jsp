<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>合同管理新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建合同管理</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">合同名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_concordatName" placeholder="请输入合同名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">合同描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="50"  name="xt_concordatDesc" placeholder="请输入合同描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtConcordat()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-concordat/xt-concordat-add.js"></script> 
</html>
