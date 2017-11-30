<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>分表策略表新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建分表策略表</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库表名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入数据库表名"  name="tableName" placeholder="请输入数据库表名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">唯一模块标识</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="onlyModulesFlag" placeholder="请输入唯一模块标识">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtDbtableStrategy()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-dbtable-strategy/xt-dbtable-strategy-add.js"></script> 
</html>
