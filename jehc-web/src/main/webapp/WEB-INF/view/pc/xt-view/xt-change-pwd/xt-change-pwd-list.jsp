<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>密码找回中心</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<div class="form-group">
					<label>用户名称</label>
					<input type="text" class="form-control" name="user_name" placeholder="请输入用户名称">
				</div>
				<div class="form-group">
					<label>性别</label>
					<input type="text" class="form-control" name="sex" placeholder="请输入性别">
				</div>
				<div class="form-group">
					<label>手机号码</label>
					<input type="text" class="form-control" name="phone" placeholder="请输入手机号码">
				</div>
				<div class="form-group">
					<label>申请时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="ctime_st" />
						<span class="input-group-addon">至</span>
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="ctime_et" />
					</div>
				</div>
			</form>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-left" style="margin-right: 20px;">
			<button class="btn btn-primary" onclick="search('datatables')">
				<i class="glyphicon glyphicon-search"></i>&nbsp;检索
			</button>
			<button class="btn btn-default" onclick="resetAll();">重置</button>
			<button class="btn btn-default" onclick="delXtChangePwd()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>用户名称</th>
					<th>登录账号</th>
					<th>性别</th>
					<th>手机号码</th>
					<th>邮箱地址</th>
					<th>创建时间</th>
					<th>状态</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-change-pwd/xt-change-pwd-list.js"></script> 
</html>
