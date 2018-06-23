<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>菜单资源模块新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建资源模块</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">模块名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="title" placeholder="请输入模块名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">唯一值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入唯一值"  name="keyid" placeholder="请输入唯一值">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="255"  name="remark" placeholder="请输入描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status">
						<option value="0">激活</option>
						<option value="1">关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtMenuinfoModule()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-add.js"></script> 
</html>
