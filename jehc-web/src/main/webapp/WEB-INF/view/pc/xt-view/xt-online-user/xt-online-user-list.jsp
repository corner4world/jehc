<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告</title>
</head>
<body>
	<div class="panel panel-default">
		<h3>在线用户列表</h3>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="removeAllXtOnLineUser()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>移除全部
			</button>
			<button class="btn btn-default" onclick="removeXtOnLineUser()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>移除
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
					<th>登录账号</th>
					<th>姓名</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-online-user/xt-online-user-list.js"></script> 
</html>
