<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>数据库信息表新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建数据库信息表</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoName" placeholder="请输入数据库名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库用户名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoUName" placeholder="请输入数据库用户名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库密码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoPwd" placeholder="请输入数据库密码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备份IP</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoIp" placeholder="请输入备份IP">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">端口号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoPort" placeholder="请输入端口号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_dbinfoType">
						<option value="mysql">mysql</option>
						<option value="Oracle">Oracle</option>
						<option value="Sqlserver">Sqlserver</option>
						<option value="db2">db2</option>
						<option value="syBase">syBase</option>
						<option value="Access">Access</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtDbinfo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-dbinfo/xt-dbinfo-add.js"></script> 
</html>
