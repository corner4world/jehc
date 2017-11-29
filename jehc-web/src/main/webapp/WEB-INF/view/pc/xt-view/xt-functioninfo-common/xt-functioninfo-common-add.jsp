<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公共功能新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建公共功能</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_functioninfo_common_title" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">方法名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="xt_functioninfo_common_method" placeholder="请输入方法名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">功能地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="xt_functioninfo_common_url" placeholder="请输入功能地址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_functioninfo_common_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="xt_functioninfo_common_content" placeholder="请输入备注"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtFunctioninfoCommon()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo-common/xt-functioninfo-common-add.js"></script> 
</html>
